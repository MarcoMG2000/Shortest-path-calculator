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
import sax.Datos;
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
    private Controller controlador1;  // punter al Control del patró
    private Controller controlador2;

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
        vista = new View();
        
        // Provisionalmente se carga desde aquí el fichero. Cuando se tenga la GUI se cargará desde allí
        MeuSax sax = new MeuSax("menorca.ltim", this);
        sax.llegir();
        modelo.actualizarNNodos();

        //vista = new View();
        controlador1 = new Controller();
        controlador2 = new Controller();

        //Establecemos el nInicio y nDestino de cada controlador
        controlador1.setnInicio(1);
        controlador1.setnDestino(modelo.getGrafo().get(3-1));
        
        controlador2.setnInicio(3);
        controlador2.setnDestino(modelo.getGrafo().get(5));

        controlador1.setModelo(modelo);
        controlador2.setModelo(modelo);
        
        //modelo.setVista(vista);
        //modelo.setControlador(controlador1);

        //vista.setModelo(modelo);
        //vista.setControlador(controlador1);
        
        controlador1.run();
        System.out.println("Distancia min: " + Arrays.toString(modelo.getDistMin()));
        System.out.println("Nodos previos: " + Arrays.toString(modelo.getNodosPrevios()));
        controlador2.run();
        System.out.println("Distancia min: " + Arrays.toString(modelo.getDistMin()));
        System.out.println("Nodos previos: " + Arrays.toString(modelo.getNodosPrevios()));
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
