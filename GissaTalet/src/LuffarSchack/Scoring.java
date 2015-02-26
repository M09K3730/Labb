/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LuffarSchack;

/**
 *
 * @author seb
 */
public class Scoring {
    String[] status = new String[9];
    private int playerScore;
    private int computerScore;

    public Scoring(){
        resetStatus();
    }

    public boolean checkForWinner(){
        if(status[0] =="x" && status[1] == "x" && status[2] == "x" || status[0] =="o" && status[1] == "o" && status[2] == "o"){
            return true;
        }
        if(status[0] =="x" && status[3] == "x" && status[6] == "x" || status[0] =="o" && status[3] == "o" && status[6] == "o"){
            return true;
        }
        if(status[1] =="x" && status[4] == "x" && status[7] == "x" || status[1] =="o" && status[4] == "o" && status[7] == "o"){
            return true;
        }
        if(status[2] =="x" && status[5] == "x" && status[8] == "x" || status[2] =="o" && status[5] == "o" && status[8] == "o"){
            return true;
        }
        if(status[3] =="x" && status[4] == "x" && status[5] == "x" || status[3] =="o" && status[4] == "o" && status[5] == "o"){
            return true;
        }
        if(status[6] =="x" && status[7] == "x" && status[8] == "x" || status[6] =="o" && status[7] == "o" && status[8] == "o"){
            return true;
        }
        if(status[0] =="x" && status[4] == "x" && status[8] == "x" || status[0] =="o" && status[4] == "o" && status[8] == "o"){
            return true;
        }
        if(status[2] =="x" && status[4] == "x" && status[6] == "x" || status[2] =="o" && status[4] == "o" && status[6] == "o"){
            return true;
        }else return false;
    }

    public boolean checkIfGameOver(){
        int x = 0;
        // check each buttons status and add 1 to x variable to record how many are disabled
        for (int i = 0; i < status.length; i++){
            if(status[i] == "x" || status[i] == "o"){
                x++;
            }
        }
        // if all 9 buttons are disabled then the game is over so return true
        if(x == 9){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkIfAvailable(int choice){
        if(status[choice] == "0"){
            return true;
        }
        return false;
    }

    public void resetStatus(){
        for(int i=0; i<status.length; i++){
            status[i] = "0";
        }

    }

    public void updateStatus(int choice, String characterSymbol){
        status[choice] = characterSymbol;
    }

    public void updatePlayerScore(){
        playerScore++;
    }

    public void updateComputerScore(){
        computerScore++;
    }

    public int getPlayerScore(){
        return this.playerScore;
    }

    public int getComputerScore(){
        return this.computerScore;
    }

}
