import java.util.HashMap;
import java.util.Map;

public class CPU{

    private HashMap<Integer, String> commandsHashMap = new HashMap<Integer, String>();

    //TODO: tikriausiai reikes perkelt i kita klase
    private String START = "$STR";
    private String END = "$END";

    private final int ADD0 = 1;
    private final int SUB0 = 2;
    private final int MUL0 = 3;
    private final int DIV0 = 4;

    private final int GAx1x2 = 5;
    private final int GBx1x2 = 6;
    private final int SAx1x2 = 7;
    private final int SBx1x2 = 8;
    private final int PRx1x2 = 9;
    private final int GDx1x2 = 10;

    private final int DBAx = 11;
    private final int UBAx = 12;
    private final int LOCx = 13;
    private final int UNLx = 14;

    private final int CMP = 15;

    private final int HALT = 16;
    private final int JMx1x2 = 17;

    public CPU(){
        commandsHashMap.put(ADD0, "ADD0");
        commandsHashMap.put(SUB0, "SUB0");
        commandsHashMap.put(MUL0, "MUL0");
        commandsHashMap.put(DIV0, "DIV0");

        commandsHashMap.put(GAx1x2, "GA");
        commandsHashMap.put(GBx1x2, "GB");
        commandsHashMap.put(SAx1x2, "SA");
        commandsHashMap.put(SBx1x2, "SB");
        commandsHashMap.put(PRx1x2, "PR");
        commandsHashMap.put(GDx1x2, "GD");

        commandsHashMap.put(DBAx, "DBA");
        commandsHashMap.put(UBAx, "UBA");
        commandsHashMap.put(LOCx, "LOC");
        commandsHashMap.put(UNLx, "UNL");

        commandsHashMap.put(CMP, "CMP");

        commandsHashMap.put(HALT, "HALT");
        commandsHashMap.put(JMx1x2, "JM");
    }

    // finds command and returns commandHashMap key
    public int findCommand(String unknownCommand){
        System.out.println("unknownCommand: " + unknownCommand);
        int commandKey = -1;
        for(Map.Entry command : commandsHashMap.entrySet()){
            if(unknownCommand.startsWith((String) command.getValue())){
                System.out.println("Command found: Key: " + command.getKey() + " & Value: " + command.getValue());
                commandKey = (int) command.getKey();
            }
        }
        return commandKey;
    }

    public void callCommand(int key, VirtualMachine vm){
        switch (key) {
            case ADD0:
                add0(vm);
                break;
            case SUB0:
                sub0(vm);
                break;
            case MUL0:
                mul0(vm);
                break;
            case DIV0:
                div0(vm);
                break;
            case CMP:
                CMP(vm);
                break;
            default:
                break;
        }
    }

    public void callCommand(int key, VirtualMachine vm, int x1, int x2){
        switch (key) {
            case GAx1x2:
                ga(vm, x1, x2);
                break;
            case GBx1x2:
                gb(vm, x1, x2);
                break;
            case SAx1x2:
                sa(vm, x1, x2);
                break;
            case SBx1x2:
                sb(vm, x1, x2);
                break;
            case PRx1x2:
                pr(vm, x1, x2);
                break;
            case GDx1x2:
                gd(vm, x1, x2);
                break;
            default:
                break;
        }
    }

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

    public void dba(int x){} // TODO: add to callCommand
    public void uba(int x){} // TODO: add to callCommand
    public void loc(int x){} // TODO: add to callCommand
    public void unl(int x){} // TODO: add to callCommand

    public void CMP(VirtualMachine vm){
        if(vm.getBA() == vm.getBB())
            vm.setSF(0);
        else if(vm.getBA() > vm.getBB())
            vm.setSF(1);
        else if(vm.getBA() < vm.getBB())
            vm.setSF(2);
    }

    public void halt(){} // TODO: add to callCommand
    public void jm(int x1, int x2){} // TODO: add to callCommand

    public String getCommandByKey(int key){
        return commandsHashMap.get(key);
    }

}