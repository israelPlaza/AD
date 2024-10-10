
import java.io.File;
import java.util.Scanner;

public class ficheros {
  protected Scanner sc = new Scanner(System.in);   
  protected int dato=0;
    public static void main(String[] args) {
     
    File nuevoFichero= new File("/home/isra/CLASE/AD/directorioPrueba/pruebaFichero.txt");
    File miDirectorio=new File("/home/isra/CLASE/AD/directorioPrueba");
    miDirectorio.mkdir();
   
    
    try {
        if (nuevoFichero.createNewFile()) {
            System.out.println("el fichero exite");
        }else{
            System.out.println("No existe");
        } 

    } catch (Exception e) {
  }
  
  if (nuevoFichero.isFile()) {
    System.out.println(nuevoFichero.getName() + " es un fichero");
    }
  if (miDirectorio.isDirectory()) {
    System.out.println(miDirectorio.getName() + " es un directorio");
    }

    //Instanciamos la clase file con la ruta del fichero
File miFichero = new File("pruebaFichero.txt");

//Comprobamos los permisos de lectura
if (miFichero.canRead()) {
    System.out.println(miFichero.getName() + " tiene permisos de lectura");
}else {
    System.out.println(miFichero.getName() + " NO tiene permisos de lectura");
}
//Comprobamos los permisos de escritura
if (miFichero.canWrite()) {
    System.out.println(miFichero.getName() + " tiene permisos de escritura");
}else {
    System.out.println(miFichero.getName() + " NO tiene permisos de escritura");
}
//Comprobamos los permisos de ejecución
if (miFichero.canExecute()) {
    System.out.println(miFichero.getName() + " tiene permisos de ejecución");
}else {
    System.out.println(miFichero.getName() + " NO tiene permisos de ejecución");
}

//Modificamos los permisos
miFichero.setExecutable(false);
miFichero.setReadable(false);
miFichero.setWritable(false);

//Mostramos los permisos
if (!miFichero.canExecute()) {
    System.out.println(miFichero.getName() + " no tiene permisos de ejecución");
}
if (!miFichero.canRead()) {
    System.out.println(miFichero.getName() + " no tiene permisos de lectura");
}

if (!miFichero.canWrite()) {
System.out.println(miFichero.getName() + " no tiene permisos de escritura");
}

mostramosLista();
mostarMenu();

    }
public static void mostramosLista(){
    File muestraDir = new File("/home/isra/CLASE/AD/");
    File[] ficheros= muestraDir.listFiles();

    for (File f : ficheros) {
        if (f.isFile()) {
            System.out.println(f.getName() + " es un fichero");
        }
        if (f.isDirectory()) {
            System.out.println(f.getName()+" es un directorio");
        }
    }
}
public  int mostarMenu() {
    
        System.out.println("----------------------------------------------------");
        System.out.println("                       MENU                         ");
        System.out.println("----------------------------------------------------");
        System.out.println("1. Mostrar ficheros del directorio actual");
        System.out.println("2. Modificar el directorio actual (añade al path actual el directorio introducido)");
        System.out.println("3. Volver atrás");
        System.out.println("4. Salir del programa");
        System.out.println();
        System.out.println("¿ Qué opción quieres realizar ?");
        return sc.nextInt();
    
    }

}
