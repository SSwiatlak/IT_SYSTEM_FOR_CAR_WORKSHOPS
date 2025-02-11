package client;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class ZamowieniaHandler extends DefaultHandler {
    private Main zamowienia;
    private Zamowienie currentZamowienie;
    private PozycjaZamowienia currentPozycja;

    // Mapy do wspó³dzielenia obiektów
    private Map<String, Pojazd> pojazdy = new HashMap<>();
    private Map<String, Czesc> czesci = new HashMap<>();

    public Main getZamowienia() {
        return zamowienia;
    }
    
    public Map<String, Pojazd> getPojazdy() {
        return pojazdy;
    }

    public Map<String, Czesc> getCzesci() {
        return czesci;
    }

    @Override
    public void startDocument() throws SAXException {
        zamowienia = new Main();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "ZAMOWIENIE":
                currentZamowienie = new Zamowienie();
                currentZamowienie.setNrZamowienia(attributes.getValue("NR_ZAMOWIENIA"));
                currentZamowienie.setDataZamowienia(attributes.getValue("DATA_ZAMOWIENIA"));
                break;

            case "POZYCJA_ZAMOWIENIA":
                currentPozycja = new PozycjaZamowienia();
                currentPozycja.setLp(Integer.parseInt(attributes.getValue("LP")));
                currentPozycja.setIlosc(Double.parseDouble(attributes.getValue("ILOSC")));

                // Pobierz numer OEM i znajdŸ istniej¹c¹ czêœæ lub utwórz now¹
                String nrOem = attributes.getValue("NR_OEM");
                Czesc czesc = czesci.computeIfAbsent(nrOem, k -> {
                    Czesc newCzesc = new Czesc();
                    newCzesc.setNrOem(k);
                    return newCzesc;
                });
                // Przeka¿ zarówno obiekt Czesc, jak i numer OEM
                currentPozycja.setCzesc(czesc);
                break;

            case "POJAZD":
                // Pobierz numer VIN i znajdŸ istniej¹cy pojazd lub utwórz nowy
                String nrVin = attributes.getValue("NR_VIN");
                Pojazd pojazd = pojazdy.computeIfAbsent(nrVin, k -> {
                    Pojazd newPojazd = new Pojazd();
                    newPojazd.setNrVin(k);
                    newPojazd.setMarka(attributes.getValue("MARKA"));
                    newPojazd.setModel(attributes.getValue("MODEL"));
                    newPojazd.setRocznik(attributes.getValue("ROCZNIK"));
                    return newPojazd;
                });
                currentPozycja.setPojazd(pojazd);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "ZAMOWIENIE":
                zamowienia.addZamowienie(currentZamowienie);
                break;
            case "POZYCJA_ZAMOWIENIA":
                currentZamowienie.addPozycjaZamowienia(currentPozycja);
                break;
        }
    }
}

