package eg.edu.alexu.csd.oop.mail;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class App extends JPanel implements ActionListener,MouseListener,MouseMotionListener {
    public static int time = 3;
    Timer t = new Timer(time,this);
    public static double x = 100, y = 100;
    Rectangle2D machine=new Rectangle2D.Double(x,y,120,70) ;

    public App() {
        registerKeys();
        addMouseMotionListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D gMachine = (Graphics2D)g;
        gMachine.setColor(Color.green);
        gMachine.fill(machine);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }


    private void registerKeys() {
        int c = JComponent.WHEN_IN_FOCUSED_WINDOW;
        getInputMap(c).put(KeyStroke.getKeyStroke("ESCAPE"), "ESCAPE");
        getActionMap().put("ESCAPE", escapeAction);
    }



    private Action escapeAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };




    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

        if(machine.contains(e.getPoint())) {
            System.out.println("Testing");
        }


    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(machine.contains(e.getPoint())) {
            x = e.getX();
            y = e.getY();
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }


    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(machine.contains(e.getPoint())){
            machine=new Rectangle2D.Double(e.getX()-60,e.getY()-35,120,70);

            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}