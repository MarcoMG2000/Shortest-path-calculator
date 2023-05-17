/**
 * Practica 4 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 12/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url
 */
package main;

import controller.Controller;
import model.Model;
import view.View;

import mesurament.Mesurament;


/**
 * Clase principal desde la que iniciamos la aplicación.
 */
public class AlgoritmosAvanzadosP4 {

    private View vista;
     // Punter a la Vista del patró

    public static void main(String[] args) {
        Mesurament.mesura();
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

}
