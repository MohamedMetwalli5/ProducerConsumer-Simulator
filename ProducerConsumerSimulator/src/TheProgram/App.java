package TheProgram;

import com.sun.source.tree.IfTree;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class App extends JPanel implements ActionListener,MouseListener,MouseMotionListener,Observer{
    public static int time = 1;
    ArrayList<Boolean> onMachine =new ArrayList<>();
    ArrayList<Boolean> onQueue =new ArrayList<>();
    public static int connectionFlag = 0;
    Timer t = new Timer(time,this);
    public static double x = 150, y = 150;
    public ArrayList<Machine> ourMachines=new ArrayList<>();
    public ArrayList<Queue> ourQueues=new ArrayList<>();
    public ArrayList<Ellipse2D> machines = new ArrayList<>();
    public  ArrayList<Rectangle2D> queues = new ArrayList<>();
    private static ArrayList<Point> coordinates = new ArrayList<Point>();
    public ArrayList<Integer> betweenMachine = new ArrayList<>();
    public  ArrayList<Integer> betweenQueue = new ArrayList<>();
    public volatile ArrayList<Color> machineColors=new ArrayList<>();
    public static int simulationFlag=0;
    Graphics2D gMachine;
    boolean rightToLeft=true;
    boolean upToDown=true;
    public App() {
        registerKeys();
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        time = 1;
        onMachine =new ArrayList<>();
        onQueue =new ArrayList<>();
        connectionFlag = 0;
        t = new Timer(time,this);
        x = 150;
        y = 150;
        ourMachines=new ArrayList<>();
        ourQueues=new ArrayList<>();
        machines = new ArrayList<>();
        queues = new ArrayList<>();
        coordinates = new ArrayList<Point>();
        betweenMachine = new ArrayList<>();
        betweenQueue = new ArrayList<>();
        machineColors=new ArrayList<>();
        simulationFlag=0;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        gMachine = (Graphics2D)g;
        gMachine.setColor(Color.green);
        for (int i=0;i<machines.size();i++){
            gMachine.setColor(machineColors.get(i));
            gMachine.fill(machines.get(i));
            gMachine.setColor(Color.black);
            gMachine.drawString("M"+i,(float) machines.get(i).getX(),(float) machines.get(i).getY());
            gMachine.setColor(Color.green);
        }

        gMachine.setColor(Color.yellow);
        for (int j=0;j<queues.size();j++){
            gMachine.fill(queues.get(j));
            gMachine.setColor(Color.black);

            if(simulationFlag==1){
                gMachine.drawString("Q"+j+"     "+ ourQueues.get(j).getQueue().size(),(float) queues.get(j).getX(),(float) queues.get(j).getY());
            }
            else
                gMachine.drawString("Q"+j+"     "+ 0,(float) queues.get(j).getX(),(float) queues.get(j).getY());
            gMachine.setColor(Color.yellow);
        }
        if (betweenMachine.size()==betweenQueue.size() && simulationFlag==0){
            coordinates.clear();
            for (int i=0;i<betweenQueue.size();i++){
                Point p =new Point();
                p.setLocation(queues.get(betweenQueue.get(i)).getCenterX(),queues.get(betweenQueue.get(i)).getCenterY());
                coordinates.add(p);
                p =new Point();
                p.setLocation(machines.get(betweenMachine.get(i)).getCenterX(),machines.get(betweenMachine.get(i)).getCenterY());
                coordinates.add(p);
            }
            /////////////////////////////
            ourMachines.clear();
            ourQueues.clear();
            for (int i=0;i<machines.size();i++){
                ourMachines.add(new Machine());
            }
            for (int i=0;i<queues.size();i++){
                ourQueues.add(new Queue(new LinkedBasedQ(),new ArrayList<>()));
            }
            if (betweenMachine.size()==1&&betweenQueue.size()==1){
                double xDifferenc=Math.abs(machines.get(betweenMachine.get(0)).getCenterX()-queues.get(betweenQueue.get(0)).getCenterX());
                double yDifferenc=Math.abs(machines.get(betweenMachine.get(0)).getCenterY()-queues.get(betweenQueue.get(0)).getCenterY());
                if (xDifferenc>=yDifferenc){
                    rightToLeft=true;
                    upToDown=false;
                }
                else {
                    rightToLeft=false;
                    upToDown=true;
                }
            }
            for (int i=0;i<betweenMachine.size();i++){
                double xDifferenc=Math.abs(machines.get(betweenMachine.get(i)).getCenterX()-queues.get(betweenQueue.get(i)).getCenterX());
                double yDifferenc=Math.abs(machines.get(betweenMachine.get(i)).getCenterY()-queues.get(betweenQueue.get(i)).getCenterY());
                if ((rightToLeft&&!upToDown)&&xDifferenc>=yDifferenc){
                    if (machines.get(betweenMachine.get(i)).getCenterX()<queues.get(betweenQueue.get(i)).getCenterX()){
                        //Machine *------------  queue
                        //the Queue is (Queue in) Proportionally to the machine
                        ourMachines.get(betweenMachine.get(i)).attachObserver(ourQueues.get(betweenQueue.get(i)));
                        ourQueues.get(betweenQueue.get(i)).getMachines().add(ourMachines.get(betweenMachine.get(i)));
                    }
                    else {
                        //queue *------------  Machine
                        //the Queue is (Queue out) Proportionally to the machine
                        ourMachines.get(betweenMachine.get(i)).setQout(ourQueues.get(betweenQueue.get(i)).getQueue());
                    }
                }
                else {
                    if (machines.get(betweenMachine.get(i)).getCenterY()<queues.get(betweenQueue.get(i)).getCenterY()){
                        /*Machine |
                            |     |
                            |     |
                            |     |
                          queue   *
                         */
                        //the Queue is (Queue out) Proportionally to the machine
                        ourMachines.get(betweenMachine.get(i)).setQout(ourQueues.get(betweenQueue.get(i)).getQueue());
                    }
                    else {
                        /* queue  |
                            |     |
                            |     |
                            |     |
                        Machine   *
                         */
                        //the Queue is (Queue in) Proportionally to the machine
                        ourMachines.get(betweenMachine.get(i)).attachObserver(ourQueues.get(betweenQueue.get(i)));
                        ourQueues.get(betweenQueue.get(i)).getMachines().add(ourMachines.get(betweenMachine.get(i)));
                    }
                }
            }
            /////////////////////////////
        }
        int u = 0;
        while(u+1 < coordinates.size()) {
            gMachine.setColor(Color.black);
            g.drawLine((int)coordinates.get(u).getX(),(int)coordinates.get(u).getY(),(int)coordinates.get(u+1).getX(),(int)coordinates.get(u+1).getY());
            u += 2;
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
        if(connectionFlag == 1) {
            boolean checkOnMachine=false;
            Integer machine=null;
            boolean checkOnQueue=false;
            Integer queue=null;
            for (int i=0;i<machines.size();i++){
                if (machines.get(i).contains(e.getPoint())==true){
                    checkOnMachine=true;
                    machine=i;
                    break;
                }
            }
            for (int i=0;i<queues.size();i++){
                if (queues.get(i).contains(e.getPoint())==true){
                    checkOnQueue=true;
                    queue=i;
                    break;
                }
            }
            if (checkOnMachine!=checkOnQueue){
                if (checkOnQueue==true){
                    betweenQueue.add(queue);
                }
                if (checkOnMachine==true){
                    betweenMachine.add(machine);
                }
            }
            if (betweenMachine.size()!=betweenQueue.size()){
                if (Math.abs(betweenMachine.size()-betweenQueue.size())==1){
                    connectionFlag=1;
                }
                else {
                    if(betweenMachine.size()>betweenQueue.size()){
                        while (betweenMachine.size()!=betweenQueue.size()){
                            betweenMachine.remove(betweenMachine.size()-1);
                        }
                    }
                    else {
                        while (betweenQueue.size()!=betweenMachine.size()){
                            betweenQueue.remove(betweenQueue.size()-1);
                        }
                    }
                    connectionFlag=0;
                }
            }
            else {
                if (checkOnMachine==false&&checkOnQueue==false){
                    //clicked on connect button then he didnot click on any shape
                    connectionFlag=1;//wait until he click on one of the shapes
                    //we have another choice to close the effect of the connect button
                }
                else {
                    connectionFlag=0;
                    int count=0;
                    if (machine!=null) {
                        for (int i=0;i<betweenMachine.size();i++){
                            if (betweenMachine.get(i).equals(betweenMachine.get(betweenMachine.size()-1))) {
                                if (betweenQueue.get(i).equals(betweenQueue.get(betweenQueue.size()-1))) {
                                    count++;
                                }
                            }
                        }
                    }
                    else {
                        for (int i=0;i<betweenQueue.size();i++){
                            if (betweenQueue.get(i).equals(betweenQueue.get(betweenQueue.size()-1))) {
                                if (betweenMachine.get(i).equals(betweenMachine.get(betweenMachine.size()-1))) {
                                    count++;
                                }
                            }
                        }
                    }
                    if (count!=1){
                        betweenMachine.remove(betweenMachine.size()-1);
                        betweenQueue.remove(betweenQueue.size()-1);
                    }
                }
            }
        }

//        for (int i=0;i<machines.size();i++){
//            if(machines.get(i).contains(e.getPoint())){
//                System.out.println("Testing");
//            }
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        if (this.onMachine.size()==0||this.onMachine.size()!=this.machines.size()){
            for (int i=0;i<machines.size();i++){
                this.onMachine.add(false);
            }
        }
        if (this.onQueue.size()==0||this.onQueue.size()!=this.queues.size()){
            for (int i=0;i<queues.size();i++){
                this.onQueue.add(false);
            }
        }

        for (int i = 0; i < machines.size(); i++) {
            if (machines.get(i).contains(e.getPoint())) {
                this.onMachine.set(i, true);
            }
        }

        for (int i = 0; i < queues.size(); i++) {
            if (queues.get(i).contains(e.getPoint())) {
                this.onQueue.set(i, true);
            }
        }

        boolean checkOnMachine=false;
        boolean checkOnQueue=false;
        for (int i=0;i<onMachine.size();i++){
            if (this.onMachine.get(i)==true){
                checkOnMachine=true;
            }
        }
        for (int i=0;i<onQueue.size();i++){
            if (this.onQueue.get(i)==true){
                checkOnQueue=true;
            }
        }
        if(checkOnQueue==true&&checkOnMachine==true){
            for (int i = 0; i < onMachine.size(); i++) {
                this.onMachine.set(i,false);
            }
            checkOnMachine=false;
            for (int i = 0; i < queues.size(); i++) {
                if (queues.get(i).contains(e.getPoint())) {
                    this.onQueue.set(i, true);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        for (int i = 0; i < onMachine.size(); i++) {
            this.onMachine.set(i,false);
        }
        for (int i = 0; i < onQueue.size(); i++) {
            this.onQueue.set(i,false);
        }
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
        if(connectionFlag == 0 && simulationFlag==0) {
            for (int i=0;i<machines.size();i++){
                if(machines.get(i).contains(e.getPoint())&&this.onMachine.get(i)){
                    machines.set(i,new Ellipse2D.Double(e.getX()-25,e.getY()-25,50,50));
                    break;
                    //repaint();
                }
            }
            for (int i=0;i<queues.size();i++){
                if(queues.get(i).contains(e.getPoint())&&this.onQueue.get(i)){
                    queues.set(i,new Rectangle2D.Double(e.getX()-25,e.getY()-25,70,50));
                    break;
                    //repaint();
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    @Override
    public synchronized void update(Observable machine, Boolean machineState) {
        Machine sentMachine =(Machine)machine;
        int index=ourMachines.indexOf(machine);

        if(sentMachine.getCurrentProduct()!=null) {
            this.machineColors.set(index,sentMachine.getCurrentProduct().getProductColor());
        }
        else {
            this.machineColors.set(index,Color.white);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.machineColors.set(index,Color.green);

        }
    }

    @Override
    public void sendProduct(Observable machine) {

    }

    @Override
    public LinkedBasedQ getQueue() {
        return null;
    }
}
