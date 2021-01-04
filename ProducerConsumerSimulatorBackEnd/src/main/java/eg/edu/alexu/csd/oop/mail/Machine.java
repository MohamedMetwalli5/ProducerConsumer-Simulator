package eg.edu.alexu.csd.oop.mail;

import java.awt.*;
import java.util.ArrayList;
public class Machine implements Runnable{
    Color defaultColor;
    Product currentProduct;
    ArrayList<LinkedBasedQ> qin=new ArrayList<LinkedBasedQ>();
    ArrayList<LinkedBasedQ> qout=new ArrayList<LinkedBasedQ>();
    Boolean state=false; //The state of the machine (True for Working and false for Waiting)
    int workingTime; //random int from 500 to 5000 milliseconds


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

    public ArrayList<LinkedBasedQ> getQin() {
        return qin;
    }

    public void setQin(ArrayList<LinkedBasedQ> qin) {
        this.qin = qin;
    }

    public ArrayList<LinkedBasedQ> getQout() {
        return qout;
    }

    public void setQout(ArrayList<LinkedBasedQ> qout) {
        this.qout = qout;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    //Run Method
    @Override
    public void run(){//Consume from the first non empty queue or wait,Push the product after consuming in a random queue
        for(;;) {
            //Loop to find the first non empty queue or wait if all queues are empty
            int index=0;
            while(qin.get(index).isEmpty()){
                synchronized (qin.get(index)){
                    try {
                        qin.get(index).wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                index++;
            }
            try {
                wait(workingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
