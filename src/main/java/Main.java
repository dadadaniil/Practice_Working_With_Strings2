import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        String filePath="C:\\Users\\Dadadaniil\\IdeaProjects\\Practice_Working_With_Strings2\\src\\main\\resources\\in1.properties";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getResult(){
    }

}
