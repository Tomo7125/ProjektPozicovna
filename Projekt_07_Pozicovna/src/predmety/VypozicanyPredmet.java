package predmety;

import entita.Zakaznik;

public abstract class VypozicanyPredmet {

    protected String nazov;
    protected double cena;
    protected Farba farba;
    private boolean zapozicany = false;

/**
     Na za�iatku je kon�truktor s parametramy nazov , cena a farba. Hodnota zapo�i�any je od za�iatku nastavena na false
     �alej tu mame len geteri a seteri pre globalne premenne
 */
    public VypozicanyPredmet(String nazov, double cena, Farba farba) {
        this.nazov = nazov;
        this.cena = cena;
        this.farba = farba;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public double getCena() {
        return cena;
    }

    public boolean isZapozicany() {
        return zapozicany;
    }

    public void setZapozicany(boolean zapozicany) {
        this.zapozicany = zapozicany;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Farba getFarba() {
        return farba;
    }

    public void setFarba(Farba farba) {
        this.farba = farba;
    }

    /**
    Tato metoda len zmeni hodnotu �i je predmet zapo�i�any na true
    Metodu je potrebne aplikovat na konkretny predmet
     */
    public boolean zapozicajPredmet(){
        this.setZapozicany(true);

        return true;
    }

    /**
    Tato metoda zmeni hodnotu predmetu na false tak�e je predmet znovu dostupny taktie� je tuto metodu potrebne aplikova� na konkretny predmet
     */
    public void vratPredmet(){
        this.setZapozicany(false);
    }

}
