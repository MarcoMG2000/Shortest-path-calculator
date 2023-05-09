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
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import model.Nodo;
import sax.MeuSax;

/**
 * Vista de la aplicación, aquí interactuaremos con la aplicación y
 * visualizaremos todos los datos y los resultados de las operaciónes.
 */
public class View extends JFrame {

    // PUNTEROS DEL PATRÓN MVC
    private Controller controlador;
    private Model modelo;

    // CONSTANTES DE LA VISTA
    protected final int MARGENLAT = 300;
    protected final int MARGENVER = 50;

    // VARIABLES DEL JPanel
    private int GraphWidth;
    private int GraphHeight;

    private LeftLateralPanel leftPanel;
    private RightLateralPanel rightPanel;
    private GraphPanel graphPanel;

    private JComboBox<String> JComboMapas;
    protected final String[] mapas = {"Eivissa i Formentera", "Menorca", "Mallorca"};
    protected final String[] rutas_mapas = {"src/mapa/pitiuses.png", "src/mapa/menorca.png", "src/mapa/mallorca.png"};

    // CONSTRUCTORS
    public View() {
    }

    public View(Controller controlador, Model modelo) {
        this.controlador = controlador;
        this.modelo = modelo;
        initModel();
    }

    // CLASS METHODS
    /**
     * Clase que inicializa la ventana principal y añade todos los elementos al
     * JFrame.
     */
    public void mostrar() {
        this.GraphWidth = 700;
        this.GraphHeight = 600;

        // NOT RESIZABLE
        this.setResizable(false);
        this.setLayout(null);

        // DIMENSION DEL JFRAME
        setSize(this.GraphWidth + this.MARGENLAT * 2, this.GraphHeight + this.MARGENVER * 3);

        // POSICIONAR EL JFRAME EN EL CENTRO DE LA PANTALLA
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // GRAPH PANEL
        graphPanel = new GraphPanel(this, GraphWidth, GraphHeight, rutas_mapas[0]);
        this.add(graphPanel);
        // Agregamos un listener para capturar el clic  
        graphPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX() + ", " + e.getY());
            }
        });

        // PANELES LATERALES
        leftPanel = new LeftLateralPanel(this);
        this.add(leftPanel);

        rightPanel = new RightLateralPanel(this);
        this.add(rightPanel);

        // SELECCION DE MAPA
        JLabel label = new JLabel("Selección de Mapa:");
        label.setFont(new Font("Britannic Bold", Font.BOLD, 15));
        label.setBounds(getWidth() / 2 - 240, getHeight() - 75, 240, 30);
        this.add(label);

        this.JComboMapas = new JComboBox<>(mapas);
        this.JComboMapas.setBounds(getWidth() / 2 - 60, getHeight() - 75, 240, 30);
        this.add(this.JComboMapas);
        this.JComboMapas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                MeuSax sax;
                switch (JComboMapas.getSelectedItem().toString()) {
                    case "Eivissa i Formentera":
                        modelo = new Model();
                        //Actualizamos modelo
                        sax = new MeuSax("pitiuses.ltim", modelo);
                        sax.llegir();
                        modelo.actualizarNNodos();

                        // Actualizamos vista
                        graphPanel.reset(rutas_mapas[0]);
                        break;
                    case "Menorca":
                        modelo = new Model();
                        //Actualizamos modelo
                        sax = new MeuSax("menorca.ltim", modelo);
                        sax.llegir();
                        modelo.actualizarNNodos();

                        // Actualizamos vista
                        graphPanel.reset(rutas_mapas[1]);
                        break;
                    case "Mallorca":
                        modelo = new Model();
                        //Actualizamos modelo
                        sax = new MeuSax("mallorca.ltim", modelo);
                        sax.llegir();
                        modelo.actualizarNNodos();

                        // Actualizamos vista
                        graphPanel.reset(rutas_mapas[2]);
                        break;
                    default:
                        System.out.println("Error: algoritmo de rescalación '" + JComboMapas.getSelectedItem().toString() + "' no encontrado");
                        return;
                }
                graphPanel.repaint();
            }
        });

        // TITULO
        label = new JLabel("Visualizador de Rutas: Djistra y Comercio");
        label.setFont(new Font("Britannic Bold", Font.BOLD, 15));
        label.setHorizontalAlignment(0);
        label.setBounds(getWidth() / 2 - 200, 10, 400, 30);
        this.add(label);

        // ÚLTIMOS AJUSTES
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.paintComponents(this.getGraphics());
    }

    protected void verGrafoClicked() {
        Controller c1 = new Controller();
        //graphpanel.getNodoInicial()
        //graphpanel.getNodosIntermedios
        c1.setModelo(modelo);
        c1.setnInicio(20);
        c1.setnDestino(16);
        c1.dijkstra();
        this.graphPanel.guardarCamino();
        this.graphPanel.repaint();
        //Necesito usar System.lineSeparator porque con \n no hace salto de linea
        this.rightPanel.setSolucion(
                getStringSolucion(this.graphPanel.getSolucion()));
    }

    private String getStringSolucion(ArrayList<Integer> sol) {
        String s = "";
        int nodo;
        int total = 0;
        Nodo n;
        Integer[] n2 = null;
        ArrayList<Integer[]> adj;
        for (int i = 0; i < sol.size(); i++) {
            nodo = sol.get(i);
            n = modelo.getGrafo().get(nodo);
            adj = n.getAdjacentes();
            s += n.getNombreNodo() + "\n";
            if (i < sol.size() - 1) {
                for (int j = 0; j < adj.size(); j++) {
                    n2 = adj.get(j);
                    if (n2[1] == sol.get(i + 1)) {
                        break;
                    }
                }
                total += n2[1];
                s += n2[1] + " km ↓\n";
            }else{
               s += "TOTAL = "+ total + " km\n"; 
            }
        }
        return s;
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

    public RightLateralPanel getrightPanel() {
        return rightPanel;
    }

    public GraphPanel getgraphPanel() {
        return graphPanel;
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

    private void initModel() {
        MeuSax sax = new MeuSax("pitiuses.ltim", modelo);
        sax.llegir();
        modelo.actualizarNNodos();
    }

}
