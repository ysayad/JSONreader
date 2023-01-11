package src.fr.iutfbleau.projetJson.ainspecteur.Vue.display;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import src.fr.iutfbleau.projetJson.ainspecteur.Modele.*;


public class Display extends JFrame {
    JPanel menu;
    String content;
    JFrame window;
    public String path;

    JsonTree tree;

    public Display(JFrame window, String path){
        this.window = window;
        this.menu = new JPanel();
        this.menu.setBackground(new Color(150, 150, 150));
        this.menu.setLayout(new BorderLayout());
        this.path = path;

        JsonString string = new JsonString(path);
        JsonParser parser = new JsonParser(string);
        JsonTree tree = new JsonTree(parser);

        this.tree = tree;
    }

    public JPanel drawDisplay(){
        JPanel compteur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        int cpt = 0;
        JPanel lignes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        compteur.setPreferredSize(new Dimension(50, this.window.getHeight()));
        lignes.setPreferredSize(new Dimension(900, this.window.getHeight()));


        while (!tree.isEmpty()) {
            cpt++;
            MaillonTree token = tree.remove();
            JTextPane ligne = new JTextPane();
            addColoredText(ligne, token.getValeur(), pickColor(token));
            System.out.println(token.getValeur() + " " + token.getType().toString());
            ligne.setEditable(false);
            lignes.add(ligne);
            // JTextArea numero = new JTextArea(Integer.toString(cpt));
            // numero.setEditable(false);
            // numero.setBorder(null);
            // compteur.add(numero);
        }

        this.menu.add(compteur, BorderLayout.WEST);
        this.menu.add(lignes, BorderLayout.CENTER);
        this.menu.setPreferredSize(new Dimension(1000, this.window.getHeight()));
        return this.menu;
    }

    private void addColoredText(JTextPane pane, String text, Color color) {
        StyledDocument doc = pane.getStyledDocument();

        Style style = pane.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);
        try {
            doc.insertString(doc.getLength(), text, style);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        }           
    }

    private Color pickColor(MaillonTree maillon){
        switch (maillon.getType()) {
            case START_OBJECT:
            case END_OBJECT:
            case START_ARRAY:
            case END_ARRAY:
                return Color.MAGENTA;
            case KEY_NAME:
                return Color.CYAN;
            case VALUE_NUMBER:
                return Color.GREEN;
            case VALUE_STRING:
                return Color.ORANGE;
            case VALUE_TRUE:
            case VALUE_FALSE:
            case VALUE_NULL:
                return Color.BLUE;
            default:
                return Color.MAGENTA;
        }
    }

    
}
