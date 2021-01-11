package TheProgram;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class App extends JPanel implements ActionListener,MouseListener,MouseMotionListener {
    public static int time = 3;
    boolean on =false;
    boolean on1 =false;
    Timer t = new Timer(time,this);
    public static double x = 150, y = 150;
    Rectangle2D machine=new Rectangle2D.Double(x,y,120,70) ;
    Rectangle2D machine1=new Rectangle2D.Double(x+50,y+50,120,70) ;
    ArrayList<Rectangle2D> machines=new ArrayList<>();
    ArrayList<Rectangle2D> queues=new ArrayList<>();

    public App() {
        registerKeys();
        addMouseMotionListener(this);
        setFocusable(true);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gMachine = (Graphics2D)g;
        gMachine.setColor(Color.green);
        for (int i=0;i<machines.size();i++){
            gMachine.fill(machines.get(i));
            gMachine.drawString("M"+i,(float) machines.get(i).getX(),(float) machines.get(i).getY());
        }
        gMachine.setColor(Color.yellow);
        for (int i=0;i<queues.size();i++){
            gMachine.fill(queues.get(i));
            gMachine.drawString("Q"+i,(float) queues.get(i).getX(),(float) queues.get(i).getY());
        }
        t.start();
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
        if(machine1.contains(e.getPoint())) {
            System.out.println("Testing");
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

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
        for (int i=0;i<machines.size();i++){
            if(machines.get(i).contains(e.getPoint())&&!this.on){
                machines.set(i,new Rectangle2D.Double(e.getX()-60,e.getY()-35,120,70));
                this.on=true;
                System.out.println(on);
                //repaint();
            }
        }
        for (int i=0;i<queues.size();i++){
            if(queues.get(i).contains(e.getPoint())&&!this.on){
                queues.set(i,new Rectangle2D.Double(e.getX()-60,e.getY()-35,120,70));
                this.on=true;
                System.out.println(on);
                //repaint();
            }
        }
        this.on=false;
        this.on1=false;
        System.out.println(on);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}