package sample.popups;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import jxl.write.WriteException;
import sample.Main;
import sample.excel.ClassGradesExporter;
import sample.excel.ClassPupilsExporter;

import java.io.IOException;

public class ClassPupilsPopup {

    public static Popup customPopup;
    private RadioButton[] radioButtons;
    private RadioButton selection;

    public boolean isGradesDuplicate;

    public ClassPupilsPopup() {
        customPopup = new Popup();
        radioButtons = new RadioButton[Main.schoolClassArrayList.size()];
        selection = new RadioButton();
    }

    public void generateLayout(String selectedDirectory) {

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
            if (i % 2 == 0) {
                radioButtons[i] = new RadioButton(i / 2 + 1 + "а");
            } else {
                radioButtons[i] = new RadioButton(i / 2 + 1 + "б");
            }
            radioButtons[i].setToggleGroup(toggleGroup);
            radioButtons[i].setOnAction(actionEvent ->
                    selection = (RadioButton) toggleGroup.getSelectedToggle());
        }
        applyButton.setOnAction(actionEvent -> {
            customPopup.hide();
            String selectedClass = getSelectionText();
            ClassPupilsExporter classPupilsExporter = new ClassPupilsExporter();
            try {
                classPupilsExporter.exportTemplate(selectedDirectory + "\\Учащиеся-" + selectedClass + ".xls", selectedClass);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        });

        cancelButton.setOnAction(actionEvent -> customPopup.hide());

        radioButtonsVBox.setPadding(new Insets(10, 10, 0, 10));

        HBox instructionButtonsHbox = new HBox(10);
        instructionButtonsHbox.getChildren().addAll(applyButton, cancelButton);

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
