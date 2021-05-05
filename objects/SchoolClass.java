package sample.objects;

import java.util.ArrayList;

public class SchoolClass {
    private int id;
    private int number;
    private String letter;
    private int numberOfPupils;
    private ArrayList<Subject> classSubjects;


    public SchoolClass() {
    }

    public SchoolClass(int id, int class_number, String class_letter, int number_of_pupils) {
        this.id = id;
        this.number = class_number;
        this.letter = class_letter;
        this.numberOfPupils = number_of_pupils;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getNumberOfPupils() {
        return numberOfPupils;
    }

    public void setNumberOfPupils(int numberOfPupils) {
        this.numberOfPupils = numberOfPupils;
    }

    public void printClass() {
        try {
            System.out.println("[ " + this.number + this.letter + " ]");
        } catch(NullPointerException e) {
            System.out.println("Seems like no class is to be printed");
        }
    }

    public ArrayList<Subject> getClassSubjects() {
        return classSubjects;
    }

    public void setClassSubjects(ArrayList<Subject> classSubjects) {
        this.classSubjects = classSubjects;
    }

    public void printClassSubjects() {
        int i = 1;
        System.out.print("---------");
        this.printClass();
        for(Subject oneSubject : this.classSubjects) {
            System.out.print(i + ")  ");
            oneSubject.printSubject();
            i++;
        }
    }
}
