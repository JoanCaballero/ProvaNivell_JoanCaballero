package org.example;

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        CorteIrlandes cI = new CorteIrlandes();
        Scanner sc = new Scanner(System.in);
        int opcio;
        do {
            System.out.println("Benvingut al Corte Irlandés!");
            System.out.println("""
                    Que vols fer?
                    1.- Crear un producte.
                    2.- Llistar els productes ordenats.
                    3.- Eliminar un producte.
                    4.- Calcular la data del venciment de la garantia.
                    5.- Aplicar les rebaixes dels productes.
                    6.- Llistar els productes rebaixats.
                    7.- Consultar l'stock d'un producte,
                    8.- Augmentar l'stock d'un producte.
                    9.- Treure stock d'un producte.
                    10.- Sortir.""");
            opcio = sc.nextInt();
            switch (opcio){
                case 1: cI.crearProducto();
                    break;
                case 2: cI.llistarProductes();
                    break;
                case 3: cI.eliminarProducto();
                    break;
                case 4: cI.calcularGarantia();
                    break;
                case 5: cI.aplicarRebajas();
                    break;
                case 6: cI.mostrarProductosRebajados();
                    break;
                case 7: cI.consultarStock();
                    break;
                case 8: cI.aumentarStock();
                    break;
                case 9: cI.treureStock();
                    break;
            }
        }while(opcio != 10);
        System.out.println("Fins un altre!");
    }
}