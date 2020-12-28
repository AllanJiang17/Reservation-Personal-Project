package persistence;

import model.Dog;
import model.Reservations;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReadNoFile() {
        JsonReader read = new JsonReader("./data/noFile.json");
        try {
            Reservations r = read.read();
            fail("Exception should have been thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReadWithEmptyReservation() {
        JsonReader read = new JsonReader("./data/testReadWithNoReservation.json");
        try {
            Reservations s = read.read();
            assertEquals(19, s.returnSize());
            assertTrue(s.reservationsEmpty());
        } catch (IOException e) {
            fail("no files read");
        }
    }

    @Test
    void testReadWithOneReservation() {
        JsonReader read = new JsonReader("./data/testReadWithOneReservation.json");
        try {
            Reservations r = read.read();
            Dog d = r.returnDog(11);
            checkDog("apple", 80, "Carrot", 11, d);
        } catch (IOException e) {
            fail("no files read");
        }
    }

}
