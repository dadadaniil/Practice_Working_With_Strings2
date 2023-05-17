import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String NAME_OF_FILE = "in%s";
    public static int AMOUNT_OF_FILES = 3;

    public static void main(String[] args) {


        for (int counter = 1; counter <= AMOUNT_OF_FILES; counter++) {
            String path = String.format(NAME_OF_FILE, counter);
            Locale locale = new Locale("en", "US");
            ResourceBundle rb = ResourceBundle.getBundle(path, locale);
            Enumeration<String> keys = rb.getKeys();

            String key;
            BigDecimal result = new BigDecimal(0);
            int misunderstandings = 0;


            while (keys.hasMoreElements()) {
                try {
                    key = keys.nextElement();
                    if (key.startsWith("index")) {
                        result = result.add(new BigDecimal((rb.getString("value" + getKeyByIndexLine(key, rb)).trim())));
                    }
                } catch (Exception wrongArgument) {
                    misunderstandings++;
                }
            }

            System.out.printf("sum = %s%nerror-lines = %d\n%n", result, misunderstandings);
        }
    }

    public static String getKeyByIndexLine(String lineToExtract, ResourceBundle bundle) {
        Pattern pattern = Pattern.compile("index(.*)");
        Matcher matcher = pattern.matcher(lineToExtract);
        String extractedString;

        if (matcher.find()) {
            extractedString = canWeUseThisDigit(matcher.group(1).trim());
            String key = canWeUseThisDigit(bundle.getString("index" + extractedString));
            return extractedString + key;
        } else {
            throw new IllegalArgumentException("Index is missing");
        }
    }

    public static String canWeUseThisDigit(String str) {
        if (!str.matches("-?\\d+")) {
            throw new IllegalArgumentException("Wrong format of index or key");
        }
        return str;
    }
}
