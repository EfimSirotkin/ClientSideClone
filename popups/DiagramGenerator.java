package sample.popups;

import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Font;
import sample.Main;
import sample.objects.*;

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

        for (Grade oneGrade : currentSubjectGrades) {
            String dateTime = oneGrade.getDateTime();
            int gradeValue = oneGrade.getValue();
            tempSeries.getData().add(new XYChart.Data<>(dateTime, gradeValue));
        }
        testLineChart.getData().add(tempSeries);
        return testLineChart;
    }

    public static XYChart.Series<String, Number> getClassesAverageDistribution(ArrayList<Pupil> pupils) {


        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("Общая средняя успеваемость");

        ArrayList<Double> averageClassScore = new ArrayList<>(5);

        for (SchoolClass oneClass : Main.schoolClassArrayList) {
            int pupilsClassId = oneClass.getId();
            ArrayList<Pupil> classPupils = ObjectUtils.findPupilsByClass(pupils, pupilsClassId);
            double averageForCurrentClass = ObjectUtils.calculateAverageForClass(classPupils);
            averageForCurrentClass = 5.56;
            averageClassScore.add(averageForCurrentClass);
            tempSeries.getData().add(new XYChart.Data<>(oneClass.getNumber() + " " + oneClass.getLetter(), averageForCurrentClass));
        }
        return tempSeries;
    }

    public static XYChart.Series<String, Number> getClassesSubjectAverageDistribution(ArrayList<Pupil> pupils, Subject subject) {
        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("Общая средняя успеваемость по предмету: " + subject.getSubjectName());

        for(SchoolClass oneClass : Main.schoolClassArrayList) {
            int pupilClassID = oneClass.getId();
            ArrayList<Pupil> classPupils = ObjectUtils.findPupilsByClass(pupils, pupilClassID);
            double averageForCurrentSubject = ObjectUtils.calculateAverageForClassSubject(classPupils, subject);
            System.out.println("av" + averageForCurrentSubject);
            tempSeries.getData().add(new XYChart.Data<>(oneClass.getNumber() + " " + oneClass.getLetter(), averageForCurrentSubject));
        }
        return tempSeries;
    }
}


