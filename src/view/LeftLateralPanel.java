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
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.TOP;
import javax.swing.border.LineBorder;
import model.Nodo;

/**
 * Panel lateral izquierdo de la ventana principal.
 */
public class LeftLateralPanel extends JPanel {

    private View vista;
    private JTextArea PuebloActual;
    private int x, y;
    private int width, height;
    
    /**
     * Panel Lateral izquierdo encargado de la configuración del algoritmo y los
     * datos de la aplicación
     */
    public LeftLateralPanel(View v) {
        this.vista = v;
        
        this.setLayout(null);
        this.x = 10;
        this.y = this.vista.MARGENVER;
        this.width = this.vista.MARGENLAT - 20;
        this.height = this.vista.getHeight() - this.vista.MARGENVER - 40;

        this.setBounds(x, y, width, height);
        this.setBackground(new Color(245, 245, 220));
        this.setBorder(new LineBorder(Color.BLACK, 2));
        
        this.init();
    }

    /**
     * Método encargado de la inicialización del JPanel y todos los componentes
     * que lo componen (JLabels, JComboBoxs y otros JPanels)
     */
    private void init() {
        PuebloActual = new JTextArea("");
        PuebloActual.setFont(new Font("Arial", Font.BOLD, 16));
        PuebloActual.setBounds(10, 10, this.width - 20, this.height - 200);
        PuebloActual.setBorder(new LineBorder(Color.BLACK, 2));
        PuebloActual.setAlignmentX(CENTER);
        PuebloActual.setAlignmentY(TOP);
        PuebloActual.setEditable(false);
        PuebloActual.setBackground(SystemColor.control);
        
        this.add(PuebloActual);
    }
    
    protected void setPuebloActual(int puebloActual) {
        ArrayList<Nodo> grafo = this.vista.getModelo().getGrafo();
        Nodo nodoActual = grafo.get(puebloActual);
        
        this.PuebloActual.setText("Pueblo " + puebloActual + " :\n" + nodoActual.getNombreNodo());
        this.PuebloActual.append("\n\nPueblos Vecinos:");
        
        Nodo nodoVecino;
        for(Integer[] adj : nodoActual.getAdjacentes()){
            nodoVecino = grafo.get(adj[0] - 1);
            this.PuebloActual.append("\n  " + nodoVecino.getNombreNodo() + "\n  " + adj[1] +" km ↓");
        };
    }
    
    protected void clearPuebloActual() {
        this.PuebloActual.setText("");
    }

}
