import java.util.Arrays;

public class RM {
    private CPU cpu;
    private RealMemory memory;

    //private RealMemory realMemory;
    //public final static int SWAP_SIZE = 2; // blocks //not sure what this is

    private final int BLOCKSIZE = 16;
    private final int supervisorMemorySize = BLOCKSIZE * 10; // NOTE: idk what size this is
    private final int userMemorySize = BLOCKSIZE * 80;
    private final int externalMemorySize = BLOCKSIZE * 50;
    

    public RM() {
        cpu = new CPU();
        memory = new Memory(supervisorMemorySize, userMemorySize, externalMemorySize);
    }

    public void loadProgram(String filename){
        VirtualMachine virtualMachine = new VirtualMachine(new RealMemory(mem))
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        while(fileReader.ready()){
            String currentLine = fileReader.readLine();
            if(currentLine.isEmpty()){
                continue;
            }
            System.out.println(currentLine);
            // TODO: komandu atpazinimas ir vykdymas
            virtualMachine.excecuteCommand(currentLine);
        }
        fileReader.close();
    }
    /*
     * private int newVM() throws MemoryException { int lastMode = cpu.getMODE();
     * cpu.setMODE(CPU.SUPERVISOR); int i = 0; while
     * (Word.wordToInt(mmu.read(vmTableAddress + i*4)) == 1) { i++; } int ptr =
     * i*PAGE_TABLE_SIZE + pageTableAddress; updateVM(i, new VirtualMachine(1, 0, 0,
     * ptr)); mmu.write(Word.intToWord(Word.wordToInt(mmu.read(vmCountAddress))+1),
     * vmCountAddress); cpu.setMODE(lastMode); return i; }
     */

    public Word[] viewRealMemory() {
        return realMemory.viewData();
    }

    public Word[] viewExternalMemory() {
        return externalMemory.viewData();
    }

    public CPU getCPU() {
        return cpu;
    }

    public int getSupervisorSize() {
        return supervisorSize;
    }

}