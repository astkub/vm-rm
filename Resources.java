import java.util.LinkedList;
import java.util.Queue;

class Resource{
   Queue<Process> queue = new LinkedList<Process>();
   void addToQueue(Process process){
      queue.add(process);
   }
   
}
public class Resources{
   MOS_pabaiga mos_pabaiga;
   IsVartotojoSasajos isVartotojoSasajos;
   IsGetLine isGetLine;
   Duomenu_vieta duomenu_vieta;
   IsLoader isLoader;
   Uzduotis_supervizorineje_atmintyje uzduotis_supervizorineje_atmintyje;
   Irasyta_i_atminti irasyta_i_atminti;
   Vartotojo_atmintis vartotojo_atmintis;
   IsInterrupt isInterrupt;
   Laukti_ivedimo laukti_ivedimo;
   Eilute_atmintyje eilute_atmintyje;
   IsPrintLine isPrintLine;
   Semaforas semaforas;
   IsMemoryGovernor isMemoryGovernor;
   Continuee continuee;
   Interrupt interrupt;
   Kanalas_1 kanalas_1;
   Kanalas_2 kanalas_2;
   Kanalas_3 kanalas_3;
   //Negzistuojantis_resursas neegzistuojantis_resursas;
   class MOS_pabaiga extends Resource{}
   class IsVartotojoSasajos extends Resource{}
   class IsGetLine extends Resource{}
   class Duomenu_vieta extends Resource{}
   class IsLoader extends Resource{}
   class Uzduotis_supervizorineje_atmintyje extends Resource{}
   class Irasyta_i_atminti extends Resource{}
   class Vartotojo_atmintis extends Resource{}
   class IsInterrupt extends Resource{}
   class Laukti_ivedimo extends Resource{}
   class Eilute_atmintyje extends Resource{}
   class IsPrintLine extends Resource{}
   class Semaforas extends Resource{}
   class IsMemoryGovernor extends Resource{}
   /*class Neegzistuojantis_resursas extends Resource{}*/
   class Continuee extends Resource{}
   class Interrupt extends Resource{}
   class Kanalas_1 extends Resource{}
   class Kanalas_2 extends Resource{}
   class Kanalas_3 extends Resource{}
}