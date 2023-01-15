package src.fr.iutfbleau.projetJson.ainspecteur.Vue;

import src.fr.iutfbleau.projetJson.ainspecteur.Vue.display.Display;
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;


public class Fenetre {
    public static void draw() {
        JFrame fenetre = new JFrame("JReader");
        fenetre.setSize(1280,720);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }


  

        Display reader = new Display(fenetre, "file:res/ex2.json");
        JScrollPane display = reader.drawDisplay();
        //JPanel readerpanel = new JPanel();

        JPanel page = new JPanel(new BorderLayout());

        //readerpanel.add(reader.drawDisplay(), BorderLayout.WEST);
        //page.add(readerpanel, BorderLayout.WEST);

        JTextField chemin = new JTextField("Saisir l'emplacement du fichier");

        chemin.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(43, 43, 43)),
        BorderFactory.createEmptyBorder(0, 10, 0, 0)));        
        chemin.setForeground(Color.GRAY);
        chemin.setBackground(new Color(250, 250, 255));
        chemin.addMouseListener(new CheminListener(chemin));

        JPanel okpanel = new JPanel();
        GridLayout gridLayout = new GridLayout(1,1);
        gridLayout.setHgap(50);
        gridLayout.setVgap(0);
        okpanel.setLayout(gridLayout);
        JButton ok = new JButton("ok");
        ok.setFont(new Font("Verdana", Font.PLAIN, 15));
        ok.setName("ok");
        ok.setBorderPainted(true);
        ok.setFocusPainted(false);
        ok.setContentAreaFilled(false);
        okpanel.setBackground(Color.BLACK);
        ok.setForeground(Color.WHITE);
        okpanel.add(ok);




        JPanel parcourirpanel = new JPanel();
        GridLayout gridLayout2 = new GridLayout(1,1);
        gridLayout2.setHgap(50);
        gridLayout2.setVgap(0);
        parcourirpanel.setLayout(gridLayout2);


        JButton parcourir = new JButton("Parcourir");
        parcourir.setFont(new Font("Verdana", Font.PLAIN, 15));
        parcourir.setName("Parcourir");
        parcourir.setBorderPainted(true);
        parcourir.setFocusPainted(false);
        parcourir.setContentAreaFilled(false);
        parcourirpanel.setBackground(Color.BLACK);
        parcourir.setForeground(Color.WHITE);
        parcourirpanel.add(parcourir);
        parcourir.addMouseListener(new ParcourirListener(chemin,parcourir,parcourirpanel,fenetre));





        JPanel test = new JPanel();
        test.setLayout(new BorderLayout());
        test.add(chemin);
        JPanel test2 = new JPanel(new GridLayout(1,2));
        test2.add(okpanel);
        test2.add(parcourirpanel);
        test.setBackground(new Color(43, 43, 43));
        test.add(chemin);
        test.add(test2, BorderLayout.EAST);

        Menu menu = new Menu(fenetre, page, display, chemin);
        ok.addMouseListener(new OkListener(chemin,ok,okpanel,fenetre,page,display, menu));
        fenetre.add(menu.drawMenu(),BorderLayout.LINE_START);
        fenetre.add(test, BorderLayout.NORTH);
        fenetre.add(display,BorderLayout.CENTER);





        fenetre.setVisible(true);
    }
}
