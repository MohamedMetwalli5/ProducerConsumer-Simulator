package eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;
import java.util.LinkedList;

public class Originator {
    private State state;

    public Originator(){
        this.state=new State(new ArrayList<Machine>(),new ArrayList<LinkedBasedQ>());
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
