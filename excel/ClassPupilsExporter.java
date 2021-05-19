package sample.excel;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import sample.Main;
import sample.objects.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClassPupilsExporter {
    public void exportTemplate(String filePath, String currentClass) throws IOException, WriteException {

        WritableWorkbook gradesWorkBook = Workbook.createWorkbook(new File(filePath));
        WritableSheet excelSheet = gradesWorkBook.createSheet("Sheet 1", 0);

        WritableCellFormat cFormat = new WritableCellFormat();
        cFormat.setAlignment(Alignment.CENTRE);
        cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        WritableFont font = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD);
        cFormat.setFont(font);

        CellView cellView = new CellView();
        cellView.setSize(30 * 256);

        Label label = new Label(0, 0, "Имя/Предмет", cFormat);
        excelSheet.setColumnView(0, cellView);
        excelSheet.addCell(label);

        label = new Label(1, 0, "Ф.И.", cFormat);
        excelSheet.setColumnView(1, cellView);
        excelSheet.addCell(label);

        label = new Label(2, 0, "эл. адрес", cFormat);
        excelSheet.setColumnView(2, cellView);
        excelSheet.addCell(label);

        label = new Label(3, 0, "Возраст", cFormat);
        excelSheet.setColumnView(3, cellView);
        excelSheet.addCell(label);

        label = new Label(4, 0, "Пол(1-М, 0-Ж", cFormat);
        excelSheet.setColumnView(4, cellView);
        excelSheet.addCell(label);

        SchoolClass currentSchoolClass = ObjectUtils.findClassByName(Main.schoolClassArrayList, currentClass);
        ArrayList<Pupil> classPupils = null;
        String currentClassTeacher = "";
        int teacherID = 0;
        Teacher currentTeacher;
        if (currentSchoolClass != null) {
            classPupils = ObjectUtils.findPupilsByClass(Main.pupils, currentSchoolClass.getId());
            currentTeacher = ObjectUtils.findTeacherByID(Main.teachers, classPupils.get(0).getTeacherID());
            if (currentTeacher != null) {
                currentClassTeacher = currentTeacher.getFullName();
            }
        }
        label = new Label(0, 0, currentClass, cFormat);
        excelSheet.addCell(label);

        label = new Label(0,1, "Класс. рук.", cFormat);
        excelSheet.addCell(label);

        label = new Label(0, 2, currentClassTeacher, cFormat);
        excelSheet.addCell(label);

        for(int i = 0; i < classPupils.size(); ++i) {
            Pupil currentPupil = classPupils.get(i);
            label = new Label(1,i+1, currentPupil.getFullName(),cFormat);
            excelSheet.addCell(label);

            label = new Label(2, i+1, currentPupil.getMail(), cFormat);
            excelSheet.addCell(label);

            label = new Label(3, i + 1, String.valueOf(currentPupil.getAge()), cFormat);
            excelSheet.addCell(label);

            label = new Label(4, i + 1, String.valueOf(currentPupil.getSex()),cFormat);
            excelSheet.addCell(label);
        }


        gradesWorkBook.write();
        gradesWorkBook.close();

    }

}
