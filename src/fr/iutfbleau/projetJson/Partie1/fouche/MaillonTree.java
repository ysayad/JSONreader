
import java.util.*;
/**
 * MaillonTree
 */
public class MaillonTree implements Maillon{
    private Deque<MaillonTree> file;
    private String valeur;
    private JsonType type;
    /** constructeur
     *
     * @param s la chaine, t le type,
     * @return void,
     * créer un maillon
     */
    public MaillonTree(String s,JsonType t){
        this.valeur=s;
        this.type=t;
        this.file=null;
    }
    /** constructeur
     *
     * @param void,
     * @return void,
     * créer un maillon
     */
    public MaillonTree(){
        this.valeur=null;
        this.type=null;
        this.file=new ArrayDeque<MaillonTree>();
    }
    /** méthode
     *
     * @param void,
     * @return String le mailllon gauche,
     * retourne le mailllon gauche du maillon
     */
    public MaillonTree remove(){
        return this.file.remove();
    }
    /** méthode
     *
     * @param void,
     * @return String le mailllon gauche,
     * retourne le mailllon gauche du maillon
     */
    public boolean isEmpty(){
        return this.file.isEmpty();
    }
     /** méthode
     *
     * @param void,
     * @return String le mailllon gauche,
     * retourne le mailllon gauche du maillon
     */
    public boolean isNoeud(){
        return this.file!=null;
    }
    /** méthode
     *
     * @param String le mailllon gauche,,
     * @return void,
     * modifie le mailllon gauche, du maillon
     */
    public void add(MaillonTree m){
        this.file.add(m);
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
     * @return JsonType le type,
     * retourne le type du maillon
     */
    public JsonType getType(){
        return this.type;
    }
    /** méthode
     *
     * @param JsonType le type,
     * @return void,
     * modifie le type du maillon
     */
    public void setType(JsonType t){
        this.type=t;
    }
}