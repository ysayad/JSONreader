

public class JsonFilter {
    public JsonFilter(){
    }
    public String filtrer(JsonTree arbre){
        String texte="",indent="";
        int compte=0;
        Maillon m=null,souvenir=new MaillonTree(null,null);
        for(;!arbre.isEmpty();){
            m=arbre.remove();
            if(m.getValeur()!=null){
                if(m.getType()==JsonType.START_OBJECT || m.getType()==JsonType.START_ARRAY){
                    compte++;
                }if(m.getType()==JsonType.END_OBJECT || m.getType()==JsonType.END_ARRAY){
                    compte--;
                }for(int i=0;i<compte;i++){
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
                if(souvenir.getType()==JsonType.KEY_NAME){
                    texte=texte+": ";
                }
                if(m.getType()==JsonType.END_ARRAY || m.getType()==JsonType.END_OBJECT){
                    texte=texte+"\n";
                    texte=texte+indent;
                }/*else if(souvenir.getType()!=null){
                    texte=texte+"\n";
                }*/
                texte=texte+m.getValeur();
                indent="";
                souvenir=m;
            }
        }
        return texte;
    }
}
