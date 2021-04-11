package proxy.v5;

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

    Moveble m;

    public LogHander(Moveble m) {
        this.m = m;
    }

    public void before(){
        System.out.println("method start..");
    }

    public void after(){
        System.out.println("method stop..");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(m, args);
        after();
        return o;
    }
}

interface Moveble{
    void move();
}