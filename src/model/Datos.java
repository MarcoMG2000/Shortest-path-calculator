package model;

import main.Errores;
import java.util.ArrayList;

public class Datos {

    private int w;
    private int h;
    private ArrayList <NodoLectura> nodosL;
    private ArrayList<Nodo> grafo;
    private ArrayList <Arista> aristas;
    private double[][] solucion;
    private String tipo = "";

    public Datos() {
        w = 600;
        h = 600;
        grafo = new ArrayList<>();
        nodosL = new ArrayList<>();
        aristas = new ArrayList<>();
        solucion = null;
        tipo = "nodirigido";
    }

    public void ponTipo(String t) {
        tipo = t;
    }

    public char getTipo() { //devuelve 'n' o 'd'
        return tipo.charAt(0);
    }

    public void ponSolucion(double[][] sol) {
        solucion = sol;
    }
    
    public boolean haySolucion() {
        return (solucion != null);
    }
    
    public double[][] getSolucion() {
        return solucion;
    }
    
    public void ponNodo(String e, String f, int x, int y) {
        if (!existe(e)) {
            nodosL.add(new NodoLectura(e, x, y));
            grafo.add(new Nodo(Integer.parseInt(e),f));
        } else {
            Errores.informaError(new Exception("Etiqueta ya existente. " + e));
        }
    }

    public void ponArista(String eti, String etf) {
        ponArista(eti, etf, 0);
        Integer[] adj = new Integer[2];
        adj[0] = Integer.valueOf(etf);
        adj[1] = 0;
        grafo.get(Integer.parseInt(eti)).añadirNodoAdj(adj);
    }

    public void ponArista(String eti, String etf, int v) {
        Arista ar = new Arista(getNodo(etf),v);
        getNodo(eti).ponArista(ar);
        ///
        Integer[] adj = new Integer[2];
        adj[0] = Integer.valueOf(etf);
        adj[1] = v;
        grafo.get(Integer.parseInt(eti)-1).añadirNodoAdj(adj);
    }
    
    public int getNNodos() {
        return nodosL.size();
    }

    private boolean existe(String e) {
        boolean res = false;
        int i = 0;
        while ((!res) && (i < nodosL.size())) {
            if (e.contentEquals((nodosL.get(i)).getEtiqueta())) {
                res = true;
            }
            i++;
        }
        return res;
    }

    public NodoLectura getNodo(int i) {
        NodoLectura res = null;
        if (i < nodosL.size()) {
            res = nodosL.get(i);
        } else {
            Errores.informaError(new Exception("Nodo inexistente: " + i + "."));
        }
        return res;
    }
    
    private NodoLectura getNodo(String e) {
        boolean res = false;
        NodoLectura n = null;
        int i = 0;
        while ((!res) && (i < nodosL.size())) {
            if (e.contentEquals((nodosL.get(i)).getEtiqueta())) {
                res = true;
                n = nodosL.get(i);
            }
            i++;
        }
        if (n == null) {
            Errores.informaError(new Exception("Nodo inexistente."));
        }
        return n;
    }
    
    public int getNAristas(int i) {
        return getNodo(i).getNAristas();
    }
    
    public double getAristaValor(int i, int j) {
        NodoLectura n = nodosL.get(i);
        return n.getArista(j).getValor();
    }
    
    public int getAristaApunta(int i, int j) {
        NodoLectura n = nodosL.get(i);
        NodoLectura ap = n.getArista(j).apunta();
        int ind = 0;
        while (ap != nodosL.get(ind)) {
            ind++;
        }
        return ind;
    }
    
    public int getNodoX(int i) {
        return getNodo(i).getX();
    }
    
    public int getNodoY(int i) {
        return getNodo(i).getY();
    }
    
    public int getNodoFX(int i, int j) {
        NodoLectura n = nodosL.get(i);
        n = n.getArista(j).apunta();
        return n.getX();
    }
    
    public int getNodoFY(int i, int j) {
        NodoLectura n = nodosL.get(i);
        n = n.getArista(j).apunta();
        return n.getY();
    }
    
    public String getNodoEt(int i) {
        return getNodo(i).getEtiqueta();
    }
    
    public int getWidth() {
        return w;
    }
    
    public int getHeight() {
        return h;
    }
    
    public int getNAristasDenodo(NodoLectura n) {
        return n.getNAristas();
    }
    
    public Arista getAristaDenodo(NodoLectura n, int i) {
        return n.getArista(i);
    }
    
    public ArrayList<Nodo> getGrafo(){
        return grafo;
    }
    
    public ArrayList<NodoLectura> getNodosL(){
        return nodosL;
    }
}
