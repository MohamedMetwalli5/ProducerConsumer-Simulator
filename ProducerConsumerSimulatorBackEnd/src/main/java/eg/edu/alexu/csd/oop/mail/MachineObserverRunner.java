package eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class MachineObserverRunner implements Runnable {
    private MachineObserver mo;
    private Boolean killThread=false;
    private ArrayList<Machine> readyMachines;
    public MachineObserverRunner(MachineObserver mo){
        this.mo=mo;
        readyMachines=mo.getReadyMachines();
    }
    public void setKillThread(Boolean killThread){
        this.killThread=killThread;
    }

    public void run(){
        while(!killThread) {
                if (!this.mo.getQueue().isEmpty() && !this.readyMachines.isEmpty()) {//if the queue has products and there are ready machines
                    Machine readyMachine = this.readyMachines.get(0);
                    mo.setSubjectState(readyMachine);
                }

        }
    }
}
/*
If two threads change the state of the same machine at the same time there is no problem (no synchronized)
ReadyMachines don't need synchronization because it is arrayList accessible only from mo
Is empty is synchronized
 */