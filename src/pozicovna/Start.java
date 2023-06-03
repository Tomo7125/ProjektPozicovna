package pozicovna;

import entita.Zakaznik;
import predmety.*;

import java.util.Scanner;

public class Start {
    public Zakaznik zakaznik;
    public Pozicovna pozicovna;


    /**
     * Metoda vytvor� po�i�ov�u a napln� ulo�isko volanim metod na pridavanie predmetov
     */
    public void inicializuj(){
        zakaznik = new Zakaznik();
        pozicovna = new Pozicovna("Altex");
        pozicovna.vytvorPozicovnu(10,10,5);

        Kniha kniha1 = new Kniha("Janko a marienka" , 19.90 , Farba.BIELA , "SK123151" , "Tom�" , 230);
        Kniha kniha2 = new Kniha("Slovensk� rozpr�vky" , 6.90 , Farba.CERVENA , "SK000001" , "Pavol Dob�insk�" , 85);
        Kniha kniha3 = new Kniha("Mal� princ" , 9.90 , Farba.ZLTA , "SK000021" , "Antoine" , 99);
        Kniha kniha4 = new Kniha("S�mrak" , 15.40 , Farba.MODRA , "SK564562" , "Stephanie Meyerov�" , 290);
        pozicovna.pridajKnihu(kniha1);
        pozicovna.pridajKnihu(kniha2);
        pozicovna.pridajKnihu(kniha3);
        pozicovna.pridajKnihu(kniha4);

        CD cd1 = new CD("Viano�n� koledy" , 2.99 , Farba.CERVENA , 42.30 , "SK12355" , "�birka");
        CD cd2 = new CD("Komorn� orchester" , 5.20 , Farba.ZLTA , 39.00 , "SK12345" , "Nezn�my");
        CD cd3 = new CD("Polemic" , 9.80 , Farba.PRIESVITNA , 42.20 , "SK123444" , "Polemix a spol");
        pozicovna.pridajCD(cd1);
        pozicovna.pridajCD(cd2);
        pozicovna.pridajCD(cd3);

        Casopis casopis1 = new Casopis("Nov� �as" , 3.20 , Farba.ZLTA , 42);
        Casopis casopis2 = new Casopis("Dennik N" , 2.40 , Farba.ZLTA , 47);
        pozicovna.pridajCasopis(casopis1);
        pozicovna.pridajCasopis(casopis2);
    }

    /**
     * Vypis menu len vyp�e nesledovne riadky
     */
    public void vypisMenu(){
        System.out.println("Zadaj podla ��sla �o si �el� vykona�: " +
                "\n1. Vyp� aktu�lne �lo�isko" +
                "\n2. Po�i�aj si predmet na indexe" +
                "\n3. Vyp� v�po�i�ky" +
                "\n4. Vr� predmet na indexe" +
                "\n5. Exit");

    }

    /**
     * Na za�iatku zavolame metodu inicializuj ktora naplni ulo�isko
     * exit nastavime na false a nesk�r zavolame cyklus while ktory bude fungova� pokial nebude exit true
     * vytvorime premennu typu int kde budeme zapisovat volbu zakaznika
     * sout vip�e vitaj + nazov po�i�ovne
     * cyklus while s podmienkou exit nastavenou na false
     * zavolame vypisanie menu
     * vytvorime si nov� scanner ktory budem mat pod premennou sc
     * volbu zakaznika zoberiem z sc.nextInt() a volozime ju do volby zakaznika , zakaznik uz videl menu tak�e vol� �o sa ma spravi�
     * vytvoril som switch a budeme switchova� volbu zakaznika
     * @case:1 v pozicovnyy zavola metodu pre vypisanie uloziska
     * @case:2 Ak bolo zadane �islo 2 tak chceme po�i�a� predmet pou�ivatela vyzvem na zadanie �isla indexu
     * zadane �islo ulo�im do volbyzakaznika a v podmienke si overim �e je �islo v rozsahu volkosti po�i�ovne
     * ak je to k tak zavolam metodu pre zakaznika pozicaj si predmet a na vstupe mu z pozicovne posuniem
     * predmet z pozicovne z daneho indexu tak�e predmet u� mame zapisany vo vypo�i�kach
     * nasledne zavolam pozicajSiPredmet aj pre pozicovnu a na vstupe dostane len volbu zakaznika takze
     * jej nastavime hodnotu pozicana na true
     * Else sa vykona len ak zadal pozivatel �islo mimo rozsah pozicovne
     * @case:3 v zakaznikovi zavolame vypisVypopzicky a len sa vyp�u v�po�i�ky
     * @case:4 vrati predmet , vyzveme zakaznika aby zadal �islo indexu z vypoziciek ktore chce cratit
     * vlozime volbu zakaznika do premennej a overime �i je zadane �islo v rozsahu vypoziciek ak ano pokracujeme
     * zavolam pozicovna.vratPredmet a na vstupe jej posuniem predmet na indexe z vypoziciek to sa dalej spracuje a porovna
     * aby program vedel ktory predmet sa vratil ( nasetuje ho na pozicany = false )
     * potom zavolam zakaznika.vratPredmet a na vstupe dostane predmet na indexe z vypoziciek ( v tejto metode
     * len vymazem predmet z vypoziciek tak ze mu nastavim hodnotu null)
     * @case:5 a @default ukon�ia program a vyp�u �e program bol ukon�en�
     */
    public void startPozicovna(){
            this.inicializuj();
            boolean exit = false;
            int volbaZakaznika;
            System.out.println("Vitaj v po�i�ovni " + pozicovna.getNazov());
            while (!exit){
                this.vypisMenu();
                Scanner sc = new Scanner(System.in);
                volbaZakaznika = sc.nextInt();
                switch (volbaZakaznika){
                    case 1 :
                        pozicovna.vypisAktualneUlozisko();
                        break;
                    case 2 :
                        System.out.println("Zadaj ��slo indexu na ktorom sa nach�dza predmet ktor� si chce� zapo�i�a�:");
                        volbaZakaznika = sc.nextInt();
                        if (volbaZakaznika <= pozicovna.getVelkostPozicovne() & volbaZakaznika > -1){
                        zakaznik.pozicajSiPredmet(pozicovna.getPredmetNaIndexe(volbaZakaznika));
                        pozicovna.pozicajPredmetZakaznikovi(volbaZakaznika);
                        }else System.out.println("Zadane ��slo je mimo rozsah pola");
                        break;
                    case 3 :
                        zakaznik.vypisVypozicky();
                        break;
                    case 4 :
                        System.out.println("Zadaj ��slo indexu s predmetom ktor� chce� aby sa z v�po�i�iek vr�til:");
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
        System.out.println("Program bol ukon�en�");
    }
}
