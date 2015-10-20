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
    // METHODS - Geometric: geometric
    // --------------------------------------------------------------------- | 

    /**
     * Calculates the positive atan2 of the given point.
     *
     * @param tgtPoint
     * @return 
     */
    public static float arctangent2(Point tgtPoint) {

        float angleRad = (float) Math.atan2(tgtPoint.y(), tgtPoint.x());

        if (angleRad < 0) {
            angleRad += 2 * Math.PI;
        }

        return angleRad;
    }

///**
// * Calculates the inner angle between three points ('tgtPointOne' - 'tgtVertex' - 'tgtPointTwo').
// * When converting from CV coords to Cartesian coords, 'tgtVertex' is used as origin (0,0) of the plane.
// */
//float GeomUtil::innerAngle(Point tgtPointOne, Point tgtPointTwo, Point tgtVertex) {
//
//	if (tgtVertex == tgtPointTwo || tgtVertex == tgtPointOne) {
//		LOG("[ImgCheck::findInnerAngle] Fatal error.");
//		exit(-1);
//	}
//
//	Point v1 = getCartesianCoordinates(tgtVertex, tgtPointOne);
//	Point v2 = getCartesianCoordinates(tgtVertex, tgtPointTwo);
//
//	float len1 = sqrt(v1.x * v1.x + v1.y * v1.y);
//	float len2 = sqrt(v2.x * v2.x + v2.y * v2.y);
//
//	float dot = v1.x * v2.x + v1.y * v2.y;
//
//	float a = dot / (len1 * len2);
//
//	if (a >= 1.0)
//		return 0.0;
//	else if (a <= -1.0)
//		return M_PI;
//	else
//		return acos(a); // 0..PI
//}
//
///**
// * Calculates the inner angle between two lines.
// * When converting from CV coords to Cartesian coords, the start point of the first line is used as origin (0,0) of the plane.
// */
//float GeomUtil::innerAngle(Point tgtOneStart, Point tgtOneEnd, Point tgtTwoStart, Point tgtTwoEnd) {
//
////	LOG("[ImgCheck::findInnerAngle] Line 01: " << tgtOneStart << " to " << tgtOneEnd);
////	LOG("[ImgCheck::findInnerAngle] Line 02: " << tgtTwoStart << " to " << tgtTwoEnd);
//
//	Point start1(0, 0);
//	Point end1 = getCartesianCoordinates(tgtOneStart, tgtOneEnd);
//	Point start2 = getCartesianCoordinates(tgtOneStart, tgtTwoStart);
//	Point end2 = getCartesianCoordinates(tgtOneStart, tgtTwoEnd);
//
//	float a = end1.x - start1.x;
//	float b = end1.y - start1.y;
//	float c = end2.x - start2.x;
//	float d = end2.y - start2.y;
//
//	float Z = ((a * c) + (b * d)) / (sqrt(a * a + b * b) * sqrt(c * c + d * d));
//
//	float theta = acos(Z);
//
//	if (theta > (M_PI / 2)) {
//		theta = M_PI - theta;
//	}
//
//	return theta;
//}
//
///**
// * Calculates the oriented angle between three points ('tgtPointOne' - 'tgtVertex' - 'tgtPointTwo').
// * When converting from CV coords to Cartesian coords, 'tgtVertex' is used as origin (0,0) of the plane.
// */
//float GeomUtil::orientedAngle(Point tgtPointOne, Point tgtPointTwo, Point tgtVertex) {
//
//	Point v1 = getCartesianCoordinates(tgtVertex, tgtPointOne);
//	Point v2 = getCartesianCoordinates(tgtVertex, tgtPointTwo);
//
//	// Line 1: End - Start;
//	float a = v1.x - 0;
//	float b = v1.y - 0;
//
//	// Line 2: End - Start;
//	float c = v2.x - 0;
//	float d = v2.y - 0;
//
//	float angleRad = atan2(c, d) - atan2(a, b);
//
//	if (angleRad > M_PI)
//		angleRad = angleRad - 2 * M_PI;
//	else if (angleRad < -M_PI)
//		angleRad = angleRad + 2 * M_PI;
//
//	LOG("Angle between: " << tgtPointOne << ", " << tgtVertex << ", " << tgtPointTwo << ": " << (angleRad * (180 / M_PI)));
//
//	return angleRad;
//}    
    
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

    /**
     * Converts OpenCV coordinates in cartesian coordinates.
     *
     * @param tgtOrigin The OpenCV coordinates of the point to be used as origin (0,0) in the cartesian plane.
     * @param tgtPoint	The OpenCV coordinates of the point to be converted.
     *
     * @return	The coordinates of 'tgtPoint', converted using 'tgtOrigin' as origin.
     */
    public static Point getCartesianCoordinates(Point tgtOrigin, Point tgtPoint) {

        Point resPoint = new Point(tgtPoint.x() - tgtOrigin.x(), tgtPoint.y() - tgtOrigin.y());

        return new Point(resPoint.x(), -resPoint.y());
    }

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

    /**
     * Shifts the given point, adding the offset identified by 'tgtOffset'.
    
     * @param tgtPoint
     * @param tgtOffset
     * @return 
     */
    public static Point shiftPoint(Point tgtPoint, Point tgtOffset) {

        int myX = MathUtil.findBetween(tgtPoint.x() + tgtOffset.x(), 0, MAX_BOUND);
	int myY = MathUtil.findBetween(tgtPoint.y() + tgtOffset.y(), 0, MAX_BOUND);
	return new Point(myX, myY);
    }

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
    /**
     * Shifts the given points, adding the offset identified by 'tgtOffset'.
    
     * @param tgtPoints
     * @param tgtOffset
     * @return 
     */
    public static List<Point> shiftPoints(List<Point> tgtPoints, Point tgtOffset) {

        for (Point curPoint : tgtPoints) {
            curPoint = shiftPoint(curPoint, tgtOffset);
        }

        return tgtPoints;
    }
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

    // --------------------------------------------------------------------- | 
    // METHODS - Geometric: mid-level operations on coordinates
    // --------------------------------------------------------------------- | 

    /**
     * Finds the distance between two given points.
     *
     * @param tgtPointA
     * @param tgtPointB
     * @return 
     */
    public static float distance(Point tgtPointA, Point tgtPointB) {

        float currentDistance = (float) Math.sqrt(Math.pow((tgtPointA.x() - tgtPointB.x()), 2) + Math.pow((tgtPointA.y() - tgtPointB.y()), 2));

        return currentDistance;
    }

///**
// * Finds the average distance between the given set of points and a given point.
// */
//float GeomUtil::avgDistance(Point tgtPoint, vector<Point> tgtPoints) {
//
//	float distance = 0;
//	int points = 0;
//
//	for (unsigned int i = 0; i < tgtPoints.size(); i++) {
//
//		distance += GeomUtil::distance(tgtPoint, tgtPoints[i]);
//		points++;
//	}
//
//	if (points == 0) {
//		return -1;
//	} else {
//		return distance / points;
//	}
//
//}
//
///**
// * Checks if the x-axis distance between two points is lesser than a given error threshold.
// */
//bool GeomUtil::checkDistanceX(Point tgtPoint1, Point tgtPoint2, int tgtError) {
//
//	return (tgtPoint2.x >= tgtPoint1.x - tgtError && tgtPoint2.x <= tgtPoint1.x + tgtError);
//}
//
///**
// * Checks if the y-axis distance between two points is lesser than a given error threshold.
// */
//bool GeomUtil::checkDistanceY(Point tgtPoint1, Point tgtPoint2, int tgtError) {
//
//	return (tgtPoint2.y >= tgtPoint1.y - tgtError && tgtPoint2.y <= tgtPoint1.y + tgtError);
//}
//
///**
// * Finds the centroid of the given set of points.
// */
//Point GeomUtil::findCentroid(vector<Point> tgtPoints) {
//
//	int sumX = 0;
//	int sumY = 0;
//	int size = tgtPoints.size();
//	Point centroid;
//	if (size > 0) {
//
//		for (unsigned int i = 0; i < tgtPoints.size(); i++) {
//			sumX += tgtPoints[i].x;
//			sumY += tgtPoints[i].y;
//		}
//
//		centroid.x = sumX / size;
//		centroid.y = sumY / size;
//	}
//
//	return centroid;
//}
//
///********************************************//**
// *  METHODS - Geometric: hi-level operations on coordinates
// ***********************************************/
//
///**
// * Checks if a given Point respects the boundaries of a given image.
// */
//bool GeomUtil::checkInclusion(Mat tgtImage, Point tgtPoint) {
//
//	if (tgtPoint.x >= 0 && tgtPoint.x < tgtImage.cols && tgtPoint.y >= 0 && tgtPoint.y < tgtImage.rows)
//		return true;
//	return false;
//}
//
///**
// * Calculates the orthogonal projection of 'refPoint' along the given line.
// */
//Point GeomUtil::findPointProjectionOnLine(Point tgtLineStart, Point tgtLineEnd, Point refPoint) {
//
//	Point lSt = Point(0, 0);
//	Point lEn = getCartesianCoordinates(tgtLineStart, tgtLineEnd);
//	Point pnt = getCartesianCoordinates(tgtLineStart, refPoint);
//	Point projectionPoint;
//
//	float t = (((pnt.x - lSt.x) * (lEn.x - lSt.x)) + ((pnt.y - lSt.y) * (lEn.y - lSt.y))) / (pow((lEn.x - lSt.x), 2) + pow((lEn.y - lSt.y), 2));
//
////	LOG("[ImgCheck::findPointProjectionOnLine] Line: " << lSt << " to " << lEn << ". Point: " << pnt);
////	LOG("[ImgCheck::findPointProjectionOnLine] t: " << t);
//
//	projectionPoint.x = lSt.x + (t * (lEn.x - lSt.x));
//	projectionPoint.y = lSt.y + (t * (lEn.y - lSt.y));
//
////	LOG("[ImgCheck::findPointProjectionOnLine] Projection: " << projectionPoint);
//
//	return getCVCoordinates(tgtLineStart, projectionPoint);
//}
//
///**
// * Calculates the point which is closer to 'refPoint' along the given line.
// */
//Point GeomUtil::findPointCloserOnLine(Mat tgtImage, Point tgtOne, Point tgtTwo, Point tgtRefPoint) {
//
//	LineIterator curLineIterator(tgtImage, tgtOne, tgtTwo, 8, false);
//
//	Point minPoint(-1, -1);
//
//	for (int k = 0; k < curLineIterator.count; k++, curLineIterator++) {
//		Point linePoint = curLineIterator.pos();
//		if (minPoint.x == -1 || distance(minPoint, tgtRefPoint) > distance(linePoint, tgtRefPoint))
//			minPoint = linePoint;
//	}
//
//	return minPoint;
//}
//
///**
// * Alters the distance between 'tgtOrigin' and 'tgtReference', preserving their respective directions.
// */
//Point GeomUtil::alterDistance(Point tgtOrigin, Point tgtReference, float tgtFactor) {
//
//	Point cartReference = GeomUtil::getCartesianCoordinates(tgtOrigin, tgtReference);
//	Point cartOrigin = Point(0, 0);
//
//	float distance = GeomUtil::distance(cartOrigin, cartReference);
//	float newDistance = distance + (tgtFactor * distance);
//
//	Point cartNewPoint = (newDistance / distance) * cartReference;
//
//	return GeomUtil::getCVCoordinates(tgtOrigin, cartNewPoint);
//}
//
///********************************************//**
// *  METHODS - Geometric: operations on shapes
// ***********************************************/
//
///**
// * Alters the size of the given rectangle, keeping its center untouched.
// */
//Rect GeomUtil::scaleRect(Rect tgtRect, float tgtFactor) {
//
//	float tgtScaling = 1 - tgtFactor;
//
//	int newX = tgtRect.x + (tgtScaling/2 * tgtRect.width);
//	int newY = tgtRect.y + (tgtScaling/2 * tgtRect.height);
//	int newWidth = tgtRect.width * tgtFactor;
//	int newHeight = tgtRect.height * tgtFactor;
//
//	return Rect(newX, newY, newWidth, newHeight);
//}
//
///**
// * Alters the size of the given rectangle, keeping its center untouched (+ image inclusion check).
// */
//Rect GeomUtil::scaleRect(Mat tgtImage, Rect tgtRect, float tgtFactor) {
//
//	return adjustRect( tgtImage, scaleRect(tgtRect, tgtFactor));
//}
//
///**
// * Ensures that the given rectangle lies within the boundaries of the given image.
// */
//Rect GeomUtil::adjustRect(Mat tgtImage, Rect tgtRect) {
//
//	tgtRect.x = MathUtil::findBetween(tgtRect.x, 0, tgtImage.cols);
//	tgtRect.y = MathUtil::findBetween(tgtRect.y, 0, tgtImage.rows);
//
//	if(tgtRect.x + tgtRect.width > tgtImage.cols)
//		tgtRect.width = tgtImage.cols - tgtRect.x;
//
//	if(tgtRect.y + tgtRect.height > tgtImage.rows)
//		tgtRect.height = tgtImage.rows - tgtRect.y;
//
//	return tgtRect;
//}
//
///**
// * Returns the points which form the perimeter of the given contour.
// * The points are picked with an interval of 'tgtMinDistance' between each other.
// */
//vector<Point> GeomUtil::findContourPerimeter(Mat tgtImage, vector<Point> tgtContour, int tgtMinDistance) {
//
//	vector<Point> myPerimeter;
//	int minCounter = 0;
//
//	for (size_t i = 1; i < tgtContour.size(); i++) {
//
//		LineIterator curLineIterator(tgtImage, tgtContour[i - 1], tgtContour[i], 8, false);
//		//	myPerimeter.push_back(tgtContour[i-1]);
//
//		for (int k = 0; k < curLineIterator.count; k++, curLineIterator++, minCounter = (minCounter + 1) % tgtMinDistance) {
//
//			if (k == 0)
//				continue;
//			if (minCounter == 0)
//				myPerimeter.push_back(curLineIterator.pos());
//		}
//	}
//
//	return myPerimeter;
//}
//
///**
// * 	Finds the Minimum Enclosing Circle of the given set of points.
// */
//GeometricCircle GeomUtil::findMEC(vector<Point> tgtPoints) {
//
//	Point_<float> centerFloat;
//	float radiusFloat = 0;
//	minEnclosingCircle((Mat) tgtPoints, centerFloat, radiusFloat);
//
//	Point center;
//	int radius;
//	center.x = floor(centerFloat.x);
//	center.y = floor(centerFloat.y);
//	radius = floor(radiusFloat);
//
//	return GeometricCircle(center, radius);
//}
//
///**
// * Finds the Minimum Bounding Rectangle (MBR) of the given set of points.
// */
//Rect GeomUtil::findMBR(vector<Point> tgtPoints) {
//
//	int minX = max(0, findMinX(tgtPoints));
//	int maxX = max(0, findMaxX(tgtPoints));
//	int minY = findMinY(tgtPoints);
//	int maxY = findMaxY(tgtPoints);
//
//	return Rect(minX, minY, maxX - minX, maxY - minY);
//}
//
///**
// * Finds the Minimum Bounding Rectangle (MBR) of the given set of points (+ image inclusion check).
// */
//Rect GeomUtil::findMBR(Mat tgtImage, vector<Point> tgtPoints) {
//
//	int minX = MathUtil::findBetween(findMinX(tgtPoints), 0, tgtImage.cols -1);
//	int maxX = MathUtil::findBetween(findMaxX(tgtPoints), 0, tgtImage.cols -1);
//	int minY = MathUtil::findBetween(findMinY(tgtPoints), 0, tgtImage.rows -1);
//	int maxY = MathUtil::findBetween(findMaxY(tgtPoints), 0, tgtImage.rows -1);
//
//	return Rect(minX, minY, maxX - minX, maxY - minY);
//}
//
///**
// * Returns true if there is a overlap between the input circles.
// */
//bool GeomUtil::overlapCircle2Circle(GeometricCircle tgtCircleOne, GeometricCircle tgtCircleTwo) {
//
//	float distanceBetweenCenters = GeomUtil::distance(tgtCircleOne.getCenter(), tgtCircleTwo.getCenter());
//
//	return (distanceBetweenCenters) <= tgtCircleOne.getRadius() + tgtCircleTwo.getRadius();
//}
//
//bool GeomUtil::overlapCircle2Circle(Point tgtCenterOne, int tgtRadiusOne, Point tgtCenterTwo, int tgtRadiusTwo) {
//
//	float distanceBetweenCenters = GeomUtil::distance(tgtCenterOne, tgtCenterTwo);
//
//	return (distanceBetweenCenters) <= tgtRadiusOne + tgtRadiusTwo;
//}
//
//int GeomUtil::overlapratioCircle2Circle(GeometricCircle tgtCircle, GeometricCircle tgtRefCircle) {
//
//	/* WARNING: NOT TESTED */
//
//	float r0 = tgtCircle.getRadius();
//	float r1 = tgtRefCircle.getRadius();
//	float dst = distance(tgtCircle.getCenter(), tgtRefCircle.getCenter());
//
//	cout << "[ImgCheck][overlapCircle2Circle] distance: " << dst << endl;
//
//	if (dst > r0 + r1)
//		return 0;
//
//	if (dst == 0 || dst < abs(r0 - r1))
//		return 100;
//
//	float part1 = r0 * r0 * acos((dst * dst + r0 * r0 - r1 * r1) / (2 * dst * r0));
//	float part2 = r1 * r1 * acos((dst * dst + r1 * r1 - r0 * r0) / (2 * dst * r1));
//	float part3 = 0.5 * sqrt((-dst + r0 + r1) * (dst + r0 - r1) * (dst - r0 + r1) * (dst + r0 + r1));
//
//	float intersectionArea = part1 + part2 - part3;
//
//	cout << "[ImgCheck][overlapCircle2Circle] intersectionArea: " << intersectionArea << endl;
//
//	return 1;
//}
//
///**
// * Returns true if there is a overlap between the input circle and the input rectangle.
// */
//bool GeomUtil::overlapCircle2Rectangle(GeometricCircle tgtCircle, Rect tgtRect) {
//
//	Point rectCenter(tgtRect.x + tgtRect.width / 2, tgtRect.y + tgtRect.height / 2);
//	Point circleDistance;
//
//	circleDistance.x = abs(tgtCircle.getCenter().x - rectCenter.x);
//	circleDistance.y = abs(tgtCircle.getCenter().y - rectCenter.y);
//
//	if (circleDistance.x > (tgtRect.width / 2 + tgtCircle.getRadius())) {
//		return false;
//	}
//	if (circleDistance.y > (tgtRect.height / 2 + tgtCircle.getRadius())) {
//		return false;
//	}
//
//	if (circleDistance.x <= (tgtRect.width / 2)) {
//		return true;
//	}
//	if (circleDistance.y <= (tgtRect.height / 2)) {
//		return true;
//	}
//
//	double cornerDistance_sq = pow((circleDistance.x - tgtRect.width / 2), 2) + pow((circleDistance.y - tgtRect.height / 2), 2);
//
//	return (cornerDistance_sq <= pow(tgtCircle.getRadius(), 2));
//}
//
///**
// * Finds the points of tgtPoints which lie within the given bounds.
// */
//vector<Point> GeomUtil::findPointsInBounds(vector<Point> tgtPoints, Rect tgtRect) {
//
//	vector<Point> resultPoints;
//
//	for (size_t i = 0; i < tgtPoints.size(); i++) {
//
//		if (tgtRect.tl().x <= tgtPoints[i].x && tgtRect.tl().y <= tgtPoints[i].y && tgtRect.br().x >= tgtPoints[i].x
//				&& tgtRect.br().y >= tgtPoints[i].y)
//
//			resultPoints.push_back(tgtPoints[i]);
//	}
//
//	return resultPoints;
//}
//
///**
// * Returns the percentage of tgtRect which lies within the vertical/horizontal projection of refRect.
// * The percentage is calculate using the width (in case of VERTICAL projection) or the height (in case of
// * HORIZONTAL projection) of the tgtRect.
// *
// * @param tgtRect		A rectagle.
// * @param refRect		The reference rectangle (the one the projection is applied to).
// * @param tgtDirection 	Identifies the direction (VERTICAL or HORIZONTAL);
// * @return 				The percentage of tgtRect which lies within the vertical/horizontal projection of refRect
// */
//float GeomUtil::withinProjection(Rect tgtRect, Rect refRect, int tgtDirection) {
//
//	if (tgtDirection == HORIZONTAL) {
//		int start = max(tgtRect.tl().y, refRect.tl().y);
//		int end = min(tgtRect.br().y, refRect.br().y);
//
//		return (100 * (end - start)) / tgtRect.height;
//	} else if (tgtDirection == VERTICAL) {
//
//		int start = max(tgtRect.tl().x, refRect.tl().x);
//		int end = min(tgtRect.br().x, refRect.br().x);
//
//		return (100 * (end - start)) / tgtRect.width;
//	}
//
//	return 0.0;
//}
//
///********************************************//**
// *  METHODS - 'Directioners'
// ***********************************************/
//
///**
// * 	Returns a numeric constant which identifies the direction of the destination point
// * 	('tgtDestination') with respect to the source point ('tgtSource').
// * 	As an example, if the function returns P2P_NORTHWEST, it means that 'tgtDestination'
// * 	is northwest of 'tgtSource'.
// *
// * 	@param	tgtSource				The source point.
// * 	@param	tgtDestination			The destination point.
// *
// * 	@return A numeric constant which identifies the direction of the destination point with
// * 			respect to the source point.
// *
// */
//int GeomUtil::directionPointToPoint(Point tgtSource, Point tgtDestination) {
//
//	if (tgtSource.x > tgtDestination.x) {
//
//		if (tgtSource.y > tgtDestination.y)
//			return P2P_NORTH_WEST;
//		if (tgtSource.y == tgtDestination.y)
//			return P2P_RESTRICTED_WEST;
//		if (tgtSource.y < tgtDestination.y)
//			return P2P_SOUTH_WEST;
//	}
//	if (tgtSource.x == tgtDestination.x) {
//
//		if (tgtSource.y > tgtDestination.y)
//			return P2P_RESTRICTED_NORTH;
//		if (tgtSource.y == tgtDestination.y)
//			return P2P_SAME_POSITION;
//		if (tgtSource.y < tgtDestination.y)
//			return P2P_RESTRICTED_SOUTH;
//	}
//	if (tgtSource.x < tgtDestination.x) {
//
//		if (tgtSource.y > tgtDestination.y)
//			return P2P_NORTH_EAST;
//		if (tgtSource.y == tgtDestination.y)
//			return P2P_RESTRICTED_EAST;
//		if (tgtSource.y < tgtDestination.y)
//			return P2P_SOUTH_EAST;
//	}
//
//	return UNIDENTIFIED;
//}
//
///**
// * Converts a Point2Point (P2P) direction into a Point offset.
// */
//Point GeomUtil::directionPointToPoint_Offset(int tgtDirection) {
//
//	switch (tgtDirection) {
//		case P2P_NORTH_WEST:
//			return Point(-1, -1);
//		case P2P_RESTRICTED_NORTH:
//			return Point(0, -1);
//		case P2P_NORTH_EAST:
//			return Point(1, -1);
//		case P2P_RESTRICTED_WEST:
//			return Point(-1, 0);
//		case P2P_SAME_POSITION:
//			return Point(0, 0);
//		case P2P_RESTRICTED_EAST:
//			return Point(1, 0);
//		case P2P_SOUTH_WEST:
//			return Point(-1, 1);
//		case P2P_RESTRICTED_SOUTH:
//			return Point(0, 1);
//		case P2P_SOUTH_EAST:
//			return Point(1, 1);
//	}
//	LOG("[GeomUtil::directionPointToPoint_Offset] Fatal error.");
//	exit(-1);
//	return Point(-1, -1);
//}
//
///**
// * Converts a Point2Point (P2P) direction into a String message.
// */
//String GeomUtil::directionPointToPoint_String(int tgtDirection) {
//
//	switch (tgtDirection) {
//	case P2P_NORTH_WEST:
//		return "NORTH_WEST";
//	case P2P_RESTRICTED_NORTH:
//		return "NORTH";
//	case P2P_NORTH_EAST:
//		return "NORTH_EAST";
//	case P2P_RESTRICTED_WEST:
//		return "WEST";
//	case P2P_SAME_POSITION:
//		return "SAME";
//	case P2P_RESTRICTED_EAST:
//		return "EAST";
//	case P2P_SOUTH_WEST:
//		return "SOUTH_WEST";
//	case P2P_RESTRICTED_SOUTH:
//		return "SOUTH";
//	case P2P_SOUTH_EAST:
//		return "SOUTH_EAST";
//	}
//
//	return "UNIDENTIFIED";
//}
//
///**
// * Converts a Point2Point (P2P) direction into its opposite direction.
// */
//int GeomUtil::directionPointToPoint_Opposite(int tgtDirection) {
//
//	return (tgtDirection + 4) % 8;
//}
//
///**
// * Checks if the given Point2Point (P2P) directions are compatible.
// */
//bool GeomUtil::directionPointToPoint_Compatible(int tgtDirectionOne, int tgtDirectionTwo) {
//
//	return MathUtil::modulo((tgtDirectionOne + 1), 8) == tgtDirectionTwo || tgtDirectionOne == tgtDirectionTwo
//			|| MathUtil::modulo((tgtDirectionOne - 1), 8) == tgtDirectionTwo;
//}
//
///**
// * 	Returns a numeric constant which identifies the direction of the destination point
// * 	('tgtDestination') with respect to the source rectangle, identified by its top-left
// * 	point ('tgtSourceTL') and its bottom-right point ('tgtSourceBR').
// * 	As an example, if the function returns "P2R_NORTHWEST, it means that 'tgtDestination'
// * 	is northwest of the source rectangle..
// *
// * 	@param	tgtSourceTL		The top-left corner of the source rectangle.
// * 	@param	tgtSourceBR		The bottom-right corner of the source rectangle.
// * 	@param	tgtDestination	The destination point.
// *
// * 	@return A numeric constant which identifies the direction of the destination point with
// * 			respect to the source rectangle.
// *
// */
//int GeomUtil::directionPointToRect(Point tgtSourceTL, Point tgtSourceBR, Point tgtDestination) {
//
//	int dirTL = directionPointToPoint(tgtSourceTL, tgtDestination);
//	int dirBR = directionPointToPoint(tgtSourceBR, tgtDestination);
//
////	LOG(tgtSourceTL << " to " << tgtDestination);
////	LOG(tgtSourceBR << " to " << tgtDestination);
//
//	/* This matrix maps the two PointToPoint directions ("tgtPrimaryRectTL"-"tgtReferencePoint"
//	 * and "tgtPrimaryRectBR"-"tgtReferencePoint") into a single PointToRect direction.
//	 * Rows: TL. Columns: BR.
//	 */
//	int P2R_MAPPPING[9][9] =
//	{ { -1, -1, -1, -1, -1, -1, -1,  1, -1},
//	  {  3,  4, -1, -1, -1, -1, -1,  2, -1},
//	  {  8,  9, -1, -1, -1, -1, -1,  7, -1},
//	  { 13, 14, 19, 24, 23, -1, 17, 12, 18},
//	  { -1, -1, -1, -1, -1, 21, 16, 11, -1},
//	  { -1, -1, -1, -1, -1, 20, 15, 10, -1},
//	  { -1, -1, -1, -1, -1, -1, -1,  5, -1},
//	  { -1, -1, -1, -1, -1, -1, -1,  0, -1},
//	  { -1, -1, -1, -1, -1, -1, -1,  6, -1} };
//
////	LOG("[GeomUtil::directionPointToRect] [TL: " << dirTL << "] [BR: " << dirBR << "] = [" << P2R_MAPPPING[dirTL][dirBR] << "]");
//
//	return P2R_MAPPPING[dirTL][dirBR];
//}
//
///**
// * 	Returns a numeric constant which identifies the direction of the destination point
// * 	('tgtDestination') with respect to the source rectangle ('tgtSource').
// * 	As an example, if the function returns "P2R_NORTHWEST, it means that 'tgtDestination'
// * 	is northwest of the source rectangle..
// *
// * 	@param	tgtSource		The source rectangle.
// * 	@param	tgtDestination	The destination point.
// *
// * 	@return A numeric constant which identifies the direction of the destination point with
// * 			respect to the source rectangle.
// *
// */
//int GeomUtil::directionPointToRect(Rect tgtSource, Point tgtDestination) {
//
//	return directionPointToRect(tgtSource.tl(), tgtSource.br(), tgtDestination);
//}
//
///**
// * Checks if the given Point2Rect (P2R) direction is a EAST direction.
// */
//bool GeomUtil::directionPointToRectIsEast(int tgtDirection) {
//	return tgtDirection == P2R_NORTH_EAST || tgtDirection == P2R_NORTHBOUND_EAST || tgtDirection == P2R_BOUNDED_EAST || tgtDirection == P2R_SOUTH_EAST
//			|| tgtDirection == P2R_SOUTHBOUND_EAST;
//}
//
///**
// * Checks if the given Point2Rect (P2R) direction is a WEST direction.
// */
//bool GeomUtil::directionPointToRectIsWest(int tgtDirection) {
//	return tgtDirection == P2R_NORTH_WEST || tgtDirection == P2R_NORTHBOUND_WEST || tgtDirection == P2R_BOUNDED_WEST || tgtDirection == P2R_SOUTH_WEST
//			|| tgtDirection == P2R_SOUTHBOUND_WEST;
//}    
//    
}
