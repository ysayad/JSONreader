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
        fenetre.add(menu.drawMenu(),BorderLayout.LINE_START);

        JPanel header = new JPanel();
        header.setBackground(new Color(23, 23, 23));
        JLabel titre = new JLabel("JReader");
        titre.setForeground(new Color(200,200,200));
        header.add(titre, BorderLayout.WEST);
        fenetre.add(header, BorderLayout.PAGE_START);


        Display reader = new Display(fenetre, "http://ergast.com/api/f1/2004/1/results.json");
        JPanel readerpanel = new JPanel();

        JPanel page = new JPanel(new BorderLayout());

        readerpanel.add(reader.drawDisplay());
        page.add(readerpanel);

        JTextField chemin = new JTextField("Saisir l'emplacement du fichier");
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
        ok.addMouseListener(new OkListener(chemin,ok,okpanel,fenetre,page));



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

        page.add(test,BorderLayout.PAGE_START);
        fenetre.add(page,BorderLayout.CENTER);




        fenetre.setVisible(true);
    }
}
