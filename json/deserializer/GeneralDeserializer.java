package sample.json.deserializer;

import com.google.gson.Gson;

public class GeneralDeserializer {

    private static Gson jsonDeserializer;

    GeneralDeserializer() {
        jsonDeserializer = new Gson();
    }

    public static Gson getInstance()
    {
        if(jsonDeserializer == null)
            jsonDeserializer = new Gson();
        return jsonDeserializer;
    }


    public static  Gson getJsonDeserializer() {
        return jsonDeserializer;
    }

}
