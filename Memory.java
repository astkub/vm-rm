
import java.util.Arrays;

public class Memory{
    
    private Word memory[];

    private final int BLOCKSIZE = 16; // words
    private final int userMemorySize = BLOCKSIZE * 68;
    private final int externalMemorySize = BLOCKSIZE * 50;
    private CPU cpu;
    
    public Memory(CPU cpu){
        this.cpu = cpu;
        memory = new Word[userMemorySize + externalMemorySize];
        for (int i = 0; i < userMemorySize + externalMemorySize; i++){
            memory[i] = new Word();
        }
    }

    public void createTable(){
        int ptr = cpu.getPTR();
        for (int i = ptr; i < ptr + BLOCKSIZE; i++){
            memory[i] = new Word().intToWord(i);//getRandomNumberInRange(0, ptr-1));
            //System.out.println("memory[" + i + "] = " + new Word().wordToInt(memory[i]));
        }
    }

    public void printUserMemory() {
        int temp = 0;
        for (int i = 0; i < userMemorySize; i++){
            if (temp == 16)
                System.out.println();
            System.out.println("memory[" + i + "] = " + memory[i]);
        }       
    }

    public void printExternalMemory() {
        int temp = 0;
        for (int i = userMemorySize; i < externalMemorySize; i++){
            if (temp == 16)
                System.out.println();
            System.out.println("memory[" + i + "] = " + memory[i]);
        }
        
    }

    public void printMemoryTable() {
        int ptr = cpu.getPTR();
        int temp = 0;
        for (int i = ptr; i < ptr + 4 * BLOCKSIZE ; i++){
            if (temp == 16)
                System.out.println();
            System.out.println("memory[" + i + "] = " + memory[i]);
        }
        
    }

    public void printMemory(){
        for (int i = 0; i < userMemorySize + externalMemorySize; i++){
            System.out.println("memory[" + i + "] = " + memory[i]);
        }
    }
 
    public Word[] returnMemory(){
        return memory;
    }

    public void writeToMemory(Word word, int x, int y){

    }
    /*

    // TODO: patikrint ar gerai veikia
    public void writeToMemory(Word word, int x, int y){
        //memory.write(word, address);
        int ptr = cpu.getPTR();
        int a0 = ptr / 16;
        int a1 = ptr % 16;
        int tmp = new Word().wordToInt(memory[16 * (16 * a0 + a1) + x]);//System.out.println(tmp);
        tmp = new Word().wordToInt(memory[tmp]);//System.out.println(tmp);
        memory[10 * tmp + y] = word;
        System.out.println("Writing memory[" + 10 * tmp + y + "] = " + new Word().wordToInt(memory[10 * tmp + y]));
    }

    // TODO: patikrint ar gerai veikia
    public Word readFromMemory(int x, int y){
        //return memory.read(address);
        int ptr = cpu.getPTR();
        int a0 = ptr / 16;
        int a1 = ptr % 16;
        int tmp = new Word().wordToInt(memory[16 * (16 * a0 + a1) + x]);//System.out.println(tmp);
        tmp = new Word().wordToInt(memory[tmp]);//System.out.println(tmp);
        System.out.println("Reading memory[" + 10 * tmp + y + "] = " + new Word().wordToInt(memory[10 * tmp + y]));
        return memory[10 * tmp + y];
    }

    */


}