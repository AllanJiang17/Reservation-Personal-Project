package ui.panels;

import exceptions.WeightException;
import model.Dog;
import ui.DoggyDayCareGui;
import ui.tool.InternalFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a make dog panel that takes dog info and export back to DoggyDayCareGui
public class MakeDogPanel extends JPanel {
    private DoggyDayCareGui doggyDayCareGui;
    private JButton submit;
    private JButton goBack;
    private JTextField dogName;
    private JTextField weight;
    private JTextField food;
    private Dog dog;

    //EFFECTS: creates the panel and add the buttons with the action selector class
    public MakeDogPanel(DoggyDayCareGui doggyDayCareGui) {
        this.doggyDayCareGui = doggyDayCareGui;
        startPanel();
        createLabels();
        addInputFields();
        buttonInitiation();
        imageInitiation();
        listenersInitiation();
    }

    //MODIFIES: this
    //EFFECTS: create the titles and bound of the panel
    private void startPanel() {
        this.setLayout(null);
        this.setBounds(0, 0, DoggyDayCareGui.WIDTH, DoggyDayCareGui.HEIGHT);
        JLabel title = new JLabel("Please Enter Your Dog's Information:");
        title.setFont(new Font("Calibri", Font.BOLD, 20));
        title.setBounds(90,20,400, 100);
        this.add(title);
        JLabel weightHeadsUp = new JLabel("Please be careful with your weight input from 0 to 200");
        weightHeadsUp.setFont(new Font("Calibri", Font.BOLD, 15));
        weightHeadsUp.setBounds(90,90,400, 100);
        this.add(weightHeadsUp);
    }

    //MODIFIES: this
    //EFFECTS: adds the input fields to the panel
    private void addInputFields() {
        dogName = new JTextField();
        dogName.setBounds(205, 160, 140, 25);

        weight = new JTextField();
        weight.setBounds(205, 180, 140, 25);

        food = new JTextField();
        food.setBounds(205, 200, 140, 25);

        this.add(dogName);
        this.add(weight);
        this.add(food);
    }

    //MODIFIES: this
    //EFFECTS: create the labels for the input fields
    private void createLabels() {
        addLabels("Dog Name:", 160);
        addLabels("Weight:", 180);
        addLabels("Food:", 200);
    }

    //MODIFIES: this
    //EFFECTS: adds the labels to the panel
    private void addLabels(String x, int y) {
        JLabel label = new JLabel(x);
        label.setBounds(105, y, 200, 20);
        this.add(label);
    }

    //MODIFIES: this
    //EFFECTS: create the buttons for the panel
    private void buttonInitiation() {
        submit = new JButton("Submit");
        submit.setBounds(105, 250, 90, 25);
        this.add(submit);
        goBack = new JButton("Return to Main Menu");
        goBack.setBounds(205, 250, 200, 25);
        this.add(goBack);
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
        ActionSelectors listener = new ActionSelectors();
        submit.addActionListener(listener);
        goBack.addActionListener(listener);
    }

    //Understood from simple drawing player
    //represents an ActionListener class
    protected class ActionSelectors implements ActionListener {

        //REQUIRES: ActionEvent
        //EFFECT: retrieve dog info and return them back into DoggyDayCareGui and return to mainMenuPanel when goBack
        //button is selected
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {
                createDog();
            } else if (e.getSource() == goBack) {
                doggyDayCareGui.returnFromMakeDog();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: test if weight input is valid and pass dog info into DoggyDayCareGui, else throws internal frame
    private void createDog() {
        String weight = this.weight.getText();
        try {
            int intWeight = Integer.parseInt(weight);
            backToGui();
        } catch (NumberFormatException ex) {
            createFrame("Weight Input Not Valid");
        } catch (WeightException w) {
            createFrame("Weight must be between 0 and 200");
        }
        revalidate();
        repaint();
    }

    //MODIFIES: DoggyDayCareGui                                         
    //EFFECTS: transport dog info back to DoogyDayCareGui
    private void backToGui() throws WeightException {
        String name = dogName.getText();
        String weight = this.weight.getText();
        int intWeight = Integer.parseInt(weight);
        String food = this.food.getText();
        dog = new Dog("empty", 0, "empty");
        dog.setDogName(name);
        dog.setDogWeight(intWeight);
        dog.setDogFood(food);
        doggyDayCareGui.madeDog(dog);
    }

    // Understood from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
    //MODIFIES: this
    //EFFECTS: create and add internal frame to panel
    protected void createFrame(String x) {
        InternalFrame frame = new InternalFrame();
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, x, "ERROR", JOptionPane.ERROR_MESSAGE);
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
