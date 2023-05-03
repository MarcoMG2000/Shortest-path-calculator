/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax;

/
import model.NodoLectura;
**
 *
 * @author mascport
 */
public class Arista {
    private NodoLectura apunta;
    private double valor;
    
    public Arista(NodoLectura f, double v) {
        apunta = f;
        valor = v;
    }
    
    public Arista(NodoLectura f) {
        apunta = f;
        valor = 0.0;
    }
    
    public NodoLectura apunta() {
        return apunta;
    }
    
    public double getValor() {
        return valor;
    }
}
