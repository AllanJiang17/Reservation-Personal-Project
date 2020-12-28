package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {
    Activity activity1;
    Activity activity2;
    Dog d;

    @BeforeEach
    public void setup() {
        activity1 = new Activity("Grooming", 30,10);
        activity2 = new Activity("Walking", 30, 30);
        d = new Dog("Ronny", 20, "Apple");
    }

    @Test
    public void testEnoughWeightForActivity() {
        assertTrue(activity1.enoughWeightForActivity(d));
        assertFalse(activity2.enoughWeightForActivity(d));
    }

    @Test
    public void testReturnActivityName() {
        assertEquals("Grooming", activity1.returnActivityName());
    }

    @Test
    public void testReturnCost() {
        assertEquals(30, activity1.returnCost());
    }
}
