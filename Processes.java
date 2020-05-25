import java.util.ArrayList;
import java.util.Iterator;

import javax.lang.model.type.NullType;

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
   public ArrayList<Process> children = new ArrayList<Process>();
   public ArrayList<Resources> resources = new ArrayList<Resources>();
}

public class Processes{
   private ArrayList<Process> runningProcesses = new ArrayList<Process>();
   private ArrayList<Process> blokuoti = new ArrayList<Process>();
   private ArrayList<Process> pasiruose = new ArrayList<Process>();
   private Process einamasis;
   private int descriptorSpace = 4; //nezinau kiek kolkas
   private int[] descriptor = new int[descriptorSpace]; //dinaminis objektas //proceso aprasas //laikomi visi reikalingi parametrai kaip virtualios masinos registru reksmes ir kiti kintamieji

   public Process newInstance(String class_name)throws ClassNotFoundException, InstantiationException, IllegalAccessException{
      Class<?> process = Class.forName(class_name);
      if (!Process.class.isAssignableFrom(process)) {
         throw new IllegalArgumentException();
      }
      return (Process) process.newInstance();
   }

   public void createProcess(Process parent, ProcessState state, ProcessPriority priority, ArrayList<String> elements, String class_name) {
      try{
         //kuriamas procesas
         Process process = newInstance(class_name);
         //procesas registruojamas bendrajame procesu sarase, tevo sunu sarase
         runningProcesses.add(process);
         parent.children.add(process);
         //skaiciuojamas vidinis identifikacijos numeris
         //? descriptor ?
         //sukuriamas vaiku procesu sarasas(tuscias), sukurtu resursu sarasas ir t.t
         process.children.clear();
         process.resources.clear();
         planner();
      }
      catch(Exception e){
         System.out.println("Failed to create process: " + class_name);
      }
   }

   public void killProcess(Process process){
      //Pradedama naikinti proceso sukurtus resursus ir vaikus
      for (int i = 0; i < process.children.size(); i++){
         killProcess(process.children.get(i));
      }
      //išmetamas iš tevo sukurtu procesu saraso
      //take from descryptor?
      //parent.children.delete(process);

      //ismetamas is bendro procesu saraso ir, jei reikia, is pasiruosusiu resursu saraso
      runningProcesses.remove(process);
      if(pasiruose.contains(process))
         pasiruose.remove(process);

      //naikinami visi jam perduoti resursai ir proceso descriptorius yra sunaikinamas
      for (int i = 0; i < process.resources.size(); i++){
         destroyResoucse(process.resources.get(i));
      }
      //process.descriptor.delete;
      planner();
   }

   public void stopProcess(Process process){
      //Keiciama proceso busena is blokuotos i blokuota sustabdyta ar is pasiruosusios i pasiruosiusia sustabdyta.
      //Einamasis procesas stabdomas tampa pasiruosusiu sustabdytu 
      if (process.state == ProcessState.valueOf("BLOCKED"))
         process.state = ProcessState.valueOf("BLOCKED_STOPPED");
      else if(process.state == ProcessState.valueOf("READY") || process.state == ProcessState.valueOf("RUNNING"))
         process.state = ProcessState.valueOf("READY_STOPPED");
      planner();
   }

   public void activateProcess(Process process){
      //Keiciama proceso busena is blokuotos sustabdytos i blokuota ar is pasiruosiusi sustabdyta i pasiruosusi.
      if (process.state == ProcessState.valueOf("BLOCKED_STOPPED"))
         process.state = ProcessState.valueOf("BLOCKED");
      else if(process.state == ProcessState.valueOf("READY_STOPPED"))
         process.state = ProcessState.valueOf("READY");
      planner();
   }

   public void planner(){
      //skirsto resursus, procesoriu.
      //Process einamasis = process.state == RUNNING;
      if (einamasis.state == ProcessState.valueOf("RUNNING")){
          einamasis.state = ProcessState.valueOf("BLOCKED");
          blokuoti.add(einamasis);
      }
      else if (!pasiruose.isEmpty()){
            einamasis = pasiruose.get(0);
            pasiruose.remove(einamasis);
            pasiruose.state = ProcessState.valueOf("RUNNING");
      //    pasiruose.give(processor);
      //    pasiruose.work();//?
      }

      
   }

   //Resurso kūrimo metu nuoroda į proceso kūrėją ir resurso išorinis vardas perduodami kaip parametrai.
   //Ar reikia proceso tevo linko?
   public void createResource(Process self, String name){
      //sukuriamas resursas name;
      //pridedamas prie bendro resursu saraso
      //pridedamas prie tevo sukurtu resursu saraso
      //priskiriamas unikalus vidinis vardas
      //sukuriamas resurso elementu sarasas
      //sukuriamas laukianciuju procesu sarasas
   }

   public void destroyResoucse(Resources resource){
      //deskriptorius ismetamas is tevo sukurtu resursu saraso
      //naikinamas jo elementu sarsas
      //atblokuojami procesai laukiantys sio resurso
      //ismetamas is bendrojo resursu saraso
      //naikinamas pats descriptorius

   }

   public void askForResource(Process self, Resources resource){
      //blokuojamas procesas iskvietes si primityva
      //ir itraukiamas i laukianciuju sarasa
      resourcesDistributor();
   }

   public void alocateResource(Resources element){
      //element pridedamas prie resurso elementu saraso;
      resourcesDistributor();
   }

   //su kiekvienu resursu susiejamas jo laukianciu procesu sarasas
   //suteikia paprasyta resurso elementu kieki procesui
   public void resourcesDistributor(){
      //kas anksciau papraso tas anksciau gauna
      //po gavimo procesas isbraukiamas is resurso saraso
      //nauji procesai talpinami i eiles gala
      //turi buti numatyta galimybe atiduoti resurso elementa konkreciam procesui, arba prasyti keliu resurso elementu.      
      //perzvelgia visus procesus sarase laukiancius sio resurso
      //atsiradus galimybei aptarnauti procesa, perduoda jam reikalingus elementus
      //pazymi ji pasiruosiusiu
      planner();    
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