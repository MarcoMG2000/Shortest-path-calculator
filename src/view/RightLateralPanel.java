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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.TOP;
import javax.swing.border.LineBorder;

/**
 * Panel lateral derecho de la ventana principal.
 */
public class RightLateralPanel extends JPanel {

    private View vista;
    private int x, y, width, height;
    private JButton verGrafo;
    private JPanel panelSolucion;
    private JTextArea caminoSolucion;
    TimePanel timePanel;
    private JComboBox<String> opcionAlg;

    public RightLateralPanel(View v) {
        this.vista = v;
        init();
    }

    private void init() {
        this.setLayout(null);
        this.x = this.vista.getWidth() + 10 - this.vista.MARGENLAT;
        this.y = this.vista.MARGENVER;
        this.width = this.vista.MARGENLAT - 20;
        this.height = this.vista.getHeight() - this.vista.MARGENVER - 40;

        this.setBounds(x, y, width, height);
        this.setBackground(new Color(245, 245, 220));
        this.setBorder(new LineBorder(Color.BLACK, 2));

        // VER GRAFO BUTTON
        this.verGrafo = new JButton("Mostrar Ruta");
        this.verGrafo.setLayout(null);
        this.verGrafo.setBounds(10, height - 100, width - 20, 90);
        this.verGrafo.setForeground(Color.WHITE);
        this.verGrafo.setBackground(Color.BLACK);
        this.verGrafo.setFont(new Font("Arial Black", Font.PLAIN, 16));
        this.add(verGrafo);

        verGrafo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.verGrafoClicked();
            }

        });

        panelSolucion = new JPanel();
        panelSolucion.setLayout(null);
        panelSolucion.setBounds(10, 10, width - 20, (int) (height * 2 / 3) - 10);
        panelSolucion.setBorder(new LineBorder(Color.BLACK, 2));

        caminoSolucion = new JTextArea("SOLUCIÓN");
        caminoSolucion.setFont(new Font("Arial", Font.BOLD, 16));
        caminoSolucion.setBounds(10, 10, panelSolucion.getWidth() - 20, panelSolucion.getHeight() - 20);
        caminoSolucion.setAlignmentX(CENTER);
        caminoSolucion.setAlignmentY(TOP);
        caminoSolucion.setPreferredSize(new Dimension(width - 40, (int) (height * 2 / 3) - 30));
        caminoSolucion.setEditable(false);
        caminoSolucion.setBackground(SystemColor.control);
        panelSolucion.add(caminoSolucion);
        this.add(panelSolucion);

        opcionAlg = new JComboBox<String>(new String[]{"Iterativo", "Recursivo"});
        opcionAlg.setBounds(verGrafo.getX(), verGrafo.getY() - verGrafo.getHeight() / 2, verGrafo.getWidth(), verGrafo.getHeight() / 2);
        opcionAlg.setLocation(panelSolucion.getX(), panelSolucion.getY() + panelSolucion.getHeight() + 10);
        this.add(opcionAlg);

        this.setVisible(true);
    }
    
    public JComboBox<String> getOpcionAlg(){
        return opcionAlg;
    }

    public void setSolucion(String s) {
        caminoSolucion.append("\n" + s);
        caminoSolucion.revalidate();
    }

    private class TimePanel extends JPanel {

        private JLabel timeLabel;

        private TimePanel(int x, int y, int width, int height) {
            this.setBounds(x, y, width, height);

            this.timeLabel = new JLabel("");
            this.add(timeLabel);
        }

        public String getTime() {
            return this.timeLabel.getText();
        }

        public void setTime(long nanoseconds) {
            this.timeLabel.setText(String.valueOf(nanoseconds));
        }
    }

    public void setTime(long nanoseconds) {
        this.timePanel.setTime(nanoseconds);
    }

}
