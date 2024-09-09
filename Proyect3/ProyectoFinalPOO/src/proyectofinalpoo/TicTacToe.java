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

public class TicTacToe extends JFrame {

    private JButton[][] botones;
    private char jugadorActual;

    public TicTacToe() {
        // layout, título y tamaño de la ventana
        setTitle("Tic-Tac-Toe");
        setSize(350, 350);
        setResizable(false); 
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(3, 3, 0, 0)); 

        botones = new JButton[3][3]; 
        jugadorActual = 'X'; // empeiza siepre el jugador x

        Font fuente = new Font("Arial", Font.BOLD, 36); // tamaño de la fuente
        Color beige = new Color(210, 180, 140);  // color de los botones
          
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton boton = new JButton(); // crea los botones
                boton.setFont(fuente); // selecciona la fuente 
                boton.setBackground(beige); // cambia el color al beoge 
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton botonClickeado = (JButton) e.getSource();
                        if (botonClickeado.getText().isEmpty()) {
                            botonClickeado.setText(String.valueOf(jugadorActual));
                            if (hayGanador(jugadorActual)) {
                                JOptionPane.showMessageDialog(TicTacToe.this, 
                                        "¡Jugador " + jugadorActual + " gana!");
                                reiniciarJuego();
                            } else {
                                jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
                            }
                        }
                    }
                });
                botones[i][j] = boton;
                add(boton);
            }
        }
    }

    private boolean hayGanador(char jugador) {
        // verificar si se ganó checando si las 3 casillas tienen una X o O
        for (int i = 0; i < 3; i++) {
            if (botones[i][0].getText().equals(String.valueOf(jugador)) &&
                botones[i][1].getText().equals(String.valueOf(jugador)) &&
                botones[i][2].getText().equals(String.valueOf(jugador))) {
                return true; // Se verifica como matriz si coinciden sus finals
            }
            if (botones[0][i].getText().equals(String.valueOf(jugador)) &&
                botones[1][i].getText().equals(String.valueOf(jugador)) &&
                botones[2][i].getText().equals(String.valueOf(jugador))) {
                return true; // se verifica como matriz si coinciden las columnas
            }
        }

        if (botones[0][0].getText().equals(String.valueOf(jugador)) &&
            botones[1][1].getText().equals(String.valueOf(jugador)) &&
            botones[2][2].getText().equals(String.valueOf(jugador))) {
            return true; // verifica diagonal principal
        }
        if (botones[0][2].getText().equals(String.valueOf(jugador)) &&
            botones[1][1].getText().equals(String.valueOf(jugador)) &&
            botones[2][0].getText().equals(String.valueOf(jugador))) {
            return true; // verifica diagonal secundaria
        }
        return false;
    }

    private void reiniciarJuego() { // settea el texto de todos los botones en blanco y reinicia el turno
        jugadorActual = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText("");
            }
        }
    }
}