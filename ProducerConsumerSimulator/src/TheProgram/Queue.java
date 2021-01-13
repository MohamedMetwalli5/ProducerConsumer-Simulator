package TheProgram;

import java.util.ArrayList;

public class Queue implements  Observer{
    private  LinkedBasedQ q;
    private ArrayList<Machine> machines;//The machines that serve the queue
    private volatile ArrayList<Machine> readyMachines=new ArrayList<>();
    private volatile Boolean killThread=false;
    public Queue(LinkedBasedQ q, ArrayList<Machine> machines){
        this.q=q;
        this.machines=machines;

    }
    public void setReadyMachines(){
        //At the beginning the readyMachines is same as machines(all machines are ready at the beginning)
        for(int i=0;i<machines.size();i++){
            readyMachines.add(machines.get(i));
        }
    }

    public void resetReadyMachines(){
        for(int i=0;i<machines.size();i++){
            readyMachines.set(i,machines.get(i));
        }
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public ArrayList<Machine> getReadyMachines() {
        return readyMachines;
    }

    public Boolean getKillThread(){
        return this.killThread;
    }

    @Override
    public void update(Observable machine,Boolean machineState) {
        synchronized (readyMachines) {
            if (machineState == true) {
                readyMachines.remove(machine);
            } else {
                readyMachines.add((Machine) machine);
            }
        }
    }
    public LinkedBasedQ getQueue(){
        return this.q;
    }


    public  void sendProduct(Observable machine) {
        synchronized (machine){
            if (((Machine) machine).getState() == false) {
                Product p = (Product) this.q.dequeue();
                System.out.println("Sending " + p.getProductColor().toString());
                ((Machine) machine).setCurrentProduct(p);//Notify the machine to start working
            }
        }
    }


}
