package eg.edu.alexu.csd.oop.mail;

import java.awt.*;
import java.util.ArrayList;
public class Machine implements Observable{
   private  Color defaultColor;
   private  Product currentProduct;
   private  ArrayList<Observer> qin=new ArrayList<Observer>();
   private  LinkedBasedQ qout=new LinkedBasedQ();
   private  Boolean state=true; //The state of the machine (True for Working and false for Waiting)
   private  int workingTime; //random int from 500 to 5000 milliseconds


    //Constructor
    public Machine(){
        workingTime=(int)(Math.random()*((5000-500+1)+500));//Set the working time randomly
    }


    //Setters And Getters
    public Color getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    public ArrayList<Observer> getQin() {
        return qin;
    }

    public void setQin(ArrayList<Observer> qin) {
        this.qin = qin;
    }

    public LinkedBasedQ getQout() {
        return qout;
    }

    public void setQout(LinkedBasedQ qout) {
        this.qout = qout;
    }

    public Boolean getState() {

        return state;

    }

    public  void  setState(Boolean state) {
        this.state = state;
        notifyObservers();
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }



    @Override
    public void attachObserver(Observer q) {
        this.qin.add(q);
    }

    @Override
    public void detachObserver(Observer q) {
        this.qin.remove(q);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : this.qin){
            o.update(this,this.getState());
        }
    }
}
