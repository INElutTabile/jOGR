package utility;

import java.util.List;
import model.GeometricCircle;
import org.apache.log4j.Logger;
import org.bytedeco.javacpp.indexer.FloatBufferIndexer;
import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import org.bytedeco.javacpp.opencv_core.Mat;
import static org.bytedeco.javacpp.opencv_highgui.CV_GUI_EXPANDED;
import static org.bytedeco.javacpp.opencv_highgui.CV_WINDOW_AUTOSIZE;
import static org.bytedeco.javacpp.opencv_highgui.CV_WINDOW_KEEPRATIO;
import static org.bytedeco.javacpp.opencv_highgui.imshow;
import static org.bytedeco.javacpp.opencv_highgui.namedWindow;
import static org.bytedeco.javacpp.opencv_highgui.waitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import org.bytedeco.javacpp.opencv_core.Point;
import static org.bytedeco.javacpp.opencv_imgproc.CV_GRAY2BGR;
import static org.bytedeco.javacpp.opencv_imgproc.circle;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import org.bytedeco.javacpp.opencv_core.Scalar;

/**
 *
 * @author fabrizioborgia
 */
public class OutputUtility {

    final static Logger logger = Logger.getLogger(OutputUtility.class);

    // --------------------------------------------------------------------- | 
    //  CONSTANTS
    // --------------------------------------------------------------------- | 
    
    private static final String MAIN_WINDOW_TITLE = "Main Display";
    private static final int MAIN_WINDOW_FLAGS = (CV_WINDOW_AUTOSIZE | CV_WINDOW_KEEPRATIO | CV_GUI_EXPANDED);

    private static final String outputPath = "/images/OUT/";

    private static int sectionCounter = 0;
    private static int imageCounter = 0;

    /**
     * Displays an image.
     *
     * @param	tgtMat	The image to be displayed.
     * @param	tgtWait	If set to true, the UI will wait for the user to strike a
     * key
     *
     */
    public static void displayMat(Mat tgtMat, boolean tgtWait) {

        namedWindow(MAIN_WINDOW_TITLE, MAIN_WINDOW_FLAGS);
        imshow(MAIN_WINDOW_TITLE, tgtMat);
        if (tgtWait == true) {
            waitKey(0);
        }
    }

    /**
     * Displays an image.
     *
     * @param	tgtMat	The image to be displayed.
     * @param	tgtMessage	The string to be used as window title.
     * @param	tgtWait	If set to true, the UI will wait for the user to strike a
     * key
     *
     */
    public static void displayMat(Mat tgtMat, String tgtMessage, boolean tgtWait) {

        namedWindow(tgtMessage, MAIN_WINDOW_FLAGS);
        imshow(tgtMessage, tgtMat);

        if (tgtWait == true) {
            waitKey(0);
        }
    }

    /**
     * Writes the content of an image on a file.
     *
     * @param	tgtMat	The image to be printed.
     * @param tgtNewSection
     *
     */
    public static void writeMat(Mat tgtMat, boolean tgtNewSection) {

        String finalPath = "images/OUT/IMG" + String.format("%03d", sectionCounter) + "-" + String.format("%03d", imageCounter++) + ".png";

        if (tgtNewSection) {
            sectionCounter++;
            imageCounter = 0;
        }
        imwrite(finalPath, tgtMat);
    }

    public static void writeMat(Mat tgtMat) {
        writeMat(tgtMat, false);
    }

    /*
     * 	Displays an image overlaid by a set of points.
     *
     * 	@param	tgtMat			The image to be displayed.
     * 	@param	tgtPoints		A set of points to be overlaid on the image.
     * 	@param	tgtMessage		The string to be used as window title.
     * 	@param	tgtWait			If set to true, the UI will wait for the user to strike a key
     *
     */
    public static void writeMatPoints(Mat tgtMat, List<Point> tgtPoints, boolean tgtNewSection) {

        Mat myImage = tgtMat.clone();
        cvtColor(myImage, myImage, CV_GRAY2BGR);

        for (Point curPoint : tgtPoints) {
            if (curPoint.x() >= 0 && curPoint.x() < myImage.cols() && curPoint.y() >= 0 && curPoint.y() < myImage.rows()) {
                circle(myImage, curPoint, 0, new Scalar(0, 0, 255, 1.0), 1, 8, 0);
            }
        }

        writeMat(myImage, tgtNewSection);
    }
    
    /*
     * 	Displays an image overlaid by circles.
     *
     *
     */
    public static void writeMatCircles(Mat tgtImage, List<GeometricCircle> tgtCircles, boolean tgtNewSection) {

        Mat myImage = new Mat();

        cvtColor(tgtImage, myImage, CV_GRAY2BGR);

        for (GeometricCircle curCircle : tgtCircles) {

            Point currentCenter = curCircle.getCenter();
            int currentRadius = curCircle.getRadius();

            // circle center
            circle(myImage, currentCenter, 1, new Scalar(0, 255, 0, 1), 2, 8, 0);
            // circle outline
            circle(myImage, currentCenter, currentRadius, new Scalar(0, 0, 255, 1), 2, 8, 0);
        }

        writeMat(myImage, tgtNewSection);
    }  

    // --------------------------------------------------------------------- | 
    // METHODS - CONSOLE PRINT
    // --------------------------------------------------------------------- |
    
    /**
     * Prints the content of an image (content: UByte) on the console.
     *
     * @param	tgtMat	The image to be printed.
     *
     */
    public static void printUByteMat(Mat tgtMat) {

        UByteBufferIndexer matIdx = tgtMat.createIndexer();

        for (int y = 0; y < tgtMat.rows(); y++) {
            String row = "|";
            for (int x = 0; x < tgtMat.cols(); x++) {
                row += matIdx.get(y, x);
            }
            row += "|";
            System.out.println(row);
        }
    }
    
    /**
     * Prints the content of an image (content: Float) on the console.
     *
     * @param	tgtMat	The image to be printed.
     *
     */
    public static void printFloatMat(Mat tgtMat) {

        FloatBufferIndexer matIdx = tgtMat.createIndexer();

        for (int y = 0; y < tgtMat.rows(); y++) {
            String row = "|";
            for (int x = 0; x < tgtMat.cols(); x++) {
                row += matIdx.get(y, x) + " ";
            }
            row += "|";
            System.out.println(row);
        }
    }    
    
    /**
     *	Prints the content of a {@code int[]} on the console.
     *
     * @param tgtList
     */
    public static void printIntArray(int[] tgtList) {

        String message = "|";

        for (int i = 0; i < tgtList.length; i++) {

            message += tgtList[i] + " ";
        }
        message += "|";
        System.out.println(message);
    }
    
    /**
     *	Prints the content of a {@code List<Point>} on the console.
     *
     * @param tgtList
     */
    public static void printPointList(List<Point> tgtList) {

        String message = "|";

        for (Point curPoint : tgtList) {

            message += "(" + curPoint.x() + ", " + curPoint.y() + ") ";
        }
        message += "|";
        System.out.println(message);
    }    

}
