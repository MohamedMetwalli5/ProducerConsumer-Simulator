package eg.edu.alexu.csd.oop.mail;
import java.util.ArrayList;

public class MachineObserver implements  Observer{
    private LinkedBasedQ q;
    private ArrayList<Machine> machines;//The machines that serve the queue
    private ArrayList<Machine> readyMachines=new ArrayList<>();
    private  Boolean killThread=false;
    public MachineObserver(LinkedBasedQ q,ArrayList<Machine> machines){
        this.q=q;
        this.machines=machines;
    }

    public ArrayList<Machine> getReadyMachines() {
        return readyMachines;
    }

    public Boolean getKillThread(){
        return this.killThread;
    }
    @Override
    public void update(Observable machine,Boolean machineState) {
        if(machineState==true){
            readyMachines.remove(machine);
        }
        else {
            readyMachines.add((Machine)machine);
        }
    }
    public LinkedBasedQ getQueue(){
        return this.q;
    }
    public void setSubjectState(Observable machine){
        int index=this.machines.indexOf(machine);
        this.machines.get(index).setState(true);//Notify the machine to start working
    }


}
