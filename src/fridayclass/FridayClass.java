/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fridayclass;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.tut.StudentFrame;

/**
 *
 * @author Simphiwe-2021
 */
public class FridayClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            StudentFrame frame = new StudentFrame();
        } catch (SQLException ex) {
            Logger.getLogger(FridayClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
