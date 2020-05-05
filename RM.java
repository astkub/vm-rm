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
            cpu.setIC(0);
            while(fileReader.ready()){
                cpu.setMODE(USER);
                String currentLine = fileReader.readLine();
                if(currentLine.isEmpty()){
                    continue;
                }
                //System.out.println(currentLine);
                int parameters = virtualMachine.saveComand(currentLine, temp);
                temp += parameters;
                //processInterrupt();
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("BufferedReader exception.");
            e.printStackTrace();
        }
        //printVMMemory();
        virtualMachine.excecuteCommand();
        cpu.setMODE(SUPERVISOR);
        //printVMMemory();
    }
   
    public void debugProgram(String fileName){
        VirtualMachine virtualMachine = new VirtualMachine(memory, cpu, (workingVMs));
        workingVMs++;

        try{
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            int temp = 0;
            cpu.setIC(0);
            while(fileReader.ready()){
                cpu.setMODE(USER);
                String currentLine = fileReader.readLine();
                if(currentLine.isEmpty()){
                    continue;
                }
                //System.out.println(currentLine);
                int parameters = virtualMachine.saveComand(currentLine, temp);
                temp += parameters;
                //processInterrupt();
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("BufferedReader exception.");
            e.printStackTrace();
        }
        //printVMMemory();
        virtualMachine.excecuteDebugCommand();
        cpu.setMODE(SUPERVISOR);
        System.out.println("Mode: " + cpu.getMODE());
        //printVMMemory();

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