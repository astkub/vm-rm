
import java.util.Arrays;

public class Memory{
    private int size; //words
    private Word[] memory; // word = 4 bytes
    
    public Memory(int size){
        if(size >= 0){
           this.size = size;
           memory = new Word[size];
           for(int i = 0; i < size; i++){
               memory[i] = new Word();
           }
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public Word read(int address){
        return memory[address].clone();
    }

    public void write(Word word, int address){
        memory[address] = word.clone();
    }

    public int getSize() {
        return size;
    }
    
    public Word[] viewData(){
        return Arrays.copyOf(memory, size);
    }
}