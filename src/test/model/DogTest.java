package model;

import exceptions.WeightException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {
    Dog d;

    @BeforeEach
    public void setup() {
        d = new Dog("Ronny", 20, "Apple");
    }

    @Test
    public void testSetDogTime() {
        d.setDogTime(11);
        assertEquals(11,d.returnTime());
    }

    @Test
    public void testSetWeightWorks() {
        try {
            d.setDogWeight(30);
        } catch (WeightException e) {
            fail();
        }
        assertEquals(30,d.returnWeight());

    }

    @Test
    public void testSetWeightUnder10() {
        try {
            d.setDogWeight(9);
            fail("WeightException should been thrown");
        } catch (WeightException e) {
            //expected
        }
    }

    @Test
    public void testSetWeightOver200() {
        try {
            d.setDogWeight(210);
            fail("WeightException should been thrown");
        } catch (WeightException e) {
            //expected
        }
    }

    @Test
    public void testSetName() {
        d.setDogName("Tim");
        assertEquals("Tim", d.returnName());
    }

    @Test
    public void testReturnDogInfo() {
        d.setDogTime(11);
        d.setDogFood("Pear");
        assertEquals("Name:Ronny, Weight: 20, Food: Pear, Reservation: 11", d.returnDogInfo());
    }
}