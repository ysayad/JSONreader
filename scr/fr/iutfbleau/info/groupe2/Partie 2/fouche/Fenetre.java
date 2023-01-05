import java.awt.*;
import javax.swing.*;

public class Fenetre {
    public Fenetre(){
        JFrame fenetre=new JFrame();
        fenetre.setSize(1000,1000);
        fenetre.setLocation(0,0);

        GridBagLayout gestionnaire=new GridBagLayout();
        GridBagConstraints contrainte=new GridBagConstraints();
        fenetre.setLayout(gestionnaire);
        
        JButton rafraichir=new JButton("Rafraichir");
        this.layoutOptions(contrainte, 90, 1, 10, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0.0, 0.0, new Insets(0,0,0,0));
        fenetre.add(rafraichir,contrainte);

        JTextField url=new JTextField("Ecrivez une URL d'un fichier Json");
        this.layoutOptions(contrainte, 1, 1, 89, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0.0, 0.0, new Insets(0,0,0,0));
        fenetre.add(url,contrainte);
        for(int i=1;i<100;i++){
            this.creerVide(i, 0, fenetre, contrainte);
        }
        
        String chaine = new String("Corps de texte\n c'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est partic'est parti\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        int nbLignes= this.compte(chaine);

        JPanel numero =new JPanel();
        numero.setPreferredSize(new Dimension(30,920));
        numero.setLayout(new GridLayout(46,1));
        for(int i=0;i<nbLignes;i++){
            String nb=Integer.toString(i+1);
            JLabel texte =new JLabel(nb);
            texte.setFont(new Font(nb,Font.PLAIN,10));
            numero.add(texte);
        }
        this.layoutOptions(contrainte, 1, 2, 3, 46, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0.0, 0.0, new Insets(0,0,0,0));
        fenetre.add(numero,contrainte);
        
        JPanel corps =new JPanel();
        corps.setPreferredSize(new Dimension(950,920));
        corps.setLayout(new FlowLayout());
        for(int i=0;i<chaine.length();i++){
            char[] tab=new char[1];
            tab[0]=chaine.charAt(i);
            String ch =new String(tab);
            JLabel texte =new JLabel(ch);
            texte.setFont(new Font(ch,Font.PLAIN,10));
            texte.setForeground(Color.RED);
            texte.setPreferredSize(new Dimension(6,10));
            corps.add(texte);
        }
        this.layoutOptions(contrainte, 4, 2, 96, 46, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0.0, 0.0, new Insets(0,0,0,0));
        fenetre.add(corps,contrainte);

        fenetre.setVisible(true);
    }

    public void layoutOptions(GridBagConstraints gbc,int x, int y, int width, int height, int fill, int anchor, double weightx, double weighty,Insets insets) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = fill;
        gbc.anchor = anchor;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = insets;
    }

    public void creerVide(int x, int y,JFrame f,GridBagConstraints gbc) {
        JPanel espace = new JPanel();
        espace.setPreferredSize(new Dimension(10,10));
        espace.setBackground(Color.BLACK);
        this.layoutOptions(gbc,x, y, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER, 0.0, 0.0,new Insets(0, 0, 0, 0));
        f.add(espace, gbc);
    }

    public int compte(String chaine) {
        int nb=1;
        for(int i=0;i<chaine.length();i++){
            if(chaine.charAt(i)=='\n'){
                nb++;
            }
        }
        return nb;
    }
    public static void main(String[] args) {
        Fenetre f= new Fenetre();
    }
}
