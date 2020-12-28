package model;

import java.util.ArrayList;
import java.util.List;

//Represents an activity with its name, cost and weightMinimum that tracks if a dog can do this activity or not
public class Activity {
    private String name; // name of activity
    private int cost; // cost of activity
    private int weightMinimum; // weight minimum of this activity

    //EFFECTS: creates an activity with its name, cost and weight minimum
    public Activity(String name, int cost, int weightMinimum) {
        this.name = name;
        this.cost = cost;
        this.weightMinimum = weightMinimum;
    }

    //EFFECTS: return true if the activity is suitable for the weight of the dog
    public boolean enoughWeightForActivity(Dog d) {
        return d.returnWeight() >= weightMinimum;
    }

    //EFFECTS: return the activity name
    public String returnActivityName() {
        return name;
    }

    //EFFECTS: returns the cost of activity
    public int returnCost() {
        return cost;
    }


}
