package eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class ThreadKiller implements Runnable{
    private ArrayList<MachineRunner> machines;
    private ArrayList<QueueRunner> queues;
    private volatile LinkedBasedQ  endQ;
    int productsNumber;
    ThreadKiller( ArrayList<MachineRunner> machines,ArrayList<QueueRunner> queues,LinkedBasedQ endQ,int productsNumber){
        this.machines=machines;
        this.queues=queues;
        this.endQ=endQ;
        this.productsNumber=productsNumber;
    }
    public void run(){
        while(true) {
            if (endQ.size() == productsNumber) {
                int i;
                for (i = 0; i < machines.size(); i++) {
                    machines.get(i).setKillThread(true);
                }
                for (i = 0; i < queues.size(); i++) {
                    queues.get(i).setKillThread(true);
                }
                System.out.println("End");
                break;
            }

        }
    }
}
