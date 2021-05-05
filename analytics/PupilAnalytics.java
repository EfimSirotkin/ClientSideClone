package sample.analytics;

import sample.objects.Pupil;

public class PupilAnalytics {
    private Pupil pupil;
    private double averageScore;
    private double median;

    public PupilAnalytics(Pupil pupil, double averageScore, double median) {
        this.pupil = pupil;
        this.averageScore = averageScore;
        this.median = median;
    }

    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }
}
