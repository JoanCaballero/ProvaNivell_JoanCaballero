package org.example.Products;

public class Belleza extends Producto {

    private boolean vegano;
    private String temporada;
    public Belleza(String nombre, String marca, double precio, int stock, double rebaja, boolean vegano, String temporada) {
        super(nombre, marca, precio, stock, rebaja);
        this.temporada = temporada;
        this.vegano = vegano;
    }
}
