/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinalpoo;

/**
 *
 * @author emili
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Buscaminas extends JFrame {
    private List<Point> minas; // lista que guarda las posiciones random de minas
    private JButton[][] botones; // matriz de tablero para cada casilla
    private boolean[][] reveladas; // matriz para ver si ya fue revelada o no 
    
    public Buscaminas() {
        setTitle("Buscaminas: Evita las 10 bombas 💣");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false); 
        setLayout(new GridLayout(10, 10, 3, 3));

        // inicializar la lista de minas
        minas = new ArrayList<>();
        // inicializar la matriz de casillas reveladas
        reveladas = new boolean[10][10];
        // función para colocar las minas aleatorieamnte 
        colocarMinas();

        // inicializar el arreglo de botones
        botones = new JButton[10][10];
        // crear y añadir los botones al tablero
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton boton = new JButton();
                boton.setBackground(Color.GRAY);
                add(boton);
                botones[i][j] = boton; // añadir el botón al arreglo
                
                final int fila = i;
                final int columna = j;
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton botonClic = (JButton) e.getSource();
                        Point posicionClic = new Point(fila, columna);
                        if (minas.contains(posicionClic)) {
                            // mostrar que hay una mina en esta posición
                            botonClic.setText("💥");
                            JOptionPane.showMessageDialog(null, "💣💀 ¡Has perdido! 💀💣",
                                    "Juego Terminado", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            // revelar casilla actual y todas las vecinas sin minas
                            revelarCasillas(fila, columna);
                        }
                        // Inhabilitar el botón después presionarl
                        botonClic.setEnabled(false);
                    }
                });
            }
        }
        setVisible(true);
    }
    // función para generar las posiciones de minas aleatorias
    private void colocarMinas() {
        // cantidad de minas en el tablero 
        int minasPorColocar = 10;
        while (minasPorColocar > 0) {
            int x = (int) (Math.random() * 10); // establece coordenada x aleatoria
            int y = (int) (Math.random() * 10); // coordenada y aleatoria
            Point posicionMina = new Point(x, y);
            if (!minas.contains(posicionMina)) {
                minas.add(posicionMina); // agrega la posición de la mina a la lista
                minasPorColocar--; // resta 1 al número de minas por generar
            }
        }
    }
    // funcion paera revelar las casillas vecinas q no tengan minas 
    private void revelarCasillas(int fila, int columna) {
        revelarCasilla(fila, columna); // revelar casilla actual
        // revelar casillas vecinas
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < 10 && j >= 0 && j < 10 && !minas.contains(new Point(i, j))) {
                    revelarCasilla(i, j);
                }
            }
        }
    }
    // funcion que revela la casilla; la cambia de color y desactiva
    private void revelarCasilla(int fila, int columna) {
        if (!reveladas[fila][columna]) {
            JButton boton = botones[fila][columna];
            reveladas[fila][columna] = true;
            boton.setBackground(Color.WHITE);
            boton.setEnabled(false);
        }
    }
}