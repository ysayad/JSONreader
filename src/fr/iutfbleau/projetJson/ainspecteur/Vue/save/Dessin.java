package src.fr.iutfbleau.projetJson.ainspecteur.Vue.save;

/**
 * Dessin
 */
import java.awt.*;
import javax.swing.*;
/**
 * Dessin
 */
public class Dessin extends JComponent{
    private String chaine;
    private int x;
    private int y;

    /** constructeur
     *
     * @param s le charactere ou la chaine de charactere, i la position en x, j la position en y
     * @return void,
     * remplis les variables
     */
    public Dessin(String s,int i,int j){
        this.chaine=s;
        this.x=i;
        this.y=j;
    }

    /** méthode
     *
     * @param pinceau le pinceau graphics,
     * @return void,
     * affiche chaque caractère
     * 
     */
    @Override
    protected void paintComponent(Graphics pinceau) {
        Graphics secondPinceau = pinceau.create();
        secondPinceau.drawString(this.chaine,x,y);
    }
}