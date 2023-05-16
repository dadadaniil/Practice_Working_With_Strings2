import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String NAME_OF_FILE = "in%s";
    public static int ANOUT_OF_FILES = 3;

    public static void main(String[] args) {


        Locale locale = new Locale("en", "US");
        ResourceBundle rb = ResourceBundle.getBundle("in", locale);
        Enumeration<String> keys = rb.getKeys();
        String key;
        System.out.println();

        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            System.out.println(key);
        }
        System.out.println("l");

        System.out.println(rb.getString("value12"));
        ;

//        System.out.println(getValueKey("index   1    = 3"));;
    }

    public static String getValueKey(String lineToExtract, ResourceBundle bundle) {
        Pattern pattern = Pattern.compile("index(.*?)=");
        Matcher matcher = pattern.matcher(lineToExtract);
        String extractedString;

        if (matcher.find()) {
            extractedString = matcher.group(1).trim();
            System.out.println(extractedString); // Output: "1"

        }
        return "nope";

    }
}
