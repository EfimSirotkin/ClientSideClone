package sample.json.serializer;

import sample.json.interfaces.JsonSerializable;
import sample.objects.SchoolClass;

import java.util.ArrayList;

public class SchoolClassSerializer extends AbstractJsonSerializer implements JsonSerializable<SchoolClass> {
    @Override
    public String serialize(ArrayList<SchoolClass> instance) {
        String schoolClassSerialized = super.getInstance().toJson(instance);
        return schoolClassSerialized;
    }
}
