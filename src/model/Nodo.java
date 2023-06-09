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
    private String nom;
    private ArrayList<Integer[]> adjacentes;

    public Nodo(int n, String nom) {
        nNodo = n;
        this.nom = nom;
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

    public String getNombreNodo() {
        return this.nom;
    }

}
