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
public class AlgoritmosAvanzadosP4 {

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
        vista = new View(new Controller(), new Model());
        vista.mostrar();
    }

    public Datos getDatos() {
        return modelo.getDatos();
    }

}
