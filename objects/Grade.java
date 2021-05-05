package sample.objects;

public class Grade {

    private int pupilID;
    private int subjectID;
    private String dateTime;
    private int value;

    public Grade(String dateTime, int value) {
        this.dateTime = dateTime;
        this.value = value;
    }

    public Grade(String value, int pupilID, int subjectID) {
        this.subjectID = subjectID;
        this.pupilID = pupilID;
        this.value = Integer.valueOf(value);
    }
    public Grade(String datetime, String value, int pupilID, int subjectID) {
        this(value,pupilID,subjectID);
        this.dateTime = datetime;
    }

    public int getPupilID() {
        return pupilID;
    }

    public void setPupilID(int pupilID) {
        this.pupilID = pupilID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
