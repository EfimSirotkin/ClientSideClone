package sample.excel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import sample.Main;
import sample.objects.Grade;
import sample.objects.ObjectUtils;
import sample.objects.Pupil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ClassGradesImporter {


    public ClassGradesImporter() {
    }

    public boolean importTemplate(String gradesFilePath, String datetimeFilePath, int classNumber) {
        Workbook workbook = null;
        boolean isDuplicateGradesPresent = false;
        try {
            workbook = Workbook.getWorkbook(new File(gradesFilePath));

            Sheet sheet = workbook.getSheet(0);
            int xMax = sheet.getColumns();
            int yMax = sheet.getRows();

            HashMap<String, String[]> dateTimes = new DatetimeGradesImporter().importSchedule(datetimeFilePath, classNumber);


            for (int i = 1; i < yMax; ++i) {
                String currentPupilName = sheet.getCell(0, i).getContents();
                Pupil currentPupil = ObjectUtils.findPupilByName(Main.pupils, currentPupilName);
                int pupilId = currentPupil.getId();
                for (int j = 1; j < xMax; ++j) {
                    String currentSubject = sheet.getCell(j, 0).getContents();
                    String[] currentDates = dateTimes.get(currentSubject);
                    int subjectID = ObjectUtils.findSubjectByName(Main.schoolSubjects, currentSubject).getSubjectID();
                    String currentGrades = sheet.getCell(j, i).getContents();
                    if (currentGrades.equals("")) continue;
                    ArrayList<Grade> newGradesList = ObjectUtils.assignGradeValues(currentGrades, currentDates, pupilId, subjectID);
                    isDuplicateGradesPresent = ObjectUtils.isDuplicateGradesPresent(currentPupil.getPupilGrades(), newGradesList);
                    if (!isDuplicateGradesPresent) {
                        currentPupil.setPupilGrades(ObjectUtils.assignGradeValues(currentGrades, currentDates, pupilId, subjectID));
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {

            if (workbook != null) {
                workbook.close();
            }
            return isDuplicateGradesPresent;
        }
    }
}
