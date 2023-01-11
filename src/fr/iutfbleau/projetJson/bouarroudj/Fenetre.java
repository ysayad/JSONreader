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


        JPanel reader = new JPanel();
        reader.setBackground(Color.GRAY);
        fenetre.add(reader);




        fenetre.setVisible(true);
    }
}
