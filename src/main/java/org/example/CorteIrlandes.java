package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CorteIrlandes {
    private Scanner sc = new Scanner(System.in);
    private List<Producto> productos;
    private List<Producto> productosRebajados;
    private String pattern = "yyyy-MM-dd";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public CorteIrlandes() {
        this.productos = new ArrayList<>();
        this.productosRebajados = new ArrayList<>();
    }

    public void crearProducto() throws ParseException {
        System.out.println("Introdueix el nom del producte: ");
        String nom = sc.nextLine();
        System.out.println("Introdueix la marca del producte: ");
        String marca = sc.nextLine();
        System.out.println("Introdueix el preu del producte: ");
        double precio = sc.nextDouble();
        boolean correcteStock = false;
        int stock;
        do {
            System.out.println("Introdueix l'stock del producte: ");
            stock = sc.nextInt();
            if(stock >0){
                correcteStock = true;
            }
        }while(!correcteStock);
        System.out.println("Introdueix la rebaixa del producte: (Escriu 20 si la rebaixa es d'un 20%)");
        double rebaja = sc.nextDouble();
        boolean correcte = false;
        do {
            System.out.println("""
                    Quin tipus de producte vols crear?
                    1.- Roba.
                    2.- Electrodomèstic
                    3.- Component Electrònic
                    4.- Bellesa""");
            int opcio = sc.nextInt();
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
        String talla = sc.nextLine();
        System.out.println("De quin tipus de teixit està fet el producte?");
        String tejido = sc.nextLine();
        System.out.println("Quin tipus de peça es el producte?");
        String tipo = sc.nextLine();
        Ropa ropa = new Ropa(nom, marca, precio, stock, rebaja, tejido, tipo, talla);
        productos.add(ropa);
    }
    public void crearElectrodomestico(String nom, String marca, double precio, int stock, double rebaja) throws ParseException {
        System.out.println("Quin consum te el producte?");
        int consum = sc.nextInt();
        System.out.println("A quina data es va fabricar el producte? yyyy-MM-dd");
        String fechaString = sc.nextLine();
        Date fecha = simpleDateFormat.parse(fechaString);
        System.out.println("Quina capacitat te el producte?");
        int capacitat = sc.nextInt();
        Electrodomestico electrodomestico = new Electrodomestico(nom, marca, precio, stock, rebaja, consum, capacitat, fecha);
        productos.add(electrodomestico);
    }
    public void crearComponent(String nom, String marca, double precio, int stock, double rebaja){
        System.out.println("Quina resolució te el producte?");
        int resolucio = sc.nextInt();
        System.out.println("Quina capacitat te la bateria del producte?");
        int capacitat = sc.nextInt();
        Componente componente = new Componente(nom, marca, precio, stock, rebaja, resolucio, capacitat);
        productos.add(componente);
    }
    public void crearBellesa(String nom, String marca, double precio, int stock, double rebaja){
        boolean correcte = false;
        boolean vega = false;
        do {
            System.out.println("El producte es Vegà? S/N");
            char siNo = sc.nextLine().charAt(0);
            if (siNo == 'S') {
                vega = true;
                correcte = true;
            } else if (siNo == 'N') {
                vega = false;
                correcte = true;
            }
            System.out.println("Valor introduït incorrectament");
        }while(!correcte);
        System.out.println("De quina temporada és el producte?");
        String temporada = sc.nextLine();
        Belleza belleza = new Belleza(nom, marca, precio, stock, rebaja, vega, temporada);
        productos.add(belleza);
    }

    public void llistarProductes(){
        Collections.sort(productos, new ProductoComparator());
        productos.stream().forEach(Producto->{
            Producto.getNombre();
            Producto.getMarca();
            Producto.getClass().getSimpleName();
        });
    }

    public void eliminarProducto(){
        System.out.println("Introdueix el nom del producte del qual vols introduir stock: ");
        String nom = sc.nextLine();
        List<Producto> trobat = productos.stream().filter(p -> p.getNombre().equals(nom)).collect(Collectors.toList());
        if(trobat.isEmpty()){
            System.out.println("No existeix un producte amb aquest nom.");
        }
        else{
            productos.remove(trobat.get(0));
        }
    }

    public void calcularGarantia() throws ParseException {
        System.out.println("Introdueix la data de compra del producte:");
        String fechaString = sc.nextLine();
        Date fecha = simpleDateFormat.parse(fechaString);
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        int garantia;
        boolean correcte = false;
        do {
            System.out.println("""
                    Quin d'aquests dos tipus de productes és el teu: 
                    1.- Electrodomèstic.
                    2.- Component electrònic.
                    """);
            int opcio = sc.nextInt();
            //TODO: posa això a un mètode.
            switch(opcio){
                case 1:
                    garantia = Electrodomestico.getGarantia();
                    c.add(Calendar.MONTH, garantia);
                    System.out.println(c);
                    correcte = true;
                case 2:
                    garantia = Componente.getGarantia();
                    c.add(Calendar.MONTH, garantia);
                    System.out.println(c);
                    correcte = true;
            }
            System.out.println("Si aquest producte no es de cap d'aquests dos tipus significa que no te garantia.");
        }while(!correcte);
    }

    public void aplicarRebajas(){
        for(int i = 0; i < productos.size(); i++){
            double rebaja = 1 - productos.get(i).getRebaja() /100;
            double nuevoPrecio = productos.get(i).getPrecio() * rebaja;
            if(productos.get(i).getPrecio() != nuevoPrecio){
                productosRebajados.add(productos.get(i));
            }
            productos.get(i).setPrecio(nuevoPrecio);
        }
    }

    public void mostrarProductosRebajados(){
        productosRebajados.stream().forEach(Producto->{
            Producto.getNombre();
            Producto.getMarca();
            Producto.getClass().getSimpleName();
        });
    }

    public void consultarStock(){
        System.out.println("Introdueix el nom del producte: ");
        String nom = sc.nextLine();
        List<Producto> trobat = productos.stream().filter(p -> p.getNombre().equals(nom)).collect(Collectors.toList());
        if(trobat.isEmpty()){
            System.out.println("No existeix un producte amb aquest nom.");
        }
        else{
            System.out.println("Stock de " + nom + " = " + trobat.get(0).getStock());
        }
    }

    public void aumentarStock(){
        System.out.println("Introdueix el nom del producte del qual vols introduir stock: ");
        String nom = sc.nextLine();
        List<Producto> trobat = productos.stream().filter(p -> p.getNombre().equals(nom)).collect(Collectors.toList());
        if(trobat.isEmpty()){
            System.out.println("No existeix un producte amb aquest nom.");
        }
        else{
            System.out.println("Quina quantitat vols afegir?");
            int quants = sc.nextInt();
            trobat.get(0).setStock(trobat.get(0).getStock() + quants);
            System.out.println("Nou stock: " + trobat.get(0).getStock());
        }
    }

    public void treureStock(){
        System.out.println("Introdueix el nom del producte del qual vols introduir stock: ");
        String nom = sc.nextLine();
        List<Producto> trobat = productos.stream().filter(p -> p.getNombre().equals(nom)).collect(Collectors.toList());
        if(trobat.isEmpty()){
            System.out.println("No existeix un producte amb aquest nom.");
        }
        else{
            System.out.println("Quina quantitat vols treure?");
            int quants = sc.nextInt();
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
