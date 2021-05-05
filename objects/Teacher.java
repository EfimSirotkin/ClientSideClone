package sample.objects;

import java.util.ArrayList;

public class Teacher extends Person {


    private String qualification;
    private ArrayList<Subject> subjects;


    public Teacher(int id, String fullName, String mail, int age, int sex, String qualification) {
        super(id,fullName,mail,age,sex);
        this.qualification = qualification;
    }

    public Teacher(int id, String fullName, String mail, int age, int sex, String qualification, ArrayList<Subject> subjects) {
        super(id,fullName,mail,age,sex);
        this.qualification = qualification;
        this.subjects = subjects;
    }


    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }


    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void printTeacher() {
        System.out.println("----------------");
        super.printPerson();
        System.out.println("Имеет: " + qualification + " квалификацию");
        System.out.print("Ведёт предметы: ");
        for(Subject teachingSubject: this.getSubjects())
            System.out.print(teachingSubject.getSubjectName() + " ");
        System.out.println();
    }
}