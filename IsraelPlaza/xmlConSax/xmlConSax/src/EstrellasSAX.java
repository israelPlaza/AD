import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EstrellasSAX {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        File file = new File("/home/isra/CLASE/AD/IsraelPlaza/xmlConSax/galaxias.xml");
        EstrellasHandler handler = new EstrellasHandler();
        saxParser.parse(file, handler);
        ArrayList<Estrella> versiones = handler.getEstrellas();
        for (Estrella v : versiones){
            System.out.println(v);
        }
    }

}