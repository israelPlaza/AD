import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class EstrellasHandler extends DefaultHandler {
    private final ArrayList<Estrella> estrellas = new ArrayList();
    private Estrella estrella;
    private StringBuilder buffer=new StringBuilder();

    public ArrayList<Estrella> getEstrellas() {
        return estrellas;
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch,start, length);
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName){
            case "nombre":
                estrella.setNombre(buffer.toString());
                break;
            case "tipo":
                estrella.setTipo(buffer.toString());
                break;
            case "magnitud":
                estrella.setMagnitud();
                break;
            case "grupo":
                estrella.getGrupo(buffer.toString());
        }
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch(qName){
            case "version":
                estrella = new Estrella();
                estrellas.add(estrella);
                estrella.setNumero(Double.parseDouble(attributes.getValue("numero")));
                break;
            case "nombre":
            case "tipo":
            case  "magnitud":
            case  "grupo":
                buffer.delete(0, buffer.length());
                break;
        }
    }
}
