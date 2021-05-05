package sample.json.interfaces;

import java.util.ArrayList;

public interface JsonDeserializable<T> {

    public ArrayList<T> deserialize(String deserializableString);
}
