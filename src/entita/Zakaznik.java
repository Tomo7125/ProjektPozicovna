package entita;

import pozicovna.Pozicovna;
import predmety.VypozicanyPredmet;

public class Zakaznik {

    private String meno;
    private String priezvisko;
    private VypozicanyPredmet[] vypozicky;

    private final int maxPocetVypoziciek = 5;

    /** Konštruktor pre zakaznika ktorý vytvorí aj pole výpožièky s dlžkou pola maxPocetVypoziciek (5) */
    public Zakaznik() {
        vypozicky = new VypozicanyPredmet[maxPocetVypoziciek];
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    /**
    Vzhladom na to že java 1.8 neponuka funkciu Arrays.isEmpty som vytvoril cyklus ktorý mi zistí èi sa v
    poli nieèo nacháda. Ak je pole prazdne podmienka if je pravdiva a vypíše sa že zakaznik nema niè vypožièané
    Ak je podmienka false tak cyklus for postupne vypíše údaje na konzolu
    Poradie je èíslo indexu v zátvorkách --> názov objektu èi ide o knihu , cd , casopis --> nazov predmetu
    --> cenu predmetu --> farbu predmetu ( každý objekt ma ešte vlastne atributy tie sme nevypisovali )
     */
    public void vypisVypozicky(){
        boolean jePrazdne = true;

        for (int i = 0; i < vypozicky.length; i++) {
            if (vypozicky[i] != null) {
                jePrazdne = false;
                break;
            }
        }
        if (jePrazdne){
            System.out.println("Aktuálne zákaznik nemá vypožièané niè");
        }else {
            System.out.println("Zakaznik ma aktualne vypožièané nasledovné predmety :");
            for (int i = 0 ; i < vypozicky.length ; i++){
                if (vypozicky[i] != null){
                    VypozicanyPredmet predmet = vypozicky[i];
                    System.out.println("[" + (i) + "]" + "Predmet :" + predmet.getClass().getSimpleName() +
                            ", Nazov : " + predmet.getNazov() + " Cena: " + predmet.getCena() + " Farba : "  + predmet.getFarba());
                }
            }

            }
    }

    /**
    Metoda vytvori premennu pocet , potom sa cyklus opakuje kym nedosiahne posledný prvok v poli ( zabezpecuje vypozicky.lenght)
    podmienka if overuje èi je prvok v poli plný ak je nul nevykoná sa no ak v òom je objekt tak vykoná pocet++
    na konci bloku kodu máme navratovu hodnotu pocet ktora vrati poèet objektov v poli
     */
    public int getPocetVypoziciek(){
        int pocet = 0;
        for (int i = 0 ; i < vypozicky.length ; i++){
            if (vypozicky[i] != null){
                pocet++;
            }
        }
        return pocet;
    }

    /**
    Metoda na zaèiatku overí èi je poèet vypožièiek menší ako maximalny poèet vypožièiek aby si zakaznik nevypožièal viac predmetov ako má
    Ak je to viac vypýše sa hláška o prekroèeni limitu
    V podmienke if je cyklus for ktorý sa opakuje kym sa neprejde cele pole vypozièiek
    Podmienka if vypoziky[i] == predmetu kontroluje èi už predmet nemam požièany ak ano vipiše sa hlaška že už je vypožièaný
    Ïalšie if kontroluje èi nieje predmet vo vstupnom predmete null inak by mohol program padnu na nullPointerException
    ak je všetko ok program pokraèuje do riadku kde kontrolujeme èi je pole vypozicky[i] prázdne ak áno zapíšeme tam predmet
    a napíšeme že predmet bol zapožièaný
     */
//    public boolean pozicajSiPredmet(VypozicanyPredmet predmet){
//        if (getPocetVypoziciek() < maxPocetVypoziciek){
//            for (int i = 0 ; i < vypozicky.length ; i++){
//                if (vypozicky[i] == predmet){
//                    if (predmet== null){
//                        System.out.println("Predmet neexistuje");
//                    }else
//                        System.out.println("Predmet už je vypožièaný");
//                    break;
//                }
//                if (vypozicky[i] == null){
//                    vypozicky[i] = predmet;
//                    System.out.println("Predmet bol zapožièaný");
//                    return true;
//                }
//            }
//        } else {
//            System.out.println("Poèet výpožièiek na zakaznika bol presiahnutý");
//        }
//        return false;

    /**
     * Táto metoda len prepisuje predošlu metódu , zmenená je v tom že to èi nam vstupny parameter neprišiel null
     * overujeme hned na zaèiatku aby sme ušetrili èas ktorý by zbytoène program vykonal ak by musel prechádza pole
     * výpožièiek a až potom overil èi mu vlastne neprišiel na vstup null
     * @param predmet
     * @return
     */
    public boolean pozicajSiPredmet(VypozicanyPredmet predmet){
        if (predmet != null){
            if (getPocetVypoziciek() < maxPocetVypoziciek) {
                for (int i = 0 ; i < vypozicky.length ; i++) {
                    if (vypozicky[i] == predmet){
                        System.out.println("Predmet už je vypožièaný");
                    }
                    if (vypozicky[i] == null){
                        vypozicky[i] = predmet;
                        System.out.println("Predmet bol zapožièaný");
                        return true;
                    }
                }
            }
        }else System.out.println("Predmet neexistuje");
        return false;
    }

    /**
    Tato metoda príjma predmet ktory vraciame prechádzame v cykle for polia a ked sa nam v podmienke if bude zhodova
    predmet na vratenie s predmetom v poli tak do daného pola vložime hodnotu null
    v týchto podmienkach som èasto miesto metodi .equals použival == lebo pri použití .equals na porovnanie predmetov
    mi padal program na nullPointerException napriklad pri vypozicky[i].equals(predmet) tak som to prepisal na
    vypozicky[i] == predmet a program beží beží už bez chyby
     */
    public boolean vratPredmet(VypozicanyPredmet predmet){
        for (int i = 0 ; i < vypozicky.length ; i++){
            if (vypozicky[i] == predmet){
                vypozicky[i] = null;
            }
        }
        return true;
    }

    /**
    Tato metoda len vracia predmet na indexe služi mi na zobratie predmetu a vloženie do dalšej metoddy pre prácu s predmetom
     */
    public VypozicanyPredmet getPredmetNaIndexe(int index){
        return vypozicky[index];
    }
    /**
    Tuto metodu som pridal len kôli tomu aby som mohol v case:4 v triede start ošetri problem s tym keby použivatel chcel
    vratit predmet na inom indexe ako je rozsah po¾a. Keby som v tride start na tvrdo zadal poèet že môže zada len èísla od 0 do 4
    mohol by to by problem keï sa zmeni poèet maximalnych výpožièiek pre zakaznika.. Takto pokial zmenime maximalny
    poèet vypožièiek pre zakaznika bude program fungovat dalej
     */
    public int getMaxPocetVypoziciek(){
        int i = vypozicky.length;
        return i;
    }
}
