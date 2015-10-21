/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import model.GeometricCircle;
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
     * Minimum number of points required for a frag to be considered a proper
     * frag (under this value, a Frag is considered as noise).
     */
    static final int MIN_FRAGPOINTS = 10;

    // --------------------------------------------------------------------- | 
    //  FIELDS
    // --------------------------------------------------------------------- | 
    
    /** Reference to the parent slice. */
    Slice slice;

    /** Reference to the associated glyph. */
    Glyph glyph;

    /** Id of the frag. */
    String id;

    /** The bounds (in the slice image) of the MBR containing the Frag. */
    int minX = -1;
    int maxX = -1;
    int minY = -1;
    int maxY = -1;

    /** Image of the frag. */
    Mat image;

    /** A list containing the coordinates of all the points in the frag. */
    List<Point> points = new ArrayList<>();

    /** A list containing the coordinates of all the holes in the frag. */
    List< List<Point>> holes = new ArrayList<>();

    /** A list containing the geometricshapes identified in the frag. */
    List<GeometricShape> shapes = new ArrayList<>();

    // --------------------------------------------------------------------- | 
    //  CONSTRUCTORS
    // --------------------------------------------------------------------- | 
    
    public Frag(Slice tgtSlice, String tgtId) {
        this.slice = tgtSlice;
        this.id = tgtId;
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
    
    public Point getTL(){
        return new Point(minX, minY);
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
    
    public void addCircle(GeometricCircle tgtCircle) {

        shapes.add(new GeometricCircle(tgtCircle));
    }  

    public void setShapes(ArrayList<GeometricShape> shapes) {
        this.shapes = shapes;
    }

    // --------------------------------------------------------------------- | 
    //  METHODS
    // --------------------------------------------------------------------- |
    
    public boolean updateImage() {

//        logger.info("Starting image update for frag: " + id + " (" + points.size() + ").");

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
//        logger.info("Image update successful.");
        return true;
    }

    public void invokeFirstScan() {

//        logger.info("Starting: Frag: " + this.toString());

	new GeometricCircle().detect(this);
//	GeometricRectangle::detect(*this);
//	GeometricTriangle::detect(*this);
        if (shapes.size() > 0) {
            slice.getChosen_frags().add(this);
        }

//        logger.info("Returning.");
    }

    @Override
    public String toString() {
        return "Frag{" + "id=" + id + ", minX=" + minX + ", minY=" + minY + ", maxX=" + maxX + ", maxY=" + maxY + '}';
    }

}
