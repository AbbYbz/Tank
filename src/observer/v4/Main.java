package observer.v4;

import java.util.ArrayList;
import java.util.List;

// 观察者模式 (监听)
interface Observer{
    void actionOnWakeUp(wakeUpEvent event);
}

abstract class Event<T>{
    abstract T getSource();
}

// 具体事件
// 获取 事件原对象
class wakeUpEvent extends Event<Child>{
    long timestamp;
    String loc;
    Child source;

    public wakeUpEvent(long timestamp, String loc, Child source){
        this.timestamp = timestamp;
        this.loc = loc;
        this.source =source;
    }

    @Override
    Child getSource() {
        return source;
    }
}

// 事件源对象（被观察者）
class Child {
    private boolean cry = false;
    private List<Observer> observers = new ArrayList<>();
    {
        observers.add(new Dad());
        observers.add(new Mom());
        observers.add(new Dog());
    }

    public boolean isCry(){
        return cry;
    }

    public void wakeup(){
        cry = true;

        wakeUpEvent event = new wakeUpEvent(System.currentTimeMillis(),"bed", this);

        for (Observer o :observers){
            o.actionOnWakeUp(event);
        }
    }

}

// 观察者1
class Dad implements Observer {
    public void feed(){
        System.out.println("dad feeding ~");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        feed();
    }

}

// 观察者2
class Mom implements Observer{
    public void hug(){
        System.out.println("mom hugging ~");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        hug();
    }
}

// 观察者3
class Dog implements Observer {
    public void wang(){
        System.out.println("dog wang ~");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        wang();
    }
}


public class Main {

    public static void main(String[] args) {
        Child c = new Child();
        c.wakeup();
    }
}
