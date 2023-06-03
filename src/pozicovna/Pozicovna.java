package pozicovna;

import com.sun.istack.internal.NotNull;
import predmety.CD;
import predmety.Casopis;
import predmety.Kniha;
import predmety.VypozicanyPredmet;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Pozicovna {
    /** globalna premenna nazov a geter pre nazov. */

    private String nazov;

    public String getNazov() {
        return nazov;
    }

    /**
    Tu vytvarame ulo�i�ko  / pole typu VypozicanyPredmet
    �alej si u�ujeme max pocet pre knihy , CD , casopisy a aj aktualnePocty
     */
    private VypozicanyPredmet[] ulozisko;
    private int maxPocetKnih;
    private int maxPocetCD;
    private int maxPocetCasopisov;
    private int aktualnyPocetKnih;
    private int aktualnyPocetCD;
    private int aktualnyPocetCasopisov;

    /** Kon�truktor pre po�i�ov�u */
    public Pozicovna(String nazov) {
        this.nazov = nazov;
    }

    /** Metoda vytvorPo�i�ov�u aplikujeme na vytvorenu po�i�ov�u a priradime jej max po�ty pre dane predmety
    / S�itame max po�ty a ur�ime jej touto hodnotou velkos� ulo�iska a nastavime aktualne po�ty na 0 */
    public void vytvorPozicovnu(int maxPocetKnih , int maxPocetCasopisov , int maxPocetCD){
        this.maxPocetKnih = maxPocetKnih;
        this.maxPocetCasopisov = maxPocetCasopisov;
        this.maxPocetCD = maxPocetCD;
        this.ulozisko = new VypozicanyPredmet[maxPocetCasopisov + maxPocetCD + maxPocetKnih];
        this.aktualnyPocetCD = 0;
        this.aktualnyPocetCasopisov = 0;
        this.aktualnyPocetKnih = 0;
    }

/**
    Metoda ma navratovy typ boolean , v prvej podmienke overim �� je aktualny po�et kn�h men�� ako max po�et knih
    ak �no pokra�ujem v bloku
    vytvoril som pomocnu int s nazvom "pom" a priradil som po�et nula preto�e aj index pola ktory budem prehladavat za�ina nulou
    for each prehladava ulozisko a uklada data do premennej "v"
    v podmienke porovnavam "v" s hodnotou null ( hodnota null mi hovori �e pole je pr�zdne )
    akon�hle je hodnota null tak podmienka je pravdiva a vch�dzame do vn�tra kde na ulo�isko s indexom "pom" vlo�ime knihu
    premennu "pom" som navy�oval pri ka�dom prejdeni cyklu for each ke� hodnota "v" nebola null
    po zapisani novej knihy do uloziska dalsi riadok kodu nav��i hodnotu aktualnypocetknih o 1 a potom vr�ti true
    pokial prejde cyklus for each do konca a nenajde volne pole kod zbehne na koniec kde je retur false
 */
    public boolean pridajKnihu(Kniha novaKniha){
        if (aktualnyPocetKnih < maxPocetKnih){
            int pom = 0;
            for (VypozicanyPredmet v : ulozisko){
                if (v == null){
                    ulozisko[pom] = novaKniha;
                    aktualnyPocetKnih++;
                    return true;
                }
                pom++;
            }
        }
        /** Ak vrati false knihu nebolo mo�n� prida� z d�vodu �e bol presiahnuty maximalny po�et knih
         alebo je ulo�isko plne */
        return false;
    }

    /** rovnaky postup ako v popise pridaj knihu */
    public boolean pridajCasopis(Casopis novyCasopis){
        if (aktualnyPocetCasopisov < maxPocetCasopisov){
            int pom =0;
            for (VypozicanyPredmet v : ulozisko){
                if (v == null){
                    ulozisko[pom] = novyCasopis;
                    aktualnyPocetCasopisov++;
                    return true;
                }
                pom++;
            }
        }
        return false;
    }

    /** rovnaky postup ako v popise pridaj knihu */
    public boolean pridajCD(CD noveCD){
        if (aktualnyPocetCD < maxPocetCD){
            int pom = 0;
            for (VypozicanyPredmet v : ulozisko){
                if (v == null){
                    ulozisko[pom] = noveCD;
                    aktualnyPocetCD++;
                    return true;
                }
                pom++;
            }
        }
        return false;
    }
    /**
    Metoda na vstupe prijme ��slo index a nasledne v podmienke if skontroluje �i na ulo�isku s dan�m indexom nieje null
    Ak plat� != �i�e dane pole nieje null tak pokra�ujeme dalej
    Na ulozisko[index] aplikujeme metodu zapo�i�aj predmet
     */
    public boolean pozicajPredmetZakaznikovi(int index){
        if (ulozisko[index] != null){
            ulozisko[index].zapozicajPredmet();
            return true;
        }
        return false;
    }
    /**
    Dos� podobn� postup ako v predo�lej metode no tu porovnavame vstupny parameter nazov
    V podmienke if skontrolujeme �i ulo�isko nieje prazdne aby nam nepadol program na NullPointerException
    Nasledne prehladavame v cykle for each pole a ukladame objekty do "v"
    V �al�iom if si porovnavame nazov z objektu s nazvom zo vstupneho parametru ak sa zhoduju zavolame metodu zapozicajPredmet
     */
    public boolean pozicajPredmetZakaznikovi(String nazovPredmetu){
        if (ulozisko != null){
            for (VypozicanyPredmet v : ulozisko){
                if (v.getNazov().equals(nazovPredmetu)){
                    v.zapozicajPredmet();
                    return true;
                }
            }
        }
        return false;
    }
    /**
    Nov�ie verzie javy poznaju aj Arrays.isEmpty() no vzhladom na pou�itie javy 1.8 nieje mo�ne pou�i� tuto funkciu
    vytvorim preto cyklus ktory overy �i su polia prazdne ak ano nap�e sa mi na konzolu hla�ka �e ulozisko je prazdne
    funkcia je zbyto�n� vzhladom na to �e na za�iatku si inicializujeme kni�nicu ale keby sme mali aplikaciu
    postavenu tak �e zadavame kni�nicu ru�ne m��e sa sta� �e bude prazdna
    Ni��ie si v cykle for prehladavame ulo�isko dal by sa pou�i� aj cyklus for each no lep�ie sa mi pracuje s klasickym cyklom for
    tak pou�ivam for each len zriedka kym si na� zvyknem
    Pokial ulozisko[i] nieje null tak sa mi ulo�� v dal�iom riadku predmet do pomocnej premennej predmet ktora
    by tam nemusela by� nakolko m��em metody aplikova� aj �t�lom ulozisko[i].getNazov() at� no ope� som bol lenivej�� a ulah�il si pisanie
    nakolko je pohodlnej�ie len �uknu� po prvom pismene enter a pokra�ova� metodou
    sout mam nastaveny tak aby mi na za�iatku napisal �islo indexu potom meno classu aby som vedel o aky predmet sa jedna
    nasledne jeho zakladne atrinbuty ako nazov , cena , farba a �i je aktualne zapo�i�an�
     */
    public void vypisAktualneUlozisko(){
        boolean jePrazdne = true;

        for (int i = 0; i < ulozisko.length; i++) {
            if (ulozisko[i] != null) {
                jePrazdne = false;
                break;
            }
        }
   /**     if (Arrays.isEmpty(ulozisko)){   Toto by bolo mo�ne v nov��ch verziach javy */
        if (jePrazdne){
            System.out.println("�lo�isko je pr�zdne");
        }else {
            System.out.println("V �lo�isku sa nach�dzaj� n�sledovne predmety :");
            for (int i = 0;  i < ulozisko.length ; i++){
                if (ulozisko[i] != null){
                    VypozicanyPredmet predmet = ulozisko[i];
                    System.out.println("[" + (i) + "]" + " Predmet : " + predmet.getClass().getSimpleName() +
                            " --- N�zov : " + predmet.getNazov() + ", Cena: " + predmet.getCena() + ", Farba : "  + predmet.getFarba() +
                             ", predmet je aktualne " + (predmet.isZapozicany() ? "zapo�i�an�" : "voln� k zapo�i�aniu"));
                }
            }
        }
    }

    /**
    Tato metoda vracia predmet na indexe ktory pri�iel ako vstupny parameter navratova hodnota je VypozicanyPredmet
    Sl��i na vybratie predmetu a pracu s nim
     */
    public VypozicanyPredmet getPredmetNaIndexe(int index){

        return this.ulozisko[index];
    }
    /**
    Vstupny parameter je predmet ktory budem vracat
    nasledne si v cykle for prejdem ulo�isko a hladam v podmienke if kedy bude predmet v ulozisku rovnak� ako predmet na vstupe
    pokial sa bude zhodova� predmet v ulo�isku a predmet na vstupe tak mu nasetujem hodnotu zapozicany na false tak�e bude
    ope� dotupn�
     */
    public void vratPredmet(VypozicanyPredmet predmet){
        if (predmet != null){
        for (int i = 0 ; i < ulozisko.length ; i++) {

            if (ulozisko[i] == predmet) {
                ulozisko[i].setZapozicany(false);
                System.out.println("Predmet bol vr�ten�");
            }
        }
        }else System.out.println("Pole na indexe je prazdne");
    }
    /**
    Tuto metodu som pou�il ako pomocnu pri rie�eni problemov na switch v niektorom z case:
    vracia len dlzku uloziska nakolko ku nej neviem pristupovat z inych tried bez pouzitia takehoto geteru
     */
    public int getVelkostPozicovne(){
        int i = ulozisko.length;
        return i;
    }
}
