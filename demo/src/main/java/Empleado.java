import java.io.*;
import java.util.Scanner;

public class Empleado {
    private int id;      // Hacer las variables privadas para cumplir con el encapsulamiento
    private String nombre;

    // Constructor
    public Empleado(int id, String nombre) {
        this.id = id;           // Asignamos los valores de los parámetros a los atributos de la clase
        this.nombre = nombre;
    }
    public void mostrarInfo() {
        System.out.println("ID: " + id + ", Nombre: " + nombre);
    }
    // Getter para 'id'
    public int getId() {
        return id;
    }

    // Setter para 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter para 'nombre'
    public String getNombre() {
        return nombre;
    }

    // Setter para 'nombre'
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static class FicheroSecuencial {

        public static void main(String[] args) {
            // Creación de directorio y archivo
            File nuevoFichero = new File("./pruebaFicheroPermiso.txt");
            File miDirectorio = new File("./directorioPrueba");
            miDirectorio.mkdir();

            try {
                // Verificar si el archivo fue creado o si ya existe
                if (nuevoFichero.createNewFile()) {
                    System.out.println("El fichero existe");
                } else {
                    System.out.println("No existe");
                }
            } catch (Exception e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }

            // Verificar si es un archivo o un directorio
            if (nuevoFichero.isFile()) {
                System.out.println(nuevoFichero.getName() + " es un fichero");
            }
            if (miDirectorio.isDirectory()) {
                System.out.println(miDirectorio.getName() + " es un directorio");
            }

            // Comprobación de permisos del archivo
            File miFichero = new File("pruebaFichero.txt");
            verificarPermisos(miFichero);

            // Modificación de permisos
            miFichero.setExecutable(false);
            miFichero.setReadable(false);
            miFichero.setWritable(false);

            // Mostrar los permisos después de la modificación
            verificarPermisos(miFichero);

            // Llamadas a otras funciones para demostrar funcionalidades
            mostramosLista();
            mostrarEscritura();
            mostrarArray();
            ficheroBinario();
            menu();
        }

        // Método para verificar permisos de lectura, escritura y ejecución
        public static void verificarPermisos(File fichero) {
            if (fichero.canRead()) {
                System.out.println(fichero.getName() + " tiene permisos de lectura");
            } else {
                System.out.println(fichero.getName() + " NO tiene permisos de lectura");
            }

            if (fichero.canWrite()) {
                System.out.println(fichero.getName() + " tiene permisos de escritura");
            } else {
                System.out.println(fichero.getName() + " NO tiene permisos de escritura");
            }

            if (fichero.canExecute()) {
                System.out.println(fichero.getName() + " tiene permisos de ejecución");
            } else {
                System.out.println(fichero.getName() + " NO tiene permisos de ejecución");
            }
        }

        // Listar todos los archivos y directorios en una ruta específica
        public static void mostramosLista() {
            File muestraDir = new File("/home/israel/CLASE/AD");
            File[] ficheros = muestraDir.listFiles();

            if (ficheros != null) {
                for (File f : ficheros) {
                    if (f.isFile()) {
                        System.out.println(f.getName() + " es un fichero");
                    }
                    if (f.isDirectory()) {
                        System.out.println(f.getName() + " es un directorio");
                    }
                }
            } else {
                System.out.println("El directorio no existe o no contiene archivos.");
            }
        }

        // Escritura en un archivo a partir de la entrada del usuario
        public static void mostrarEscritura() {
            Scanner sc = new Scanner(System.in);
            File nuevoFichero = new File("/home/israel/CLASE/AD/directorioPrueba/pruebaFichero.txt");
            System.out.println("Introduzca una cadena de caracteres:");
            String cadena = "Hola mundo";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nuevoFichero))) {
                writer.write(cadena);
                writer.write("*");
            } catch (Exception e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
            sc.close();
        }

        // Escritura de un array de cadenas en un archivo
        public static void mostrarArray() {
            File nuevoFichero = new File("/home/israel/CLASE/AD/pruebaCadena.txt");
            String[] arrayCadenas = {"Carl Marx", "La Pasionaria", "Vladimir Ilich Ulianov"};

            System.out.println("Escribiendo cadenas en el archivo...");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nuevoFichero))) {
                for (String cadena : arrayCadenas) {
                    writer.write(cadena);
                    writer.newLine();
                }
            } catch (Exception e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }

        public static void leerCadena() { //Me falta
            File nuevoFichero = new File("/home/israel/CLASE/AD/pruebaCadena.txt");
            String[] arrayCadenas = {"Carl Marx", "La Pasionaria", "Vladimir Ilich Ulianov"};

            System.out.println("Escribiendo cadenas en el archivo...");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nuevoFichero))) {
                for (String cadena : arrayCadenas) {
                    writer.write(cadena);
                    writer.newLine();
                }
            } catch (Exception e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }

        public static void ficheroBinario() {
            Empleado[] empleados={
                    new Empleado(1,"Car Sagan"),
                    new Empleado(2, "Albert Estein")
            };

            String nombreArchivo="/home/israel/CLASE/AD/ficheroBin.bin";
            try (DataOutputStream dos= new DataOutputStream(new FileOutputStream(nombreArchivo))){
                for (Empleado empleado : empleados) {
                    empleado.mostrarInfo();
                    dos.write(empleado.getId());
                    dos.writeUTF(empleado.getNombre());
                }
            } catch (Exception e) {
            }
        }

        public static void menu(){
            Scanner sc = new Scanner(System.in);

            System.out.println("**************************");
            System.out.println("*********MENU*************");
            System.out.println("**************************");
            System.out.println("[1] Añadir Empleado");
            System.out.println("[2] Editar Empleado");
            System.out.println("[0]     salir");


        }

    }
}