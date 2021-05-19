package sample.screens;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import sample.Main;
import sample.analytics.AnalyticsUtils;
import sample.objects.ObjectUtils;
import sample.objects.Subject;
import sample.popups.DiagramGenerator;
import sample.popups.SubjectPopup;
;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AnalyticsScreenController implements Initializable, ControlledScreen {

    private ScreensController myController;


    @FXML
    private Label modaLabel;
    @FXML
    private Label averageScoreLabel;
    @FXML
    private Label medianLabel;
    @FXML
    private Label dispersionLabel;


    @FXML
    private ImageView logoImageView;

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button returnButton;

    @FXML
    public LineChart<String, Number> classesAverageDiagram;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("res/Logo.JPG");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        classesAverageDiagram.getData().add(DiagramGenerator.getClassesAverageDistribution(Main.pupils));

        ArrayList<Integer> gradesList = ObjectUtils.getGradesAsInteger();
        modaLabel.setText(String.valueOf(AnalyticsUtils.calculateModa(gradesList)));
        averageScoreLabel.setText(String.valueOf(AnalyticsUtils.calculateAverageScore(gradesList)));
        medianLabel.setText(String.valueOf(AnalyticsUtils.calculateMedian(gradesList)));
        dispersionLabel.setText(String.valueOf(AnalyticsUtils.calculateDispersion(gradesList, Double.parseDouble(averageScoreLabel.getText()))));


    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }



    public void initializeDiagram() {
    }


    public void onReturnButtonMouseEntered() {
        returnButton.setStyle("-fx-background-color: #FFCC66; -fx-background-radius: 1em;");
    }

    public void onReturnButtonMouseExited() {
        returnButton.setStyle("-fx-background-color: #FFC000; -fx-background-radius: 1em;");
    }

    public void onLoadDiagramButtonMouseEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #66CCCC; -fx-background-radius: 1em;");
    }

    public void onLoadDiagramButtonMouseExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #66CC99; -fx-background-radius: 1em;");
    }


    public void onSubjectButtonMouseEntered(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #FFCC66; -fx-background-radius: 1em;");
    }

    public void onSubjectButtonMouseExited(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #FFC000; -fx-background-radius: 1em;");
    }


    public void onReturnButtonClicked() {
        myController.setScreen(Main.mainScreenID);
    }

    public void onSubjectButtonClicked(MouseEvent event) {
        Button button = (Button) event.getSource();
        String selectedSubject = button.getText();
        SubjectPopup subjectPopup = new SubjectPopup();
        subjectPopup.generateLayout(selectedSubject);
        SubjectPopup.customPopup.show(Window.getWindows().get(0));
    }


}
