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

    // TODO: patikrint ar gerai veikia
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

    // TODO: patikrint ar gerai veikia
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