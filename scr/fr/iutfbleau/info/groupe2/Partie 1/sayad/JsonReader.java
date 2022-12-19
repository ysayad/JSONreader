import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

public class JsonReader{
    private String origin;
    private String output;

    // Constructeur qui génère le string
    public JsonReader(String path){
        this.origin = path;
        try {
            this.output = this.toStr(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Getter du json
    public String getOrigin(){
        return this.origin;
    }

    // Getter du string
    public String getString(){
        return this.output;
    }

    // Transforme le json en String
    public String toStr(String path) throws Exception {
            URL json = new URL(path);
            InputStream input = json.openStream();
            BufferedInputStream reader = new BufferedInputStream(input);
            
            StringBuilder output = new StringBuilder();
            while (reader.available() > 0) {
                output.append((char)reader.read());
            }

            reader.close();   

            return output.toString();
    }
}