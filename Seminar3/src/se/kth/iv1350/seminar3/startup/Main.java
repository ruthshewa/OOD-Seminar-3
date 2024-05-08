package se.kth.iv1350.seminar3.startup;

import se.kth.iv1350.seminar3.controller.Controller;
import se.kth.iv1350.seminar3.view.View;
import se.kth.iv1350.seminar3.dto.ItemDTO;

/**
 * Kommentarer
 */

 public class Main{

    /**
    * The main method is the entry point for the application
    * 
    *@param args 
    */



    public static void main(String [] args){

        

        Controller contr = new Controller ();
        View view =new View(contr);
       

       

    }
}
