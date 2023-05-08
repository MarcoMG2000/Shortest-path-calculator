/**
 * Practica 4 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 12/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url
 */
package model;

import sax.NodoLectura;
import sax.Datos;
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
    private Controller controlador1;
    private Controller controlador2;
    
    private int totalNodos;
    private int totalAristas;
    private Nodo nodoInicio;
    private ArrayList<Nodo> grafo;
    private Datos datos;

    private int[] nodosPrevios;
    private int[] distMin;


    // CONSTRUCTORS
    public Model() {
        datos = new Datos();
    }

    public Model(View vista, Controller controlador1, Controller controlador2, Datos datos) {
        this.grafo = datos.getGrafo();
        this.vista = vista;
        this.controlador1 = controlador1;
        this.controlador2 = controlador2;
    }

    // CLASS METHODS
    // GETTERS & SETTERS
    public View getVista() {
        return vista;
    }

    public void setVista(View vista) {
        this.vista = vista;
    }

    public Controller getControlador1() {
        return controlador1;
    }

    public void setControlador1(Controller controlador1) {
        this.controlador1 = controlador1;
    }

    public Controller getControlador2() {
        return controlador2;
    }

    public void setControlador2(Controller controlador2) {
        this.controlador2 = controlador2;
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
    
    public ArrayList<NodoLectura> getNodosL(){
        return datos.getNodosL();
    }
    
    public Datos getDatos(){
        return datos;
    }


    public void actualizarNNodos() {
        grafo = datos.getGrafo();
        totalNodos = datos.getNNodos();
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
