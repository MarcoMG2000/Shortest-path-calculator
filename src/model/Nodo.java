/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author jfher
 */
public class Nodo {

    private int nNodo;
    private ArrayList<Integer[]> adjacentes;

    public Nodo(int n) {
        nNodo = n;
        adjacentes = new ArrayList<>();
    }

    public void añadirNodoAdj(Integer[] n) {
        adjacentes.add(n);
    }

    public int getnNodo() {
        return nNodo;
    }

    public ArrayList<Integer[]> getAdjacentes() {
        return adjacentes;
    }

    public void setAdjacentes(ArrayList<Integer[]> adjacentes) {
        this.adjacentes = adjacentes;
    }

}
