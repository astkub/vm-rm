

public class VirtualMachine {
    private CPU cpu;
    //private Memory memory;
    private Memory memory;
    private int ID;

    // TODO
    public VirtualMachine(Memory memory, CPU cpu, int ID) {
        this.memory = memory;
        this.cpu = cpu;
        memory.fillTable(ID);
    }

    public void excecuteCommand(String command){
        //System.out.println(command);
        int commandKey = cpu.findCommand(command);
        cpu.parseCommand(command, commandKey);
        cpu.callCommand(commandKey, this);
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