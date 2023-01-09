

import javax.swing.*;
import java.awt.*;

public class Fenetre {

    public static void main(String[] args) {
        JFrame fenetre = new JFrame();
        
        JPanel zoneTxt = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        //Création de l'AST
        JsonString str = new JsonString("file:ex1.json");
        JsonParser parser = new JsonParser(str);
        JsonTree tree = new JsonTree(parser);

        while (!tree.isEmpty()) {
            JTextArea ligne = new JTextArea(tree.afficher(0));
            ligne.setEditable(false);
            zoneTxt.add(ligne);
        }

        JPanel zoneNbr = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JTextArea testLigne = new JTextArea("1.", 1, 10);
        testLigne.setEditable(false);
        zoneNbr.add(testLigne);
        //fenetre.add(zoneNbr);

        fenetre.add(zoneTxt);


        for (int i = 0; i < zoneTxt.getComponentCount(); i++) {
            
        }

        
        





        fenetre.setSize(1000,1000);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    

}
