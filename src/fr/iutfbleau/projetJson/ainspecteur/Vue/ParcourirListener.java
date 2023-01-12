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
public class ParcourirListener implements MouseListener{
    JButton button;
    JPanel buttonpannel;
    JFrame window;
    JTextField searchbar;

    public ParcourirListener(JTextField searchbar,JButton button, JPanel buttonpannel, JFrame window) {
        this.button = button;
        this.buttonpannel = buttonpannel;
        this.window = window;
        this.searchbar = searchbar;
    }





    public void mouseClicked(MouseEvent e) {
        JFileChooser choix_fichier = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        choix_fichier.setDialogTitle("Choisissez un fichier : ");
        choix_fichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
        choix_fichier.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));


        int res = choix_fichier.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File file = choix_fichier.getSelectedFile();
            System.out.println(file.getAbsolutePath());
            this.searchbar.setText("file:///" +     file.getAbsolutePath());
            this.searchbar.setForeground(Color.BLACK);
        }


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