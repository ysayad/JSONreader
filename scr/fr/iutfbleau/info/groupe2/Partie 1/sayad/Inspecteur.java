public class Inspecteur {
    public static void main(String[] args) {
        JsonString brut = new JsonString(args[0]);
        JsonParser parser = new JsonParser(brut);
        JsonTree tree = new JsonTree(parser);

        tree.toString();
    }
}
