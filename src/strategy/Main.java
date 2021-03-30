package strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Cat[] a = {new Cat(3,3), new Cat(5,5), new Cat(1,1)};
        Sorter<Cat> sorter = new Sorter<>();
//        sorter.sort(a, new CatWeightComparator());
//        Sorter<Dog> sorter = new Sorter<>();
//        Dog[] a = {new Dog(1),new Dog(5),new Dog(2)};
//        sorter.sort(a, new DogComparator());

        // lambda写法
        sorter.sort(a, ((o1, o2) -> {
            if(o1.weight<o2.weight) return -1;
            else if (o1.weight> o2.weight) return 1;
            else return 0;
        }));
        System.out.println(Arrays.toString(a));
    }
}
