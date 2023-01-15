package src.fr.iutfbleau.projetJson.ainspecteur.Modele;


import java.util.*;

/**
 * JsonTree
 */
public class JsonTree implements Tree{
    private MaillonTree noeud;
    private MaillonTree substitution;
    private Deque<MaillonTree> pile;
    private Deque<MaillonTree> stock;
    private List<MaillonTree> dico;
    private List<Integer> tab;
    private int taille;
    private int seuil;
    /** constructeur
     *
     * @param liste  l'arbre à faire,
     * @return void,
     * constructeur de l'arbre
     */
    public JsonTree(Parser liste){
        this.pile=new ArrayDeque<MaillonTree>();
        this.stock=new ArrayDeque<MaillonTree>();
        this.dico = new ArrayList<MaillonTree>();
        this.tab = new ArrayList<Integer>();
        this.noeud=new MaillonTree();
        this.substitution=new MaillonTree();
        this.taille=0;
        initialiser(liste);
        this.seuil=this.taille;
    }
    /** méthode
     *
     * @param liste  l'arbre à faire,
     * @return void,
     * initialisateur de l'arbre
     */
    public void initialiser(Parser liste){
        MaillonTree m=new MaillonTree();
        MaillonTree n=new MaillonTree();
        this.noeud=n;
        for(int i=0;!liste.hasNext();i++){
            Maillon o=liste.next();
            m=new MaillonTree(o.getValeur(),o.getType());
            this.ajouter(m,i);
        }
    }
    /** méthode
     *
     * @param void,
     * @return void,
     * modifie l'arbre en retirant tous les éléments et en le reconstruisant 
     */
    public void modifier(){
        for(;!this.isEmpty();){
            this.remove();
        }
        this.reconstruire();
    }
    /** méthode
     *
     * @param void,
     * @return MaillonTree l'élément enlevé,
     * retire un élément de l'arbre
     */
    public MaillonTree remove(){
        MaillonTree m = this.noeud.remove();
        this.substitution.add(m);
        if(m.isNoeud()){
            for(int i=0;i<this.dico.size();i++){
                if(this.dico.get(i).equals(m)){
                    if(this.tab.get(i)==0){
                        m.setType(JsonType.OPEN);
                    }
                    if(this.tab.get(i)==1){
                        m.setType(JsonType.CLOSE);
                    }
                }
            }
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
     * @param void,
     * @return boolean arbre vide,
     * vérifie si l'arbre est vide
     */
    public boolean isEmpty(){
        return this.taille==0;
    }
    /** méthode
     *
     * @param void,
     * @return void,
     * reconstruit l'arbre
     */
    public void reconstruire(){
        this.taille=this.seuil;
        this.noeud=this.substitution;
        this.substitution=new MaillonTree();
    }
    /** méthode
     *
     * @param MaillonTree m le maillon à ajouter,
     * @return void,
     * ajoute un élément à l'arbre
     */
    public void ajouter(MaillonTree m,int i){
        if(m.getType()==JsonType.START_OBJECT || m.getType()==JsonType.START_ARRAY){
            MaillonTree t = new MaillonTree();
            if(m.getType()==JsonType.START_OBJECT){
                t.setValeur("{...}");
            }
            if(m.getType()==JsonType.START_ARRAY){
                t.setValeur("[...]");
            }if(i==0){
                t.setType(JsonType.OPEN);
                this.tab.add(0);
            }else{
                t.setType(JsonType.CLOSE);
                this.tab.add(1);
            }
            this.dico.add(t);
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
     * @param int i le numero du noeud dans l'ordre,
     * @return void,
     * modifie l'ouverture d'un noeud
     */
    public void changement(int i){
        this.tab.set(i,(this.tab.get(i)+1)%2);
    }
    /** méthode
     *
     * @param void,
     * @return String chaine,
     * renvoie le String qui représente l'arbre
     */
    public String toString(){
        String chaine=this.afficher(this.noeud,0);
        this.reconstruire();
        return chaine;
    }
    /** méthode
     *
     * @param maillon le premier maillon de l'arbre, i entier qui indique la profondeur de la branche
     * @return String chaine,
     * renvoie le String qui représente l'arbre
     */
    public String afficher(MaillonTree maillon,int i){
        String chaine="",indent="";
        MaillonTree m=new MaillonTree(null,null);
        for(;!maillon.isEmpty() &&  m.getType()!=JsonType.END_OBJECT && m.getType()!=JsonType.END_ARRAY;){
            m=maillon.remove();
            if(m.isNoeud()){
                if(m.getType()==JsonType.OPEN){
                    chaine=chaine+this.afficher(m,i+1);
                }
                if(m.getType()==JsonType.CLOSE){
                    for(int j=0;j<i;j++){
                        indent=indent+"    ";
                    }
                    chaine=chaine+indent+m.getValeur()+"\n";
                }
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
    /** méthode
     *
     * @param void,
     * @return MaillonTree noeud de l'arbre en cours de maniement,
     * renvoie le noeud de l'arbre
     */
    public MaillonTree getNoeud(){
        return this.noeud;
    }
    /** main
     *
     * @param args les arguments à la console,
     * @return void,
     * appelle le constructeur pour créer un JSonTree
     */
    public static void main(String[] args) {
        //String chaine = new String("{\"status\":\"ok\",\"size\":-3333.5444E+100,\"values\":[0.5,null,1e1],\"object\":{\"nom\":\"instrument\",\"values\":50}}");
        JsonString s = new JsonString(args[0]);
        JsonParser j = new JsonParser(s.toString());
        JsonTree t = new JsonTree(j);
        System.out.println(t.toString());
    }
}