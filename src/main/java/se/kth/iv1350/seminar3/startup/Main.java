package se.kth.iv1350.seminar3.startup;
import se.kth.iv1350.seminar3.controller.Controller;
import se.kth.iv1350.seminar3.view.View;

/**
 * The main class is the entry start point for the entire program.
 */

 public class Main{

    public static void main(String [] args){

        Controller contr = new Controller();
        View view =new View(contr);

        view.runFakeExecution();


    }
}
