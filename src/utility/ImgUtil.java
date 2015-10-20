/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import java.util.List;
import model.GeometricCircle;
import org.apache.log4j.Logger;
import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Scalar;

/**
 *
 * @author fborgia
 */
public class ImgUtil {

    final static Logger logger = Logger.getLogger(ImgUtil.class);
//
//    /* Loaded from static library */
//void thin(cv::Mat& img, bool need_boundary_smoothing = false, bool need_acute_angle_emphasis = false, bool destair = false);
//
///********************************************//**
// *  METHODS - Foreground detection
// ***********************************************/
//
///**
// * Checks if two given points are connected (with an error margin). The check can be performed in two different ways.
// * Strict: the check is performed evaluating any point along the line connecting tgtSrc and tgtDest.
// * Normal: The check is performed evaluating any point along the line connecting tgtSrc and tgtDest and the neighborhood of
// * 		   each of these point, if necessary.
// *
// */
//bool ImgUtil::checkConnection_Line(Mat tgtImage, Point tgtSrc, Point tgtDest, bool tgtStrict) {
//
//	LineIterator curLineIterator(tgtImage, tgtSrc, tgtDest, 8, false);
//
//	int curWhiteSpacesError = 0;
//	int maxWhiteSpacesError = MathUtil::minimum(CONNECTION_MAXWHITE_MIN, curLineIterator.count * CONNECTION_MAXWHITE_RATIO);
//
//	for (int k = 0; k < curLineIterator.count; k++, curLineIterator++) {
//
//		Point linePoint = curLineIterator.pos();
//
//		if (tgtImage.at<uchar>(linePoint) != 255)
//			continue;
//
//		if (!tgtStrict && checkForeground_OrthLine(tgtImage, linePoint, tgtSrc, tgtDest))
//			continue;
//
//		if (++curWhiteSpacesError > maxWhiteSpacesError)
//			break;
//	}
//
////	LOG("[ImgCheck::checkConnection] [" << (curWhiteSpacesError <= maxWhiteSpacesError) << "]curError: " << curWhiteSpacesError << " VS " << "maxError: " << maxWhiteSpacesError << ".");
//	return (curWhiteSpacesError <= maxWhiteSpacesError);
//}
//
///**
// * 	Returns any foreground pixel in a 3x3 matrix centered on the given point (excluding the point itself).
// */
//vector<Point> ImgUtil::findForeground_3x3(Mat tgtImage, Point tgtSrc) {
//
//	return findForeground_3x3(tgtImage, tgtSrc, 255);
//}
//
///**
// * 	Returns any foreground pixel in a 3x3 matrix centered on the given point (excluding the point itself).
// */
//vector<Point> ImgUtil::findForeground_3x3(Mat tgtImage, Point tgtSrc, int tgtBgValue) {
//
//	vector<Point> resultPoints;
//
//	for(int iX = tgtSrc.x - 1; iX <= tgtSrc.x + 1; iX++ ){
//
//		for(int iY = tgtSrc.y - 1; iY <= tgtSrc.y + 1; iY++ ){
//
//			if(iX == tgtSrc.x && iY == tgtSrc.y)	continue;
//
//			if (MathUtil::isBetween(Point(iX, iY), Point(0,0), Point(tgtImage.cols -1, tgtImage.rows -1)) && tgtImage.at<uchar>(Point(iX, iY)) != tgtBgValue){
//				resultPoints.push_back(Point(iX, iY));
//			}
//		}
//	}
//
//	return resultPoints;
//}
//
///**
// * 	Returns any foreground pixel in a 5x5 matrix centered on the given point (excluding the point itself).
// */
//vector<Point> ImgUtil::findForeground_5x5(Mat tgtImage, Point tgtSrc) {
//
//	vector<Point> resultPoints;
//
//	for(int iX = tgtSrc.x - 2; iX <= tgtSrc.x + 2; iX++ ){
//
//		for(int iY = tgtSrc.y - 2; iY <= tgtSrc.y + 2; iY++ ){
//
//			if(iX == tgtSrc.x && iY == tgtSrc.y)	continue;
//
//			if (MathUtil::isBetween(Point(iX, iY), Point(0,0), Point(tgtImage.cols -1, tgtImage.rows -1)) && tgtImage.at<uchar>(Point(iX, iY)) != 255){
//				resultPoints.push_back(Point(iX, iY));
//			}
//		}
//	}
//
//	return resultPoints;
//}
//
///**
// * Checks if the line between two given points contains any foreground pixel.
// */
//bool ImgUtil::checkForeground_Line(Mat tgtImage, Point tgtSrc, Point tgtDest) {
//
//	LineIterator curLineIterator(tgtImage, tgtSrc, tgtDest, 8, false);
//
//	for (int k = 0; k < curLineIterator.count; k++, curLineIterator++) {
//
//		Point linePoint = curLineIterator.pos();
//
//		if (tgtImage.at<uchar>(linePoint) != 255)
//			return true;
//	}
//
//	return false;
//}
//
///**
// *	Returns the percentage of foreground pixels along the the line between two given points.
// */
//float ImgUtil::countForeground_Line(Mat tgtImage, Point tgtSrc, Point tgtDest) {
//
//	LineIterator curLineIterator(tgtImage, tgtSrc, tgtDest, 8, false);
//
//	int contatore = 0;
//	int all = 0;
//
//	for (int k = 0; k < curLineIterator.count; k++, curLineIterator++, all++) {
//
//		Point linePoint = curLineIterator.pos();
//		if (tgtImage.at<uchar>(Point(linePoint.x, linePoint.y)) == 0)
//			contatore++;
//	}
//
//	float f = contatore * 100 / all;
//	return f;
//}
//
///**
// * Returns any foreground pixel on the line between two given points.
// */
//vector<Point> ImgUtil::findForeground_Line(Mat tgtImage, Point tgtSrc, Point tgtDest) {
//
//	vector<Point> resultPoints;
//
//	LineIterator curLineIterator(tgtImage, tgtSrc, tgtDest, 8, false);
//
//	for (int k = 0; k < curLineIterator.count; k++, curLineIterator++) {
//
//		Point linePoint = curLineIterator.pos();
//
//		if (tgtImage.at<uchar>(linePoint) != 255)
//			resultPoints.push_back(linePoint);
//	}
//
//	return resultPoints;
//}
//
///**
// * Finds the perpendicular segment of a given point (curPoint) on the line between tgtSrc and tgtDest and checks
// * if it contains any foreground pixel.
// */
//bool ImgUtil::checkForeground_OrthLine(Mat tgtImage, Point curPoint, Point tgtSrc, Point tgtDest) {
//
//	int dX = abs(tgtSrc.x - tgtDest.x);
//	int dY = abs(tgtSrc.y - tgtDest.y);
//	Point p1, p2;
//
//	int segmentLen = GeomUtil::distance(tgtSrc, tgtDest) * NEIGH_PERPENDICULAR_RATIO;
//	segmentLen = MathUtil::findBetween(segmentLen, NEIGH_PERPENDICULAR_MIN, NEIGH_PERPENDICULAR_MAX);
//
//	if (dX < 0.25 * dY) {
//		p1 = GeomUtil::shiftPoint(curPoint, Point(-segmentLen, 0), tgtImage);
//		p2 = GeomUtil::shiftPoint(curPoint, Point(segmentLen, 0), tgtImage);
//	} else if (dY < 0.25 * dX) {
//		p1 = GeomUtil::shiftPoint(curPoint, Point(0, -segmentLen), tgtImage);
//		p2 = GeomUtil::shiftPoint(curPoint, Point(0, segmentLen), tgtImage);
//	} else {
//		segmentLen = segmentLen / 2;
//		if (tgtSrc.x - tgtDest.x > 0) {
//			p1 = GeomUtil::shiftPoint(curPoint, Point(-segmentLen, -segmentLen), tgtImage);
//			p2 = GeomUtil::shiftPoint(curPoint, Point(segmentLen, segmentLen), tgtImage);
//		} else {
//			p1 = GeomUtil::shiftPoint(curPoint, Point(segmentLen, -segmentLen), tgtImage);
//			p2 = GeomUtil::shiftPoint(curPoint, Point(-segmentLen, segmentLen), tgtImage);
//		}
//	}
//
////	vector<Point> myPoint;
////	myPoint.push_back(tgtSrc);
////	myPoint.push_back(tgtDest);
////	myPoint.push_back(p1);
////	myPoint.push_back(p2);
////	Utility::displayMat_Points(tgtImage, myPoint, "AZATOTH", true);
//
//	return checkForeground_Line(tgtImage, p1, p2);
//}
//
///**
// * Returns any foreground pixel on the perimeter of the given GeometricRectangle.
// */
//vector<Point> ImgUtil::findForeground_GeomRect(Mat tgtImage, GeometricRectangle tgtRect) {
//
//	vector<Point> vertices = tgtRect.getPoints();
//	vector<Point> resultPoints;
//
//	for (int i = 0; i < 4; i++) {
//		vector<Point> curLinePoints = findForeground_Line(tgtImage, vertices[i], vertices[(i + 1) % 4]);
//		resultPoints.insert(resultPoints.end(), curLinePoints.begin(), curLinePoints.end());
//	}
//
//	return resultPoints;
//}
//
///**
// * Returns any foreground pixel on the perimeter of the given GeometricTriangle.
// */
//vector<Point> ImgUtil::findForeground_GeomTri(Mat tgtImage, GeometricTriangle tgtTriangle) {
//
//	vector<Point> vertices = tgtTriangle.getPoints();
//	vector<Point> resultPoints;
//
//	for (int i = 0; i < 3; i++) {
//		vector<Point> curLinePoints = findForeground_Line(tgtImage, vertices[i], vertices[(i + 1) % 3]);
//		resultPoints.insert(resultPoints.end(), curLinePoints.begin(), curLinePoints.end());
//	}
//
//	return resultPoints;
//}
//
    /**
     * Find the connected components within the image.
     *
     * @param tgtImage
     * @return
     */
    public static List< List<Point>> findBlobs(Mat tgtImage) {

        List< List<Point>> blobs = new ArrayList();

        Mat woIm = tgtImage.clone();
        UByteBufferIndexer woImIdx = woIm.createIndexer();

        // Start labelling the image starting from label 2, since 0 and 1are already in use.
        int label_count = 2;

        for (int y = 0; y < woIm.rows(); y++) {

            for (int x = 0; x < woIm.cols(); x++) {

                if (woImIdx.get(y, x) == 0) {

                    Scalar fillColor = new Scalar(label_count, label_count, label_count, 1.0);

                    Rect fragBounds = new Rect();
                    opencv_imgproc.floodFill(woIm, new Point(x, y), fillColor, fragBounds, new Scalar(), new Scalar(), 8);

                    List<Point> blob = new ArrayList();

                    for (int i = fragBounds.y(); i < (fragBounds.y() + fragBounds.height()); i++) {

                        for (int j = fragBounds.x(); j < (fragBounds.x() + fragBounds.width()); j++) {

                            if (woImIdx.get(i, j) == label_count) {
                                blob.add(new Point(j, i));
                            }
                        }
                    }

                    blobs.add(blob);
//                    logger.info("Blob detected (" + blob.size() + " points). Current count: " + label_count);
                    label_count++;
                }

            }
        }

        return blobs;
    }
    
///**
// * Find the holes within the image.
// */
//vector<vector<Point> > ImgUtil::findHoles(Mat tgtImage) {
//
//	Mat workImage = tgtImage.clone();
//	vector<vector<Point> > holes;
//
//// Preliminary background fill.
//	workImage = ImgUtil::fillBackground(workImage);
//
//	Mat label_image;
//	cvtColor(workImage, label_image, CV_GRAY2BGR);
//
//// Starts at 2 because 0,1 are used already.
//	int label_count = 2;
//
//	for (int y = 0; y < workImage.rows; y++) {
//
//		for (int x = 0; x < workImage.cols; x++) {
//
//			if (workImage.at<uchar>(y, x) == 255) {
//
//				Rect rect;
//				floodFill(label_image, Point(x, y), label_count, &rect, 0, 0, 8);
//				floodFill(workImage, Point(x, y), label_count, &rect, 0, 0, 8);
//
//				vector<Point> hole;
//
//				for (int i = rect.y; i < (rect.y + rect.height); i++) {
//
//					for (int j = rect.x; j < (rect.x + rect.width); j++) {
//
//						if (workImage.at<uchar>(i, j) == label_count) {
//							hole.push_back(Point(j, i));
//						}
//
//					}
//				}
//
//				holes.push_back(hole);
//
//				label_count++;
//			}
//		}
//	}
//
//	sort(holes.begin(), holes.end(), CmpUtil::compareContourSize_D);
//	return holes;
//}
//
///**
// * Calculates the histogram of the image.
// */
//MatND ImgUtil::histogram(Mat tgtImage, int tgtBins) {
//
//	MatND histData;
//
//	// Establish the number of bins
//	int histSize = tgtBins;
//
//	// Set the ranges.
//	float range[] = { 0, 256 };
//	const float* histRange = { range };
//
//	calcHist(&tgtImage, 1, 0, Mat(), histData, 1, &histSize, &histRange, true, false);
//
//	return histData;
//}
//
///********************************************//**
// *  METHODS - Advanced checks
// ***********************************************/
//
///**
// * Checks if the points rectangle can be found using 3 fixed Points.
// */
//Point ImgUtil::buildRectagle(Point tgtCurrent, Point tgtConnectedOne, Point tgtConnectedTwo, Mat tgtImage) {
//
//	int hDiff = tgtCurrent.x - tgtConnectedOne.x;
//	int vDiff = tgtCurrent.y - tgtConnectedOne.y;
//
//	Point likelyFourth = tgtConnectedTwo - Point(hDiff, vDiff);
//
//	if (GeomUtil::checkInclusion(tgtImage, likelyFourth) && (int) (tgtImage.at<uchar>(likelyFourth)) == 0
//			&& ImgUtil::checkConnection_Line(tgtImage, tgtConnectedOne, likelyFourth, false)
//			&& ImgUtil::checkConnection_Line(tgtImage, tgtConnectedTwo, likelyFourth, false)) {
//
//		return likelyFourth;
//	}
//
//	return Point(-1, -1);
//}

/**
 *  Scales the circle using 'tgtRadiusExp' factor. The circle is divided in sectors, each sector having an angle of 'tgtStep' degrees.
 *  For each sector, the number of contained foreground pixels is counted.
 *   
 * @param tgtPoints
 * @param tgtCircle
 * @param tgtRadiusExp
 * @param tgtStep
 * @return 
 */
public static List<Integer> checkCircleSectorsCoverage(List<Point> tgtPoints, GeometricCircle tgtCircle, float tgtRadiusExp, int tgtStep) {

	Point circleCenter = tgtCircle.getCenter();
	int circleRadius = (int) (tgtCircle.getRadius() * tgtRadiusExp);

	vector<int> circleData(360 / tgtStep, 0);

	for (size_t i = 0; i < tgtPoints.size(); i++) {

		Point curCartesianPoint = GeomUtil::getCartesianCoordinates(circleCenter, tgtPoints[i]);

		float angleRad = GeomUtil::arctangent2(curCartesianPoint);
		int angleDeg = angleRad * (180 / M_PI);

		if (GeomUtil::distance(tgtPoints[i], circleCenter) <= circleRadius) {
			circleData.at(angleDeg / tgtStep) += 1;
		}

	}

	return circleData;
}

///**
// *  Scales the circle using 'tgtRadiusExp' factor. The circle is divided in sectors, each sector having an angle of 'tgtStep' degrees.
// *	For each sector, the minimum distance of the contained foreground pixels from the center of the circle is calculated.
// */
//vector<int> ImgUtil::checkCircleSectorsDistance(vector<Point> tgtPoints, GeometricCircle tgtCircle, float tgtRadiusExp, int tgtStep) {
//
//	Point circleCenter = tgtCircle.getCenter();
//	int circleRadius = tgtCircle.getRadius() * tgtRadiusExp;
//
//	vector<int> circleData(360 / tgtStep, circleRadius);
//
//	for (size_t i = 0; i < tgtPoints.size(); i++) {
//
//		Point curCartesianPoint = GeomUtil::getCartesianCoordinates(circleCenter, tgtPoints[i]);
//
//		float angleRad = GeomUtil::arctangent2(curCartesianPoint);
//		int angleDeg = angleRad * (180 / M_PI);
//
//		if (GeomUtil::distance(tgtPoints[i], circleCenter) <= circleRadius) {
//			if (circleData.at(angleDeg / tgtStep) == -1 || circleData.at(angleDeg / tgtStep) > GeomUtil::distance(tgtPoints[i], circleCenter))
//				circleData.at(angleDeg / tgtStep) = GeomUtil::distance(tgtPoints[i], circleCenter);
//		}
//
//	}
//
//	return circleData;
//}
//
///**
// *	Identifies the MBR of the given sequence of circle sector distances.
// *
// *  @param tgtCircle			The circle.
// *	@param tgtSequenceStart		The first sector of the sequence.
// *  @param tgtSequence			The values (minimum distances from the circle center).
// *  @param tgtStep				The angle of each sector (in degrees).
// *
// *	@return 					The MBR of the given sequence of circle sector distances.
// */
//Rect ImgUtil::getCircleSectorDistanceSequenceMBR(Mat tgtImage, GeometricCircle tgtCircle, int tgtSequenceStart, vector<int> tgtSequence,
//		int tgtStep) {
//
//	vector<Point> outPoints;
//	int tgtSectors = (360 / tgtStep);
//	float tgtStepRad = tgtStep * 0.0174532;
//
//	for (size_t i = 0; i < tgtSequence.size(); i++) {
//
//		int curSector = (i + tgtSequenceStart) % tgtSectors;
//
//		Point curOuterCartesian = Point(tgtCircle.getRadius() * cos(curSector * tgtStepRad), tgtCircle.getRadius() * sin(curSector * tgtStepRad));
//		Point curInnerCartesian = Point(tgtSequence[i] * cos(curSector * tgtStepRad), tgtSequence[i] * sin(curSector * tgtStepRad));
//
//		outPoints.push_back(GeomUtil::getCVCoordinates(tgtCircle.getCenter(), curOuterCartesian));
//		outPoints.push_back(GeomUtil::getCVCoordinates(tgtCircle.getCenter(), curInnerCartesian));
//	}
//
//	return GeomUtil::findMBR(tgtImage, outPoints);
//}
//
///*
// * Interpolates the points in 'myCorners'. If 'tgtPeano' is true, the points will be sorted using Peano keys, else
// * they will be sorted using their x-axis; If 'tgtMethod' is true, linear regression will be used, else linear interpolation
// * will be used. Elimination {1,2,3};
// *
// */
//void ImgUtil::interpolate(vector<Point> &tgtPts, int tgtWin, bool tgtPeano, bool tgtMethod, int tgtElim)
//{
//	//Contiene i punti da interpolare ordinati secondo il metodo scelto
//	vector<Point> toInterpolate;
//	vector< pair<int,Point> > priority;
//	for(size_t i=0; i< tgtPts.size();i++)
//	{
//		//Per ogni corner calcola la coordinata di Peano.
//		priority.push_back(pair<int,Point> (tgtPts.size()-i,tgtPts.at(i)));
//	}
//
//	if(tgtPeano)
//	{
//		vector< pair<int,Point> > peanoInterpol;
//
//		for(size_t i=0; i< tgtPts.size();i++)
//		{
//			//Per ogni corner calcola la coordinata di Peano
//			peanoInterpol.push_back(pair <int,Point> (MathUtil::zOrder(tgtPts.at(i).x,tgtPts.at(i).y),tgtPts.at(i)) );
//
//		}
//
//		sort(peanoInterpol.begin(), peanoInterpol.end(), CmpUtil::comparePairInt_A);
//
//		for(size_t w=0;w<peanoInterpol.size();w++) toInterpolate.push_back(peanoInterpol.at(w).second);
//	}
//
//	if(!tgtPeano)
//	{
//		sort(tgtPts.begin(), tgtPts.end(), CmpUtil::comparePointX_A);
//		for(size_t w=0;w<tgtPts.size();w++) toInterpolate.push_back(tgtPts.at(w));
//	}
//
//	if(toInterpolate.size()>tgtWin)
//	{
//			//Contiene le y teoriche dei punti analizzati
//			vector<Point> teory;
//			if(tgtMethod) ImgUtil::lRegression(tgtWin, priority, toInterpolate, teory, tgtElim);
//			if(!tgtMethod) ImgUtil::lInterpolation(tgtWin, priority, toInterpolate, teory, tgtElim);
//
//	}
//		tgtPts.clear();
//		for(size_t i=0;i<toInterpolate.size();i++) tgtPts.push_back(toInterpolate.at(i));
//}
//
//void ImgUtil::lRegression(int tgtWin, vector< pair<int,Point> > &tgtPriority, vector<Point>  &tgtInterpol, vector<Point> &tgtTheory ,int tgtElim){
//	for(size_t i=0;i<(tgtInterpol.size()-(size_t) tgtWin);i++)
//		{
//			int j=i;
//			float b = 0;
//			int x_med = 0;
//			int y_med =0 ;
//			float a = 0 ;
//			int S1 = 0,S2 = 0,S3 =0 ,S4 = 0 ,S5 = 0;
//			vector< pair<int,Point> > pr;
//
//			for(int k=j; k < (j+tgtWin); k++ )
//				for(size_t w=0; w < tgtPriority.size();w++)
//					if(tgtInterpol.at(k).x == tgtPriority.at(w).second.x && tgtInterpol.at(k).y == tgtPriority.at(w).second.y )
//						pr.push_back(pair<int,Point> (tgtPriority.at(w).first,tgtInterpol.at(k)));
//
//			sort(pr.begin(),pr.end(), CmpUtil::comparePairInt_A);
//
//			for(int k=j; k < j + tgtWin; k++ )
//			{
//				S1 = S1 + (tgtInterpol[k].x * tgtInterpol[k].y);
//				S2 = S2 + (tgtInterpol[k].x);
//				S3 = S3 + (tgtInterpol[k].y);
//				S4 = S4 + (pow(tgtInterpol[k].x,2));
//				S5 = S5 + (tgtInterpol[k].x);
//				x_med = x_med + (tgtInterpol[k].x);
//				y_med = y_med + (tgtInterpol[k].y);
//			}
//			x_med=x_med/tgtWin;
//			y_med=y_med/tgtWin;
//
//			b = ((tgtWin*S1) - (S2 * S3) )/ ((tgtWin * S4) - pow(S5,2) );
//
//			a = y_med - (b*x_med);
//
//
//			for(int z=0; z < tgtWin; z++ )	tgtTheory.push_back (Point(tgtInterpol[j+z].x ,(a + (b * tgtInterpol[j+z].x))));
//
//			ImgUtil::interpolateDelete(tgtWin, tgtElim, j, pr, tgtInterpol, tgtTheory);
//		}
//}
//
//void ImgUtil::lInterpolation(int tgtWin, vector< pair<int,Point> > &tgtPriority, vector<Point>  &tgtInterpol, vector<Point> &tgtTheory, int tgtElim)
//{
//
//	for(size_t i=0;i<(tgtInterpol.size()-(size_t)tgtWin);i++)
//	{
//		int j=i;
//		vector< pair<int,Point> > pr;
//		for(int k=j; k < (j+tgtWin); k++ )
//			for(size_t w=0; w < tgtPriority.size();w++)
//				if(tgtInterpol.at(k).x == tgtPriority.at(w).second.x && tgtInterpol.at(k).y == tgtPriority.at(w).second.y )
//					pr.push_back(pair<int,Point> (tgtPriority.at(w).first,tgtInterpol.at(k)));
//
//		sort(pr.begin(),pr.end(), CmpUtil::comparePairInt_A);
//		//in pr punti ordinati secondo la priorità
//
//		tgtTheory.push_back (tgtInterpol[j]);
//
//		int k=j;
//
//		for(int z=0; z < (tgtWin-2); z++ )
//		{
//
//			Point p1 = tgtInterpol[k] ;
//			Point p2 = tgtInterpol[k+1] ;
//			Point p3 = tgtInterpol[k+2] ;
//
//			//y = y1 + (y1-y3)*((x-x1)/(x3-x1))
//			if( (p3.x - p1.x) != 0 )
//				tgtTheory.push_back(Point(tgtInterpol[k+1].x, (p1.y + ((p1.y -p3.y)*((p2.x - p1.x)/(p3.x - p1.x))))));
//			else
//				tgtTheory.push_back (Point(tgtInterpol[k+1].x,tgtInterpol[k+1].y));
//
//			k++;
//		}
//		tgtTheory.push_back (tgtInterpol[j+tgtWin]);
//
//		ImgUtil::interpolateDelete(tgtWin, tgtElim, j, pr, tgtInterpol, tgtTheory);
//	}
//}
//
//
//void ImgUtil::interpolateDelete(int tgtWin, int tgtElim, int tgtInit, vector< pair<int,Point> > &pr, vector<Point>  &tgtInterpol, vector<Point> &tgtTheory)
//{
//	vector<Point> moritvri;
//	int eliminationCnt=0;
//	switch(tgtElim)
//	{
//		case 1:
//			for(size_t z=0; z < tgtTheory.size();z++){
//
//				if((tgtInterpol[tgtInit+z].y > (tgtTheory[z].y+ 3)) || (tgtInterpol[tgtInit+z].y < (tgtTheory[z].y - 3))){
//					for(size_t k = 0;k<pr.size();k++){
//						if(pr.at(k).second.x == tgtTheory[z].x && pr.at(k).second.y == tgtTheory[z].y  )
//							pr.erase(pr.begin()+k);
//					}
//				}
//				else
//					moritvri.push_back(tgtInterpol[tgtInit+z]);
//			}
//
//			eliminationCnt=moritvri.size()-1;
//
//			for(int w=0; w < eliminationCnt; w++){
//
//				for(size_t i=0; i<tgtInterpol.size()-1 ;i++){
//					for(size_t j=0; j<moritvri.size() ;j++){
//
//						if(tgtInterpol.at(i).x == pr.at(0).second.x && tgtInterpol.at(i).y == pr.at(0).second.y && tgtInterpol.at(i).y == moritvri.at(j).y && tgtInterpol.at(i).x == moritvri.at(j).x ){
//							tgtInterpol.erase(tgtInterpol.begin()+i);
//						}
//					}
//				}
//			}
//		break;
//
//		case 2:
//
//			for(size_t z=0; z < tgtTheory.size(); z++){
//
//				if((tgtInterpol[tgtInit+z].y > (tgtTheory[z].y+ 3)) || (tgtInterpol[tgtInit +z].y < (tgtTheory[z].y - 3)))
//				{
//					for(size_t k = 0;k<pr.size();k++){
//						if(pr.at(k).second.x == tgtTheory[z].x && pr.at(k).second.y == tgtTheory[z].y  )
//							pr.erase(pr.begin()+k);
//					}
//				}
//				else
//					moritvri.push_back(tgtInterpol[tgtInit+z]);		//candidato all'eliminazione a partire da Peano_interpol[j]
//			}
//
//			for(size_t i=0; i<tgtInterpol.size();i++){
//
//				for(size_t j=0; j<moritvri.size();j++){
//
//					if(tgtInterpol.at(i).x == pr.at(0).second.x && tgtInterpol.at(i).y == pr.at(0).second.y && tgtInterpol.at(i).y == moritvri.at(j).y && tgtInterpol.at(i).x == moritvri.at(j).x ){
//						tgtInterpol.erase(tgtInterpol.begin()+i);
//						moritvri.erase(moritvri.begin()+j);
//						pr.erase(pr.begin());
//					}
//				}
//			}
//		break;
//
//		case 3:
//			int prestr1;
//			int prestr2;
//
//			for(size_t w=0 ; w< pr.size();w++){
//				if(tgtInterpol.at(tgtInit).x == pr.at(w).second.x && tgtInterpol.at(tgtInit).y == pr.at(w).second.y)
//					prestr1=pr.at(w).first;
//			}
//
//			for(size_t w=0 ; w< pr.size();w++){
//				if(tgtInterpol.at(tgtInit+tgtWin).x == pr.at(w).second.x && tgtInterpol.at(tgtInit+tgtWin).y == pr.at(w).second.y)
//					prestr2=pr.at(w).first;
//			}
//
//			for(int i=tgtInit+1; i<((tgtInit+tgtWin)-1);i++){
//
//				for(size_t j=0; j<moritvri.size();j++){
//
//					for(size_t w=0; w<pr.size();w++){
//
//						if(tgtInterpol.at(i).y == moritvri.at(j).y && tgtInterpol.at(i).x == moritvri.at(j).x && moritvri.at(j).x ==pr.at(w).second.x && moritvri.at(j).y ==pr.at(w).second.y){
//
//							if(pr.at(w).first < prestr1 || pr.at(w).first < prestr2)
//								tgtInterpol.erase(tgtInterpol.begin()+i);
//
//							moritvri.erase(moritvri.begin()+j);
//							pr.erase(pr.begin()+w);
//
//						}
//					}
//				}
//			}
//	break;
//
//	default:
//	break;
//	}
//
//	moritvri.clear();
//	tgtTheory.clear();
//	pr.clear();
//}
//
///********************************************//**
// *  METHODS - Image manipulation
// ***********************************************/
//
///**
// *	Thins the given image. Different pre-post processing options are available.
// */
//Mat ImgUtil::thinImage(Mat tgtImage, bool tgtBoundarySmoothing, bool tgtAcuteEmphasys, bool tgtDestair) {
//
//	Mat myImage = tgtImage.clone();
//	Rect ROI(10,10, myImage.cols, myImage.rows);
//	copyMakeBorder(myImage, myImage, 10, 10, 10, 10, BORDER_CONSTANT, Scalar(256, 256, 256));
//
//	thin(myImage, true, true, true);
//	myImage= myImage(ROI);
//
//	return myImage;
//}
//
///**
// *	Applies a vertical auto-crop to the given image.
// */
//Mat ImgUtil::verticalAutoCrop(Mat tgtImage, int tgtTreshold) {
//
//	Mat workImage = tgtImage.clone();
//	bool pixFound = false;
//	int upperBound = 0;
//	int lowerBound = workImage.cols;
//
//	for (int cropRow = 0; cropRow < workImage.rows; ++cropRow) {
//
//		for (int cropCol = 0; cropCol < workImage.cols; ++cropCol) {
//
//			Point curPoint = Point(cropCol, cropRow);
//
//			if (workImage.at<uchar>(curPoint) < tgtTreshold) {
//				upperBound = cropRow;
//				pixFound = true;
//				break;
//			}
//		}
//
//		if (pixFound == true)		break;
//	}
//
//	pixFound = false;
//
//	for (int cropRow = workImage.rows - 1; cropRow > 0; --cropRow) {
//		for (int cropCol = workImage.cols - 1; cropCol > 0; --cropCol) {
//
//			Point curPoint = Point(cropCol, cropRow);
//
//			if (workImage.at<uchar>(curPoint) < tgtTreshold) {
//
//				lowerBound = cropRow;
//				pixFound = true;
//				break;
//			}
//		}
//
//		if (pixFound == true)		break;
//	}
//
//	Rect tempROI(0, upperBound, workImage.cols, lowerBound - upperBound);
//	workImage = workImage(tempROI);
//
//	return workImage;
//}
//
///**
// * Rotates a given image by a given number of degrees.
// */
//Mat ImgUtil::rotateImage(Mat tgtImage, double angle) {
//
//	Mat myImage = tgtImage.clone();
//
//	int myMargin = floor(myImage.rows / 2) + 100;
//	if (floor(myImage.cols / 2) + 100 > myMargin)
//		myMargin = floor(myImage.cols / 2) + 100;
//
//	copyMakeBorder(myImage, myImage, myMargin, myMargin, myMargin, myMargin, BORDER_CONSTANT, Scalar(256, 256, 256));
//
//	Point2f center(myImage.cols / 2.0F, myImage.rows / 2.0F);
//	Mat rotationMatrix = getRotationMatrix2D(center, angle, 1.0);
//	Mat rotatedMat;
//	warpAffine(myImage, rotatedMat, rotationMatrix, myImage.size());
//
//	int vMarginNew = floor(rotatedMat.rows / 2) - 100;
//	int hMarginNew = floor(rotatedMat.cols / 2) - 100;
//
//	Rect tempROI(center.x - hMarginNew, center.y - vMarginNew, hMarginNew * 2, vMarginNew * 2);
//	Mat tempImage = rotatedMat(tempROI);
//
//	return tempImage;
//}
//
//
//vector<Point> ImgUtil::rotateImagePoints(vector<Point> tgtPoints, Mat tgtRotatedImage, Mat tgtOrigImage, int tgtAngle) {
//
//	Point2f center(tgtRotatedImage.cols / 2.0F, tgtRotatedImage.rows / 2.0F);
//
//	int myMargin = max(floor(tgtRotatedImage.rows / 4), floor(tgtRotatedImage.cols / 4));
//
//	for (size_t i = 0; i < tgtPoints.size(); i++) {
//		tgtPoints[i] = GeomUtil::rotatePoint(tgtPoints[i], center, tgtAngle);
//		tgtPoints[i].x -= myMargin;
//		if (tgtPoints[i].x < 0)
//			tgtPoints[i].x = 0;
//		if (tgtPoints[i].x >= tgtOrigImage.cols)
//			tgtPoints[i].x = tgtOrigImage.cols - 1;
//
//		tgtPoints[i].y -= myMargin;
//		if (tgtPoints[i].y < 0)
//			tgtPoints[i].y = 0;
//		if (tgtPoints[i].y >= tgtOrigImage.rows)
//			tgtPoints[i].y = tgtOrigImage.rows - 1;
//	}
//
//	return tgtPoints;
//}
//
///**
// * Inverts the given grayscale image.
// */
//Mat ImgUtil::invertImage(Mat tgtImage){
//
//	Mat workImage = tgtImage.clone();
//	bitwise_not(workImage, workImage);
//	return workImage;
//}
//
///**
// * Fills the background of the given image with black.
// * Used within the 'findHoles' function.
// */
//Mat ImgUtil::fillBackground(Mat tgtImage) {
//
//	for (int x = 0; x < tgtImage.cols; x++) {
//
//		if (tgtImage.at<uchar>(0, x) == 255)
//			floodFill(tgtImage, Point(x, 0), 0, 0, 8);
//
//		if (tgtImage.at<uchar>(tgtImage.rows - 1, x) == 255)
//			floodFill(tgtImage, Point(x, tgtImage.rows - 1), 0, 0, 8);
//	}
//
//	for (int y = 0; y < tgtImage.rows; y++) {
//
//		if (tgtImage.at<uchar>(y, 0) == 255)
//			floodFill(tgtImage, Point(0, y), 0, 0, 8);
//
//		if (tgtImage.at<uchar>(y, tgtImage.cols - 1) == 255)
//			floodFill(tgtImage, Point(tgtImage.cols - 1, y), 0, 0, 8);
//	}
//
//	return tgtImage;
//}

}
