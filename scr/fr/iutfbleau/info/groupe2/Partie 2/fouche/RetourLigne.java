

public class RetourLigne {
    public String filtrer(String chaine){
        String texte="";
        for(int i=0;i<chaine.length();i++){
            char c=chaine.charAt(i);
            if(c=='}' || c==']'){
                texte=texte+'\n';
            }
            texte=texte+c;
            if(c=='{' || c=='[' || c==','){
                texte=texte+'\n';
            }
        }
        return texte;
    }
    public static void main(String[] args) {
        String chaine = new String("{\"status\": \"ok\",\"size\": 3,\"values\": [0.5,null,1e1]}");
        RetourLigne r = new RetourLigne();
        System.out.println(r.filtrer(chaine));
    }
}
