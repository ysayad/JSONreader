import java.util.*;

/**
 * JsonTree
 */
public class JsonTree {
    private MaillonTree noeud;
    private MaillonTree substitution;
    private Deque<MaillonTree> pile;
    private Deque<MaillonTree> stock;
    private int taille;
    /** constructeur
     *
     * @param liste  l'arbre à faire,
     * @return void,
     * constructeur de l'arbre
     */
    public JsonTree(JsonParser liste){
        this.pile=new ArrayDeque<MaillonTree>();
        this.stock=new ArrayDeque<MaillonTree>();
        this.noeud=new MaillonTree();
        this.substitution=new MaillonTree();
        this.taille=0;
        initialiser(liste);
    }
    /** méthode
     *
     * @param liste  l'arbre à faire,
     * @return void,
     * initialisateur de l'arbre
     */
    public void initialiser(JsonParser liste){
        MaillonTree m=new MaillonTree();
        MaillonTree n=new MaillonTree();
        this.noeud=n;
        for(;!liste.hasNext();){
            Maillon o=liste.next();
            m=new MaillonTree(o.getValeur(),o.getType());
            this.ajouter(m);
        }
    }
    /** méthode
     *
     * @param taille  taille de l'arbre,
     * @return void,
     * modificateur de l'arbre
     */
    public MaillonTree remove(){
        MaillonTree m = this.noeud.remove();
        this.substitution.add(m);
        if(m.isNoeud()){
            this.pile.addFirst(this.noeud);
            this.stock.addFirst(this.substitution);
            this.noeud=m;
            this.substitution=m;
        }
        if(m.getType()==JsonType.END_OBJECT || m.getType()==JsonType.END_ARRAY){
            this.noeud=this.pile.removeFirst();
            this.substitution=this.stock.removeFirst();
        }
        this.taille--;
        return m;
    }
    /** méthode
     *
     * @param taille  taille de l'arbre,
     * @return void,
     * modificateur de l'arbre
     */
    public boolean isEmpty(){
        return this.taille==0;
    }
    /** méthode
     *
     * @param taille  taille de l'arbre,
     * @return void,
     * modificateur de l'arbre
     */
    public void reconstruire(){
        this.noeud=this.substitution;
        this.substitution=new MaillonTree();;
    }
    /** méthode
     *
     * @param void,
     * @return String chaine,
     * renvoie le String qui représente l'arbre
     */
    public void ajouter(MaillonTree m){
        if(m.getType()==JsonType.START_OBJECT || m.getType()==JsonType.START_ARRAY){
            MaillonTree t = new MaillonTree();
            this.noeud.add(t);
            this.pile.addFirst(this.noeud);
            this.noeud=t;
            this.taille++;
        }
        this.noeud.add(m);
        if(m.getType()==JsonType.END_OBJECT || m.getType()==JsonType.END_ARRAY){
            this.noeud=this.pile.removeFirst();
        }
        this.taille++;
    }
    /** méthode
     *
     * @param void,
     * @return String chaine,
     * renvoie le String qui représente l'arbre
     */
    public String toString(){
        String chaine=this.afficher(0);
        this.reconstruire();
        return chaine;
    }
    /** méthode
     *
     * @param maillon le premier maillon de l'arbre, i entier qui indique la profondeur de la branche
     * @return String chaine,
     * renvoie le String qui représente l'arbre
     */
    public String afficher(int i){
        String chaine="",indent="";
        MaillonTree m=new MaillonTree(null,null);
        for(;!this.isEmpty() &&  m.getType()!=JsonType.END_OBJECT && m.getType()!=JsonType.END_ARRAY;){
            m=this.remove();
            if(m.isNoeud()){
                chaine=chaine+this.afficher(i+1)+"\n";
            }else{
                for(int j=0;j<i;j++){
                    indent=indent+"    ";
                }
                chaine=chaine+indent+m.getValeur()+"    "+m.getType()+"\n";
            }
            indent="";
        }
        return chaine;
    }
    /** main
     *
     * @param args les arguments à la console,
     * @return void,
     * appelle le constructeur pour créer un JSonTree
     */
    public static void main(String[] args) {
        String chaine = new String("{\"status\":\"ok\",\"size\":-3333.5444E+100,\"values\":[0.5,null,1e1],\"object\":{\"nom\":\"instrument\",\"values\":50}}");
        JsonParser j = new JsonParser(chaine);
        JsonTree t = new JsonTree(j);
        System.out.println(t.toString());
    }
}