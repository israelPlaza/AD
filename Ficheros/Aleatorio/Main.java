import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    protected static int registroBuscado;
    protected static int datoMenu;
    protected static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Producto 1", 0, false, 'T'));
        productos.add(new Producto(2, "Producto 2", 0, false, 'E'));
        productos.add(new Producto(3, "Producto 3", 0, false, 'T'));
        productos.add(new Producto(4, "Producto 4", 0, false, 'E'));
        productos.add(new Producto(5, "Producto 5", 0, false, 'T'));

        // Escribir los productos en el fichero
        try (RandomAccessFile raf = new RandomAccessFile("/home/isra/CLASE/ADejemplo_raf.dat", "rw")) {
            for (Producto p : productos) {
                raf.writeInt(p.getId());
                StringBuilder sb = new StringBuilder(p.getNombre());
                sb.setLength(10);
                raf.writeChars(sb.toString());
                raf.writeDouble(p.getPrecio());
                raf.writeBoolean(p.isDescuento());
                raf.writeChar(p.getTipo());
            }
        } catch (Exception e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }

        // Leer el tercer registro
        try (RandomAccessFile raf = new RandomAccessFile("/home/isra/CLASE/ADejemplo_raf.dat", "r")) {
            registroBuscado = 3; // Buscar el tercer registro (índice 2 en lista)
            long posicion = (registroBuscado - 1) * Producto.getRecordSize();
            raf.seek(posicion);

            System.out.println("ID: " + raf.readInt());
            String nombre = "";
            for (int i = 0; i < 10; i++) {
                nombre += raf.readChar();
            }
            System.out.println("Nombre: " + nombre.trim());
            System.out.println("Precio: " + raf.readDouble());
            System.out.println("Descuento: " + raf.readBoolean());
            System.out.println("Tipo: " + raf.readChar());

        } catch (Exception e) {
            System.err.println("Error al leer del archivo: " + e.getMessage());
        }

        mostrarMenu();
        elegir(productos);
    }

    public static void mostrarMenu() {
        System.out.println("---------- Menú ----------");
        System.out.println("[1] Mostrar todos los productos");
        System.out.println("[2] Mostrar producto por ID");
        System.out.println("[3] Añadir producto");
        System.out.println("[4] Modificar producto");
        System.out.println("[5] Eliminar producto");
        System.out.println("[6] Salir");
    }

    public static void elegir(ArrayList<Producto> productos) {
        while (datoMenu != 6) {
            System.out.println("Escribe la opción que quieras:");
            datoMenu = sc.nextInt();
            switch (datoMenu) {
                case 1:
                    mostrarTodosProductos(productos);
                    break;
                case 6:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void mostrarTodosProductos(ArrayList<Producto> productos) {
        for (Producto p : productos) {
            System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre() +
                    ", Precio: " + p.getPrecio() + ", Descuento: " + p.isDescuento() + ", Tipo: " + p.getTipo());
        }
    }
}
