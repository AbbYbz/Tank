package proxy.v4;

import sun.rmi.runtime.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

// 如果想让LogProxy可以重用，不仅可以代理Tank，还可以代理任何其他可以代理的类型Object
// how?
// 分离代理行为和被代理对象
// 使用jdk的动态代理

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

        Tank tank = new Tank();

        // reflection 反射，通过二进制字节码分析类的属性和方法
        // 生成动态代理
        Moveble m = (Moveble) Proxy.newProxyInstance(
                Tank.class.getClassLoader(),
                new Class[]{Moveble.class}, //接口数组
                new LogHander(tank)
        );

        m.move();
    }
}

class LogHander implements InvocationHandler{

    Tank tank;

    public LogHander(Tank tank) {
        this.tank = tank;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method "+method.getName()+" start..");
        Object o = method.invoke(tank, args);
        System.out.println("method "+method.getName()+" end!");
        return o;
    }
}

interface Moveble{
    void move();
}