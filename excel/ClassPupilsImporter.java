package sample.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import sample.Main;
import sample.objects.ObjectUtils;
import sample.objects.Pupil;
import sample.objects.SchoolClass;

import java.io.File;
import java.io.IOException;

public class ClassPupilsImporter implements Importer {

    @Override
    public boolean importTemplate(String filePath) {
        Workbook workbook = null;
        boolean isDuplicate = false;
        try {

            workbook = Workbook.getWorkbook(new File(filePath));

            Sheet sheet = workbook.getSheet(0);
            int yMax = sheet.getRows();

            String currentClass = sheet.getCell(0,0).getContents();
            SchoolClass schoolClass = ObjectUtils.findClassByName(Main.schoolClassArrayList, currentClass);
            String currentTeacherName = sheet.getCell(0,2).getContents();
            int teacherID = ObjectUtils.findTeacherByName(Main.teachers, currentTeacherName).getId();

            for (int i = 1; i < yMax; ++i) {
                String currentPupilName = sheet.getCell(1, i).getContents();
                String currentPupilMail = sheet.getCell(2,i).getContents();
                String currentPupilAge = sheet.getCell(3,i).getContents();
                String currentPupilSex = sheet.getCell(4,i).getContents();
                Pupil currentNewPupil = new Pupil(Main.pupils.size()+ 1,
                                                    currentPupilName,
                                                    currentPupilMail,
                                                    Integer.parseInt(currentPupilAge),
                                                    Integer.parseInt(currentPupilSex));
                if((isDuplicate = ObjectUtils.isDuplicatePresent(Main.pupils, currentNewPupil)))
                    continue;
                currentNewPupil.setTeacherID(teacherID);
                currentNewPupil.setPupilClass(schoolClass);
                Main.pupils.add(currentNewPupil);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {

            if (workbook != null) {
                workbook.close();
            }

        }
        return isDuplicate;
    }
}
