/**
 * Practica 4 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 12/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Scrollable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Panel para pintar los puntos generados.
 */
public class GraphPanel extends JPanel {

    private View vista;
    

    public GraphPanel(View v, int width, int height) {
        vista = v;
        Border borde = new LineBorder(Color.BLACK, 2);
        setBorder(borde);
        setLayout(null);
        setBounds(vista.MARGENLAT, vista.MARGENVER,
                width, height);
        setBackground(Color.WHITE);
        
        /*Imagen del mapa del GraphPanel*/
        JLabel fondo;
        ImageIcon img = new ImageIcon("src/mapa/pitiuses.png");
        fondo = new JLabel("", img, JLabel.CENTER);
        fondo.setBounds(0, 0, getWidth(), getHeight());
        add(fondo);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
    
}
