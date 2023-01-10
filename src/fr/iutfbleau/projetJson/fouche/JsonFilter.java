
public class JsonFilter {
    private JsonTree arbre;
    private int compte;
    /** constructeur
     *
     * @param JsonTree l'arbre,
     * @return void,
     * constructeur du filtre
     */
    public JsonFilter(JsonTree tree){
        this.arbre = tree;
        this.compte=0;
    }
    /** méthode
     *
     * @param void,
     * @return String la chaine représentant les filtres,
     * renvoie la chaine à afficher
     */
    public String toString(){
        String chaine=this.filtrer(this.arbre.getNoeud());
        return chaine;
    }
    /** méthode
     *
     * @param MaillonTree le noeud que l'on filtre,
     * @return String la chaine filtrée,
     * renvoie la chaine qui à été filtrer
     */
    public String filtrer(MaillonTree noeud){
        String texte="",indent="";
        MaillonTree m=null,souvenir=new MaillonTree(null,null);
        for(;!noeud.isEmpty();){
            m=noeud.remove();
            if(souvenir.getType()==JsonType.KEY_NAME){
                texte=texte+": ";
            }
            if(m.isNoeud()){
                if(m.getType()==JsonType.OPEN){
                    texte=texte+this.filtrer(m);
                }
                if(m.getType()==JsonType.CLOSE){
                    for(int j=0;j<this.compte;j++){
                        indent=indent+"    ";
                    }
                    texte=texte+"\n"+indent+m.getValeur();
                }
            }
            if(!m.isNoeud()){
                if(m.getType()==JsonType.START_OBJECT || m.getType()==JsonType.START_ARRAY){
                    this.compte++;
                }if(m.getType()==JsonType.END_OBJECT || m.getType()==JsonType.END_ARRAY){
                    this.compte--;
                }for(int i=0;i<this.compte;i++){
                    indent=indent+"    ";
                }
                if((m.getType()==JsonType.VALUE_STRING || m.getType()==JsonType.VALUE_NUMBER || m.getType()==JsonType.VALUE_NULL || m.getType()==JsonType.VALUE_TRUE || m.getType()==JsonType.VALUE_FALSE)&& souvenir.getType()!=JsonType.KEY_NAME){
                    if(souvenir.getType()!=JsonType.START_ARRAY){
                        texte=texte+",";    
                    }
                    texte=texte+"\n";
                    texte=texte+indent;
                }if(m.getType()==JsonType.KEY_NAME){
                    if(souvenir.getType()!=JsonType.START_OBJECT){
                        texte=texte+",";    
                    }
                    texte=texte+"\n";
                    texte=texte+indent;
                }
                if(m.getType()==JsonType.END_ARRAY || m.getType()==JsonType.END_OBJECT){
                    texte=texte+"\n";
                    texte=texte+indent;
                }
                texte=texte+m.getValeur();
            }
            indent="";
            souvenir=m;
        }
        return texte;
    }
    /** main
     *
     * @param args liste des arguments à la console,
     * @return void,
     * lance le constructeur
     */
    public static void main(String[] args) {
        String chaine = new String("{\"status\":\"ok\",\"size\":-3333.5444E+100,\"values\":[0.5,null,1e1],\"object\":{\"nom\":\"instrument\",\"values\":50}}");
        JsonParser j = new JsonParser(chaine);
        JsonTree t = new JsonTree(j);
        t.changement(2);
        t.modifier();
        //System.out.println(t.toString());
        JsonFilter r = new JsonFilter(t);
        System.out.println(r.toString());
    }
}
