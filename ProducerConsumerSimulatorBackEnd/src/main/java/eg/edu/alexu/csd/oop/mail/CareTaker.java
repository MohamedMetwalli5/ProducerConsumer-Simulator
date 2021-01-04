package eg.edu.alexu.csd.oop.mail;

import java.util.ArrayList;

public class CareTaker {
    ArrayList<Momento> momentos;

    public CareTaker(){
        momentos=new ArrayList<Momento>() ;
    }
    public void addMomento(Momento momento){
        momentos.add(momento);
    }
    public Momento getMomento(int index){
        return momentos.get(index);
    }
}
