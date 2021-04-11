package visitor;

public class PersonelVisitor implements Visitor {

    double totalPrice = 0;
    @Override
    public void visitCPU(CPU cpu) {
        totalPrice = cpu.getPrice()*0.9;
    }

    @Override
    public void visitMemory(Memory memory) {

    }

    @Override
    public void visitBorad(Board board) {

    }
}
