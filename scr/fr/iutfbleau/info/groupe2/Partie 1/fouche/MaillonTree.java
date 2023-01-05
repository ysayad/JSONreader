/**
 * MaillonTree
 */
public class MaillonTree implements Maillon{
    private MaillonTree pere;
    private MaillonTree gauche;
    private MaillonTree droite;
    private String valeur;
    private String type;
    public MaillonTree(String s,String t){
        this.valeur=s;
        this.type=t;
        this.pere=null;
        this.gauche=null;
        this.droite=null;
    }
    public MaillonTree getGauche(){
        return this.gauche;
    }
    public void setGauche(MaillonTree m){
        this.gauche=m;
    }
    public MaillonTree getPere(){
        return this.pere;
    }
    public void setPere(MaillonTree m){
        this.pere=m;
    }
    public MaillonTree getDroite(){
        return this.droite;
    }
    public void setDroite(MaillonTree m){
        this.droite=m;
    }
    public String getValeur(){
        return this.valeur;
    }
    public void setValeur(String s){
        this.valeur=s;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String t){
        this.type=t;
    }
}