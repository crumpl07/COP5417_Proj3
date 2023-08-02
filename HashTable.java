public class HashTable {
    public HashTable hash[];

    public HashTable(int size){
        hash = new HashTable[size];
    }

    public int hash(String key){
        return Math.abs(key.hashCode() % 2000);
    }

    public void put(String key){
        int index = hash(key);
        if(hash[index] != ""){

        }
    }

    public boolean contains(){
        return true;
    }
}
