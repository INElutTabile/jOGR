package beans;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import static org.bytedeco.javacpp.opencv_core.CV_8UC1;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.THRESH_OTSU;
import static org.bytedeco.javacpp.opencv_imgproc.medianBlur;
import static org.bytedeco.javacpp.opencv_imgproc.threshold;
import utility.OutputUtility;

/**
 *
 * @author fabrizioborgia
 */
public class Text {

    final static Logger logger = Logger.getLogger(Text.class);

    // --------------------------------------------------------------------- | 
    //  CONSTANTS
    // --------------------------------------------------------------------- |
    /** Possible values for the visual organization of the Text. Organization is unidentified. */
    public static final short UNIDENTIFIED = -1;
    /** Possible values for the visual organization of the Text. Organization is horizontal. */
    public static final short HORIZONTAL = 1;
    /** Possible values for the visual organization of the Text. Organization is vertical. */
    public static final short VERTICAL = 2;

    /** Minimum required distance between two Slices in the same Text. */
    static final int SLICE_DISTANCE = 20;

    /** Standard head size. */
    static final double STD_HEAD_SIZE = 35.0;
    /** Standard hand size. */
    static final double STD_HAND_SIZE = 15.0;

    // --------------------------------------------------------------------- | 
    //  FIELDS
    // --------------------------------------------------------------------- |
    /** The visual organization of the {@code text}. */
    private short organization;

    /** Image of the {@code text}. */
    private Mat image;

    /** The set of {@code slices} identified in the {@code text}. */
    private List<Slice> slices;

    /** Scale factor of the {@code text}. */
    private double scaleFactor;

    /** Output image of the {@code text}. */
    private Mat outputImage;

    // --------------------------------------------------------------------- | 
    //  CONSTRUCTORS
    // --------------------------------------------------------------------- | 
    public Text() {
        organization = Text.UNIDENTIFIED;
        slices = new ArrayList<>();
        scaleFactor = 0.0;
        outputImage = null;
    }

    public Text(String tgtImagePath, short tgtOrganization) {

        image = imread(tgtImagePath, CV_8UC1);
        slices = new ArrayList<>();
        organization = tgtOrganization;
        scaleFactor = 0.0;
        outputImage = null;

        preprocessText();
        thresholdText();
        postprocessText();
    }

    // --------------------------------------------------------------------- | 
    //  EXTENDED GETTERS AND SETTERS
    // --------------------------------------------------------------------- |
    public short getOrganization() {
        return organization;
    }

    public void setOrganization(short organization) {
        this.organization = organization;
    }

    public Mat getImage() {
        return image;
    }

    public void setImage(Mat image) {
        this.image = image;
    }

    public List<Slice> getSlices() {
        return slices;
    }

    public void addSlice(Slice tgtSlice) {
        slices.add(tgtSlice);
    }

    public void setSlices(List<Slice> slices) {
        this.slices = slices;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public Mat getOutputImage() {
        return outputImage;
    }

    public void setOutputImage(Mat outputImage) {
        this.outputImage = outputImage;
    }

    // --------------------------------------------------------------------- | 
    //  METHODS
    // --------------------------------------------------------------------- |  
    public void updateOutputImage() {

        logger.info("Starting.");

        logger.info("Expected width: " + image.cols() * getScaleFactor());
        logger.info("Expected height: " + image.rows() * getScaleFactor());

        outputImage = new Mat(image.rows() * getScaleFactor(), image.cols() * getScaleFactor(), CV_8UC1, 255);

        logger.info("Updating image.");

        for (Slice curSlice : slices) {

            //        curSlice.updateOutputImage(outputImage);
        }
    }

    /**
     *	Text preprocessing routine.
     *	Includes smoothing.
     */
    private void preprocessText() {

        //  double perf = (double) getTickCount();
        //  blur(image,image,Size(3,3),Point(-1,-1),BORDER_DEFAULT);
        medianBlur(image, image, 3);

        //  perf = ((double) getTickCount() - perf) / getTickFrequency();
        //  logger.info("Time passed for preprocessing: " + perf + " ms.");
    }

    /**
     *	Text thresholding routine.
     */
    private void thresholdText() {

        threshold(image, image, 0, 255, THRESH_OTSU);
    }

    /**
     *	Text postprocessing routine.
     *	Includes smoothing or opening/closure.
     */
    private void postprocessText() {

        //	blur(image,image,Size(3,3),Point(-1,-1),BORDER_DEFAULT);
        medianBlur(image, image, 3);

        //	erode(image, image, Mat(), Point(-1,-1),1, 1, 1);
        //	dilate(image, image, Mat(), Point(-1,-1),1, 1, 1);
    }

    /**
     *	Invokes the proper slice identification routine, according to the visual
     *	organization of the current text. If the organization is neither VERTICAL nor
     *	HORIZONTAL, the function does nothing.
     */
    public void identifySlices() {
        if (organization == VERTICAL) {
            identifyVerticalSlices();
        } else if (organization == HORIZONTAL) {
            identifyHorizontalSlices();
        }
    }

    /**
     *	Slice identification routine for the VERTICAL organisation.
     *	Splits the current Text in a set of vertical Slices.
     */
    private void identifyVerticalSlices() {

        logger.info("Starting.");

        Mat woIm = image.clone();

        // Counts the total number of Slices in the text.
        int sliceCount = 0;

        // Keeps track of the average size of the Slices in the text.
        int sliceWidthAverage = 0;

        // Stores all the Slices found in the text.
        List<Slice> allSlices = new ArrayList<>();

        // tarting point of the current gap, used in the Slice detection loop.
        int CurrentSliceEnd = -1;
        int CurrentSliceStart = -1;

        // Slice detection loop, iterating column by column.
        UByteBufferIndexer woImIdx = woIm.createIndexer();

        for (int x = 0; x < woIm.cols(); ++x) {
            int y;

            for (y = 0; y < woIm.rows(); ++y) {

                // If the current column contains a black point.
                if (woImIdx.get(y, x) == 0) {

                    // Beginning of a new Slice after a gap.
                    if (CurrentSliceStart == -1) {
                        CurrentSliceStart = x;
                    }

                    CurrentSliceEnd = x;
                    break;
                }
            }

            // If the current column DOES NOT contains a black point.
            if (y == woIm.rows()) {

                // The gap is bigger than the minimum required distance, finalizing slice.
                if (CurrentSliceEnd != -1 && x - CurrentSliceEnd >= SLICE_DISTANCE) {

                    int sliceWidth = CurrentSliceEnd - CurrentSliceStart;

                    Rect tempROI = new Rect(CurrentSliceStart, 0, sliceWidth, woIm.rows());

                    Slice newSlice = new Slice(this, new Mat(woIm, tempROI), CurrentSliceStart, 0);
                    allSlices.add(newSlice);

                    sliceCount++;
                    sliceWidthAverage += sliceWidth;
                    CurrentSliceStart = -1;
                    CurrentSliceEnd = -1;
                }
            }
        }

        /** Slice filtering.													*/
        sliceWidthAverage = sliceWidthAverage / sliceCount;

        for (Slice curSlice : allSlices) {

            if (curSlice.getImage().cols() >= sliceWidthAverage / 2) {
                addSlice(curSlice);
                OutputUtility.writeMat(curSlice.getImage());
            }
        }

        logger.info("All slices: " + sliceCount + " (" + sliceWidthAverage + " pt. average width).");
        logger.info("Chosen slices: " + slices.size() + ".");
    }

    /**
     *	Slice identification routine for the HORIZONTAL organization.
     *	Splits the current Text in a set of horizontal Slices.
     */
    private void identifyHorizontalSlices() {

        logger.error("TO BE DONE!");
    }

}