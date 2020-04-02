import java.util.Arrays;

public class RealMemory{
    private int size; //words
    private Word[] memory; // word = 4 bytes

    private Memory supervisorMemory;
    private Memory userMemory;
    private Memory externalMemory;

    private final int BLOCKSIZE = 16;
    private int supervisorMemorySize = BLOCKSIZE * 10; // NOTE: idk what size this is
    private int userMemorySize = BLOCKSIZE * 80;
    private int externalMemorySize = BLOCKSIZE * 50;
    
    public RealMemory(int supervisorSize, int userSize, int externalSize){
        this.supervisorMemorySize = supervisorSize;
        this.userMemorySize = userSize;
        this.externalMemorySize = externalSize;

        supervisorMemory = new Memory(supervisorMemorySize);
        userMemory = new Memory(userMemorySize);
        externalMemory = new Memory(externalMemorySize);
    }

    public Memory getUserMemory(){
        return userMemory;
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