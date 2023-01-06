/**
 * JsonTree
 */
public class JsonTree {
    private MaillonTree racine;
    public final int debut=0;
    public final int milieu=1;
    public final int fin=2;
    private int indent;
    /** constructeur
     *
     * @param liste  l'arbre à faire,
     * @return void,
     * constructeur de l'arbre
     */
    public JsonTree(JsonParser liste){
        int taille=initialiser(liste);
        modifier(taille);
    }
    /** méthode
     *
     * @param liste  l'arbre à faire,
     * @return void,
     * initialisateur de l'arbre
     */
    public int initialiser(JsonParser liste){
        MaillonTree m=null,n=null;
        int taille=0;
        if(!liste.isEmpty()){
            Maillon o=liste.remove();
            n=new MaillonTree(o.getValeur(),o.getType());
            this.racine=n;
            taille=1;
        }   
        for(;!liste.isEmpty();){
            taille++;
            Maillon o=liste.remove();
            m=new MaillonTree(o.getValeur(),o.getType());
            this.ajouterDroite(n,m);
            this.ajouterPere(m,n);
            n=m;
        }
        return taille;
    }
    /** méthode
     *
     * @param taille  taille de l'arbre,
     * @return void,
     * modificateur de l'arbre
     */
    public void modifier(int taille){
        for(int i=1;i<=taille;i++){
            this.indent=i;
            MaillonTree maillon=this.parcourir(this.racine,debut);
            String chaine=maillon.getValeur();
            if(chaine.compareTo(",")==0){
                int compteurAccolade=0,compteurCrochet=0;
                for (;maillon!=this.racine && compteurAccolade!=0 || compteurCrochet!=0 || (maillon.getPere().getValeur().compareTo(",")!=0 && maillon.getPere().getValeur().compareTo("[")!=0 && maillon.getPere().getValeur().compareTo("{")!=0);) {
                    if(maillon.getPere().getValeur().compareTo("}")==0){
                        compteurAccolade++;
                    }
                    if(maillon.getPere().getValeur().compareTo("]")==0){
                        compteurCrochet++;
                    }
                    if(maillon.getPere().getValeur().compareTo("{")==0){
                        compteurAccolade--;
                    }
                    if(maillon.getPere().getValeur().compareTo("[")==0){
                        compteurCrochet--;
                    }
                    permuter(maillon.getPere(),maillon);
                }
            }
            if(chaine.compareTo(":")==0){
                for (;maillon!=this.racine && maillon.getPere().getValeur().compareTo(",")!=0 && maillon.getPere().getValeur().compareTo("{")!=0;) {
                    permuter(maillon.getPere(),maillon);
                }
            }
        }
        int compteurVirgule=0;
        for(int i=1;i<=taille;i++){
            this.indent=i;
            MaillonTree maillon=this.parcourir(this.racine,debut);
            String chaine=maillon.getValeur();
            if(chaine.compareTo(",")==0){
                compteurVirgule++;
            }
            if(chaine.compareTo("{")==0){

                boolean milieu=false,reussite=false;
                int compteurAccolade=0,compteurCrochet=0;
                for(int j=1;j<=taille;j++){
                    MaillonTree maillon2=this.parcourir(this.racine,debut);
                    String chaine2=maillon2.getValeur();
                    if(maillon2==maillon){
                        milieu=true;
                    }else if(maillon2!=maillon && chaine2.compareTo("{")==0){
                        milieu=false;
                        compteurAccolade++;
                    }else if(chaine2.compareTo("[")==0){
                        milieu=false;
                        compteurCrochet++;
                    }else if(chaine2.compareTo("}")==0 && compteurAccolade!=0){
                        compteurAccolade--;
                    }else if(chaine2.compareTo("}")==0 && compteurAccolade==0 || compteurCrochet==0){
                        milieu=false;
                    }else if(chaine2.compareTo("]")==0 && compteurCrochet!=0){
                        compteurCrochet--;
                    }
                    //this.indent=i;
                    if (chaine2.compareTo(",")==0){
                        if (milieu==true && reussite==false){
                            optimiser(maillon2,compteurVirgule);
                        }
                        reussite=true;
                    }
                }
            }
            if(chaine.compareTo("[")==0){
                boolean milieu=false,reussite=false;
                int compteurAccolade=0,compteurCrochet=0;
                for(int j=1;j<=taille;j++){
                    MaillonTree maillon2=this.parcourir(this.racine,debut);
                    String chaine2=maillon2.getValeur();
                    if(maillon2==maillon){
                        milieu=true;
                    }else if(maillon2!=maillon && chaine2.compareTo("[")==0){
                        milieu=false;
                        compteurCrochet++;
                    }else if(chaine2.compareTo("{")==0){
                        milieu=false;
                        compteurAccolade++;
                    }else if(chaine2.compareTo("]")==0 && compteurCrochet!=0){
                        compteurCrochet--;
                    }else if(chaine2.compareTo("]")==0 && compteurAccolade==0 || compteurCrochet==0){
                        milieu=false;
                    }else if(chaine2.compareTo("}")==0 && compteurAccolade!=0){
                        compteurAccolade--;
                    }
                    //this.indent=i;
                    if (chaine2.compareTo(",")==0){
                        if (milieu==true && reussite==false){
                            optimiser(maillon2,compteurVirgule);
                        }
                        reussite=true;
                    }
                }
            }
        }
        for(int i=1;i<=taille;i++){
            this.indent=i;
            MaillonTree maillon=this.parcourir(this.racine,debut);
            String chaine=maillon.getValeur();
            if(chaine.compareTo("{")==0){
                
                /*for (;maillon.getDroite()!=null;) {
                    permuter(maillon,maillon.getDroite());
                }*/
            }
            if(chaine.compareTo("[")==0){
                /*for (;maillon.getDroite()!=null;) {
                    permuter(maillon,maillon.getDroite());
                }*/
            }
        }
    }

    public void optimiser(MaillonTree m,int nb){
        
    }
    /** méthode
     *
     * @param taille  taille de l'arbre,
     * @return void,
     * modificateur de l'arbre
     */
    public MaillonTree parcourir(MaillonTree maillon,int etat){
        MaillonTree m=null,n=null;
        if(maillon.getGauche()!=null){
            m=this.parcourir(maillon.getGauche(),debut);
            if(m!=null){
                return m;
            }
        }
        this.indent=this.indent-1;
        if(this.indent==0){
            return maillon;
        }
        if(maillon.getDroite()!=null){
            n=this.parcourir(maillon.getDroite(),debut);
            if(n!=null){
                return n;
            }
        }
        return null;
    }
    /** méthode
     *
     * @param pere maillon de l'arbre, fils maillon de l'arbre,
     * @return void,
     * permuter deux éléments de l'arbre
     */
    public void permuter(MaillonTree pere, MaillonTree fils){
        if(pere!=this.racine){
            if(pere.getPere().getDroite()==pere){
                pere.getPere().setDroite(fils);
            }
            if(pere.getPere().getGauche()==pere){
                pere.getPere().setGauche(fils);
            }
        }else{
            this.racine=fils;
        }

        fils.setPere(pere.getPere());
        pere.setPere(fils);
        if(pere.getDroite()==fils){
            if(fils.getGauche()!=null){
                fils.getGauche().setPere(pere);
            }
            pere.setDroite(fils.getGauche());
            fils.setGauche(pere);
            return;
        }
        if(pere.getGauche()==fils){
            if(fils.getDroite()!=null){
                fils.getDroite().setPere(pere);
            }
            pere.setGauche(fils.getDroite());
            fils.setDroite(pere);
            return;
        }
        
    }
    /** méthode
     *
     * @param pere maillon de l'arbre, fils maillon de l'arbre,
     * @return void,
     * ajouter le fils à l'arbre
     */
    public boolean ajouter(MaillonTree pere, MaillonTree fils){
        if(ajouterGauche(pere,fils)){
            return true;
        }
        if(ajouterDroite(pere,fils)){
            return true;
        }
        return false;
    }
    /** méthode
     *
     * @param pere maillon de l'arbre, fils maillon de l'arbre,
     * @return void,
     * ajouter le fils à gauche du pere de l'arbre
     */
    public boolean ajouterGauche(MaillonTree pere, MaillonTree fils){
        if(pere.getGauche()==null){
            pere.setGauche(fils);
            return true;
        }
        return false;
    }
    /** méthode
     *
     * @param pere maillon de l'arbre, fils maillon de l'arbre,
     * @return void,
     * ajouter le fils à droite du pere de l'arbre
     */
    public boolean ajouterDroite(MaillonTree pere, MaillonTree fils){
        if(pere.getDroite()==null){
            pere.setDroite(fils);
            return true;
        }
        return false;
    }
    /** méthode
     *
     * @param fils maillon de l'arbre, pere maillon de l'arbre,
     * @return void,
     * ajouter le pere au fils de l'arbre
     */
    public boolean ajouterPere(MaillonTree fils, MaillonTree pere){
        if(fils.getPere()==null){
            fils.setPere(pere);
            return true;
        }
        return false;
    }
    /** méthode
     *
     * @param void,
     * @return String chaine,
     * renvoie le String qui représente l'arbre
     */
    public String toString(){
        return this.afficher(this.racine,0);
    }
    /** méthode
     *
     * @param maillon le premier maillon de l'arbre, i entier qui indique la profondeur de la branche
     * @return String chaine,
     * renvoie le String qui représente l'arbre
     */
    public String afficher(MaillonTree maillon,int i){
        String indent="";
        for(int j=0;j<i;j++){
            indent=indent+"    "; 
        }
        if(maillon.getGauche()==null && maillon.getDroite()==null){
            return indent+maillon.getValeur()+"\n";
        }
        if(maillon.getGauche()==null){
            return indent+maillon.getValeur()+"\n"+"D "+i+this.afficher(maillon.getDroite(), i+1);
        }
        if(maillon.getDroite()==null){
            return indent+maillon.getValeur()+"\n"+"G "+i+this.afficher(maillon.getGauche(), i+1);
        }
        return indent+maillon.getValeur()+"\n"+"K "+i+this.afficher(maillon.getGauche(),i+1)+"L "+i+this.afficher(maillon.getDroite(), i+1);
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