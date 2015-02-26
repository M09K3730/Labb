/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LuffarSchack;

import java.lang.reflect.Method;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author seb
 */
public class Game {

    Window window;
    Scoring score;
    Computer computer;
    String playerSymbol = "x";
    String computerSymbol = "o";

    public Game() {
    }

    public Game(Scoring score, Computer computer) {
        this.score = score;
        this.computer = computer;

    }

    // Called if someone wins
    private void showWinner(String winner) {
        String[] choices = {"Yes", "No"};
        if (winner == "Player") {
            //update score
            score.updatePlayerScore();
            int newScore = score.getPlayerScore();
            window.setPlayerScore(newScore);
            //show message dialgoe and ask if they want to play again
            int playAgain = JOptionPane.showOptionDialog(
                    null // Center in window.
                    , "You won! Do you want to play again?" // Message
                    , "You Won!" // Title in titlebar
                    , JOptionPane.YES_NO_OPTION // Option type
                    , JOptionPane.PLAIN_MESSAGE // messageType
                    , null // Icon (none)
                    , choices // Button text as above.
                    , "None of your business" // Default button's label
                    );
            // if yes reset board
            switch (playAgain) {
                case 0:
                    newGame();
                    break;
                case 1:
                    //if not playing again then ask them if they want to save their score
                    int saveScores = JOptionPane.showOptionDialog(
                            null // Center in window.
                            , "Do you want to save your scores?" // Message
                            , "Save?" // Title in titlebar
                            , JOptionPane.YES_NO_OPTION // Option type
                            , JOptionPane.PLAIN_MESSAGE // messageType
                            , null // Icon (none)
                            , choices // Button text as above.
                            , "None of your business" // Default button's label
                            );
                    switch (saveScores) {
                        case 0:
                            saveScore();
                            break;
                        case 1:
                            try {
                                //window.closeJFrame();
                                runMain("Meny.Meny");
                            } catch (Exception e) {
                                System.out.println(e);
                            }

                            System.exit(0);
                            break;
                    }
            }
        } else {
            //update score
            score.updateComputerScore();
            int newScore = score.getComputerScore();
            window.setComputerScore(newScore);
            //show message dialgoe and ask if they want to play again
            int playAgain = JOptionPane.showOptionDialog(
                    null // Center in window.
                    , "You lost! Do you want to play again?" // Message
                    , "You Lost!" // Title in titlebar
                    , JOptionPane.YES_NO_OPTION // Option type
                    , JOptionPane.PLAIN_MESSAGE // messageType
                    , null // Icon (none)
                    , choices // Button text as above.
                    , "None of your business" // Default button's label
                    );
            // if yes reset board
            switch (playAgain) {
                case 0:
                    newGame();
                    break;
                case 1:
                    //if not playing again then ask them if they want to save their score
                    int saveScores = JOptionPane.showOptionDialog(
                            null // Center in window.
                            , "Do you want to save your scores?" // Message
                            , "Save?" // Title in titlebar
                            , JOptionPane.YES_NO_OPTION // Option type
                            , JOptionPane.PLAIN_MESSAGE // messageType
                            , null // Icon (none)
                            , choices // Button text as above.
                            , "None of your business" // Default button's label
                            );
                    switch (saveScores) {
                        case 0:
                            saveScore();
                            break;
                        case 1:
                            System.exit(0);
                            break;
                    }
            }

        }
    }

    public static void runMain(String classname) throws Exception {
        Class cls = Class.forName(classname);
        Method method = cls.getDeclaredMethod("main", new Class[]{String[].class});
        method.invoke(null, new Object[]{null});
        System.out.println(" " + classname + " exekverad");
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    //Called if game is a draw
    private void drawnGame() {
        // show messgage dialogue and ask if they want to play again
        JOptionPane.showMessageDialog(null, "There was no winner :-(");

        // if yes reset board

        // if not ask if they want to save their score
    }

    // called if reset for a new game
    private void newGame() {
        score.resetStatus();
        window.resetButtons();
    }

    // called to save scores to database
    private void saveScore() {
        //ask for users name
        String userName = JOptionPane.showInputDialog("Enter your name:");
        //get users score
        int userScore = score.getPlayerScore();
        // Get today's date
        Date date = new Date();
        Format formatter;
        formatter = new SimpleDateFormat("dd-MM-yy");
        String formattedDate = formatter.format(date);
        formatter = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = formatter.format(date);
        //create MySQL query
        String query = "INSERT INTO highscores VALUES ('1','" + formattedDate + "','" + formattedTime + "', '" + userName + "', " + userScore + " );";
        //make mysql call
        MysqlDB mysql = new MysqlDB();      // this is the class Rolf gave us
        mysql.sendQuery(query);         // send query String to class
    }

    // method called after user makes a choice
    public void action(int choice) {
        //update players choice
        score.updateStatus(choice, playerSymbol);
        //check if player has won
        if (score.checkForWinner() == true) {
            showWinner("Player");

        }
        // check if game is over
        if (score.checkIfGameOver() == true) {
            drawnGame();
        } else {
            // let computer choose a position
            int computersChoice = computer.makeChoice();
            // update GIU
            window.updateComputerChoice(computersChoice);
            //update computers choice
            score.updateStatus(computersChoice, computerSymbol);
            // check if computer has won
            if (score.checkForWinner() == true) {
                showWinner("Computer");
            }
            // check if game is over
            if (score.checkIfGameOver() == true) {
                drawnGame();
            }
        }

    }


    public static void main(String[] args) {
        Scoring score = new Scoring();
        Computer computer = new Computer(score);
        Game game = new Game(score, computer);
        Window window = new Window(game);






    }
}
