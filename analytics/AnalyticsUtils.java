package sample.analytics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class AnalyticsUtils {

    public static double calculateAverageScore(ArrayList<Integer> sourceArray) {
        int sum = 0;
        for (int i = 0; i < sourceArray.size(); ++i) {
            sum += sourceArray.get(i);
        }
        return (double) sum / sourceArray.size();

    }

    public static double calculateMedian(ArrayList<Integer> sourceArray) {
        Collections.sort(sourceArray);
        double median;
        if (sourceArray.size() % 2 == 0) {
            median = ((double) sourceArray.get(sourceArray.size() / 2) + (double) sourceArray.get(sourceArray.size() / 2 - 1)) / 2;
        } else {
            median = (double) sourceArray.get(sourceArray.size() / 2);
        }
        return median;
    }

    public static int calculateModa(ArrayList<Integer> sourceArray) {
        int maxFreq = 0;
        int moda = 0;
        int currentFreq = 0;
        int fixedNumber = 0;
        for (int i = 0; i < sourceArray.size(); ++i) {
            fixedNumber = sourceArray.get(i);
            currentFreq = 1;
            for (int j = 0; j < sourceArray.size(); ++j)
                if (isDoubleEqual(fixedNumber, sourceArray.get(j))) {
                    currentFreq++;
                }
            if (currentFreq >= maxFreq) {
                moda = fixedNumber;
                maxFreq = currentFreq;
            }
        }
        return moda;
    }

    private static boolean isDoubleEqual(double firstNumber, double secondNumber) {
        double delta = secondNumber - firstNumber;
        double maxDelta = 0.001;
        if (Math.abs(delta) < maxDelta)
            return true;
        else
            return false;
    }

    public static double calculateDispersion(ArrayList<Integer> array, double average) {
        double dispersion = 0.0;
        double n = array.size();
        for (int i = 0; i < n; ++i) {
            dispersion += Math.pow((array.get(i) - average), 2);
            dispersion /= n;
        }
        return dispersion;
    }


    
}
