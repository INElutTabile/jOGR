/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import model.GeometricShape;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;

/**
 *
 * @author fborgia
 */
public class Frag {

    /**
     * 	Minimum number of points required for a Frag to be considered a proper
     * 	Frag (under this value, a Frag is considered as noise).
     */
    static final int MIN_FRAGPOINTS = 10;  
    
    // --------------------------------------------------------------------- | 
    //  FIELDS
    // --------------------------------------------------------------------- |  
    
    /** Reference to the parent Slice. */
    Slice slice;

    /** Reference to the associated Glyph. */
    Glyph glyph;

    /** Id of the Frag. */
    String id;

    /**	The bounds (in the Slice image) of the MBR containing the Frag.	*/
    int minX = -1;
    int maxX = -1;
    int minY = -1;
    int maxY = -1;

    /**	Image of the Frag. */
    Mat image;

    /** A vector which contains the coordinates of all the points in the Frag. */
    ArrayList<Point> points;

    /** A vector which contains the coordinates of all the holes in the Frag. */
    ArrayList< ArrayList<Point>> holes;

    /**	A vector which contains the GeometricShape(s) identified in the Frag. */
    ArrayList<GeometricShape> shapes ;
    
    // --------------------------------------------------------------------- | 
    //  CONSTRUCTORS
    // --------------------------------------------------------------------- | 

    public Frag(Slice tgtSlice, String tgtId) {
        this.slice = tgtSlice;
        this.id = tgtId;
    }

    
}
