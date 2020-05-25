import java.util.ArrayList;
import java.util.Iterator;
import java.util.*; 
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

class Process{
   public ProcessState state;
   public void work() {};
   public ArrayList<Process> children = new ArrayList<Process>();
   public ArrayList<Resources> resources = new ArrayList<Resources>();
}

class ProcessComparator implements Comparator<Process>{
   public int compare(Process s1, Process s2) { 
      if (s1.state.ordinal() < s2.state.ordinal()) 
         return 1; 
      else if (s1.state.ordinal() > s2.state.ordinal()) 
         return -1; 
      return 0; 
   } 
} 
public class Processes{
   private ArrayList<Process> runningProcesses = new ArrayList<Process>();
   private PriorityQueue<Process> blocked = new PriorityQueue<Process>(5, new ProcessComparator());
   private PriorityQueue<Process> ready = new PriorityQueue<Process>(5, new ProcessComparator());
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
      if(ready.contains(process))
         ready.remove(process);

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
          blocked.add(einamasis);
      }
      else if (!ready.isEmpty()){
         einamasis = ready.poll(); // Retrieves and removes the head of this queue, or returns null if this queue is empty.
            //einamasis = ready.get(0);
            //ready.remove(einamasis);

            ready.state = ProcessState.valueOf("RUNNING"); // wtf is this? ready is list, not process
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
      self.state = ProcessState.valueOf("BLOCKED");
      //ir itraukiamas i laukianciuju sarasa
      resource.waitList(self);
      resourcesDistributor();
   }

   public void alocateResource(Resources resource, Resources element){
      //element pridedamas prie resurso elementu saraso;
      resource.elements(element);
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

   class StartStop extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //gaves procesoriu sukuria visus sisteminius resursus
         //sukuria sisteminius procesus
         //createProcess(self, ProcessState.valueOf("BLOCKED"), priority, elements, class_name); reikalingas visiems
         //Prašo "MOS pabaiga resurso" - blokuojasi ir laukia kol bus atlaisvintas pranesimas apie MOS pabaiga
         //askForResource(Process, resource)
         //visu procesoriu naikinimas
         //sisteminiu resursu naikinimas
         //MOS darbo pabaiga
      }
   }

   class ReadFromInterface extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //laukiama resurso "is vartotojo sasajos"
         //laukiama resurso "isGetLine"
         //Loader perduodamas resursas "duomenu vieta" su rogramos vieta
         //Laukiama kol Loader nuskaitys ir irasys programa i supervizorine atminti
         //atlaisvinamas "uzduotis supervizorineje atmintyje" resursas
         //atlaisvinus resursa ReadFromInterfase uzsiblokuoja
      }
   }

   class MainProc extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //Parasoma "uzduotis supervizorineje atmintyje" resurso, kuris yra atlaisvinamas proceso ReadFromInterface
         //Tikrinamas MainProc vykdymo laikas
         //jei 0, kuriamas procesas WriteToMemory ir JobGovernor
         //jei ne 0, JobGovernor yra naikinamas
         //blokuojasi laukdamas "Uzduotis supervizorineje atmintyje"
      }
   }

   class JobGovernor extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //praso vartotojo atminties, kad patalpinti vartotojo uzduoties programa
         //laukiama kol vartotojo programa bus irasyta i vartotojo atminti
         //puslapiu lentele yra sukuriama ir uzpildoma išskirtos vartotojo atminties adresais
         //kuriamas procesas MemoryGovernor
         //kuriamas procesas VirtualMachine
         //blokuojasi ir laukia pertraukimo proceso
            //jei pertraukimas GD ivesties, procesui GetLine atlaisvinamas "Laukti ivedimo" resursas
            //jei ne GD, procesui PrintLine atlaisvinamas "Eilute atmintyje" resursas
            //jei semaforo, atlaisvinamas "Semaforas" resursas konkreciai VM
            //Laukiama resurso is MemoryGovernor proceso
               //Jei pertraukimas nei ivedimo nei isvedimo, nei semaforo, VM procesas naikinamas
               //Atlaisvinama VM uzimta atmintis
               //Kol nesunaikintas blokuojasi, kaukiant "Neegzistuojantis resursas"
         //procesas VM aktivuojamas atlaisvinant "Continue" resursa ir JG cikliskai grizta blokuotis
      }
   }

   class VirtualMachine extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //vartotojo programa vykdoma vartotojo rezime iki pertraukimo
         //ivykus pertraukimui, VM issaugo savo procesoriaus busena valdymas perduodamas pertraukima apdorosiancioms programoms (Interrupt)
         //kuriamas "Interrupt" resursas, kuris identifikuos pertraukima ir perduos informacija JG
         //Laukia kol gales testi darba
      }
   }

   class Interrupt extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //kartu su pranesimuapie pertraukima yra perduodamas ir tevo identifikatorius, kuri procesas Interrupt naudoja JG atskyrimui is kitu
         //Procesas laukia "Interrupt" resurso, kuri  siuncia procesas VM
         //Nustatomas pertraukimo tipas perziurint sisteminiu kintamuju reiksmes
         //Nustatomas JG
         //kuriamas ir atlaisvinamas "isInterupt" resursas, skirtas nustatytas JG procesoriui
         //cikliskai blokuojasi laukdamas "Interrupt" resurso
         
      }
   }

   class PrintLine extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //blokuojasi laukdamas "Eilute atmintyje" resurso, kur gauna parametra is kurios atminties reikes pasiusti eilute i isvedimo
         //srauta ir atminties adresa, zyminti bloko numeri
         //blokuojasi laukdamas leidimo dirbti su kanalu "CH2"
         //nustatoma "CH2" reiksme, agal tai ar ivyko isvedimas
         //atlaisvinamas "Kanalas2" resursas
         //sukuriamas ir atlaisvinamas "isPrintLine" resursas
         //cikliskai blokuojasi laukdamas "Eilute atmintyje" resurso
      }
   }

   class GetLine extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //Blokuojasi laukdamas "Laukti ivedimo" resurso, kuris turi parametra i kuri bloka rasyti gautus duomenis
         //Blokuojasi laukdamas leidimo sirbti su kanalu "CH1"
         //Nustatoma "CH1" reiksme, pagal ar pavyko nuskaityti
         //atlaisvinamas "Kanalas1" resursas;
         //Sukuriamas ir atlaisvinamas "isGetLine" resursas
         //blokuojasi laukiant "Laukti ivedimo"
      }
   }

   class Loader extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //blokuojasi laukdamas "Laukti ivedimo" resurso, kuris turi parametra i kuri bloka rasyti gautus duomenis
         //Blokuojasi laukdamas leidimo sirbti su kanalu "CH3"
         //Nustatoma "CH3" reiksme, pagal ar pavyko nuskaityti
         //atlaisvinamas "Kanalas3" resursas;
         //Sukuriamas ir atlaisvinamas "isLoader" resursas
         //blokuojasi laukiant "Laukti ivedimo"
      }
   }

   class WriteToMemory extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //Blokuojasi laukiant "Uzduotis supervizorineje atmintyje"
         //Sintakses paskirstymas
         //Programa irasoma i vartotojo atminti
         //Atlaisvinamas "Irasyta i atminti" resursas
      }
   }

   class MemoryGovernor extends Process{
      public ProcessPriority priority;
      public ProcessState state;
      public void work(){
         //cikliskai blokuojasi laukiant "Semaforas" resurso
         //Veiksmai su duomenimis
            //Vykdomas nuskaitymas
            //vykdomas irasymas
         //Atlaisvinama nustatyta bendros atminties sritis
         //sukuriamas ir atlaisvinams resursas "IsMemoryGovernor" 
      }  
   }
}