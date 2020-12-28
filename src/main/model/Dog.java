package model;

import exceptions.WeightException;
import org.json.JSONObject;
import persistence.Savable;

// Represents a dog with its name, weight, food and reservation time
public class Dog implements Savable {
    private String name; // tracks the dog name
    private int weight; // tracks the dog weight
    private String food; // tracks the dog food
    private int reservation; // 0 means no reservation set, reservation can only be from 10 am to 18 pm

    //EFFECTS: instantiate a dog object with its name, weight, food of preference and no reservation time
    public Dog(String name, int weight, String food) {
        this.name = name;
        this.weight = weight;
        this.food = food;
        reservation = 0;
    }

    //MODIFIES: this
    //EFFECTS: if new time is not same time as old one, switch old time to new one
    public void setDogTime(int newTime) {
        reservation = newTime;
    }

    //MODIFIES: this
    //EFFECTS: set dog weight to given int
    public void setDogWeight(int newWeight) throws WeightException {
        if (newWeight < 10 || newWeight > 200) {
            throw new WeightException();
        } else {
            this.weight = newWeight;
        }
    }

    //MODIFIES: this
    //EFFECTS: set dog name to given string
    public void setDogName(String newName) {
        name = newName;
    }

    //MODIFIES: this
    //EFFECTS: set dog food to given string
    public void setDogFood(String newFood) {
        food = newFood;
    }

    //EFFECTS: returns the name of dog
    public String returnName() {
        return name;
    }

    //EFFECTS: returns the weight of dog
    public int returnWeight() {
        return weight;
    }

    //EFFECTS: returns the reservation time
    public int returnTime() {
        return reservation;
    }

    //EFFECTS: returns the reservation time
    public String returnFood() {
        return food;
    }

    //EFFECTS: returns a string of the dog info
    public String returnDogInfo() {
        String x = "Name:" + name;
        String y = Integer.toString(weight);
        String z = Integer.toString(reservation);
        return x.concat(", " + "Weight: " + y + ", " + "Food: " + food + ", " + "Reservation: " + z);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("weight", weight);
        json.put("food", food);
        json.put("reservation", reservation);
        return json;
    }
}
