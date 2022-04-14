package de.dvckbass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Puzzle  extends JFrame {
    JPanel panel;
    ArrayList<Icon> allImages = new ArrayList<>();
    ArrayList<JButton> allButtons = new ArrayList<>();
    public Puzzle() {
        super("Puzzle Picture");
        storeImages();
        init();
    }

    public void init() {
        panel  = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 1, 1));
        getContentPane().add(panel, BorderLayout.CENTER);

//        setContentPane(panel);

        createButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void storeImages() {
        for(int i=1; i<=9; i++) {
            Icon icon = new ImageIcon(getClass().getResource("/dog-puzzle/" + i + ".jpg"));
            allImages.add(icon);
        }
    }

    public void createButtons() {
        for(int i =0; i < 9; i++) {
            JButton button = new JButton(resizeImage(allImages.get(i)));
            allButtons.add(button);
        }

        Collections.shuffle(allButtons);

        for(int i=0; i< 9; i++) {
            panel.add(allButtons.get(i));
        }
    }

    public Icon resizeImage(Icon input) {
        ImageIcon icon = new ImageIcon(((ImageIcon) input).getImage().getScaledInstance(150, 150, DO_NOTHING_ON_CLOSE));
        return  icon;
    }

    public static void main(String[] args) {
        new Puzzle();
    }
}
