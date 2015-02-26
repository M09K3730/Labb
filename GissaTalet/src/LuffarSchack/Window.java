/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LuffarSchack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author seb
 */
public class Window implements ActionListener {

    Game game;
    private JFrame frame = new JFrame("Luffarschack");
    private JButton topLeft = new JButton();
    private JButton topCenter = new JButton();
    private JButton topRight = new JButton();
    private JButton middleLeft = new JButton();
    private JButton middleCenter = new JButton();
    private JButton middleRight = new JButton();
    private JButton bottomLeft = new JButton();
    private JButton bottomCenter = new JButton();
    private JButton bottomRight = new JButton();
    private JLabel lblPlayerTitle = new JLabel("Player", SwingConstants.CENTER);
    private JLabel lblVersus = new JLabel("vs" , SwingConstants.CENTER);
    private JLabel lblComputerTitle = new JLabel("Computer", SwingConstants.CENTER);
    private JLabel lblHyphen = new JLabel(" - ", SwingConstants.CENTER);
    private JTextArea taPlayerScore = new JTextArea();
    private JTextArea taComputerScore = new JTextArea();
    private JPanel titleAndScore = new JPanel(new GridLayout(2, 3));
    private JPanel buttons = new JPanel(new GridLayout(3, 3));
    private JPanel layout = new JPanel(new BorderLayout());


    public Window(Game game) {
        this.game = game;
        this.game.setWindow(this);

        frame.setSize(300, 350);
        frame.setTitle("Luffarschack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleAndScore.add(lblPlayerTitle);
        titleAndScore.add(lblVersus);
        titleAndScore.add(lblComputerTitle);
        titleAndScore.add(taPlayerScore);
        titleAndScore.add(lblHyphen);
        titleAndScore.add(taComputerScore);

        buttons.add(topLeft);
        buttons.add(topCenter);
        buttons.add(topRight);
        buttons.add(middleLeft);
        buttons.add(middleCenter);
        buttons.add(middleRight);
        buttons.add(bottomLeft);
        buttons.add(bottomCenter);
        buttons.add(bottomRight);

        layout.add(titleAndScore, BorderLayout.NORTH);
        layout.add(buttons, BorderLayout.CENTER);

        topLeft.addActionListener(this);
        topCenter.addActionListener(this);
        topRight.addActionListener(this);
        middleLeft.addActionListener(this);
        middleCenter.addActionListener(this);
        middleRight.addActionListener(this);
        bottomLeft.addActionListener(this);
        bottomCenter.addActionListener(this);
        bottomRight.addActionListener(this);

        frame.add(layout);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == topLeft) {
            topLeft.setText("X");
            topLeft.setEnabled(false);
            int choice = 0;
            game.action(0);
        } else if (obj == topCenter) {
            topCenter.setText("X");
            topCenter.setEnabled(false);
            game.action(1);
        } else if (obj == topRight) {
            topRight.setText("X");
            topRight.setEnabled(false);
            game.action(2);
        } else if (obj == middleLeft) {
            middleLeft.setText("X");
            middleLeft.setEnabled(false);
            game.action(3);
        } else if (obj == middleCenter) {
            middleCenter.setText("X");
            middleCenter.setEnabled(false);
            game.action(4);
        } else if (obj == middleRight) {
            middleRight.setText("X");
            middleRight.setEnabled(false);
            game.action(5);
        } else if (obj == bottomLeft) {
            bottomLeft.setText("X");
            bottomLeft.setEnabled(false);
            game.action(6);
        } else if (obj == bottomCenter) {
            bottomCenter.setText("X");
            bottomCenter.setEnabled(false);
            game.action(7);
        } else if (obj == bottomRight) {
            bottomRight.setText("X");
            bottomRight.setEnabled(false);
            game.action(8);
        }

    }

    public void updateComputerChoice(int computersChoice) {
        switch (computersChoice) {
            case 0:
                topLeft.setText("O");
                topLeft.setEnabled(false);
                break;
            case 1:
                topCenter.setText("O");
                topCenter.setEnabled(false);
                break;
            case 2:
                topRight.setText("O");
                topRight.setEnabled(false);
                break;
            case 3:
                middleLeft.setText("O");
                middleLeft.setEnabled(false);
                break;
            case 4:
                middleCenter.setText("O");
                middleCenter.setEnabled(false);
                break;
            case 5:
                middleRight.setText("O");
                middleRight.setEnabled(false);
                break;
            case 6:
                bottomLeft.setText("O");
                bottomLeft.setEnabled(false);
                break;
            case 7:
                bottomCenter.setText("O");
                bottomCenter.setEnabled(false);
                break;
            case 8:
                bottomRight.setText("O");
                bottomRight.setEnabled(false);
                break;
        }


    }

    public void resetButtons() {
        topLeft.setEnabled(true);
        topLeft.setText("");
        topCenter.setEnabled(true);
        topCenter.setText("");
        topRight.setEnabled(true);
        topRight.setText("");
        middleLeft.setEnabled(true);
        middleLeft.setText("");
        middleCenter.setEnabled(true);
        middleCenter.setText("");
        middleRight.setEnabled(true);
        middleRight.setText("");
        bottomLeft.setEnabled(true);
        bottomLeft.setText("");
        bottomCenter.setEnabled(true);
        bottomCenter.setText("");
        bottomRight.setEnabled(true);
        bottomRight.setText("");

    }

    public void setPlayerScore(int newScore) {
        taPlayerScore.setText(Integer.toString(newScore));
    }

    public void setComputerScore(int newScore) {
        taComputerScore.setText(Integer.toString(newScore));
    }

    public void closeJFrame(){
        //this.frame.dispose();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
