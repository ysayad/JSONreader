package src.fr.iutfbleau.projetJson.ainspecteur.Modele;
/**
 * JSonParser
 */
public class JsonParser implements Parser{
    private MaillonParser premier;
    private String element;
    private String chaine;
    /** constructeur
     *
     * @param texte  la chaine à ecrire,
     * @return void,
     * construis le JSon parser avec une chaine de charactere
     */
    public JsonParser(JsonString texte) {
        this.chaine=texte.toString();
        this.premier=new MaillonParser(null, null);
        this.premier=this.transformer(this.premier);
        //this.premier=this.inversion(this.premier);
    }
    /** constructeur
     *
     * @param texte  la chaine à ecrire,
     * @return void,
     * construis le JSon parser avec une chaine de charactere
     */
    public JsonParser(String texte) {
        this.chaine=texte;
        this.premier=new MaillonParser(null, null);
        this.premier=this.transformer(this.premier);
    }
    /** methode
     *
     * @param MaillonParser un maillon qui sera modifier,
     * @return MaillonParser le premier maillon de la chaine,
     * construis et affiche la fenetre
     */
    public MaillonParser transformer(MaillonParser maillon){
        MaillonParser apres=null,debut=null;
        for(;this.chaine.length()!=0;){
            this.chaine=this.retirer(this.chaine);
            if(this.element!=null){
                if(this.element.compareTo(" ")!=0 && Character.codePointAt(this.element,0)!=10){
                    JsonType type=this.typer();
                    maillon = new MaillonParser(this.element,type);
                    if(apres==null){
                        apres=maillon;
                        debut=maillon;
                    }
                    else if(maillon!=null){
                        apres.setSuivant(maillon);
                        apres=maillon;
                    }
                }
            }
        }
        return debut;
    }
    /** methode
     *
     * @param void,
     * @return String le type de l'element,
     * renvoie le type de l'element de la chaine
     */
    public JsonType typer(){
        if(this.element.compareTo("{")==0){
            return JsonType.START_OBJECT;
        }
        if(this.element.compareTo("}")==0){
            return JsonType.END_OBJECT;
        }
        if(this.element.compareTo("[")==0){
            return JsonType.START_ARRAY;
        }
        if(this.element.compareTo("]")==0){
            return JsonType.END_ARRAY;
        }
        if(this.chaine.charAt(0)==':'){
            return JsonType.KEY_NAME;
        }
        if(this.element.charAt(0)=='"'){
            return JsonType.VALUE_STRING;
        }
        if(this.element.codePointAt(0)>=48 && this.element.codePointAt(0)<=57 || this.element.charAt(0)=='-'){
            return JsonType.VALUE_NUMBER;
        }
        if(this.element.compareTo("true")==0){
            return JsonType.VALUE_TRUE;
        }
        if(this.element.compareTo("false")==0){
            return JsonType.VALUE_FALSE;
        }
        if(this.element.compareTo("null")==0){
            return JsonType.VALUE_NULL;
        }
        else {
            return JsonType.VALUE_NULL;
        }
    }
    /** methode
     *
     * @param String la chaine de charactere,
     * @return String l'element,
     * renvoie un element de la chaine de String
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
        }if(texte.charAt(0)!=':' && texte.charAt(0)!=','){
            char[] tab = {texte.charAt(0)};
            this.element=new String(tab);
            return texte.substring(1);
        }
        this.element=null;
        return texte.substring(1);
    }
    /** methode
     *
     * @param String la chaine de charactere,
     * @return String l'element,
     * renvoie l'element de type String
     */
    public String caractere(String texte){
        this.element="\"";
        String precedant=" ";
        texte=texte.substring(1);
        for(;(texte.charAt(0)!='"' || precedant.charAt(0)=='\\')  && texte.length()!=0;){
            this.element=this.element+texte.charAt(0);
            char[] c={this.element.charAt(this.element.length()-1)};
            precedant=new String(c);
            texte=texte.substring(1);
        }
        this.element=this.element+texte.charAt(0);
        return texte.substring(1);
    }
    /** methode
     *
     * @param String la chaine de charactere,
     * @return String l'element,
     * renvoie l'element de type entier
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
    /** methode
     *
     * @param void,
     * @return String contenu,
     * renvoie le contenu du JSonParser
     */
    public String toString(){
        return this.afficher(this.premier);
    }
    /** methode
     *
     * @param MaillonParser le maillon dans lequel retirer l'element,
     * @return String l'element,
     * renvoie un element du JSonParser
     */
    public String afficher(MaillonParser maillon){
        String chaine="";
        for(;maillon.getSuivant()!=null;){
            chaine=chaine+maillon.getValeur()+" "+maillon.getType()+" ";
            maillon=maillon.getSuivant();
        }
        chaine=chaine+maillon.getValeur()+" "+maillon.getType()+" ";
        return chaine;
    }
    /** methode
     *
     * @param void,
     * @return Maillon un maillon,
     * retire un maillons du JSonParser dans l'ordre
     */
    public Maillon next(){
        MaillonParser texte =this.premier;
        this.premier=this.premier.getSuivant();
        return texte;
    }
    /** methode
     *
     * @param void,
     * @return boolean true ou false,
     * verifie si le JSonParser est vide
     */
    public boolean hasNext(){
        return this.premier==null;
    }
    /** main
     *
     * @param args les arguments à la console,
     * @return void,
     * appelle le constructeur pour creer un JSonParser
     */
    public static void main(String[] args) {
        //String chaine = new String("{\"status\":\"ok\",\"size\":-3333.5444E+100,\"values\":[0.5,null,1e1]}");
        JsonString s = new JsonString(args[0]);
        JsonParser j = new JsonParser(s.toString());
        System.out.println(j.toString());
    }
}