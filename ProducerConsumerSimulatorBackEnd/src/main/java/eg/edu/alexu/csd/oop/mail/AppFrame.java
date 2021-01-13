package eg.edu.alexu.csd.oop.mail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class AppFrame extends JFrame  {
    MainClass mc=new MainClass();
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JTextField products;
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

        button6 = new JButton("Rplay");
        button6.setBackground(Color.MAGENTA);
        button6.setForeground(Color.DARK_GRAY);
        button6.setFont(new Font("atilic",Font.BOLD,30));
        a.add(button6);
        eve v = new eve();
        button1.addActionListener(v);
        button2.addActionListener(v);
        button3.addActionListener(v);
        button4.addActionListener(v);
        button5.addActionListener(v);
        button6.addActionListener(v);
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
                a.machines.add(new Ellipse2D.Double(a.x, a.y, 100, 100));
                a.x+=50;
                a.y+=50;
                a.machineColors.add(Color.green);
                //a.ourMachines.add(new Machine());
            }
            if(e.getSource() == button2) {
                a.queues.add(new Rectangle2D.Double(a.x,a.y,120,70));
                a.x+=50;
                a.y+=50;
                //a.ourQueues.add(new Queue(new LinkedBasedQ(),new ArrayList<>()));
            }
            
            if(e.getSource() == button3) {
            	a.connectionFlag = 1;

            }

            if(e.getSource() == button4) {
                a.simulationFlag=1;
                ArrayList<MachineRunner> ourMachinesRunner=new ArrayList<>();
                ArrayList<QueueRunner> ourQueuesRunner=new ArrayList<>();
                for (int i=0;i<a.ourMachines.size();i++){
                    ourMachinesRunner.add(new MachineRunner(a.ourMachines.get(i)));
                }
                for (int i=0;i<a.ourQueues.size();i++){
                    ourQueuesRunner.add(new QueueRunner(a.ourQueues.get(i)));
                }

                ArrayList<Producer> ps=mc.produce(7,a.ourQueues.get(0).getQueue());
                for (int i=0;i<a.ourQueues.size();i++){
                    a.ourQueues.get(i).setReadyMachines();
                }
                for (int i=0;i<a.ourMachines.size();i++){
                    a.ourMachines.get(i).setAppFrame(a);
                }
                mc.saveCurrentSimulation(ourMachinesRunner,ourQueuesRunner,ps,7,a.ourQueues.get(0).getQueue(),a.ourQueues.get(a.ourQueues.size()-1).getQueue());
                mc.simulate(ourMachinesRunner,ourQueuesRunner,ps,7);
                ThreadKiller tk =new ThreadKiller(ourMachinesRunner,ourQueuesRunner,a.ourQueues.get(a.ourQueues.size()-1).getQueue(),7);
                Thread t=new Thread(tk);
                t.start();
            }
            if(e.getSource()==button6){
                if(mc.getOrigin().getState()!=null) {
                    ArrayList<QueueRunner> queues = mc.getOrigin().getState().getQueues();
                    ArrayList<MachineRunner> machines = mc.getOrigin().getState().getMachines();
                    LinkedBasedQ endQ = mc.getOrigin().getState().getEndQ();
                    int productsNumber = mc.getOrigin().getState().getProductsNumber();
                    for (int i=0;i<queues.size();i++){
                    queues.get(i).getMo().resetReadyMachines();
                    }
                    for (int i = 0; i < queues.size(); i++) {
                        queues.get(i).setKillThread(false);
                    }
                    for (int i = 0; i < machines.size(); i++) {
                        machines.get(i).setKillThread(false);
                    }
                    while(!endQ.isEmpty()){
                        endQ.dequeue();
                    }
                    System.out.println(mc.loadPreviousSimulation());
                    ThreadKiller tk = new ThreadKiller(machines, queues, endQ, productsNumber);
                    Thread t = new Thread(tk);
                    t.start();
                }
                else {

                    /*
                    HANDLE THE ERROR HERE
                     */
                }
            }
        }
    }
}
