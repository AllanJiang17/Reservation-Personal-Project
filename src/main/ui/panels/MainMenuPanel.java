package ui.panels;

import ui.DoggyDayCareGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a panel class that contains buttons that lead to other panels or back to DoggyDayCareGui
public class MainMenuPanel extends JPanel {
    DoggyDayCareGui doggyDayCareGui;
    private JButton seeSchedule;
    private JButton makeDog;
    private JButton modifyReservation;
    private JButton retrieveDog;
    private JButton save;
    private JButton load;
    private static final int XPOSITION = 100;
    private static final int YPOSITION = 80;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 35;

    //EFFECTS: creates the panel and adds in buttons
    public MainMenuPanel(DoggyDayCareGui doggyDayCareGui) {
        this.doggyDayCareGui = doggyDayCareGui;
        buttonInitiation();
        buttonLayout();
        imageInitiation();
        listenersInitiation();
    }

    //MODIFIES: this
    //EFFECTS: creates the buttons with their text
    private void buttonInitiation() {
        makeDog = new JButton("Enter Dog Info");
        seeSchedule = new JButton("See Schedule");
        modifyReservation = new JButton("Modify Dog Reservation");
        retrieveDog = new JButton("Retrieve Dog Information");
        save = new JButton("Save Current Schedule");
        load = new JButton("Load Previous Schedule");
    }

    //MODIFIES: this
    //EFFECTS: modifies the buttons positions
    private void buttonLayout() {
        setLayout(null);
        this.setBounds(0, 0, DoggyDayCareGui.WIDTH, DoggyDayCareGui.HEIGHT);
        makeDog.setBounds(XPOSITION,YPOSITION, WIDTH,HEIGHT);
        add(makeDog);
        seeSchedule.setBounds(XPOSITION,YPOSITION + 50, WIDTH,HEIGHT);
        add(seeSchedule);
        modifyReservation.setBounds(XPOSITION,YPOSITION + 100, WIDTH,HEIGHT);
        add(modifyReservation);
        retrieveDog.setBounds(XPOSITION,YPOSITION + 150, WIDTH,HEIGHT);
        add(retrieveDog);
        save.setBounds(XPOSITION,YPOSITION + 200, WIDTH, HEIGHT);
        add(save);
        load.setBounds(XPOSITION,YPOSITION + 250, WIDTH,HEIGHT);
        add(load);
    }

    //MODIFIES: this
    //EFFECTS: creates the doggy label image
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
        makeDog.addActionListener(listener);
        seeSchedule.addActionListener(listener);
        modifyReservation.addActionListener(listener);
        retrieveDog.addActionListener(listener);
        save.addActionListener(listener);
        load.addActionListener(listener);
    }

    //Understood from simple drawing player
    //represents an ActionListener class
    private class ActionSelector implements ActionListener {

        //REQUIRES: ActionEvent
        //MODIFIES: DoggyDayCareGui
        //EFFECT: goes to corresponding panel with selected buttons or save and load when save and load buttons are
        //pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == makeDog) {
                doggyDayCareGui.openDogPanel();
            } else if (e.getSource() == seeSchedule) {
                doggyDayCareGui.openSchedulePanel();
            } else if (e.getSource() == modifyReservation) {
                doggyDayCareGui.openModifyPanel();
            } else if (e.getSource() == retrieveDog) {
                doggyDayCareGui.openRetrieveDogPanel();
            } else if (e.getSource() == save) {
                doggyDayCareGui.saveSchedule();
            } else if (e.getSource() == load) {
                doggyDayCareGui.loadSchedule();
            }
        }
    }
}
