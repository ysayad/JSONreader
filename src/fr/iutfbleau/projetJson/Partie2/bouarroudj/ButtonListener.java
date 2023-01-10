import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Cursor;
import java.awt.*;


public class ButtonListener implements MouseListener{
    JButton button;
    JPanel buttonpannel;
    JFrame window;

    public ButtonListener(JButton button, JPanel buttonpannel, JFrame window) {
        this.button = button;
        this.buttonpannel = buttonpannel;
        this.window = window;
    }



    public void mouseClicked(MouseEvent e) {
        System.out.println(this.button.getName());
        if (this.button.getName().equals("Quitter")){
            System.exit(1);
        }

        if (this.button.getName().equals("Ouvrir un fichier")){
            JDialog dialog = new JDialog(this.window, "Ouvrir un fichier");
            JLabel desc = new JLabel("Saisir un url ");

            dialog.setSize(500, 600);
            dialog.setModal(true);


            dialog.setLocationRelativeTo(null);
            dialog.setResizable(false);
            dialog.setVisible(true);
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