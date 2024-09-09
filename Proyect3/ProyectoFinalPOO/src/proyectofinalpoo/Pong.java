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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends JPanel implements ActionListener, KeyListener {
    private int pelotaX = 250, pelotaY = 250, pelotaXDir = 3, pelotaYDir = 3;
    private int pala1Y = 250, pala2Y = 250;
    private int puntaje1 = 0, puntaje2 = 0;
    private JLabel pelota, pala1, pala2;
    private JLabel etiquetaPuntaje1, etiquetaPuntaje2;
    private Timer temporizador;

    public Pong() {
        setLayout(null); 
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        // crear la pelota y añadirla
        pelota = new JLabel();
        pelota.setOpaque(true); // label relleno para simulae barra
        pelota.setBackground(Color.WHITE);
        pelota.setBounds(pelotaX, pelotaY, 20, 20); // bounds en tamaño son constante,
        // los bounds en x y y son variables para que pueda cambiar con el trimer 
        add(pelota);

        // crear las barras
        pala1 = new JLabel();
        pala1.setOpaque(true);
        pala1.setBackground(Color.BLUE);
        pala1.setBounds(10, pala1Y, 10, 60);
        add(pala1);

        pala2 = new JLabel();
        pala2.setOpaque(true);
        pala2.setBackground(Color.RED);
        pala2.setBounds(480, pala2Y, 10, 60);
        add(pala2);

        // crear etiquetas de puntaje
        etiquetaPuntaje1 = new JLabel("Jugador 1: 0");
        etiquetaPuntaje1.setForeground(Color.WHITE);
        etiquetaPuntaje1.setBounds(20, 20, 100, 20);
        add(etiquetaPuntaje1);

        etiquetaPuntaje2 = new JLabel("Jugador 2: 0");
        etiquetaPuntaje2.setForeground(Color.WHITE);
        etiquetaPuntaje2.setBounds(380, 20, 100, 20);
        add(etiquetaPuntaje2);

        // iniciar temporizador
        temporizador = new Timer(5, this);
        temporizador.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // movimiento de la pelota
        pelotaX += pelotaXDir;
        pelotaY += pelotaYDir;

        // colisión con las palas
        if (pelotaX < 20 && pelotaY + 10 >= pala1Y && pelotaY <= pala1Y + 60) {
            pelotaXDir = -pelotaXDir;
        } else if (pelotaX < 0) {
            // ++ puntaje del jugador 2
            puntaje2++;
            etiquetaPuntaje2.setText("Jugador 2: " + puntaje2);
            reiniciarPelota();
        }

        if (pelotaX > 460 && pelotaY + 10 >= pala2Y && pelotaY <= pala2Y + 60) {
            pelotaXDir = -pelotaXDir;
        } else if (pelotaX > 480) {
            // ++ puntaje del jugador 1
            puntaje1++;
            etiquetaPuntaje1.setText("Jugador 1: " + puntaje1);
            reiniciarPelota();
        }

        // rebote techo y piso 
        if (pelotaY < 0 || pelotaY > 480) {
            pelotaYDir = -pelotaYDir;
        }

        // actualizar posiciones 
        pelota.setBounds(pelotaX, pelotaY, 20, 20);
        pala1.setBounds(10, pala1Y, 10, 60);
        pala2.setBounds(480, pala2Y, 10, 60);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // movimiento del jugador rojo (W y S)
        if (e.getKeyCode() == KeyEvent.VK_W && pala1Y > 0) {
            pala1Y -= 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_S && pala1Y < 440) {
            pala1Y += 10;
        }

        // movimiento del jugador 2 (up y down)
        if (e.getKeyCode() == KeyEvent.VK_UP && pala2Y > 0) {
            pala2Y -= 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && pala2Y < 440) {
            pala2Y += 10;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    private void reiniciarPelota() { // regresa a valores iniciales 
        pelotaX = 250;
        pelotaY = 250;
        pelotaXDir = -pelotaXDir;
    }
}