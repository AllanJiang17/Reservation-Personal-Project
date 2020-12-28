package ui.panels;

import model.Reservations;
import ui.DoggyDayCareGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a table panel that shows the list of schedule with dogs scheduled at corresponding time
public class ReservationTablePanel extends JPanel implements ActionListener {
    private Reservations reservations;
    private JButton goBack;
    private DoggyDayCareGui doggyDayCareGui;

    //EFFECTS: creates the panel, add the buttons with the action selector class and create table to show schedule
    public ReservationTablePanel(DoggyDayCareGui doggyDayCareGui, Reservations reservations) {
        this.reservations = reservations;
        this.doggyDayCareGui = doggyDayCareGui;
        JTable table = getjTable();
        JScrollPane scrollPane = new JScrollPane(table);
        goBackButton();
        layOut(scrollPane);
    }

    //MODIFIES: this
    //EFFECTS: create the dimension for a return button that returns to MainMenuPanel
    private void goBackButton() {
        goBack = new JButton("Return");
        goBack.setSize(200,100);
        goBack.addActionListener(this);
        this.add(goBack);
    }

    //MODIFIES: this
    //EFFECTS: create the table, buttons layout using gridbaglayout
    private void layOut(JScrollPane j) {
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 0, 0, 8);
        g.gridx = 0;
        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        add(goBack, g);
        g.gridx = 0;
        g.gridy = 0;
        add(j, g);
    }

    //Code understood from
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/SimpleTableDemoProject/src/components/
    //SimpleTableDemo.java
    //EFFECTS: instantiate the Jtable object with the reservation time and dog at corresponding time
    private JTable getjTable() {
        String[] columnNames = {"Reservation Time", "Dog", "Showed Up?"};
        Object[][] data = {
                {"10 AM", reservations.getDogName(10), new Boolean(false)},
                {"11 AM", reservations.getDogName(11), new Boolean(false)},
                {"12 PM", reservations.getDogName(12), new Boolean(false)},
                {"13 PM", reservations.getDogName(13), new Boolean(false)},
                {"14 PM", reservations.getDogName(14), new Boolean(false)},
                {"15 PM", reservations.getDogName(15), new Boolean(false)},
                {"16 PM", reservations.getDogName(16), new Boolean(false)},
                {"17 PM", reservations.getDogName(17), new Boolean(false)},
                {"18 PM", reservations.getDogName(18), new Boolean(false)},
        };

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        return table;
    }

    @Override
    //REQUIRES: ActionEvent
    //MODIFIES: DoggyDayCareGui
    //EFFECT: return to mainMenuPanel
    public void actionPerformed(ActionEvent e) {
        doggyDayCareGui.returnMainMenu();
    }
}
