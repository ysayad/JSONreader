package src.fr.iutfbleau.projetJson.ainspecteur.Modele;

public interface Tree {
    /** méthode
     *
     * @param liste  l'arbre à faire,
     * @return void,
     * initialisateur de l'arbre
     */
    public void initialiser(Parser liste);
    /** méthode
     *
     * @param void,
     * @return void,
     * modifie l'arbre en retirant tous les éléments et en le reconstruisant 
     */
    public void modifier();
    /** méthode
     *
     * @param void,
     * @return MaillonTree l'élément enlevé,
     * retire un élément de l'arbre
     */
    public MaillonTree remove();
    /** méthode
     *
     * @param void,
     * @return boolean arbre vide,
     * vérifie si l'arbre est vide
     */
    public boolean isEmpty();
    /** méthode
     *
     * @param void,
     * @return void,
     * reconstruit l'arbre
     */
    public void reconstruire();
    /** méthode
     *
     * @param MaillonTree m le maillon à ajouter,
     * @return void,
     * ajoute un élément à l'arbre
     */
    public void ajouter(MaillonTree m);
    /** méthode
     *
     * @param void,
     * @return String chaine,
     * renvoie le String qui représente l'arbre
     */
    public String toString();
    /** méthode
     *
     * @param maillon le premier maillon de l'arbre, i entier qui indique la profondeur de la branche
     * @return String chaine,
     * renvoie le String qui représente l'arbre
     */
    public String afficher(MaillonTree maillon,int i);
    /** méthode
     *
     * @param void,
     * @return MaillonTree noeud de l'arbre en cours de maniement,
     * renvoie le noeud de l'arbre
     */
    public MaillonTree getNoeud();
}
