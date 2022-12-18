import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Lecture{
    private String path;
    private String output;

    public Lecture(String path){
        this.path = path;
    }

    public static void main(String[] args) throws Exception {
            URL json = new URL(args[0]);
            InputStream input = json.openStream();
            BufferedInputStream reader = new BufferedInputStream(input);
            
            StringBuilder output = new StringBuilder();
            while (reader.available() > 0) {
                output.append((char)reader.read());
            }

            System.out.println(output.toString());

            reader.close();    
    }
}