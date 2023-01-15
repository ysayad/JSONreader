package src.fr.iutfbleau.projetJson.ainspecteur.Vue.display;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import src.fr.iutfbleau.projetJson.ainspecteur.Modele.JsonTree;
import src.fr.iutfbleau.projetJson.ainspecteur.Modele.JsonType;
import src.fr.iutfbleau.projetJson.ainspecteur.Modele.MaillonTree;
import src.fr.iutfbleau.projetJson.ainspecteur.Vue.CheminListener;
import src.fr.iutfbleau.projetJson.ainspecteur.Vue.Menu;
import src.fr.iutfbleau.projetJson.ainspecteur.Vue.ParcourirListener;



public class TextListener implements MouseListener {

    private JFrame window;
    private JPanel vertical;
    private JsonTree arbre;
    private int n;
    private MaillonTree dernier;
    private int compte;

    public TextListener(JFrame window, JPanel vertical, JsonTree arbre, int n) {

        this.window = window;
        this.vertical = vertical;
        this.arbre = arbre;
        this.n = n;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        arbre.changement(n);
        arbre.reconstruire();
        System.out.println("test");
        

        MaillonTree token = arbre.getNoeud();
        JPanel lignes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));

        this.vertical.removeAll();

        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));

        refresh(token, vertical, arbre, lignes);

        this.vertical.revalidate();
        this.vertical.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    private void refresh(MaillonTree noeud, JPanel vertical, JsonTree tree, JPanel ligne){
        String indent="";
        MaillonTree m=this.dernier,souvenir=new MaillonTree();
        while(!noeud.isEmpty()){
            m=noeud.remove();
            if(souvenir.getType()==JsonType.KEY_NAME){
                ligne.add(new JLabel(": "));
            }
            if(m.isNoeud()){
                n++;
                if(m.getType()==JsonType.OPEN){
                    souvenir=m;
                    this.dernier=m;

                    refresh(m, vertical, tree, ligne);
                }
                if(m.getType()==JsonType.CLOSE){
                    souvenir=m;
                    this.dernier=m;
                    for(int j=0;j<this.compte;j++){
                        indent=indent+"    ";
                    }
                    vertical.add(ligne);
                    ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    ligne.add(new JLabel(indent));
                    JLabel close = new JLabel(m.getValeur());
                    close.addMouseListener(new TextListener(this.window, vertical, tree, n));
                    ligne.add(close);
                }
            }
            if(!m.isNoeud()){
                JLabel actual = new JLabel(m.getValeur());
                pickColor(actual, m);
                if(m.getType()==JsonType.END_OBJECT || m.getType()==JsonType.END_ARRAY){
                    this.compte--;
                }for(int i=0;i<this.compte;i++){
                    indent=indent+"    ";
                }if(m.getType()==JsonType.START_OBJECT || m.getType()==JsonType.START_ARRAY){
                    this.compte++;
                }
                if((m.getType()==JsonType.VALUE_STRING || m.getType()==JsonType.VALUE_NUMBER || m.getType()==JsonType.VALUE_NULL || m.getType()==JsonType.VALUE_TRUE || m.getType()==JsonType.VALUE_FALSE)&& souvenir.getType()!=JsonType.KEY_NAME){
                    if(souvenir.getType()!=JsonType.START_ARRAY){
                        ligne.add(new JLabel(","));
                    }
                    vertical.add(ligne);
                    ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    ligne.add(new JLabel(indent));
                }if(m.getType()==JsonType.KEY_NAME){
                    if(souvenir.getType()!=JsonType.START_OBJECT){
                        ligne.add(new JLabel(","));  
                    }
                    vertical.add(ligne);
                    ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    ligne.add(new JLabel(indent));
                }
                if(m.getType()==JsonType.START_ARRAY || m.getType()==JsonType.START_OBJECT){
                    //System.out.println(souvenir.getType());
                    if(this.dernier.getType()==JsonType.END_ARRAY || this.dernier.getType()==JsonType.END_OBJECT){
                        ligne.add(new JLabel(",")); 
                    }
                    vertical.add(ligne);
                    ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    ligne.add(new JLabel(indent));
                }
                if(m.getType()==JsonType.END_ARRAY || m.getType()==JsonType.END_OBJECT){
                    vertical.add(ligne);
                    ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    ligne.add(new JLabel(indent));
                }
                ligne.add(actual);
                souvenir=m;
            }
            indent="";
        }
        this.dernier=m;
    }

    private void pickColor(JLabel label, MaillonTree maillon){
        switch (maillon.getType()) {
            case START_OBJECT:
            case END_OBJECT:
            case START_ARRAY:
            case END_ARRAY:
                label.setForeground(Color.BLACK);
                break;
            case KEY_NAME:
                label.setForeground(Color.RED);
                break;
            case VALUE_NUMBER:
                label.setForeground(Color.GREEN);
                break;
            case VALUE_STRING:
                label.setForeground(Color.ORANGE);
                break;
            case VALUE_TRUE:
            case VALUE_FALSE:
            case VALUE_NULL:
                label.setForeground(Color.BLUE);
                break;
            default:
                label.setForeground(Color.MAGENTA);
                break;
        }
    }
    
}
