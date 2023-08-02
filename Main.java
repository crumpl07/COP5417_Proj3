public class Main {
    public static void main(String[] args) throws Exception{
        // Create an instance of the SpellCheck class
        SpellChecker spellChecker = new SpellChecker();

        // Load the dictionary file into the hash table
        spellChecker.loadDictionary("dict.txt");

        // Perform spell check on the input file
        //spellChecker.checkWords("input.txt");
    }
}