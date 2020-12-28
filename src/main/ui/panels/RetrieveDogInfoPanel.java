package ui.panels;

import model.Activities;
import model.Dog;
import model.Reservations;
import ui.DoggyDayCareGui;
import ui.tool.InternalFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a retrieve dog info panel class that output dog name, weight, food, list of activities it did and cost
public class RetrieveDogInfoPanel extends JPanel {
    private Reservations reservations;
    private Dog dog;
    private JButton goBack;
    private JButton retrieve;
    private DoggyDayCareGui doggyDayCareGui;
    private JLabel label;
    private Activities activities;

    //EFFECTS: creates the panel, add the buttons with the action selector class and create table to show schedule
    public RetrieveDogInfoPanel(DoggyDayCareGui doggyDayCareGui, Reservations reservations, Dog dog, Activities a) {
        this.reservations = reservations;
        this.doggyDayCareGui = doggyDayCareGui;
        this.dog = dog;
        this.activities = a;
        JTable table = getjTable();
        JScrollPane scrollPane = new JScrollPane(table);
        createLabels();
        setUpButton();
        listenersInitiation();
        layOut(scrollPane);
    }

    //Code understood from
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/SimpleTableDemoProject/src/components/
    //SimpleTableDemo.java
    //EFFECTS: instantiate the Jtable object with the reservation time and dog at corresponding time
    private JTable getjTable() {
        String[] columnNames = {"Reservation Time", "Dog"};
        Object[][] data = {
                {"10 AM", reservations.getDogName(10)},
                {"11 AM", reservations.getDogName(11)},
                {"12 PM", reservations.getDogName(12)},
                {"13 PM", reservations.getDogName(13)},
                {"14 PM", reservations.getDogName(14)},
                {"15 PM", reservations.getDogName(15)},
                {"16 PM", reservations.getDogName(16)},
                {"17 PM", reservations.getDogName(17)},
                {"18 PM", reservations.getDogName(18)},
        };

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        return table;
    }

    //MODIFIES: this
    //EFFECTS: create the labels for the input fields
    private void createLabels() {
        label = new JLabel("Enter Time To Get Dog Information:");
        label.setSize(100, 20);
    }

    //MODIFIES: this
    //EFFECTS: create the buttons for the panel
    private void setUpButton() {
        goBack = new JButton("Return");
        goBack.setSize(200, 100);
        this.add(goBack);
        retrieve = new JButton("Retrieve");
        retrieve.setSize(200, 100);
        this.add(retrieve);
    }

    //MODIFIES: this
    //EFFECTS: creates a action selector class and adds listeners to the buttons
    private void listenersInitiation() {
        ActionSelector listener = new ActionSelector();
        goBack.addActionListener(listener);
        retrieve.addActionListener(listener);
    }

    //Understood from simple drawing player
    //represents an ActionListener class
    protected class ActionSelector implements ActionListener {

        //REQUIRES: ActionEvent
        //EFFECT: retrieve time input and output internal frame with corresponding dog information and return to
        //mainMenuPanel with goBack button
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == retrieve) {
                retrieveInfo();
            } else if (e.getSource() == goBack) {
                doggyDayCareGui.returnFromRetrieveDog();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: create the table, buttons layout using gridbaglayout
    private void layOut(JScrollPane j) {
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 0, 8);
        g.gridx = 0;
        g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        add(j, g);
        g.gridx = 0;
        g.gridy = 1;
        add(label, g);
        g.gridx = 0;
        g.gridy = 2;
        add(retrieve, g);
        g.gridx = 0;
        g.gridy = 3;
        add(goBack, g);
    }

    //MODIFIES: this, DoggyDayCareGui
    //Understood from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
    //EFFECTS: show a input dialog that retrieves the user selection of time
    private void retrieveInfo() {
        Object[] possibilities = {"10", "11", "12", "13", "14", "15", "16", "17", "18"};
        String s = (String)JOptionPane.showInputDialog(
                this,
                "Choose Your Reservation Time\n" + "\"To Retrieve Your Dog Information.\"",
                "Customized Dialog", JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "10");;
        backToGui(s);
    }

    //MODIFIES: DoggyDayCareGui
    //EFFECTS: takes user time selection and set the newDog field in DoggyDayCareGui as the dog with time selected
    // if time input is illegal, throws error internal frame, otherwise displays internal frame with dog information
    public void backToGui(String s) {
        try {
            int intTime = Integer.parseInt(s);
            showDogInfo(intTime);
            doggyDayCareGui.retrievedDog(intTime);
        } catch (NumberFormatException e) {
            createFrame("Please Select A Time");
        }
    }

    //MODIFIES: this
    //EFFECTS: displays dog info as an internal frame
    public void showDogInfo(int s) {
        dog = reservations.returnDog(s);
        String x = dog.returnDogInfo();
        activities.returnListOfWeight(dog);
        String y = activities.getListOfActivities();
        activities.setListOfWeightToEmpty();
        activities.returnCostOfAllActivities(dog);
        int z = activities.getCostOfActivities();
        String w = Integer.toString(z);
        activities.setListOfCostToZero();
        createFrame("\n" + x + "\n" + "Activities" + y + "\n" + "Cost: " + w);

    }

    //Understood from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
    //MODIFIES: this
    //EFFECTS: create and add internal frame to panel
    protected void createFrame(String x) {
        InternalFrame frame = new InternalFrame();
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, x);
        JLabel invalid = new JLabel(x);
        invalid.setBounds(180, 500, 300, 200);
        frame.add(invalid);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
