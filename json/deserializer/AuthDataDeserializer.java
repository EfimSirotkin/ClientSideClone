package sample.json.deserializer;


import com.google.gson.reflect.TypeToken;
import sample.json.interfaces.JsonDeserializable;
import sample.objects.AuthData;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AuthDataDeserializer extends GeneralDeserializer implements JsonDeserializable<AuthData> {

    @Override
    public ArrayList<AuthData> deserialize(String deserializableString) {
        Type type = new TypeToken<ArrayList<AuthData>>(){}.getType();
        ArrayList<AuthData> dsGrades = GradeDeserializer.getInstance().fromJson(deserializableString, type);
        return dsGrades;
    }
}
