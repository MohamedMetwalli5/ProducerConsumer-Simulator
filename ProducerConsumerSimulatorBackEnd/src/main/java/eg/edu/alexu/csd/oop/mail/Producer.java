package eg.edu.alexu.csd.oop.mail;
import java.awt.*;
import java.util.Random;

public class Producer implements Runnable {
    LinkedBasedQ q0;
    int productionTime;
    public Producer(LinkedBasedQ q0){
        this.q0=q0;
        productionTime=(int)(Math.random()*((5000-500+1)+500));
    }

    //TODO A better way to get the random color of the Product
    public void run(){
            synchronized (q0) {
                Product producedProduct = new Product(generateRandomColor());
                try {
                    Thread.sleep(productionTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Product Color "+producedProduct.getProductColor().toString() +" Has been Produced");
                q0.enqueue(producedProduct);
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
