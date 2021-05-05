package sample.json.interfaces;

import java.util.ArrayList;

public interface JsonSerializable<T> {
    public String serialize(ArrayList<T> instance);
}
