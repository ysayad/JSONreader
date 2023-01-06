package fr.iutfbleau.projetJson.Partie1.sayad;

import java.util.LinkedList;

public class JsonNode {
    private JsonType type;
    private String value;
    private LinkedList enfants;

    public JsonNode(JsonType type, String value){
        this.type = type;
        this.value = value;
    }
}
