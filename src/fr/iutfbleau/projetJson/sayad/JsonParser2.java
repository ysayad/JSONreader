//package fr.iutfbleau.projetJson.Partie1.sayad;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class JsonParser2 {

    private InputStream stream;
    private final StringBuilder buffer = new StringBuilder();
    private JsonType type;
    private String value;
    
    public JsonParser2(String chemin){
        try {
            URL url = new URL(chemin);
            this.stream = url.openStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JsonType getType(){
        return type;
    }

    public String getValue(){
        return value;
    }

    public JsonType next(){
        int c = skipWhitespace();

        switch (c) {
            case -1:
                return null;
            case '{':
                buffer.setLength(0);
                buffer.append((char) c);
                this.type = JsonType.START_OBJECT;
                this.value = buffer.toString();
                return type;
            case '}':
                buffer.setLength(0);
                buffer.append((char) c);
                this.type = JsonType.END_OBJECT;
                this.value = buffer.toString();
                return type;
            case '[':
                buffer.setLength(0);
                buffer.append((char) c);
                this.type = JsonType.START_ARRAY;
                this.value = buffer.toString();
                return type;
            case ']':
                buffer.setLength(0);
                buffer.append((char) c);
                this.type = JsonType.START_ARRAY;
                this.value = buffer.toString();
                return type;
            case '"':
                buffer.setLength(0);
                buffer.append((char) c);
                c = readString();
                try {
                    c = stream.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                if (c ==':') {
                    this.type = JsonType.KEY_NAME;
                } else {
                    this.type = JsonType.VALUE_STRING;
                }
                this.value = buffer.toString();
                return type;
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                buffer.setLength(0);
                buffer.append((char) c);
                c = readNumber();
                this.type = JsonType.VALUE_NUMBER;
                this.value = buffer.toString();
                return type;
            case 'n':
                this.type = JsonType.VALUE_NULL;
                this.value = "null";
                try {
                    stream.readNBytes(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return type;
            case 't':
                this.type = JsonType.VALUE_TRUE;
                this.value = "true";
                try {
                    stream.readNBytes(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return type;
            case 'f':                
                this.type = JsonType.VALUE_FALSE;
                this.value = "false";
                try {
                    stream.readNBytes(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return type;
            default:
                try {
                    stream.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
        }
    }

    private int readString(){
        int c = 0;
        while (c!='"') {
            try {
                c = stream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer.append((char) c);
        }
        return c;
    }

    private int readNumber(){
        int c = 0;
        while (true) {
            try {
                c = stream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (Character.isDigit(c) || c == '.' || c == 'e' || c == 'E') {
                buffer.append((char) c);
            } else {
                break;
            }
        }
        return c;
    }

    private int skipWhitespace(){
        int c = 0;
        do {
            try {
                c = stream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (Character.isWhitespace(c));
        return c;
    }

    private boolean isAlphaNumeric(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }
}
