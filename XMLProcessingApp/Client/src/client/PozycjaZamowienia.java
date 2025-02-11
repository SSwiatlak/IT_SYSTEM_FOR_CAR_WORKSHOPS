package client;

public class PozycjaZamowienia {
    private int lp;
    private double ilosc;
    private Pojazd pojazd;
    private Czesc czesc;

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public Pojazd getPojazd() {
        return pojazd;
    }
    
    public Czesc getCzesc() {
        return czesc;
    }

    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public void setCzesc(Czesc czesc) {
        this.czesc = czesc;
    }

    public double getIlosc() {
        return ilosc;
    }

    public void setIlosc(double ilosc) {
        this.ilosc = ilosc;
    }
}
