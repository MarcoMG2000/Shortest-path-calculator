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
    
    public void ponArista(Arista a) {
        salientes.add(a);
    }
    
    public String getEtiqueta() {
        return etiqueta;
    }
    
    public int getX() {
        return X;
    }
    
    public int getY() {
        return Y;
    }

    public int getNAristas() {
        return salientes.size();
    }

    public Arista getArista(int i) {
        return salientes.get(i);
    }
}
