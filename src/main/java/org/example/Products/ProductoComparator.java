package org.example.Products;

import org.example.Products.Producto;

import java.util.Comparator;

public class ProductoComparator implements Comparator<Producto> {
    public int compare(Producto p1, Producto p2) {
        return Double.compare(p1.getPrecio(), p2.getPrecio());
    }
}
