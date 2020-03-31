import java.util.Arrays;

public class RM {
    private CPU cpu;
    private RealMemory realMemory;
    private RealMemory externalMemory;

    public final static int SWAP_SIZE = 2; // blocks //not sure what this is

    private final int supervisorSize = 160; // words (10 blocks)
    private final int userMemory = 1280; // words (80 blocks)
    private final int BLOCKSIZE = 16;
    private final int outerMemory = 800; // words (50 blocks)

    public RM() {
        cpu = new CPU();
        realMemory = new RealMemory(BLOCKSIZE * (supervisorSize + userMemory));
        // mmu = new MMU(cpu, realMemory, this, BLOCKSIZE*SWAP_SIZE);
        // cpu.setMMU(mmu);
        externalMemory = new RealMemory(BLOCKSIZE * SWAP_SIZE);
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