package sample.analytics;

import java.util.Arrays;

public class AnalyticsUtils {

    public static double calculateAverageScore(int[] sourceArray) {
        int sum = 0;
        for(int i = 0; i < sourceArray.length; ++i) {
            sum += sourceArray[i];
        }
        return sum / sourceArray.length;

    }

    public static double calculateMedian(int[] sourceArray) {
        Arrays.sort(sourceArray);
        double median;
        if(sourceArray.length % 2 == 0) {
            median = ((double) sourceArray[sourceArray.length / 2] + (double)sourceArray[sourceArray.length / 2 -1]) / 2;
        }
        else {
            median = (double) sourceArray[sourceArray.length / 2];
        }
        return median;
    }
}
