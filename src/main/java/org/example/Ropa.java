package org.example;

import java.util.Date;

public class Ropa extends Producto{
    private String tejido, tipo, talla;

    public Ropa(String nombre, String marca, double precio, int stock, double rebaja, String tejido, String tipo, String talla) {
        super(nombre, marca, precio, stock, rebaja);
        this.tejido = tejido;
        this.tipo = tipo;
        this.talla = talla;
    }
}
