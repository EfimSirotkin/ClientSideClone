package sample.screens;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.Main;
import sample.excel.ClassGradesImporter;
import sample.excel.ClassPupilsImporter;
import sample.excel.ClassSubjectsImporter;
import sample.excel.TeachersImporter;
import sample.json.deserializer.TeacherDeserializer;
import sample.objects.Grade;
import sample.objects.Teacher;
import sample.popups.*;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportExportScreenController implements Initializable, ControlledScreen {

    private ScreensController myController;

    @FXML
    private ImageView logoImageView;

    @FXML
    private ImageView uploadImageView;

    @FXML
    private ImageView downloadImageView;

    @FXML
    private ImageView studentsImageView;

    @FXML
    private ImageView diaryImageView;

    @FXML
    private ImageView teacherImageView;

    @FXML
    private ImageView subjectsImageView;

    @FXML
    private ImageView scheduleImageView;

    @FXML
    private ImageView serverImageView;

    @FXML
    private ImageView databaseImageView;


    @FXML
    private Button returnButton;

    public static String gradesFileAbsolutePath;
    public static String scheduleFileAbsolutePath;
    public static String classNumber;
    public static boolean isGradesDuplicate = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("res/Logo.JPG");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        File uploadFile = new File("res\\ImportExport\\upload.jpg");
        Image uploadImage = new Image(uploadFile.toURI().toString());
        uploadImageView.setImage(uploadImage);

        File downloadFile = new File("res\\ImportExport\\download.jpg");
        Image downloadImage = new Image(downloadFile.toURI().toString());
        downloadImageView.setImage(downloadImage);

        File studentsFile = new File("res\\ImportExport\\students.jpg");
        Image studentsImage = new Image(studentsFile.toURI().toString());
        studentsImageView.setImage(studentsImage);

        File diaryFile = new File("res\\ImportExport\\diary.jpg");
        Image diaryImage = new Image(diaryFile.toURI().toString());
        diaryImageView.setImage(diaryImage);

        File teacherFile = new File("res\\ImportExport\\teacher.jpg");
        Image teacherImage = new Image(teacherFile.toURI().toString());
        teacherImageView.setImage(teacherImage);

        File subjectsFile = new File("res\\ImportExport\\subjects.jpg");
        Image subjectsImage = new Image(subjectsFile.toURI().toString());
        subjectsImageView.setImage(subjectsImage);

        File scheduleFile = new File("res\\ImportExport\\schedule.jpg");
        Image scheduleImage = new Image(scheduleFile.toURI().toString());
        scheduleImageView.setImage(scheduleImage);

        File serverFile = new File("res\\ImportExport\\server.jpg");
        Image serverImage = new Image(serverFile.toURI().toString());
        serverImageView.setImage(serverImage);

        File databaseFile = new File("res\\ImportExport\\database-storage.jpg");
        Image databaseImage = new Image(databaseFile.toURI().toString());
        databaseImageView.setImage(databaseImage);


    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    public void onReturnButtonMouseEntered() {
        returnButton.setStyle("-fx-background-color: #FFCC66; -fx-background-radius: 1em;");
    }

    public void onReturnButtonMouseExited() {
        returnButton.setStyle("-fx-background-color: #FFC000; -fx-background-radius: 1em;");
    }

    public void onReturnButtonClicked() {
        myController.setScreen(Main.mainScreenID);
    }

    public void onButtonMouseEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #BAE8D1");
    }

    public void onButtonMouseExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #66CC99");
    }

    private String getSelectedFilePath(String dialogTitle) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(dialogTitle);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("xls Files", "*.xls"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null)
            return selectedFile.getAbsolutePath();
        else
            return "";

    }

    private String getSelectedDirectory(String dialogTitle) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(dialogTitle);
        File selectedDir = directoryChooser.showDialog(null);
        if(selectedDir != null)
            return selectedDir.getAbsolutePath();
        else
            return "";


    }

    public void onImportPupilsButtonClicked() {
        ClassPupilsImporter classPupilsImporter = new ClassPupilsImporter();
        String absolutePath = getSelectedFilePath("Выберите файл с информацией об обучающихся");

        boolean isDuplicate = false;
        isDuplicate = classPupilsImporter.importTemplate(absolutePath);
        if (isDuplicate) {
            AlertWarner.showAlert("Дубликаты", "В выбранном файле содержатся дубликаты", "Соответствующие записи не добавлены", Alert.AlertType.WARNING);
        } else {
            AlertWarner.showAlert("Успешно", "Операция выполнена", "Информация об обучающихся успешно добавлена", Alert.AlertType.INFORMATION);
        }

    }

    public void onImportGradesButtonClicked() {

        gradesFileAbsolutePath = getSelectedFilePath("Выберите файл с информацией об отметках");
        scheduleFileAbsolutePath = getSelectedFilePath("Выберите файл с расписанием занятий");

        GradePopup classSelectionPopup = new GradePopup();
        classSelectionPopup.generateLayout();
        GradePopup.customPopup.show(Window.getWindows().get(0));

    }

    public void onImportTeachersButtonClicked() {
        String absolutePath = getSelectedFilePath("Выберите файл с информацией об учителях:");
        TeachersImporter teachersImporter = new TeachersImporter();

        if(!absolutePath.isEmpty()) {
            boolean isDuplicateFound = teachersImporter.importTemplate(absolutePath);
            if (isDuplicateFound) {
                AlertWarner.showAlert("Пустые ячейки", "В выбранном файле содержатся пустые ячейки", "Соответствующие записи не добавлены", Alert.AlertType.ERROR);
            } else {
                AlertWarner.showAlert("Успешно", "Операция выполнена", "Информация об учителях успешно добавлена", Alert.AlertType.WARNING);
            }
        }
        else {
            AlertWarner.showAlert("Отмена", "Операция отменена", "Информация об учителях не добавлена", Alert.AlertType.INFORMATION);
        }
        for(Teacher oneTeacher: Main.teachers)
            oneTeacher.printTeacher();

    }

    public void onImportClassSubjectsButtonClicked() {
        String absolutePath = getSelectedFilePath("Выберите файл с информацией об учебных предметах по классам");
        ClassSubjectsImporter classSubjectsImporter = new ClassSubjectsImporter();

        if(!absolutePath.isEmpty()) {
            if(classSubjectsImporter.importTemplate(absolutePath)) {
                AlertWarner.showAlert("Успешно", "Операция выполнена", "Информация об учителях успешно добавлена", Alert.AlertType.WARNING);
            }

        }
        else {
            AlertWarner.showAlert("Отмена", "Операция отменена", "Информация об учителях не добавлена", Alert.AlertType.INFORMATION);
        }
    }

    public void onUpdateServerButtonClicked() {
        ServerUpdatePopup serverUpdatePopup = new ServerUpdatePopup();
        serverUpdatePopup.generateLayout();
        ServerUpdatePopup.customPopup.show(Window.getWindows().get(0));
    }

    public void onGradesExportButtonClicked() {
        String absolutePath = getSelectedDirectory("Выберите место для экспорта");
        if(!absolutePath.isEmpty()) {
            ClassGradesPopup classGradesPopup = new ClassGradesPopup();
            classGradesPopup.generateLayout(absolutePath);
            ClassGradesPopup.customPopup.show(Window.getWindows().get(0));
        }
    }

    public void onClassPupilsExportButtonClicked() {
        String absolutePath = getSelectedDirectory("Выберите место для экспорта");
        if(!absolutePath.isEmpty()) {
            ClassPupilsPopup classPupilsPopup = new ClassPupilsPopup();
            classPupilsPopup.generateLayout(absolutePath);
            ClassPupilsPopup.customPopup.show(Window.getWindows().get(0));
        }
    }

}
