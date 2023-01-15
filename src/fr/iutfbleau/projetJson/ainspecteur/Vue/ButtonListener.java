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
public class ButtonListener implements MouseListener{
    JButton button;
    JPanel buttonpannel;
    JFrame window;
    Menu menu;
    JPanel page;
    JScrollPane display;
    JTextField searchbar;

    public ButtonListener(JButton button, JPanel buttonpannel, JFrame window, JPanel page, JScrollPane display, JTextField searchbar) {
        this.button = button;
        this.buttonpannel = buttonpannel;
        this.window = window;
        this.menu = menu;
        this.display = display;
        this.searchbar=searchbar;
    }


    public void refresh(){
        this.window.remove(this.button);
        String test = this.button.getName();
        this.button = new JButton("test");
        this.button.setName(test);
        this.window.add(this.button);
    }




    public void affichePHP(){






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


        if (this.button.getName().equals("Afficher la version PHP")){
            System.out.println("Affichage php");
            affichePHP();
            this.window.invalidate();
            this.window.validate();
            this.window.revalidate();
        }


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
            //rafraichir.addMouseListener(new RafraichirListener(chemin,rafraichir,rafraichirpanel,window));



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