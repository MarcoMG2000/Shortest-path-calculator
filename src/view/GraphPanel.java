/**
 * Practica 4 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 12/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url 
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import sax.NodoLectura;

/**
 * Panel para pintar los puntos generados.
 */
public class GraphPanel extends JPanel {

    private static final String ICONS_ROUTE     = "src/mapa/icons/";
    
    private static final ImageIcon DEFAULT_ICON = new ImageIcon(ICONS_ROUTE + "start_icon.png");
    private static final ImageIcon START_ICON   = new ImageIcon(ICONS_ROUTE + "start_icon.png");
    private static final ImageIcon END_ICON     = new ImageIcon(ICONS_ROUTE + "finish_icon.png");
    private static final ImageIcon INT_ICON     = new ImageIcon(ICONS_ROUTE + "start_icon.png");
    private static final ImageIcon BLOQUED_ICON = new ImageIcon(ICONS_ROUTE + "start_icon.png");

    private View vista;
    private String mapaRoute;
    
    private final MouseListener labelListener;
    private JLabel[] labelNodos;
    private int nodoActual = -1;
    
    private int nodoInicial = -1;
    private int nodoDestino = -1;
    private ArrayList<Integer> nodosIntermedios;
    private ArrayList<Integer> nodosBloqueados;
    
    private NodeTypeGraph nodeType;
    
    private ArrayList<Integer> solucion;
    private boolean isShowingSolution = false;

    public GraphPanel(View v, int width, int height, String mapaRoute) {
        this.mapaRoute = mapaRoute;
        
        this.solucion = new ArrayList();
        this.nodosIntermedios = new ArrayList();
        this.nodosBloqueados = new ArrayList();
        
        this.nodeType = NodeTypeGraph.DEFAULT;
        
        vista = v;
        Border borde = new LineBorder(Color.BLACK, 2);
        setBorder(borde);
        setLayout(null);
        setBounds(vista.MARGENLAT, vista.MARGENVER,
                width, height);
        setBackground(Color.WHITE);

        
        this.labelListener = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent me) {
                if(!isShowingSolution & me.getComponent().getClass().equals(JLabel.class)){
                    // Si no hay ningún tipo de nodo seleccionado
                    if(nodeType == NodeTypeGraph.DEFAULT) return;
                    
                    String[] nodoText = me.getComponent().getName().split(":");
                    int IndxNode = Integer.parseInt(nodoText[0]);
                    
                    if(me.getButton() == MouseEvent.BUTTON1){ // LEFT CLICK

                        switch(nodeType){
                            case INICIAL:
                                if(nodoInicial != -1){
                                    labelNodos[nodoInicial-1].setName("" + nodoInicial + ":default");
                                    labelNodos[nodoInicial-1].setIcon(DEFAULT_ICON);
                                }

                                if("destino".equals(nodoText[1])) nodoDestino = -1;
                                if("intermedio".equals(nodoText[1])) nodosIntermedios.remove(nodosIntermedios.indexOf(IndxNode));
                                if("bloqueado".equals(nodoText[1])) nodosBloqueados.remove(nodosBloqueados.indexOf(IndxNode));
                                
                                nodoInicial = IndxNode;
                                labelNodos[nodoInicial-1].setName("" + nodoInicial + ":inicial");
                                labelNodos[nodoInicial-1].setIcon(START_ICON);
                                
                                break;
                                
                            case DESTINO:
                                if(nodoDestino != -1){
                                    labelNodos[nodoDestino-1].setName("" + nodoDestino + ":default");
                                    labelNodos[nodoDestino-1].setIcon(DEFAULT_ICON);
                                }
                                
                                if("inicial".equals(nodoText[1])) nodoInicial = -1;
                                if("intermedio".equals(nodoText[1])) nodosIntermedios.remove(nodosIntermedios.indexOf(IndxNode));
                                if("bloqueado".equals(nodoText[1])) nodosBloqueados.remove(nodosBloqueados.indexOf(IndxNode));

                                
                                nodoDestino = IndxNode;
                                labelNodos[nodoDestino-1].setName("" + nodoDestino + ":destino");
                                labelNodos[nodoDestino-1].setIcon(END_ICON);
                                
                                break;
                                
                            case INTERMEDIO:
                                
                                if("inicial".equals(nodoText[1])) nodoInicial = -1;
                                if("destino".equals(nodoText[1])) nodoDestino = -1;
                                if("intermedio".equals(nodoText[1])) nodosIntermedios.remove(nodosIntermedios.indexOf(IndxNode));
                                if("bloqueado".equals(nodoText[1])) nodosBloqueados.remove(nodosBloqueados.indexOf(IndxNode));
                                 
                                nodosIntermedios.add(IndxNode); //Reiniciamos y le posicionamos en la última posición del arrayList
                                
                                labelNodos[IndxNode-1].setName("" + IndxNode + ":intermedio");
                                labelNodos[IndxNode-1].setIcon(INT_ICON);

                                break;
                            case BLOQUEADO:

                                if("inicial".equals(nodoText[1])) nodoInicial = -1;
                                if("destino".equals(nodoText[1])) nodoDestino = -1;
                                if("intermedio".equals(nodoText[1])) nodosIntermedios.remove(nodosIntermedios.indexOf(IndxNode));
                                if("bloqueado".equals(nodoText[1])) nodosBloqueados.remove(nodosBloqueados.indexOf(IndxNode));
                                 
                                nodosBloqueados.add(IndxNode); //Reiniciamos y le posicionamos en la última posición del arrayList
                                
                                labelNodos[IndxNode-1].setName("" + IndxNode + ":bloqueado");
                                labelNodos[IndxNode-1].setIcon(BLOQUED_ICON);
                                
                                break;
                            default:
                                return;
                        }
                    }
                    
                    if(me.getButton() == MouseEvent.BUTTON3){ // RIGHT CLICK
                        
                        if("inicial".equals(nodoText[1])) nodoInicial = -1;
                        if("destino".equals(nodoText[1])) nodoDestino = -1;
                        if("intermedio".equals(nodoText[1])) nodosIntermedios.remove(nodosIntermedios.indexOf(IndxNode));
                        if("bloqueado".equals(nodoText[1])) nodosBloqueados.remove(nodosBloqueados.indexOf(IndxNode));
                        
                        labelNodos[IndxNode-1].setName("" + IndxNode + ":default");
                        labelNodos[IndxNode-1].setIcon(DEFAULT_ICON);

                    }
                    
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if(!isShowingSolution & me.getComponent().getClass().equals(JLabel.class)){
                    String[] nodoText = me.getComponent().getName().split(":");
                    nodoActual = Integer.parseInt(nodoText[0]) - 1;
                    System.out.println("Enter " + nodoActual);
                    vista.getLeftPanel().setPuebloActual(nodoActual);
                    vista.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                if(!isShowingSolution & me.getComponent().getClass().equals(JLabel.class)){
                    System.out.println("Exit " + ++nodoActual);
                    nodoActual = -1;
                    vista.getLeftPanel().clearPuebloActual();
                    vista.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {}
            @Override
            public void mouseReleased(MouseEvent me) {}
        };
        
        initLabels();
        
        
    }

    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        BufferedImage img = new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) img.getGraphics();
        
        ImageIcon icon = new ImageIcon(this.mapaRoute);
        g2d.drawImage(icon.getImage(), 0, 0, null);
        
        g2d.setColor(Color.BLUE);
        
        int totalNodos = vista.getModelo().getTotalNodos();
        ArrayList<NodoLectura> nodosL = vista.getModelo().getNodosL();
        
        for (int i = 0; i < totalNodos; i++) {
            
            NodoLectura NodoActual = nodosL.get(i);
            //g2d.fillOval(nodosL.get(i).getX()-2, nodosL.get(i).getY()-2, 5, 5);
                        
            int nAristas = NodoActual.getNAristas();
            
            for (int j = 0; j < nAristas; j++) {
                
                NodoLectura fi = NodoActual.getArista(j).apunta();
                
                if(i==nodoActual) g2d.setStroke(new BasicStroke(10));
                
                g2d.drawLine(NodoActual.getX()+12, NodoActual.getY()+12, fi.getX()+12, fi.getY()+12);
                
                g2d.setStroke(new BasicStroke(3));
            }

        }
        
        if (isShowingSolution) {
            NodoLectura nodoAnterior = null;
            g2d.setColor(Color.GREEN);

            g2d.setStroke(new BasicStroke(8));

            nodoAnterior = nodosL.get(15);
            
            for (Integer indice : solucion) {
                NodoLectura nodoActual = nodosL.get((int) indice);

                g2d.drawLine(nodoAnterior.getX() + 12, nodoAnterior.getY() + 12,
                        nodoActual.getX() + 12, nodoActual.getY() + 12);
                
                nodoAnterior = nodoActual;
            }
        }
        
        g.drawImage(img, 0, 0, this);

    }

    void reset(String ruta) {
        this.mapaRoute = ruta;
        isShowingSolution = false;
        this.solucion = null;
        for(JLabel label: labelNodos){
            this.remove(label);
        }
        initLabels();
    }

    private void initLabels() {
        int i = 0;
        this.labelNodos = new JLabel[vista.getModelo().getTotalNodos()];
        for (NodoLectura nodoActual: vista.getModelo().getNodosL()) {
            
            this.labelNodos[i] = new JLabel();
            this.labelNodos[i].setName("" + (i+1) + ":none");
            this.labelNodos[i].setIcon(new ImageIcon(ICONS_ROUTE + "start_icon.png"));
            this.labelNodos[i].setBounds(nodoActual.getX(), nodoActual.getY(), 24, 24);
            this.labelNodos[i].setBorder(new LineBorder(Color.BLACK, 2));            
            this.labelNodos[i].addMouseListener(this.labelListener);

            this.add(this.labelNodos[i++]);
        }
    }
    
    public void guardarCamino() {
        solucion = new ArrayList();
        int[] camino = vista.getModelo().getNodosPrevios();
        int destino = 16;
        int previo = camino[destino - 1];
        solucion.add(previo);
        System.out.println(previo);
        while (previo != -1) {
            previo = camino[previo];
            solucion.add(previo);
            System.out.println(previo);
        }
        solucion.remove(solucion.size()-1);
        isShowingSolution = true;
    }

    protected void setTipoNodo(NodeTypeGraph nodeType){
        this.nodeType = nodeType;
    }
    
    
    protected ArrayList<Integer> getSolucion() {
        return solucion;
    }
    
    protected int getNodoInicial() {
        return this.nodoInicial;
    }

    protected int getNodoDestino() {
        return this.nodoDestino;
    }
    
    
    
}
