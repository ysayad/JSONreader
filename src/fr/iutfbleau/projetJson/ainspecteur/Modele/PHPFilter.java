package src.fr.iutfbleau.projetJson.ainspecteur.Modele;

import java.util.*;

public class PHPFilter {
    private JsonTree arbre;
    private int compte;
    private int numero;
    private Deque<Integer> pile;
    /** constructeur
     *
     * @param JsonTree l'arbre,
     * @return void,
     * constructeur du filtre
     */
    public PHPFilter(JsonTree tree){
        this.arbre = tree;
        this.compte=0;
        this.numero=1;
        this.pile= new ArrayDeque<Integer>();
    }
    /** méthode
     *
     * @param void,
     * @return String la chaine représentant les filtres,
     * renvoie la chaine à afficher
     */
    public String toString(){
        String chaine=this.filtrer(this.arbre.getNoeud(),new MaillonTree(null,null));
        return chaine;
    }
    /** méthode
     *
     * @param MaillonTree le noeud que l'on filtre,
     * @return String la chaine filtrée,
     * renvoie la chaine qui à été filtrer
     */
    public String filtrer(MaillonTree noeud,MaillonTree ancetre){
        String texte="",indent="",indent2="    ";
        MaillonTree m=null,souvenir=ancetre;
        for(;!noeud.isEmpty();){
            m=noeud.remove();
            if(m.isNoeud()){
                texte=texte+this.filtrer(m,souvenir);
            }
            if(!m.isNoeud()){
                if(souvenir.getType()==JsonType.KEY_NAME){
                    texte=texte+" => ";
                }
                for(int i=0;i<this.compte-1;i++){
                    indent=indent+"        ";
                }
                if(m.getType()==JsonType.START_OBJECT || m.getType()==JsonType.START_ARRAY){
                    this.compte++;
                }if(m.getType()==JsonType.END_OBJECT || m.getType()==JsonType.END_ARRAY){
                    this.compte--;
                }
                if(m.getType()==JsonType.VALUE_STRING || m.getType()==JsonType.VALUE_NUMBER || m.getType()==JsonType.VALUE_NULL || m.getType()==JsonType.VALUE_TRUE || m.getType()==JsonType.VALUE_FALSE){
                    if(souvenir.getType()!=JsonType.KEY_NAME){
                        texte=texte+"\n";
                        texte=texte+indent+indent2;
                        texte=texte+"["+Integer.toString(this.numero)+"]";
                        this.numero++;
                        texte=texte+" => ";
                        if (m.getType()==JsonType.VALUE_STRING) {
                            texte=texte+m.getValeur().substring(1,m.getValeur().length()-1);
                        }else{
                            texte=texte+m.getValeur();
                        }
                    }else{
                        if (m.getType()==JsonType.VALUE_STRING) {
                            texte=texte+m.getValeur().substring(1,m.getValeur().length()-1);
                        }else{
                            texte=texte+m.getValeur();
                        }
                    }
                }else if(m.getType()==JsonType.KEY_NAME){
                    texte=texte+"\n";
                    texte=texte+indent+indent2;
                    texte=texte+"[";
                    texte=texte+m.getValeur().substring(1,m.getValeur().length()-1);
                    texte=texte+"]";
                }
                else if(m.getType()==JsonType.END_ARRAY || m.getType()==JsonType.END_OBJECT){
                    if(m.getType()==JsonType.END_ARRAY){
                        this.numero=this.pile.removeFirst();
                    }
                    texte=texte+"\n";
                    texte=texte+indent;
                    texte=texte+")";
                }
                else if(m.getType()==JsonType.START_ARRAY || m.getType()==JsonType.START_OBJECT){
                    if(souvenir.getType()!=JsonType.KEY_NAME && souvenir.getType()!=null){
                        texte=texte+"\n";
                        texte=texte+indent+indent2;
                        texte=texte+"["+Integer.toString(this.numero)+"]";
                        texte=texte+" => ";
                        this.numero++;
                    }
                    if(m.getType()==JsonType.START_ARRAY){
                        this.pile.addFirst(this.numero);
                        this.numero=1;
                    }
                    texte=texte+"Array";
                    texte=texte+"\n";
                    if(souvenir.getType()!=null){
                        texte=texte+indent+indent2+indent2;
                    }else{
                        texte=texte+indent;
                    }
                    texte=texte+"(";
                }
            }
            indent="";
            souvenir=m;
        }
        return texte;
    }
}
