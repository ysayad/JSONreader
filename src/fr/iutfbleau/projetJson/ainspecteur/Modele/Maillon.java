package src.fr.iutfbleau.projetJson.ainspecteur.Modele;


/**
 * Maillon
 */
public interface Maillon {
    /** méthode
     *
     * @param void,
     * @return String la valeur ,
     * retourne la valeur du maillon
     */
    public String getValeur();
    /** méthode
     *
     * @param String la valeur,
     * @return void,
     * modifie la valeur du maillon
     */
    public void setValeur(String s);
    /** méthode
     *
     * @param void,
     * @return String le type,
     * retourne le type du maillon
     */
    public JsonType getType();
    /** méthode
     *
     * @param String le type,
     * @return void,
     * modifie le type du maillon
     */
    public void setType(JsonType t);
}