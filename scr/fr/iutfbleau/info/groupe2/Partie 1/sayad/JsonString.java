import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonString{
    private String origin;
    private String str;

    // Constructeur qui génère le string
    public JsonString(String path){
        this.origin = path;

        URL json;
        StringBuilder output = new StringBuilder();
        try {
            json = new URL(origin);
            InputStream input = json.openStream();
            BufferedInputStream reader = new BufferedInputStream(input);
            
            while (reader.available() > 0) {
                output.append((char)reader.read());
            }

            reader.close();   
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        this.str = output.toString();
    }

    @Override
    public String toString() {
        return str;
    }
}