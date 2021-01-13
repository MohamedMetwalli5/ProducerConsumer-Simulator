package main.java.eg.edu.alexu.csd.oop.mail;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class MainMenu extends JFrame{
    JFrame f1 = new JFrame();
    JPanel p1 = new JPanel();
    JButton button1;
    JButton button2;
    public static Image img;
    eve v = new eve();
    public MainMenu() {
        this.setTitle("Physics Collisions");
        this.setSize(800, 800);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(330, 20);
        p1.setSize(800, 800);
        p1.setBackground(Color.pink);

        p1.setLayout(null);
        button1 = new JButton("Play");
        button2 = new JButton("What is this");

        button1.setBounds(250, 200, 250, 80);
        button1.setBackground(Color.MAGENTA);
        button1.setForeground(Color.DARK_GRAY);
        button1.setFont(new Font("atilic",Font.BOLD,30));

        button2.setBounds(250, 300, 250, 80);
        button2.setBackground(Color.MAGENTA);
        button2.setForeground(Color.DARK_GRAY);
        button2.setFont(new Font("atilic",Font.BOLD,30));


        this.add(p1);
        p1.add(button1);
        p1.add(button2);
        button1.addActionListener(v);
        button2.addActionListener(v);
    }
    private class eve implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == button1) {
                App a = new App();
                JFrame frame = new JFrame();
                frame.add(a);
                frame.setVisible(true);
                frame.setSize(800,800);
                frame.setLocation(330,20);
                frame.setResizable(false);
                frame.setTitle("Collisions Monitor");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                a.setVisible(true);
                f1.setVisible(false);
                f1.dispose();
            }

        }

    }

}
