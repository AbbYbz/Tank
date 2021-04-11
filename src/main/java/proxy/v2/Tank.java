package proxy.v2;

import java.util.Random;

public class Tank implements Moveble{
    @Override
    public void move() {

        System.out.println("moving~");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TankTimeProxy(new Tank()).move();
    }
}

// 代理人1
class TankTimeProxy implements Moveble{

    Tank tank;

    public TankTimeProxy(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        tank.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

// 代理人2
class TankLogProxy implements Moveble{

    Tank tank;

    public TankLogProxy(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void move() {
        System.out.println("start moving~");
        tank.move();
        System.out.println("stopped!");
    }
}

interface Moveble{
    void move();
}