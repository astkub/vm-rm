public class VirtualMachine {
    private CPU cpu = new CPU();
    private RealMemory memory;
    private int memorySize = 100;
    //private int BA;
    //private int BB;
    //private int IC;
    //private int SF;

    // TODO
    public VirtualMachine() {
        this.memory = new RealMemory(memorySize);
    }

    public void excecuteCommand(String command){
        //System.out.println(command);
        cpu.clearParameters();
        int commandKey = cpu.findCommand(command);
        cpu.parseCommand(command, commandKey);
        cpu.callCommand(commandKey, this);
    }

    // TODO: patikrint ar gerai veikia
    public void writeToMemory(Word word, int address){
        memory.write(word, address);
    }

    // TODO: patikrint ar gerai veikia
    public Word readFromMemory(int address){
        return memory.read(address);
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