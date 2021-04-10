package observer.v1;


class Child {
    private boolean cry = false;
    private Dad dad = new Dad();
    private Mom mom = new Mom();
    private Dog dog = new Dog();


    public boolean isCry(){
        return cry;
    }

    public void wakeup(){
        cry = true;
        dad.feed();
        mom.hug();
        dog.wang();
    }

}

// 观察者1
class Dad {
    public void feed(){
        System.out.println("dad feeding ~");
    }
}

// 观察者2
class Mom {
    public void hug(){
        System.out.println("mom hugging ~");
    }
}

// 观察者3
class Dog {
    public void wang(){
        System.out.println("dog wang ~");
    }
}


public class Main1 {

    public static void main(String[] args) {
        Child c = new Child();
        c.wakeup();
    }
}
