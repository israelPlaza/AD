import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException {
        int id=1;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementacion = builder.getDOMImplementation();
        Document documento = implementacion.createDocument(null, "Productos", null);

        Element raiz = documento.createElement("producto");
        documento.getDocumentElement().appendChild(raiz);

            while (id<3) {
            crearElemento("ID", Integer.toString(id), raiz, documento);
            id++;
            }
        Source source = new DOMSource(documento);
        Result result = new StreamResult(new File("/home/isra/CLASE/AD/ ficheros xml/EscrituraXML/pruebas/Productos.xml"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Ajusta la indentación
        try {
            transformer.transform(source,result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }
    static void crearElemento(String datoProducto, String valor, Element raiz, Document documento) {
        Element elem = documento.createElement(datoProducto); //creamos hijo
        Text text = documento.createTextNode(valor); // damos valor al campo
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor
    }
}