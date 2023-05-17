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
    public static final String INDEX = "index";
    public static final String VALUE = "value";
    private static final String INDEX_PATTERN = INDEX + "(.+)";
    private static final String CORRECT_NUMBER_PATTERN = "([1-9])([0-9])*";
    public static final String SUM_TEXT = "sum = ";
    public static final String ERRORS_TEXT = "\nerror-lines = ";


    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();

        try {
            for (int counter = 1; counter <= AMOUNT_OF_FILES; counter++) {
                String path = String.format(NAME_OF_FILE, counter);
                ResourceBundle rb = ResourceBundle.getBundle(path, Locale.ENGLISH);
                int errors = searchForCorrectValues(builder, rb);
                builder.append(ERRORS_TEXT).append(errors);
                builder.append("\n\n");
            }
        } catch (MissingResourceException e) {
            System.out.println("File not found: " + e);
        }
        System.out.println(builder);
    }

    public static int searchForCorrectValues(StringBuilder builder, ResourceBundle bundle) {
        Enumeration<String> keys = bundle.getKeys();

        String key;
        BigDecimal result = new BigDecimal(0);
        int misunderstandings = 0;

        while (keys.hasMoreElements()) {
            try {
                key = keys.nextElement();
                if (key.startsWith(INDEX)) {
                    result = result.add(new BigDecimal((bundle.getString(VALUE + getKeyByIndexLine(key, bundle)).trim())));
                }
            } catch (Exception wrongArgument) {
                misunderstandings++;
            }
        }
        builder.append(SUM_TEXT).append(result);
        return misunderstandings;
    }

    public static String getKeyByIndexLine(String lineToExtract, ResourceBundle bundle) {
        Pattern pattern = Pattern.compile(INDEX_PATTERN);
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

        if (!str.matches(CORRECT_NUMBER_PATTERN)) {
            throw new IllegalArgumentException("Wrong format of index or key");
        }
        return str;
    }
}