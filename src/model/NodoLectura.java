package model;

import java.util.ArrayList;

public class NodoLectura {

    private String etiqueta;
    private ArrayList <Arista> salientes;
    private int X;
    private int Y;

    public NodoLectura(String et, int x, int y) {
        etiqueta = et;
        X = x;
        Y = y;
        salientes = new ArrayList <Arista> ();
    }
    
    protected void ponArista(Arista a) {
        salientes.add(a);
    }
    
    protected String getEtiqueta() {
        return etiqueta;
    }
    
    protected int getX() {
        return X;
    }
    
    protected int getY() {
        return Y;
    }

    protected int getNAristas() {
        return salientes.size();
    }

    protected Arista getArista(int i) {
        return salientes.get(i);
    }
}
