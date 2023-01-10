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

        JFileChooser choix_fichier = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        choix_fichier.setDialogTitle("Choisissez un fichier : ");
        choix_fichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
        choix_fichier.addChoosableFileFilter(new FileNameExtensionFilter("json", "json"));


        int res = choix_fichier.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File file = choix_fichier.getSelectedFile();
            System.out.println(file.getAbsolutePath());
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