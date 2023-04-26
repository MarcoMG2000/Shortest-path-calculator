/**
 * Practica 4 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 12/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url
 */
package model;

import view.View;
import controller.Controller;
import java.util.ArrayList;

/**
 * Modelo de la aplicación, aquí se guardan todos los datos necesarios para su
 * correcta operación.
 */
public class Model {

    // PUNTEROS DEL PATRÓN MVC
    private View vista;
    private Controller controlador;
    private int totalNodos;
    private int totalAristas;
    private Nodo nodoInicio;
    private ArrayList<Nodo> grafo;
    private int[] nodosPrevios;
    private int[] distMin;

    // CONSTRUCTORS
    public Model() {
    }

    public Model(View vista, Controller controlador) {
        this.vista = vista;
        this.controlador = controlador;
    }

    // CLASS METHODS
    // GETTERS & SETTERS
    public View getVista() {
        return vista;
    }

    public void setVista(View vista) {
        this.vista = vista;
    }

    public Controller getControlador() {
        return controlador;
    }

    public void setControlador(Controller controlador) {
        this.controlador = controlador;
    }

    public int getTotalNodos() {
        return totalNodos;
    }

    public void setTotalNodos(int totalNodos) {
        this.totalNodos = totalNodos;
    }

    public int getTotalAristas() {
        return totalAristas;
    }

    public void setTotalAristas(int totalAristas) {
        this.totalAristas = totalAristas;
    }

    public Nodo getNodoInicio() {
        return nodoInicio;
    }

    public void setNodoInicio(Nodo nodoInicio) {
        this.nodoInicio = nodoInicio;
    }

    public ArrayList<Nodo> getGrafo() {
        return grafo;
    }

    public void setGrafo(ArrayList<Nodo> al) {
        this.grafo = al;
    }

    public int[] getNodosPrevios() {
        return nodosPrevios;
    }

    public void setNodosPrevios(int[] nodosPrevios) {
        this.nodosPrevios = nodosPrevios;
    }

    public int[] getDistMin() {
        return distMin;
    }

    public void setDistMin(int[] distMin) {
        this.distMin = distMin;
    }

}
