package pozicovna;

import entita.Zakaznik;
import predmety.*;

import java.util.Scanner;

public class Start {
    public Zakaznik zakaznik;
    public Pozicovna pozicovna;


    /**
     * Metoda vytvor˝ poùiËovÚu a naplnÌ uloûisko volanim metod na pridavanie predmetov
     */
    public void inicializuj(){
        zakaznik = new Zakaznik();
        pozicovna = new Pozicovna("Altex");
        pozicovna.vytvorPozicovnu(10,10,5);

        Kniha kniha1 = new Kniha("Janko a marienka" , 19.90 , Farba.BIELA , "SK123151" , "Tom·ö" , 230);
        Kniha kniha2 = new Kniha("SlovenskÈ rozpr·vky" , 6.90 , Farba.CERVENA , "SK000001" , "Pavol Doböinsk˝" , 85);
        Kniha kniha3 = new Kniha("MalÌ princ" , 9.90 , Farba.ZLTA , "SK000021" , "Antoine" , 99);
        Kniha kniha4 = new Kniha("S˙mrak" , 15.40 , Farba.MODRA , "SK564562" , "Stephanie Meyerov·" , 290);
        pozicovna.pridajKnihu(kniha1);
        pozicovna.pridajKnihu(kniha2);
        pozicovna.pridajKnihu(kniha3);
        pozicovna.pridajKnihu(kniha4);

        CD cd1 = new CD("VianoËnÈ koledy" , 2.99 , Farba.CERVENA , 42.30 , "SK12355" , "ûbirka");
        CD cd2 = new CD("Komorn˝ orchester" , 5.20 , Farba.ZLTA , 39.00 , "SK12345" , "Nezn·my");
        CD cd3 = new CD("Polemic" , 9.80 , Farba.PRIESVITNA , 42.20 , "SK123444" , "Polemix a spol");
        pozicovna.pridajCD(cd1);
        pozicovna.pridajCD(cd2);
        pozicovna.pridajCD(cd3);

        Casopis casopis1 = new Casopis("Nov˝ Ëas" , 3.20 , Farba.ZLTA , 42);
        Casopis casopis2 = new Casopis("Dennik N" , 2.40 , Farba.ZLTA , 47);
        pozicovna.pridajCasopis(casopis1);
        pozicovna.pridajCasopis(casopis2);
    }

    /**
     * Vypis menu len vypÌöe nesledovne riadky
     */
    public void vypisMenu(){
        System.out.println("Zadaj podla ËÌsla Ëo si ûel·ö vykonaù: " +
                "\n1. VypÌö aktu·lne ˙loûisko" +
                "\n2. PoûiËaj si predmet na indexe" +
                "\n3. VypÌö v˝poûiËky" +
                "\n4. Vr·ù predmet na indexe" +
                "\n5. Exit");

    }

    /**
     * Na zaËiatku zavolame metodu inicializuj ktora naplni uloûisko
     * exit nastavime na false a neskÙr zavolame cyklus while ktory bude fungovaù pokial nebude exit true
     * vytvorime premennu typu int kde budeme zapisovat volbu zakaznika
     * sout vipÌöe vitaj + nazov poûiËovne
     * cyklus while s podmienkou exit nastavenou na false
     * zavolame vypisanie menu
     * vytvorime si nov˝ scanner ktory budem mat pod premennou sc
     * volbu zakaznika zoberiem z sc.nextInt() a volozime ju do volby zakaznika , zakaznik uz videl menu takûe volÌ Ëo sa ma spraviù
     * vytvoril som switch a budeme switchovaù volbu zakaznika
     * @case:1 v pozicovnyy zavola metodu pre vypisanie uloziska
     * @case:2 Ak bolo zadane Ëislo 2 tak chceme poûiËaù predmet pouûivatela vyzvem na zadanie Ëisla indexu
     * zadane Ëislo uloûim do volbyzakaznika a v podmienke si overim ûe je Ëislo v rozsahu volkosti poûiËovne
     * ak je to k tak zavolam metodu pre zakaznika pozicaj si predmet a na vstupe mu z pozicovne posuniem
     * predmet z pozicovne z daneho indexu takûe predmet uû mame zapisany vo vypoûiËkach
     * nasledne zavolam pozicajSiPredmet aj pre pozicovnu a na vstupe dostane len volbu zakaznika takze
     * jej nastavime hodnotu pozicana na true
     * Else sa vykona len ak zadal pozivatel Ëislo mimo rozsah pozicovne
     * @case:3 v zakaznikovi zavolame vypisVypopzicky a len sa vypÌöu v˝poûiËky
     * @case:4 vrati predmet , vyzveme zakaznika aby zadal Ëislo indexu z vypoziciek ktore chce cratit
     * vlozime volbu zakaznika do premennej a overime Ëi je zadane Ëislo v rozsahu vypoziciek ak ano pokracujeme
     * zavolam pozicovna.vratPredmet a na vstupe jej posuniem predmet na indexe z vypoziciek to sa dalej spracuje a porovna
     * aby program vedel ktory predmet sa vratil ( nasetuje ho na pozicany = false )
     * potom zavolam zakaznika.vratPredmet a na vstupe dostane predmet na indexe z vypoziciek ( v tejto metode
     * len vymazem predmet z vypoziciek tak ze mu nastavim hodnotu null)
     * @case:5 a @default ukonËia program a vypÌöu ûe program bol ukonËen˝
     */
    public void startPozicovna(){
            this.inicializuj();
            boolean exit = false;
            int volbaZakaznika;
            System.out.println("Vitaj v poûiËovni " + pozicovna.getNazov());
            while (!exit){
                this.vypisMenu();
                Scanner sc = new Scanner(System.in);
                volbaZakaznika = sc.nextInt();
                switch (volbaZakaznika){
                    case 1 :
                        pozicovna.vypisAktualneUlozisko();
                        break;
                    case 2 :
                        System.out.println("Zadaj ËÌslo indexu na ktorom sa nach·dza predmet ktor˝ si chceö zapoûiËaù:");
                        volbaZakaznika = sc.nextInt();
                        if (volbaZakaznika <= pozicovna.getVelkostPozicovne() & volbaZakaznika > -1){
                        zakaznik.pozicajSiPredmet(pozicovna.getPredmetNaIndexe(volbaZakaznika));
                        pozicovna.pozicajPredmetZakaznikovi(volbaZakaznika);
                        }else System.out.println("Zadane ËÌslo je mimo rozsah pola");
                        break;
                    case 3 :
                        zakaznik.vypisVypozicky();
                        break;
                    case 4 :
                        System.out.println("Zadaj ËÌslo indexu s predmetom ktor˝ chceö aby sa z v˝poûiËiek vr·til:");
                        volbaZakaznika = sc.nextInt();
                        if (volbaZakaznika <= (zakaznik.getMaxPocetVypoziciek()-1) & volbaZakaznika > -1){
                            pozicovna.vratPredmet(zakaznik.getPredmetNaIndexe(volbaZakaznika));
                            zakaznik.vratPredmet(zakaznik.getPredmetNaIndexe(volbaZakaznika));
                        }
                        break;
                    case 5 : exit = true;
                    default: exit = true;
                }
            }
        System.out.println("Program bol ukonËen˝");
    }
}
