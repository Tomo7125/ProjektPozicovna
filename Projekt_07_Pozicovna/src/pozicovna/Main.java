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
        Vytvárame novy objekt Start a potom zavolame jeho metodu start požièovne.
         */

        Start start = new Start();
        start.startPozicovna();

    }
}