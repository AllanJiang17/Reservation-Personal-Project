package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivitiesTest {
    Activity activity1;
    Activity activity2;
    Activity activity3;
    Dog d;
    Activities listOfActivities;

    @BeforeEach
    public void setup() {
        activity1 = new Activity("Grooming", 30,10);
        activity2 = new Activity("Walking",  10, 20);
        activity3 = new Activity("Fetch", 5, 30);
        d = new Dog("Ronny", 20, "Apple");
        listOfActivities = new Activities();
        listOfActivities.addActivity(activity1);
        listOfActivities.addActivity(activity2);
        listOfActivities.addActivity(activity3);
    }

    @Test
    public void testCostOfAllActivities() {
        listOfActivities.returnCostOfAllActivities(d);
        assertEquals(40, listOfActivities.getCostOfActivities());
        listOfActivities.setListOfCostToZero();
        assertEquals(0, listOfActivities.getCostOfActivities());
    }

    @Test
    public void testListOfWeight() {
        listOfActivities.returnListOfWeight(d);
        assertEquals(":Grooming,Walking,", listOfActivities.getListOfActivities());
        listOfActivities.setListOfWeightToEmpty();
        String y = listOfActivities.getListOfActivities();
        assertEquals(":", y);
    }

}
