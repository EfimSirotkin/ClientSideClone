package sample.json.deserializer;

import com.google.gson.reflect.TypeToken;
import sample.json.interfaces.JsonDeserializable;
import sample.objects.Teacher;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TeacherDeserializer extends GeneralDeserializer implements JsonDeserializable<Teacher> {
    @Override
    public ArrayList<Teacher> deserialize(String deserializableString) {
        Type type = new TypeToken<ArrayList<Teacher>>(){}.getType();
        ArrayList<Teacher> dsSchoolTeachers = TeacherDeserializer.getInstance().fromJson(deserializableString, type);
        return dsSchoolTeachers;
    }
}
