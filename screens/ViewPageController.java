package sample.screens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Window;
import sample.Main;
import sample.objects.ObjectUtils;
import sample.objects.Pupil;
import sample.objects.SchoolClass;
import sample.objects.Teacher;
import sample.popups.PupilPopup;
import sample.utils.SchoolClassUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPageController implements Initializable, ControlledScreen {

    private ScreensController myController;

    @FXML
    private ImageView teacherAvatarImageView;

    @FXML
    private ImageView logoImageView;
    @FXML
    private TableView<Pupil> pupilTable;

    @FXML
    private ListView<String> classesList;

    @FXML
    private Button returnButton;

    @FXML
    private Label fullTeacherNameLabel;

    @FXML
    private Label teacherMailLabel;

    @FXML
    private Label teacherEducationLabel;

    @FXML
    private Label teacherQualificationLabel;

    @FXML
    private Label numberOfPupilsLabel;

    @FXML
    private Label averagePointLabel;

    @FXML
    private Label mostPopularSubjectLabel;

    @FXML
    private Label leastPopularSubjectLabel;

    public static Popup pupilPopup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("res/Logo.JPG");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        File avatarImageView = new File("res/teacher (1).JPG");
        Image avatarImage = new Image(avatarImageView.toURI().toString());
        teacherAvatarImageView.setImage(avatarImage);
        putDataIntoPupilTableView();
        initializeClassesList();

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    public void putDataIntoPupilColumn(String tableColumnName, String sourceField, int width) {
        TableColumn<Pupil, String> column = new TableColumn<>(tableColumnName);
        column.setCellValueFactory(new PropertyValueFactory<>(sourceField));
        column.setMinWidth(width);

        pupilTable.getColumns().add(column);
    }

    public void putDataIntoPupilTableView() {
        putDataIntoPupilColumn("Номер", "id", 100);
        putDataIntoPupilColumn("ФИО", "fullName", 300);
        putDataIntoPupilColumn("Почта", "mail", 300);
        putDataIntoPupilColumn("Возраст", "age", 100);

    }

    public void initializeClassesList() {

        ObservableList<String> classObservableList = FXCollections.observableArrayList(SchoolClassUtils.getSchoolClassesList(Main.schoolClassArrayList));
        classesList.setItems(classObservableList);
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

    public void onClassItemMouseClicked() {
        StringBuilder stringBuilder = new StringBuilder(classesList.getSelectionModel().getSelectedItem());
        if(stringBuilder.length() == 3) {
            stringBuilder.deleteCharAt(1);
        }
        else {
            stringBuilder.deleteCharAt(2);
        }

        SchoolClass selectedSchoolClass = ObjectUtils.findClassByName(Main.schoolClassArrayList, stringBuilder.toString());
        ArrayList<Pupil> classPupils = ObjectUtils.findPupilsByClass(Main.pupils, selectedSchoolClass.getId());
        int classTeacherID = classPupils.get(0).getTeacherID();
        Teacher classTeacher = ObjectUtils.findTeacherByID(Main.teachers, classTeacherID);
        initializeTeacherTextFields(classTeacher);
        ObservableList<Pupil> observableList = FXCollections.observableArrayList(classPupils);
        pupilTable.getColumns().clear();
        pupilTable.setItems(observableList);

        averagePointLabel.setText(String.valueOf(ObjectUtils.calculateAverageForClass(classPupils)));
        numberOfPupilsLabel.setText(String.valueOf(classPupils.size()));
        try {
            mostPopularSubjectLabel.setText(ObjectUtils.calculateTheMostPopularSubject(classPupils));
            leastPopularSubjectLabel.setText(ObjectUtils.calculateTheLeastPopularSubject(classPupils));
        }catch (NullPointerException e) {
            System.out.println("no marks");
        }

        putDataIntoPupilTableView();
    }

    public void initializeTeacherTextFields(Teacher sourceTeacher) {
        fullTeacherNameLabel.setText(sourceTeacher.getFullName());
        teacherMailLabel.setText(sourceTeacher.getMail());
        teacherEducationLabel.setText("высшее");
        teacherQualificationLabel.setText(sourceTeacher.getQualification());
    }

    public void onPupilItemClicked() {
        if(PupilPopup.customPopup != null)
            PupilPopup.customPopup.hide();
        PupilPopup pupilPopup = new PupilPopup();
        String selectedName = pupilTable.getSelectionModel().getSelectedItem().getFullName();
        pupilPopup.generatePopup(ObjectUtils.findPupilByName(Main.pupils, selectedName));
        PupilPopup.customPopup.show(Window.getWindows().get(0));
    }
}
