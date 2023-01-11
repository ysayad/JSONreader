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
public class RafraichirListener implements MouseListener{
    JButton button;
    JPanel buttonpannel;
    JFrame window;
    JTextField searchbar;

    public RafraichirListener(JTextField searchbar,JButton button, JPanel buttonpannel, JFrame window) {
        this.button = button;
        this.buttonpannel = buttonpannel;
        this.window = window;
        this.searchbar = searchbar;
    }





    public void mouseClicked(MouseEvent e) {
        System.out.println(searchbar.getText());
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

}