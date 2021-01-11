package eg.edu.alexu.csd.oop.mail;

public class MainMain {
    static class prod implements Runnable {
        LinkedBasedQ q0;

        public prod(LinkedBasedQ q0) {
            this.q0 = q0;
        }

        public void run() {
            synchronized (this) {
                try {
                    this.wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                q0.enqueue("Hello World");
            }
        }
    }
    static class cons implements Runnable{
        LinkedBasedQ q0;
        public cons(LinkedBasedQ q0){
            this.q0=q0;
        }
        public void run() {
            for(;;){
                if(!q0.isEmpty()){
                    System.out.println((String) q0.dequeue());
                }
            }
        }
    }
public static void main(String [] args){
    LinkedBasedQ q0=new LinkedBasedQ();
    prod p=new prod(q0);
    cons q=new cons(q0);
    Thread prod=new Thread(p);
    Thread cons=new Thread(q);
    prod.start();
    cons.start();
}
}
