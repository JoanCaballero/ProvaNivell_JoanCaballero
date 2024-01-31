package org.example;

import java.util.Date;

public class Electrodomestico extends Producto{
    private int consumo, capacidad;
    private final static int GARANTIA = 18;
    private Date fabricacion;

    public Electrodomestico(String nombre, String marca, double precio, int stock, double rebaja, int consumo, int capacidad, Date fabricacion) {
        super(nombre, marca, precio, stock, rebaja);
        this.consumo = consumo;
        this.capacidad = capacidad;
        this.fabricacion = fabricacion;
    }

    public static int getGarantia(){
        return GARANTIA;
    }
}
