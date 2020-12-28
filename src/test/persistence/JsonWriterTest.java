package persistence;

import model.Dog;
import model.Reservations;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterBadFile() {
        try {
            Reservations r = new Reservations();
            JsonWriter writer = new JsonWriter("./data/my\10:fileName.json");
            writer.open();
            fail("Exception should have been thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterWithEmptyReservation() {
        try {
            Reservations r = new Reservations();
            JsonWriter writer = new JsonWriter("./data/testWriterWithEmptyReservation.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWithEmptyReservation.json");
            r = reader.read();
            assertEquals(19, r.returnSize());
            assertTrue(r.reservationsEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterWithOneReservation() {
        try {
            Reservations r = new Reservations();
            Dog d = new Dog("Apple", 10, "carrot");
            r.addReservations(d, 10);
            JsonWriter writer = new JsonWriter("./data/testWriterWithOneReservation.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWithOneReservation.json");
            r = reader.read();
            assertEquals("Apple", r.checkMyReservation(10));
            checkDog("Apple", 10, "carrot", 10, d);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
