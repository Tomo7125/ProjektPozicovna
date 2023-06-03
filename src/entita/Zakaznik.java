package entita;

import pozicovna.Pozicovna;
import predmety.VypozicanyPredmet;

public class Zakaznik {

    private String meno;
    private String priezvisko;
    private VypozicanyPredmet[] vypozicky;

    private final int maxPocetVypoziciek = 5;

    /** Kon�truktor pre zakaznika ktor� vytvor� aj pole v�po�i�ky s dl�kou pola maxPocetVypoziciek (5) */
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
    Vzhladom na to �e java 1.8 neponuka funkciu Arrays.isEmpty som vytvoril cyklus ktor� mi zist� �i sa v
    poli nie�o nach�da. Ak je pole prazdne podmienka if je pravdiva a vyp�e sa �e zakaznik nema ni� vypo�i�an�
    Ak je podmienka false tak cyklus for postupne vyp�e �daje na konzolu
    Poradie je ��slo indexu v z�tvork�ch --> n�zov objektu �i ide o knihu , cd , casopis --> nazov predmetu
    --> cenu predmetu --> farbu predmetu ( ka�d� objekt ma e�te vlastne atributy tie sme nevypisovali )
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
            System.out.println("Aktu�lne z�kaznik nem� vypo�i�an� ni�");
        }else {
            System.out.println("Zakaznik ma aktualne vypo�i�an� nasledovn� predmety :");
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
    Metoda vytvori premennu pocet , potom sa cyklus opakuje kym nedosiahne posledn� prvok v poli ( zabezpecuje vypozicky.lenght)
    podmienka if overuje �i je prvok v poli pln� ak je nul nevykon� sa no ak v �om je objekt tak vykon� pocet++
    na konci bloku kodu m�me navratovu hodnotu pocet ktora vrati po�et objektov v poli
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
    Metoda na za�iatku over� �i je po�et vypo�i�iek men�� ako maximalny po�et vypo�i�iek aby si zakaznik nevypo�i�al viac predmetov ako m�
    Ak je to viac vyp��e sa hl�ka o prekro�eni limitu
    V podmienke if je cyklus for ktor� sa opakuje kym sa neprejde cele pole vypozi�iek
    Podmienka if vypoziky[i] == predmetu kontroluje �i u� predmet nemam po�i�any ak ano vipi�e sa hla�ka �e u� je vypo�i�an�
    �al�ie if kontroluje �i nieje predmet vo vstupnom predmete null inak by mohol program padnu na nullPointerException
    ak je v�etko ok program pokra�uje do riadku kde kontrolujeme �i je pole vypozicky[i] pr�zdne ak �no zap�eme tam predmet
    a nap�eme �e predmet bol zapo�i�an�
     */
//    public boolean pozicajSiPredmet(VypozicanyPredmet predmet){
//        if (getPocetVypoziciek() < maxPocetVypoziciek){
//            for (int i = 0 ; i < vypozicky.length ; i++){
//                if (vypozicky[i] == predmet){
//                    if (predmet== null){
//                        System.out.println("Predmet neexistuje");
//                    }else
//                        System.out.println("Predmet u� je vypo�i�an�");
//                    break;
//                }
//                if (vypozicky[i] == null){
//                    vypozicky[i] = predmet;
//                    System.out.println("Predmet bol zapo�i�an�");
//                    return true;
//                }
//            }
//        } else {
//            System.out.println("Po�et v�po�i�iek na zakaznika bol presiahnut�");
//        }
//        return false;

    /**
     * T�to metoda len prepisuje predo�lu met�du , zmenen� je v tom �e to �i nam vstupny parameter nepri�iel null
     * overujeme hned na za�iatku aby sme u�etrili �as ktor� by zbyto�ne program vykonal ak by musel prech�dza� pole
     * v�po�i�iek a a� potom overil �i mu vlastne nepri�iel na vstup null
     * @param predmet
     * @return
     */
    public boolean pozicajSiPredmet(VypozicanyPredmet predmet){
        if (predmet != null){
            if (getPocetVypoziciek() < maxPocetVypoziciek) {
                for (int i = 0 ; i < vypozicky.length ; i++) {
                    if (vypozicky[i] == predmet){
                        System.out.println("Predmet u� je vypo�i�an�");
                    }
                    if (vypozicky[i] == null){
                        vypozicky[i] = predmet;
                        System.out.println("Predmet bol zapo�i�an�");
                        return true;
                    }
                }
            }
        }else System.out.println("Predmet neexistuje");
        return false;
    }

    /**
    Tato metoda pr�jma predmet ktory vraciame prech�dzame v cykle for polia a ked sa nam v podmienke if bude zhodova�
    predmet na vratenie s predmetom v poli tak do dan�ho pola vlo�ime hodnotu null
    v t�chto podmienkach som �asto miesto metodi .equals pou�ival == lebo pri pou�it� .equals na porovnanie predmetov
    mi padal program na nullPointerException napriklad pri vypozicky[i].equals(predmet) tak som to prepisal na
    vypozicky[i] == predmet a program be�� be�� u� bez chyby
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
    Tato metoda len vracia predmet na indexe slu�i mi na zobratie predmetu a vlo�enie do dal�ej metoddy pre pr�cu s predmetom
     */
    public VypozicanyPredmet getPredmetNaIndexe(int index){
        return vypozicky[index];
    }
    /**
    Tuto metodu som pridal len k�li tomu aby som mohol v case:4 v triede start o�etri� problem s tym keby pou�ivatel chcel
    vratit predmet na inom indexe ako je rozsah po�a. Keby som v tride start na tvrdo zadal po�et �e m��e zada� len ��sla od 0 do 4
    mohol by to by� problem ke� sa zmeni po�et maximalnych v�po�i�iek pre zakaznika.. Takto pokial zmenime maximalny
    po�et vypo�i�iek pre zakaznika bude program fungovat dalej
     */
    public int getMaxPocetVypoziciek(){
        int i = vypozicky.length;
        return i;
    }
}
