package ui;

import exceptions.WeightException;
import model.Activities;
import model.Activity;
import model.Dog;
import model.Reservations;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Code understood from the JsonSerializationDemo and TellerApp
// Represents the doggy daycare application
public class DoggyDayCareApp {
    private static final String JSONSTORE = "./data/reservations.json";
    private Dog newDog;
    private Reservations reservations;
    private Scanner input;
    private Activities listOfActivities;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    //EFFECTS: runs the daycare application
    public DoggyDayCareApp() {
        runDayCare();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDayCare() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            showMenu();
            command = input.next();
            input.nextLine();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nHope to see you and your doggy again next time!");
    }

    // MODIFIES: this
    // EFFECTS: initializes some test dogs, input, a newDog field, reservations field, initializes some activities
    // and add them in reservations
    private void init() {
        input = new Scanner(System.in);
        newDog = new Dog("dogName", 0, "dogFood");
        reservations = new Reservations();
        Dog dog1 = new Dog("testCase1", 80, "Carrot");
        Dog dog2 = new Dog("testCase2", 20, "Apple");
        reservations.addReservations(dog1, 11);
        reservations.addReservations(dog2, 12);
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

    // EFFECTS: displays menu of options to user
    private void showMenu() {
        System.out.println("\nWelcome to Doggy DayCare, Please enter dog info if this is first time visiting:");
        System.out.println("\t1 -> Add Dog Information");
        System.out.println("\t2 -> Update Doggy Information");
        System.out.println("\t3 -> Create reservation for your dog");
        System.out.println("\t4 -> Modify Booking");
        System.out.println("\t5 -> See Reservation Schedule");
        System.out.println("\t6 -> Retrieve Doggy Info");
        System.out.println("\t7 -> save Reservations");
        System.out.println("\t8 -> load Reservations");
        System.out.println("\te -> exit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        System.out.println("Please input a number from 1 to 8 or exit with e");
        if (command.equals("1")) {
            addDogInfo();
        } else if (command.equals("2")) {
            wrongDogInfoCommand();
        } else if (command.equals("3")) {
            createBooking();
        } else if (command.equals("4")) {
            modifyReservation();
        } else if (command.equals("5")) {
            seeReservation();
        } else if (command.equals("6")) {
            retrieveDoggyInfo();
        } else if (command.equals("7")) {
            saveReservations();
        } else if (command.equals("8")) {
            loadReservations();
        }
    }

    //MODIFIES: this
    //EFFECTS: update newDog with doggy info and output its info
    private void addDogInfo() {
        createDogName();
        createDogWeight();
        createDogFood();
        String dogCompleteInfo = newDog.returnDogInfo();
        System.out.println("\nConfirm entry:" + dogCompleteInfo);
    }

    //MODIFIES: this
    //EFFECTS: ask for dog name, update the name
    private void createDogName() {
        System.out.println("\nPlease enter dog's name");
        String dogName = input.nextLine();
        newDog.setDogName(dogName);
    }

    //MODIFIES: this
    //EFFECTS: ask for dog weight, update the weight
    private void createDogWeight() {
        System.out.println("\nPlease enter dog's weight");
        int dogWeight = input.nextInt();
        try {
            newDog.setDogWeight(dogWeight);
        } catch (WeightException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        input.nextLine();
    }

    //MODIFIES: this
    //EFFECTS: ask for dog food, update the food
    private void createDogFood() {
        System.out.println("\nPlease enter dog's food");
        String dogFood = input.nextLine();
        newDog.setDogFood(dogFood);
    }

    // MODIFIES: this
    // EFFECTS: processes user command for wrong dog info command in main menu
    private void wrongDogInfoCommand() {
        boolean keepGoing = true;
        while (keepGoing) {
            wrongDogInfoOption();
            String wrongInput = input.nextLine();
            switch (wrongInput) {
                case "n":
                    wrongName();
                    break;
                case "w":
                    wrongWeight();
                    break;
                case "f":
                    wrongFood();
                    break;
                case "c":
                    keepGoing = false;
                    break;
            }
        }
    }

    // EFFECTS: displays menu of wrong dog options to user
    private void wrongDogInfoOption() {
        System.out.println("\nSelect error made:");
        System.out.println("\tn -> Change name");
        System.out.println("\tw -> Change weight");
        System.out.println("\tf -> Change food");
        System.out.println("\tc -> Return to Main Menu");
    }

    //MODIFIES: this
    //EFFECTS: ask for dog name, update the dog name and print out complete and corrected info.
    private void wrongName() {
        System.out.println("\nPlease enter dog's name");
        String correctName = input.nextLine();
        newDog.setDogName(correctName);
        String dogCompleteInfo = newDog.returnDogInfo();
        System.out.println("\nConfirm entry:" + dogCompleteInfo);
    }

    //MODIFIES: this
    //EFFECTS: ask for dog weight, update the dog weight and print out complete and corrected info.
    private void wrongWeight() {
        System.out.println("\nPlease enter dog's weight");
        int correctWeight = input.nextInt();
        try {
            newDog.setDogWeight(correctWeight);
        } catch (WeightException e) {
            System.out.println(e.getMessage());
        }
        String dogCompleteInfo = newDog.returnDogInfo();
        System.out.println("\nConfirm entry:" + dogCompleteInfo);
    }

    //MODIFIES: this
    //EFFECTS: ask for dog food, update the dog food and print out complete and corrected info.
    private void wrongFood() {
        System.out.println("\nPlease enter dog's food");
        String correctFood = input.nextLine();
        newDog.setDogFood(correctFood);
        String dogCompleteInfo = newDog.returnDogInfo();
        System.out.println("\nConfirm entry:" + dogCompleteInfo);
    }

    //MODIFIES: this
    //EFFECTS: ask for reservation time. If dog is not booked already and time is not full then
    // update the newDog to the inputted time and print successful booking.
    private void createBooking() {
        System.out.println("\nPlease Enter Reservation Time");
        System.out.println("\nOur Hours of Operation: 10am to 18pm");
        int reservationTime = input.nextInt();
        String y = Integer.toString(reservationTime);
        if (!reservations.alreadyBooked(newDog)) {
            System.out.println("Your dog has already been booked once but you can still modify the reservation");
        } else if (!reservations.isSpotOpen(reservationTime)) {
            System.out.println("\nSpot is full or Time Entry is not within hours of operation");
        } else {
            reservations.addReservations(newDog, reservationTime);
            System.out.println("Your dog has been successfully booked at " + y);
        }
    }

    //MODIFIES: this
    //EFFECTS: ask for new booking, remove old booking and update new booking time if time is within
    // requirements. Then print out successful booking.
    private void modifyReservation() {
        System.out.println("\nPlease Enter New Reservation Time.");
        System.out.println("\nOur Hours of Operation: 10am to 18pm");
        int newReservationTime = input.nextInt();
        String y = Integer.toString(newReservationTime);
        if (!reservations.isSpotOpen(newReservationTime)) {
            System.out.println("\nSpot is full or Time Entry is not within hours of operation");
        } else {
            reservations.changeDogReservation(newDog, newReservationTime);
            System.out.println("Your dog has been successfully booked at " + y);
        }
    }

    //EFFECTS: return a schedule of operation hours and print the dog name if booked
    private void seeReservation() {
        for (int i = Reservations.STARTOFDAYCARE; i <= Reservations.ENDOFDAY; i++) {
            Dog d = reservations.returnDog(i);
            System.out.print("\n-----------------------------------");
            if (d.returnName() != "empty") {
                String x = d.returnName();
                System.out.print("\n" + " " + i + "hrs: " + x + " ");
            } else {
                System.out.print("\n" + " " + i + "hrs: ");
                System.out.println(" available ");
            }
            System.out.print("\n-----------------------------------");
        }
    }

    //EFFECTS: output doggy info like name, weight, food, list of activities and cost based on time
    private void retrieveDoggyInfo() {
        System.out.print("---------------------------------------------");
        System.out.print("\n Please Enter Reservation Time to Get:");
        System.out.print("\n Your Doggy basic info");
        System.out.print("\n List of Activities");
        System.out.print("\n Cost");
        System.out.print("\n-------------------------------------------");
        int time = input.nextInt();
        Dog doggy = reservations.returnDog(time);
        String x = doggy.returnDogInfo();
        System.out.print("\n" + x);
        retrieveActivities(doggy);
        retrieveCost(doggy);
        System.out.print("\n-------------------------------------------");
    }

    //EFFECTS: get doggy activities based on the doggy weight
    private void retrieveActivities(Dog doggy) {
        listOfActivities.returnListOfWeight(doggy);
        System.out.print("\n List of Activities:");
        System.out.print("\n" + listOfActivities.getListOfActivities());
        listOfActivities.setListOfWeightToEmpty();
    }

    //EFFECTS: get doggy session cost based on dog weights and activities done
    private void retrieveCost(Dog doggy) {
        listOfActivities.returnCostOfAllActivities(doggy);
        System.out.print("\n Cost:");
        System.out.print("\n" + listOfActivities.getCostOfActivities());
        listOfActivities.setListOfCostToZero();
    }

    // EFFECTS: saves reservation to file
    private void saveReservations() {
        try {
            jsonWriter.open();
            jsonWriter.write(reservations);
            jsonWriter.close();
            System.out.println("Saved reservations" + " to " + JSONSTORE);
        } catch (FileNotFoundException e) {
            System.out.println("Error in saving file to " + JSONSTORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads reservation from file
    private void loadReservations() {
        try {
            reservations = jsonReader.read();
            System.out.println("Loaded reservations from " + JSONSTORE);
        } catch (IOException e) {
            System.out.println("Error in loading file from " + JSONSTORE);
        }
    }

}
