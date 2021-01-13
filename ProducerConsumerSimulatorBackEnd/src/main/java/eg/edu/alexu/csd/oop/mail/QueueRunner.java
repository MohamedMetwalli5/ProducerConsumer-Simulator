package eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class QueueRunner implements Runnable {
    private Queue mo;
    private volatile Boolean killThread=false;
    private  ArrayList<Machine> readyMachines;
    public QueueRunner(Queue mo){
        this.mo=mo;
        readyMachines=mo.getReadyMachines();
    }

    public Queue getMo() {
        return mo;
    }

    public void setKillThread(Boolean killThread){
        this.killThread=killThread;
    }

    public void run(){
        while(!killThread) {
            synchronized (this.mo.getQueue()) {
                synchronized (readyMachines) {

                    if (!this.mo.getQueue().isEmpty() && !this.readyMachines.isEmpty()) {//if the queue has products and there are ready machines
                        Machine readyMachine = this.readyMachines.get(0);

                        mo.sendProduct(readyMachine);
                    }
                }
            }

        }
    }
}
