import java.io.*;

public class SpellChecker {
    
    HashTable hashTable = new HashTable(2000);

    public SpellChecker(){}

    public void loadDictionary(String inputFile) throws Exception{
        
        try {
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            // Declaring loop variable
            String st;
            // Holds true till there is nothing to read
            while ((st = br.readLine()) != null){
                System.out.println(st);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void checkWords(String inputFile){

    }
}
