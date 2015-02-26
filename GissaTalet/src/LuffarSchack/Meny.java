/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LuffarSchack;
import java.lang.reflect.*;
import javax.swing.*;

/**
 *
 * @author seb
 */
public class Meny {

    public static void runMain(String classname) throws Exception {
        Class cls = Class.forName( classname );
        Method method = cls.getDeclaredMethod( "main", new Class[]{String[].class} );
        method.invoke( null, new Object[]{null} );
        System.out.println( " " + classname + " exekverad" );
    }

    public static void main( String[] args ) {
        Class cls;
        String meny = "Välj det spelet som du vill spela:\n\n" +
                "1.  Luffarschack\n" +
                "2.  Gissar Talet\n" +
                "3.  Visa Highscores Luffarschack\n" +
                "4.  Visa Highscores Gissar Talet\n " +
                "0.  Avsluta programmet\n\n" +
                "Ange ditt val";
        //int val = -1;
        //while(val!=0) {
        int val = Integer.parseInt( JOptionPane.showInputDialog( meny ) );
            try {
                switch(val) {
                    case 1 :  runMain( "LuffarSchack.Game" ); break;        //länk till första spelet
                    case 2 :  runMain( "GissaTalet.GissaTalet" ); break;             //länk till andra spelet
                    case 3 :  ShowResults results = new ShowResults(1); break;    //showResults LuffarSchack
                    case 4 :  ShowResults results2 = new ShowResults(2); break;     //showResults Gissar Talet
                }
                
            } catch ( Exception e ) {
                System.out.println( e );
            }
        }
    }




