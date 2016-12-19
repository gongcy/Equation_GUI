package equation;

import static equation.BinaryOperation.UPPER;

import java.util.ArrayList;
import java.util.Random;

public class Exercise extends ArrayList<BinaryOperation> {

    private ArrayList<BinaryOperation> operationList = new ArrayList<BinaryOperation>();
    private int current = 0;
    /**
     * @value 0 - add, 1 - sub, 2 - mix
     */
    private int exerciseType = 2;

    public int getExerciseType() {
        return exerciseType;
    }

    public Exercise generateBinaryExercise(int operationCount, BinaryOperation[][] base) {
        operationList.clear();
        current = 0;
        BinaryOperation anOperation;
        Exercise anExercise = new Exercise();
        Random random = new Random();
        int row, colum;
        while (operationCount > 0) {
            do {
                row = random.nextInt(UPPER);
                colum = random.nextInt(UPPER);
                anOperation = base[row][colum];
            } while (anOperation == null || anExercise.contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
        return anExercise;
    }

    @Override
    public String toString() {
        return operationList.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public Exercise generateAdditionExercise(int operationCount) {
        operationList.clear();
        current = 0;
        exerciseType = 0;
        while (operationCount > 0) {
            OperationBase ob = new OperationBase();
            BinaryOperation[][] base = ob.produceAdditionBase();
            return generateBinaryExercise(operationCount, base);
        }
        return null;
    }

    public Exercise generateSubstractionExercise(int operationCount) {
        operationList.clear();
        current = 0;
        exerciseType = 1;
        while (operationCount > 0) {
            OperationBase ob = new OperationBase();
            BinaryOperation[][] base = ob.produceSubstractOperations();
            return generateBinaryExercise(operationCount, base);
        }
        return null;
    }

    public Exercise generateMixExercise(int operationCount) {
        operationList.clear();
        current = 0;
        exerciseType = 2;
        OperationBase ob = new OperationBase();
        BinaryOperation[][] addBase = ob.produceAdditionBase();
        BinaryOperation[][] subBase = ob.produceSubstractOperations();
        BinaryOperation anOperation;
        Exercise anExercise = new Exercise();
        Random random = new Random();
        int row, colum;
        while (operationCount > 0) {
            do {
                row = random.nextInt(UPPER);
                colum = random.nextInt(UPPER);
                if (random.nextInt(2) == 1) {
                    anOperation = addBase[row][colum];
                } else {
                    anOperation = subBase[row][colum];
                }
            } while (anOperation == null || anExercise.contains(anOperation));
            operationList.add(anOperation);
            operationCount--;
        }
        return anExercise;
    }

    public BinaryOperation getOperation(int index) {
        if (index < operationList.size()) {
            return operationList.get(index);
        }
        return null;
    }

    public boolean hasNext() {
        return current <= operationList.size() - 1;
    }

    public BinaryOperation next() {
        return operationList.get(current++);
    }

    public void moveToHead() {
        current = 0;
    }

    public void setCursor(int i) {
        current = i;
    }

    @Override
    public int size() {
        return operationList.size();
    }

    @Override
    public void clear() {
        operationList.clear();
    }

    @Override
    public boolean add(BinaryOperation e) {
        return operationList.add(e);
    }
}
