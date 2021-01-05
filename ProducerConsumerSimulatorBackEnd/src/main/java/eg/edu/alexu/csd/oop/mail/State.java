package eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class State {
    private ArrayList<Machine>machines;
    private ArrayList<LinkedBasedQ>queues;
    public State(ArrayList<Machine>machines,ArrayList<LinkedBasedQ>queues){
        this.machines=machines;
        this.queues=queues;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public void setMachines(ArrayList<Machine> machines) {
        this.machines = machines;
    }

    public ArrayList<LinkedBasedQ> getQueues() {
        return queues;
    }

    public void setQueues(ArrayList<LinkedBasedQ> queues) {
        this.queues = queues;
    }
    public void createMachine(){
        Machine m=new Machine();
        machines.add(m);
    }
    public void createQueue(){
        LinkedBasedQ q=new LinkedBasedQ();
        queues.add(q);
    }
}
