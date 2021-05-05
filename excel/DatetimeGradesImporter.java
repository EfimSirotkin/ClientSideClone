package sample.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import sample.Main;
import sample.objects.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class DatetimeGradesImporter  {

    public HashMap<String, String[]> importSchedule(String filePath, int classNumber) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(new File(filePath));

            Sheet sheet = workbook.getSheet(0);
            int xMax = sheet.getColumns();
            int yMax = sheet.getRows();

            HashMap<String, String[]> subjectDatesMap = new HashMap<>(5);

            for(int i  = 1; i < yMax; ++i) {
                String currentDates = sheet.getCell(classNumber * 2 - 1, i).getContents();
                if(currentDates.equals("")) continue;
                String currentSubject = ObjectUtils.findSubjectByName(Main.schoolSubjects, sheet.getCell(0, i).getContents()).getSubjectName() ;
                String[] currentDatesArray = ObjectUtils.generateDates(currentDates);
                subjectDatesMap.put(currentSubject, currentDatesArray);
            }
        return subjectDatesMap;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {

            if (workbook != null) {
                workbook.close();
            }

        }
        return null;
    }
}
