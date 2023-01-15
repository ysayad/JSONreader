package src.fr.iutfbleau.projetJson.ainspecteur.Vue;
import java.awt.*;
import javax.swing.*;


public class Menu extends JFrame {
    JPanel menu;
    String content;
    JFrame window;

    JPanel page;
    JScrollPane display;
    JTextField searchbar;
    public Menu(JFrame window,JPanel page, JScrollPane display,JTextField searchbar){
        this.window = window;
        this.menu = new JPanel();
        this.menu.setBackground(new Color(43, 43, 43));
        GridLayout t = new GridLayout(12,1);
        this.searchbar =searchbar;
        t.setHgap(0);
        t.setVgap(0);
        this.menu.setLayout(t);
        this.display = display;
        this.page = page;

    }




    public JPanel drawButton(String name){
        JPanel buttonpanel = new JPanel();
        GridLayout gridLayout = new GridLayout(1,1);
        gridLayout.setHgap(50);
        gridLayout.setVgap(0);
        buttonpanel.setLayout(gridLayout);
        JButton button = new JButton(name);
        button.setFont(new Font("Verdana", Font.PLAIN, 15));
        button.setName(name);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setContentAreaFilled(false);
        buttonpanel.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        buttonpanel.add(button);
        button.addMouseListener(new ButtonListener(button,buttonpanel,window,this.page,this.display,this.searchbar,this));
        return buttonpanel;
    }


/*
Cette méthode permet de rajouter un panneau vide utile pour espacer les boutons du menu
*/
    public JPanel vide(){
        JPanel vide = new JPanel();
        GridLayout gridLayout = new GridLayout(1,1);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        vide.setLayout(gridLayout);
        vide.setBackground(new Color(43, 43, 43));
        return vide;
    }


    public JPanel drawMenu(){
        //this.menu.setResizable(false);







        this.menu.add(this.vide());
        this.menu.add(this.vide());
        this.menu.add(this.drawButton("Afficher la version PHP"));
        this.menu.add(this.vide());
        this.menu.add(this.drawButton("Tout deplier"));

        this.menu.add(this.vide());
        this.menu.add(this.drawButton("Tout plier"));

        
        this.menu.add(this.vide());
        this.menu.add(this.vide());
        this.menu.add(this.vide());

        this.menu.add(this.vide());

        this.menu.add(this.drawButton("Quitter"));
        //this.menu.setResizeHorizontal(false);
        //this.menu.setResizeVertical(true);
        this.menu.setPreferredSize(new Dimension(300, this.window.getHeight()));
        this.menu.setSize(new Dimension(400, this.window.getHeight()));




        return this.menu;
    }
}
