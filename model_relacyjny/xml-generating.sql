--ALTER SESSION SET NLS_NUMERIC_CHARACTERS = '. ';
--CREATE TABLE XML_ZAMOWIENIA(XML_CONTENT XMLTYPE);

--INSERT INTO XML_ZAMOWIENIA
SELECT XMLRoot(
XMLElement("ZAMOWIENIA",
  XMLAttributes(
    'http://www.moda4.com' AS "xmlns",
    'http://www.w3.org/2001/XMLSchema-instance' AS "xmlns:xsi",
    'http://www.moda4.com my_schema.xsd' AS "xsi:schemaLocation"
  ),
  XMLAgg(
    XMLElement("ZAMOWIENIE",
      XMLAttributes(
        Z.NR_ZAMOWIENIA AS "NR_ZAMOWIENIA",
        TO_CHAR(Z.DATA_ZAMOWIENIA, 'YYYY-MM-DD') AS "DATA_ZAMOWIENIA"
      ),
      XMLAgg(
        XMLElement("POZYCJA_ZAMOWIENIA",
          XMLAttributes(
            P.LP AS "LP",
            P.NR_OEM AS "NR_OEM",
            P.ILOSC AS "ILOSC"
          ),
          CASE WHEN POJ.NR_VIN IS NOT NULL THEN
          XMLElement("POJAZD",
            XMLAttributes(
              POJ.MARKA AS "MARKA",
              POJ.MODEL AS "MODEL",
              POJ.ROCZNIK AS "ROCZNIK",
              POJ.NR_VIN AS "NR_VIN"
            )
          ) END
        ) ORDER BY P.LP
      )
    ) ORDER BY Z.NR_ZAMOWIENIA
  ) 
), VERSION '1.0'
) AS XML_ZAMOWIENIA
FROM ZAMOWIENIE Z
JOIN POZYCJA_ZAMOWIENIA P ON Z.NR_ZAMOWIENIA = P.NR_ZAMOWIENIA
LEFT JOIN CZYNNOSC C ON P.ID_CZYNNOSCI = C.ID_CZYNNOSCI
LEFT JOIN KSIAZKA_SERWISOWA K ON C.ID_WIZYTY = K.ID_WIZYTY
LEFT JOIN POJAZD POJ ON K.NR_VIN = POJ.NR_VIN
GROUP BY Z.NR_ZAMOWIENIA, Z.DATA_ZAMOWIENIA;


--SELECT XMLIsValid(XML_CONTENT,'my_schema.xsd') AS IS_VALID
--FROM XML_ZAMOWIENIA;

--DROP TABLE XML_ZAMOWIENIA PURGE;