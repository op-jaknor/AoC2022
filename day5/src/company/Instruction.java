package company;

public class Instruction {
    public final int source;
    public final int target;
    public final int amount;

    public Instruction(final int source, final int target, final int amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }
}
