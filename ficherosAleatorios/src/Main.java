import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    protected static int registroBuscado;
    protected static int datoMenu;
    protected static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, false, "Producto 1", 100.0, false, 'T'));
        productos.add(new Producto(2, false, "Producto 2", 200.0, true, 'E'));
        productos.add(new Producto(3, false, "Producto 3", 150.0, false, 'T'));
        productos.add(new Producto(4, true, "Producto 4", 250.0, true, 'E'));
        productos.add(new Producto(5, false, "Producto 5", 300.0, false, 'T'));

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

            // Aquí agregamos 'false' como el valor para el campo 'borrado'
            long posicion = new Producto(registroBuscado, false, "", 0.0, false, ' ').getPosition();
            raf.seek(posicion);

            // Ahora puedes continuar con la lectura del archivo
            System.out.println(raf.readInt()); // Leer ID
            String nombre = "";
            for (int i = 0; i < 10; i++) {
                nombre += raf.readChar(); // Leer nombre
            }
            System.out.println(nombre.trim()); // Imprimir nombre
            System.out.println(raf.readDouble()); // Leer precio
            System.out.println(raf.readBoolean()); // Leer descuento
            System.out.println(raf.readChar()); // Leer tipo

        } catch (Exception e) {
            e.printStackTrace();
        }


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

    public static void elegir(ArrayList<Producto> productos) throws IOException {

        while (datoMenu != 6) {
            mostrarMenu();
            System.out.println("Escribe la opción que quieras:");
            datoMenu = sc.nextInt();
            switch (datoMenu) {
                case 1:
                    mostrarTodosProductos(productos);
                    break;
                case 2:
                    mostrarProductoPorId("/home/isra/CLASE/ADejemplo_raf.dat");

                    break;
                case 3:
                    anadirProducto(productos, "/home/isra/CLASE/ADejemplo_raf.dat");
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

    public static void mostrarProductoPorId(String filePath) {
        System.out.println("Escribe el número ID del producto que quieras buscar: ");
        int idBuscado = sc.nextInt();
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            int recordSize = 36; // Tamaño fijo de cada registro
            boolean encontrado = false;

            // Iterar por el archivo hasta encontrar el ID o llegar al final
            while (raf.getFilePointer() < raf.length()) {
                // Leer el ID
                int id = raf.readInt();
                boolean borrado = raf.readBoolean();

                // Si el registro está borrado, saltar al siguiente
                if (borrado) {
                    raf.skipBytes(recordSize - 5); // Restar el tamaño de los campos ya leídos
                    continue;
                }

                // Si encontramos el ID, leemos los otros campos y mostramos el producto
                if (id == idBuscado) {
                    encontrado = true;
                    String nombre = "";
                    for (int i = 0; i < 10; i++) {
                        nombre += raf.readChar();
                    }
                    double precio = raf.readDouble();
                    boolean descuento = raf.readBoolean();
                    char tipo = raf.readChar();

                    // Mostrar información del producto
                    System.out.println("ID: " + id);
                    System.out.println("Borrado: " + borrado);
                    System.out.println("Nombre: " + nombre.trim());
                    System.out.println("Precio: " + precio);
                    System.out.println("Descuento: " + descuento);
                    System.out.println("Tipo: " + tipo);
                    break;
                } else {
                    // Saltar al siguiente registro
                    raf.skipBytes(recordSize - 5); // Restar el tamaño de los campos ya leídos
                }
            }

            if (!encontrado) {
                System.out.println("Producto con ID " + idBuscado + " no encontrado.");
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }


    public static void anadirProducto(ArrayList<Producto> productos, String filePath) {
        Scanner sc = new Scanner(System.in);

        // Pedir datos al usuario
        System.out.println("Ingrese el ID del nuevo producto:");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar el salto de línea

        System.out.println("¿Está borrado? (true/false):");
        boolean borrado = sc.nextBoolean();

        sc.nextLine(); // Limpiar el salto de línea

        System.out.println("Ingrese el nombre del nuevo producto (máximo 10 caracteres):");
        String nombre = sc.nextLine();
        if (nombre.length() > 10) {
            nombre = nombre.substring(0, 10); // Limitar a 10 caracteres
        }

        System.out.println("Ingrese el precio del nuevo producto:");
        double precio = sc.nextDouble();

        System.out.println("¿Tiene descuento? (true/false):");
        boolean descuento = sc.nextBoolean();

        System.out.println("Ingrese el tipo de producto ('T' para tipo T o 'E' para tipo E):");
        char tipo = sc.next().charAt(0);

        // Crear y agregar el producto a la lista en memoria
        Producto nuevoProducto = new Producto(id, borrado, nombre, precio, descuento, tipo);
        productos.add(nuevoProducto);

        // Escribir el producto en el archivo binario
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            // Mover el cursor al final del archivo
            raf.seek(raf.length());

            // Escribir los datos del producto
            raf.writeInt(nuevoProducto.getId());
            raf.writeBoolean(nuevoProducto.isBorrado());
            StringBuilder sb = new StringBuilder(nuevoProducto.getNombre());
            sb.setLength(10);
            raf.writeChars(sb.toString());
            raf.writeDouble(nuevoProducto.getPrecio());
            raf.writeBoolean(nuevoProducto.isDescuento());
            raf.writeChar(nuevoProducto.getTipo());

            System.out.println("Producto añadido exitosamente.");

        } catch (IOException e) {
            System.err.println("Error al añadir el producto al archivo: " + e.getMessage());
        }
    }


}

