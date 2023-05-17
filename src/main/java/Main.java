import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String NAME_OF_FILE = "in%s";
    private static final int AMOUNT_OF_FILES = 3;
    public static final String INDEX = "index";
    public static final String VALUE = "value";

    public static void main(String[] args) {


        for (int counter = 1; counter <= AMOUNT_OF_FILES; counter++) {
            String path = String.format(NAME_OF_FILE, counter);
            ResourceBundle rb = ResourceBundle.getBundle(path, Locale.ENGLISH);
            Enumeration<String> keys = rb.getKeys();

            String key;
            BigDecimal result = new BigDecimal(0);
            int misunderstandings = 0;


            while (keys.hasMoreElements()) {
                try {
                    key = keys.nextElement();
                    if (key.startsWith(INDEX)) {
                        result = result.add(new BigDecimal((rb.getString(VALUE + getKeyByIndexLine(key, rb)).trim())));
                    }
                } catch (Exception wrongArgument) {
                    misunderstandings++;
                }
            }

            System.out.printf("sum = %s%nerror-lines = %d\n%n", result, misunderstandings);
        }
    }

    public static String getKeyByIndexLine(String lineToExtract, ResourceBundle bundle) {
        Pattern pattern = Pattern.compile(INDEX + "(.+)");
        Matcher matcher = pattern.matcher(lineToExtract);
        String extractedString;

        if (matcher.find()) {
            extractedString = canWeUseThisDigit(matcher.group(1).trim());
            String key = canWeUseThisDigit(bundle.getString(lineToExtract));
            return extractedString + key;
        } else {
            throw new IllegalArgumentException("Index is missing");
        }
    }

    public static String canWeUseThisDigit(String str) {
        if (!str.matches("([1-9])([0-9])*")) {
            throw new IllegalArgumentException("Wrong format of index or key");
        }
        return str;
    }
}