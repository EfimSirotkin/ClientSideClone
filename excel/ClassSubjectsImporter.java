package sample.excel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import sample.Main;
import sample.objects.ObjectUtils;
import sample.objects.Pupil;
import sample.objects.SchoolClass;
import sample.objects.Subject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ClassSubjectsImporter implements Importer{

    @Override
    public boolean importTemplate(String filePath) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(new File(filePath));

            Sheet sheet = workbook.getSheet(0);
            int xMax = sheet.getColumns();
            int yMax = sheet.getRows();
            System.out.println("[ " + xMax + "x" + yMax + "]");

            for(int  i = 1; i < xMax; i++) {
                ArrayList<Subject> importedSubjects = new ArrayList<Subject>(5);
                for(int j = 1; j < yMax; j++) {
                    String currentSubjectCell = sheet.getCell(i,j).getContents();
                    if(currentSubjectCell.equals(""))
                        break;
                    importedSubjects.add(ObjectUtils.findSubjectByName(Main.schoolSubjects, currentSubjectCell));

                }
                SchoolClass firstClass = ObjectUtils.findClassByName(Main.schoolClassArrayList, i + "а");
                SchoolClass secondClass = ObjectUtils.findClassByName(Main.schoolClassArrayList, i + "б");
                int firstClassID = firstClass.getId();
                int secondClassID = secondClass.getId();

                ArrayList<Pupil> firstClassPupils = ObjectUtils.findPupilsByClass(Main.pupils, firstClassID);
                ArrayList<Pupil> secondClassPupils = ObjectUtils.findPupilsByClass(Main.pupils, secondClassID);

                if(firstClassPupils.size() !=0 && secondClassPupils.size() !=0) {

                    firstClassPupils.get(0).getPupilClass().setClassSubjects(importedSubjects);
                    secondClassPupils.get(0).getPupilClass().setClassSubjects(importedSubjects);
                    ObjectUtils.findClassByName(Main.schoolClassArrayList, i + "а").setClassSubjects(importedSubjects);
                    ObjectUtils.findClassByName(Main.schoolClassArrayList, i + "б").setClassSubjects(importedSubjects);
                }
                else {
                    ObjectUtils.findClassByName(Main.schoolClassArrayList, i + "а").setClassSubjects(importedSubjects);
                    ObjectUtils.findClassByName(Main.schoolClassArrayList, i + "б").setClassSubjects(importedSubjects);
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
        }
        return true;
    }
}
