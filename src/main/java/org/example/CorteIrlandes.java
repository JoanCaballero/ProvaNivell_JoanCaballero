package org.example;

import org.example.Products.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CorteIrlandes {
    private List<Producto> productos;
    private List<Producto> productosRebajados;
    private static final String PATTERN = "yyyy-MM-dd";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);

    public CorteIrlandes() {
        this.productos = new ArrayList<>();
        this.productosRebajados = new ArrayList<>();
    }

    public void crearProducto() throws ParseException {
        System.out.println("Introdueix el nom del producte: ");
        String nom = ConsoleManager.readString();
        System.out.println("Introdueix la marca del producte: ");
        String marca = ConsoleManager.readString();
        System.out.println("Introdueix el preu del producte: ");
        double precio = ConsoleManager.readDouble();
        boolean correcteStock = false;
        int stock;
        do {
            System.out.println("Introdueix l'stock del producte: ");
            stock = ConsoleManager.readInt();
            if(stock >0){
                correcteStock = true;
            }
        }while(!correcteStock);
        System.out.println("Introdueix la rebaixa del producte: (Escriu 20 si la rebaixa es d'un 20%)");
        double rebaja = ConsoleManager.readDouble();
        boolean correcte = false;
        do {
            System.out.println("""
                    Quin tipus de producte vols crear?
                    1.- Roba.
                    2.- Electrodomèstic
                    3.- Component Electrònic
                    4.- Bellesa""");
            int opcio = ConsoleManager.readInt();
            switch (opcio) {
                case 1:
                    crearRopa(nom, marca, precio, stock, rebaja);
                    correcte = true;
                case 2:
                    crearElectrodomestico(nom, marca, precio, stock, rebaja);
                    correcte = true;
                case 3:
                    crearComponent(nom, marca, precio, stock, rebaja);
                    correcte = true;
                case 4:
                    crearBellesa(nom, marca, precio, stock, rebaja);
                    correcte = true;
            }
        } while (!correcte);
    }

    public void crearRopa(String nom, String marca, double precio, int stock, double rebaja){
        System.out.println("Quina talla es el producte?");
        String talla = ConsoleManager.readString();
        System.out.println("De quin tipus de teixit està fet el producte?");
        String tejido = ConsoleManager.readString();
        System.out.println("Quin tipus de peça es el producte?");
        String tipo = ConsoleManager.readString();
        Ropa ropa = new Ropa(nom, marca, precio, stock, rebaja, tejido, tipo, talla);
        productos.add(ropa);
    }
    public void crearElectrodomestico(String nom, String marca, double precio, int stock, double rebaja) throws ParseException {
        System.out.println("Quin consum te el producte?");
        int consum = ConsoleManager.readInt();
        System.out.println("A quina data es va fabricar el producte? yyyy-MM-dd");
        String fechaString = ConsoleManager.readString();
        Date fecha = simpleDateFormat.parse(fechaString);
        System.out.println("Quina capacitat te el producte?");
        int capacitat = ConsoleManager.readInt();
        Electrodomestico electrodomestico = new Electrodomestico(nom, marca, precio, stock, rebaja, consum, capacitat, fecha);
        productos.add(electrodomestico);
    }
    public void crearComponent(String nom, String marca, double precio, int stock, double rebaja){
        System.out.println("Quina resolució te el producte?");
        int resolucio = ConsoleManager.readInt();
        System.out.println("Quina capacitat te la bateria del producte? 2500mAh/3000mAh/4000mAh");
        String capacitat = ConsoleManager.readString();
        Componente componente = new Componente(nom, marca, precio, stock, rebaja, resolucio, capacitat);
        productos.add(componente);
    }
    public void crearBellesa(String nom, String marca, double precio, int stock, double rebaja){
        boolean correcte = false;
        boolean vega = false;
        do {
            System.out.println("El producte es Vegà? S/N");
            char siNo = ConsoleManager.readString().charAt(0);
            if (siNo == 'S') {
                vega = true;
                correcte = true;
            } else if (siNo == 'N') {
                correcte = true;
            }
            System.out.println("Valor introduït incorrectament");
        }while(!correcte);
        System.out.println("De quina temporada és el producte? ESTIU/PRIMAVERA/HIVERN/TARDOR");
        String temporada = ConsoleManager.readString().toUpperCase();
        Belleza belleza = new Belleza(nom, marca, precio, stock, rebaja, vega, temporada);
        productos.add(belleza);
    }

    public void llistarProductes(){
        productos.sort(new ProductoComparator());
        productos.forEach(Producto->{
            System.out.println(Producto.getNombre());
            System.out.println(Producto.getMarca());
            System.out.println(Producto.getClass().getSimpleName());
        });
    }

    public void eliminarProducto(){
        System.out.println("Introdueix el nom del producte del qual vols introduir stock: ");
        String nom = ConsoleManager.readString();
        List<Producto> trobat = productos.stream().filter(p -> p.getNombre().equals(nom)).toList();
        if(trobat.isEmpty()){
            System.out.println("No existeix un producte amb aquest nom.");
        }
        else{
            productos.remove(trobat.get(0));
        }
    }

    public void calcularGarantia() {
        int garantia;
        boolean correcte = false;
        do {
            System.out.println("""
                    Quin d'aquests dos tipus de productes és el teu:
                    1.- Electrodomèstic.
                    2.- Component electrònic.
                    """);
            int opcio = ConsoleManager.readInt();
            switch(opcio){
                case 1:
                    try{
                        Electrodomestico e = new Electrodomestico("", "", 0, 0, 0, 0, 0, null);
                        e.calculGarantia();
                        correcte = true;
                    } catch(ParseException e){
                        System.out.println(e.getMessage());
                    }
                case 2:
                    try{
                        Componente c = new Componente("", "", 0, 0, 0, 0, "mAh");
                        c.calculGarantia();
                        correcte = true;
                    } catch(ParseException e){
                        System.out.println(e.getMessage());
                    }
            }
            System.out.println("Si aquest producte no es de cap d'aquests dos tipus significa que no te garantia.");
        }while(!correcte);
    }

    public void aplicarRebajas(){
        for (Producto producto : productos) {
            double rebaja = 1 - producto.getRebaja() / 100;
            double nuevoPrecio = producto.getPrecio() * rebaja;
            if (producto.getPrecio() != nuevoPrecio) {
                productosRebajados.add(producto);
            }
            producto.setPrecio(nuevoPrecio);
        }
    }

    public void mostrarProductosRebajados(){
        productosRebajados.forEach(Producto->{
            System.out.println(Producto.getNombre());
            System.out.println(Producto.getMarca());
            System.out.println(Producto.getClass().getSimpleName());
        });
    }

    public void consultarStock(){
        System.out.println("Introdueix el nom del producte: ");
        String nom = ConsoleManager.readString();
        List<Producto> trobat = productos.stream().filter(p -> p.getNombre().equals(nom)).toList();
        if(trobat.isEmpty()){
            System.out.println("No existeix un producte amb aquest nom.");
        }
        else{
            System.out.println("Stock de " + nom + " = " + trobat.get(0).getStock());
        }
    }

    public void aumentarStock(){
        System.out.println("Introdueix el nom del producte del qual vols introduir stock: ");
        String nom = ConsoleManager.readString();
        List<Producto> trobat = productos.stream().filter(p -> p.getNombre().equals(nom)).toList();
        if(trobat.isEmpty()){
            System.out.println("No existeix un producte amb aquest nom.");
        }
        else{
            System.out.println("Quina quantitat vols afegir?");
            int quants = ConsoleManager.readInt();
            trobat.get(0).setStock(trobat.get(0).getStock() + quants);
            System.out.println("Nou stock: " + trobat.get(0).getStock());
        }
    }

    public void treureStock(){
        System.out.println("Introdueix el nom del producte del qual vols introduir stock: ");
        String nom = ConsoleManager.readString();
        List<Producto> trobat = productos.stream().filter(p -> p.getNombre().equals(nom)).toList();
        if(trobat.isEmpty()){
            System.out.println("No existeix un producte amb aquest nom.");
        }
        else{
            System.out.println("Quina quantitat vols treure?");
            int quants = ConsoleManager.readInt();
            if (trobat.get(0).getStock() < quants) {
                System.out.println("No es pot treure més stock del que hi ha.");
            }
            else{
                trobat.get(0).setStock(trobat.get(0).getStock() - quants);
                System.out.println("Nou stock: " + trobat.get(0).getStock());
            }
        }
    }
}
