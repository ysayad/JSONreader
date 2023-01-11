package src.fr.iutfbleau.projetJson.ainspecteur.Vue;

import java.awt.event.*;
import javax.swing.*;
import java.awt.Cursor;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
public class ButtonListener implements MouseListener{
    JButton button;
    JPanel buttonpannel;
    JFrame window;

    public ButtonListener(JButton button, JPanel buttonpannel, JFrame window) {
        this.button = button;
        this.buttonpannel = buttonpannel;
        this.window = window;
    }

    public void refresh(){
        this.window.remove(this.button);
        String test = this.button.getName();
        this.button = new JButton("test");
        this.button.setName(test);
        this.window.add(this.button);
    }



    public void mouseClicked(MouseEvent e) {
        System.out.println(this.button.getName());
        if (this.button.getName().equals("Quitter")){
            System.exit(1);
        }

        if (this.button.getName().equals("Ouvrir un fichier")){



            JDialog dialog = new JDialog(this.window, "Ouvrir un fichier");
            JLabel desc = new JLabel("Saisir un url ");
            JTextField chemin = new JTextField("Saisir l'emplacement du fichier");


            JPanel rafraichirpanel = new JPanel();
            GridLayout gridLayout = new GridLayout(1,1);
            gridLayout.setHgap(50);
            gridLayout.setVgap(0);
            rafraichirpanel.setLayout(gridLayout);
            JButton rafraichir = new JButton("Rafraichir");
            rafraichir.setFont(new Font("Verdana", Font.PLAIN, 15));
            rafraichir.setName("Parcourir");
            rafraichir.setBorderPainted(true);
            rafraichir.setFocusPainted(false);
            rafraichir.setHorizontalAlignment(SwingConstants.LEFT);
            rafraichir.setContentAreaFilled(false);
            rafraichirpanel.setBackground(Color.BLACK);
            rafraichir.setForeground(Color.WHITE);
            rafraichirpanel.add(rafraichir);
            rafraichir.addMouseListener(new RafraichirListener(chemin,rafraichir,rafraichirpanel,window));



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
            parcourir.addMouseListener(new ParcourirListener(chemin,parcourir,parcourirpanel,window));





            JPanel test = new JPanel();
            test.setLayout(new GridLayout(1,3));
            test.add(chemin);
            test.add(parcourirpanel);
            test.add(rafraichirpanel);
            dialog.add(test);
            //chemin.addMouseListener(new ParcourirListener(chemin,window));
            dialog.setSize(500, 600);

            dialog.setModal(true);

            dialog.setLocationRelativeTo(null);
            dialog.setResizable(false);
            dialog.setVisible(true);


        }

        if (this.button.getName().equals("Tout deplier")){
            //le code pour tout deplier
        }
        if (this.button.getName().equals("Tout plier")){
            //le code pour tout plier
        }
        if (this.button.getName().equals("Rafraichir")){
            //le code pour rafraichir
        }
        //refresh();
    }

    public void mouseEntered(MouseEvent e) {
        this.button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.button.setForeground(Color.BLACK);
        this.buttonpannel.setBackground(Color.WHITE);

    }

    public void mouseExited(MouseEvent e) {
        this.button.setForeground(Color.WHITE);
        this.buttonpannel.setBackground(Color.BLACK);
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

}