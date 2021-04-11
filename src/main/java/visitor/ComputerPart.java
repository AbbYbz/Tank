package visitor;

abstract class ComputerPart {
    abstract void accept(Visitor v);
    abstract double getPrice();
}
