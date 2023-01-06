import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Lecture{

    public Lecture(){}

    public static void main(String[] args) throws FileNotFoundException, IOException {

        

    List<String> contenu = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("test.json"))) {
        String ligne;
        while ((ligne = reader.readLine()) != null) {
        contenu.add(ligne.replace(":", ": "));
        }
    }
    System.out.println(contenu);
    }
}

    