/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.bytedeco.javacpp.opencv_core.Rect;

import beans.Frag;
import beans.Text;
import com.sun.javafx.geom.Vec3f;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.bytedeco.javacpp.helper.opencv_core.CvArr;
import org.bytedeco.javacpp.indexer.FloatBufferIndexer;
import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvPoint;
import org.bytedeco.javacpp.opencv_core.CvPoint2D32f;
import org.bytedeco.javacpp.opencv_core.CvPoint3D32f;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Size;
import static org.bytedeco.javacpp.opencv_core.cvGetSeqElem;
import static org.bytedeco.javacpp.opencv_core.cvPointFrom32f;
import org.bytedeco.javacpp.opencv_imgproc;
import static org.bytedeco.javacpp.opencv_imgproc.CV_AA;
import static org.bytedeco.javacpp.opencv_imgproc.CV_HOUGH_GRADIENT;
import static org.bytedeco.javacpp.opencv_imgproc.HoughCircles;
import static org.bytedeco.javacpp.opencv_imgproc.cvHoughCircles;
import utility.GeomUtil;
import utility.ImgUtil;
import utility.OutputUtility;

/**
 *
 * @author fborgia
 */
public class GeometricCircle extends GeometricShape {

    final static Logger logger = Logger.getLogger(GeometricCircle.class);

    // --------------------------------------------------------------------- | 
    //  CONSTANTS
    // --------------------------------------------------------------------- |
    static final int MAX_DEGREES = 360;

    static final double RELIABILITY_RADIUSEXP = 1.25;

    static final int RELIABILITY_SECTOR_DEGREES = 5;

    static final int RELIABILITY_TRESHOLD = 75;

    // --------------------------------------------------------------------- | 
    //  FIELDS
    // --------------------------------------------------------------------- |
    /** The centre of the circle. */
    private Point center;

    /** The radius of the circle. */
    int radius;

    // --------------------------------------------------------------------- | 
    //  CONSTRUCTORS
    // --------------------------------------------------------------------- |
    public GeometricCircle() {
        super(GeometricShape.CIRCLE);
    }

    public GeometricCircle(GeometricCircle tgtGeometricCircle) {
        super(GeometricShape.CIRCLE);
        this.center = tgtGeometricCircle.getCenter();
        this.radius = tgtGeometricCircle.getRadius();
    }

    public GeometricCircle(Point tgtCenter, int tgtRadius) {
        super(GeometricShape.CIRCLE);
        this.center = tgtCenter;
        this.radius = tgtRadius;
    }

    // --------------------------------------------------------------------- | 
    //  EXTENDED GETTERS AND SETTERS
    // --------------------------------------------------------------------- |
    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Finds the Minimum Bounding Rectangle (MBR).
     * @return 
     */
    public Rect getMBR() {

        return new Rect(center.x() - radius, center.y() - radius, radius * 2, radius * 2);
    }

    // --------------------------------------------------------------------- | 
    //  METHODS
    // --------------------------------------------------------------------- | 
    @Override
    public void detect(Frag tgtFrag) {

        IplImage currentImageGray = new IplImage(tgtFrag.getImage().clone());
        CvMemStorage mStorage = CvMemStorage.create();

        List<GeometricCircle> outputCircles = new ArrayList<>();

        CvSeq detectedCircles = cvHoughCircles(
                currentImageGray, // The 8-bit, single-channel, grayscale input image.
                mStorage, // The output vector of found circles. Each vector is encoded as 3-element floating-point vector.
                CV_HOUGH_GRADIENT, // Currently, the only implemented method is CV_HOUGH_GRADIENT.
                1, // The inverse ratio of the accumulator resolution to the image resolution.
                2, // Minimum distance between the centers of the detected circles. If the parameter
                254, // CV_HOUGH_GRADIENT it is the higher threshold of the two passed to Canny() edge
                25, // CV_HOUGH_GRADIENT is the accumulator threshold at the center detection stage.
                tgtFrag.getImage().rows() / 4, // Minimum circle radius.
                0 // Maximum circle radius.
        );

        Point avgCenter = new Point(0, 0);
        int avgRadius = 0;

        if (detectedCircles != null && detectedCircles.total() > 0) {

            for (int i = 0; i < detectedCircles.total(); i++) {
                CvPoint3D32f curCircle = new CvPoint3D32f(cvGetSeqElem(detectedCircles, i));

                avgRadius += Math.round(curCircle.z());
                avgCenter.x(avgCenter.x() + Math.round(curCircle.x()));
                avgCenter.y(avgCenter.y() + Math.round(curCircle.y()));
            }

            avgCenter = new Point(avgCenter.x() / detectedCircles.total(), avgCenter.y() / detectedCircles.total());
            avgRadius /= detectedCircles.total();

            outputCircles.add(new GeometricCircle(avgCenter, avgRadius));
            verify(tgtFrag, outputCircles);
        }

    }

    @Override
    public void verify(Frag tgtFrag, List tgtShapes) {

        Point fTL = tgtFrag.getTL();

        List<Point> tgtPoints = GeomUtil.shiftPoints(tgtFrag.getPoints(), new Point(-fTL.x(), -fTL.y()));

        for (Object curItem : tgtShapes) {

            GeometricCircle curCircle = (GeometricCircle) curItem;
            
            float detectionReliability = (float) 0.0;

            int[] circleData = ImgUtil.checkCircleSectorsCoverage(tgtPoints, curCircle, RELIABILITY_RADIUSEXP, RELIABILITY_SECTOR_DEGREES);
            
            for (int i = 0; i < circleData.length; i++) {
                if (circleData[i] > 0) {
                    detectionReliability += 100.0 / (MAX_DEGREES / RELIABILITY_SECTOR_DEGREES);
                }
            }

            logger.info("Circle reliability: " + detectionReliability + " (" + RELIABILITY_TRESHOLD + ")");
            
            if (detectionReliability >= RELIABILITY_TRESHOLD) {
                tgtFrag.addCircle(curCircle);
                OutputUtility.writeMatCircles(tgtFrag.getImage(), tgtShapes, true);
            }
        }
    }

    @Override
    public String toString() {
        return "GeometricCircle{" + "center=(" + center.x() + ", " + center.y() + ")" + ", radius=" + radius + '}';
    }

}
