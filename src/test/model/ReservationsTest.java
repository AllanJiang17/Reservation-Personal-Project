package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationsTest {
    Reservations rs;
    Dog d;
    Dog s;

    @BeforeEach
    public void setup() {
        rs = new Reservations();
        d = new Dog("Ronny", 20, "Apple");
        s = new Dog("Tina", 10, "Noodle");
    }

    @Test
    public void testConstructor() {
        assertEquals(19, rs.returnSize());
    }

    @Test
    public void testIsSpotOpen() {
        assertTrue(rs.isSpotOpen(10));
        assertFalse(rs.isSpotOpen(1));
        assertFalse(rs.isSpotOpen(19));
    }

    @Test
    public void TestAddReservation() {
        assertTrue(rs.addReservations(d, 10));
        assertFalse(rs.addReservations(d, 10));
        assertFalse(rs.addReservations(d, 11));
        assertFalse(rs.isSpotOpen(10));
        assertEquals(d, rs.returnDog(10));
    }

    @Test
    public void TestChangeDogReservation() {
        assertTrue(rs.addReservations(d,10));
        assertTrue(rs.changeDogReservation(d, 11));
        assertEquals(d, rs.returnDog(11));
        assertTrue(rs.isSpotOpen(10));
        assertTrue(rs.addReservations(s,12));
        assertFalse(rs.changeDogReservation(d,12));
    }

    @Test
    public void TestCheckMyReservation() {
        assertTrue(rs.addReservations(d,10));
        assertEquals("Ronny", rs.checkMyReservation(10));
    }

    @Test
    public void TestAlreadyBooked() {
        assertTrue(rs.alreadyBooked(d));
        assertTrue(rs.addReservations(d,10));
        assertFalse(rs.alreadyBooked(d));
    }

    @Test
    public void TestEmptySchedule() {
        assertTrue(rs.reservationsEmpty());
    }

    @Test
    public void TestNotEmptySchedule() {
        assertTrue(rs.addReservations(d,10));
        assertFalse(rs.reservationsEmpty());
    }

    @Test
    public void TestGetDogName() {
        assertTrue(rs.addReservations(d,10));
        assertEquals("Ronny", rs.getDogName(10));
    }
}
