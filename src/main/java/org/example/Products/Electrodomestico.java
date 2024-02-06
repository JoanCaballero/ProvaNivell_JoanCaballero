package org.example.Products;

import org.example.ConsoleManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Electrodomestico extends Producto implements Garantia {
    private int consumo, capacidad;
    private final static int GARANTIA = 18;
    private Date fabricacion;
    private static String pattern = "yyyy-MM-dd";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public Electrodomestico(String nombre, String marca, double precio, int stock, double rebaja, int consumo, int capacidad, Date fabricacion) {
        super(nombre, marca, precio, stock, rebaja);
        this.consumo = consumo;
        this.capacidad = capacidad;
        this.fabricacion = fabricacion;
    }

    public static int getGarantia(){
        return GARANTIA;
    }


    @Override
    public void calculGarantia() throws ParseException {
        System.out.println("Introdueix la data de compra del producte:");
        String fechaString = ConsoleManager.readString();
        Date fecha = simpleDateFormat.parse(fechaString);
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.MONTH, GARANTIA);
        System.out.println(c);
    }
}
