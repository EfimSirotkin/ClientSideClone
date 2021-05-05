package sample.utils;

import sample.objects.SchoolClass;

import java.util.ArrayList;

public class SchoolClassUtils {

    public static ArrayList<String> getSchoolClassesList(ArrayList<SchoolClass> classArrayList)
    {
        ArrayList<String> schoolClasses = new ArrayList<>(5);
        for(SchoolClass oneClass : classArrayList) {
            schoolClasses.add(oneClass.getNumber() + " " + oneClass.getLetter());
            System.out.println(oneClass.getNumber() + " " + oneClass.getLetter());
        }
        return schoolClasses;
    }
}
