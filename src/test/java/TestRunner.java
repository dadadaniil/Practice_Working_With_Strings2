import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.MissingResourceException;
import static org.junit.jupiter.api.Assertions.*;

public class TestRunner {
    @Test
    public void testMainScenario() {
        class TestCase {
            private final String fileName;
            private final Main.Result result;

            public TestCase(String fileName, Main.Result result) {
                this.fileName = fileName;
                this.result = result;
            }

            public TestCase(String fileName, int errors, BigDecimal sum) {
                this.fileName = fileName;
                this.result = new Main.Result(errors, sum);
            }

            public String getFileName() {
                return fileName;
            }

            public Main.Result getResult() {
                return result;
            }
        }

        TestCase[] testCases = {
                new TestCase("in1", 3, new BigDecimal("8.24")),
                new TestCase("in2", 9, new BigDecimal("30.242")),
                new TestCase("in3", 0, new BigDecimal("1.9"))
        };
        for (TestCase testCase : testCases) {
            Main.Result result = Main.getResult(testCase.getFileName());
            assertEquals(result.getErrorLines(), testCase.getResult().getErrorLines());
            assertEquals(result.getSum(), testCase.getResult().getSum());
        }
    }

    @Test
    public void testWrongName() {
        assertThrows(MissingResourceException.class, () -> Main.getResult("in"));
    }
}