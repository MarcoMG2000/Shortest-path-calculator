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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
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
public class View extends JFrame{

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
        this.GraphWidth = 800;
        this.GraphHeight = 700;

        // NOT RESIZABLE
        this.setResizable(false);
        this.setLayout(null);
        
        // DIMENSION DEL JFRAME
        setSize(this.GraphWidth + this.MARGENLAT * 2, this.GraphHeight + this.MARGENVER * 3);

        // POSICIONAR EL JFRAME EN EL CENTRO DE LA PANTALLA
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        getContentPane().setBackground(new Color(212, 191, 142));
        
        // GRAPH PANEL
        graphPanel = new GraphPanel(this, GraphWidth, GraphHeight, rutas_mapas[0]);
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

        // SELECCION DE MAPA
        JLabel label = new JLabel("Selección de Mapa:");
        label.setFont(new Font("Britannic Bold", Font.BOLD, 15));
        label.setBounds(getWidth() / 2 - 240, getHeight() - 75, 240, 30);
        this.add(label);
        
        this.JComboMapas = new JComboBox<>(mapas);
        this.JComboMapas.setBounds(getWidth() / 2 - 60, getHeight() - 75, 240, 30);
        this.add(this.JComboMapas);
        this.JComboMapas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                MeuSax sax;
                switch(JComboMapas.getSelectedItem().toString()) {
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
                rightPanel.clearSolucion();
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
    }
    
    protected void verGrafoClicked() throws InterruptedException {
        if(graphPanel.getNodoInicial() == -1){
            new Notification("Seleccione el nodo Inicial");
            return;
        }
        
        if(graphPanel.getNodoDestino() == -1){
            new Notification("Seleccione el nodo Destino");
            return;
        }
        
        modelo.setNodosBloqueados(graphPanel.getNodosBloqueados());
        
        ArrayList<Thread> hilosController = new ArrayList();
        ArrayList<Controller> Controllers = new ArrayList();
        int nodoInicio = graphPanel.getNodoInicial();
        
        Controller c;
        for(Integer nodoIntermedio: graphPanel.getNodosIntermedios()){
            c = new Controller();
            c.setVista(this);
            c.setModelo(modelo);
            
            c.setnInicio(nodoInicio);
            c.setnDestino(nodoIntermedio);
            
            nodoInicio = nodoIntermedio;
            
            hilosController.add(new Thread(c));
            Controllers.add(c);
        }
        
        c = new Controller();
        
        c.setVista(this);
        c.setModelo(modelo);

        c.setnInicio(nodoInicio);
        c.setnDestino(graphPanel.getNodoDestino());
        
        hilosController.add(new Thread(c));
        Controllers.add(c);
        
        long tiempoI = System.nanoTime();
        for(Thread t : hilosController){
            t.start();
        }
        
        for(Thread t : hilosController){
            t.join();
        }
        
        tiempoI = System.nanoTime() - tiempoI;
        rightPanel.setTime(tiempoI);        
        
        ArrayList<Integer> solucion = new ArrayList();
        ArrayList<Integer> Auxsolucion;
        
        int iteracion = 1;
        for(Controller cActual : Controllers){
            Auxsolucion = new ArrayList();
            
            if(iteracion != 1) solucion.remove(solucion.size()-1);
            
            int[] camino = cActual.getNodosPrevios();
            int destino = cActual.getnDestino();
            Auxsolucion.add(destino);
            
            int previo = camino[destino];
            Auxsolucion.add(previo);
            
            while (previo != -1) {
                previo = camino[previo];
                Auxsolucion.add(previo);
            }
            
            
            Auxsolucion.remove(Auxsolucion.size()-1);
            Collections.reverse(Auxsolucion);
            System.out.println("Iteracion " + iteracion + ": " + Auxsolucion);
            
            solucion.addAll(Auxsolucion);
            
            iteracion++;

        }
        
        
        System.out.println(solucion);
        
        /**
        solucion = new ArrayList();
        int[] camino = vista.getModelo().getNodosPrevios();
        int destino = this.nodoDestino-1;
        solucion.add(destino);
        int previo = camino[destino];
        solucion.add(previo);
        System.out.println(previo);
        while (previo != -1) {
            previo = camino[previo];
            solucion.add(previo);
            System.out.println(previo);
        }
        solucion.remove(solucion.size()-1);
        System.out.println(solucion);
        Collections.reverse(solucion);
        **/
                
        this.graphPanel.guardarCamino(solucion);
        this.graphPanel.repaint();

    //Necesito usar System.lineSeparator porque con \n no hace salto de linea
        this.rightPanel.clearSolucion();
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

    public void setModelo(Model modelo) {
        this.modelo = modelo;
    }

    public int getGraphWidth() {
        return GraphWidth;
    }

    public int getGraphHeight() {
        return GraphHeight;
    }

    public RightLateralPanel getrightPanel() {
        return rightPanel;
    }

    public GraphPanel getgraphPanel() {
        return graphPanel;
    }
    
    protected void paintGraphPanel() {
        this.graphPanel.paint(this.getGraphics());
    }

    private void initModel() {
        MeuSax sax = new MeuSax("pitiuses.ltim", modelo);
        sax.llegir();
        modelo.actualizarNNodos();
    }

    protected LeftLateralPanel getLeftPanel() {
        return this.leftPanel;
    }
    
}
