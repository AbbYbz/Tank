package observer.v2;

import java.util.ArrayList;
import java.util.List;

// 观察者模式 (监听)
interface Observer{
    void actionOnWakeUp();
}

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
        for (Observer o :observers){
            o.actionOnWakeUp();
        }
    }

}

// 观察者1
class Dad implements Observer {
    public void feed(){
        System.out.println("dad feeding ~");
    }

    @Override
    public void actionOnWakeUp() {
        feed();
    }
}

// 观察者2
class Mom implements Observer{
    public void hug(){
        System.out.println("mom hugging ~");
    }

    @Override
    public void actionOnWakeUp() {
        hug();
    }
}

// 观察者3
class Dog implements Observer {
    public void wang(){
        System.out.println("dog wang ~");
    }

    @Override
    public void actionOnWakeUp() {
        wang();
    }
}


public class Main {

    public static void main(String[] args) {
        Child c = new Child();
        c.wakeup();
    }
}
