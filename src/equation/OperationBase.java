package equation;

import static equation.BinaryOperation.LOWER;
import static equation.BinaryOperation.UPPER;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class OperationBase {

    public AdditionOperation[][] produceAdditionBase() {
        AdditionOperation[][] base = new AdditionOperation[BinaryOperation.UPPER + 1][UPPER + 1];
        AdditionOperation ao;
        for (int i = 0; i <= UPPER; i++) {
            for (int j = 0; j <= UPPER; j++) {
                if (i + j <= UPPER) {
                    ao = new AdditionOperation(i, j);
                    base[i][j] = ao;
                }
            }
        }
        return base;
    }

    public SubstractOperation[][] produceSubstractOperations() {
        SubstractOperation[][] base = new SubstractOperation[UPPER + 1][UPPER + 1];
        SubstractOperation so;
        for (int i = 0; i <= UPPER; i++) {
            for (int j = 0; j <= UPPER; j++) {
                if (i - j >= LOWER) {
                    so = new SubstractOperation(i, j);
                    base[i][j] = so;
                }
            }
        }
        return base;
    }

    public void displayBase(BinaryOperation[][] base) {
        for (BinaryOperation[] row : base) {
            for (BinaryOperation operation : row) {
                if (operation != null) {
                    System.out.print(operation.asString());
                }
            }
            System.out.println();
        }
    }

    public void generateBaseFile(BinaryOperation[][] base, String filename)
            throws IOException {
        File file = new File(filename);
        Writer out = new FileWriter(file);
        for (BinaryOperation[] row : base) {
            for (BinaryOperation operation : row) {
                if (operation != null) {
                    out.write(operation.asString());
                }
            }
        }
        out.flush();
        out.close();
    }
}
