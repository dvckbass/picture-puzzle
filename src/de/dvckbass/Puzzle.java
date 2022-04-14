package de.dvckbass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Puzzle  extends JFrame implements ActionListener {
    JPanel panel;
    JButton firstButton;
    JButton secondButton;
    boolean firstClick = false;

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

        JButton resetButton = new JButton("RESET");
        getContentPane().add(resetButton, BorderLayout.SOUTH);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allButtons.clear();
                getContentPane().removeAll();
                init();
            }
        });

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
            ((ImageIcon)button.getIcon()).setDescription(String.valueOf(i));
            button.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!firstClick) {
            firstClick = true;
            firstButton = (JButton) e.getSource();
        } else {
            firstClick = false;
            secondButton = (JButton) e.getSource();

            swap();

            boolean result = checkWin();
            if(result) {
                JOptionPane.showMessageDialog(this, "Great Job! You WON!", "Congrats!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public void swap() {
        Icon icon1 = firstButton.getIcon();
        Icon icon2 = secondButton.getIcon();
        firstButton.setIcon(icon2);
        secondButton.setIcon(icon1);
    }

    public boolean checkWin() {
        boolean win = true;

        for(int i=0; i<9; i++) {
            ImageIcon icon = (ImageIcon) allButtons.get(i).getIcon();
            if(!icon.getDescription().equals(String.valueOf(i))) {
                win = false;
                break;
            }
        }

        return win;
    }
}
