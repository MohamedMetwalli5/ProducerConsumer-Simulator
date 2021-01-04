package eg.edu.alexu.csd.oop.mail;

public class MainClass {
    private Originator origin=new Originator();
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
    }
}
