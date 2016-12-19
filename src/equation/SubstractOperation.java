package equation;

public class SubstractOperation extends BinaryOperation {
    
    public SubstractOperation() {
        generateBinaryOperation('-');
    }

    public SubstractOperation(int left, int right) {
        super(left, right);
        this.operator = '-';
        this.value = left - right;
        generateBinaryOperation('-');
    }

    @Override
    int calculate(int left, int right) {
        return left - right;
    }

    @Override
    public boolean checkingCalculation(int anInteger) {
        return anInteger >= LOWER;
    }

    @Override
    public void contruct(String equation) {
        this.operator = '-';
        this.left_operand = Integer.parseInt(equation.split("=")[0].split("\\-")[0]);
        this.right_operand = Integer.parseInt(equation.split("=")[0].split("\\-")[1]);
        this.value = left_operand - right_operand;
    }

}
