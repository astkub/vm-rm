

public class VirtualMachine {
    private CPU cpu;
    //private Memory memory;
    private Memory memory;
    private int ID;

    private final int SUPERVISOR = 0;
    private final int USER = 1;

    public VirtualMachine(Memory memory, CPU cpu, int ID) {
        this.memory = memory;
        this.cpu = cpu;
        this.ID = ID;
        //memory.fillTable();
    }

    //TODO getEND
    public void excecuteCommand(){  
        System.out.println("Executing");
        cpu.setMODE(USER);
        Word command = memory.readFromMemory(0, 0, USER);
        int temp = 0;
        int temp1 = 0;
        int temp2 = 0;
        while(Word.wordToInt(command) != 100){
            System.out.println(Word.wordToInt(command));
            cpu.callCommand(Word.wordToInt(command), this);
            if (temp >= 16)
            {
                temp2 = temp / 16;
                temp1 = temp % 16;
            }
            else temp1 = temp;
            
            //System.out.println("Reading from: " + temp2 + ", " + temp1);
            command = memory.readFromMemory(temp1, temp2, USER);
            temp++;
        }
        cpu.callCommand(Word.wordToInt(command), this);
        cpu.setMODE(SUPERVISOR);
    }

    public void saveComand(String command, int temp1){
        int commandKey = cpu.findCommand(command);
        cpu.parseCommand(command, commandKey);
        int temp2 = 0;
        if (temp1 >= 16)
            {
                temp2 = temp1 / 16;
                temp1 = temp1 % 16;
            }
        memory.writeToMemory(new Word().intToWord(commandKey), temp2, temp1, USER);
        //System.out.println("Writing to: " + temp2 + ", " + temp1);
        cpu.setIC(cpu.getIC() + 1);
    }

    public int getSF() {
        return cpu.getSF();
    }

    public void setSF(int sF) {
        cpu.setSF(sF);
    }

    public int getIC() {
        return cpu.getIC();
    }

    public void setIC(int iC) {
        cpu.setIC(iC);
    }

    public int getBB() {
        return cpu.getBB();
    }

    public void setBB(int bB) {
        cpu.setBB(bB);
    }

    public int getBA() {
        return cpu.getBA();
    }

    public void setBA(int bA) {
        cpu.setBA(bA);
    }
}