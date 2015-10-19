package beans;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.bytedeco.javacpp.opencv_core;
import static org.bytedeco.javacpp.opencv_core.CV_32S;
import org.bytedeco.javacpp.opencv_core.Mat;
import static org.bytedeco.javacpp.opencv_imgproc.THRESH_BINARY;
import static org.bytedeco.javacpp.opencv_imgproc.threshold;
import org.bytedeco.javacpp.opencv_core.Point;
import utility.ImgUtil;

/**
 *
 * @author fabrizioborgia
 */
public class Slice {

    final static Logger logger = Logger.getLogger(Slice.class);    
    
    // --------------------------------------------------------------------- | 
    //  FIELDS
    // --------------------------------------------------------------------- | 
    
    /**	Reference to the parent Text. */
    private Text text;

    /** The bounds of the slice in the text. */
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    /**	Image of the Slice. */
    private Mat image;

    /**	A Mat<int> matching each non-white point (in the Slice) with the ID of its Frag. */
    private Mat image_ext;

    /**	Contains each Frag related to the Slice, the IDs of the Frags are the keys of the Map. */
    HashMap<String, Frag> frag_map;
    /**	Keep track of any Frag in which a geometric shape was detected. */
    ArrayList<Frag> chosen_frags;
    /**	Counter for the Glyphs in the Slice. */
    int glyph_counter;

    /**	Contains each Glyph related to the Slice, the IDs of the Glyphs are the keys of the Map. */
    HashMap<String, Glyph> glyph_map;

    // --------------------------------------------------------------------- | 
    //  CONSTRUCTORS
    // --------------------------------------------------------------------- |   
    public Slice() {

        text = null;
        minX = -1;
        maxX = -1;
        minY = -1;
        maxY = -1;
        glyph_counter = 0;
    }

    public Slice(Text tgtText, Mat tgtImage) {

        text = tgtText;
        minX = -1;
        maxX = -1;
        minY = -1;
        maxY = -1;
        image = tgtImage;
        glyph_counter = 0;
//        thresholdSlice();
    }

    public Slice(Text tgtText, Mat tgtImage, int tgtMinX, int tgtMinY) {

        text = tgtText;
        minX = tgtMinX;
        maxX = -1;
        minY = tgtMinY;
        maxY = -1;
        image = tgtImage;
        glyph_counter = 0;
//	thresholdSlice();
    }

    // --------------------------------------------------------------------- | 
    //  EXTENDED GETTERS AND SETTERS
    // --------------------------------------------------------------------- |
    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
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

    public Mat getImage_ext() {
        return image_ext;
    }

    public void setImage_ext(Mat image_ext) {
        this.image_ext = image_ext;
    }

    public int getGlyph_counter() {
        return glyph_counter;
    }

    public void setGlyph_counter(int glyph_counter) {
        this.glyph_counter = glyph_counter;
    }

    // --------------------------------------------------------------------- | 
    //  METHODS
    // --------------------------------------------------------------------- | 
    
    private void thresholdSlice() {

        threshold(image, image, 100, 255, THRESH_BINARY);
    }

    private void identifyFrags() {

        logger.info("Starting, image: " + image.cols() + "x" + image.rows() + ".");

        Mat woIm = image.clone();

        /* La Mat<int> "image_ext" immagazzinerà, per ogni pixel dell' immagine
         * della Slice, l'id del Frag al quale esso appartiene. Ogni cella della
         * matrice viene inizializzata a -1: id che identifica pixel non interessanti.
         */
        image_ext = new Mat(image.rows(), image.cols(), CV_32S, -1);

        ArrayList< ArrayList<Point>> fragPoints = ImgUtil.findBlobs(woIm);

	for (int i = 0; i < fragPoints.size(); i++) {

            Frag newFrag = new Frag(this, Integer.toString(i));
            frag_map.put(Integer.toString(i), newFrag);

            for (Point curPoint : fragPoints.get(i)) {
//                image_ext.at<int>(curPoint) = i;
//                newFrag -> addPoint(curPoint);
            }

        }

        logger.info("Frags identified: " + frag_map.size());
    }

}
