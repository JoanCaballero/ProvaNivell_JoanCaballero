package org.example;

import java.util.Date;

public class Componente extends Producto{

    private int resolucion, bateria;
    private final static int GARANTIA = 9;

    public Componente(String nombre, String marca, double precio, int stock, double rebaja, int resolucion, int bateria) {
        super(nombre, marca, precio, stock, rebaja);
        this.resolucion = resolucion;
        this.bateria = bateria;
    }

    public static int getGarantia(){
        return GARANTIA;
    }
}
