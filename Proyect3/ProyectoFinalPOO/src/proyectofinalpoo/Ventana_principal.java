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
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Ventana_principal extends JFrame {

    public JButton salir;
    public JMenuBar menuBar;
    public JMenuItem Msalir;
    public JMenuItem Puntajes;
    public JMenuItem Registrarse;
    private RegistroPuntajes registroPuntajes;
    private String nombreJugador;

    public Ventana_principal() {
        // cierre, tamaño y organización de la ventana principal  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1550, 914);
        setLayout(null);
        setUndecorated(true); // esconder la barra de titulo 

        // inicializar el registro de puntajes
        registroPuntajes = new RegistroPuntajes("puntajes.txt");

        // crear, instanciar y colocar imágen de fondo 
        ImageIcon ImagenFondo = new ImageIcon("fondoparapoo.png");
        JLabel ImgFondo = new JLabel(ImagenFondo);
        ImgFondo.setBounds(0, 0, 1550, 914);

        // crear, instancias, fuente y colocar botón de salir funcional 
        salir = new JButton("Salir");
        salir.setBounds(1360, 15, 160, 40);
        salir.setFont(new Font("Roboto", Font.BOLD, 20));
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        add(salir);

        // menu bar para iniciar sesión, registrarse ó salir 
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("MENÚ");
        Msalir = new JMenuItem("Salir");
        Puntajes = new JMenuItem("Ver puntajes más altos");
        Registrarse = new JMenuItem("Registrarse");

        // actionlisteners para cada boton
        Msalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        Registrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nombreJugador = JOptionPane.showInputDialog("Ingrese su nombre:");
            }
        });

        Puntajes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPuntajes();
            }
        });

        // Agregar elementos al menu
        menu.add(Registrarse);
        menu.add(Puntajes);
        menu.add(Msalir);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // botones para cada juego y su actionlistener para ejecutar
        JButton Btictactoe = new JButton("Jugar Tic-Tac-Toe");
        Btictactoe.setBounds(255, 470, 150, 35);
        Btictactoe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuegoTicTacToe();
            }
        });
        add(Btictactoe);

        JButton Bmemorama = new JButton("Jugar Memorama");
        Bmemorama.setBounds(700, 470, 150, 35);
        Bmemorama.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuegoMemorama();
            }
        });
        add(Bmemorama);

        JButton Bpong = new JButton("Jugar Ping Pong");
        Bpong.setBounds(1145, 465, 150, 35);
        Bpong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuegoPong();
            }
        });
        add(Bpong);

        JButton Bbuscaminas = new JButton("Jugar Buscaminas");
        Bbuscaminas.setBounds(485, 765, 150, 35);
        Bbuscaminas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuegoBuscaminas();
            }
        });
        add(Bbuscaminas);

        JButton Bflappybird = new JButton("Jugar Flappy Bird");
        Bflappybird.setBounds(950, 765, 150, 35);
        Bflappybird.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuegoFlappyBird();
            }
        });
        add(Bflappybird);

        add(ImgFondo); // añadir fondo al final 
    }

    private void mostrarPuntajes() {
        Map<String, Integer> puntajes = registroPuntajes.leerPuntajes();
        StringBuilder mensaje = new StringBuilder("Puntajes más altos:\n");
        for (Map.Entry<String, Integer> entry : puntajes.entrySet()) {
            mensaje.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(this, mensaje.toString());
    }

    // ejecución al presionar el boton de cada juego 
    private void iniciarJuegoFlappyBird() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setResizable(false);
        FlappyBird flappyBird = new FlappyBird() {
            @Override
            public void actionPerformed(ActionEvent e) {
                super.actionPerformed(e);
                if (yPajaro + DIAMETRO_PAJARO >= ALTO || yPajaro <= 0) {
                    registroPuntajes.escribirPuntaje(nombreJugador, score);
                }
            }
        };
        frame.add(flappyBird);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void iniciarJuegoPong() {
        JFrame frame = new JFrame("Ping Pong 1 vs. 1");
        frame.setResizable(false);
        frame.add(new Pong());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void iniciarJuegoTicTacToe() {
        TicTacToe tictactoe = new TicTacToe();
        tictactoe.setVisible(true);
    }

    private void iniciarJuegoBuscaminas() {
        Buscaminas buscaminas = new Buscaminas();
    }

    private void iniciarJuegoMemorama() {
        Memorama memorama = new Memorama();
    }
}