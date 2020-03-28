public class VirtualMachine {
    private int BA;
    private int BB;
    private int IC;
    private int SF;

    public VirtualMachine() {

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