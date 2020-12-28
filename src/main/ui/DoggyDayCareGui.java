package ui;

import exceptions.WeightException;
import model.Activities;
import model.Activity;
import model.Dog;
import model.Reservations;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import ui.panels.*;

import javax.swing.*;
import java.io.*;

// Code understood from the JsonSerializationDemo and Simple Drawing Player
// Represents the main doggy day care GUI application
public class DoggyDayCareGui extends JFrame {
    private JFrame mainMenu;
    private static final String JSONSTORE = "./data/reservations.json";
    private Dog newDog;
    private Reservations reservations;
    private Activities listOfActivities;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
    private JPanel makeDogPanel;
    private JPanel seeSchedulePanel;
    private JPanel mainMenuPanel;
    private JPanel addDogPanel;
    private JPanel modifyPanel;
    private JPanel retrieveDog;

    //EFFECTS: start the mainMenu frame and set the layout for the frame and add the mainMenuPanel to the frame then
    //create sound
    public DoggyDayCareGui() {
        mainMenu = new JFrame();
        init();
        mainMenu.setTitle("DoggyDayCare");
        mainMenu.setBounds(500, 200, WIDTH, HEIGHT);
        mainMenu.setVisible(true);
        mainMenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenuPanel = new MainMenuPanel(this);
        mainMenu.add(mainMenuPanel);
        makeSound();
    }

    // MODIFIES: this
    // EFFECTS: initializes some test dogs, input, a newDog field, reservations field, initializes some activities
    // and add them in reservations
    private void init() {
        reservations = new Reservations();
        Dog dog1 = new Dog("testCase1", 80, "Carrot");
        Dog dog2 = new Dog("testCase2", 20, "Apple");
        Dog dog3 = new Dog("testCase3", 20, "Apple");
        reservations.addReservations(dog1, 11);
        reservations.addReservations(dog2, 12);
        reservations.addReservations(dog3, 13);
        Activity walking = new Activity("Walking", 5, 15);
        Activity grooming = new Activity("Grooming", 40, 20);
        Activity throwAndFetch = new Activity("Throw and Fetch", 10, 60);
        Activity swimming = new Activity("Swimming", 60, 50);
        Activity playTime = new Activity("Play Time ", 20, 80);
        listOfActivities = new Activities();
        listOfActivities.addActivity(walking);
        listOfActivities.addActivity(grooming);
        listOfActivities.addActivity(throwAndFetch);
        listOfActivities.addActivity(swimming);
        listOfActivities.addActivity(playTime);
        jsonReader = new JsonReader(JSONSTORE);
        jsonWriter = new JsonWriter(JSONSTORE);
    }

    //Code understood from https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java
    // -calculator
    //MODIFIES: this
    //EFFECTS: creates InputStream sound file and play that file otherwise throw error exception
    private void makeSound() {
        String soundFile = "./data/dogAudio.wav";
        InputStream sound;
        try {
            sound = new FileInputStream(new File(soundFile));
            AudioStream audioStream = new AudioStream(sound);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
        }
    }

    //MODIFIES: this
    //EFFECTS: close mainMenuPanel and adds new makeDogPanel
    public void openDogPanel() {
        mainMenu.remove(mainMenuPanel);
        mainMenu.repaint();
        makeDogPanel = new MakeDogPanel(this);
        mainMenu.add(makeDogPanel);
    }

    //MODIFIES: this
    //EFFECTS: close mainMenuPanel and adds new ReservationTablePanel
    public void openSchedulePanel() {
        mainMenu.remove(mainMenuPanel);
        mainMenu.repaint();
        seeSchedulePanel = new ReservationTablePanel(this, reservations);
        mainMenu.add(seeSchedulePanel);
        mainMenu.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: close mainMenuPanel and adds new modifyReservationPanel
    public void openModifyPanel() {
        mainMenu.remove(mainMenuPanel);
        mainMenu.repaint();
        modifyPanel = new ModifyReservationPanel(this, reservations);
        mainMenu.add(modifyPanel);
        mainMenu.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: close mainMenuPanel and adds new retrieveDogInfoPanel
    public void openRetrieveDogPanel() {
        mainMenu.remove(mainMenuPanel);
        mainMenu.repaint();
        retrieveDog = new RetrieveDogInfoPanel(this, reservations, newDog, listOfActivities);
        mainMenu.add(retrieveDog);
        mainMenu.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: updates the newDog field with user input received from the makeDogPanel, remove the makeDogPanel and
    //add addDogPanel
    public void madeDog(Dog dog) {
        mainMenu.remove(makeDogPanel);
        mainMenu.repaint();
        newDog = dog;
        mainMenu.repaint();
        addDogPanel = new AddDogPanel(this, reservations);
        mainMenu.add(addDogPanel);
    }

    //MODIFIES: this
    //EFFECTS: removes addDogPanel and add newDog to user input time received from addDogPanel and add mainMenuPanel
    public void addDog(int time) {
        mainMenu.remove(addDogPanel);
        mainMenu.repaint();
        mainMenu.add(mainMenuPanel);
        reservations.addReservations(newDog, time);
    }

    //MODIFIES: this
    //EFFECTS: updates the reservations with the newDog and new user input time from modifyPanel
    public void modifyDog(int time) {
        reservations.changeDogReservation(newDog, time);
    }

    //MODIFIES: this
    //EFFECTS: updates the newDog with dog object got from user input time of the reservations list
    public void retrievedDog(int intTime) {
        newDog = reservations.returnDog(intTime);
    }

    //MODIFIES: this
    //EFFECTS: saves schedule
    public void saveSchedule() {
        try {
            jsonWriter.open();
            jsonWriter.write(reservations);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error in saving file to " + JSONSTORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads reservation from file
    public void loadSchedule() {
        try {
            reservations = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Error in loading file from " + JSONSTORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: return from seeSchedulePanel and add mainMenuPanel
    public void returnMainMenu() {
        mainMenu.remove(seeSchedulePanel);
        mainMenu.repaint();
        mainMenu.add(mainMenuPanel);
    }

    //MODIFIES: this
    //EFFECTS: return from makeDogPanel and add mainMenuPanel
    public void returnFromMakeDog() {
        mainMenu.remove(makeDogPanel);
        mainMenu.repaint();
        mainMenu.add(mainMenuPanel);
    }

    //MODIFIES: this
    //EFFECTS: return from addDogPanel and add mainMenuPanel
    public void returnFromAddDog() {
        mainMenu.remove(addDogPanel);
        mainMenu.repaint();
        mainMenu.add(makeDogPanel);
    }

    //MODIFIES: this
    //EFFECTS: return from modifyPanel and add mainMenuPanel
    public void returnFromModifyDog() {
        mainMenu.remove(modifyPanel);
        mainMenu.repaint();
        mainMenu.add(mainMenuPanel);
    }

    //MODIFIES: this
    //EFFECTS: return from retrieveDog and add mainMenuPanel
    public void returnFromRetrieveDog() {
        mainMenu.remove(retrieveDog);
        mainMenu.repaint();
        mainMenu.add(mainMenuPanel);
    }

    //MODIFIES: this
    //EFFECTS: return from modifyPanel, creates new ModifyReservationPanel with updated reservations and add that
    //modifyPanel
    public void refreshModifyDog() {
        mainMenu.remove(modifyPanel);
        modifyPanel = new ModifyReservationPanel(this, reservations);
        mainMenu.add(modifyPanel);
        mainMenu.setVisible(true);
    }
}
