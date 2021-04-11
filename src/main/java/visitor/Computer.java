package visitor;

public class Computer {
    ComputerPart cpu = new CPU();
    ComputerPart memory = new Memory();
    ComputerPart board = new Board();

}


class CPU extends ComputerPart {
    @Override
    void accept(Visitor v) {
        v.visitCPU(this);
    }

    @Override
    double getPrice() {
        return 0;
    }
}

class Memory extends ComputerPart{
    @Override
    void accept(Visitor v) {
        v.visitMemory(this);
    }

    @Override
    double getPrice() {
        return 0;
    }
}


class Board extends ComputerPart{
    @Override
    void accept(Visitor v) {
        v.visitBorad(this);
    }

    @Override
    double getPrice() {
        return 0;
    }
}

