/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Frag;
import java.util.List;

/**
 *
 * @author fborgia
 */
public abstract class GeometricShape {

    // --------------------------------------------------------------------- | 
    //  CONSTANTS
    // --------------------------------------------------------------------- |     
    
    /** Constant used to identify the type of the shape. The shape is not identified. */
    static final int UNIDENTIFIED = -1;

    /** Constant used to identify the type of the shape. The shape is a circle. */
    static final int CIRCLE = 1;

    /** Constant used to identify the type of the shape. The shape is a rectangle. */
    static final int RECTANGLE = 2;

    /** Constant used to identify the type of the shape. The shape is a triangle. */
    static final int TRIANGLE = 3;
    
    // --------------------------------------------------------------------- | 
    //  FIELDS
    // --------------------------------------------------------------------- |
    
    /** The type of the shape */
    private int type = -1;
    
    // --------------------------------------------------------------------- | 
    //  CONSTRUCTORS
    // --------------------------------------------------------------------- | 

    public GeometricShape(int type) {
        this.type = type;
    }
    
    // --------------------------------------------------------------------- | 
    //  EXTENDED GETTERS AND SETTERS
    // --------------------------------------------------------------------- |

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
     
    // --------------------------------------------------------------------- | 
    //  METHODS
    // --------------------------------------------------------------------- |     

    public abstract void detect(Frag tgtFrag);    
    
    public abstract void verify(Frag tgtFrag, List tgtShapes);     

}
