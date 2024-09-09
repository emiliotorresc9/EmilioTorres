/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinalpoo;

/**
 *
 * @author emili
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author emili
 */
public class Memorama extends JFrame{
    private JPanel panel;
    private JButton[] cards;
    private int gridRows = 4;
    private int gridCols = 3;
    private int[] cardValues;
    private Map<Integer, ImageIcon> cardImages;

    private int firstCardIndex = -1;
    private int secondCardIndex = -1;
    private boolean isClickable = true;
    private int pairsFound = 0;

    public Memorama() {
        setTitle("Memorama");
        setSize(600, 600); 
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(gridRows, gridCols, 5, 5));
        add(panel, BorderLayout.CENTER);

        cargarImagenes();
        reiniciarJuego();

        setVisible(true);
    }

    private void reiniciarJuego() {
        panel.removeAll();

        cardValues = generarValoresTarjetas(gridRows * gridCols);
        cards = new JButton[gridRows * gridCols];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new JButton("?");
            cards[i].setBackground(Color.LIGHT_GRAY); 
            cards[i].setFont(new Font("Arial", Font.BOLD, 20));
            cards[i].setPreferredSize(new Dimension(100, 100)); // Tamaño cuadrado para las tarjetas
            cards[i].addActionListener(new CardClickListener(i));
            panel.add(cards[i]);
        }

        panel.revalidate();
        panel.repaint();
    }

    private int[] generarValoresTarjetas(int totalCards) {
        int[] values = new int[totalCards / 2];

        for (int i = 0; i < values.length; i++) {
            values[i] = i + 1;
        }

        int[] allValues = new int[totalCards];
        for (int i = 0; i < values.length; i++) {
            allValues[i] = values[i];
            allValues[i + values.length] = values[i];
        }

        for (int i = 0; i < allValues.length; i++) {
            int randomIndex = (int) (Math.random() * allValues.length);
            int temp = allValues[randomIndex];
            allValues[randomIndex] = allValues[i];
            allValues[i] = temp;
        }

        return allValues;
    }

    private void voltearTarjeta(int index) {
        ImageIcon icon = cardImages.get(cardValues[index]);
        if (icon != null) {
            java.awt.Image img = icon.getImage();
            java.awt.Image scaledImg = img.getScaledInstance(cards[index].getWidth(), cards[index].getHeight(), java.awt.Image.SCALE_SMOOTH);
            cards[index].setIcon(new ImageIcon(scaledImg));
        }
        cards[index].setText("");
    }

    private void cargarImagenes() {
        cardImages = new HashMap<>();
        // Agregar los nombres de tus imágenes aquí
        cardImages.put(1, new ImageIcon("Torre_Eiffel.png"));
        cardImages.put(2, new ImageIcon("Cristo_Redentor.png"));
        cardImages.put(3, new ImageIcon("Estatua_Libertad.png"));
        cardImages.put(4, new ImageIcon("Coliseo_Romano.png"));
        cardImages.put(5, new ImageIcon("Piramide_chichenitza.png"));
        cardImages.put(6, new ImageIcon("Muralla_China.png"));

        // Comprobación de carga de imágenes
        for (Map.Entry<Integer, ImageIcon> entry : cardImages.entrySet()) {
            if (entry.getValue().getIconWidth() == -1) {
                System.err.println("Error cargando la imagen: " + entry.getKey());
            }
        }
    }

    private void verificarFinDelJuego() {
        if (pairsFound == (gridRows * gridCols) / 2) {
            JOptionPane.showMessageDialog(this, "¡Felicidades! Has encontrado todos los pares.");
        }
    }

    private class CardClickListener implements ActionListener {
        private int index;

        public CardClickListener(int index) {
            this.index = index;
        }

        public void actionPerformed(ActionEvent e) {
            if (!isClickable || cards[index].getIcon() != null) {
                return;
            }

            voltearTarjeta(index);

            if (firstCardIndex == -1) {
                firstCardIndex = index;
            } else {
                secondCardIndex = index;
                isClickable = false;

                if (cardValues[firstCardIndex] == cardValues[secondCardIndex]) {
                    firstCardIndex = -1;
                    secondCardIndex = -1;
                    isClickable = true;
                    pairsFound++;
                    verificarFinDelJuego();
                } else {
                    Timer timer = new Timer(1000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            cards[firstCardIndex].setIcon(null);
                            cards[firstCardIndex].setText("?");
                            cards[secondCardIndex].setIcon(null);
                            cards[secondCardIndex].setText("?");
                            firstCardIndex = -1;
                            secondCardIndex = -1;
                            isClickable = true;
                            ((Timer) e.getSource()).stop();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        }
    }
}