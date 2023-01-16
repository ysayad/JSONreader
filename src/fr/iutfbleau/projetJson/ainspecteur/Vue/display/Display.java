package src.fr.iutfbleau.projetJson.ainspecteur.Vue.display;
import src.fr.iutfbleau.projetJson.ainspecteur.Modele.*;
import java.awt.*;

import javax.swing.*;



public class Display extends JFrame {

    private JPanel menu;
    private String content;
    private JFrame window;
    public String path;
    private JPanel ligne; 

    private MaillonTree dernier;
    private int compte;
    private int n;

    JsonTree tree;
    JsonTree copie;

    public Display(JFrame window, String path){
        this.window = window;

        this.menu = new JPanel();
        this.menu.setBackground(new Color(150, 150, 150));
        this.menu.setLayout(new BorderLayout());
        this.path = path;

        JsonString string = new JsonString(path);
        Parser parser = new JsonParser(string);
        this.tree = new JsonTree(parser);

        this.dernier = new MaillonTree(null, null);

        JsonString string2 = new JsonString(path);
        Parser parser2 = new JsonParser(string2);
        this.copie = new JsonTree(parser2);

        this.n = 0;
    }


    public JScrollPane drawDisplayPHP(){
        PHPFilter phpfiltrer = new PHPFilter(this.tree);
        JPanel contenu = new JPanel();
        contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));



        String chaine = phpfiltrer.toString();
        String stock = "";


        for(int i = 0;chaine.length()>i; i+=1 ){
            if (chaine.charAt(i) == '\n'){
                System.out.println(stock);
                contenu.add(new JLabel(stock));
                stock ="";
            }else{
                char[] c= {chaine.charAt(i)};
                chaine.charAt(i);
                stock+=new String(c);
            }
        }

        JScrollPane container = new JScrollPane(contenu);
        return container;

    }


    public JScrollPane drawDisplay(){
        JPanel vertical = new JPanel();

        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));

        this.ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));

        MaillonTree token = tree.getNoeud();
        

        filter(token, vertical);


        vertical.add(this.ligne);

        this.menu.add(vertical);

        JScrollPane jsp = new JScrollPane(menu);
        jsp.setHorizontalScrollBarPolicy(jsp.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.getVerticalScrollBar().setUnitIncrement(16);
        jsp.setBorder(BorderFactory.createEmptyBorder());

        return jsp;
    }

    private void filter(MaillonTree noeud, JPanel vertical){
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

                    filter(m, vertical);
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
                    close.addMouseListener(new TextListener(this.window, vertical, copie, n));
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
                    actual.addMouseListener(new TextListener(this.window, vertical, copie, n));
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
