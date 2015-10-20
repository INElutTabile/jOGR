package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.bytedeco.javacpp.indexer.IntBufferIndexer;
import static org.bytedeco.javacpp.opencv_core.CV_32S;
import org.bytedeco.javacpp.opencv_core.Mat;
import static org.bytedeco.javacpp.opencv_imgproc.THRESH_BINARY;
import static org.bytedeco.javacpp.opencv_imgproc.threshold;
import org.bytedeco.javacpp.opencv_core.Point;
import utility.ImgUtil;
import utility.OutputUtility;
import org.bytedeco.javacpp.opencv_core.Scalar;

/**
 *
 * @author fabrizioborgia
 */
public class Slice {

    final static Logger logger = Logger.getLogger(Slice.class);

    // --------------------------------------------------------------------- | 
    //  FIELDS
    // --------------------------------------------------------------------- |
    
    /** Reference to the parent text. */
    private Text text;

    /** The bounds of the slice in the text. */
    private int minX = -1;
    private int maxX = -1;
    private int minY = -1;
    private int maxY = -1;

    /** Image of the slice. */
    private Mat image;

    /** A Mat<int> matching each non-white point (in the slice) with the ID of its frag. */
    private Mat image_ext;

    /** Contains each frag related to the slice, the IDs of the frag are the keys of the map. */
    HashMap<String, Frag> frag_map = new HashMap<>();

    /** Keep track of any frag in which a geometric shape was detected. */
    List<Frag> chosen_frags = new ArrayList<>();

    /** Counter for the glyphs in the Slice. */
    int glyph_counter = 0;

    /** Contains each glyph related to the slice, the IDs of the glyphs are the keys of the Map. */
    HashMap<String, Glyph> glyph_map;

    // --------------------------------------------------------------------- | 
    //  CONSTRUCTORS
    // --------------------------------------------------------------------- |
    
    public Slice() {

        text = null;
    }

    public Slice(Text tgtText, Mat tgtImage) {

        text = tgtText;
        image = tgtImage;
    }

    public Slice(Text tgtText, Mat tgtImage, int tgtMinX, int tgtMinY) {

        text = tgtText;
        minX = tgtMinX;
        minY = tgtMinY;
        image = tgtImage;
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

    public HashMap<String, Frag> getFrag_map() {
        return frag_map;
    }

    public void setFrag_map(HashMap<String, Frag> frag_map) {
        this.frag_map = frag_map;
    }

    public List<Frag> getChosen_frags() {
        return chosen_frags;
    }

    public void setChosen_frags(List<Frag> chosen_frags) {
        this.chosen_frags = chosen_frags;
    }

    public int getGlyph_counter() {
        return glyph_counter;
    }

    public void setGlyph_counter(int glyph_counter) {
        this.glyph_counter = glyph_counter;
    }

    public HashMap<String, Glyph> getGlyph_map() {
        return glyph_map;
    }

    public void setGlyph_map(HashMap<String, Glyph> glyph_map) {
        this.glyph_map = glyph_map;
    }

    // --------------------------------------------------------------------- | 
    //  METHODS
    // --------------------------------------------------------------------- |
    
    private void thresholdSlice() {

        threshold(image, image, 100, 255, THRESH_BINARY);
    }

    /**
     * Scan the image of the current Slice to identify its frag. The function
     * does not return anything since the output is stored in the following
     * class fields.<br>
     *
     * 1) {@code image_ext}: stores, for each point P in the slice image, the id of the
     * frag to which P belongs.<br>
     * 2) {@code frag_map}: stores, for each frag id, the
     * corresponding frag object.<br>
     *
     */
    public void identifyFrags() {

        logger.info("Starting, image: " + image.cols() + "x" + image.rows() + ".");

        Mat woIm = image.clone();

        /* La Mat<int> "image_ext" immagazzinerà, per ogni pixel dell' immagine
         * della Slice, l'id del Frag al quale esso appartiene. Ogni cella della
         * matrice viene inizializzata a -1: id che identifica pixel non interessanti.
         */
        image_ext = new Mat(image.rows(), image.cols(), CV_32S, new Scalar(0));
        IntBufferIndexer image_extIdx = image_ext.createIndexer();

        List< List<Point>> fragPoints = ImgUtil.findBlobs(woIm);

        for (int i = 0; i < fragPoints.size(); i++) {

            // Frag id starts from 5, in order to avoid frag id 0.
            int fragId = 100 + i;

            Frag newFrag = new Frag(this, Integer.toString(fragId));
            frag_map.put(Integer.toString(fragId), newFrag);

            for (Point curPoint : fragPoints.get(i)) {
                image_extIdx.put(curPoint.y(), curPoint.x(), fragId);
                newFrag.addPoint(curPoint);
            }

        }

        logger.info("Frags identified: " + frag_map.size());
    }

    public void invokeFirstScan() {

        logger.info("Starting.");

        if (frag_map.isEmpty()) {
            return;
        }

        Iterator it = frag_map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            boolean keepFrag = ((Frag) pair.getValue()).updateImage();
            if (keepFrag) {
                OutputUtility.writeMat(((Frag) pair.getValue()).getImage(), false);
                ((Frag) pair.getValue()).invokeFirstScan();
            } else {
                it.remove();
            }
        }

        logger.info("Frags identified: " + chosen_frags.size());
    }

}
