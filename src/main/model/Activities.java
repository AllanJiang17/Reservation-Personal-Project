package model;

import java.util.ArrayList;

//Represents an list of activities with activities, a int costOfAllActivities to track all activities cost based on dog
// weight and a string weightOfAllActivities that output name of all activities that are suitable for a dog based on
// weight
public class Activities {
    private ArrayList<Activity> activities; // a list to store activities
    private int costOfAllActivities; // an int that stores the total cost of activities a dog can do
    private String weightOfAllActivities; //an string of activities name that a dog can do

    //EFFECTS: create a new list of activities and set the string of all weights to :
    public Activities() {
        activities = new ArrayList<>();
        weightOfAllActivities = ":";
    }

    //MODIFY: this
    //EFFECTS: modify the string weightOfAllActivities according to the weight of the dog
    public void returnListOfWeight(Dog d) {
        for (Activity i : activities) {
            if (i.enoughWeightForActivity(d)) {
                weightOfAllActivities = weightOfAllActivities.concat(i.returnActivityName() + ",");
            }
        }
    }

    //MODIFY: this
    //EFFECTS: modify the int costOfAllActivities according to the weight of the dog
    public void returnCostOfAllActivities(Dog d) {
        for (Activity i : activities) {
            if (i.enoughWeightForActivity(d)) {
                costOfAllActivities = costOfAllActivities + i.returnCost();
            }
        }
    }

    //MODIFY: this
    //EFFECTS: set costOfAllActivities to 0
    public void setListOfCostToZero() {
        costOfAllActivities = 0;
    }

    //MODIFY: this
    //EFFECTS: set weightOfAllActivities to ":"
    public void setListOfWeightToEmpty() {
        weightOfAllActivities = ":";
    }

    //MODIFY: this
    //EFFECTS: add an activity into list
    public void addActivity(Activity a) {
        activities.add(a);
    }

    //EFFECTS: return weightOfAllActivities
    public String getListOfActivities() {
        return weightOfAllActivities;
    }

    //EFFECTS: return costOfAllActivities
    public int getCostOfActivities() {
        return costOfAllActivities;
    }

}
