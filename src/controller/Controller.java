/**
 * Practica 4 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 12/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import model.Model;
import model.Nodo;
import view.View;

/**
 * Controlador de la aplicación, aquí se procesarán las funciones y los cálculos
 * de la aplicación.
 */
public class Controller {

    // PUNTEROS DEL PATRÓN MVC
    private Model modelo;
    private View vista;

    // CONSTRUCTORS
    public Controller() {
    }

    public Controller(Model modelo, View vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public int[] dijkstra() {
        /*Creamos una PriorityQueue que contenga pares de nodo y distancia, esta
        distancia es la distancia desde el origen hasta dicho nodo*/
        PriorityQueue<Par> colaP = new PriorityQueue<Par>((p1, p2) -> p1.distancia - p2.distancia);
        /*En estos array alojaremos la informacion de las distancias que vamos
        obteniendo desde el origen hasta cada nodo, y el nodo previo respectivamente*/
        int[] distMin = new int[modelo.getTotalNodos()];
        int[] nodosPrevios = new int[modelo.getTotalNodos()];
        /*Rellenamos el primer array con -1 y el segundo con un valor "infinito"*/
        Arrays.fill(nodosPrevios, -1);
        Arrays.fill(distMin, Integer.MAX_VALUE);
        /*La distancia del origen al origen es 0*/
        distMin[modelo.getNodoInicio().getnNodo() - 1] = 0;
        /*Añadimos el primer par a la PriorityQueue e iniciamos el algoritmo*/
        colaP.add(new Par(0, modelo.getNodoInicio().getnNodo() - 1));
        /*Sacamos el nodo de distancia mínima primero de la cola de prioridad
        y recorremos todos sus nodos adyacentes.*/
        while (colaP.size() != 0) {
            int dist = colaP.peek().distancia;
            int nodo = colaP.peek().nodo;
            colaP.remove();
            /*Verificamos todos los nodos adyacentes del nodo escogido y tambien
            si la distancia previa es mayor que la actual o no.*/
            for (int i = 0; i < modelo.getGrafo().get(nodo).getAdjacentes().size(); i++) {
                int pesoArista = modelo.getGrafo().get(nodo).getAdjacentes().get(i)[1];
                int nodoAdj = modelo.getGrafo().get(nodo).getAdjacentes().get(i)[0];
                /*Si la distancia actual es menor la añadimos a la cola.*/
                if (dist + pesoArista < distMin[nodoAdj - 1]) {
                    distMin[nodoAdj - 1] = dist + pesoArista;
                    nodosPrevios[nodoAdj - 1] = nodo;
                    colaP.add(new Par(distMin[nodoAdj - 1], nodoAdj - 1));
                }
            }
        }

        // Para recuperar el camino más corto desde el origen hasta un nodo destino específico,
        // se puede seguir los nodos previos desde el destino hasta el origen.
        return distMin;
    }

    // CLASS METHODS
    // SETTERS & GETTERS
    public Model getModelo() {
        return modelo;
    }

    public void setModelo(Model modelo) {
        this.modelo = modelo;
    }

    public View getVista() {
        return vista;
    }

    public void setVista(View vista) {
        this.vista = vista;
    }

    public class Par {

        int nodo;
        int distancia;

        public Par(int d, int n) {
            this.nodo = n;
            this.distancia = d;
        }
    }
}
