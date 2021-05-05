package sample.popups;

import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Font;
import sample.Main;
import sample.objects.Grade;
import sample.objects.ObjectUtils;
import sample.objects.Pupil;
import sample.objects.Subject;

import java.awt.*;
import java.util.ArrayList;

public class DiagramGenerator {

    public static LineChart<String, Number> getPupilAcademicPerformanceDistribution(Pupil pupil, Subject subject) {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.tickLabelFontProperty().set(Font.font(14));
        yAxis.tickLabelFontProperty().set(Font.font(14));

        xAxis.setLabel("Дата");
        yAxis.setLabel("Отметка");
        xAxis.setSide(Side.BOTTOM);
        yAxis.setSide(Side.LEFT);

        LineChart<String, Number> testLineChart = new LineChart<String, Number>(xAxis, yAxis);
        testLineChart.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        testLineChart.setPrefHeight(500);
        testLineChart.setPrefWidth(1000);
        testLineChart.setTitle(subject.getSubjectName());
        ArrayList<Grade> currentPupilGrades = pupil.getPupilGrades();
        ArrayList<Grade> currentSubjectGrades = ObjectUtils.getSubjectGrades(currentPupilGrades, subject.getSubjectName());

        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName(subject.getSubjectName());

        for(Grade oneGrade : currentSubjectGrades) {
            String dateTime = oneGrade.getDateTime();
            int gradeValue = oneGrade.getValue();
            tempSeries.getData().add(new XYChart.Data<>(dateTime, gradeValue));
        }
        testLineChart.getData().add(tempSeries);
        return testLineChart;
    }
}
