/**
 * Dessin
 */
import java.awt.*;
import javax.swing.*;

public class Dessin extends JComponent{
    private String chaine;
    private int x;
    private int y;
    public Dessin(String s,int i,int j){
        this.chaine=s;
        this.x=i;
        this.y=j;
    }
    @Override
    protected void paintComponent(Graphics pinceau) {
        Graphics secondPinceau = pinceau.create();
        secondPinceau.drawString(this.chaine,x,y);
    }
}