package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Savable;

import java.util.ArrayList;
import java.util.List;

// Reservations of dog booked from 10 to 18
public class Reservations implements Savable {
    public static int STARTOFDAYCARE = 10;
    public static int ENDOFDAY = 18;

    private List<Dog> listOfReservations;

    //EFFECTS: Instantiate a list of 18 spots
    public Reservations() {
        listOfReservations = new ArrayList<>();
        for (int i = 0; i <= 18; i++) {
            Dog d = new Dog("empty", 0, "empty");
            listOfReservations.add(i, d);
        }
    }

    //EFFECT: return true if the time spot asked for has space and time is within hours of operation
    public boolean isSpotOpen(int i) {
        if (i <= ENDOFDAY && i >= STARTOFDAYCARE) {
            Dog d = listOfReservations.get(i);
            return d.returnName() == "empty";
        }
        return false;
    }

    //EFFECTS: return false if dog is already booked
    public boolean alreadyBooked(Dog d) {
        if (listOfReservations.contains(d)) {
            return false;
        } else {
            return true;
        }
    }

    //MODIFIES: this and reservation
    //EFFECTS: if time is within hours of operation and the dog is not within list already, add into list
    public boolean addReservations(Dog d, int time) {
        if (isSpotOpen(time) && alreadyBooked(d)) {
            listOfReservations.set(time, d);
            d.setDogTime(time);
            return true;
        }
        return false;
    }

    //MODIFIES: this and Dog
    //EFFECTS: if new time is empty then set dog to new time and change dog reservation
    public boolean changeDogReservation(Dog d, int newTime) {
        Dog x = new Dog("empty", 0, "empty");
        if (isSpotOpen(newTime)) {
            int oldTime = d.returnTime();
            listOfReservations.set(oldTime, x);
            listOfReservations.set(newTime, d);
            d.setDogTime(newTime);
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: return dog name on time inputted
    public String checkMyReservation(int time) {
        Dog d = listOfReservations.get(time);
        return d.returnName();
    }

    //getter
    //EFFECTS: return dog at int i
    public Dog returnDog(int i) {
        return listOfReservations.get(i);
    }

    //getter
    //EFFECTS: return size of reservation
    public int returnSize() {
        return listOfReservations.size();
    }

    //EFFECTS: produce true if all dog in list has name empty
    public boolean reservationsEmpty() {
        int x = 0;
        for (Dog d : listOfReservations) {
            if ("empty".equals(d.returnName())) {
                x += 1;
            }
        }
        return (x == 19);
    }

    public String getDogName(int x) {
        return listOfReservations.get(x).returnName();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dogs", dogToJson());
        return json;
    }

    //EFFECTS: return dogs in reservations as jsonArray
    private JSONArray dogToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Dog d : listOfReservations) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }
}
