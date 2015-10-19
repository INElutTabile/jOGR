/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Scalar;

/**
 *
 * @author fborgia
 */
public class ImgUtil {

    final static Logger logger = Logger.getLogger(ImgUtil.class);

    /**
     * Find the connected components within the image.
     *
     * @param tgtImage
     * @return
     */
    public static List< List<Point>> findBlobs(Mat tgtImage) {

        List< List<Point>> blobs = new ArrayList();

        Mat woIm = tgtImage.clone();
        UByteBufferIndexer woImIdx = woIm.createIndexer();

        // Start labelling the image starting from label 2, since 0 and 1are already in use.
        int label_count = 2;

        for (int y = 0; y < woIm.rows(); y++) {

            for (int x = 0; x < woIm.cols(); x++) {

                if (woImIdx.get(y, x) == 0) {

                    Scalar fillColor = new Scalar(label_count, label_count, label_count, 1.0);

                    Rect fragBounds = new Rect();
                    opencv_imgproc.floodFill(woIm, new Point(x, y), fillColor, fragBounds, new Scalar(), new Scalar(), 8);

                    List<Point> blob = new ArrayList();

                    for (int i = fragBounds.y(); i < (fragBounds.y() + fragBounds.height()); i++) {

                        for (int j = fragBounds.x(); j < (fragBounds.x() + fragBounds.width()); j++) {

                            if (woImIdx.get(i, j) == label_count) {
                                blob.add(new Point(j, i));
                            }
                        }
                    }

                    blobs.add(blob);
//                    logger.info("Blob detected (" + blob.size() + " points). Current count: " + label_count);
                    label_count++;
                }

            }
        }

        return blobs;
    }

}
