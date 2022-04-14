package de.dvckbass;

import javax.swing.*;

public class Puzzle  extends JFrame {

    public Puzzle() {
        super("Puzzle Picture");
        init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Puzzle();
    }
}
