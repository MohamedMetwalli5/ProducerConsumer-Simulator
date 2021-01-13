package main.java.eg.edu.alexu.csd.oop.mail;

public class Momento {
    private State state;
    public Momento(State state){
        this.state=state;
    }
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
