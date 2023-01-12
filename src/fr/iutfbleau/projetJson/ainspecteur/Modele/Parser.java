package src.fr.iutfbleau.projetJson.ainspecteur.Modele;
/**
 * Parser
 */
public interface Parser {
    /** méthode
     *
     * @param MaillonParser un maillon qui sera modifier,
     * @return MaillonParser le premier maillon de la chaine,
     * construis et affiche la fenetre
     */
    public MaillonParser transformer(MaillonParser maillon);
    /** méthode
     *
     * @param void,
     * @return String le type de l'élément,
     * renvoie le type de l'élément de la chaine
     */
    public JsonType typer();
    /** méthode
     *
     * @param String la chaine de charactere,
     * @return String l'élément,
     * renvoie un élément de la chaine de String
     */
    public String retirer (String texte);
    /** méthode
     *
     * @param String la chaine de charactere,
     * @return String l'élément,
     * renvoie l'élement de type String
     */
    public String caractere(String texte);
    /** méthode
     *
     * @param String la chaine de charactere,
     * @return String l'élément,
     * renvoie l'élement de type entier
     */
    public String entier(String texte);
    /** méthode
     *
     * @param void,
     * @return String contenu,
     * renvoie le contenu du JSonParser
     */
    public String toString();
    /** méthode
     *
     * @param MaillonParser le maillon dans lequel retirer l'élément,
     * @return String l'élément,
     * renvoie un élément du JSonParser
     */
    public String afficher(MaillonParser maillon);
    /** méthode
     *
     * @param void,
     * @return Maillon un maillon,
     * retire un maillons du JSonParser dans l'ordre
     */
    public Maillon next();
    /** méthode
     *
     * @param void,
     * @return boolean true ou false,
     * vérifie si le JSonParser est vide
     */
    public boolean hasNext();
}