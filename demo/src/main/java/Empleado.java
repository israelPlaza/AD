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
}