package pozicovna;

import entita.Zakaznik;
import pozicovna.Start;
import predmety.CD;
import predmety.Casopis;
import predmety.Farba;
import predmety.Kniha;

public class Main {
    public static void main(String[] args) {


        /**
        Vytv�rame novy objekt Start a potom zavolame jeho metodu start po�i�ovne.
         */

        Start start = new Start();
        start.startPozicovna();

    }
}