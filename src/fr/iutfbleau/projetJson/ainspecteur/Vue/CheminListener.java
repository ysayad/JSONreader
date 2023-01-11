package src.fr.iutfbleau.projetJson.ainspecteur.Vue;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class CheminListener implements MouseListener {
    JTextField searchbar;
    int val;

    public CheminListener(JTextField searchbar) {
        this.searchbar = searchbar;
        this.val = 0;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        this.searchbar.setFocusable(true);
    }

    public void mouseExited(MouseEvent e) {
        if (this.searchbar.getText().equals("") && this.val == 1) {
            this.searchbar.setForeground(Color.GRAY);
            this.searchbar.setText("Saisir l'emplacement du fichier");
            this.searchbar.setFocusable(false);
            this.val = 0;
        } else if (this.searchbar.getText().equals("Saisir l'emplacement du fichier")) {
            this.searchbar.setFocusable(false);
            this.searchbar.setForeground(Color.GRAY);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (this.val == 0) {
            this.searchbar.setFocusable(true);
            this.searchbar.setText("");
            this.searchbar.setForeground(Color.BLACK);
            this.val = 1;
        }
    }

    public void mouseReleased(MouseEvent e) {}
}