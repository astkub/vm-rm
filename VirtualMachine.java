public class VirtualMachine {
    private CPU cpu;
    //private Memory memory;
    private Word memory[];


    // TODO
    public VirtualMachine(Word memory[], CPU cpu) {
        this.memory = memory;
        this.cpu = cpu;
        cpu.clearParameters();
    }

    public Word[] excecuteCommand(String command){
        //System.out.println(command);
        int commandKey = cpu.findCommand(command);
        cpu.parseCommand(command, commandKey);
        cpu.callCommand(commandKey, this);
        return memory;
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