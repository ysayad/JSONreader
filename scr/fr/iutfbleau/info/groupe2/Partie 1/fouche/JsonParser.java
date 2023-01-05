/**
 * JSonParser
 */
public class JsonParser {
    private MaillonParser premier;
    private String element;
    private String chaine;
    private String base;
    /** constructeur
     *
     * @param texte  la chaine à écrire,
     * @return void,
     * construis le JSon parser avec une chaine de charactere
     */
    public JsonParser(String texte) {
        this.base=texte;
        this.chaine=texte;
        this.premier=this.transformer(this.premier);
    }
    /** méthode
     *
     * @param maillon un maillon qui sera modifier,
     * @return MaillonParser le premier maillon de la chaine,
     * construis et affiche la fenetre
     */
    public MaillonParser transformer(MaillonParser maillon){
        if(this.chaine.length()==0){
            return maillon;
        }
        this.chaine= this.retirer(this.chaine);
        String type=this.typer();
        maillon = new MaillonParser (this.element,type);

        maillon.setSuivant(transformer(maillon.getSuivant()));
        return maillon;
    }
    /** méthode
     *
     * @param void,
     * @return String le type de l'élément,
     * renvoie le type de l'élément de la chaine
     */
    public String typer(){
        if(this.element.compareTo("{")==0 || this.element.compareTo("}")==0 || this.element.compareTo("[")==0 || this.element.compareTo("]")==0 || this.element.compareTo(",")==0 || this.element.compareTo(":")==0){
            return "separateur";
        }
        if(this.chaine.charAt(0)==':'){
            return "nom";
        }
        if(this.base.charAt(this.base.length()-this.chaine.length()-this.element.length()-1)==':'){
            if(this.element.charAt(0)=='"'){
                return "chaine";
            }
            if(this.element.codePointAt(0)>=48 && this.element.codePointAt(0)<=57 || this.element.charAt(0)=='-'){
                return "nombre";
            }
        }
        return "autre";
    }
    /** méthode
     *
     * @param String la chaine de charactere,
     * @return String l'élément,
     * renvoie un élément de la chaine de String
     */
    public String retirer (String texte){
        if(texte.charAt(0)=='"'){
            return this.caractere(texte);
        }
        if(texte.codePointAt(0)>=48 && texte.codePointAt(0)<=57 || texte.charAt(0)=='-'){
            return this.entier(texte);
        }
        if(texte.length()>=5){
            String mot=texte.substring(0,4);
            String mot2=texte.substring(0,5);
            if(mot.compareTo("null")==0){
                this.element=mot;
                return texte.substring(4);
            }
            if(mot.compareTo("true")==0){
                this.element=mot;
                return texte.substring(4);
            }
            if(mot2.compareTo("false")==0){
                this.element=mot2;
                return texte.substring(5);
            }
        }
        char[] tab = {texte.charAt(0)};
        this.element=new String(tab);
        return texte.substring(1);
    }
    /** méthode
     *
     * @param String la chaine de charactere,
     * @return String l'élément,
     * renvoie l'élement de type String
     */
    public String caractere(String texte){
        this.element="\"";
        texte=texte.substring(1);
        for(;texte.charAt(0)!='"';){
            this.element=this.element+texte.charAt(0);
            texte=texte.substring(1);
        }
        this.element=this.element+texte.charAt(0);
        return texte.substring(1);
    }
    /** méthode
     *
     * @param String la chaine de charactere,
     * @return String l'élément,
     * renvoie l'élement de type entier
     */
    public String entier(String texte){
        this.element=""+texte.charAt(0);
        texte=texte.substring(1);
        for(;texte.codePointAt(0)>=48 && texte.codePointAt(0)<=57;){
            this.element=this.element+texte.charAt(0);
            texte=texte.substring(1);
        }
        if(texte.charAt(0)=='.'){
            this.element=this.element+texte.charAt(0);
            texte=texte.substring(1);
            for(;texte.codePointAt(0)>=48 && texte.codePointAt(0)<=57;){
                this.element=this.element+texte.charAt(0);
                texte=texte.substring(1);
            }
        }
        if(texte.charAt(0)=='e' || texte.charAt(0)=='E'){
            this.element=this.element+texte.charAt(0);
            texte=texte.substring(1);
            if(texte.charAt(0)=='-' || texte.charAt(0)=='+'){
                this.element=this.element+texte.charAt(0);
                texte=texte.substring(1);
            }
            for(;texte.codePointAt(0)>=48 && texte.codePointAt(0)<=57;){
                this.element=this.element+texte.charAt(0);
                texte=texte.substring(1);
            }
        }
        return texte;
    }
    /** méthode
     *
     * @param void,
     * @return String contenu,
     * renvoie le contenu du JSonParser
     */
    public String toString(){
        return this.afficher(this.premier);
    }
    /** méthode
     *
     * @param MaillonParser le maillon dans lequel retirer l'élément,
     * @return String l'élément,
     * renvoie un élément du JSonParser
     */
    public String afficher(MaillonParser maillon){
        if(maillon.getSuivant()==null){
            return maillon.getValeur();
        }
        return maillon.getValeur()+" "+this.afficher(maillon.getSuivant());
    }
    /** méthode
     *
     * @param void,
     * @return Maillon un maillon,
     * retire un maillons du JSonParser dans l'ordre
     */
    public Maillon remove(){
        MaillonParser texte =this.premier;
        this.premier=this.premier.getSuivant();
        return texte;
    }
    /** méthode
     *
     * @param void,
     * @return boolean true ou false,
     * vérifie si le JSonParser est vide
     */
    public boolean isEmpty(){
        return this.premier==null;
    }
    /** main
     *
     * @param args les arguments à la console,
     * @return void,
     * appelle le constructeur pour créer un JSonParser
     */
    public static void main(String[] args) {
        String chaine = new String("{\"status\":\"ok\",\"size\": -3333.5444E+100,\"values\": [0.5,null,1e1]}");
        JsonParser j = new JsonParser(chaine);
        System.out.println(j.toString());
    }
}