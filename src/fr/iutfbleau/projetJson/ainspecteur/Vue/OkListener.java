package src.fr.iutfbleau.projetJson.ainspecteur.Vue;

import src.fr.iutfbleau.projetJson.ainspecteur.Vue.display.Display;

import java.awt.event.*;
import javax.swing.*;
import java.awt.Cursor;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
public class OkListener implements MouseListener{
    JButton button;
    JPanel buttonpannel;
    JFrame window;
    JTextField searchbar;
    Menu menu;
    JPanel page;
    JScrollPane display;
    String path;
    public OkListener(JTextField searchbar,JButton button, JPanel buttonpannel, JFrame window,JPanel page, JScrollPane display, Menu menu) {
        this.window = window;
        this.searchbar = searchbar;
        this.page = page;
        this.button = button;
        this.buttonpannel = buttonpannel;
        this.menu = menu;
        this.display = display;
        this.page = page;
    }


    public void refresh(){






        Display reader = new Display(this.window, this.searchbar.getText());
        JScrollPane display2 = reader.drawDisplay();
        //JPanel display2 = reader.drawDisplay();
        //JPanel readerpanel = new JPanel();

        JPanel page2 = new JPanel(new BorderLayout());

        //readerpanel.add(reader.drawDisplay(), BorderLayout.WEST);
        //page.add(readerpanel, BorderLayout.WEST);

                JTextField chemin = new JTextField("Saisir l'emplacement du fichier");

        chemin.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(43, 43, 43)),
        BorderFactory.createEmptyBorder(0, 10, 0, 0)));        
        chemin.setForeground(Color.GRAY);
        chemin.setBackground(new Color(250, 250, 255));
        chemin.addMouseListener(new CheminListener(chemin,1));

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
        //ok.addMouseListener(new OkListener(chemin,ok,okpanel,this.window,page2,display2,menu2));



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
        parcourir.addMouseListener(new ParcourirListener(chemin,parcourir,parcourirpanel,this.window));





        JPanel test = new JPanel();
        test.setLayout(new BorderLayout());
        test.add(chemin);
        JPanel test2 = new JPanel(new GridLayout(1,2));
        test2.add(okpanel);
        test2.add(parcourirpanel);
        test.setBackground(new Color(43, 43, 43));
        test.add(chemin);
        test.add(test2, BorderLayout.EAST);

        Menu menu2 = new Menu(this.window, page2,display2,chemin);
        this.window.add(menu2.drawMenu(),BorderLayout.LINE_START);
        this.window.add(test, BorderLayout.NORTH);
        //this.window.add(display2,BorderLayout.CENTER);



    }



    public void mouseClicked(MouseEvent e) {
        this.window.remove(this.menu.drawMenu());
        this.window.remove(this.page);
        this.window.remove(this.display);
        this.refresh();
        this.window.invalidate();
        this.window.validate();
        this.window.revalidate();

        System.out.println(searchbar.getText());
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