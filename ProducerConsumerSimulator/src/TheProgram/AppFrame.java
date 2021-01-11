package TheProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
public class AppFrame extends JFrame{
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    App a = new App();
    JFrame frame = new JFrame();
    public AppFrame(){
        //StartWindow run = new StartWindow();
        //run.setVisible(true);
        //MainMenu a=new MainMenu();

        /////////////////////////////

        button1 = new JButton("Add Machine");
        button1.setBackground(Color.MAGENTA);
        button1.setForeground(Color.DARK_GRAY);
        button1.setFont(new Font("atilic",Font.BOLD,30));
        a.add(button1);

        button2 = new JButton("Add Queue");
        button2.setBackground(Color.MAGENTA);
        button2.setForeground(Color.DARK_GRAY);
        button2.setFont(new Font("atilic",Font.BOLD,30));
        a.add(button2);

        button3 = new JButton("Connect");
        button3.setBackground(Color.MAGENTA);
        button3.setForeground(Color.DARK_GRAY);
        button3.setFont(new Font("atilic",Font.BOLD,30));
        a.add(button3);

        button4 = new JButton("Start Simulation");
        button4.setBackground(Color.MAGENTA);
        button4.setForeground(Color.DARK_GRAY);
        button4.setFont(new Font("atilic",Font.BOLD,30));
        a.add(button4);

        button5 = new JButton("End Simulation");
        button5.setBackground(Color.MAGENTA);
        button5.setForeground(Color.DARK_GRAY);
        button5.setFont(new Font("atilic",Font.BOLD,30));
        a.add(button5);
        eve v = new eve();
        button1.addActionListener(v);
        button2.addActionListener(v);
        button3.addActionListener(v);
        button4.addActionListener(v);
        button5.addActionListener(v);
        frame.add(a);
        frame.setVisible(true);

        frame.setSize(800,800);
        frame.setLocation(330,20);
        frame.setResizable(false);
        frame.setTitle("Collisions Monitor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //a.setVisible(true);
    }

    private class eve implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == button1) {
                a.machines.add(new Rectangle2D.Double(a.x,a.y,120,70));
                a.x+=50;
                a.y+=50;
            }
            if(e.getSource() == button2) {
                a.queues.add(new Rectangle2D.Double(a.x, a.y, 120, 70));
                a.x+=50;
                a.y+=50;
            }
        }
    }
}
