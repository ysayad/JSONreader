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

    JPanel page;
    String path;
    public OkListener(JTextField searchbar,JButton button, JPanel buttonpannel, JFrame window,JPanel page) {
        this.window = window;
        this.searchbar = searchbar;
        this.page = page;
        this.button = button;
        this.buttonpannel = buttonpannel;
    }


    public void refresh(){
        Menu menu = new Menu(this.window);
        this.window.add(menu.drawMenu(),BorderLayout.LINE_START);

        JPanel header = new JPanel();
        header.setBackground(new Color(23, 23, 23));
        // JLabel titre = new JLabel("JReader");
        // titre.setForeground(new Color(200,200,200));
        // header.add(titre, BorderLayout.WEST);
        // this.window.add(header, BorderLayout.PAGE_START);


        Display reader = new Display(this.window, this.searchbar.getText());
        //JPanel readerpanel = new JPanel();

        JPanel page = new JPanel(new BorderLayout());

        // readerpanel.add(reader.drawDisplay());
        // page.add(readerpanel);

        window.add(reader.drawDisplay(),BorderLayout.CENTER);

        JTextField chemin = new JTextField(this.searchbar.getText());
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
        ok.setHorizontalAlignment(SwingConstants.LEFT);
        ok.setContentAreaFilled(false);
        okpanel.setBackground(Color.BLACK);
        ok.setForeground(Color.WHITE);
        okpanel.add(ok);
        ok.addMouseListener(new OkListener(chemin,ok,okpanel,this.window,page));



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
        parcourir.addMouseListener(new ParcourirListener(chemin,parcourir,parcourirpanel,this.window));





        JPanel test = new JPanel();
        test.setLayout(new GridLayout(1,3));
        test.add(chemin);
        test.add(okpanel);
        test.add(parcourirpanel);

        page.add(test,BorderLayout.PAGE_START);
        this.window.add(page,BorderLayout.PAGE_START);
        // page.add(test,BorderLayout.PAGE_START);
        // this.window.add(page,BorderLayout.CENTER);
    }



    public void mouseClicked(MouseEvent e) {
        this.window.remove(page);
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