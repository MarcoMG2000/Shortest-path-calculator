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

    private View vista;
    
    private final MouseListener labelListener;
    private int nodoActual = -1;
    

    public GraphPanel(View v, int width, int height) {
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        // DIBUJAMOS EL MAPA DEFAULT = pitiusas
        ImageIcon icon = new ImageIcon("src/mapa/pitiuses.png");
//        ImageIcon icon = new ImageIcon("src/mapa/menorca.png");
        g2d.drawImage(icon.getImage(), 0, 0, null);
        
        g2d.setColor(Color.BLUE);
        
        int totalNodos = vista.getModelo().getTotalNodos();
        ArrayList<NodoLectura> nodosL = vista.getModelo().getNodosL();
        
        for (int i = 0; i < totalNodos; i++) {
            
            NodoLectura NodoActual = nodosL.get(i);
            //g2d.fillOval(nodosL.get(i).getX()-2, nodosL.get(i).getY()-2, 5, 5);
                        
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("src/mapa/icons/start_icon.png"));
            label.setBounds(NodoActual.getX(), NodoActual.getY(), 24, 24);
            label.setBorder(new LineBorder(Color.BLACK, 2));
            label.setName("" + (i+1));
            label.addMouseListener(this.labelListener);
            this.add(label);
            
            
            int nAristas = NodoActual.getNAristas();
            
            for (int j = 0; j < nAristas; j++) {
                
                NodoLectura fi = NodoActual.getArista(j).apunta();
                
                if(i==nodoActual) g2d.setStroke(new BasicStroke(10));
                
                g2d.drawLine(NodoActual.getX()+12, NodoActual.getY()+12, fi.getX()+12, fi.getY()+12);
                
                g2d.setStroke(new BasicStroke(3));
            }

        }

    }
    
}
