package sample.objects;

public class Subject {
    private String subjectName;
    private int subjectID;


    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public Subject(int subjectID, String subjectName) {
        this.subjectName = subjectName;
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void printSubject()
    {
        System.out.println("id: " + this.subjectID +  " " + this.subjectName);
    }
}
