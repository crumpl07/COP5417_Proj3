import java.io.*;
import java.util.*;

public class SpellChecker {
/*
 *  Contains the spell checker 
 */
    HashTable hashTable = new HashTable(2000);

    public SpellChecker(){
        /*
         *  Constructor for SpellChecker class
         */
    }

    public void loadDictionary(String inputFile) throws Exception{
        /*
         *  Reads the input txt file and appends it to the hash table
         * 
         *  @param inputFile
         */

        int numOfInputs = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            
            String st;
            // While there is more data keep reading
            System.out.println("Reading from " + inputFile);
            while ((st = br.readLine()) != null){
                hashTable.put(st);
                numOfInputs++;
            }
        } catch (Exception e) {}

        System.out.println(numOfInputs + " words loaded");
    }

    public void checkWords(String inputFile){
        int numOfInputs = 0;
        int numOfMisp = 0;
        ArrayList<String> misspelled = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            
            String st;
            // While there is more data keep reading
            System.out.println("Reading from " + inputFile);
            while ((st = br.readLine()) != null){
                if(!hashTable.contains(st)){
                    numOfMisp++;
                    misspelled.add(st);
                } 
                numOfInputs++;
            }
            System.out.println(numOfInputs + " words to check");
            System.out.println(numOfMisp + " misspelled words found");

            //Print suggested words
            for(int i = 0; i < misspelled.size(); i++){
                System.out.println("Suggestions for " + misspelled.get(i) + ":");

                for(int j = 0; j < hashTable.hash.size(); j++){
                    for(int k = 0; k < hashTable.hash.get(j).size(); k++){

                        if(difference(misspelled.get(i), hashTable.hash.get(j).get(k)) > 0.8){
                            System.out.println(hashTable.hash.get(j).get(k));
                        }
                    } 
                }
            }

        } catch (Exception e){}

    }

    public double difference(String misspelled, String dictVal){
        /*
         *  Gets the difference of two strings as a percentage.
         * 
         *  @param misspelled
         *  @param dictVal
         *  @return percentDiff
         */
        String longer = misspelled;
        String shorter = dictVal;
        double percentDiff = 0;

        if(misspelled.length() < dictVal.length()){
          longer = dictVal; 
          shorter = misspelled;
        }

        if(longer.length() == 0){
            percentDiff = 0;
        }
        else{
            percentDiff = (longer.length() - stringDiff(longer, shorter)) / (double) longer.length();
        }
        
        return percentDiff;
    }

    public int stringDiff(String longer, String shorter){
        /*
         *  Gets the difference between two strings using the 
         *  Levenshtein distance algorithm.
         * 
         *  @param longer
         *  @param shorter
         *  @return difference
         */
        longer = longer.toLowerCase();
        shorter = shorter.toLowerCase();
    
        int[] difference = new int[shorter.length() + 1];

        for(int i = 0; i <= longer.length(); i++){

            int lastValue = i;

            for(int j = 0; j <= shorter.length(); j++){
                if(i == 0){
                    difference[j] = j;
                }
                else{
                    if(j > 0){
                        int newValue = difference[j - 1];
                        if(longer.charAt(i - 1) != shorter.charAt(j - 1)){
                            newValue = Math.min(Math.min(newValue, lastValue), difference[j]) + 1;
                        }
                        difference[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if(i > 0){
                difference[shorter.length()] = lastValue;
            }
        }
        return difference[shorter.length()];
    }
}
