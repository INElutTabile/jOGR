/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import org.bytedeco.javacpp.opencv_core.Point;

/**
 *
 * @author fabrizioborgia
 */
public class MathUtil {
//
//
///********************************************//**
// *  METHODS - Numeric
// ***********************************************/
//
///**
// * Calculates tgtNumber % tgtModule. Negative tgtNumber handling.
// */
//int MathUtil::modulo(int tgtNumber, int tgtModule) {
//
//	return tgtNumber >= 0 ? tgtNumber % tgtModule : (tgtModule - abs(tgtNumber % tgtModule)) % tgtModule;
//}
//
///**
// * Calculates the minimum value between two points.
// */
//int MathUtil::minimum(int tgtNumberOne, int tgtNumberTwo) {
//
//	return min(tgtNumberOne, tgtNumberTwo);
//}
//
///**
// * Calculates the maximum value between two points.
// */
//int MathUtil::maximum(int tgtNumberOne, int tgtNumberTwo) {
//
//	return max(tgtNumberOne, tgtNumberTwo);
//}
//
///**
// * Calculates the average value within the given set of integers.
// */
//float MathUtil::average(vector<int> tgtValues) {
//
//	float valueSum = accumulate(tgtValues.begin(), tgtValues.end(), 0);
//	float valueCount = tgtValues.size();
//	return valueSum / valueCount;
//}
//
///**
// * Calculates the average value within the given set of integers. The result is an approximated integer value.
// */
//int MathUtil::averageApprox(vector<int> tgtValues) {
//
//	float valueSum = accumulate(tgtValues.begin(), tgtValues.end(), 0);
//	float valueCount = tgtValues.size();
//	return round(valueSum / valueCount);
//}
//
///**
// * Calculates the standard deviation value within the given set of integers.
// */
//float MathUtil::stddeviation(vector<int> tgtValues) {
//
//	int mean = average(tgtValues);
//	int sq_sum = inner_product(tgtValues.begin(), tgtValues.end(), tgtValues.begin(), 0);
//
//	return sqrt(sq_sum / tgtValues.size() - mean * mean);
//}
//
//unsigned int MathUtil::zOrder(uint16_t xPos, uint16_t yPos){
//
//    static const uint32_t MASKS[] = {0x55555555, 0x33333333, 0x0F0F0F0F, 0x00FF00FF};
//    static const uint32_t SHIFTS[] = {1, 2, 4, 8};
//
//    uint32_t x = xPos;  // Interleave lower 16 bits of x and y, so the bits of x
//    uint32_t y = yPos;  // are in the even positions and bits from y in the odd;
//
//    x = (x | (x << SHIFTS[3])) & MASKS[3];
//    x = (x | (x << SHIFTS[2])) & MASKS[2];
//    x = (x | (x << SHIFTS[1])) & MASKS[1];
//    x = (x | (x << SHIFTS[0])) & MASKS[0];
//
//    y = (y | (y << SHIFTS[3])) & MASKS[3];
//    y = (y | (y << SHIFTS[2])) & MASKS[2];
//    y = (y | (y << SHIFTS[1])) & MASKS[1];
//    y = (y | (y << SHIFTS[0])) & MASKS[0];
//
//    const uint32_t result = x | (y << 1);
//    return result;
//}

    // --------------------------------------------------------------------- |
    //  METHODS - "Rangers"
    // --------------------------------------------------------------------- |
    
    /**
     * Ensures that the value of {@code tgtNumber} is between {@code tgtMin} and {@code tgtMax}, 
     * altering the value of {@code tgtNumber} if necessary.
     *
     * @param tgtNumber
     * @param tgtMin
     * @param tgtMax
     * @return 
     */
    public static int findBetween(int tgtNumber, int tgtMin, int tgtMax) {

        tgtNumber = Math.max(tgtNumber, tgtMin);
        return Math.min(tgtNumber, tgtMax);
    }

    /**
     * Returns true if the value of {@code tgtPoint} is between {@code tgtMin} and {@code tgtMax}.
     *
     * @param tgtPoint
     * @param tgtMin
     * @param tgtMax
     *@return 
     */
    public static boolean isBetween(Point tgtPoint, Point tgtMin, Point tgtMax) {

        return (tgtPoint.x() >= tgtMin.x() && tgtPoint.x() <= tgtMax.x())
                && (tgtPoint.y() >= tgtMin.y() && tgtPoint.y() <= tgtMax.y());
    }

///********************************************//**
// *  METHODS - Vector analysis
// ***********************************************/
//
///**
// * Get vector distinct values.
// */
//vector<int> MathUtil::distinctVector(vector<int> tgtValues) {
//
//	vector<int> resultValues;
//
//	for (size_t i = 0; i < tgtValues.size(); i++) {
//		if (Utility::containsInt(resultValues, tgtValues[i]) == false) {
//			resultValues.push_back(tgtValues[i]);
//		}
//	}
//
//	return resultValues;
//}
//
///**
// * Get vector distinct values. Values with insufficient occurrence percentage are discarded.
// */
//vector<int> MathUtil::distinctVector(vector<int> tgtValues, float thresoldLength) {
//
//	vector<int> distinctValues;
//	map<int, float> countValues;
//
//	for (size_t i = 0; i < tgtValues.size(); ++i) {
//		map<int, float>::iterator it(countValues.find(tgtValues[i]));
//		if (it != countValues.end()) {
//			it->second++;
//		} else {
//			countValues[tgtValues[i]] = 1;
//		}
//	}
//
//	for (map<int, float>::iterator it = countValues.begin(); it != countValues.end(); it++) {
//		if (it->second > thresoldLength * tgtValues.size()) {
//			distinctValues.push_back(it->first);
//		}
//	}
//
//	return distinctValues;
//}
//
///**
// * Get vector distinct values (and their occurrence percentage). Values with insufficient occurrence
// * percentage are discarded.
// */
//map<int, float> MathUtil::distinctVectorCount(vector<int> tgtValues, float thresoldLength) {
//
//	map<int, float> resultValues;
//	map<int, float> countValues;
//
//	for (size_t i = 0; i < tgtValues.size(); ++i) {
//		map<int, float>::iterator it(countValues.find(tgtValues[i]));
//		if (it != countValues.end()) {
//			it->second++;
//		} else {
//			countValues[tgtValues[i]] = 1;
//		}
//	}
//
//	for (map<int, float>::iterator it = countValues.begin(); it != countValues.end(); it++) {
//		it->second /= tgtValues.size();
//		if (it->second > thresoldLength) {
//			resultValues.insert(make_pair(it->first, it->second));
//		}
//	}
//
//	return resultValues;
//}
//
///**
// * Calculates the dominant value (and its occurrence percentage) within the given set of integers.
// */
//pair<int, float> MathUtil::findDominantValue(vector<int> tgtValues) {
//
//	map<int, float> valueChart;
//	pair<int, float> finalPair(-1, -1.0);
//	for (size_t i = 0; i < 8; i++) {
//		valueChart.insert(make_pair<int, int>(i, 0));
//	}
//
//	for (size_t i = 0; i < tgtValues.size(); i++) {
//		valueChart.at(tgtValues[i]) = valueChart.at(tgtValues[i]) + 1;
//	}
//
//	for (map<int, float>::iterator it = valueChart.begin(); it != valueChart.end(); it++) {
//		if (it->second > finalPair.second) {
//			finalPair.first = it->first;
//			finalPair.second = it->second;
//		}
//	}
//
//	finalPair.second = finalPair.second / tgtValues.size();
//	return finalPair;
//}
//
///**
// * Calculates the trend within the given set of integers, accumulating the differences of consecutive values.
// * The trend is negative if the trend is descending, positive if it is ascending.
// * A zero value indicates a perfect balance between ascending and descending (uncommon).
// */
//int MathUtil::findTrend(vector<int> tgtPoints) {
//
//	int trend = 0;
//
//	for (size_t i = 0; i < tgtPoints.size() - 1; ++i) {
//		trend += tgtPoints[i + 1] - tgtPoints[i];
//	}
//
//	return trend;
//}
//
///**
// *  Searches the input set of values for sequences of consecutive values which meet the given requirement. The requirement is
// *  defined by an operator ('tgtOperator', which can be "Lesser", "Greater", etc.) and a value ('tgtCheckValue'). Any sequence
// *  whose length is lesser than 'tgtMinLength' is discarded.
// *  The vector is treated as "circular" vector, this means that if both first and last value of the the set meet the requirement,
// *  their sequences are merged.
// *
// *  @param	tgtValue		The set of values to be analyzed.
// *  @param	tgtOperator		The operator used to check any value in the set.
// *  @param	tgtCheckValue	The value used to check any value in the set.
// *  @param	tgtMinLength	Minimum sequence length.
// *
// *  @return A map which stores, for each sequence, its starting index and its values.
// *
// */
//map<int, vector<int> > MathUtil::findVectorSequences(vector<int> tgtValues, int tgtOperator, int tgtCheckValue, int tgtMinLength) {
//
//	/** Scanning for suitable sequences.										*/
//	map<int, int> positiveSeqs;
//	int seqStart = -1;
//
//	for (size_t i = 0; i < tgtValues.size(); i++) {
//
//		if (CmpUtil::compareInt(tgtValues[i], tgtCheckValue, tgtOperator)) {
//			// First positive sector of a potential sequence.
//			if (seqStart == -1)
//				seqStart = i;
//		} else {
//			// First negative sector after a positive streak.
//			if (seqStart != -1) {
//				positiveSeqs.insert(make_pair(seqStart, i));
//				seqStart = -1;
//			}
//		}
//	}
//	// Last check.
//	if (seqStart != -1) {
//		positiveSeqs.insert(make_pair(seqStart, tgtValues.size() - 1));
//		seqStart = -1;
//	}
//
//	/** Extracting sector values from sequence data 							*/
//	return extractVectorSequences(tgtValues, positiveSeqs, tgtMinLength);
//}
//
///**
// * 	Extracts, from the input set of values, the sequences whose start and end index is identified by each entry of 'tgtChosenSeqs'.
// * 	Any sequence whose length is lesser than 'tgtMinLength' is discarded.
// *	The vector is treated as "circular" vector, this means that if both first and last value of the the set are in a sequence,
// *  their sequences are merged.
// */
//map<int, vector<int> > MathUtil::extractVectorSequences(vector<int> tgtSectors, map<int, int> tgtChosenSeqs, int tresholdLength) {
//
//	map<int, vector<int> > resultSeqs;
//	vector<int> seqInserter;
//
//	if (tgtChosenSeqs.empty()) {
//		return resultSeqs;
//	}
//
//	/** Handling first-last sequence separation.								*/
//	if (tgtChosenSeqs.find(0) != tgtChosenSeqs.end() && tgtChosenSeqs.rbegin()->second == (int)tgtSectors.size() - 1) {
//
//		seqInserter.insert(seqInserter.end(), tgtSectors.begin() + tgtChosenSeqs.rbegin()->first,
//				tgtSectors.begin() + tgtChosenSeqs.rbegin()->second + 1);
//		seqInserter.insert(seqInserter.end(), tgtSectors.begin(), tgtSectors.begin() + tgtChosenSeqs.at(0) + 1);
//
//		if ( (int)seqInserter.size() >= tresholdLength)
//			resultSeqs.insert(make_pair(tgtChosenSeqs.rbegin()->first, seqInserter));
//
//		tgtChosenSeqs.erase(0);
//		tgtChosenSeqs.erase(tgtChosenSeqs.rbegin()->first);
//	}
//
//	for (map<int, int>::iterator it = tgtChosenSeqs.begin(); it != tgtChosenSeqs.end(); it++) {
//		seqInserter.erase(seqInserter.begin(), seqInserter.end());
//
//		if (it->second - it->first >= tresholdLength) {
//			seqInserter.insert(seqInserter.end(), tgtSectors.begin() + it->first, tgtSectors.begin() + it->second);
//			resultSeqs.insert(make_pair(it->first, seqInserter));
//		}
//	}
//
//	return resultSeqs;
//}
//
///**
// * Smoothes the vector.
// */
//vector<int> MathUtil::smoothVector_Int(vector<int> tgtValues) {
//
//	vector<int> resultValues;
//
//	for (size_t i = 0; i < tgtValues.size(); i++) {
//
//		vector<int> myChunk;
//
//		if (i == tgtValues.size() - 2) {
//			myChunk = vector<int>(tgtValues.begin() + tgtValues.size() - 4, tgtValues.end() - 1);
//		} else if (i == tgtValues.size() - 1) {
//			myChunk = vector<int>(tgtValues.begin() + tgtValues.size() - 3, tgtValues.end());
//		} else {
//			myChunk = vector<int>(tgtValues.begin() + i, tgtValues.begin() + (i + 3));
//		}
//		resultValues.push_back(findDominantValue(myChunk).first);
//	}
//
//	return resultValues;
//}
//
//
///**
// * Smoothes the vector.
// */
//vector<Point> MathUtil::smoothVector_Point(vector<Point> tgtValues){
//
//	vector<Point> resultValues;
//
//	resultValues.push_back(tgtValues.at(0));
//	resultValues.push_back(Point( tgtValues.at(1).x, ((tgtValues.at(0).y  + tgtValues.at(1).y + tgtValues.at(2).y ) / 3 ) ));
//
//	for(size_t i = 2; i< tgtValues.size()-2; i++){
//
//		int  y_med = tgtValues.at(i-2).y + tgtValues.at(i-1).y + tgtValues.at(i).y + tgtValues.at(i+1).y + tgtValues.at(i+2).y;
//		y_med= y_med /5;
//		resultValues.push_back(Point(tgtValues.at(i).x,y_med));
//	}
//	resultValues.push_back(Point(tgtValues.at(tgtValues.size()-2).x, ((tgtValues.at(tgtValues.size()-3).y  + tgtValues.at(tgtValues.size()-2).y + tgtValues.at(tgtValues.size()-1).y ) / 3 ) ));
//	resultValues.push_back(tgtValues.at(tgtValues.size()-1));
//
//	return resultValues;
//}
//
//    
}
