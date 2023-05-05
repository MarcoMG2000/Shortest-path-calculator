/**
 * Practica 4 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 12/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url
 */
package view;

import model.Model;
import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Vista de la aplicación, aquí interactuaremos con la aplicación y
 * visualizaremos todos los datos y los resultados de las operaciónes.
 */
public class View extends JFrame{

    // PUNTEROS DEL PATRÓN MVC
    private Controller controlador;
    private Model modelo;

    // CONSTANTES DE LA VISTA
    protected final int MARGENLAT = 300;
    protected final int MARGENVER = 20;

    // VARIABLES DEL JPanel
    private int GraphWidth;
    private int GraphHeight;

    private LeftLateralPanel leftPanel;
    private RightLateralPanel rightPanel;
    private GraphPanel graphPanel;

    

    // CONSTRUCTORS
    public View() {
    }

    public View(Controller controlador, Model modelo) {
        this.controlador = controlador;
        this.modelo = modelo;
    }

    // CLASS METHODS
    /**
     * Clase que inicializa la ventana principal y añade todos los elementos al
     * JFrame.
     */
    public void mostrar() {
        this.GraphWidth = 800;
        this.GraphHeight = 700;

        // DIMENSION DEL JFRAME
        setSize(this.GraphWidth + this.MARGENLAT * 2, this.GraphHeight + this.MARGENVER + 40);

        // POSICIONAR EL JFRAME EN EL CENTRO DE LA PANTALLA
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // GRAPH PANEL
        graphPanel = new GraphPanel(this, GraphWidth, GraphHeight);
        this.add(graphPanel);
        // Agregamos un listener para capturar el clic  
        graphPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX()+", "+e.getY());
            }
        });

        // PANELES LATERALES
        leftPanel = new LeftLateralPanel(this);
        this.add(leftPanel);

        rightPanel = new RightLateralPanel(this);
        this.add(rightPanel);

        // ÚLTIMOS AJUSTES
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.paintComponents(this.getGraphics());
    }
    
    protected void verGrafoClicked() {
        this.graphPanel.repaint();

    }

    // GETTERS & SETTERS
    public Controller getControlador() {
        return controlador;
    }

    public void setControlador(Controller controlador) {
        this.controlador = controlador;
    }

    public Model getModelo() {
        return modelo;
    }

    public void setModelo(Model modelo) {
        this.modelo = modelo;
    }

    public int getGraphWidth() {
        return GraphWidth;
    }

    public int getGraphHeight() {
        return GraphHeight;
    }

    protected void paintGraphPanel() {
        this.graphPanel.paint(this.getGraphics());
    }
    

}
