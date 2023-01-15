package src.fr.iutfbleau.projetJson.ainspecteur.Vue.display;
import src.fr.iutfbleau.projetJson.ainspecteur.Modele.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;



public class Display extends JFrame {
    JPanel menu;
    String content;
    JFrame window;
    public String path;

    Tree tree;

    public Display(JFrame window, String path){
        this.window = window;
        this.menu = new JPanel();
        //this.menu.setSize(500, 500);
        this.menu.setBackground(new Color(150, 150, 150));
        this.menu.setLayout(new BorderLayout());
        this.path = path;

        JsonString string = new JsonString(path);
        Parser parser = new JsonParser(string);
        Tree tree = new JsonTree(parser);

        this.tree = tree;
    }


    public JScrollPane drawDisplayPHP(){
        PHPFilter phpfiltrer = new PHPFilter(this.tree);
        JScrollPane container = new JScrollPane();

        String chaine = phpfiltrer.toString();
        String stock = "";


        for(int i = 0;chaine.length()>i; i+=1 ){
            if (chaine.charAt(i) == '\n'){
                System.out.println(stock);
                container.add(new JLabel(stock));
            }else{
                char[] c= {chaine.charAt(i)};
                chaine.charAt(i);
                stock+=new String(c);
            }
        }
    System.out.println("soteszihsepoifhsepuoibf");
    return container;




    }


    public JScrollPane drawDisplay(){
        JPanel compteur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 1));
        int cpt = 0;
        boolean isnoeud;
        int level = 0;

        
        JPanel vertical = new JPanel();

        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));

        JPanel lignes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));

        JTextPane blank = new JTextPane();
        blank.setBorder(BorderFactory.createEmptyBorder());
        blank.setEditable(false);

        

        compteur.add(new JLabel("test"));

        lignes.add(blank);
        vertical.add(lignes);

        //System.out.println(jsp.getAlignmentX() + " "+ jsp.getSize().getHeight());


        while (!tree.isEmpty()) {
            Maillon token = tree.remove();
            //System.out.println(token.getValeur() + " " + token.getType());

            JTextPane ligne = new JTextPane();
            ligne.setBorder(BorderFactory.createEmptyBorder());
            ligne.setEditable(false);
            switch (token.getType()) {
                case OPEN:
                    cpt++;
                    break;
                case KEY_NAME:
                    for (int i = 0; i < cpt; i++) {
                        addColoredText(ligne, "     ", Color.BLACK);
                    }
                    addColoredText(ligne, token.getValeur(), pickColor(token));
                    JTextPane pts = new JTextPane();
                    pts.setBorder(BorderFactory.createEmptyBorder());
                    pts.setEditable(false);
                    addColoredText(pts, " : ", Color.BLACK);
                    lignes.add(ligne);
                    lignes.add(pts);
                    break;
                case CLOSE:
                    cpt--;
                    ligne.addMouseListener(new TextListener(token.getValeur()));; //pour mettre le listener du dépli syntaxique
                    break;
                case START_ARRAY:
                case START_OBJECT:
                    if (lignes.getComponentCount() == 0) {
                        cpt++;
                    }
                    if (lignes.getComponentCount() == 0) {
                        for (int i = 0; i < cpt; i++) {
                            addColoredText(ligne, "     ", Color.BLACK);
                        }
                    }
                    ligne.addMouseListener(new TextListener(token.getValeur()));; //pour mettre le listener du dépli syntaxique
                    addColoredText(ligne, token.getValeur(), pickColor(token));
                    lignes.add(ligne);
                    lignes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    vertical.add(lignes);
                    break;
                case END_ARRAY:
                case END_OBJECT:
                    cpt--;
                    for (int i = 0; i < cpt; i++) {
                        addColoredText(ligne, "     ", Color.BLACK);
                    }
                    addColoredText(ligne, token.getValeur(), pickColor(token));
                    lignes.add(ligne);
                    lignes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    vertical.add(lignes);
                    break;
                default:
                    if (lignes.getComponentCount() == 0) {
                        for (int i = 0; i < cpt; i++) {
                            addColoredText(ligne, "     ", Color.BLACK);
                        }
                    }
                    addColoredText(ligne, token.getValeur(), pickColor(token));
                    lignes.add(ligne);
                    lignes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
                    vertical.add(lignes);
                    break;
            }


            // JTextArea numero = new JTextArea(Integer.toString(cpt));
            // numero.setEditable(false);
            // numero.setBorder(null);
            // compteur.add(numero);
        }

        compteur.setPreferredSize(new Dimension(50, lignes.getHeight()));
        lignes.setPreferredSize(new Dimension(this.window.getWidth() - 450, 300));

        this.menu.add(compteur, BorderLayout.WEST);
        this.menu.add(vertical, BorderLayout.CENTER);
        JScrollPane jsp = new JScrollPane(menu);
        //jsp.setSize(10000,10000);
        jsp.setHorizontalScrollBarPolicy(jsp.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.getVerticalScrollBar().setUnitIncrement(16);
        jsp.setBorder(BorderFactory.createEmptyBorder());

        return jsp;
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

    private Color pickColor(Maillon maillon){
        switch (maillon.getType()) {
            case START_OBJECT:
            case END_OBJECT:
            case START_ARRAY:
            case END_ARRAY:
                return Color.BLACK;
            case KEY_NAME:
                return Color.RED;
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
