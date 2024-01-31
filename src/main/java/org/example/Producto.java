package org.example;

import java.util.Date;
import java.util.Scanner;

public abstract class Producto {
    private int codigo;
    private static int cont = 0;
    private int stock;
    private double precio, rebaja;
    private String nombre, marca;
    private Date fecha;
    public Producto(String nombre, String marca, double precio, int stock, double rebaja){
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.rebaja = rebaja;
        this.fecha = null;
        codigo = ++cont;
    }

    public String getNombre() {
        System.out.println(nombre);
        return nombre;
    }

    public String getMarca() {
        System.out.println(marca);
        return marca;
    }

    public double getPrecio(){
        return precio;
    }
    public double getRebaja(){
        return rebaja;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }
}
