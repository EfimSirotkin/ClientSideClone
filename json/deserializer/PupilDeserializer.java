package sample.json.deserializer;

import com.google.gson.reflect.TypeToken;
import sample.json.interfaces.JsonDeserializable;
import sample.objects.Pupil;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PupilDeserializer extends GeneralDeserializer implements JsonDeserializable<Pupil> {
    @Override
    public ArrayList<Pupil> deserialize(String deserializableString) {
        Type type = new TypeToken<ArrayList<Pupil>>(){}.getType();
        ArrayList<Pupil> dsPupils = PupilDeserializer.getInstance().fromJson(deserializableString, type);

        return dsPupils;
    }
}
