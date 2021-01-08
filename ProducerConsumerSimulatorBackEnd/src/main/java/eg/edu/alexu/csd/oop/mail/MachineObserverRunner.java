package eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class MachineObserverRunner implements Runnable {
    private MachineObserver mo;
    Boolean killThread=false;
    public MachineObserverRunner(MachineObserver mo){
        this.mo=mo;
    }
    public void setKillThread(Boolean killThread){
        this.killThread=killThread;
    }
    ArrayList<Machine> readyMachines=mo.getReadyMachines();
    public void run(){
        while(!killThread) {
            synchronized (this.mo) {
                if (!this.mo.getQueue().isEmpty() && !this.readyMachines.isEmpty()) {//if the queue has products and there are ready machines
                    Machine readyMachine = this.readyMachines.get(0);
                    mo.setSubjectState(readyMachine);
                    this.readyMachines.remove(0);
                }
            }
        }
    }
}
