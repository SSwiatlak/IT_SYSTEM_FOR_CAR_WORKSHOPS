DECLARE
  l_schema  CLOB;
BEGIN
  -- Define your XML schema as a CLOB
  l_schema := '<?xml version="1.0" encoding="windows-1252"?>
<xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://www.moda4.com"
            targetNamespace="http://www.moda4.com" elementFormDefault="qualified"
            xmlns:xdb="http://xmlns.oracle.com/xdb">
  <xsd:element name="ZAMOWIENIA">
    <xsd:annotation>
      <xsd:documentation>A sample element</xsd:documentation>
    </xsd:annotation>
    <xsd:complexType>
      <xsd:sequence>
        <xsd:element name="ZAMOWIENIE" maxOccurs="unbounded">
          <xsd:complexType>
            <xsd:sequence maxOccurs="unbounded">
              <xsd:element name="POZYCJA_ZAMOWIENIA">
                <xsd:complexType>
                  <xsd:choice minOccurs="0" maxOccurs="1">
                    <xsd:element name="POJAZD">
                      <xsd:complexType>
                        <xsd:attribute name="MARKA" use="required" type="xsd:string"/>
                        <xsd:attribute name="MODEL" use="required" type="xsd:string"/>
                        <xsd:attribute name="ROCZNIK" use="required" type="xsd:gYear"/>
                        <xsd:attribute name="NR_VIN" use="optional" type="VIN"/>
                      </xsd:complexType>
                    </xsd:element>
                  </xsd:choice>
                  <xsd:attribute name="LP" use="required" type="xsd:integer"/>
                  <xsd:attribute name="NR_OEM" type="OEM" use="required"/>
                  <xsd:attribute name="ILOSC" use="required" type="xsd:decimal"/>
                </xsd:complexType>
              </xsd:element>
            </xsd:sequence>
            <xsd:attribute name="NR_ZAMOWIENIA" use="required" type="xsd:string"/>
            <xsd:attribute name="DATA_ZAMOWIENIA" use="required" type="xsd:date"/>
          </xsd:complexType>
        </xsd:element>
      </xsd:sequence>
    </xsd:complexType>
  </xsd:element>
  <xsd:simpleType name="OEM">
    <xsd:restriction base="xsd:string">
      <xsd:maxLength value="30"/>
    </xsd:restriction>
  </xsd:simpleType>
  <xsd:simpleType name="VIN">
    <xsd:restriction base="xsd:string">
      <xsd:length value="17"/>
    </xsd:restriction>
  </xsd:simpleType>
</xsd:schema>';

  -- Register the schema in the Oracle database
  DBMS_XMLSCHEMA.registerSchema(
    schemaurl       => 'my_schema.xsd', 
    schemadoc       => l_schema,
    local           => TRUE,
    gentypes        => FALSE,
    gentables       => FALSE,
    enablehierarchy => DBMS_XMLSCHEMA.enable_hierarchy_none
  );
END;
/
