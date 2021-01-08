package eg.edu.alexu.csd.oop.mail;

public class MachineRunner implements Runnable {
    private Machine m;
    boolean killThread=false;
    public MachineRunner(Machine m,int productsNumber){
        this.m=m;
    }
    public void setKillThread(Boolean killThread){
        this.killThread=killThread;
    }
    //Run Method
    @Override
    public void run(){//Consume from the first non empty queue or wait,Push the product after consuming in a random queue
        while(!killThread) {
            //Loop to find the first non empty queue or notify  all queues that the machine is ready
            if(m.getState()) { //if the current machine is in the working state
                int index=-1;
                for(int i=0;i< m.getQin().size();i++){
                    if(!m.getQin().get(i).getQueue().isEmpty()){
                        index=i;
                        break;
                    }
                }
                if(index!=-1){
                    LinkedBasedQ currentQin=m.getQin().get(index).getQueue();
                    synchronized (currentQin){
                        m.setCurrentProduct((Product)currentQin.dequeue());
                        try {
                            wait(this.m.getWorkingTime());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        m.getQout().enqueue(m.getCurrentProduct());
                    }
                }
                else {//Change the state of the machine to be ready and Notify the queues that the machine is ready
                    m.setState(false);
                }
            }
        }
    }

}
