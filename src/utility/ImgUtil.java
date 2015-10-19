/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_imgproc;
import static org.bytedeco.javacpp.opencv_imgproc.CV_GRAY2BGR;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Scalar;

/**
 *
 * @author fborgia
 */
public class ImgUtil {

    /**
     * Find the connected components within the image.
     * @param tgtImage
     * @return 
     */
    public static ArrayList< ArrayList<Point>> findBlobs(Mat tgtImage) {

        ArrayList< ArrayList<Point>> blobs = new ArrayList();
        Mat label_image = new Mat();
        Mat woIm = tgtImage.clone();

        cvtColor(woIm, label_image, CV_GRAY2BGR);

        // Starts at 2 because 0,1 are used already.
        int label_count = 2;

        UByteBufferIndexer woImIdx = woIm.createIndexer();

        for (int y = 0; y < woIm.rows(); y++) {

            for (int x = 0; x < woIm.cols(); x++) {

                if (woImIdx.get(y, x) == 0) {

                    Rect rect = new Rect();
                    opencv_imgproc.floodFill(label_image, new Point(x, y), new Scalar(label_count), rect, new Scalar(0), new Scalar(0), 8);
                    opencv_imgproc.floodFill(woIm, new Point(x, y), new Scalar(label_count), rect, new Scalar(0), new Scalar(0), 8);

                    ArrayList<Point> blob = new ArrayList();

                    for (int i = rect.y(); i < (rect.y() + rect.height()); i++) {

                        for (int j = rect.x(); j < (rect.x() + rect.width()); j++) {

                            if (woImIdx.get(y, x) == label_count) {
                                blob.add(new Point(j, i));
                            }

                        }
                    }

                    blobs.add(blob);
                    label_count++;
                }
            }
        }

        return blobs;
    }

}
