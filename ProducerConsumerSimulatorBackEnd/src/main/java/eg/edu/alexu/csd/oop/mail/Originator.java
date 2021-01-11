package eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class Originator {
    private State state;

    public Originator(){
        this.state=new State(new ArrayList<MachineRunner>(),new ArrayList<QueueRunner>(),new ArrayList<Producer>(),0,new LinkedBasedQ(),new LinkedBasedQ());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Momento saveStateToMomento(){
        return new Momento(this.state);
    }
    public void getStateFromMomento(Momento momento){
        this.state=momento.getState();
    }
}
