package sample.json.deserializer;

import com.google.gson.reflect.TypeToken;
import sample.json.interfaces.JsonDeserializable;
import sample.objects.Subject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SchoolSubjectsDeserializer extends GeneralDeserializer implements JsonDeserializable<Subject> {
    @Override
    public ArrayList<Subject> deserialize(String deserializableString) {
        Type type = new TypeToken<ArrayList<Subject>>(){}.getType();
        ArrayList<Subject> dsSchoolSubjects = SchoolSubjectsDeserializer.getInstance().fromJson(deserializableString, type);
        return dsSchoolSubjects;
    }
}
