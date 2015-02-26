package gissaTalet;

import LuffarSchack.MysqlDB;
import java.text.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Klassen fungerar som ett "Gissa talet"-spel
 * @author David Dahl
 */
public class GissaTalet {
    private Random rand = new Random();
    private int number;
    private int guess;
    private int attempts = 0;
    private int max = 100;
    private int min = 1;

    /**
     * Metoden återställer alla variabler till spelets startläge
     */
    private void reset() {
        setNumber();
        max = 100;
        min = 1;
        attempts = 0;
    }

    /**
     * Metoden väljer det tal som spelaren ska lista ut
     */
    private void setNumber() {
        number = rand.nextInt(100)+1;
    }

    /**
     * Metoden kontrollerar om talet är större eller mindre än det korrekta talet
     */
    private void checkNumber() {
        attempts++;
        if(guess > number) {
            JOptionPane.showMessageDialog(null, "Talet är för högt!", "Gissa talet", JOptionPane.DEFAULT_OPTION);
            max = guess-1;
        } else if(guess < number) {
            JOptionPane.showMessageDialog(null, "Talet är för lågt!", "Gissa talet", JOptionPane.DEFAULT_OPTION);
            min = guess+1;
        }
    }

    /**
     * Metoden skriver ut huruvida spelaren vann eller förlorade
     */
    private void gameOver() {
        if(guess == number) {
            saveToDatabase();
            
            if(JOptionPane.showConfirmDialog(null, "Grattis, du vann!\n\nTalet var: " + number + "\nDu behövde gissa " + attempts + " gånger\n\n" + "Vill du spela igen?", "Gissa talet", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {
                play();
            }
        } else {
            if(JOptionPane.showConfirmDialog(null, "Du förlorade, bättre lycka nästa gång!\n\nTalet var: " + number + "\n\n" + "Vill du spela igen?", "Gissa talet", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == 0) {
                play();
            }
        }
    }
    
    private void saveToDatabase(){
        //ask for users name
        String userName = JOptionPane.showInputDialog("Ange ditt namn:");
        //get users score
        int userScore = attempts;
        // Get today's date
        Date date = new Date();
        Format formatter;
        formatter = new SimpleDateFormat("dd-MM-yy");
        String formattedDate = formatter.format(date);
        formatter = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = formatter.format(date);
        //create MySQL query
        String query = "INSERT INTO highscores VALUES ('2','" + formattedDate + "','" + formattedTime + "', '" + userName + "', " + userScore + " );";
        //make mysql call
        MysqlDB mysql = new MysqlDB();      // this is the class Rolf gave us
        mysql.sendQuery(query);         // send query String to class
    }

    /**
     * Metoden låter användaren spela en omgång "Gissa talet"
     */
    public void play() {
        String temp = "";

        reset();
        do {
            try {
                temp = JOptionPane.showInputDialog(null, "Vilket tal tror du att datorn tänker på?\n\nTalet är minst " + min + " och max " + max + "\n", "Gissa talet", JOptionPane.DEFAULT_OPTION);
                guess = Integer.parseInt(temp);
                while(guess < min || guess > max) {
                    try{
                        temp = JOptionPane.showInputDialog(null, "Talet ligger inte inom giltigt intervall\n\nTalet är minst " + min + " och max " + max + "\n", "Gissa talet", JOptionPane.DEFAULT_OPTION);
                        guess = Integer.parseInt(temp);
                    } catch(NumberFormatException e){
                        if(temp == null)
                            System.exit(0);
                        askForNumber();
                    }
                }
            } catch(NumberFormatException e) {
                if(temp == null)
                    System.exit(0);
                askForNumber();
            }
            checkNumber();
        } while(attempts < 15 && guess != number);
        gameOver();
    }

    /**
     * Metoden ber användaren om ett tal i intervallet min-max tills dess att
     * användaren matar in ett tal i intervallet
     */
    private void askForNumber() {
        String temp = "";

        try{
            temp = JOptionPane.showInputDialog(null, "Du måste ange ett tal inom intervallet " + min + "-" + max + "\n", "Gissa talet", JOptionPane.DEFAULT_OPTION);
            guess = Integer.parseInt(temp);
            while(guess < min || guess > max) {
                try{
                    temp = JOptionPane.showInputDialog(null, "Talet ligger inte inom giltigt intervall\n\nTalet är minst " + min + " och max " + max + "\n", "Gissa talet", JOptionPane.DEFAULT_OPTION);
                    guess = Integer.parseInt(temp);
                } catch(NumberFormatException e){
                    if(temp == null)
                        System.exit(0);
                    askForNumber();
                }
            }
        } catch(NumberFormatException e){
            if(temp == null)
                System.exit(0);
            askForNumber();
        }
    }
    public static void main(String[] args) {
        GissaTalet guessTheNumber = new GissaTalet();
        guessTheNumber.play();
    }
}
