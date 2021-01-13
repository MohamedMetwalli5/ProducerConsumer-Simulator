package main.java.eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class MainClass {


    private Originator origin=new Originator();

    public Originator getOrigin() {
        return origin;
    }

    public ArrayList<Producer> produce(int productsNumber, LinkedBasedQ q0) {
        ArrayList<Producer> producers = new ArrayList<Producer>();
        //Creating the producing threads
        for (int i = 0; i < productsNumber; i++) {
            Producer p = new Producer(q0);
            producers.add(p);
            Thread t = new Thread(p);
        }
        return producers;
    }
    public void startProducers(ArrayList<Producer>producers,int productsNumber){
        //Running the production threads
        ArrayList<Thread> producersThreads=new ArrayList<>();
        for(int i=0;i<producers.size();i++){
            Thread t= new Thread(producers.get(i));
            producersThreads.add(t);
        }
        for (int i = 0; i < productsNumber; i++) {
            producersThreads.get(i).start();
        }
    }


    public void startMachines(ArrayList<MachineRunner> machines){
        ArrayList<Thread> machineThreads=new ArrayList<>();
        for(int i=0;i<machines.size();i++){
            String threadName="M"+(i+1);
            Thread t=new Thread(machines.get(i),threadName);
            machineThreads.add(t);
            t.start();
        }

    }

    public void startQueues(ArrayList<QueueRunner> queues){
        ArrayList<Thread> queueThreads=new ArrayList<>();
        for(int i=0;i<queues.size();i++){
            String threadName="Q"+i;
            Thread t=new Thread(queues.get(i),threadName);
            queueThreads.add(t);
            t.start();
        }

    }
    public void saveCurrentSimulation(ArrayList<MachineRunner> machines,ArrayList<QueueRunner> queues,ArrayList<Producer> producers,int productsNumber,LinkedBasedQ q0,LinkedBasedQ endQ){
        origin.setState(new State(machines,queues,producers,productsNumber,q0,endQ));
    }
    public Boolean loadPreviousSimulation() {
        if (origin.getState()!=null) {
            State previousState = origin.getState();
            simulate(previousState.getMachines(), previousState.getQueues(), previousState.getProducers(), previousState.getProductsNumber());
            return true;
        }
        else {
            return false;
        }
    }

    public void simulate(ArrayList<MachineRunner> machines,ArrayList<QueueRunner> queues,ArrayList<Producer> producers,int productsNumber){
        startProducers(producers,productsNumber);
        startMachines(machines);
        startQueues(queues);

    }
}
