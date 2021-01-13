package main.java.eg.edu.alexu.csd.oop.mail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class AppFrame extends JFrame  {
    MainClass mc=new MainClass();
    ThreadKiller tk;
    Thread t;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JTextField products;
    int numOfProducts=7;
    App a = new App();
    JFrame frame = new JFrame();
    public AppFrame(){
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

        button6 = new JButton("Replay");
        button6.setBackground(Color.MAGENTA);
        button6.setForeground(Color.DARK_GRAY);
        button6.setFont(new Font("atilic",Font.BOLD,30));
        a.add(button6);

        products = new JTextField("number of products(7 is default)");
        products.setFont(new Font("atilic", Font.BOLD, 20));
        products.setForeground(Color.BLACK);
        a.add(products);

        button7 = new JButton("Clear");
        button7.setBackground(Color.MAGENTA);
        button7.setForeground(Color.DARK_GRAY);
        button7.setFont(new Font("atilic",Font.BOLD,30));
        a.add(button7);

        eve v = new eve();
        button1.addActionListener(v);
        button2.addActionListener(v);
        button3.addActionListener(v);
        button4.addActionListener(v);
        button5.addActionListener(v);
        button6.addActionListener(v);
        button7.addActionListener(v);
        frame.add(a);
        frame.setVisible(true);

        frame.setSize(800,800);
        frame.setLocation(330,20);
        frame.setResizable(false);
        frame.setTitle("Collisions Monitor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    private class eve implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {//Add Macine
                a.machines.add(new Ellipse2D.Double(a.x, a.y, 100, 100));
                a.x += 50;
                a.y += 50;
                a.machineColors.add(Color.green);
                //a.ourMachines.add(new Machine());
            }
            if (e.getSource() == button2) {//Add Queue
                a.queues.add(new Rectangle2D.Double(a.x, a.y, 120, 70));
                a.x += 50;
                a.y += 50;
                //a.ourQueues.add(new Queue(new LinkedBasedQ(),new ArrayList<>()));
            }

            if (e.getSource() == button3) {//Connect
                a.connectionFlag = 1;

            }

            if (e.getSource() == button4) {//Start Simulation
                LinkedBasedQ qIn=new LinkedBasedQ(),qOut=new LinkedBasedQ();
                for (int i=0;i<a.ourQueues.size();i++){
                    if (a.ourQueues.get(i).getMachines().size()==0){
                        qOut=a.ourQueues.get(i).getQueue();
                        break;
                    }
                }
                ArrayList<Boolean> queueTaken=new ArrayList<Boolean>();
                for (int i=0;i<a.ourQueues.size();i++){
                    queueTaken.add(false);
                }
                for (int i=0;i<a.ourMachines.size();i++){
                    for (int j=0;j<a.ourQueues.size();j++){
                        if(a.ourQueues.get(j).getQueue()==a.ourMachines.get(i).getQout()){
                            queueTaken.set(j,true);
                        }
                    }
                }
                for (int i=0;i<queueTaken.size();i++){
                    if (queueTaken.get(i)!=true){
                        qIn=a.ourQueues.get(i).getQueue();
                        break;
                    }
                }
                try {
                    // checking valid integer using parseInt() method
                    numOfProducts = Integer.parseInt(products.getText());
                } catch (NumberFormatException numberFormatException) {
                    numOfProducts = 7;
                }
                a.simulationFlag = 1;
                ArrayList<MachineRunner> ourMachinesRunner = new ArrayList<>();
                ArrayList<QueueRunner> ourQueuesRunner = new ArrayList<>();
                for (int i = 0; i < a.ourMachines.size(); i++) {
                    ourMachinesRunner.add(new MachineRunner(a.ourMachines.get(i)));
                }
                for (int i = 0; i < a.ourQueues.size(); i++) {
                    ourQueuesRunner.add(new QueueRunner(a.ourQueues.get(i)));
                }

                ArrayList<Producer> ps = mc.produce(numOfProducts, qIn);
                for (int i = 0; i < a.ourQueues.size(); i++) {
                    a.ourQueues.get(i).setReadyMachines();
                }
                for (int i = 0; i < a.ourMachines.size(); i++) {
                    a.ourMachines.get(i).setAppFrame(a);
                }

                mc.simulate(ourMachinesRunner, ourQueuesRunner, ps, numOfProducts);
                //i want to change the thread killer to take a which is tha App to make a.simulationfalg=0  when it end
                tk = new ThreadKiller(ourMachinesRunner, ourQueuesRunner, qOut, numOfProducts);
                t = new Thread(tk);
                t.start();
                mc.saveCurrentSimulation(ourMachinesRunner, ourQueuesRunner, ps, numOfProducts, qIn, qOut);
            }
            if (e.getSource() == button5) {//End Simulation
                //here i want to kill all threads running
            }
            if (e.getSource() == button6) {//Replay
                if (mc.getOrigin().getState() != null) {
                    ArrayList<QueueRunner> queues = mc.getOrigin().getState().getQueues();
                    ArrayList<MachineRunner> machines = mc.getOrigin().getState().getMachines();
                    LinkedBasedQ endQ = mc.getOrigin().getState().getEndQ();
                    int productsNumber = mc.getOrigin().getState().getProductsNumber();
                    for (int i = 0; i < queues.size(); i++) {
                        queues.get(i).getMo().resetReadyMachines();
                    }
                    for (int i = 0; i < queues.size(); i++) {
                        queues.get(i).setKillThread(false);
                    }
                    for (int i = 0; i < machines.size(); i++) {
                        machines.get(i).setKillThread(false);
                    }
                    while (!endQ.isEmpty()) {
                        endQ.dequeue();
                    }
                    System.out.println(mc.loadPreviousSimulation());
                    ThreadKiller tk = new ThreadKiller(machines, queues, endQ, productsNumber);
                    Thread t = new Thread(tk);
                    t.start();
                } else {

                    /*
                    HANDLE THE ERROR HERE
                     */
                }
            }
            if (e.getSource() == button7) {//Clear
                numOfProducts = 7;
                a = new App();
                frame.setVisible(false);
                frame = new JFrame();
                button1 = new JButton("Add Machine");
                button1.setBackground(Color.MAGENTA);
                button1.setForeground(Color.DARK_GRAY);
                button1.setFont(new Font("atilic", Font.BOLD, 30));
                a.add(button1);

                button2 = new JButton("Add Queue");
                button2.setBackground(Color.MAGENTA);
                button2.setForeground(Color.DARK_GRAY);
                button2.setFont(new Font("atilic", Font.BOLD, 30));
                a.add(button2);

                button3 = new JButton("Connect");
                button3.setBackground(Color.MAGENTA);
                button3.setForeground(Color.DARK_GRAY);
                button3.setFont(new Font("atilic", Font.BOLD, 30));
                a.add(button3);

                button4 = new JButton("Start Simulation");
                button4.setBackground(Color.MAGENTA);
                button4.setForeground(Color.DARK_GRAY);
                button4.setFont(new Font("atilic", Font.BOLD, 30));
                a.add(button4);

                button5 = new JButton("End Simulation");
                button5.setBackground(Color.MAGENTA);
                button5.setForeground(Color.DARK_GRAY);
                button5.setFont(new Font("atilic", Font.BOLD, 30));
                a.add(button5);

                button6 = new JButton("Replay");
                button6.setBackground(Color.MAGENTA);
                button6.setForeground(Color.DARK_GRAY);
                button6.setFont(new Font("atilic", Font.BOLD, 30));
                a.add(button6);

                products = new JTextField("number of products(7 is default)");
                products.setFont(new Font("atilic", Font.BOLD, 20));
                products.setForeground(Color.BLACK);
                a.add(products);

                button7 = new JButton("Clear");
                button7.setBackground(Color.MAGENTA);
                button7.setForeground(Color.DARK_GRAY);
                button7.setFont(new Font("atilic", Font.BOLD, 30));
                a.add(button7);

                eve v = new eve();
                button1.addActionListener(v);
                button2.addActionListener(v);
                button3.addActionListener(v);
                button4.addActionListener(v);
                button5.addActionListener(v);
                button6.addActionListener(v);
                button7.addActionListener(v);
                frame.add(a);
                frame.setVisible(true);

                frame.setSize(800, 800);
                frame.setLocation(330, 20);
                frame.setResizable(false);
                frame.setTitle("Collisions Monitor");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
    }
}
