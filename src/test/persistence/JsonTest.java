package persistence;

import model.Dog;
import model.Reservations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkDog(String name, int weight, String food, int reservation, Dog dog) {
        assertEquals(name, dog.returnName());
        assertEquals(weight, dog.returnWeight());
        assertEquals(food, dog.returnFood());
        assertEquals(reservation, dog.returnTime());
    }

}
