import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.Border;
import java.io.*;
import java.lang.Thread;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Menu extends JFrame {
    JPanel menu;
    String content;
    JFrame window;

    public Menu(JFrame window){
        this.window = window;
        this.menu = new JPanel();
        this.menu.setBackground(new Color(43, 43, 43));
        this.menu.setLayout(new GridLayout(12,1));
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
        if (name == "Changements" || name == "Deconnexion") {
            button.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(50,50,50)));
        }else{
            button.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, new Color(50,50,50)));
        }

        button.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        if (name == "Deconnexion") { 
            button.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        }
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setContentAreaFilled(false);
        button.setBackground(new Color(64,0,128));
        button.setForeground(Color.GRAY);
        //JLabel test = new JLabel(icfaon);
        //test.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

        //button.add(test);
        //button.setHorizontalAlignment(SwingConstants.CENTER);
        //button.setHorizontalTextPosition(SwingConstants.LEFT); 
        buttonpanel.add(button);
        button.addMouseListener(new ButtonListener(button));
        return buttonpanel;
    }

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
        this.menu.add(this.vide());
        this.menu.add(this.drawButton("Ouvrir un fichier"));
        this.menu.add(this.vide());
        this.menu.add(this.drawButton("Sauvegarder & quitter"));



        
        this.menu.add(this.vide());
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
