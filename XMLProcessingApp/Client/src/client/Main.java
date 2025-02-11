package client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File xmlFile = new File("C:\\Users\\sebas\\Documents\\STUDIA\\XMLProcessingApp\\XML_files\\XMLMODA4.xml");
        File xsdFile = new File("C:\\Users\\sebas\\Documents\\STUDIA\\XMLProcessingApp\\XML_files\\MODA4_XMLSchema.xsd");

        // Parsowanie XML
        ZamowieniaHandler handler = XMLValidator.parseAndValidateXml(xmlFile, xsdFile);
        if (handler != null) {
            // Pobieranie zamówieñ
            Main zamowienia = handler.getZamowienia();

            // Wypisywanie zamówieñ
            for (Zamowienie zamowienie : zamowienia.getZamowienia()) {
                System.out.println("Order Number: " + zamowienie.getNrZamowienia());
                System.out.println("Order Date: " + zamowienie.getDataZamowienia());

                for (PozycjaZamowienia pozycja : zamowienie.getPozycjeZamowienia()) {
                    System.out.println("  Position: " + pozycja.getLp());
                    
                    Czesc czesc = pozycja.getCzesc();
                    System.out.println("    NrOem:  " + czesc.getNrOem());
                    System.out.println("    Ilosc:  " + pozycja.getIlosc());

                    Pojazd pojazd = pozycja.getPojazd();
                    if (pojazd != null) {
                        System.out.println("    Vehicle Make: " + pojazd.getMarka());
                        System.out.println("    Model: " + pojazd.getModel());
                        System.out.println("    Year: " + pojazd.getRocznik());
                        System.out.println("    VIN: " + pojazd.getNrVin());
                    }
                }
            }

            // Wypisywanie unikalnych pojazdów
            System.out.println("\nUnique Vehicles:");
            handler.getPojazdy().values().forEach(pojazd -> {
                System.out.println("VIN: " + pojazd.getNrVin() +
                        ", Make: " + pojazd.getMarka() +
                        ", Model: " + pojazd.getModel() +
                        ", Year: " + pojazd.getRocznik());
            });

            // Wypisywanie unikalnych czêœci
            System.out.println("\nUnique Parts:");
            handler.getCzesci().values().forEach(czesc -> {
                System.out.println("OEM Number: " + czesc.getNrOem());
            });
        } else {
            System.out.println("Failed to parse and validate XML.");
        }
    }
    private List<Zamowienie> zamowienia = new ArrayList<>();

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void addZamowienie(Zamowienie zamowienie) {
        zamowienia.add(zamowienie);
    }
}

