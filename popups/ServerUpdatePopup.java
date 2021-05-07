package sample.popups;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import sample.Main;
import sample.http.PostGenerator;
import sample.json.serializer.PupilSerializer;
import sample.json.serializer.SchoolClassSerializer;
import sample.json.serializer.SchoolSubjectsSeralizer;
import sample.json.serializer.TeacherSerializer;

public class ServerUpdatePopup {

    public static Popup customPopup;
    private RadioButton[] radioButtons;

    public ServerUpdatePopup() {
        customPopup = new Popup();
        radioButtons = new RadioButton[5];

    }

    public void generateLayout() {
        VBox radioButtonsVBox = new VBox(10);
        ToggleGroup toggleGroup = new ToggleGroup();

        Label instructionsLabel = new Label("Выберите категорию объектов:");
        instructionsLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button applyButton = new Button("Окей");
        Button cancelButton = new Button("Выход");
        applyButton.setStyle("-fx-background-color: #80DC94; -fx-font-size: 20px; -fx-font-weight: bold;");
        cancelButton.setStyle("-fx-background-color: #e3756d; -fx-font-size: 20px; -fx-font-weight: bold;");
        applyButton.setMinWidth(130);
        cancelButton.setMinWidth(130);

        radioButtons[0] = new RadioButton("Обновить учащихся(включая отметки)");
        radioButtons[1] = new RadioButton("Обновить учителей");
        radioButtons[2] = new RadioButton("Обновить учебные предметы");
        radioButtons[3] = new RadioButton("Обновить учебные классы");
        radioButtons[4] = new RadioButton("Обновите все");

        PostGenerator postGenerator = new PostGenerator();
        final String[] responseResult = {""};

        for (int i = 0; i < radioButtons.length; ++i) {
            radioButtons[i].setToggleGroup(toggleGroup);
        }

        radioButtons[0].setOnAction(actionEvent -> {
            PupilSerializer pupilSerializer = new PupilSerializer();
            responseResult[0] = postGenerator.postPupils(pupilSerializer.serialize(Main.pupils));
            showAlert(responseResult[0]);
        });

        radioButtons[1].setOnAction(actionEvent -> {
            TeacherSerializer teacherSerializer = new TeacherSerializer();
            responseResult[0] = postGenerator.postTeachers(teacherSerializer.serialize(Main.teachers));
            showAlert(responseResult[0]);
        });

        radioButtons[2].setOnAction(actionEvent -> {
            SchoolSubjectsSeralizer schoolSubjectsSeralizer = new SchoolSubjectsSeralizer();
            responseResult[0] = postGenerator.postSubjects(schoolSubjectsSeralizer.serialize(Main.schoolSubjects));
            showAlert(responseResult[0]);
        });

        radioButtons[3].setOnAction(actionEvent -> {
            SchoolClassSerializer schoolClassSerializer = new SchoolClassSerializer();
            responseResult[0] = postGenerator.postSchoolClasses(schoolClassSerializer.serialize(Main.schoolClassArrayList));
            showAlert(responseResult[0]);
        });

        radioButtons[4].setOnAction(actionEvent -> {
            PupilSerializer pupilSerializer = new PupilSerializer();
            postGenerator.postPupils(pupilSerializer.serialize(Main.pupils));
            TeacherSerializer teacherSerializer = new TeacherSerializer();
            postGenerator.postTeachers(teacherSerializer.serialize(Main.teachers));
            SchoolSubjectsSeralizer schoolSubjectsSeralizer = new SchoolSubjectsSeralizer();
            postGenerator.postSubjects(schoolSubjectsSeralizer.serialize(Main.schoolSubjects));
            SchoolClassSerializer schoolClassSerializer = new SchoolClassSerializer();
            responseResult[0] = postGenerator.postSchoolClasses(schoolClassSerializer.serialize(Main.schoolClassArrayList));
            showAlert(responseResult[0]);
        });


        cancelButton.setOnAction(actionEvent -> customPopup.hide());

        HBox instructionButtonsHbox = new HBox(10);
        instructionButtonsHbox.getChildren().addAll(applyButton, cancelButton);
        radioButtonsVBox.getChildren().add(instructionsLabel);
        radioButtonsVBox.getChildren().addAll(radioButtons);
        radioButtonsVBox.getChildren().add(instructionButtonsHbox);

        radioButtonsVBox.setPadding(new Insets(10, 10, 0, 10));
        radioButtonsVBox.setStyle("-fx-background-color: white;  -fx-border-width: 1; -fx-border-radius: 2; -fx-border-color: black;");
        radioButtonsVBox.setMinHeight(250);
        radioButtonsVBox.setMinWidth(300);

        customPopup.getContent().add(radioButtonsVBox);
        customPopup.setHeight(500);
        customPopup.setWidth(1600);
        customPopup.setX(350);
        customPopup.setY(650);

    }

    public void showAlert(String condition) {
        if (condition.equals("Ok")) {
            AlertWarner.showAlert("Успешно", "Информация успешно отправлена сереверу и обработана.", "Соответствующие записи обновлены", Alert.AlertType.INFORMATION);
        } else {
            AlertWarner.showAlert("Ошибка", "В процессе пересылки произошел сбой", "Соответствующие записи не обновлены", Alert.AlertType.ERROR);
        }
    }
}
