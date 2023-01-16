package src.fr.iutfbleau.projetJson.ainspecteur.Vue.display;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import src.fr.iutfbleau.projetJson.ainspecteur.Modele.JsonTree;
import src.fr.iutfbleau.projetJson.ainspecteur.Modele.JsonType;
import src.fr.iutfbleau.projetJson.ainspecteur.Modele.MaillonTree;



public class TextListener implements MouseListener {

    private JFrame window;
    private JPanel vertical;
    private JsonTree arbre;
    private int n;
    private JPanel ligne; 
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
        this.arbre.changement(n-1);
        this.arbre.modifier();

        MaillonTree token = arbre.getNoeud();

        this.vertical.removeAll();
        this.vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));

        this.ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
        refresh(token, vertical, arbre);

        this.vertical.add(this.ligne);
        this.vertical.revalidate();
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
    
    private void refresh(MaillonTree noeud, JPanel vertical, JsonTree tree){
        String indent="";
        MaillonTree m=this.dernier,souvenir=new MaillonTree();
        while(!noeud.isEmpty()){
            m=noeud.remove();
            if(souvenir.getType()==JsonType.KEY_NAME){
                this.ligne.add(new JLabel(": "));
            }
            if(m.isNoeud()){
                n++;
                if(m.getType()==JsonType.OPEN){
                    souvenir=m;
                    this.dernier=m;

                    refresh(m, vertical, tree);
                }
                if(m.getType()==JsonType.CLOSE){
                    souvenir=m;
                    this.dernier=m;
                    for(int j=0;j<this.compte;j++){
                        indent=indent+"    ";
                    }
                    vertical.add(this.ligne);
                    this.ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    this.ligne.add(new JLabel(indent));
                    JLabel close = new JLabel(m.getValeur());
                    close.addMouseListener(new TextListener(this.window, vertical, arbre, n));
                    this.ligne.add(close);
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
                    actual.addMouseListener(new TextListener(this.window, vertical, arbre, n));
                }
                if((m.getType()==JsonType.VALUE_STRING || m.getType()==JsonType.VALUE_NUMBER || m.getType()==JsonType.VALUE_NULL || m.getType()==JsonType.VALUE_TRUE || m.getType()==JsonType.VALUE_FALSE)&& souvenir.getType()!=JsonType.KEY_NAME){
                    if(souvenir.getType()!=JsonType.START_ARRAY){
                        this.ligne.add(new JLabel(","));
                    }
                    vertical.add(this.ligne);
                    this.ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    this.ligne.add(new JLabel(indent));
                }if(m.getType()==JsonType.KEY_NAME){
                    if(souvenir.getType()!=JsonType.START_OBJECT){
                        this.ligne.add(new JLabel(","));  
                    }
                    vertical.add(this.ligne);
                    this.ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    this.ligne.add(new JLabel(indent));
                }
                if(m.getType()==JsonType.START_ARRAY || m.getType()==JsonType.START_OBJECT){
                    //System.out.println(souvenir.getType());
                    if(this.dernier.getType()==JsonType.END_ARRAY || this.dernier.getType()==JsonType.END_OBJECT){
                        this.ligne.add(new JLabel(",")); 
                    }
                    vertical.add(this.ligne);
                    this.ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    this.ligne.add(new JLabel(indent));
                }
                if(m.getType()==JsonType.END_ARRAY || m.getType()==JsonType.END_OBJECT){
                    vertical.add(this.ligne);
                    this.ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    this.ligne.add(new JLabel(indent));
                }
                this.ligne.add(actual);
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
