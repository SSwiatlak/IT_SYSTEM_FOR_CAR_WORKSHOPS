package client;

import java.util.ArrayList;
import java.util.List;

public class Zamowienie {
    private String nrZamowienia;
    private String dataZamowienia;
    private List<PozycjaZamowienia> pozycjeZamowienia = new ArrayList<>();

    public String getNrZamowienia() {
        return nrZamowienia;
    }

    public void setNrZamowienia(String nrZamowienia) {
        this.nrZamowienia = nrZamowienia;
    }

    public String getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(String dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public List<PozycjaZamowienia> getPozycjeZamowienia() {
        return pozycjeZamowienia;
    }

    public void addPozycjaZamowienia(PozycjaZamowienia pozycja) {
        pozycjeZamowienia.add(pozycja);
    }
}
