package sample.objects;

import java.util.ArrayList;

public class Pupil extends Person{

    private ArrayList<Grade> pupilGrades;
    private SchoolClass pupilClass;
    private int teacherID;

    public Pupil(int id, String fullName, String mail, int age, int sex, ArrayList<Grade> pupilGrades, SchoolClass pupilClass) {
        super(id,fullName,mail,age,sex);
        this.pupilGrades = pupilGrades;
        this.pupilClass = pupilClass;
    }
    public Pupil(int id, String fullName, String mail, int age, int sex) {
        super(id,fullName,mail,age,sex);
        this.pupilGrades = new ArrayList<>(5);
    }

    public void setPupilGrades(ArrayList<Grade> pupilGrades) {
        if(this.pupilGrades.size() == 0)
            this.pupilGrades = pupilGrades;
        else
            this.pupilGrades.addAll(pupilGrades);
    }

    public ArrayList<Grade> getPupilGrades() {
        return pupilGrades;
    }

    public SchoolClass getPupilClass() {
        return pupilClass;
    }

    public void setPupilClass(SchoolClass pupilClass) {
        this.pupilClass = pupilClass;
    }

    public void printPupil()
    {
        System.out.println("--------------");
        super.printPerson();
        System.out.println("Учится в: " + + this.getPupilClass().getNumber() + " " + this.getPupilClass().getLetter());
        System.out.print("Оценки: ");
        for(Grade grade : this.getPupilGrades())
            System.out.print(" " + grade.getValue() + " [" + grade.getDateTime() + "]");
        System.out.println();
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public void printPupilGrades() {
        System.out.print(this.getFullName() + " : ");
        for(Grade grade : pupilGrades)
            System.out.print(" [" + grade.getSubjectID() + ":" + grade.getValue() + "--" + grade.getDateTime()+ "]");
        System.out.println();
    }
}
