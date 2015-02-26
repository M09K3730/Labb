/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LuffarSchack;
import java.util.Random;

/**
 *
 * @author seb
 */
public class Computer {
    Scoring score;

    public Computer(Scoring score){
        this.score = score;
    }



    public int makeChoice(){
        //make random choice between 0 and 8
        Random random = new Random();
        int choice = random.nextInt(9);
        //check if available
        do{
            choice = random.nextInt(9);
        }while(score.checkIfAvailable(choice) == false);
        //return choice
        return choice;
    }


}
