package ui;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

//Represents the main class that starts the DoggyDayCareGui
public class Main {
    //runs the new DoggyDayCareGui if no exceptions are thrown
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(() -> new DoggyDayCareGui());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
