package beans;

import org.bytedeco.javacpp.opencv_core.Mat;

/**
 *
 * @author fabrizioborgia
 */
public class Slice {

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
//    HashMap <int, Frag> frag_map;
    /**	Keep track of any Frag in which a geometric shape was detected. */
//    ArrayList<Frag> chosen_frags ;
    /**	Counter for the Glyphs in the Slice. */
    int glyph_counter;

    /**	Contains each Glyph related to the Slice, the IDs of the Glyphs are the keys of the Map. */
//    HashMap<int, Glyph> glyph_map ;
    
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
    
    

}
