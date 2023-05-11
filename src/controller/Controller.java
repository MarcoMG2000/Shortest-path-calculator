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
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import model.Model;
import model.Nodo;
import view.View;

/**
 * Controlador de la aplicación, aquí se procesarán las funciones y los cálculos
 * de la aplicación.
 */
public class Controller implements Runnable {

    // PUNTEROS DEL PATRÓN MVC
    private Model modelo;
    private View vista;
    private int[] nodosPrevios;
    private int[] distMin;
    private int nInicio;
    private Nodo nDestino;

    // CONSTRUCTORS
    public Controller() {
    }

    public Controller(Model modelo, View vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public int[] getNodosPrevios() {
        return nodosPrevios;
    }

    public int[] getDistMin() {
        return distMin;
    }
    
    public int getnInicio(){
        return this.nInicio;
    }
    
    public int getnDestino(){
        return modelo.getGrafo().indexOf(this.nDestino);
    }
    
    
    public int[] dijkstra() {
        int numNodos = modelo.getTotalNodos();
        int nodoInicio = nInicio - 1;
        /*Creamos una PriorityQueue que contenga pares de nodo y distancia, esta
        distancia es la distancia desde el origen hasta dicho nodo*/
        PriorityQueue<Par> colaP = new PriorityQueue<>(Comparator.comparingInt(p -> p.distancia));
        /*En estos array alojaremos la informacion de las distancias que vamos
        obteniendo desde el origen hasta cada nodo, y el nodo previo respectivamente*/
        distMin = new int[numNodos];
        nodosPrevios = new int[numNodos];
        /*Rellenamos el primer array con -1 y el segundo con un valor "infinito"*/
        Arrays.fill(nodosPrevios, -1);
        Arrays.fill(distMin, Integer.MAX_VALUE);
        /*La distancia del origen al origen es 0*/
        distMin[nodoInicio] = 0;
        /*Añadimos el primer par a la PriorityQueue e iniciamos el algoritmo*/
        colaP.add(new Par(0, nodoInicio));
        /*Este HashSet visitados se utiliza para almacenar los nodos que ya han 
        sido visitados. Si el nodo actual ya está en este conjunto, se salta el 
        procesamiento del nodo y se pasa al siguiente. De esta forma, evitamos
        volver a procesar un nodo que ya ha sido visitado y se reduce el número 
        de operaciones necesarias para completar el algoritmo.*/
        Set<Integer> visitados = new HashSet<>();
        
        /*Ponemos como visitados los nodos Bloqueados para que no se tomen en 
        cuenta durante la ejecucíon del algoritmo. ATENCION: CON ESTO PODEMOS
        LLEGAR AL FINAL DEL ALGORITMO SIN SOLUCIÓN*/
        for(Integer indxNodoBloqueado : modelo.getNodosBloqueados()){
            visitados.add(indxNodoBloqueado-1);
        }
        
        int iteraciones = 0;
        /*Sacamos el nodo de distancia mínima primero de la cola de prioridad
        y recorremos todos sus nodos adyacentes.*/
        while (!colaP.isEmpty()) {
            iteraciones++;
            Par parActual = colaP.poll();
            int nodoActual = parActual.nodo;
            if (visitados.contains(nodoActual)) {
                continue; // Podar si el nodo ya fue visitado
            }
            visitados.add(nodoActual);
            int distActual = parActual.distancia;
            /*Verificamos todos los nodos adyacentes del nodo escogido y tambien
            si la distancia previa es mayor que la actual o no.*/
            for (Integer[] arista : modelo.getGrafo().get(nodoActual).getAdjacentes()) {
                int pesoArista = arista[1];
                int nodoAdj = arista[0] - 1;
                /*Si la distancia actual es menor la añadimos a la cola.*/
                if (distActual + pesoArista < distMin[nodoAdj]) {
                    distMin[nodoAdj] = distActual + pesoArista;
                    nodosPrevios[nodoAdj] = nodoActual;
                    colaP.add(new Par(distMin[nodoAdj], nodoAdj));
                }
            }
        }
        // Para recuperar el camino más corto desde el origen hasta un nodo destino específico,
        // se puede seguir los nodos previos desde el destino hasta el origen.
        System.out.println(iteraciones);
        
        //modelo.setNodosPrevios(nodosPrevios);
        //modelo.setDistMin(distMin);
        return distMin;
    }

    public int[] dijkstraRec() {
        int numNodos = modelo.getTotalNodos();
        int nodoInicio = nInicio - 1;
        int[] distMin = new int[numNodos];
        int[] nodosPrevios = new int[numNodos];
        Arrays.fill(nodosPrevios, -1);
        Arrays.fill(distMin, Integer.MAX_VALUE);
        distMin[nodoInicio] = 0;

        Set<Integer> visitados = new HashSet<>();
        PriorityQueue<Par> colaP = new PriorityQueue<>(Comparator.comparingInt(p -> p.distancia));

        dijkstraRecAux(nodoInicio, 0, visitados, colaP, nodosPrevios, distMin, nDestino);

        modelo.setNodosPrevios(nodosPrevios);
        modelo.setDistMin(distMin);
        
        for(Integer indxNodoBloqueado : modelo.getNodosBloqueados()){
            visitados.add(indxNodoBloqueado-1);
        }
        
        return distMin;
    }

    private boolean dijkstraRecAux(int nodoActual, int distActual, Set<Integer> visitados,
            PriorityQueue<Par> colaP, int[] nodosPrevios, int[] distMin, Nodo nDestino) {
        if (visitados.contains(nodoActual)) {
            if(colaP.isEmpty()) return false;
            
            Par parActual = colaP.poll();
            return dijkstraRecAux(parActual.nodo, parActual.distancia, visitados, colaP, nodosPrevios, distMin, nDestino); // Podar si el nodo ya fue visitado
        }
        visitados.add(nodoActual);
        for (Integer[] arista : modelo.getGrafo().get(nodoActual).getAdjacentes()) {
            int pesoArista = arista[1];
            int nodoAdj = arista[0] - 1;
            //(!visitados.contains(nodoAdj) && distActual + pesoArista < distMin[nodoAdj])
            if (distActual + pesoArista < distMin[nodoAdj]) {
                distMin[nodoAdj] = distActual + pesoArista;
                nodosPrevios[nodoAdj] = nodoActual;
                colaP.add(new Par(distMin[nodoAdj], nodoAdj));
            }
        }
        if (!colaP.isEmpty()) {
            Par parActual = colaP.poll();
            return dijkstraRecAux(parActual.nodo, parActual.distancia, visitados, colaP, nodosPrevios, distMin, nDestino);
        }
        return false;
    }

    // CLASS METHODS
    // SETTERS & GETTERS

    public void setnInicio(int nInicio) {
        this.nInicio = nInicio;
    }

    public void setnDestino(int nDestino) {
        this.nDestino = this.modelo.getGrafo().get(nDestino-1);
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

    @Override
    public void run() {
        if(vista.getrightPanel().getOpcionAlg().getSelectedItem() == "Recursivo"){
            dijkstraRec();
        }else{
            dijkstra();
        }
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
