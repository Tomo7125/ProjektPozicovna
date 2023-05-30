package predmety;

public class CD extends VypozicanyPredmet{
    /**
    Na za�iatku mame globalne premenne pre CD ako dlzka nahravky , seriove cislo , umelca
    potom tu mame kon�truktor aj s parametrami rodi�a a geteri + seteri
     */

    private double dlzkaNahravky;
    private String serioveCislo;
    private String umelec;

    public CD(String nazov, double cena, Farba farba, double dlzkaNahravky, String serioveCislo, String umelec) {
        super(nazov, cena, farba);
        this.dlzkaNahravky = dlzkaNahravky;
        this.serioveCislo = serioveCislo;
        this.umelec = umelec;
    }

    public double getDlzkaNahravky() {
        return dlzkaNahravky;
    }

    public void setDlzkaNahravky(double dlzkaNahravky) {
        this.dlzkaNahravky = dlzkaNahravky;
    }

    public String getSerioveCislo() {
        return serioveCislo;
    }

    public void setSerioveCislo(String serioveCislo) {
        this.serioveCislo = serioveCislo;
    }

    public String getUmelec() {
        return umelec;
    }

    public void setUmelec(String umelec) {
        this.umelec = umelec;
    }
}
