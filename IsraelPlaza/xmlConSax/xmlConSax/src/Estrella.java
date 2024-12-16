public class Estrella {
    private double numero;
    private String nombre;
    private String tipo;
    private double magnitud;
    private String grupo;

    public Estrella() {
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Estrella{" +
                "numero=" + numero +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", magnitud=" + magnitud +
                ", grupo='" + grupo + '\'' +
                '}';
    }
}
