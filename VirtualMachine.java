public class VirtualMachine {
    private CPU cpu = new CPU();
    private RealMemory memory;
    private int memorySize = 100;
    private int BA;
    private int BB;
    private int IC;
    private int SF;

    // TODO
    public VirtualMachine() {
        this.memory = new RealMemory(memorySize);
    }

    public void excecuteCommand(String command){
        //System.out.println(command);
        int commandKey = cpu.findCommand(command);
    }

    //TODO: patikrint ar gerai veikia
    public void writeToMemory(Word word, int address){
        memory.write(word, address);
    }

    //TODO: patikrint ar gerai veikia
    public Word readFromMemory(int address){
        return memory.read(address);
    }

    public int getSF() {
        return SF;
    }

    public void setSF(int sF) {
        this.SF = sF;
    }

    public int getIC() {
        return IC;
    }

    public void setIC(int iC) {
        this.IC = iC;
    }

    public int getBB() {
        return BB;
    }

    public void setBB(int bB) {
        this.BB = bB;
    }

    public int getBA() {
        return BA;
    }

    public void setBA(int bA) {
        this.BA = bA;
    }
}