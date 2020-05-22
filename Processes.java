import java.util.ArrayList;

enum ProcessID{
  START_STOP,
  READ_FROM_INTERFACE,
  MAIN_PROC,
  JOB_GOVERNOR,
  VIRTUAL_MACHINE,
  INTERRUPT,
  PRINT_LINE,
  GET_LINE,
  LOADER,
  WRITE_TO_MEMORY,
  MEMORY_GOVERNOR
}

public class Processes{
  private List runningProcesses = new ArrayList();
  private int descriptorSpace = 4; //nezinau kiek kolkas
  private int[] descriptor = new int[descriptorSpace]; //dinaminis objektas //proceso aprasas //laikomi visi reikalingi parametrai kaip virtualios masinos registru reksmes ir kiti kintamieji

  public void createProcess(Processes parent, String state, int priority, List<String, Integer> elements, String name){
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

  interface Process{
    public void work(Resources resource);
  }

  class StartStop implements Process{
      public void work(Resources resource){}
  }

  class ReadFromInterface implements Process{
      public void work(Resources resource){}
  }

  class MainProc implements Process{
      public void work(Resources resource){}
  }

  class JobGovernor implements Process{
      public void work(Resources resource){}
  }

  class VirtualMachine implements Process{
      public void work(Resources resource){}
  }

  class Interupt implements Process{
      public void work(Resources resource){}
  }

  class PrintLine implements Process{
      public void work(Resources resource){}
  }

  class GetLine implements Process{
      public void work(Resources resource){}
  }

  class Loader implements Process{
      public void work(Resources resource){}
  }

  class WriteToMemory implements Process{
      public void work(Resources resource){}
  }

  class MemoryGovernor implements Process{
      public void work(Resources resource){}  
  }

}