/**
 * MaillonParser
 */
public class MaillonParser {
    MaillonParser suivant;
    String valeur;
    String type;
    public MaillonParser(String s,String t){
        this.valeur=s;
        this.type=t;
        this.suivant=null;
    }
    public MaillonParser getSuivant(){
        return this.suivant;
    }
    public void setSuivant(MaillonParser m){
        this.suivant=m;
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
        this.valeur=t;
    }
}