import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Lecture{
    public Lecture(){}

    public static void main(String[] args) {
        StringBuilder output = new StringBuilder();
        
        try {
            URL json = new URL(args[0]);
            InputStream input = json.openStream();
            
            while (input.available() > 0) {
                output.append(input.read());
            }

            System.out.println(output.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
}