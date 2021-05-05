package sample.http;

import org.w3c.dom.html.HTMLTableCaptionElement;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;


public class GetGenerator extends RequestGenerator{

    private final String getTeachers = "get-teachers";
    private final String getPupils = "get-pupils";
    private final String getGrades = "get-grades";
    private final String getSchoolClasses = "get-schoolclasses";
    private final String getSubjects = "get-subjects";

    public GetGenerator(HttpRequest customRequest) {
        request = customRequest;
    }

    public GetGenerator() {}

    public String queryTeachers() {
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + serverIPport + "/" + getTeachers))
                .build();
        return getResponseResults();
    }

    public String queryPupils() {
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + serverIPport + "/" + getPupils))
                .build();
        return getResponseResults();
    }

    public String queryGrades() {
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + serverIPport + "/" + getGrades))
                .build();
        return getResponseResults();
    }

    public String querySchoolClasses() {
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + serverIPport + "/" + getSchoolClasses))
                .build();
        return getResponseResults();
    }

    public String querySubjects() {
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + serverIPport + "/" + getSubjects))
                .build();
        return getResponseResults();
    }




}
