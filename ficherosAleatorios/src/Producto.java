/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author isra
 */
public class Producto {
    public static int getPosition;
    private int id;
    private boolean borrado;
    private String nombre;
    private double precio;
    private boolean descuento;
    private char tipo;

    public Producto(int id, boolean borrado, String nombre, double precio, boolean descuento, char tipo) {
        this.id = id;
        this.borrado = borrado;
        this.nombre = nombre;
        this.precio = precio;
        this.descuento = descuento;
        this.tipo = tipo;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isDescuento() {
        return descuento;
    }

    public char getTipo() {
        return tipo;
    }

    public int length() {
        // return Integer.BYTES + Byte.BYTES + 10 * Character.BYTES + Double.BYTES + Byte.BYTES + Character.BYTES;//para simplificar, no se utiliza en este caso
        return 36;
    }

    public long getPosition() {
        return (id - 1) * length(); // El id comienza en 1, así que restamos 1
    }

    public boolean isBorrado() {
        return borrado;
    }

}
