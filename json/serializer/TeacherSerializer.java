package sample.json.serializer;

import sample.json.interfaces.JsonSerializable;
import sample.objects.Teacher;

import java.util.ArrayList;

public class TeacherSerializer extends AbstractJsonSerializer implements JsonSerializable<Teacher> {
    @Override
    public String serialize(ArrayList<Teacher> instance) {
        String teachersSerialized = super.getInstance().toJson(instance);
        return teachersSerialized;
    }
}
