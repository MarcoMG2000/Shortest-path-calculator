/**
 * Practica 4 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 12/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Panel lateral derecho de la ventana principal.
 */
public class RightLateralPanel extends JPanel {

    private View vista;
    private int x, y, width, height;
    private JButton verGrafo;

    TimePanel timePanel;

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
        this.setBackground(Color.red);
        this.setBorder(new LineBorder(Color.BLACK, 2));
        
        // VER GRAFO BUTTON
        this.verGrafo = new JButton("Mostrar Ruta");
        this.verGrafo.setLayout(null);
        this.verGrafo.setBounds(10, height - 100, width - 20, 90);
        this.add(verGrafo);
        
        verGrafo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vista.verGrafoClicked();
            }

        });

        this.setVisible(true);

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
