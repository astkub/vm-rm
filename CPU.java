

public class CPU{
    //TODO: tikriausiai reikes perkelt i kita klase
    private String START = "$STR";
    private String END = "$END";

    private String ADD0 = "ADD0";
    private String SUB0 = "SUB0";
    private String MUL0 = "MUL0";
    private String DIV0 = "DIV0";

    private String GAx1x2 = "GA";
    private String GBx1x2 = "GB";
    private String SAx1x2 = "SA";
    private String SBx1x2 = "SB";
    private String PRx1x2 = "PR";
    private String GDx1x2 = "GD";

    private String DBAx = "DBA";
    private String UBAx = "UBA";
    private String LOCx = "LOC";
    private String UNLx = "UNL";

    private String CMP = "CMP";

    private String HALT = "HALT";
    private String JMx1x2 = "JM";

    public void add0(VirtualMachine vm){
        vm.setBA( vm.getBA() + vm.getBB() );
    }
    public void sub0(VirtualMachine vm){
        vm.setBA( vm.getBA() - vm.getBB() );
    }
    public void mul0(VirtualMachine vm){
        vm.setBA( vm.getBA() * vm.getBB() );
    }
    public void div0(VirtualMachine vm){
        vm.setBA( vm.getBA() * vm.getBB() );
    }

    public void ga(VirtualMachine vm, int x1, int x2){
        Word word = vm.readFromMemory(16 * x1 + x2);
        vm.setBA(word.wordToInt(word)); // TODO: patikrint ar gerai konvertina ir ar tikrai ten reikia static?
    }
    public void gb(VirtualMachine vm, int x1, int x2){
        Word word = vm.readFromMemory(16 * x1 + x2);
        vm.setBB(word.wordToInt(word)); // TODO: patikrint ar gerai konvertina ir ar tikrai ten reikia static?
    }
    public void sa(VirtualMachine vm, int x1, int x2){
        int wrd = vm.getBA();
        Word word = (new Word()).intToWord(wrd); // TODO: patikrint ar gerai konvertina ir ar tikrai ten reikia static?
        vm.writeToMemory(word, 16 * x1 + x2);
    }
    public void sb(VirtualMachine vm, int x1, int x2){
        int wrd = vm.getBB();
        Word word = (new Word()).intToWord(wrd); // TODO: patikrint ar gerai konvertina ir ar tikrai ten reikia static?
        vm.writeToMemory(word, 16 * x1 + x2);
    }
    public void pr(VirtualMachine vm, int x1, int x2){}
    public void gd(VirtualMachine vm, int x1, int x2){}

    public void dba(){

    }
    public void uba(int x){}
    public void loc(int x){}
    public void unl(int x){}

    public void CMP(VirtualMachine vm){
        if(vm.getBA() == vm.getBB())
            vm.setSF(0);
        else if(vm.getBA() > vm.getBB())
            vm.setSF(1);
        else if(vm.getBA() < vm.getBB())
            vm.setSF(2);
    }

    public void halt(){}
    public void jm(int x1, int x2){}


}