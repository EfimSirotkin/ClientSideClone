package sample.popups;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import sample.Main;
import sample.excel.ClassGradesImporter;
import sample.screens.ImportExportScreenController;

import javax.net.ssl.HostnameVerifier;
import java.util.ArrayList;

public class GradePopup {

    public static Popup customPopup;
    private RadioButton[] radioButtons;
    private RadioButton selection;

    public boolean isGradesDuplicate;

    public GradePopup() {
        customPopup = new Popup();
        radioButtons = new RadioButton[11];
        selection = new RadioButton();
    }

    public void generateLayout() {

        VBox radioButtonsVBox = new VBox(5);
        ToggleGroup toggleGroup = new ToggleGroup();
        Button applyButton = new Button("Окей");
        Button cancelButton = new Button("Выход");
        applyButton.setStyle("-fx-background-color: #80DC94; -fx-font-size: 20px; -fx-font-weight: bold;");
        cancelButton.setStyle("-fx-background-color: #e3756d; -fx-font-size: 20px; -fx-font-weight: bold;");
        applyButton.setMinWidth(130);
        cancelButton.setMinWidth(130);

        Label instructionsLabel = new Label("Выберите номер класса:");
        instructionsLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        for (int i = 0; i < radioButtons.length; ++i) {
            radioButtons[i] = new RadioButton(String.valueOf(i + 1));
            radioButtons[i].setToggleGroup(toggleGroup);
            radioButtons[i].setOnAction(actionEvent ->
                    selection = (RadioButton) toggleGroup.getSelectedToggle());
        }
        applyButton.setOnAction(actionEvent -> {
            customPopup.hide();
            ImportExportScreenController.classNumber = getSelectionText();
            ClassGradesImporter classGradesImporter = new ClassGradesImporter();
            boolean error = ImportExportScreenController.gradesFileAbsolutePath.isEmpty() ||
                    ImportExportScreenController.scheduleFileAbsolutePath.isEmpty() ||
                    ImportExportScreenController.classNumber.isEmpty();
            if (!error) {
                isGradesDuplicate = classGradesImporter.importTemplate(ImportExportScreenController.gradesFileAbsolutePath,
                        ImportExportScreenController.scheduleFileAbsolutePath,
                        Integer.parseInt(ImportExportScreenController.classNumber));
            }

            if (isGradesDuplicate) {
                AlertWarner.showAlert("Дубликаты", "В выбранном файле содержатся дубликаты отметок по предметам", "Соответствующие записи не добавлены", Alert.AlertType.WARNING);
            }
            else if(error) {
                AlertWarner.showAlert("Ошибка", "Неверно или не полностью указана информация", "Операция прервана пользователем", Alert.AlertType.ERROR);
            }
            else {
                AlertWarner.showAlert("Успешно", "Операция выполнена", "Информация об обучающихся успешно добавлена", Alert.AlertType.INFORMATION);
            }

        });

        cancelButton.setOnAction(actionEvent -> customPopup.hide());

        radioButtonsVBox.setPadding(new Insets(10,10,0,10));

        HBox instructionButtonsHbox = new HBox(10);
        instructionButtonsHbox.getChildren().addAll(applyButton,cancelButton);

        radioButtonsVBox.getChildren().add(instructionsLabel);
        radioButtonsVBox.getChildren().addAll(radioButtons);
        radioButtonsVBox.getChildren().add(instructionButtonsHbox);
        radioButtonsVBox.setStyle("-fx-background-color: white;  -fx-border-width: 1; -fx-border-radius: 2; -fx-border-color: black;");
        radioButtonsVBox.setMinHeight(350);
        radioButtonsVBox.setMinWidth(300);

        customPopup.getContent().add(radioButtonsVBox);
        customPopup.setHeight(900);
        customPopup.setWidth(1600);
        customPopup.setX(400);
        customPopup.setY(200);
    }

    public String getSelectionText() {
        return selection.getText();
    }


}
