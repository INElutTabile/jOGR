package utility;

import org.bytedeco.javacpp.opencv_core.Mat;
import static org.bytedeco.javacpp.opencv_highgui.CV_GUI_EXPANDED;
import static org.bytedeco.javacpp.opencv_highgui.CV_WINDOW_AUTOSIZE;
import static org.bytedeco.javacpp.opencv_highgui.CV_WINDOW_KEEPRATIO;
import static org.bytedeco.javacpp.opencv_highgui.CV_WINDOW_NORMAL;
import static org.bytedeco.javacpp.opencv_highgui.imshow;
import static org.bytedeco.javacpp.opencv_highgui.namedWindow;
import static org.bytedeco.javacpp.opencv_highgui.waitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;

/**
 *
 * @author fabrizioborgia
 */
public class OutputUtility {

    // --------------------------------------------------------------------- | 
    //  CONSTANTS
    // --------------------------------------------------------------------- |   
    private static final String MAIN_WINDOW_TITLE = "Main Display";
    private static final int MAIN_WINDOW_FLAGS = (CV_WINDOW_AUTOSIZE | CV_WINDOW_KEEPRATIO | CV_GUI_EXPANDED);

    private static final String outputPath = "/images/OUT/";

    private static int sectionCounter = 0;
    private static int imageCounter = 0;

    /**
     * 	Displays an image.
     *
     * 	@param	tgtMat			The image to be displayed.
     * 	@param	tgtWait			If set to true, the UI will wait for the user to strike a key
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
     * 	Displays an image.
     *
     * 	@param	tgtMat			The image to be displayed.
     * 	@param	tgtMessage		The string to be used as window title.
     * 	@param	tgtWait			If set to true, the UI will wait for the user to strike a key
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
     *	Writes the content of an image on a file.
     *
     * 	@param	tgtMat		The image to be printed.
     *  @param tgtNewSection
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

}
