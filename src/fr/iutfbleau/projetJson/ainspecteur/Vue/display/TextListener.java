package src.fr.iutfbleau.projetJson.ainspecteur.Vue.display;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class TextListener implements MouseListener {

    String test;

    public TextListener(String test) {
        this.test = test;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(test);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
