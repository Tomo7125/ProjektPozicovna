package predmety;

public class Casopis extends VypozicanyPredmet{

    /**
    Na za�iatku mame globalnu premennu
    Ni��ie najdeme kon�truktor s parametrami ktore dedi od rodi�a a prida sa pocet stran
    Ni��ie mame geter a seter pre pocet stran
     */
    private int pocetStran;

    public Casopis(String nazov, double cena, Farba farba, int pocetStran) {
        super(nazov, cena, farba);
        this.pocetStran = pocetStran;
    }

    public int getPocetStran() {
        return pocetStran;
    }

    public void setPocetStran(int pocetStran) {
        this.pocetStran = pocetStran;
    }
}
