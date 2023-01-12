package src.fr.iutfbleau.projetJson.ainspecteur.Vue;

import src.fr.iutfbleau.projetJson.ainspecteur.Modele.*;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            Fenetre fenetre = new Fenetre();
            fenetre.draw();
        } else {
            JsonString str = new JsonString(args[0]);
            JsonParser parser = new JsonParser(str);
            JsonTree tree = new JsonTree(parser);
            JsonFilter r = new JsonFilter(tree);
            System.out.print(r.toString());
            System.out.println(System.getProperty("user.dir"));
        }
    }
}
