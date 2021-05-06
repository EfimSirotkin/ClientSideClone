package sample.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import sample.Main;
import sample.objects.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TeachersImporter {

    public boolean importTemplate(String teachersFilePath) {
        Workbook workbook = null;
        boolean isCellEmpty = false;
        try {
            workbook = Workbook.getWorkbook(new File(teachersFilePath));

            Sheet sheet = workbook.getSheet(0);
            int xMax = sheet.getColumns();
            int yMax = sheet.getRows();

            ArrayList<Teacher> newTeacherList = new ArrayList<>(5);

            for(int i = 1; i < yMax; ++i) {
                String currentTeacherName = sheet.getCell(1,i).getContents();
                String currentTeacherMail = sheet.getCell(2,i).getContents();
                String currentTeacherAge = sheet.getCell(3,i).getContents();
                String currentTeacherSex = sheet.getCell(4,i).getContents();
                String currentTeacherQualification = sheet.getCell(5,i).getContents();
                String currentTeacherSubjects = sheet.getCell(6,i).getContents();

                isCellEmpty = currentTeacherName.isEmpty() ||
                        currentTeacherMail.isEmpty() ||
                        currentTeacherAge.isEmpty() ||
                        currentTeacherSex.isEmpty() ||
                        currentTeacherQualification.isEmpty() ||
                        currentTeacherSubjects.isEmpty();

                if(isCellEmpty)
                    continue;

                String[] currentTeacherSubjectsArray = currentTeacherSubjects.split("-");

                ArrayList<Subject> currentTeacherSubjectsList = ObjectUtils.constructSubjectsList(currentTeacherSubjectsArray);

                Teacher currentNewTeacher = new Teacher(i, currentTeacherName,
                                                        currentTeacherMail,
                                                        Integer.parseInt(currentTeacherAge),
                                                        Integer.parseInt(currentTeacherSex),
                                                        currentTeacherQualification,
                                                        currentTeacherSubjectsList);

                newTeacherList.add(currentNewTeacher);

            }

            Main.teachers = newTeacherList;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {

            if (workbook != null) {
                workbook.close();
            }
        }
        return isCellEmpty;
    }

}
