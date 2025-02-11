package client;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XMLValidator {
    public static ZamowieniaHandler parseAndValidateXml(File xmlFile, File xsdFile) {
        try {
            // Load and validate XSD schema
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsdFile);

            // Create SAX Parser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setSchema(schema);
            SAXParser saxParser = factory.newSAXParser();

            // Parse XML file
            ZamowieniaHandler handler = new ZamowieniaHandler();
            saxParser.parse(xmlFile, handler);

            return handler;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
}

