# vm-rm
Virtualios ir realios mašinos realizacija
Virtualios ir realios mašinos realizacija

Užduočiai atlikti reikia realizuoti suprojektuotą virtualią ir realią mašiną, t.y. parašyti klases, modulius, funkcijas (priklauso nuo pasirinktos kalbos), realizuojančias suprojektuotas komponentes.

Jei realizuojant pasirodys, kad reikia dėl vienų ar kitų priežasčių taisyti projektą (kažkas nebuvo numatyta ar pan.), tą galima daryti, tačiau nekeičiant užduoties esmės. Atsiskaitymo metu tuomet reikės parodyti ir paaiškinti atliktus pakeitimus.

Virtualios mašinos projekte turi būti realizuota:
1. Parašyta bent viena pavyzdinė programa, kurią reiks naudoti pristatant virtualią mašiną atsiskaitymo metu.
2. Galimybė įvykdyti programą iš karto arba vykdyti ją žingsniniu rėžimu.
3. Vartotojo sąsajoje turi būti komandų atlikimo demonstracija bei visų VM ir RM komponentų būsenų kaita vykdant programą žingsniniu rėžimu.
   3.1. Registrų reikšmės (VM ir RM).
   3.2. Sekanti vykdoma komanda.
   3.3. Išorinių įrenginių būsenos.
4. Turi būti galimybė atvaizduoti VM atmintį.
5. Turi būti galimybė atvaizduoti RM atmintį arba nurodytą RM atminties puslapį.
6. Puslapiai VM skiriami ne nuosekliai

Kadangi OS dar nėra, turėtų būti parašyta valdymo programa, kuri atstotų primityvių nemultiprograminų OS: leistų įkelti vieną vartotojo programą ir ją įvykdyti.

Programuojama gali būti bet kuria programavimo kalba. Vartotojo interfeisas gali būti tiek grafinis (GUI), tiek komandinis (CMD). Programos išeities tekstai (source kodas) turi būti tvarkingi, parašyti laikantis struktūrinio (ar objektinio) programavimo reikalavimų.

Užduotį rekomenduojama atlikti etapais. Pradžioje parašykite programą, emuliuojančią VM veikimą, kuri tiesiog įkeltų į atmintį (be jokios puslapių transliacijos) programą ir ją įvykdytų. Tada realizuokite realios mašinos komponentes, jau laikydami, kad virtualaus procesoriaus darbą imituoja realios mašinos procesorius, o virtuali atmintis yra dalis realios mašinos vartotojo atminties.

Atsiskaitymui reikia parašyti kelias nesudėtingas, bet prasmingas programėles jūsų virtualiai mašinai, kurios leistų pademonstruoti visą komandų veikimą. Aišku, realizuota virtuali mašina turi galėti įvykdyti ne tik šias, bet ir bet kokias kitas jai parašytas programas. Atsiskaitymo metu reikės pateikti projektą iš pirmos dalies, parodyti ir paaiškinti programos išeities tekstus (source kodą), pademonstruoti ir paaiškinti jos veikimą, atsakyti į klausimus, susijusius tiek su dalykine sritimi ("kam reikalingas šis procesoriaus registras"), tiek realizacija ("ką daro ši funkcija"). Reikalaujama žinoti tiek dalykinės srities, tiek realizacines sąvokas, mokėti modifikuoti programą, realizuojančią VM, bei programą, parašytą tai VM, paaiškinti atliktų modifikacijų įtaką.
