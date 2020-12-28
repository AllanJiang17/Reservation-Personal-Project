package ui.panels;

import model.Reservations;
import ui.DoggyDayCareGui;
import ui.tool.InternalFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the add dog panel that shows dog inputs and export them back into DoggyDayCareGui
public class AddDogPanel extends JPanel {
    DoggyDayCareGui doggyDayCareGui;
    Reservations reservations;
    JButton submit;
    JButton goBack;
    JTextField time;

    //EFFECTS: creates the add dog panel
    public AddDogPanel(DoggyDayCareGui doggyDayCareGui, Reservations reservations) {
        this.doggyDayCareGui = doggyDayCareGui;
        this.reservations = reservations;
        panelSetup();
        createLabels();
        addInputFields();
        buttonInitiation();
        imageInitiation();
        listenersInitiation();
    }

    //MODIFIES: this
    //EFFECTS: create the titles and bound of the panel
    private void panelSetup() {
        this.setLayout(null);
        this.setBounds(0, 0, DoggyDayCareGui.WIDTH, DoggyDayCareGui.HEIGHT);
        JLabel title = new JLabel("Please Enter Your Reservation Time From 10am to 18pm");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setFont(new Font("Calibri", Font.BOLD, 20));
        title.setBounds(100,20,600, 100);
        this.add(title);
    }

    //MODIFIES: this
    //EFFECTS: adds the input fields to the panel
    private void addInputFields() {
        time = new JTextField();
        time.setBounds(105, 160, 140, 25);

        this.add(time);
    }

    //MODIFIES: this
    //EFFECTS: create the labels for the input fields
    private void createLabels() {
        JLabel label = new JLabel("Preferred Reservation Time:");
        label.setBounds(105, 120, 300, 20);
        this.add(label);
    }

    //MODIFIES: this
    //EFFECTS: create the buttons for the panel
    private void buttonInitiation() {
        submit = new JButton("Submit");
        submit.setBounds(105, 250, 90, 25);
        this.add(submit);
        goBack = new JButton("Return to Dog Information");
        goBack.setBounds(205, 250, 200, 25);
        this.add(goBack);
    }

    //MODIFIES: this
    //EFFECTS: create the dog label for the panel
    private void imageInitiation() {
        ImageIcon doggyImage = new ImageIcon("./data/dog1.jpg");
        JLabel dogImage = new JLabel(doggyImage);
        dogImage.setSize(700,500);
        this.add(dogImage);
    }

    //MODIFIES: this
    //EFFECTS: creates a action selector class and adds listeners to the buttons
    private void listenersInitiation() {
        ActionSelector listener = new ActionSelector();
        submit.addActionListener(listener);
        goBack.addActionListener(listener);
    }

    //Understood from simple drawing player
    //represents an ActionListener class
    protected class ActionSelector implements ActionListener {

        //REQUIRES: ActionEvent
        //EFFECT: retrieve time input and return time input back into DoggyDayCareGui
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {
                addDog();
            } else if (e.getSource() == goBack) {
                doggyDayCareGui.returnFromAddDog();
            }
        }
    }

    //MODIFIES: this, DoggyDayCareGui
    //EFFECTS: checks for weight input, throws exceptions if it is illegal input, otherwise transfers dog info back to
    // DoggyDayCareGui
    private void addDog() {
        String time = this.time.getText();
        try {
            int intTIme = Integer.parseInt(time);
            if (reservations.isSpotOpen(intTIme)) {
                backToGui();
            } else {
                createFrame("Time is full or Not Within Hours of Operation");
            }
        } catch (NumberFormatException ex) {
            createFrame("Time Input Not Valid");
        }
        revalidate();
        repaint();
    }

    //MODIFIES: this
    //EFFECTS:  Transfers dog info back to DoggyDayCareGui
    public void backToGui() {
        String time = this.time.getText();
        int intTime = Integer.parseInt(time);

        doggyDayCareGui.addDog(intTime);
    }

    //MODIFIES: this
    //EFFECTS: creates frame and display them according to the text given
    // Understood from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
    protected void createFrame(String x) {
        InternalFrame frame = new InternalFrame();
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, x, "ERROR", JOptionPane.WARNING_MESSAGE);
        JLabel invalid = new JLabel(x);
        invalid.setBounds(180, 500, 200, 100);
        frame.add(invalid);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
