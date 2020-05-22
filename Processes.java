import java.util.ArrayList;

enum ProcessPriority{
  JOB_GOVERNOR,
  VIRTUAL_MACHINE,
  PRINT_LINE,
  GET_LINE,
  WRITE_TO_MEMORY,
  LOADER,
  READ_FROM_INTERFACE,
  MAIN_PROC,
  MEMORY_GOVERNOR,
  INTERRUPT,
  START_STOP
}

enum ProcessState{
   RUNNING, 
   BLOCKED,
   BLOCKED_STOPPED,
   READY_STOPPED,
   READY
}

interface Process{
   public void work();
}

public class Processes{
   private ArrayList<Process> runningProcesses = new ArrayList<Process>();
   private int descriptorSpace = 4; //nezinau kiek kolkas
   private int[] descriptor = new int[descriptorSpace]; //dinaminis objektas //proceso aprasas //laikomi visi reikalingi parametrai kaip virtualios masinos registru reksmes ir kiti kintamieji

   public void createProcess(Process parent, ProcessState state, ProcessPriority priority, ArrayList<String> elements, String name){
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

   class StartStop implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}
   }

   class ReadFromInterface implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}
   }

   class MainProc implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}
   }

   class JobGovernor implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}
   }

   class VirtualMachine implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}
   }

   class Interupt implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}
   }

   class PrintLine implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}
   }

   class GetLine implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}
   }

   class Loader implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}
   }

   class WriteToMemory implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}
   }

   class MemoryGovernor implements Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){}  
   }
}