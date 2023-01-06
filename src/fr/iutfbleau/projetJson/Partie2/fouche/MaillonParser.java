/**
 * MaillonParser
 */
public class MaillonParser implements Maillon{
    private MaillonParser suivant;
    private String valeur;
    private String type;
    /** constructeur
     *
     * @param s la chaine, t le type,
     * @return void,
     * créer un maillon
     */
    public MaillonParser(String s,String t){
        this.valeur=s;
        this.type=t;
        this.suivant=null;
    }
    /** méthode
     *
     * @param void,
     * @return MaillonParser le maillon suivant,
     * retourne le maillon suivant du maillon
     */
    public MaillonParser getSuivant(){
        return this.suivant;
    }
    /** méthode
     *
     * @param MaillonParser le maillon suivant,
     * @return void,
     * modifie le maillon suivant du maillon
     */
    public void setSuivant(MaillonParser m){
        this.suivant=m;
    }
    /** méthode
     *
     * @param void,
     * @return String la valeur ,
     * retourne la valeur du maillon
     */
    public String getValeur(){
        return this.valeur;
    }
    /** méthode
     *
     * @param String la valeur,
     * @return void,
     * modifie la valeur du maillon
     */
    public void setValeur(String s){
        this.valeur=s;
    }
    /** méthode
     *
     * @param void,
     * @return String le type,
     * retourne le type du maillon
     */
    public String getType(){
        return this.type;
    }
    /** méthode
     *
     * @param String le type,
     * @return void,
     * modifie le type du maillon
     */
    public void setType(String t){
        this.valeur=t;
    }
}