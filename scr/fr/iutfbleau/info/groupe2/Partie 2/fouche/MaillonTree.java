/**
 * MaillonTree
 */
public class MaillonTree implements Maillon{
    private MaillonTree pere;
    private MaillonTree gauche;
    private MaillonTree droite;
    private String valeur;
    private String type;
    /** constructeur
     *
     * @param s la chaine, t le type,
     * @return void,
     * créer un maillon
     */
    public MaillonTree(String s,String t){
        this.valeur=s;
        this.type=t;
        this.pere=null;
        this.gauche=null;
        this.droite=null;
    }
    /** méthode
     *
     * @param void,
     * @return String le mailllon gauche,
     * retourne le mailllon gauche du maillon
     */
    public MaillonTree getGauche(){
        return this.gauche;
    }
    /** méthode
     *
     * @param String le mailllon gauche,,
     * @return void,
     * modifie le mailllon gauche, du maillon
     */
    public void setGauche(MaillonTree m){
        this.gauche=m;
    }
    /** méthode
     *
     * @param void,
     * @return String le mailllon pere,
     * retourne le mailllon pere du maillon
     */
    public MaillonTree getPere(){
        return this.pere;
    }
    /** méthode
     *
     * @param void,
     * @return String le mailllon pere,
     * retourne le mailllon pere du maillon
     */
    public void setPere(MaillonTree m){
        this.pere=m;
    }
    /** méthode
     *
     * @param void,
     * @return String le mailllon droite,
     * retourne le mailllon droite du maillon
     */
    public MaillonTree getDroite(){
        return this.droite;
    }
    /** méthode
     *
     * @param void,
     * @return String le mailllon droite,
     * retourne le mailllon droite du maillon
     */
    public void setDroite(MaillonTree m){
        this.droite=m;
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
        this.type=t;
    }
}