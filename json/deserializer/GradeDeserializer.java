package sample.json.deserializer;

import com.google.gson.reflect.TypeToken;
import sample.json.interfaces.JsonDeserializable;
import sample.objects.Grade;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GradeDeserializer extends GeneralDeserializer implements JsonDeserializable<Grade> {
    @Override
    public ArrayList<Grade> deserialize(String deserializableString) {
        Type type = new TypeToken<ArrayList<Grade>>(){}.getType();
        ArrayList<Grade> dsGrades = GradeDeserializer.getInstance().fromJson(deserializableString, type);
        return dsGrades;
    }
}
