package sample.http;

import java.net.URI;
import java.net.http.HttpRequest;

public class PostGenerator extends RequestGenerator {

    private final String postTeachers = "post-teachers";
    private final String postGrades = "post-grades";
    private final String postPupils = "post-pupils";
    private final String postSchoolClasses = "post-schoolclasses";
    private final String postSubjects = "post-subjects";

    public PostGenerator() {}

    public String postTeachers(String serializedTeachers) {
        buildPostRequest(serverIPport,postTeachers, serializedTeachers);
        return getResponseResults();
    }

    public String postGrades(String serializedGrades) {
        buildPostRequest(serverIPport, postGrades, serializedGrades);
        return getResponseResults();
    }

    public String postPupils(String serializedPupils) {
        buildPostRequest(serverIPport, postPupils, serializedPupils);
        return getResponseResults();
    }

    public String postSchoolClasses(String serializedClasses) {
        buildPostRequest(serverIPport, postSchoolClasses, serializedClasses);
        return getResponseResults();
    }

    public String postSubjects(String serializedSubjects) {
        buildPostRequest(serverIPport, postSubjects, serializedSubjects);
        return getResponseResults();
    }


    public void buildPostRequest(String serverIPport, String type, String serializedObjects)
    {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + serverIPport + "/" + type))
                .POST(HttpRequest.BodyPublishers.ofString(serializedObjects))
                .build();
    }
}
