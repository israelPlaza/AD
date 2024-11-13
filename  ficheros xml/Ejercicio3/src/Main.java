import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.imageio.metadata.IIOMetadataNode;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    protected static Scanner sc= new Scanner(System.in);

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        int id=1;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementacion = builder.getDOMImplementation();
        Document documento = implementacion.createDocument(null, "Productos", null);

        Element raiz = documento.createElement("Estrella");
        documento.getDocumentElement().appendChild(raiz);

            elegirMenu();
            crearElemento("Nombre", Integer.toString(id), raiz, documento);
            crearElemento("Tamaño", Integer.toString(id), raiz, documento);
            crearElemento("Distancia", Integer.toString(id), raiz, documento);

        Source source = new DOMSource(documento);
        Result result = new StreamResult(new File("/home/isra/CLASE/AD/Estrellas.xml"));
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

    static void crearElemento(String datoEstrella, String valor, Element raiz, Document documento) {
        Element elem = documento.createElement(datoEstrella); //creamos hijo
        Text text = documento.createTextNode(valor); // damos valor al campo
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor
    }

    static void menu(){
        System.out.println("===============MENU===============");
        System.out.println("Marca [1] para una nueva estrella");
        System.out.println("Marca [2] para ver las estrellas");
        System.out.println("Marca [3] para salir");
        System.out.println("==================================");
    }

    static void elegirMenu() throws ParserConfigurationException, IOException, SAXException {
        int dato = 0;

        while (dato != 3) {
            menu();
            dato = sc.nextInt();
            seleccion(dato);
        }
    }

    static void seleccion(int dato) throws ParserConfigurationException, IOException, SAXException {

        switch (dato) {
            case 1:
                anadir();
                break;
            case 2:
                mostrar();
                break;
        }
    }

    static void mostrar() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File("/home/isra/CLASE/AD/Estrellas.xml"));
        doc.getDocumentElement().normalize();

        NodeList listaEstrellas = doc.getElementsByTagName("Estrella");

        for (int i = 0; i < listaEstrellas.getLength(); i++) {
            Node estrella = listaEstrellas.item(i);

            if (estrella.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("Estrella " + (i+1));
                System.out.println("==========");

                NodeList datosEstrella = estrella.getChildNodes();

                for (int j = 0; j < datosEstrella.getLength(); j++) {
                    Node dato = datosEstrella.item(j);

                    if (dato.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.print(dato.getNodeName() + ": ");
                        Node datoContenido = dato.getFirstChild();

                        if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                            System.out.println(datoContenido.getNodeValue());
                        }
                    }
                }
                System.out.println();
            }
        }
    }

    static void anadir(){

    }

}