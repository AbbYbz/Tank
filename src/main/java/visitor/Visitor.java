package visitor;

public interface Visitor {
    void visitCPU(CPU cpu);
    void visitMemory(Memory memory);
    void visitBorad(Board board);
}
