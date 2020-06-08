import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.cdv.Calculator;

import java.io.FileReader;
import java.io.IOException;

public class CalculatorTest {
    // Kalkulator z danymi przekazanymi przez testng
    @Parameters({"x", "y", "result"})
    @Test
    public void calculatorTest(int x, int y, int result) {

        int wynik = Calculator.add(x, y);
        Assert.assertEquals(wynik, result);
    }

    // Kalkulator z danymi przekazanymi z pliku .csv
    @Test
    public void calculatorTestCSV() {
        try {
            CSVReader reader = new CSVReader(new FileReader("./src/test/java/data/dane.csv"));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                int x = Integer.parseInt(nextLine[1]);
                int y = Integer.parseInt(nextLine[2]);
                int wynikzcsv = Integer.parseInt(nextLine[3]);
                String operacja = (nextLine[0]);
                int wynik = 0;

                if (operacja.equals("*")) {
                    wynik = Calculator.multiply(x, y);

                } else if (operacja.equals("-")) {
                    wynik = Calculator.sub(x, y);

                } else if (operacja.equals("/")) {
                    wynik = Calculator.div(x, y);

                } else {
                    wynik = Calculator.add(x, y);

                }

                Assert.assertEquals(wynik, wynikzcsv);
            }
        } catch (IOException | RuntimeException exception) {
            System.out.println(exception.getMessage());
        }

    }


}
