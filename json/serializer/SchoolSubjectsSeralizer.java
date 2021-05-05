package sample.json.serializer;

import sample.json.interfaces.JsonSerializable;
import sample.objects.Subject;

import java.util.ArrayList;

public class SchoolSubjectsSeralizer extends AbstractJsonSerializer implements JsonSerializable<Subject> {
    @Override
    public String serialize(ArrayList<Subject> instance) {
        String serializedSubjects = super.getInstance().toJson(instance);
        return serializedSubjects;
    }
}
