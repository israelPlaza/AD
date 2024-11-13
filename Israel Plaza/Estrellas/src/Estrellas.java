import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Estrellas {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        int id = 1;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementacion = builder.getDOMImplementation();
        Document documento = implementacion.createDocument((String) null, "Productos", (DocumentType) null);
        Element raiz = documento.createElement("Estrella");
        documento.getDocumentElement().appendChild(raiz);

        crearElemento("Nombre", Integer.toString(id), raiz, documento);
        crearElemento("Tamaño", Integer.toString(id), raiz, documento);
        crearElemento("Distancia", Integer.toString(id), raiz, documento);
        Source source = new DOMSource(documento);
        Result result = new StreamResult(new File("/home/isra/CLASE/AD/Estrellas.xml"));
        Transformer transformer;

        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException var12) {
            TransformerConfigurationException TransformerConfigurationException = var12;
            throw new RuntimeException();
        }

        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        try {
            transformer.transform(source, result);
        } catch (TransformerException var11) {
            TransformerException e = var11;
            throw new RuntimeException(e);
        }
        elegirMenu();
    }


    static void crearElemento(String datoProducto, String valor, Element raiz, Document documento) {
        Element elem = documento.createElement(datoProducto);
        Text text = documento.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }

    static void menu() {
        System.out.println("===============MENU===============");
        System.out.println("Marca [1] para una nueva estrella");
        System.out.println("Marca [2] para ver las estrellas");
        System.out.println("Marca [3] para salir");
        System.out.println("==================================");
    }

    public static void elegirMenu() throws ParserConfigurationException, IOException, SAXException {
        int dato = 0;

        while (dato != 3) {
            menu();
            dato = new Scanner(System.in).nextInt();
            seleccion(dato);
        }

    }

    static void seleccion(int dato) throws ParserConfigurationException, IOException, SAXException {
        switch (dato) {
            case 1:
                crear();
            case 2:
                mostrar();
            default:
                System.out.println("Saliendo....");
        }
    }

    private static void crear() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elije el nombre de la Estrella:");
        String nombre = scanner.nextLine();
        System.out.println("Elije el tamaño de la Estrella:");
        String tamaño = scanner.nextLine();
        System.out.println("Elije la distancia de la Estrella:");
        String distancia = scanner.nextLine();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("/home/isra/CLASE/AD/Estrellas.xml"));
            doc.getDocumentElement().normalize();
            Element raiz = doc.getDocumentElement();

            Element estrella = doc.createElement("Estrella");
            crearElemento("Nombre", nombre, estrella, doc);
            crearElemento("Tamaño", tamaño, estrella, doc);
            crearElemento("Distancia", distancia, estrella, doc);
            raiz.appendChild(estrella);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(doc), new StreamResult(new File("/home/isra/CLASE/AD/Estrellas.xml")));

            System.out.println("Estrella creada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear la estrella: " + e.getMessage());
        }
    }


    static void mostrar() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File("/home/isra/CLASE/AD/Estrellas.xml"));
        doc.getDocumentElement().normalize();
        NodeList listaEstrellas = doc.getElementsByTagName("Estrella");

        for (int i = 0; i < listaEstrellas.getLength(); ++i) {
            Node estrella = listaEstrellas.item(i);
            if (estrella.getNodeType() == 1) {
                System.out.println("Estrella " + (i + 1));
                System.out.println("==========");
                NodeList datosEstrella = estrella.getChildNodes();

                for (int j = 0; j < datosEstrella.getLength(); ++j) {
                    Node dato = datosEstrella.item(j);
                    if (dato.getNodeType() == 1) {
                        System.out.print(dato.getNodeName() + ": ");
                        Node datoContenido = dato.getFirstChild();
                        if (datoContenido != null && datoContenido.getNodeType() == 3) {
                            System.out.println(datoContenido.getNodeValue());
                        }
                    }
                }

                System.out.println();
            }
        }

    }
}
