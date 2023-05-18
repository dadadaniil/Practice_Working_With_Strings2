import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final String NAME_OF_FILE = "in%s";
    private static final int AMOUNT_OF_FILES = 3;
    public static final String SUM_TEXT = "sum = ";
    public static final String ERRORS_TEXT = "\nerror-lines = ";


    public static void main(String[] args) {

        for (int counter = 1; counter <= AMOUNT_OF_FILES; counter++) {
            String fileName = String.format(NAME_OF_FILE, counter);
            try {
                Result result = getResult(fileName);
                System.out.println(SUM_TEXT + result.getSum() + ERRORS_TEXT + result.getErrorLines());
                System.out.println();
            } catch (MissingResourceException e) {
                System.out.println("File not found: " + e);
            }
        }
    }

    protected static class Result {

        private final int errorLines;
        private final BigDecimal sum;

        public Result(int errorLines, BigDecimal sum) {
            this.errorLines = errorLines;
            this.sum = sum;
        }

        public Result() {
            this.errorLines = 0;
            this.sum = null;
        }

        public int getErrorLines() {
            return errorLines;
        }

        public BigDecimal getSum() {
            return sum;
        }
    }

    protected static Result getResult(String fileName) throws MissingResourceException {

        ResourceBundle rb = ResourceBundle.getBundle(fileName, Locale.ENGLISH);
        Enumeration<String> keys = rb.getKeys();

        final String KEY_REG_EXP = "index(.*)";
        final String NUM_REG_EXP = "[1-9]\\d*";
        Pattern pattern1 = Pattern.compile(KEY_REG_EXP);
        Pattern pattern2 = Pattern.compile(NUM_REG_EXP);
        final int TAIL_INDEX = 1;
        final String VALUE = "value";
        int errors = 0;
        String key;
        String iStr;
        String jStr;
        String valueIJ;
        BigDecimal sum = new BigDecimal(0);

        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            Matcher keyMatcher = pattern1.matcher(key);

            if (keyMatcher.matches()) {
                iStr = keyMatcher.group(TAIL_INDEX);
                jStr = rb.getString(key).trim();

                Matcher iMatcher = pattern2.matcher(iStr);
                Matcher jMatcher = pattern2.matcher(jStr);

                if (iMatcher.matches() && jMatcher.matches()) {
                    valueIJ = VALUE + iStr + jStr;
                    try {
                        sum = sum.add(new BigDecimal(rb.getString(valueIJ)));
                    } catch (Exception wrongArgument) {
                        errors++;
                    }
                } else {
                    errors++;
                }
            }
        }
        return new Result(errors, sum);
    }
}