
import java.util.ArrayList;

public class Processes{
    private List runningProcesses = new ArrayList();
    private int descriptorSpace = 4; //nezinau kiek kolkas
    private int[] descriptor = new int[descriptorSpace]; //dinaminis objektas //proceso aprasas //laikomi visi reikalingi parametrai kaip virtualios masinos registru reksmes ir kiti kintamieji

    public void createProcess(Processes parent, String state, int priority, List<String, int> elements, String name){
        //kuriamas procesas
        //procesas registruojamas bendrajame procesu sarase, tevo sunu sarase
        //skaiciuojamas vidinis identifikacijos numeris
        //sukuriamas vaiku procesu sarasas(tuscias), sukurtu resursu sarasas ir t.t
        planner();
    }

    public void killProcess(){
        //Pradedama naikinti proceso sukurtus resursus ir vaikus
        //išmetamas iš tevo sukurtu procesu saraso
        //ismetamas is bendro procesu saraso ir, jei reikia, is pasiruosusiu resursu saraso
        //naikinami visi jam perduoti resursai ir proceso descriptorius yra sunaikinamas
        planner();
    }

    public void stopProcess(){
        //Keiciama proceso busena is blokuotos i blokuota sustabdyta ar is pasiruosusios i pasiruosiusia sustabdyta.
        //Einamasis procesas stabdomas tampa pasiruosusiu sustabdytu 
        //planner();
    }

    public void activateProcess(){
        //Keiciama proceso busena is blokuotos sustabdytos i blokuota ar is pasiruosiusi sustabdyta i pasiruosusi.
        //planner();
    }

    public void planner(){
        //skirsto resursus, procesoriu.
    }

    class StartStop{
        public void startStop(){}
    }

    class ReadFromInterface{
        public void readFromInterface(){}
    }

    class MainProc{
        public void mainProc(){}
    }

    class JobGovernor{
        public void jobGovernor(){}
    }

    class VirtualMachine{
        public void virtualMachine(){}
    }

    class Interupt{
        public void interupt(){}
    }

    class PrintLine{
        public void printLine(){}
    }

    class GetLine{
        public void getLine(){}
    }

    class Loader{
        public void loader(){}
    }

    class WriteToMemory{
        public void writeToMemory(){}
    }

    class MemoryGovernor{
        public void memoryGovernor(){}  
    }

}