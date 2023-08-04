import java.util.ArrayList;

public class HashTable {
/*
 *  Contains the hash table
 */
    public ArrayList<ArrayList<String>> hash;

    public HashTable(int size){
        /*
         *  Creates a blank hash table based with the specified
         *  input size.
         * 
         *  @param size
         */
        hash = new ArrayList<ArrayList<String>>();
        
        for(int i = 0; i < size; i++){
            ArrayList<String> st = new ArrayList<String>();
            st.add("");
            hash.add(st);
        }
    }

    public int hash(String key){
        /*
         *  Gets index of the input key by taking the absolute value and modulus
         *  of the key.
         * 
         *  @param key
         *  @return hash
         */
        return Math.abs(key.hashCode() % 2000);
    }

    public void put(String key){
        /*
         *  Adds the key value to a hash table based on its hash code.
         *  Uses "seperate chaining" or external chaining to add values in case
         *  of a collision.
         * 
         *  @param key
         */
        
        int index = hash(key);

        ArrayList<String> st = hash.get(index);
        if(st.get(0).equals("")){
            st.set(0, key);
        }
        else{
            st.add(key);
        }
       

        hash.set(index, st);
    }

    public boolean contains(String key){
        /*
         *  Checks if the key value is inside the hash table
         *  
         *  @param key
         *  @return contains
         */
        int index = hash(key);
        boolean contains = false;

        for(int i = 0; i < hash.get(index).size(); i++){
            if(hash.get(index).get(i).equals(key)){
                contains = true;
            }
            else{
                contains = false;
            }
        }

        return contains;
    }
}
