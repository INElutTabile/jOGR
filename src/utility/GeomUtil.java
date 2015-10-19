/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import org.bytedeco.javacpp.opencv_core.Point;

import java.util.List;

/**
 *
 * @author fborgia
 */
public class GeomUtil {

    private static final int MAX_BOUND = 48151623;
    private static final int MIN_BOUND = -48151623;

    // --------------------------------------------------------------------- | 
    //  METHODS - Geometric: low-level operations on coordinates
    // --------------------------------------------------------------------- | 
    /**
     * Finds the minimum X-coordinate (i.e. north) in the given set of points.
     *
     * @param tgtPoints
     * @return
     */
    public static int findMinX(List<Point> tgtPoints) {

        int minX = MAX_BOUND;

        for (Point curPoint : tgtPoints) {

            if (curPoint.x() < minX) {
                minX = curPoint.x();
            }
        }
        return minX;
    }

    /**
     * Finds the maximum X-coordinate (i.e. south) in the given set of points.
     *
     * @param tgtPoints
     * @return
     */
    public static int findMaxX(List<Point> tgtPoints) {

        int maxX = MIN_BOUND;

        for (Point curPoint : tgtPoints) {

            if (curPoint.x() > maxX) {
                maxX = curPoint.x();
            }
        }
        return maxX;
    }

    /**
     * Finds the minimum Y-coordinate (i.e. west) in the given set of points.
     * 
     * @param tgtPoints
     * @return 
     */
    public static int findMinY(List<Point> tgtPoints) {

        int minY = MAX_BOUND;

        for (Point curPoint : tgtPoints) {

            if (curPoint.y() < minY) {
                minY = curPoint.y();
            }
        }
        return minY;
    }

    /**
     * Finds the maximum Y-coordinate (i.e. east) in the given set of points.
     * 
     * @param tgtPoints
     * @return 
     */
    public static int findMaxY(List<Point> tgtPoints) {

        int maxY = MIN_BOUND;
        
        for (Point curPoint : tgtPoints) {

            if (curPoint.y() > maxY) {
                maxY = curPoint.y();
            }
        }
        return maxY;
    }
//
//    /**
//     * This method returns the point with the smaller y-axis coordinate within
//     * the given
//     * set of points.
//     *
//     * @param tgtPoints	A vector of points.
//     *
//     * @return Point The point with the smaller y-axis coordinate.
//     */
//    public static Point findTopPoint(List<Point> tgtPoints) {
//
//        if (tgtPoints.isEmpty()) {
//            return new Point(MIN_BOUND, MIN_BOUND);
//        }
//
//        sort(tgtPoints.begin(), tgtPoints.end(), CmpUtil::comparePointY_A);
//        return tgtPoints.get(0);
//    }
//
//    /**
//     * This method returns the point(s) with the smaller y-axis coordinate
//     * within the given
//     * set of points.
//     *
//     * @param tgtPoints	A vector of points.
//     *
//     * @return List<Point> The point(s) with the smaller y-axis coordinate.
//     */
//    public static List<Point> GeomUtil
//
//    ::findTopPoints(List<Point> tgtPoints) {
//
//        if (tgtPoints.empty()) {
//            return tgtPoints;
//        }
//
//        List<Point> chosenPoints;
//        sort(tgtPoints.begin(), tgtPoints.end(), CmpUtil::comparePointY_A);
//
//        int baseCoord = tgtPoints[0].y;
//        for (unsigned  int i = 0;i< tgtPoints.size
//        (); i++
//        
//            ) {
//
//		if (tgtPoints[i].y == baseCoord) {
//                chosenPoints.push_back(tgtPoints[i]);
//            } else {
//                break;
//            }
//        }
//
//        return chosenPoints;
//    }
//
//    /**
//     * This method returns the point with the greater y-axis coordinate within
//     * the given
//     * set of points.
//     *
//     * @param tgtPoints	A vector of points.
//     *
//     * @return Point The point with the greater y-axis coordinate.
//     */
//    public static Point findBottomPoint(List<Point> tgtPoints) {
//
//        if (tgtPoints.empty()) {
//            return Point(-1, -1);
//        }
//
//        sort(tgtPoints.begin(), tgtPoints.end(), CmpUtil::comparePointY_D);
//        return tgtPoints[0];
//    }
//
//    /**
//     * This method returns the point(s) with the greater y-axis coordinate
//     * within the given
//     * set of points.
//     *
//     * @param tgtPoints	A vector of points.
//     *
//     * @return List<Point> The point(s) with the greater y-axis coordinate.
//     */
//    public static List<Point> GeomUtil
//
//    ::findBottomPoints(List<Point> tgtPoints) {
//
//        if (tgtPoints.empty()) {
//            return tgtPoints;
//        }
//
//        List<Point> chosenPoints;
//        sort(tgtPoints.begin(), tgtPoints.end(), CmpUtil::comparePointY_D);
//
//        int baseCoord = tgtPoints[0].y;
//        for (unsigned  int i = 0;i< tgtPoints.size
//        (); i++
//        
//            ) {
//
//		if (tgtPoints[i].y == baseCoord) {
//                chosenPoints.push_back(tgtPoints[i]);
//            } else {
//                break;
//            }
//        }
//
//        return chosenPoints;
//    }
//
//    /**
//     * This method returns the point with the smaller x-axis coordinate within
//     * the given
//     * set of points.
//     *
//     * @param tgtPoints	A vector of points.
//     *
//     * @return Point The point with the smaller x-axis coordinate.
//     */
//    public static Point findLeftPoint(List<Point> tgtPoints) {
//
//        if (tgtPoints.empty()) {
//            return Point(-1, -1);
//        }
//
//        sort(tgtPoints.begin(), tgtPoints.end(), CmpUtil::comparePointX_A);
//        return tgtPoints[0];
//    }
//
//    /**
//     * This method returns the point(s) with the smaller x-axis coordinate
//     * within the given
//     * set of points.
//     *
//     * @param tgtPoints	A vector of points.
//     *
//     * @return List<Point> The point(s) with the smaller x-axis coordinate.
//     */
//    public static List<Point> GeomUtil
//
//    ::findLeftPoints(List<Point> tgtPoints) {
//
//        if (tgtPoints.empty()) {
//            return tgtPoints;
//        }
//
//        List<Point> chosenPoints;
//        sort(tgtPoints.begin(), tgtPoints.end(), CmpUtil::comparePointX_A);
//
//        int baseCoord = tgtPoints[0].x;
//        for (size_t i = 0; i < tgtPoints.size(); i++) {
//
//            if (tgtPoints[i].x == baseCoord) {
//                chosenPoints.push_back(tgtPoints[i]);
//            } else {
//                break;
//            }
//        }
//
//        return chosenPoints;
//    }
//
//    /**
//     * This method returns the point with the greater x-axis coordinate within
//     * the given
//     * set of points.
//     *
//     * @param tgtPoints	A vector of points.
//     *
//     * @return Point The point with the greater x-axis coordinate.
//     */
//    public static Point findRightPoint(List<Point> tgtPoints) {
//
//        if (tgtPoints.empty()) {
//            return Point(-1, -1);
//        }
//
//        sort(tgtPoints.begin(), tgtPoints.end(), CmpUtil::comparePointX_D);
//        return tgtPoints[0];
//    }
//
//    /**
//     * This method returns the point(s) with the greater x-axis coordinate
//     * within the given
//     * set of points.
//     *
//     * @param tgtPoints	A vector of points.
//     *
//     * @return List<Point> The point(s) with the greater x-axis coordinate.
//     */
//    public static List<Point> GeomUtil
//
//    ::findRightPoints(List<Point> tgtPoints) {
//
//        if (tgtPoints.empty()) {
//            return tgtPoints;
//        }
//
//        List<Point> chosenPoints;
//        sort(tgtPoints.begin(), tgtPoints.end(), CmpUtil::comparePointX_D);
//
//        int baseCoord = tgtPoints[0].x;
//        for (unsigned  int i = 0;i< tgtPoints.size
//        (); i++
//        
//            ) {
//
//		if (tgtPoints[i].x == baseCoord) {
//                chosenPoints.push_back(tgtPoints[i]);
//            } else {
//                break;
//            }
//        }
//
//        return chosenPoints;
//    }
//
//    /**
//     * Converts OpenCV coordinates in cartesian coordinates.
//     *
//     * @param	tgtOrigin	The OpenCV coordinates of the point to be used as origin
//     * (0,0) in the cartesian plane.
//     * @param	tgtPoint	The OpenCV coordinates of the point to be converted.
//     *
//     * @return	The coordinates of 'tgtPoint', converted using 'tgtOrigin' as
//     * origin.
//     */
//    public static Point getCartesianCoordinates(Point tgtOrigin, Point tgtPoint) {
//
//        Point resPoint = tgtPoint - tgtOrigin;
//
//        return Point(resPoint.x, -resPoint.y);
//    }
//
//    /**
//     * Converts cartesian coordinates in OpenCV coordinates.
//     *
//     * @param	tgtOrigin	The OpenCV coordinates of the point which is the origin
//     * (0,0) of the cartesian plane.
//     * @param	tgtPoint	The cartesian coordinates of the point to be converted.
//     *
//     * @return	The coordinates of 'tgtPoint', converted using 'tgtOrigin' as
//     * origin.
//     */
//    public static Point getCVCoordinates(Point tgtOrigin, Point tgtPoint) {
//
//        Point resPoint = Point(tgtPoint.x, -tgtPoint.y);
//
//        return resPoint + tgtOrigin;
//    }
//
//    /**
//     * Shifts the given point, adding the offset identified by 'tgtOffset'.
//     */
//    public static Point shiftPoint(Point tgtPoint, Point tgtOffset) {
//
//        int myX = MathUtil::findBetween(tgtPoint.x + tgtOffset.x
//        , 0, 48151623);
//	int myY = MathUtil::findBetween(tgtPoint.y + tgtOffset.y
//        , 0, 48151623);
//	return Point(myX, myY);
//    }
//
//    /**
//     * Shifts the given point, adding the offset identified by 'tgtOffset' (+
//     * image inclusion check).
//     */
//    public static Point shiftPoint(Point tgtPoint, Point tgtOffset, Mat tgtImage) {
//
//        int myX = MathUtil::findBetween(tgtPoint.x + tgtOffset.x, 0, tgtImage.cols
//        );
//	int myY = MathUtil::findBetween(tgtPoint.y + tgtOffset.y, 0, tgtImage.rows
//        );
//	return Point(myX, myY);
//    }
//
//    /**
//     * Shifts the given points, adding the offset identified by 'tgtOffset'.
//     */
//    public static List<Point> GeomUtil
//
//    ::shiftPoints(List<Point> tgtPoints, Point tgtOffset) {
//
//        for (size_t i = 0; i < tgtPoints.size(); i++) {
//            tgtPoints[i] = shiftPoint(tgtPoints[i], tgtOffset);
//        }
//
//        return tgtPoints;
//    }
//
//    /**
//     * Shifts the given points, adding the offset identified by 'tgtOffset'.
//     */
//    public static List<List<Point>> GeomUtil
//
//    ::shiftPoints(vector<List<Point>> tgtPoints, Point tgtOffset) {
//
//        for (size_t i = 0; i < tgtPoints.size(); i++) {
//            tgtPoints[i] = shiftPoints(tgtPoints[i], tgtOffset);
//        }
//
//        return tgtPoints;
//    }
//
//    /**
//     * Rotates the given point, using the given pivot and angle.
//     */
//    public static Point rotatePoint(Point tgtPoint, Point tgtPivot, double tgtAngle) {
//
//        double radAngle = tgtAngle * 3.141592653589793 / 180.0;
//
//        tgtPoint -= tgtPivot;
//
//        Point resultPoint((tgtPoint.x * cos(radAngle) - tgtPoint.y * sin(radAngle)), (tgtPoint.x * sin(radAngle) + tgtPoint.y * cos(radAngle)
//        ));
//
//	resultPoint += tgtPivot;
//
//        return resultPoint;
//    }
//
//    /**
//     * Rotates the given points, using the given pivot and angle.
//     */
//    public static List<Point> GeomUtil
//
//    ::rotatePoints(List<Point> tgtPoints, Point tgtPivot, double tgtAngle) {
//
//        for (size_t i = 0; i < tgtPoints.size(); i++) {
//            tgtPoints[i] = rotatePoint(tgtPoints[i], tgtPivot, tgtAngle);
//        }
//        return tgtPoints;
//    }

}
