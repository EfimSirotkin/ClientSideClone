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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ClassGradesExporter {

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
        for (int i = 0; i <= Main.schoolSubjects.size() + 1; i++)
            excelSheet.setColumnView(i, cellView);

        Label label = new Label(0, 0, "Имя/Предмет", cFormat);

        excelSheet.addCell(label);
        Collections.sort(Main.schoolSubjects, new Comparator<Subject>() {
            @Override
            public int compare(Subject subject, Subject t1) {
                return Integer.compare(subject.getSubjectID(), t1.getSubjectID());
            }
        });

        for(int i = 0; i < Main.schoolSubjects.size(); ++i) {
            String currentSubject = Main.schoolSubjects.get(i).getSubjectName();
            label = new Label(i+1, 0, currentSubject, cFormat);
            excelSheet.addCell(label);
        }

        SchoolClass currentSchoolClass = ObjectUtils.findClassByName(Main.schoolClassArrayList, currentClass);
        if(currentSchoolClass != null) {
            ArrayList<Pupil> classPupils = ObjectUtils.findPupilsByClass(Main.pupils, currentSchoolClass.getId());

            for(int i = 0; i < classPupils.size(); ++i) {
                Pupil currentPupil = classPupils.get(i);
                ArrayList<Grade> currentPupilGrades = currentPupil.getPupilGrades();
                String currentPupilName = currentPupil.getFullName();
                label = new Label(0, i+1, currentPupilName);
                excelSheet.addCell(label);
                for(int j = 0; j < Main.schoolSubjects.size(); ++j)
                {
                    String currentSubject = Main.schoolSubjects.get(j).getSubjectName();
                    ArrayList<Grade> currentSubjectGrades = ObjectUtils.getSubjectGrades(currentPupilGrades, currentSubject);
                    String currentSubjectGradesString = "";
                    for(Grade oneGrade : currentSubjectGrades) {
                        if(oneGrade != null) {
                            currentSubjectGradesString += oneGrade.getValue() + " ";
                        }
                    }

                    label = new Label(j+1, i+1, currentSubjectGradesString);
                    excelSheet.addCell(label);
                }
            }
        }

        gradesWorkBook.write();
        gradesWorkBook.close();

    }
}
