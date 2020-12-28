package persistence;

import model.Dog;
import model.Reservations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Code understood from the JsonSerializationDemo
// A reader that reads reservations from JSON object stored
public class JsonReader {
    private String source;

    // EFFECTS: creates reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads reservation from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Reservations read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return oldReservations(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder content = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> content.append(s));
        }

        return content.toString();
    }

    // EFFECTS: read old reservation from JSON object and returns it
    private Reservations oldReservations(JSONObject jsonObject) {
        Reservations oldList = new Reservations();
        addDogsIntoList(oldList, jsonObject);
        return oldList;
    }

    // MODIFIES: oldList
    // EFFECTS: parses dogs from JSON object and adds them to reservation
    private void addDogsIntoList(Reservations oldList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("dogs");
        for (Object json : jsonArray) {
            JSONObject newDog = (JSONObject) json;
            addDoggy(oldList, newDog);
        }
    }

    // MODIFIES: oldList
    // EFFECTS: parses dog from JSON object and adds it to reservation
    private void addDoggy(Reservations oldList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int weight = jsonObject.getInt("weight");
        String food = jsonObject.getString("food");
        int reservation = jsonObject.getInt("reservation");
        Dog dog = new Dog(name, weight, food);
        dog.setDogTime(reservation);
        oldList.addReservations(dog, reservation);
    }
}
