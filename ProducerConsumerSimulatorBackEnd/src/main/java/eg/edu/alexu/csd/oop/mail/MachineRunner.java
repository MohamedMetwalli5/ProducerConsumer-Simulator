package eg.edu.alexu.csd.oop.mail;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MachineRunner implements Runnable {
    private Machine m;
    private boolean killThread=false;
    public MachineRunner(Machine m){
        this.m=m;
    }
    public void setKillThread(Boolean killThread){
        this.killThread=killThread;
    }



    //Run Method
    @Override
    public void run(){//Consume from the first non empty queue or wait,Push the product after consuming in a random queue
        while(!killThread) {
            synchronized (this){
                if(m.getCurrentProduct()!=null){
                     System.out.println(Thread.currentThread().getName() + " Is Serving");
                    System.out.println("Serving Product " + m.getCurrentProduct().getProductColor().toString());
                    try {

                        Thread.sleep(this.m.getWorkingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   System.out.println("Finished Serving Product" + m.getCurrentProduct().getProductColor().toString());
                    m.getQout().enqueue(m.getCurrentProduct());
                    m.setCurrentProduct(null);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //Loop to find the first non empty queue or notify  all queues that the machine is ready
               /* if (m.getState()) { //if the current machine is in the working state
                    int index = -1;
                    for (int i = 0; i < m.getQin().size(); i++) {
                        LinkedBasedQ queue = m.getQin().get(i).getQueue();
                        if (!queue.isEmpty()) {
                            index = i;
                            break;
                        }
                    }
                    if (index != -1) {
                        LinkedBasedQ currentQin = m.getQin().get(index).getQueue();
                        // synchronized (currentQin) {
                        //while(!currentQin.isEmpty()){
                        m.setCurrentProduct((Product) currentQin.dequeue());
                        System.out.println(Thread.currentThread().getName() + " Is Serving");
                        System.out.println("Serving Product " + m.getCurrentProduct().getProductColor().toString());
                        try {
                            Thread.sleep(this.m.getWorkingTime());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Finished Serving Product" + m.getCurrentProduct().getProductColor().toString());
                        synchronized (m.getQout()) {
                            m.getQout().enqueue(m.getCurrentProduct());
                        }
                        //}
                        // }
                    } else {//Change the state of the machine to be ready and Notify the queues that the machine is ready
                        m.setState(false);
                    }
                }*/
            }
        }
    }

}
