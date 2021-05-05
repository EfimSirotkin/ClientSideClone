package sample.screens;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable, ControlledScreen{

    private ScreensController myController;


    @FXML
    private ImageView classListImageView;

    @FXML
    private ImageView analyticImageView;

    @FXML
    private ImageView importExportImageView;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Button classListButton;

    @FXML
    private Button analyticsButton;

    @FXML
    private Button importExportButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        File logoFile = new File("res/Logo.JPG");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        File classListImageFile = new File("res/to-do-list.jpg");
        Image classListImage = new Image(classListImageFile.toURI().toString());
        classListImageView.setImage(classListImage);

        File analyticImageFile = new File("res/analytics.jpg");
        Image analyticsImage = new Image(analyticImageFile.toURI().toString());
        analyticImageView.setImage(analyticsImage);

        File importImageFile = new File("res/import.jpg");
        Image importImage = new Image(importImageFile.toURI().toString());
        importExportImageView.setImage(importImage);

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void onImportButtonMouseEntered() {
        importExportButton.setStyle("-fx-background-color: #00CCCC");
    }

    @FXML
    private void onImportButtonMouseExited() {
        importExportButton.setStyle("-fx-background-color: #0099CC");
    }

    @FXML
    private void onAnalyticsButtonMouseEntered() {
        analyticsButton.setStyle("-fx-background-color: #66CCCC");
    }

    @FXML
    private void onAnalyticsButtonMouseExited() {
        analyticsButton.setStyle("-fx-background-color: #66CC99");
    }

    @FXML
    private void onClassListButtonMouseEntered() {
        classListButton.setStyle("-fx-background-color: #6699FF");
    }

    @FXML
    private void onClassListButtonMouseExited() {
        classListButton.setStyle("-fx-background-color: #41C7DB");
    }

    @FXML
    private void onClassListButtonClicked() {
        myController.setScreen(Main.classListScreenID);
    }

    @FXML
    private void onImportExportButtonClicked() { myController.setScreen(Main.importExportScreenID); }
}
