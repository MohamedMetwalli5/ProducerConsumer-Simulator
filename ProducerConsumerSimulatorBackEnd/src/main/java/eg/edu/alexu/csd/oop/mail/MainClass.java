package eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class MainClass {
    /*private Originator origin=new Originator();
    private State originState=origin.getState();//TODO is this a good way to use the state of the originator ?
    //Production Thread is part of the Facade Class  because the Production thread doesn't change
    private Thread productionThread=new Thread(new Producer(),"Production Thread");


    //TODO A Better Way to Get the random color of the product
    public String produce(){//TODO problem cannot start 2 threads at the same time even
        //TODO if i check if the thread is running or not this won't help at all
        //TODO because i need to create a new product but it's already producing
        productionThread.start();
        LinkedBasedQ q0=origin.getState().getQueues().get(0);//Get q0
        synchronized (q0){
            Product producedProduct =(Product)q0.peek();//get the product that was just produced
            q0.notifyAll();
            return producedProduct.getProductColor().toString();
        }
    }
    public void createMachine(){
        originState.createMachine();//TODO is there a better way to add Machines ? Note:We use indices to access machines or queues
    }
    public void createQueue(){
        originState.createQueue();
    }
    public void addInQueue(int machineIndex,int queueIndex){
        Machine m=originState.getMachines().get(machineIndex);
        LinkedBasedQ qin=originState.getQueues().get(queueIndex);
        m.getQin().add(qin);
    }
    public void addOutQueue(int machineIndex,int queueIndex){
        Machine m=originState.getMachines().get(machineIndex);
        LinkedBasedQ qout=originState.getQueues().get(queueIndex);
        m.getQout().add(qout);
    }*/




    public void produce(int productsNumber,LinkedBasedQ q0) {
        ArrayList<Thread> producers = new ArrayList<Thread>();
        //Creating the producing threads
        for (int i = 0; i < productsNumber; i++) {
            Producer p = new Producer(q0);
            Thread t = new Thread(p);
            producers.add(t);
        }
        //Running the production threads
        for (int i = 0; i < productsNumber; i++) {
            producers.get(i).start();
        }
    }


    public ArrayList<Thread> startMachines(ArrayList<MachineRunner> machines){
        ArrayList<Thread> machineThreads=new ArrayList<>();
        for(int i=0;i<machines.size();i++){
            String threadName="M"+(i+1);
            Thread t=new Thread(machines.get(i),threadName);
            machineThreads.add(t);
            t.start();
        }
        return machineThreads;
    }

    public ArrayList<Thread> startQueues(ArrayList<MachineObserverRunner> queues){
        ArrayList<Thread> queueThreads=new ArrayList<>();
        for(int i=0;i<queues.size();i++){
            String threadName="Q"+i;
            Thread t=new Thread(queues.get(i),threadName);
            queueThreads.add(t);
            t.start();
        }
        return queueThreads;
    }


    public void simulate(int productsNumber,LinkedBasedQ q0,ArrayList<MachineRunner> machines,ArrayList<MachineObserverRunner> queues){
        produce(productsNumber,q0);
        ArrayList<Thread> machineThreads=startMachines(machines);//TODO should we run machines then queues or queues then machines
        ArrayList<Thread> queueThreads=startQueues(queues);
    }
}
