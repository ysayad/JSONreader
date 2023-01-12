package src.fr.iutfbleau.projetJson.ainspecteur.Vue;

import src.fr.iutfbleau.projetJson.ainspecteur.Vue.display.Display;
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;





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
        Menu menu = new Menu(fenetre);
        JPanel zoz = new JPanel();
        zoz.add(menu.drawMenu());
        fenetre.add(zoz,BorderLayout.LINE_START);

  

        Display reader = new Display(fenetre, "file:res/ex1.json");
        JPanel display = reader.drawDisplay();
        //JPanel readerpanel = new JPanel();

        JPanel page = new JPanel(new BorderLayout());

        //readerpanel.add(reader.drawDisplay(), BorderLayout.WEST);
        //page.add(readerpanel, BorderLayout.WEST);

        JTextField chemin = new JTextField("Saisir l'emplacement du fichier");
        chemin.setForeground(Color.GRAY);
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
        ok.setHorizontalAlignment(SwingConstants.LEFT);
        ok.setContentAreaFilled(false);
        okpanel.setBackground(Color.BLACK);
        ok.setForeground(Color.WHITE);
        okpanel.add(ok);
        ok.addMouseListener(new OkListener(chemin,ok,okpanel,fenetre,page,display, zoz));



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
        parcourir.setHorizontalAlignment(SwingConstants.LEFT);
        parcourir.setContentAreaFilled(false);
        parcourirpanel.setBackground(Color.BLACK);
        parcourir.setForeground(Color.WHITE);
        parcourirpanel.add(parcourir);
        parcourir.addMouseListener(new ParcourirListener(chemin,parcourir,parcourirpanel,fenetre));





        JPanel test = new JPanel();
        test.setLayout(new GridLayout(1,3));
        test.add(chemin);
        test.add(okpanel);
        test.add(parcourirpanel);
        display.add(test,BorderLayout.PAGE_START);
        fenetre.add(display,BorderLayout.CENTER);




        fenetre.setVisible(true);
    }
}
