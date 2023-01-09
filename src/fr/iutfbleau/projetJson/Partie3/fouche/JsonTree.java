

import java.time.chrono.ThaiBuddhistChronology;
import java.util.*;

/**
 * JsonTree
 */
public class JsonTree {
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
    public JsonTree(JsonParser liste){
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
     * @param liste  l'arbre à faire,
     * @return void,
     * initialisateur de l'arbre
     */
    public void modifier(){
        for(;!this.isEmpty();){
            this.remove();
        }
        this.reconstruire();
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
            for(int i=0;i<this.dico.size();i++){
                //System.out.println(this.dico.get(i).getValeur());
                //System.out.println(m.getValeur());
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
        //System.out.println(this.substitution.getValeur());
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
        this.taille=this.seuil;
        this.noeud=this.substitution;
        //System.out.println(this.substitution.getValeur());
        //System.out.println(this.noeud.getValeur());
        this.substitution=new MaillonTree();
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
            if(m.getType()==JsonType.START_OBJECT){
                t.setValeur("{...}");
            }
            if(m.getType()==JsonType.START_ARRAY){
                t.setValeur("[...]");
            }
            t.setType(JsonType.OPEN);
            this.dico.add(t);
            this.tab.add(0);
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
    public void changement(int i){
        this.tab.set(i,this.tab.get(i)+1%2);
        //System.out.println(this.tab.get(i));
    }
    /** méthode
     *
     * @param void,
     * @return String chaine,
     * renvoie le String qui représente l'arbre
     */
    public String toString(){
        String chaine=this.afficher(this.noeud,0);
        //System.out.println(this.substitution.getValeur());
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
            //System.out.println(m.getType());
            if(m.isNoeud()){
                if(m.getType()==JsonType.OPEN){
                    //System.out.println(this.substitution.getValeur());
                    chaine=chaine+this.afficher(m,i+1)+"\n";
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
     * @param liste  l'arbre à faire,
     * @return void,
     * initialisateur de l'arbre
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
        String chaine = new String("{\"status\":\"ok\",\"size\":-3333.5444E+100,\"values\":[0.5,null,1e1],\"object\":{\"nom\":\"instrument\",\"values\":50}}");
        JsonParser j = new JsonParser(chaine);
        JsonTree t = new JsonTree(j);
        System.out.println(t.toString());
    }
}