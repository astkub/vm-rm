import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CPU {
    private HashMap<Integer, String> commandsHashMap = new HashMap<Integer, String>();
    private Memory memory;

    // Parameters:
    private int x = -1, x1 = -1, x2 = -1;
    private int parameterLength = 1;

    private final int SUPERVISOR = 0;
    private final int USER = 1;

    // TODO: tikriausiai reikes perkelt i kita klase
    private final int START = 99;
    private final int END = 100;

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

    private int BA;
    private int BB;
    private int BC;
    private int IC;     // komandu skaitliukas // TODO
    private int SF;     // loginis registras priklauso nuo CMP // TODO
    private int S;      // TODO
    private int MODE;   // TODO supervisor
    private int PTR;    // TODO
    private int SPTR;   // TODO

    private int TI = 50;     // timerio pertraukimo registras // TODO
    private int SI;     // supervizoriniu pertraukimu registras
                        // 1 - komanda GD 
                        // 2 - komanda PR //TODO
                        // 3 - komanda HALT // TODO
                        // 4 - komanda LOC // TODO
                        // 5 - komanda UNL //TODO
    private int PI;     // programiniu pertraukimu registras
                        // 1 - neteisingas adresas //TODO
                        // 2 - neteisingas operacijos kodas
                        // 3 - neteisingas priskyrimas //TODO
                        // 4 - perpildymas (overflow) //TODO

    private int CH1;    // 
    private int CH2;    // 
    private int CH3;    // 
                        // CH: 0 - ivyko klaida, 1 - kanalas atliko darba
    //private static final int TIME = 20;

    public CPU() {
        BA = 0;
        BB = 0;
        BC = 0;
        
        CH1 = 1;
        CH2 = 1;
        CH3 = 1;//TODO add more

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

        commandsHashMap.put(START, "$STR");
        commandsHashMap.put(END, "$END");
    }

    // finds command and returns commandHashMap key
    public int findCommand(String unknownCommand){
        //System.out.println("unknownCommand: " + unknownCommand);
        if(MODE == USER)
        {
            int commandKey = -1;
            for(Map.Entry command : commandsHashMap.entrySet()){
                if(unknownCommand.startsWith((String) command.getValue())){
                    //System.out.println("Command found: Key: " + command.getKey() + " & Value: " + command.getValue());
                    commandKey = (int) command.getKey();
                }
            }
            return commandKey;
        }
        PI = 2;
        return -1;
    }

    public int parseCommand(String command, int commandKey){
        if(MODE == USER)
        switch (commandKey) {
            case ADD0:
                format0(command, commandKey);
                return 0;
            case SUB0:
                format0(command, commandKey);
                return 0;
            case MUL0:
                format0(command, commandKey);
                return 0;
            case DIV0:
                format0(command, commandKey);
                return 0;
            case GAx1x2:
                format2(command);
                return 2;
            case GBx1x2:
                format2(command);
                return 2;
            case SAx1x2:
                format2(command);
                return 2;
            case SBx1x2:
                format2(command);
                return 2;
            case PRx1x2:
                format2(command);
                return 2;
            case GDx1x2:
                format2(command);
                return 2;
            case DBAx:
                format1(command);
                return 1;
            case UBAx:
                format1(command);
                return 1;
            case LOCx:
                format1(command);
                return 1;
            case UNLx:
                format1(command);
                return 1;
            case CMP:
                format0(command, commandKey);
                return 0;
            case HALT:
                format0(command, commandKey);
                return 0;
            case JMx1x2:
                format2(command);
                return 2;
            case 99:
                System.out.println("START");
                return 0;
            case 100:
                System.out.println("END");
                return 0;
            default:
                System.out.println("Command not found. Command: " + command + " Key: " + commandKey);
                break;
        }
        return -1;
    }
    public int getParameterCount(int commandKey){
        if(MODE == USER)
        switch (commandKey) {
            case ADD0:
                return 0;
            case SUB0:
                return 0;
            case MUL0:
                return 0;
            case DIV0:
                return 0;
            case GAx1x2:
                return 2;
            case GBx1x2:
                return 2;
            case SAx1x2:
                return 2;
            case SBx1x2:
                return 2;
            case PRx1x2:
                return 2;
            case GDx1x2:
                return 2;
            case DBAx:
                return 1;
            case UBAx:
                return 1;
            case LOCx:
                return 1;
            case UNLx:
                return 1;
            case CMP:
                return 0;
            case HALT:
                return 0;
            case JMx1x2:
                return 2;
            case 99:
                return 0;
            case 100:
                return 0;
            default:
                System.out.println("Command not found. Key: " + commandKey);
                break;
        }
        return -1;
    }

    public void format0(String command,  int commandKey){ // no parameters, e.g. CMP, HALT or ADD0
        int commandLength = (commandKey == CMP) ? 3 : 4;
        if(command.length() != commandLength)
            System.out.println("Incorrect command: " + command);
    }
    public void format1(String command){ // 1 parameter, e.g. DBAx or LOCx
        int commandLength = 3;
        String tmp = command.substring(commandLength).trim();

        if(tmp.length() != parameterLength)
            System.out.println("Incorrect command: " + command);

        x = Integer.parseInt(tmp);
    }
    public void format2(String command){ // 2 parameters, e.g. GAx1x2 or JMx1x2
        int commandLength = 2;
        String tmp1 = command.substring(commandLength, commandLength + parameterLength).trim();
        String tmp2 = command.substring(commandLength + parameterLength).trim();

        if(tmp1.length() != parameterLength || tmp2.length() != parameterLength)
            System.out.println("Incorrect command: " + command);

        x1 = Integer.parseInt(tmp1);
        x2 = Integer.parseInt(tmp2);
    }
 
    public void callCommand(int key, VirtualMachine vm, boolean debug){
        if(MODE == USER)
        switch (key) {
            case ADD0:
                add0(vm);
                TI--;
                break;
            case SUB0:
                sub0(vm);
                TI--;
                break;
            case MUL0:
                mul0(vm);
                TI--;
                break;
            case DIV0:
                div0(vm);
                TI--;
                break;
            case GAx1x2:
                ga(vm, x1, x2);
                TI--;
                break;
            case GBx1x2:
                gb(vm, x1, x2);
                TI--;
                break;
            case SAx1x2:
                sa(vm, x1, x2);
                TI--;
                break;
            case SBx1x2:
                sb(vm, x1, x2);
                TI--;
                break;
            case PRx1x2:
                pr(vm, x1, x2);
                SI = 2;
                TI-=3;
                break;
            case GDx1x2:
                gd(vm, x1, x2);
                SI = 1;
                TI-=3;
                break;
            case DBAx:
                dba(x);
                TI--;
                break;
            case UBAx:
                uba(x);
                TI--;
                break;
            case LOCx:
                loc(x);
                SI = 4;
                TI--;
                break;
            case UNLx:
                unl(x);
                SI = 5;
                TI--;
                break;
            case CMP:
                cmp(vm);
                TI--;
                break;
            case HALT:
                halt();
                SI = 3;
                TI--;
                break;
            case JMx1x2:
                jm(x1, x2);
                TI--;
                break;
            case 99: //START
                IC++;
                break;
            case 100: //END
                break;
            default:
                System.out.println("Command not found. Key: " + key);
                break;
        }
        if(debug)
            printRegisters();
    }

    public void add0(VirtualMachine vm){
        System.out.println("add0(VirtualMachine vm)");
        IC++;
        vm.setBA( vm.getBA() + vm.getBB() );
    }
    public void sub0(VirtualMachine vm){
        System.out.println("sub0(VirtualMachine vm)");
        IC++;
        vm.setBA( vm.getBA() - vm.getBB() );
    }
    public void mul0(VirtualMachine vm){
        System.out.println("mul0(VirtualMachine vm)");
        IC++;
        vm.setBA( vm.getBA() * vm.getBB() );
    }
    public void div0(VirtualMachine vm){
        System.out.println("div0(VirtualMachine vm)");
        IC++;
        vm.setBA( vm.getBA() * vm.getBB() );
    }

    public void ga(VirtualMachine vm, int x1, int x2){
        System.out.println("ga(VirtualMachine vm, int x1 = " + x1 + ", int x2 = " + x2 + ")");
        IC++;
        Word word = memory.readFromMemory(x1, x2, USER);
        //Word word = vm.readFromMemory(x1, x2);
        vm.setBA(word.wordToInt(word));
    }
    public void gb(VirtualMachine vm, int x1, int x2){
        System.out.println("gb(VirtualMachine vm, int x1 = " + x1 + ", int x2 = " + x2 + ")");
        IC++;
        Word word = memory.readFromMemory(x1, x2, USER);
        //Word word = vm.readFromMemory(x1, x2);
        vm.setBB(word.wordToInt(word));
    }
    public void sa(VirtualMachine vm, int x1, int x2){
        System.out.println("sa(VirtualMachine vm, int x1 = " + x1 + ", int x2 = " + x2 + ")");
        IC++;
        int wrd = vm.getBA();
        Word word = (new Word()).intToWord(wrd);
        memory.writeToMemory(word, x1, x2, USER);
        
    }
    public void sb(VirtualMachine vm, int x1, int x2){
        System.out.println("sb(VirtualMachine vm, int x1 = " + x1 + ", int x2 = " + x2 + ")");
        IC++;
        int wrd = vm.getBB();
        Word word = (new Word()).intToWord(wrd);
        memory.writeToMemory(word, x1, x2, USER);
    }
    public void pr(VirtualMachine vm, int x1, int x2){
        System.out.println("pr(VirtualMachine vm, int x1 = " + x1 + ", int x2 = " + x2 + ")");
        IC++;
        System.out.println(new Word().wordToInt(memory.readFromMemory(x1, x2, USER)));
        // TODO: SI = 2;
        // TODO: CH2 = 0;
        // bet turi but klaidu apdorojimas, kokia klaida gali but su println()?
    }
    public void gd(VirtualMachine vm, int x1, int x2){
        System.out.println("gd(VirtualMachine vm, int x1 = " + x1 + ", int x2 = " + x2 + ")");
        IC++;
        System.out.print("Input: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            String inputString = reader.readLine(); // pakeiciu is 16 zodziu i 8 zodzius, 1 zodis butu du simboliai, dabar max gali buti 16 simboliu
            int inputLength = inputString.length();
            if(inputLength >= 16)
                inputString.substring(0, 16).trim(); // nukertam gala, jei daugiau nei 16 simboliu

            int inputInt = Integer.parseInt(inputString);
            Word inputWord = new Word().intToWord(inputInt);

            memory.writeToMemory(inputWord, x1, x2, USER);
        } catch (IOException e) {
            SI = 1;
            CH1 = 0;
            System.out.println("BufferedReader exception.");
            e.printStackTrace();
        }
    }

    public void dba(int x){
        System.out.println("dba(int x = " + x + ")");
        IC++;
        // TODO
    } 
    public void uba(int x){
        System.out.println("uba(int x = " + x + ")");
        IC++;
        // TODO
    }
    public void loc(int x){
        System.out.println("loc(int x = " + x + ")");
        IC++;
        // TODO
    }
    public void unl(int x){
        System.out.println("unl(int x = " + x + ")");
        IC++;
        // TODO
    }

    public void cmp(VirtualMachine vm){
        System.out.println("cmp(int x = " + x + ")");
        IC++;
        if(vm.getBA() == vm.getBB())
            vm.setSF(0);
        else if(vm.getBA() > vm.getBB())
            vm.setSF(1);
        else if(vm.getBA() < vm.getBB())
            vm.setSF(2);
    }

    public void halt(){
        System.out.println("halt()");
        IC++;
        // TODO
    }
    public void jm(int x1, int x2){
        System.out.println("jm(int x1 = " + x1 + ", int x2 = " + x2 + ")");
        IC = 16 * x1 + x2;
    }

    public int getInterrupt(){
        switch (PI) {
            case 1: return 1;
            case 2: return 2;
            case 3: return 3;
            case 4: return 4;
        }
        switch (SI) {
            case 1: return 5;
            case 2: return 6;
            case 3: return 7;
            case 4: return 8;
            case 5: return 9;
        }
        //if(TI == 0) return 10;
        return 0;
    }
    public void processInterrupt(){
        /*
        1 - neteisingas adresas
        2 - neteisingas operacijos kodas
        3 - neteisingas priskyrimas
        4 - perpildymas (overflow)
        5 - komanda GD
        6 - komanda PR
        7 - komanda HALT
        8 - komanda LOC
        9 - komanda UNL
        10 - timerio pertraukimas
        */
        int interrupt = getInterrupt();
        while(interrupt != 0) {
            switch (interrupt) {
                case 1:
                    System.out.println("Out of bounds");
                    break;
                case 2:
                    System.out.println("Invalid command");
                    break;
                case 3:
                    System.out.println("neteisingas priskyrimas");
                    break;
                case 4:
                    System.out.println("Not enough disk space");
                    break;
                case 5:
                    System.out.println("komanda GD");
                    break;
                case 6:
                    System.out.println("komanda PR");
                    break;
                case 7:
                    System.out.println("komanda HALT");
                    break;
                case 8:
                    System.out.println("komanda LOC");
                    break;
                case 9:
                    System.out.println("komanda UNL");
                    break;
                case 10:
                    System.out.println("Not enough disk space");
                    break;
                default:
                    break;
            }
            getInterrupt();
        }

    }

    public void printRegisters(){
        System.out.println("BA:" + BA);
        System.out.println("BB:" + BB);
        System.out.println("BC:" + BC);
        System.out.println("IC:" + IC);
        System.out.println("SF:" + SF);
        System.out.println("S:" + S);
        System.out.println("MODE:" + MODE);
        System.out.println("PTR:" + PTR);
        System.out.println("SPTR:" + SPTR);
        System.out.println("TI:" + TI);
        System.out.println("SI:" + SI);
        System.out.println("PI:" + PI);
    }

    public void resetInterrupts(){
        PI = 0;
        SI = 0;
        //TI = ?
    }

    public String getCommandByKey(int key){
        return commandsHashMap.get(key);
    }

    public void clearParameters(){
        x = -1;
        x1 = -1;
        x2 = -1;
    }

    public void setX1(int x){this.x1 = x;}
    public void setX2(int x){this.x2 = x;}
    public void setX(int x){this.x = x;}

    public int getX1(){return x1;}
    public int getX2(){return x2;}
    public int getX(){return x;}

    public int getCH3() {
        return CH3;
    }

    public void setCH3(int cH3) {
        this.CH3 = cH3;
    }

    public int getCH2() {
        return CH2;
    }

    public void setCH2(int cH2) {
        this.CH2 = cH2;
    }

    public int getCH1() {
        return CH1;
    }

    public void setCH1(int cH1) {
        this.CH1 = cH1;
    }

    public int getPI() {
		return PI;
	}

	public void setPI(int pI) {
		this.PI = pI;
	}

	public int getSI() {
		return SI;
	}

	public void setSI(int sI) {
		this.SI = sI;
	}

	public int getTI() {
        return TI;
    }

    public void setTI(int tI) {
        this.TI = tI;
    }

    public int getSPTR() {
        return SPTR;
    }

    public void setSPTR(int sPTR) {
        this.SPTR = sPTR;
    }

    public int getPTR() {
        return PTR;
    }

    public void setPTR(int pTR) {
        this.PTR = pTR;
    }

    public int getMODE() {
        return MODE;
    }

    public void setMODE(int mODE) {
        this.MODE = mODE;
    }

    public int getS() {
		return S;
	}

	public void setS(int s) {
		this.S = s;
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

    public int getBC() {
        return BC;
    }

    public void setBC(int bC) {
        this.BC = bC;
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

    public void setMemory(Memory memory){
        this.memory = memory;
    }
}