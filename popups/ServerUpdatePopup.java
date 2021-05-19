package sample.popups;

import javafx.geometry.Insets;
import javafx.scene.control.*;
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
    private Button[] Buttons;

    public ServerUpdatePopup() {
        customPopup = new Popup();
        Buttons = new Button[5];

    }

    public void generateLayout() {
        VBox radioButtonsVBox = new VBox(10);
        ToggleGroup toggleGroup = new ToggleGroup();

        Label instructionsLabel = new Label("Выберите категорию объектов:");
        instructionsLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button applyButton = new Button("Окей");
        applyButton.setStyle("-fx-background-color: #80DC94; -fx-font-size: 20px; -fx-font-weight: bold;");
        applyButton.setMinWidth(280);

        Buttons[0] = new Button("Обновить учащихся(включая отметки)");
        Buttons[1] = new Button("Обновить учителей");
        Buttons[2] = new Button("Обновить учебные предметы");
        Buttons[3] = new Button("Обновить учебные классы");
        Buttons[4] = new Button("Обновите все");

        for(int i = 0;i < Buttons.length; ++i)
            Buttons[i].setStyle("-fx-font-size: 14px;");

        PostGenerator postGenerator = new PostGenerator();
        final String[] responseResult = {""};


        Buttons[0].setOnAction(actionEvent -> {
            PupilSerializer pupilSerializer = new PupilSerializer();
            responseResult[0] = postGenerator.postPupils(pupilSerializer.serialize(Main.pupils));
            showAlert(responseResult[0]);
            Buttons[0].setText("Обновить учащихся(включая отметки) - Обновлено");
            Buttons[0].setDisable(true);
        });

        Buttons[1].setOnAction(actionEvent -> {
            TeacherSerializer teacherSerializer = new TeacherSerializer();
            responseResult[0] = postGenerator.postTeachers(teacherSerializer.serialize(Main.teachers));
            showAlert(responseResult[0]);
            Buttons[1].setText("Обновить учителей - Обновлено");
            Buttons[1].setDisable(true);
        });

        Buttons[2].setOnAction(actionEvent -> {
            SchoolSubjectsSeralizer schoolSubjectsSeralizer = new SchoolSubjectsSeralizer();
            responseResult[0] = postGenerator.postSubjects(schoolSubjectsSeralizer.serialize(Main.schoolSubjects));
            showAlert(responseResult[0]);
            Buttons[2].setText("Обновить учебные предметы - Обновлено");
            Buttons[2].setDisable(true);
        });

        Buttons[3].setOnAction(actionEvent -> {
            SchoolClassSerializer schoolClassSerializer = new SchoolClassSerializer();
            responseResult[0] = postGenerator.postSchoolClasses(schoolClassSerializer.serialize(Main.schoolClassArrayList));
            showAlert(responseResult[0]);
            Buttons[3].setText("Обновить учебные классы - Обновлено");
            Buttons[3].setDisable(true);
        });

        Buttons[4].setOnAction(actionEvent -> {
            PupilSerializer pupilSerializer = new PupilSerializer();
            postGenerator.postPupils(pupilSerializer.serialize(Main.pupils));
            TeacherSerializer teacherSerializer = new TeacherSerializer();
            postGenerator.postTeachers(teacherSerializer.serialize(Main.teachers));
            SchoolSubjectsSeralizer schoolSubjectsSeralizer = new SchoolSubjectsSeralizer();
            postGenerator.postSubjects(schoolSubjectsSeralizer.serialize(Main.schoolSubjects));
            SchoolClassSerializer schoolClassSerializer = new SchoolClassSerializer();
            responseResult[0] = postGenerator.postSchoolClasses(schoolClassSerializer.serialize(Main.schoolClassArrayList));
            showAlert(responseResult[0]);
            Buttons[0].setText("Обновить учащихся(включая отметки) - Обновлено");
            Buttons[0].setDisable(true);
            Buttons[1].setText("Обновить учителей - Обновлено");
            Buttons[1].setDisable(true);
            Buttons[2].setText("Обновить учебные предметы - Обновлено");
            Buttons[2].setDisable(true);
            Buttons[3].setText("Обновить учебные классы - Обновлено");
            Buttons[3].setDisable(true);
            Buttons[4].setText("Обновить все - Обновлено");
            Buttons[4].setDisable(true);
        });

        applyButton.setOnAction(actionEvent -> customPopup.hide());

        radioButtonsVBox.getChildren().add(instructionsLabel);
        radioButtonsVBox.getChildren().addAll(Buttons);
        radioButtonsVBox.getChildren().add(applyButton);

        radioButtonsVBox.setPadding(new Insets(10, 10, 0, 10));
        radioButtonsVBox.setStyle("-fx-background-color: white;  -fx-border-width: 1; -fx-border-radius: 2; -fx-border-color: black;");
        radioButtonsVBox.setMinHeight(320);
        radioButtonsVBox.setMinWidth(300);

        customPopup.getContent().add(radioButtonsVBox);
        customPopup.setHeight(560);
        customPopup.setWidth(1600);
        customPopup.setX(350);
        customPopup.setY(650);

    }

    public void showAlert(String condition) {
        if (condition.equals("OK")) {
            AlertWarner.showAlert("Успешно", "Информация успешно отправлена сереверу и обработана.", "Соответствующие записи обновлены", Alert.AlertType.INFORMATION);
        } else {
            AlertWarner.showAlert("Ошибка", "В процессе пересылки произошел сбой", "Соответствующие записи не обновлены", Alert.AlertType.ERROR);
        }
    }
}
