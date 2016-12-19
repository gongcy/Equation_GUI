package equation;

import java.util.Random;

public abstract class BinaryOperation {

    static final int UPPER = 100;
    static final int LOWER = 0;
    protected int left_operand = 0, right_operand = 0;
    protected char operator = '+';
    protected int value = 0;

    public int getLeftOprand() {
        return left_operand;
    }

    public BinaryOperation() {

    }

    public BinaryOperation(int left, int right) {
        this.left_operand = left;
        this.right_operand = right;
    }

    public int getRightOprand() {
        return right_operand;
    }

    public int getOperator() {
        return operator;
    }

    public int getResult() {
        return value;
    }

    protected void generateBinaryOperation(char anOperator) {
        int left, right, result;
        Random random = new Random();
        left = random.nextInt(UPPER);
        do {
            right = random.nextInt(UPPER);
            result = calculate(left, right);
        } while (!checkingCalculation(result));
        left_operand = left;
        right_operand = right;
        operator = anOperator;
        value = result;
    }

    abstract int calculate(int left, int right);

    abstract boolean checkingCalculation(int result);
    
    public abstract void contruct(String equation);

    public boolean equals(BinaryOperation anOperation) {
        return left_operand == anOperation.getLeftOprand()
                && right_operand == anOperation.getRightOprand()
                && operator == anOperation.getOperator();
    }

    public String toString() {
        String res = String.format("%2d %2c %2d\t", left_operand, operator,
                right_operand);
        return res;
    }

    public String asString() {
        String res = String.format("%d%c%d=", left_operand, operator,
                right_operand);
        return res;
    }

    public String fullString() {
        String res = String.format("%2d %2c %2d = %2d\t", left_operand,
                operator, right_operand, value);
        return res;
    }
}
