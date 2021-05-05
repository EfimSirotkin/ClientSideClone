package sample.popups;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import jxl.format.Border;
import sample.Main;
import sample.objects.Pupil;
import sample.objects.Subject;

import java.util.ArrayList;

public class PupilPopup {
    public static Popup customPopup;
    ArrayList<LineChart<String,Number>> pupilDiagrams;

    public PupilPopup(Popup customPopup) {
        this.customPopup = customPopup;
    }
    public PupilPopup() {
        pupilDiagrams = new ArrayList<LineChart<String, Number>>(5);
        customPopup = new Popup();
    }

    public void generatePopup(Pupil pupil) {

        for(Subject oneSubject : Main.schoolSubjects) {

            pupilDiagrams.add(DiagramGenerator.getPupilAcademicPerformanceDistribution(pupil,oneSubject));
    }
        generateLayout(pupil.getFullName());
    }

    private void generateLayout(String pupilName) {


        HBox horizontalBox = new HBox();

        RadioButton[] radioButtons = new RadioButton[Main.schoolSubjects.size()];
        ToggleGroup toggleGroup = new ToggleGroup();
        VBox pupilDiagramVBox = new VBox(10);
        VBox radioButtonsVBox = new VBox(10);
        Button hideButton = new Button("Выйти");
        Label pupilNameLabel = new Label();
        pupilNameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        pupilNameLabel.setText(pupilName);
        hideButton.setPrefWidth(250);
        hideButton.setPrefHeight(50);
        hideButton.setStyle("-fx-background-color: #80DC94; -fx-font-size: 20px; -fx-font-weight: bold;");
        hideButton.setOnAction(actionEvent -> customPopup.hide());


        for(int i = 0; i < radioButtons.length; ++i) {
            radioButtons[i] = new RadioButton(Main.schoolSubjects.get(i).getSubjectName());
            radioButtons[i].setToggleGroup(toggleGroup);
            radioButtons[i].setOnAction(actionEvent ->
                    {
                        RadioButton selection = (RadioButton) toggleGroup.getSelectedToggle();
                        pupilDiagramVBox.getChildren().clear();
                        pupilDiagramVBox.getChildren().add(findDiagramByTitle(selection.getText()));
                    }
            );

        }
        radioButtonsVBox.getChildren().add(pupilNameLabel);
        radioButtonsVBox.getChildren().addAll(radioButtons);
        radioButtonsVBox.getChildren().add(hideButton);


        horizontalBox.setStyle("-fx-background-color: #FFE9A3; -fx-border-width: 1; -fx-border-radius: 2; -fx-border-color: black;");

        horizontalBox.setSpacing(10);
        horizontalBox.setPadding(new Insets(5,5,5,5));


        horizontalBox.getChildren().addAll(radioButtonsVBox, pupilDiagramVBox);

        this.customPopup.getContent().add(horizontalBox);
        this.customPopup.setHeight(900);
        this.customPopup.setWidth(1600);
        this.customPopup.setX(100);
        this.customPopup.setY(100);
    }



    public Popup getCustomPopup() {
        return this.customPopup;
    }

    public void setCustomPopup(Popup popup) {
        this.customPopup = popup;
    }

    private LineChart<String, Number> findDiagramByTitle(String title) {
        for(LineChart<String, Number> lineChart : this.pupilDiagrams) {
            String currentDiagramTitle = lineChart.getTitle().toLowerCase();
            if (currentDiagramTitle.equals(title.toLowerCase()))
                return lineChart;
        }

            return null;
    }
}
