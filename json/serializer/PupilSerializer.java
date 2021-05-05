package sample.json.serializer;

import sample.json.interfaces.JsonSerializable;
import sample.objects.Pupil;

import java.util.ArrayList;

public class PupilSerializer extends AbstractJsonSerializer implements JsonSerializable<Pupil> {
    @Override
    public String serialize(ArrayList<Pupil> instance) {
        String pupilsSerialized = super.getInstance().toJson(instance);
        return pupilsSerialized;
    }
}
