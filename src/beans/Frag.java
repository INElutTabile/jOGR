/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import model.GeometricShape;
import org.apache.log4j.Logger;
import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import static org.bytedeco.javacpp.opencv_core.CV_8U;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Scalar;
import utility.GeomUtil;

/**
 *
 * @author fborgia
 */
public class Frag {

    final static Logger logger = Logger.getLogger(Frag.class);

    // --------------------------------------------------------------------- | 
    //  CONSTANTS
    // --------------------------------------------------------------------- |    
    /**
     * Minimum number of points required for a Frag to be considered a proper
     * Frag (under this value, a Frag is considered as noise).
     */
    static final int MIN_FRAGPOINTS = 10;

    // --------------------------------------------------------------------- | 
    //  FIELDS
    // --------------------------------------------------------------------- | 
    /**
     * Reference to the parent Slice.
     */
    Slice slice;

    /**
     * Reference to the associated Glyph.
     */
    Glyph glyph;

    /**
     * Id of the Frag.
     */
    String id;

    /**
     * The bounds (in the Slice image) of the MBR containing the Frag.
     */
    int minX = -1;
    int maxX = -1;
    int minY = -1;
    int maxY = -1;

    /**
     * Image of the Frag.
     */
    Mat image;

    /**
     * A vector which contains the coordinates of all the points in the Frag.
     */
    List<Point> points;

    /**
     * A vector which contains the coordinates of all the holes in the Frag.
     */
    List< List<Point>> holes;

    /**
     * A vector which contains the GeometricShape(s) identified in the Frag.
     */
    List<GeometricShape> shapes;

    // --------------------------------------------------------------------- | 
    //  CONSTRUCTORS
    // --------------------------------------------------------------------- | 
    public Frag(Slice tgtSlice, String tgtId) {
        this.slice = tgtSlice;
        this.id = tgtId;
        points = new ArrayList<>();
    }

    // --------------------------------------------------------------------- | 
    //  EXTENDED GETTERS AND SETTERS
    // --------------------------------------------------------------------- |
    public Slice getSlice() {
        return slice;
    }

    public void setSlice(Slice slice) {
        this.slice = slice;
    }

    public Glyph getGlyph() {
        return glyph;
    }

    public void setGlyph(Glyph glyph) {
        this.glyph = glyph;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public Mat getImage() {
        return image;
    }

    public void setImage(Mat image) {
        this.image = image;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(Point tgtPoint) {
        points.add(tgtPoint);
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public List<List<Point>> getHoles() {
        return holes;
    }

    public void setHoles(List<List<Point>> holes) {
        this.holes = holes;
    }

    public List<GeometricShape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<GeometricShape> shapes) {
        this.shapes = shapes;
    }

    // --------------------------------------------------------------------- | 
    //  METHODS
    // --------------------------------------------------------------------- |
    public boolean updateImage() {

        logger.info("Starting image update for frag: " + id + " (" + points.size() + ").");

        if (getPoints().size() < MIN_FRAGPOINTS) {
            return false;
        }

        Mat sliceImage = slice.getImage();
        UByteBufferIndexer sliceImageIdx = sliceImage.createIndexer();

        minX = GeomUtil.findMinX(points);
        maxX = GeomUtil.findMaxX(points);
        minY = GeomUtil.findMinY(points);
        maxY = GeomUtil.findMaxY(points);

        int newWidth = maxX - minX;
        int newHeight = maxY - minY;

        logger.info(newWidth);
        logger.info(newHeight);

        if (newHeight > 0 || newWidth > 0) {

            Mat newImage = new Mat(newHeight + 1, newWidth + 1, CV_8U, new Scalar(255, 255, 255, 1.0));
            UByteBufferIndexer newImageIdx = newImage.createIndexer();

            for (Point curPoint : points) {
                newImageIdx.put(curPoint.y() - minY, curPoint.x() - minX, sliceImageIdx.get(curPoint.y(), curPoint.x()));
            }
            image = newImage;
        } else {
            return false;
        }

        //     holes = GeomUtil::shiftPoints(ImgUtil::findHoles(image), this->getTL());
        logger.info("Image update successful.");
        return true;
    }

    public void invokeFirstScan() {

        logger.info("Starting: Frag id: " + id);

//	GeometricCircle::detect(*this);
//	GeometricRectangle::detect(*this);
//	GeometricTriangle::detect(*this);
        if (shapes.size() > 0) {
            slice.getChosen_frags().add(this);
        }

        logger.info("Returning.");
    }

}
