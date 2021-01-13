package eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class Originator {
    private State state;

    public Originator(){
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
