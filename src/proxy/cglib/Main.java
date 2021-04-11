package proxy.cglib;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

    }
}

class TimeMehodInterceptor{

}

class Tank{
    public void move() {

        System.out.println("moving~");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}