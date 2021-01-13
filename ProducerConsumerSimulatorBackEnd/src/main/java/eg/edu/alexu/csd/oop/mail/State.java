package main.java.eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class State {
    private ArrayList<MachineRunner>machines;
    private ArrayList<QueueRunner>queues;
    private ArrayList<Producer> producers;
    private  int productsNumber;
    private LinkedBasedQ q0;
    private LinkedBasedQ endQ;
    public State(ArrayList<MachineRunner>machines,ArrayList<QueueRunner>queues,ArrayList<Producer> producers,int productsNumber,LinkedBasedQ q0,LinkedBasedQ endQ){
        this.machines=machines;
        this.queues=queues;
        this.producers=producers;
        this.productsNumber=productsNumber;
        this.q0=q0;
        this.endQ=endQ;
    }

    public LinkedBasedQ getEndQ() {
        return endQ;
    }

    public void setEndQ(LinkedBasedQ endQ) {
        this.endQ = endQ;
    }

    public ArrayList<MachineRunner> getMachines() {
        return machines;
    }

    public void setMachines(ArrayList<MachineRunner> machines) {
        this.machines = machines;
    }

    public ArrayList<QueueRunner> getQueues() {
        return queues;
    }

    public void setQueues(ArrayList<QueueRunner> queues) {
        this.queues = queues;
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    public int getProductsNumber() {
        return productsNumber;
    }

    public void setProductsNumber(int productsNumber) {
        this.productsNumber = productsNumber;
    }

    public LinkedBasedQ getQ0() {
        return q0;
    }

    public void setQ0(LinkedBasedQ q0) {
        this.q0 = q0;
    }
}
