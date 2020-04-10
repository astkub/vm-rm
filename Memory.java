
import java.util.Arrays;

public class Memory{
    
    private Word memory[];

    private final int BLOCKSIZE = 16; // words
    private final int userMemorySize = BLOCKSIZE * 68;
    private final int externalMemorySize = BLOCKSIZE * 50;
    private CPU cpu;

    private final int SUPERVISOR = 0;
    private final int USER = 1;
    
    public Memory(CPU cpu){
        this.cpu = cpu;
        memory = new Word[userMemorySize + externalMemorySize];
        for (int i = 0; i < userMemorySize + externalMemorySize; i++){
            memory[i] = new Word();
        }
    }

    public void createTable(){
        int ptr = cpu.getPTR();
        for (int i = ptr; i < ptr + 4*BLOCKSIZE; i++){
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

    public void writeToMemory(Word word, int x, int y, int mode){  //naudojant memorytable ir skaitant kad yra tik 1 VM
        if (mode == USER){
            int ptr = cpu.getPTR();
            int rmblock = new Word().wordToInt(memory[ptr + x]);   //is memorytable nustatomas atitinkamas blokas
            memory[rmblock + y] = word; 
        }
        else if (mode == SUPERVISOR){
            int rmblock = new Word().wordToInt(memory[userMemorySize + x]);
            memory[rmblock + y] = word; 
        }
        else{
            System.out.println("Incorrect mode id.");
        }

    }

    public Word readFromMemory(int x, int y, int mode){  //naudojant memorytable
        if (mode == USER){
            int ptr = cpu.getPTR();
            int rmblock = new Word().wordToInt(memory[ptr + x]); 
            return memory[rmblock + y]; 
        }
        else if (mode == SUPERVISOR){
            int rmblock = new Word().wordToInt(memory[userMemorySize + x]);
            return memory[rmblock + y];
        }
        else{
            System.out.println("Incorrect mode id.");
            return new Word().intToWord(0);
        }

    }

    /*

    // TODO: patikrint ar gerai veikia  //naudojant formule
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

    // TODO: patikrint ar gerai veikia    //naudojant formule
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