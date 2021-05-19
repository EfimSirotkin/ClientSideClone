package sample.objects;

import sample.Main;
import sample.json.deserializer.GradeDeserializer;
import sample.json.deserializer.PupilDeserializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ObjectUtils {

    public static Teacher findTeacherByID(ArrayList<Teacher> teachers, int teacherID) {
        for (Teacher oneTeacher : teachers) {
            if (oneTeacher.getId() == teacherID)
                return oneTeacher;
        }
        return null;
    }

    public static Pupil findPupilByID(ArrayList<Pupil> pupils, int pupilID) {
        for (Pupil onePupil : pupils) {
            if (onePupil.getId() == pupilID)
                return onePupil;
        }
        return null;
    }

    public static ArrayList<Pupil> findPupilsByClass(ArrayList<Pupil> pupils, int pupilsClassID) {
        ArrayList<Pupil> pupilsOfOneClass = new ArrayList<Pupil>(5);
        for (Pupil onePupil : pupils) {
            if (onePupil.getPupilClass().getId() == pupilsClassID)
                pupilsOfOneClass.add(onePupil);
        }
        return pupilsOfOneClass;

    }

    public static SchoolClass findClassByName(ArrayList<SchoolClass> classes, String className) {
        for (SchoolClass oneClass : classes) {
            if ((oneClass.getNumber() + oneClass.getLetter()).equals(className.toLowerCase()))
                return oneClass;
        }
        return null;
    }

    public static Subject findSubjectByName(ArrayList<Subject> subjects, String subjectName) {
        for (Subject oneSubject : subjects) {
            if (oneSubject.getSubjectName().toLowerCase().equals(subjectName.toLowerCase()))
                return oneSubject;
        }
        return null;
    }

    public static Pupil findPupilByName(ArrayList<Pupil> pupils, String name) {
        for (Pupil onePupil : pupils)
            if (onePupil.getFullName().toLowerCase().equals(name.toLowerCase()))
                return onePupil;
        return null;
    }


    public static ArrayList<Grade> constructGradesList(String values, String dates) {
        ArrayList<Grade> constructedGradeList = new ArrayList<Grade>(5);
        String[] valuesArray = values.split("\\s+");
        String[] datesArray = generateDates(dates);

        for (int i = 0; i < valuesArray.length; ++i) {
            constructedGradeList.add(new Grade(datesArray[i], Integer.valueOf(valuesArray[i])));
        }
        return constructedGradeList;
    }

    public static String[] generateDates(String sourceDates) {
        String[] sourceDatesArray = sourceDates.split("\\s+");
        for (int i = 0; i < sourceDatesArray.length; ++i) {
            StringBuilder stringBuilder = new StringBuilder(sourceDatesArray[i]);
            stringBuilder.setCharAt(2, '-');
            int time = generateTime();
            String insertion = null;
            if (time < 10) {
                insertion = "-2021 0";
            } else {
                insertion = "-2021 ";
            }

            stringBuilder.insert(stringBuilder.length(), insertion + time + ":30:00");
            sourceDatesArray[i] = stringBuilder.toString();
        }
        return sourceDatesArray;
    }


    private static int generateTime() {
        int min = 8;
        int max = 14;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }

    public static ArrayList<Grade> assignPupilSubjectID(ArrayList<Grade> sourceList, int pupilID, int subjectID) {
        ArrayList<Grade> tempList = sourceList;
        for (Grade oneGrade : tempList) {
            oneGrade.setPupilID(pupilID);
            oneGrade.setSubjectID(subjectID);
        }
        return tempList;
    }

    public static ArrayList<Grade> assignGradeValues(String values, String[] dates, int pupilID, int subjectID) {
        ArrayList<Grade> tempGrades = new ArrayList<Grade>(5);
        String[] valuesArray = values.split("\\s+");
        for (int i = 0; i < valuesArray.length; ++i)
            tempGrades.add(new Grade(dates[i], valuesArray[i], pupilID, subjectID));

        return tempGrades;
    }

    public static ArrayList<Grade> getSubjectGrades(ArrayList<Grade> grades, String subject) {
        int subjectID = ObjectUtils.findSubjectByName(Main.schoolSubjects, subject).getSubjectID();
        ArrayList<Grade> tempGradesList = new ArrayList<Grade>(5);
        for (Grade oneGrade : grades) {
            if (oneGrade.getSubjectID() == subjectID)
                tempGradesList.add(oneGrade);
        }
        return tempGradesList;
    }

    public static Teacher findTeacherByName(ArrayList<Teacher> teachers, String teacherName) {
        for (Teacher teacher : teachers)
            if (teacher.getFullName().toLowerCase().equals(teacherName.toLowerCase()))
                return teacher;
        return null;
    }

    public static double calculateAverageForClass(ArrayList<Pupil> classPupilsList) {
        double averagePoint = 0.0;

        for (Pupil onePupil : classPupilsList)
            averagePoint += calculateAverageForPupil(onePupil);

        return averagePoint / classPupilsList.size();
    }

    public static double calculateAverageForPupil(Pupil onePupil) {
        double totalSum = 0.0;
        for (Grade oneGrade : onePupil.getPupilGrades())
            totalSum += oneGrade.getValue();
        return totalSum / onePupil.getPupilGrades().size();
    }

    public static double calculateAverageForSubject(Pupil pupil, Subject oneSubject) {
        double totalSum = 0.0;

        ArrayList<Grade> subjectGrades = getSubjectGrades(pupil.getPupilGrades(), oneSubject.getSubjectName());
        for (Grade oneGrade : subjectGrades) {
            totalSum += oneGrade.getValue();
        }
        return totalSum / subjectGrades.size();

    }

    public static String calculateTheMostPopularSubject(ArrayList<Pupil> classPupils) {
        Subject theMostPopular = null;
        double theHighestPoint = 0.0;
        double temp = 0.0;

        ArrayList<Subject> classSubjects = classPupils.get(0).getPupilClass().getClassSubjects();
        for (Subject oneSubject : classSubjects) {
            temp = 0.0;
            for (Pupil onePupil : classPupils) {
                temp += calculateAverageForSubject(onePupil, oneSubject);

            }
            temp /= classPupils.size();
            if (temp > theHighestPoint) {
                theHighestPoint = temp;
                theMostPopular = oneSubject;
            }

        }
        return theMostPopular.getSubjectName();
    }


    public static String calculateTheLeastPopularSubject(ArrayList<Pupil> classPupils) {
        Subject theLeastPopular = null;
        double theLeast = 10.0;
        double temp = 0.0;
        ArrayList<Subject> classSubjects = classPupils.get(0).getPupilClass().getClassSubjects();
        for (Subject oneSubject : classSubjects) {
            temp = 10.0;
            for (Pupil onePupil : classPupils) {
                temp += calculateAverageForSubject(onePupil, oneSubject);

            }
            temp /= classPupils.size();
            if (temp < theLeast && temp != 0) {
                theLeast = temp;
                theLeastPopular = oneSubject;
            }

        }
        return theLeastPopular.getSubjectName();
    }

    public static boolean isDuplicatePresent(ArrayList<Pupil> sourceList, Pupil pupil) {
        for (Pupil onePupil : sourceList)
            if (onePupil.getMail().toLowerCase().
                    equals(pupil.getMail().toLowerCase())
                    && onePupil.getFullName().toLowerCase().
                    equals(pupil.getFullName().toLowerCase())
                    && onePupil.getAge() == pupil.getAge())
                return true;

        return false;
    }

    public static boolean isDuplicateGradesPresent(ArrayList<Grade> sourceList, ArrayList<Grade> compareList) {
        for(Grade oneCompareGrade : compareList) {
            for(Grade oneSourceGrade : sourceList) {
                boolean datesEqual = oneCompareGrade.getDateTime().equals(oneSourceGrade.getDateTime());
                if(datesEqual) {
                    boolean valuesEqual = oneCompareGrade.getValue() == oneSourceGrade.getValue();
                    boolean subjectsEqual = oneCompareGrade.getSubjectID() == oneSourceGrade.getSubjectID();
                    boolean pupilEqual = oneCompareGrade.getPupilID() == oneSourceGrade.getPupilID();
                    if(valuesEqual && subjectsEqual && pupilEqual)
                        return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Subject> constructSubjectsList(String[] subjectsArray) {
        ArrayList<Subject> tempSubjectsList = new ArrayList<Subject>(5);
        for(String oneSubject : subjectsArray) {
            Subject tempSubject = ObjectUtils.findSubjectByName(Main.schoolSubjects, oneSubject);
            if(tempSubject != null) {
                tempSubjectsList.add(tempSubject);
            }
        }
        return tempSubjectsList;
    }

    public static ArrayList<Integer> getGradesAsInteger() {
        ArrayList<Integer> tempList = new ArrayList<Integer>(5);
        for(SchoolClass oneClass : Main.schoolClassArrayList) {
            ArrayList<Pupil> currentClassPupils = ObjectUtils.findPupilsByClass(Main.pupils, oneClass.getId());
            tempList.addAll(getClassGradesAsInteger(currentClassPupils));
        }
        return tempList;
    }

    public static ArrayList<Integer> getClassGradesAsInteger(ArrayList<Pupil> classPupils) {
        ArrayList<Integer> tempList = new ArrayList<Integer>(5);
        for (Pupil onePupil : classPupils) {
            tempList.addAll(getPupilsGradesAsIntegers(onePupil));
        }
        return tempList;
    }

    public static ArrayList<Integer> getPupilsGradesAsIntegers(Pupil onePupil) {
        ArrayList<Integer> tempList = new ArrayList<Integer>(5);
        for(Grade oneGrade : onePupil.getPupilGrades()) {
            tempList.add(oneGrade.getValue());
        }
        return tempList;
    }

    public static ArrayList<Integer> getClassPupilsSubjectGrades(ArrayList<Pupil> classPupils, Subject selectedSubject) {
        ArrayList<Integer> tempList = new ArrayList<>(5);

        for(Pupil onePupil : classPupils) {
            ArrayList<Grade> currentPupilGrades = onePupil.getPupilGrades();
            for(Grade oneGrade : ObjectUtils.getSubjectGrades(currentPupilGrades,selectedSubject.getSubjectName())) {
                tempList.add(oneGrade.getValue());
            }
        }
        return tempList;
    }

    public static double calculateAverageForClassSubject(ArrayList<Pupil> classPupils, Subject subject) {
        double average = 0.0;
        for(Pupil onePupil : classPupils) {
            average += calculateAverageForSubject(onePupil, subject);
        }
        return (double) average / classPupils.size();
    }

    public static ArrayList<Integer> getGeneralPupilsSubjectGrades(Subject selectedSubject) {
        ArrayList<Integer> generalSubjectGrades = new ArrayList<Integer>(5);

        for(SchoolClass oneClass : Main.schoolClassArrayList) {
            ArrayList<Pupil> currentClassPupils = ObjectUtils.findPupilsByClass(Main.pupils, oneClass.getId());
            generalSubjectGrades.addAll(getClassPupilsSubjectGrades(currentClassPupils, selectedSubject));
        }
        return generalSubjectGrades;
    }

}

