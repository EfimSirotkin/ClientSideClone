package sample.json.serializer;

import sample.json.interfaces.JsonSerializable;
import sample.objects.Grade;

import java.util.ArrayList;

public class GradeSerializer extends AbstractJsonSerializer implements JsonSerializable<Grade> {
    @Override
    public String serialize(ArrayList<Grade> instance) {
        String gradesSerialized = super.getInstance().toJson(instance);
        return gradesSerialized;
    }
}
