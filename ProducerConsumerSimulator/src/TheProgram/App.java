package TheProgram;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
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
    public static int connectionFlag = 0;
    Timer t = new Timer(time,this);
    
    public static double x = 150, y = 150;
    Rectangle2D machine=new Rectangle2D.Double(x,y,120,70) ;
    Rectangle2D machine1=new Rectangle2D.Double(x+50,y+50,120,70) ;
    public ArrayList<Rectangle2D> machines = new ArrayList<>();
    public  ArrayList<Ellipse2D> queues = new ArrayList<>();
    private static ArrayList<Integer> coordinates = new ArrayList<>();
    public App() {
        registerKeys();
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gMachine = (Graphics2D)g;
        gMachine.setColor(Color.green);
        for (int i=0;i<machines.size();i++){
            gMachine.fill(machines.get(i));
            gMachine.setColor(Color.black);
            gMachine.drawString("M"+i,(float) machines.get(i).getX(),(float) machines.get(i).getY());
            gMachine.setColor(Color.green);
        }
        
        gMachine.setColor(Color.yellow);
        for (int j=0;j<queues.size();j++){
            gMachine.fill(queues.get(j));
            gMachine.setColor(Color.black);
            gMachine.drawString("Q"+j,(float) queues.get(j).getX(),(float) queues.get(j).getY());
            gMachine.setColor(Color.yellow);
        }
          int u = 0, v = 1;
          while(u+4 < coordinates.size() && v+4 < coordinates.size()) {
        	  gMachine.setColor(Color.black);
              g.drawLine(coordinates.get(u),coordinates.get(v),coordinates.get(u+2),coordinates.get(v+2));
        	  u += 4;
        	  v += 4;
        	  
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
    	 coordinates.add((int)e.getPoint().getX());
    	 coordinates.add((int) e.getPoint().getY());
    	  
//        for (int i=0;i<machines.size();i++){
//            if(machines.get(i).contains(e.getPoint())){
//                System.out.println("Testing");
//            }
//        }
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
    	if(connectionFlag == 0) {
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
	                queues.set(i,new Ellipse2D.Double(e.getX()-50,e.getY()-50,100,100));
	                this.on=true;
	                System.out.println(on);
	                //repaint();
	            }
	        }
	        
	        this.on=false;
	        this.on1=false;
	        System.out.println(on);
	    }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}