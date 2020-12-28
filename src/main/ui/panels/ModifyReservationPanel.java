package ui.panels;

import model.Reservations;
import ui.DoggyDayCareGui;
import ui.tool.InternalFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represants modify dog panel with a table and buttons to modify schedule, also export the new dog schedule back to
// DoggyDayCareGui
public class ModifyReservationPanel extends JPanel {
    private Reservations reservations;
    private JButton goBack;
    private JButton submit;
    private JButton refresh;
    private DoggyDayCareGui doggyDayCareGui;
    private JTextField newTime;
    private JLabel label;

    //EFFECTS: creates the panel, add the buttons with the action selector class and create table to show schedule
    public ModifyReservationPanel(DoggyDayCareGui doggyDayCareGui, Reservations reservations) {
        this.reservations = reservations;
        this.doggyDayCareGui = doggyDayCareGui;
        JTable table = getjTable();
        JScrollPane scrollPane = new JScrollPane(table);
        addInputFields();
        createLabels();
        setUpButton();
        listenersInitiation();
        layOut(scrollPane);
    }

    //Code understood from
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/SimpleTableDemoProject/src/components/
    //SimpleTableDemo.java
    //EFFECTS: instantiate the Jtable object with the reservation
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
    //EFFECTS: adds the input fields to the panel
    private void addInputFields() {
        newTime = new JTextField();
        newTime.setSize(140, 25);
    }

    //MODIFIES: this
    //EFFECTS: create the labels for the input fields
    private void createLabels() {
        label = new JLabel("New Reservation Time:");
        label.setSize(100, 20);
    }

    //MODIFIES: this
    //EFFECTS: create the buttons for the panel
    private void setUpButton() {
        submit = new JButton("Submit");
        this.add(submit);
        goBack = new JButton("Return");
        this.add(goBack);
        refresh = new JButton("Refresh");
        this.add(refresh);
    }

    //MODIFIES: this
    //EFFECTS: creates a action selector class and adds listeners to the buttons
    private void listenersInitiation() {
        ActionSelector listener = new ActionSelector();
        submit.addActionListener(listener);
        goBack.addActionListener(listener);
        refresh.addActionListener(listener);
    }

    //Understood from simple drawing player
    //represents an ActionListener class
    protected class ActionSelector implements ActionListener {

        //REQUIRES: ActionEvent
        //EFFECT: retrieve new dog back into doggyDayCareGui, return to mainMenuPanel and refresh to repaint this panel
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {
                modifyReservation();
            } else if (e.getSource() == goBack) {
                doggyDayCareGui.returnFromModifyDog();
            } else if (e.getSource() == refresh) {
                doggyDayCareGui.refreshModifyDog();
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
        add(newTime, g);
        g.gridx = 0;
        g.gridy = 3;
        add(submit, g);
        g.gridx = 0;
        g.gridy = 4;
        add(refresh, g);
        g.gridx = 0;
        g.gridy = 5;
        add(goBack, g);
    }

    //MODIFIES: this, DoggyDayCareGui
    //EFFECTS: test if time selection is valid and pass schedule into DoggyDayCareGui, else throws internal frame
    private void modifyReservation() {
        String time = this.newTime.getText();
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
    }

    //MODIFIES: DoggyDayCareGui
    //EFFECTS: transport new dog time back to DoogyDayCareGui
    public void backToGui() {
        String time = newTime.getText();
        int intTime = Integer.parseInt(time);
        doggyDayCareGui.modifyDog(intTime);
    }

    // Understood from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
    //MODIFIES: this
    //EFFECTS: create and add internal frame to panel
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
