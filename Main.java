package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.excel.*;
import sample.http.GetGenerator;
import sample.http.PostGenerator;
import sample.json.deserializer.PupilDeserializer;
import sample.json.deserializer.SchoolClassDeserializer;
import sample.json.deserializer.SchoolSubjectsDeserializer;
import sample.json.deserializer.TeacherDeserializer;
import sample.json.serializer.PupilSerializer;
import sample.json.serializer.TeacherSerializer;
import sample.objects.*;
import sample.popups.PupilPopup;
import sample.screens.ScreensController;
import sample.utils.SchoolClassUtils;

import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

    public static String mainScreenID = "Main";
    public static String mainScreenFile = "MainPage.fxml";

    public static String classListScreenID = "View";
    public static String classListScreenFile = "ViewPage.fxml";

    public static String importExportScreenID = "ImportExport";
    public static String importExportScreenFile = "ImportExport.fxml";


    public static String analyticsScreenID = "Analytics";
    public static String analyticsScreenFile = "AnalyticsPage.fxml";

    public static ArrayList<SchoolClass> schoolClassArrayList;
    public static ArrayList<Pupil> pupils;
    public static ArrayList<Teacher> teachers;
    public static ArrayList<Subject> schoolSubjects;


    @Override
    public void start(Stage primaryStage) throws Exception{


        GetGenerator queryGenerator = new GetGenerator();
        SchoolClassDeserializer schoolClassDeserializer = new SchoolClassDeserializer();
        schoolClassArrayList = schoolClassDeserializer.deserialize(queryGenerator.querySchoolClasses());

//        for(SchoolClass oneClass : schoolClassArrayList)
//            oneClass.printClass();
//
        SchoolSubjectsDeserializer schoolSubjectsDeserializer = new SchoolSubjectsDeserializer();
        schoolSubjects = schoolSubjectsDeserializer.deserialize(queryGenerator.querySubjects());

        PupilDeserializer pupilDeserializer = new PupilDeserializer();
        String queriedPupils = queryGenerator.queryPupils();
        System.out.println(queriedPupils);
        pupils = pupilDeserializer.deserialize(queryGenerator.queryPupils());
//
//        for(Pupil onePupil : pupils)
//            onePupil.printPupil();

        TeacherDeserializer teacherDeserializer = new TeacherDeserializer();
        String queriedTeachers = queryGenerator.queryTeachers();
       // System.out.println(queriedTeachers);
        teachers = teacherDeserializer.deserialize(queryGenerator.queryTeachers());


        TeacherSerializer teacherSerializer = new TeacherSerializer();
        PostGenerator postGenerator = new PostGenerator();
        postGenerator.postTeachers(teacherSerializer.serialize(teachers));

        PupilSerializer pupilSerializer = new PupilSerializer();
        postGenerator.postPupils(pupilSerializer.serialize(pupils));


//        for(Teacher teacher : teachers)
//            teacher.printTeacher();
        //       ClassGradesImporter classGradesImporter = new ClassGradesImporter();
//        classGradesImporter.importTemplate("res\\??????????????????????????.xls");



        ClassGradesImporter classGradesImporter = new ClassGradesImporter();
        classGradesImporter.importTemplate("res\\??????????????????????????.xls","res\\????????????????????.xls", 1 );

//        for(Pupil onePupil : ObjectUtils.findPupilsByClass(Main.pupils, 1))
//            onePupil.printPupilGrades();

        System.out.println(Main.pupils.get(0).getTeacherID());

        for(Subject oneSubject : Main.schoolSubjects)
            System.out.println(oneSubject.getSubjectName());
        System.out.println(Main.pupils.size());
        ClassPupilsImporter classPupilsImporter = new ClassPupilsImporter();
        classPupilsImporter.importTemplate("res\\????????????????\\1??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\2??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\2??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\3??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\3??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\4??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\4??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\5??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\5??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\6??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\6??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\7??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\7??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\8??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\8??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\9??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\9??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\10??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\10??.xls");
        System.out.println(Main.pupils.size());
        classPupilsImporter.importTemplate("res\\????????????????\\11??.xls");
        System.out.println(Main.pupils.size());
        //classPupilsImporter.importTemplate("res\\????????????????\\11??.xls");
        System.out.println(Main.pupils.size());


        ClassSubjectsImporter classSubjectsImporter = new ClassSubjectsImporter();
        classSubjectsImporter.importTemplate("res\\?????????????? ????????????????.xls");

        for(Subject oneSubject : Main.schoolClassArrayList.get(0).getClassSubjects())
            System.out.println(oneSubject.getSubjectName());

        for(Pupil onePupil : Main.pupils)
            onePupil.printPerson();

        classGradesImporter.importTemplate("res\\??????????????\\1??.xls", "res\\????????????????????.xls", 1);

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(mainScreenID, mainScreenFile);
        mainContainer.loadScreen(classListScreenID, classListScreenFile);
        mainContainer.loadScreen(importExportScreenID, importExportScreenFile);
        mainContainer.loadScreen(analyticsScreenID, analyticsScreenFile);
        mainContainer.setScreen(mainScreenID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("?????????????????????? ???????????????? ?????????? ???????????????????????? ????????????????");
        primaryStage.show();


        for(Subject oneSubject : Main.schoolSubjects)
            System.out.println(oneSubject.getSubjectName());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
