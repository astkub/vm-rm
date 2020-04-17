
import java.util.Arrays;

import java.util.Random;


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
        int ptr = 4 * 16;
        cpu.setPTR(ptr);
        int sptr = userMemorySize;
        cpu.setSPTR(sptr);
        memory = new Word[userMemorySize + externalMemorySize];
        for (int i = 0; i < userMemorySize + externalMemorySize; i++){
            memory[i] = new Word();
        }
        fillTable();
    }

    /*
    public void createTable(){
        int ptr = cpu.getPTR();
        memory[ptr] = new Word().intToWord(0);
        for (int i = ptr + 1; i < ptr + 1*BLOCKSIZE; i++){
            int temp = getRandomNumberInRange(1, ptr-1);
            boolean write = false;
            for(int k = ptr; k < userMemorySize; k++)
                    if (temp == Word.wordToInt(memory[k])){
                        write = false;
                        break;
                    }
                    else
                        write = true;
                if (write){
                    memory[i] = Word.intToWord(temp);
                    break;
                }
        }
    }

    private int getRandomNumberInRange(int min, int max) {

		if (min < max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
    }
    */

    public void fillTable(){
        int ptr = cpu.getPTR();
        boolean write = false;

        int first = userMemorySize - ptr;
        memory[first] = Word.intToWord(13);
        memory[first+1] = Word.intToWord(1);
        memory[first+2] = Word.intToWord(20);
        memory[first+3] = Word.intToWord(16);
        memory[first+4] = Word.intToWord(0);
        memory[first+5] = Word.intToWord(9);
        memory[first+6] = Word.intToWord(40);
        memory[first+7] = Word.intToWord(39);
        memory[first+8] = Word.intToWord(26);
        memory[first+9] = Word.intToWord(5);
        memory[first+10] = Word.intToWord(8);
        memory[first+11] = Word.intToWord(14);
        memory[first+12] = Word.intToWord(45);
        memory[first+13] = Word.intToWord(32);
        memory[first+14] = Word.intToWord(17);
        memory[first+15] = Word.intToWord(63);

        /*
        for (int i = userMemorySize - ptr + 1; i < userMemorySize - ptr + 1 * BLOCKSIZE; i++){
            for (int j = 1; j < ptr; j++)
            {
                for(int k = userMemorySize - ptr; k < userMemorySize - ptr + 4 * BLOCKSIZE; k++){
                    if (j == Word.wordToInt(memory[k])){
                        write = false;
                        break;
                    }
                    else{
                        write = true;
                    }
                }
                if (write){
                    memory[i] = Word.intToWord(j);
                    break;
                }
            }
        }
        */
    }

    //kolkas ID = 0
    public void printVMMemory(int ID){
        int ptr = cpu.getPTR();
        int temp = 0;
        for (int i = userMemorySize - ptr + ID * BLOCKSIZE; i < userMemorySize - ptr + (ID + 1) * BLOCKSIZE ; i++){
            int rmblock = new Word().wordToInt(memory[i]); 
            for (int j = 0; j < 16; j++){
               
                  
                System.out.print(Word.wordToInt(readFromMemory(rmblock, j, USER)) + " ");
            }
            System.out.println();
        }
    }

    public void printUserMemory() {
        int temp = 0;
        for (int i = 0; i < userMemorySize; i++){
            if (temp == 16){
                System.out.println("\n");
                temp = 0;
            }
            temp++;
            System.out.println(Word.wordToInt(memory[i]));
        }       
    }

    public void printExternalMemory() {
        int temp = 0;
        for (int i = userMemorySize; i < externalMemorySize; i++){
            if (temp == 16){
                System.out.println("\n");
                temp = 0;
            }
            temp++;
            System.out.println(Word.wordToInt(memory[i]) + " ");
        }
        
    }

    public void printMemoryTable() {
        int ptr = cpu.getPTR();
        int temp = 0;
        for (int i = userMemorySize - ptr; i < userMemorySize - ptr + 4 * BLOCKSIZE ; i++){
            if (temp == 16){
                System.out.println("\n");
                temp = 0;
            }
            temp++;
            System.out.print(Word.wordToInt(memory[i]) + " ");
        }
        
    }

    public void printMemory(){
        int temp = 0;
        int block = 0;
        System.out.print(block + ":   ");
        block++;
        for (int i = 0; i < userMemorySize + externalMemorySize; i++){
            if (temp == 16){
                System.out.println();
                System.out.print(block + ":   ");
                block++;
                temp = 0;
            }
            temp++;
            System.out.print(Word.wordToInt(memory[i]) + " ");
        }
    }
 
    public Word[] returnMemory(){
        return memory;
    }

    public void writeToMemory(Word word, int x, int y, int mode){  //naudojant memorytable ir skaitant kad yra tik 1 VM
        if (mode == USER){
            int ptr = cpu.getPTR();
            int rmblock = new Word().wordToInt(memory[userMemorySize - ptr + x]);   //is memorytable nustatomas atitinkamas blokas
            memory[rmblock*16 + y] = word; 
        }
        else if (mode == SUPERVISOR){
            int rmblock = userMemorySize + x*16;
            memory[rmblock + y] = word; 
        }
        else{
            System.out.println("Incorrect mode id.");
        }

    }

    public Word readFromMemory(int x, int y, int mode){  //naudojant memorytable
        if (mode == USER){
            int ptr = cpu.getPTR();
            int rmblock = new Word().wordToInt(memory[userMemorySize - ptr + x]); 
            return memory[rmblock*16 + y]; 
        }
        else if (mode == SUPERVISOR){
            int rmblock = userMemorySize + x*16;
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