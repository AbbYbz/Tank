package proxy.v3;

import java.util.Random;

// 静态代理
// 在v2基础上，实现代理的各种组合

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

        new TankTimeProxy(
                new TankLogProxy(
                        new Tank()
                )
        ).move();
    }
}

// 代理人1
class TankTimeProxy implements Moveble{

    Moveble m;

    public TankTimeProxy(Moveble m) {
        this.m = m;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        m.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

// 代理人2
class TankLogProxy implements Moveble{

    Moveble m;

    public TankLogProxy(Moveble m) {
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("start moving~");
        m.move();
        System.out.println("stopped!");
    }
}

interface Moveble{
    void move();
}