package main.java.eg.edu.alexu.csd.oop.mail;

import java.awt.*;
import java.util.Random;

public class Producer implements Runnable {
    LinkedBasedQ q0;
    int productionTime;
    Color randomColor;
    public Producer(LinkedBasedQ q0){
        this.q0=q0;
        productionTime=(int)((Math.random()*((5000-2000+1)))+2000);
        randomColor=generateRandomColor();
    }

    //TODO A better way to get the random color of the Product
    public void run(){
            Product producedProduct = new Product(randomColor);
            try {
                Thread.sleep(productionTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Product Color "+producedProduct.getProductColor().toString() +" Has been Produced");
            q0.enqueue(producedProduct);
    }
    public Color generateRandomColor(){
        Random rand = new Random();
        int r =rand.nextInt(255);
        int g =rand.nextInt(255);
        int b =rand.nextInt(255);
        Color productColor = new Color(r,g,b);
        return productColor;
    }
}
