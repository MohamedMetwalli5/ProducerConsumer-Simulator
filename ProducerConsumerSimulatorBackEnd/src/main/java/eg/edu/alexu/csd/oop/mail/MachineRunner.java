package main.java.eg.edu.alexu.csd.oop.mail;

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

            }
        }
    }

}
