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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    // declaración de constantes (static) y variables 
    private static int ANCHO = 800;
    static int ALTO = 600;
    static int DIAMETRO_PAJARO = 20;
    private static int GRAVEDAD = 1; // simulación de grasvedad 
    private static int ESPACIO_OBSTACULOS = 200;
    private static int ANCHO_OBSTACULO = 50;
    private static int DISTANCIA_ENTRE_OBSTACULOS = 300;
    private static int VELOCIDAD_OBSTACULOS = 3;

    int yPajaro = ALTO / 2;
    private int velocidadY = 0;
    private List<Rectangle> obstaculos = new ArrayList<>();
    private Random random = new Random(); // random para geenrar obstaculos de tamaño aleatorios
    private Timer temporizador;
    int score = 0;
    private ImageIcon fondoIcon;

    public FlappyBird() {
        // características del panel
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setFocusable(true);
        addKeyListener(this);

        // cargar la imagen de fondo
        fondoIcon = new ImageIcon("flappyfondo.jpg");

        // inicializar el temporizador
        temporizador = new Timer(20, this);
        temporizador.start();

        // generar los primeros obstáculos
        for (int i = 0; i < 3; i++) {
            int altura = random.nextInt(ALTO - ESPACIO_OBSTACULOS - 100) + 50;
            obstaculos.add(new Rectangle(ANCHO + i * DISTANCIA_ENTRE_OBSTACULOS, 
                    0, ANCHO_OBSTACULO, altura));
            obstaculos.add(new Rectangle(ANCHO + i * DISTANCIA_ENTRE_OBSTACULOS, 
                    altura + ESPACIO_OBSTACULOS, ANCHO_OBSTACULO, ALTO - altura - ESPACIO_OBSTACULOS));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // dibujar la imagen de fondo
        fondoIcon.paintIcon(this, g, 0, 0);
        
        // cuerpo del pájaro
        int[] xPoints = {100, 110, 120};
        int[] yPoints = {yPajaro + DIAMETRO_PAJARO / 2, yPajaro, yPajaro + DIAMETRO_PAJARO / 2};
        g.setColor(Color.YELLOW);
        g.fillPolygon(xPoints, yPoints, 3);
        
        // ojos del pájaro
        g.setColor(Color.BLACK);
        g.fillOval(110, yPajaro + DIAMETRO_PAJARO / 4, 5, 5);
        g.fillOval(115, yPajaro + DIAMETRO_PAJARO / 4, 5, 5);
        
        // pico del pájaro
        int[] xPico = {120, 125, 120};
        int[] yPico = {yPajaro + DIAMETRO_PAJARO / 2, yPajaro + DIAMETRO_PAJARO / 2,
            yPajaro + DIAMETRO_PAJARO / 3};
        g.setColor(Color.ORANGE);
        g.fillPolygon(xPico, yPico, 3);
        
        // obstáculos
        
        g.setColor(new Color(34, 177, 76));
        for (Rectangle obstaculo : obstaculos) {
            g.fillRect(obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height);
        }
        // puntaje
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 20, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // simulación de la gravedad
        velocidadY += GRAVEDAD;
        yPajaro += velocidadY;

        // limitar la posición del pájaro dentro de los limites del panel
        if (yPajaro < 0) {
            yPajaro = 0;
            velocidadY = 0;
        } else if (yPajaro + DIAMETRO_PAJARO > ALTO) {
            yPajaro = ALTO - DIAMETRO_PAJARO;
            velocidadY = 0;
        }

        // mover los obstáculos hacia la izquierda continuamente
        for (int i = 0; i < obstaculos.size(); i++) {
            Rectangle obstaculo = obstaculos.get(i);
            obstaculo.x -= VELOCIDAD_OBSTACULOS;
        }
        // eliminar los obstáculos que están fuera del panel y generar nuevos
        if (obstaculos.get(0).x + ANCHO_OBSTACULO < 0) {
            obstaculos.remove(0);
            obstaculos.remove(0);

            int altura = random.nextInt(ALTO - ESPACIO_OBSTACULOS - 100) + 50;
            obstaculos.add(new Rectangle(ANCHO, 0, ANCHO_OBSTACULO, altura));
            obstaculos.add(new Rectangle(ANCHO, altura + ESPACIO_OBSTACULOS, 
                    ANCHO_OBSTACULO, ALTO - altura - ESPACIO_OBSTACULOS));

            score++; // incrementar el puntaje cuando pasa un par de obstáculos
        }
        // comprobar colisiones entre el pájaro y los obstáculos
        for (Rectangle obstaculo : obstaculos) {
            if (obstaculo.intersects(new Rectangle(100, yPajaro, DIAMETRO_PAJARO,
                    DIAMETRO_PAJARO))) {
                temporizador.stop(); // detener el temporizador si hay colisión
                JOptionPane.showMessageDialog(null, "¡Has perdido!");
            }
        }

        // volver a dibujar el panel
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocidadY = -10; // hacer que el pájaro salte al presionar la tecla de espacio
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}