package org.example;

import java.util.Comparator;

public class ProductoComparator implements Comparator<Producto> {
    public int compare(Producto p1, Producto p2) {
        if (p1.getPrecio() < p1.getPrecio()) {
            return -1;
        } else if (p1.getPrecio() == p1.getPrecio()) {
            return 0;
        } else {
            return 1;
        }
    }
}
