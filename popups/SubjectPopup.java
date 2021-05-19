package sample.popups;

import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import sample.Main;
import sample.analytics.AnalyticsUtils;
import sample.excel.ClassGradesImporter;
import sample.objects.ObjectUtils;
import sample.objects.Subject;
import sample.screens.ImportExportScreenController;

import java.util.ArrayList;

public class SubjectPopup {

    public static Popup customPopup;

    public boolean isGradesDuplicate;

    public SubjectPopup() {
        customPopup = new Popup();
    }

    public void generateLayout(String subjectName) {

        VBox layoutVBox = new VBox(5);
        ToggleGroup toggleGroup = new ToggleGroup();
        Button applyButton = new Button("Окей");
        applyButton.setStyle("-fx-background-color: #80DC94; -fx-font-size: 20px; -fx-font-weight: bold;");
        applyButton.setMinWidth(280);

        Label classModaLabel = new Label();
        Label classMedianLabel = new Label();
        Label classAverageScoreLabel = new Label();
        Label classDispersionLabel = new Label();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        Subject currentSubject = ObjectUtils.findSubjectByName(Main.schoolSubjects, subjectName);
        BarChart<String, Number> subjectsAverageBarChart = new BarChart<String,Number>(xAxis,yAxis);
        subjectsAverageBarChart.getData().add(DiagramGenerator.getClassesSubjectAverageDistribution(Main.pupils, currentSubject));

        ArrayList<Integer> subjectGrades = ObjectUtils.getGeneralPupilsSubjectGrades(currentSubject);
        int classModa = AnalyticsUtils.calculateModa(subjectGrades);
        int classMedian = AnalyticsUtils.calculateModa(subjectGrades);
        double classAverageScore = AnalyticsUtils.calculateAverageScore(subjectGrades);
        double classDispersion = AnalyticsUtils.calculateDispersion(subjectGrades, classAverageScore);

        classModaLabel.setText(String.valueOf(classModa));
        classMedianLabel.setText(String.valueOf(classMedian));
        classAverageScoreLabel.setText(String.valueOf(classAverageScore));
        classDispersionLabel.setText(String.valueOf(classDispersion));


        applyButton.setOnAction(actionEvent -> {
            customPopup.hide();
        });


        layoutVBox.setPadding(new Insets(10,10,0,10));

        layoutVBox.getChildren().add(subjectsAverageBarChart);
        layoutVBox.getChildren().add(applyButton);
        layoutVBox.setStyle("-fx-background-color: white;  -fx-border-width: 1; -fx-border-radius: 2; -fx-border-color: black;");
        layoutVBox.setMinHeight(350);
        layoutVBox.setMinWidth(300);

        customPopup.getContent().add(layoutVBox);
        customPopup.setHeight(900);
        customPopup.setWidth(1600);
        customPopup.setX(400);
        customPopup.setY(200);
    }

}
