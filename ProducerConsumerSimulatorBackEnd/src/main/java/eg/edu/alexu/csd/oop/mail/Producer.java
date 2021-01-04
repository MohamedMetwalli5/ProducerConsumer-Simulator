package eg.edu.alexu.csd.oop.mail;
import java.awt.*;
import java.util.Random;

public class Producer implements Runnable {
    LinkedBasedQ q0=new LinkedBasedQ();

    //TODO A better way to get the random color of the Product
    public void run(){
            synchronized (q0) {
                Product producedProduct = new Product(generateRandomColor());
                q0.enqueue(producedProduct);
               //We call q0.notifyAll in the mainClass //TODO is there a better way ?
            }
    }
    public Color generateRandomColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color productColor = new Color(r, g, b);
        return productColor;
    }

}
