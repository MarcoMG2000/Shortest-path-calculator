/**
 * Practica 4 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 12/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url 
 */
package main;

import controller.Controller;
import mesurament.Mesurament;
import model.Model;
import view.View;

/**
 * Clase principal desde la que iniciamos la aplicación.
 */
public class AlgoritmosAvanzadosP4 {

    public static void main(String[] args) {
        Mesurament.mesura();
        MVCInit();
    }

    /**
     * Establece los punteros entre las distintas clases del patrón MVC para que
     * se puedan comunicar entre ellas.
     */
    private static void MVCInit() {
        Model modelo = new Model();
        View vista = new View();
        Controller controlador = new Controller();

        modelo.setVista(vista);
        modelo.setControlador(controlador);

        vista.setModelo(modelo);
        vista.setControlador(controlador);

        controlador.setModelo(modelo);
        controlador.setVista(vista);

        vista.mostrar();
    }

}
