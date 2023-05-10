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
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.TOP;
import javax.swing.border.LineBorder;
import model.Nodo;
import static view.GraphPanel.BLOQUED_ICON;
import static view.GraphPanel.END_ICON;
import static view.GraphPanel.ICONS_ROUTE;
import static view.GraphPanel.INT_ICON;
import static view.GraphPanel.START_ICON;

/**
 * Panel lateral izquierdo de la ventana principal.
 */
public class LeftLateralPanel extends JPanel {

    private View vista;
    private JTextArea PuebloActual;
    private int x, y;
    private int width, height;
    
    private JLabel iconoActual = new JLabel();
    
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
        PuebloActual.setBounds(10, 10, this.width - 20, this.height - 260);
        PuebloActual.setBorder(new LineBorder(Color.BLACK, 2));
        PuebloActual.setAlignmentX(CENTER);
        PuebloActual.setAlignmentY(TOP);
        PuebloActual.setEditable(false);
        PuebloActual.setBackground(SystemColor.control);
        
        this.add(PuebloActual);
        
        
         MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                iconoActual.setBorder(null);
                vista.getgraphPanel().setTipoNodo(NodeTypeGraph.values()[Integer.parseInt(e.getComponent().getName())]);
                iconoActual = (JLabel) e.getComponent();
                iconoActual.setBackground(Color.LIGHT_GRAY);
                iconoActual.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(Color.BLACK, 2),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
                System.out.println(e.getComponent().getName());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        
        JLabel labelText;
        
        // ICONO START
        ImageIcon icon = START_ICON;
        JLabel labelStart = new JLabel(icon);
        
        labelStart.setBounds(10,520,50,50);
        labelStart.setBackground(Color.LIGHT_GRAY);
        labelStart.setName("1");
        labelStart.addMouseListener(listener);
        
        labelText = new JLabel("Nodo Inicial");
        labelText.setBounds(80,520,150,50);
        
        add(labelText);
        add(labelStart);
        
        // ICONO END
        icon = END_ICON;
        JLabel labelEnd = new JLabel(icon);

        labelEnd.setBounds(10,580,50,50);
        labelEnd.setBackground(Color.LIGHT_GRAY);

        labelEnd.setName("2");
        labelEnd.addMouseListener(listener);

        labelText = new JLabel("Nodo Final");
        labelText.setBounds(80,580,150,50);
        
        add(labelText);
        add(labelEnd);
        
        // ICONO INTERMEDIO
        icon = INT_ICON;
        JLabel labelIntermedio = new JLabel(icon);

        labelIntermedio.setBounds(10,640,50,50);
        labelIntermedio.setBackground(Color.LIGHT_GRAY);

        labelIntermedio.setName("3");
        labelIntermedio.addMouseListener(listener);

        labelText = new JLabel("Nodo Intermedio");
        labelText.setBounds(80,640,150,50);
        
        add(labelText);
        add(labelIntermedio);
        
        // ICONO BLOQUEADO
        icon = BLOQUED_ICON;
        JLabel labelBloqueado = new JLabel(icon);

        labelBloqueado.setBounds(10,700,50,50);
        labelBloqueado.setBackground(Color.LIGHT_GRAY);

        labelBloqueado.setName("4");
        labelBloqueado.addMouseListener(listener);

        labelText = new JLabel("Nodo Bloqueado");
        labelText.setBounds(80,700,150,50);
        
        add(labelText);
        add(labelBloqueado);        
        
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
