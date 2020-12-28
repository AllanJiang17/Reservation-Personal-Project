package persistence;

import org.json.JSONObject;

//Studied from JsonSerializationDemo
public interface Savable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
