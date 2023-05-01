/**
 * Practica 4 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 12/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url
 */
package main;

import controller.Controller;
import java.util.ArrayList;
import java.util.Arrays;
import model.Datos;
//import mesurament.Mesurament;
import model.Model;
import model.Nodo;
import sax.MeuSax;
import view.View;

/**
 * Clase principal desde la que iniciamos la aplicación.
 */
public class AlgoritmosAvanzadosP4 implements InterfazPrincipal {

    private Model modelo;    // Punter al Model del patró
    private View vista;    // Punter a la Vista del patró
    private Controller controlador;  // punter al Control del patró

    public static void main(String[] args) {
        //Mesurament.mesura();
        (new AlgoritmosAvanzadosP4()).MVCInit();
    }

    /**
     * Establece los punteros entre las distintas clases del patrón MVC para que
     * se puedan comunicar entre ellas.
     */
    private void MVCInit() {
        modelo = new Model();
        /*
        Todo esto lo he necesitado para poder hacer una prueba de funcionamiento
         */
        /*
        Nodo n1 = new Nodo(1,"");
        Nodo n2 = new Nodo(2,"");
        Nodo n3 = new Nodo(3,"");
        Nodo n4 = new Nodo(4,"");
        Nodo n5 = new Nodo(5,"");
        Integer[] adj1 = new Integer[2];
        Integer[] adj2 = new Integer[2];
        Integer[] adj3 = new Integer[2];
        Integer[] adj4 = new Integer[2];
        Integer[] adj5 = new Integer[2];
        Integer[] adj6 = new Integer[2];
        Integer[] adj7 = new Integer[2];
        Integer[] adj8 = new Integer[2];
        Integer[] adj9 = new Integer[2];
        Integer[] adj10 = new Integer[2];
        Integer[] adj11 = new Integer[2];
        Integer[] adj12 = new Integer[2];
        Integer[] adj13 = new Integer[2];
        Integer[] adj14 = new Integer[2];
        adj1[0] = 2;
        adj1[1] = 6;
        n1.añadirNodoAdj(adj1);
        adj2[0] = 3;
        adj2[1] = 1;
        n1.añadirNodoAdj(adj2);
        adj3[0] = 1;
        adj3[1] = 6;
        n2.añadirNodoAdj(adj3);
        adj4[0] = 3;
        adj4[1] = 2;
        n2.añadirNodoAdj(adj4);
        adj5[0] = 4;
        adj5[1] = 2;
        n2.añadirNodoAdj(adj5);
        adj6[0] = 5;
        adj6[1] = 5;
        n2.añadirNodoAdj(adj6);
        adj7[0] = 1;
        adj7[1] = 1;
        n3.añadirNodoAdj(adj7);
        adj8[0] = 2;
        adj8[1] = 2;
        n3.añadirNodoAdj(adj8);
        adj9[0] = 4;
        adj9[1] = 1;
        n3.añadirNodoAdj(adj9);
        adj10[0] = 2;
        adj10[1] = 2;
        n4.añadirNodoAdj(adj10);
        adj11[0] = 1;
        adj11[1] = 1;
        n4.añadirNodoAdj(adj11);
        adj12[0] = 5;
        adj12[1] = 5;
        n4.añadirNodoAdj(adj12);
        adj13[0] = 2;
        adj13[1] = 5;
        n5.añadirNodoAdj(adj13);
        adj14[0] = 4;
        adj14[1] = 5;
        n5.añadirNodoAdj(adj14);
        ArrayList<Nodo> grafo = new ArrayList<Nodo>();
        grafo.add(n1);
        grafo.add(n2);
        grafo.add(n3);
        grafo.add(n4);
        grafo.add(n5);
        modelo.setGrafo(grafo);
        modelo.setTotalNodos(5);
        modelo.setTotalAristas(7);
        modelo.setNodoInicio(n1);
        */
        //////////
        // Provisionalmente se carga desde aquí el fichero. Cuando se tenga la GUI se cargará desde allí
        MeuSax sax = new MeuSax("grafobase.ltim", this);
        sax.llegir();
        modelo.actualizarNNodos();
        //modelo.setNodoInicio(modelo.getGrafo().get(0));
        //System.out.println("nodes llegits = " + modelo.getTotalNodos());
        //////////
        
        vista = new View();
        controlador = new Controller();

        //modelo.setVista(vista);
        modelo.setControlador(controlador);

        //vista.setModelo(modelo);
        //vista.setControlador(controlador);

        controlador.setModelo(modelo);
        
        System.out.println(Arrays.toString(controlador.dijkstraRec(1, modelo.getGrafo().get(4))));
      //  controlador.dijkstra(n1.getnNodo(), n5);
//        System.out.println(Arrays.toString(modelo.getDistMin()));
//        System.out.println(Arrays.toString(modelo.getNodosPrevios()));
        //controlador.setVista(vista);

        vista.mostrar();
    }

    /**
     * Método que sirve de nexo de unión, para que la IGU y el hilo de proceso
     * se comuniquen entre ellos y con la clase principal.
     *
     * @param s Sirve para que las distintas clases de la aplicación manden
     * notificaciones a la clase principal, es una forma de centralizar los
     * distintos eventos y situaciones. Es el encargado de arrancar y terminar
     * el proceso de cálculo, así como de pasar la información entre la IGU y el
     * hilo secundario de ejecución.
     */
    @Override
    public void notificar(String s) {
        if (s.startsWith("proceso-start")) {
//            Controller pr = new Controller();
//            pr.calcular();
//            gui.repaint();
        } else if (s.contentEquals("proceso-stop")) {
            //proc = null;
            //gui.borrarProgreso();
        } else if (s.startsWith("proceso-cargargrafo")) {
//            datos = new Datos();
//            MeuSax sax = new MeuSax(gui.getFile(), this);
//            sax.llegir();
//            gui.repaint();
        }
    }

    public Datos getDatos() {
        return modelo.getDatos();
    }

}
