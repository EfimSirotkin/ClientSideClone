package sample.screens;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import sample.Main;
import sample.excel.ClassPupilsImporter;
import sample.popups.AlertWarner;

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
    private Button returnButton;

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

    public void onImportPupilsButtonClicked() {
        ClassPupilsImporter classPupilsImporter = new ClassPupilsImporter();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл с информацией об учащихся");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("xls Files", "*.xls"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String absolutePath = selectedFile.getAbsolutePath();
        boolean isDuplicate = false;
        isDuplicate = classPupilsImporter.importTemplate(absolutePath);
        if(isDuplicate) {
            AlertWarner.showAlert("Дубликаты", "В выбранном файле содержатся дубликаты", "Соответствующие записи не добавлены", Alert.AlertType.WARNING);
        }
        else {
            AlertWarner.showAlert("Успешно","Операция выполнена", "Информация об обучающихся успешно добавлена", Alert.AlertType.INFORMATION);
        }

    }



}
