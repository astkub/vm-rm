import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class RM {
    private CPU cpu;

    private final int SUPERVISOR = 0;
    private final int USER = 1;
    private Memory memory;
    private VirtualMachine VMs[];
    private int workingVMs;

    // VM skiriama 16 bloku. Vartotojo atmintis:
    // [(VM 1)(VM 2)(VM 3)(VM 4)(puslapiu lentele 4 blokai) ]
    // RM skiriama 50 blokų po 16 zodziu 


    public RM() {
        cpu = new CPU();
        int ptr = 4 * 16;
        cpu.setPTR(ptr);
        cpu.setMODE(SUPERVISOR);
        memory = new Memory(cpu);
        cpu.setMemory(memory);
        workingVMs = 0;

    }

    private int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

    public void loadProgram(String fileName){
        VirtualMachine virtualMachine = new VirtualMachine(memory, cpu, (workingVMs));
        workingVMs++;

        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            int temp = 0;
            while(fileReader.ready()){
                cpu.setMODE(USER);
                String currentLine = fileReader.readLine();
                if(currentLine.isEmpty()){
                    continue;
                }
                //System.out.println(currentLine);
                virtualMachine.saveComand(currentLine, temp);
                temp++;
                processInterrupt();
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("BufferedReader exception.");
            e.printStackTrace();
        }
        printVMMemory();
        virtualMachine.excecuteCommand();
        cpu.setMODE(SUPERVISOR);
        printVMMemory();

    }

    public void processInterrupt(){
        /*
        1 - neteisingas adresas
        2 - neteisingas operacijos kodas
        3 - neteisingas priskyrimas
        4 - perpildymas (overflow)
        5 - komanda GD
        6 - komanda PR
        7 - komanda HALT
        8 - komanda LOC
        9 - komanda UNL
        10 - timerio pertraukimas
        */
        int interrupt = cpu.getInterrupt();
        while(interrupt != 0) {
            switch (interrupt) {
                case 1:
                    System.out.println("Out of bounds");
                    break;
                case 2:
                    System.out.println("Invalid command");
                    break;
                case 3:
                    
                    break;
                case 4:
                    System.out.println("Not enough disk space");
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                default:
                    break;
            }
            interrupt = cpu.getInterrupt();
        }

    }

    public CPU getCPU() {
        return cpu;
    }

    public void printMemory() {
        memory.printMemory();
    }

    public void printMemoryTable() {
        memory.printMemoryTable();
    }

    public void printVMMemory() {
        memory.printVMMemory(0);
    }
 
}