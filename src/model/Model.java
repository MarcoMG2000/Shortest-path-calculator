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
import java.util.ArrayList;

/**
 * Modelo de la aplicación, aquí se guardan todos los datos necesarios para su
 * correcta operación.
 */
public class Model {

    // PUNTEROS DEL PATRÓN MVC
    private View vista;
    
    private int totalNodos;
    private int totalAristas;
    
    private ArrayList<Nodo> grafo;
    private Datos datos;
    
    private Nodo nodoInicio;

    private ArrayList<Integer> nodosBloqueados;


    // CONSTRUCTORS
    public Model() {
        datos = new Datos();
    }

    public Model(View vista, Datos datos) {
        this.grafo = datos.getGrafo();
        this.vista = vista;
    }

    // CLASS METHODS
    // GETTERS & SETTERS
    public View getVista() {
        return vista;
    }

    public void setVista(View vista) {
        this.vista = vista;
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

    public void setNodosBloqueados(ArrayList<Integer> nodosBloqueados) {
        this.nodosBloqueados = nodosBloqueados;
    }
    
    public ArrayList<Integer> getNodosBloqueados() {
        return nodosBloqueados;
    }
    
    

}
