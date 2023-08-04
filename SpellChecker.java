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
        /*
         *  Checks the input file for mispelt words
         * 
         *  @param inputFile
         */
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

    public double difference(String firstString, String secondString){
        /*
         * Calculates the percentage difference between two strings.
         * 
         * @param firstString 
         * @param secondString 
         * @return percentDifference
         */

        String longerString = firstString;
        String shorterString = secondString;
        double percentDifference = 0;
 
        if(firstString.length() < secondString.length()){
            longerString = secondString; 
            shorterString = firstString;
        }
 
        if(longerString.length() == 0){
            percentDifference = 0;
        }
        else{
         int stringDiff = stringDiff(longerString, shorterString);
         percentDifference = (longerString.length() - stringDiff) / (double) longerString.length();
        }
 
        return percentDifference;
    }

    public int stringDiff(String source, String target){
        /*
         * Calculates the Levenshtein distance between two strings.
         * 
         * @param source 
         * @param target 
         * @return distance
         */

        source = source.toLowerCase();
        target = target.toLowerCase();

        int[] distance = new int[target.length() + 1];

        for(int i = 0; i <= source.length(); i++){
            int previousValue = i;

            for(int j = 0; j <= target.length(); j++){
                if (i == 0){
                    distance[j] = j;
                }
                else{
                    if(j > 0){
                        int newValue = distance[j - 1];
                        if(source.charAt(i - 1) != target.charAt(j - 1)){
                            newValue = Math.min(Math.min(newValue, previousValue), distance[j]) + 1;
                        }
                        distance[j - 1] = previousValue;
                        previousValue = newValue;
                    }
                }
            }
            if (i > 0) {
                distance[target.length()] = previousValue;
            }
        }
        return distance[target.length()];
    }
}
