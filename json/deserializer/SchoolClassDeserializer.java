package sample.json.deserializer;

import com.google.gson.reflect.TypeToken;
import sample.json.interfaces.JsonDeserializable;
import sample.objects.SchoolClass;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SchoolClassDeserializer extends GeneralDeserializer implements JsonDeserializable<SchoolClass> {
    @Override
    public ArrayList<SchoolClass> deserialize(String deserializableString) {
        Type type = new TypeToken<ArrayList<SchoolClass>>(){}.getType();
        ArrayList<SchoolClass> dsSchoolClasses = SchoolClassDeserializer.getInstance().fromJson(deserializableString, type);
        return dsSchoolClasses;
    }
}
