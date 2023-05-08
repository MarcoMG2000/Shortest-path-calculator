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

    private static final String ICONS_ROUTE = "src/mapa/icons/";
    
    private View vista;
    private String mapaRoute;
    
    private final MouseListener labelListener;
    private JLabel[] labelNodos;
    private int nodoActual = -1;
    

    public GraphPanel(View v, int width, int height, String mapaRoute) {
        this.mapaRoute = mapaRoute;
        
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
                System.out.println(me.getComponent().getName());
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if(me.getComponent().getClass().equals(JLabel.class)){
                    nodoActual = Integer.parseInt(me.getComponent().getName());
                    System.out.println("Enter " + nodoActual--);
                    vista.paintComponents(vista.getGraphics());
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                if(me.getComponent().getClass().equals(JLabel.class)){
                    System.out.println("Exit " + ++nodoActual);
                    nodoActual = -1;
                    vista.paintComponents(vista.getGraphics());
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        // DIBUJAMOS EL MAPA DEFAULT = pitiusas
//        ImageIcon icon = new ImageIcon("src/mapa/pitiuses.png");
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

    }

    void reset(String ruta) {
        this.mapaRoute = ruta;
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
            this.labelNodos[i].setName("" + (i+1));
            this.labelNodos[i].setIcon(new ImageIcon(ICONS_ROUTE + "start_icon.png"));
            this.labelNodos[i].setBounds(nodoActual.getX(), nodoActual.getY(), 24, 24);
            this.labelNodos[i].setBorder(new LineBorder(Color.BLACK, 2));            
            this.labelNodos[i].addMouseListener(this.labelListener);

            this.add(this.labelNodos[i++]);
        }
    }
    
}
