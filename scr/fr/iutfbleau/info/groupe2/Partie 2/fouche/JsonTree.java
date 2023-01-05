/**
 * JsonTree
 */
public class JsonTree {
    private MaillonTree racine;
    public final int debut=0;
    public final int milieu=1;
    public final int fin=2;
    private int indent;
    public JsonTree(JsonParser liste){
        int taille=initialiser(liste);
        modifier(taille);
    }
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
    public void modifier(int taille){
        for(int i=1;i<=taille;i++){
            this.indent=i;
            MaillonTree maillon=this.parcourir(this.racine,debut);
            String chaine=maillon.getValeur();
            if(chaine.compareTo(",")==0){
                for (;maillon!=this.racine && maillon.getPere().getValeur().compareTo(",")!=0 && maillon.getPere().getValeur().compareTo("[")!=0 && maillon.getPere().getValeur().compareTo("{")!=0;) {
                    permuter(maillon.getPere(),maillon);
                    
                }
            }
            if(chaine.compareTo(":")==0){
                for (;maillon!=this.racine && maillon.getPere().getValeur().compareTo(",")!=0 && maillon.getPere().getValeur().compareTo("{")!=0;) {
                    permuter(maillon.getPere(),maillon);
                }
            }
        }
        //System.out.println(this.racine.getGauche().getValeur());
        for(int i=1;i<=taille;i++){
            this.indent=i;
            MaillonTree maillon=this.parcourir(this.racine,debut);
            String chaine=maillon.getValeur();
            if(chaine.compareTo("{")==0){
                permuter(maillon,maillon.getDroite());
                for (;maillon.getDroite()!=null;) {
                    permuter(maillon,maillon.getDroite());
                }
            }
            if(chaine.compareTo("[")==0){
                for (;maillon.getDroite()!=null;) {
                    permuter(maillon,maillon.getDroite());
                }
            }
        }
    }
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
    public boolean ajouter(MaillonTree pere, MaillonTree fils){
        if(ajouterGauche(pere,fils)){
            return true;
        }
        if(ajouterDroite(pere,fils)){
            return true;
        }
        return false;
    }
    public boolean ajouterGauche(MaillonTree pere, MaillonTree fils){
        if(pere.getGauche()==null){
            pere.setGauche(fils);
            return true;
        }
        return false;
    }
    public boolean ajouterDroite(MaillonTree pere, MaillonTree fils){
        if(pere.getDroite()==null){
            pere.setDroite(fils);
            return true;
        }
        return false;
    }
    public boolean ajouterPere(MaillonTree fils, MaillonTree pere){
        if(fils.getPere()==null){
            fils.setPere(pere);
            return true;
        }
        return false;
    }
    public String toString(){
        return this.afficher(this.racine,0);
    }
    public String afficher(MaillonTree maillon,int i){
        String indent="";
        for(int j=0;j<i;j++){
            indent=indent+"    "; 
        }
        if(maillon.getGauche()==null && maillon.getDroite()==null){
            return indent+maillon.getValeur()+"\n";
        }
        if(maillon.getGauche()==null){
            return indent+maillon.getValeur()+"\n"+this.afficher(maillon.getDroite(), i+1);
        }
        if(maillon.getDroite()==null){
            return indent+maillon.getValeur()+"\n"+this.afficher(maillon.getGauche(), i+1);
        }
        return indent+maillon.getValeur()+"\n"+this.afficher(maillon.getGauche(),i+1)+this.afficher(maillon.getDroite(), i+1);
    }
    public static void main(String[] args) {
        String chaine = new String("{\"status\":\"ok\",\"size\":-3333.5444E+100,\"values\":[0.5,null,1e1],\"object\":{\"nom\":\"instrument\",\"values\":50}}");
        JsonParser j = new JsonParser(chaine);
        JsonTree t = new JsonTree(j);
        System.out.println(t.toString());
    }
}