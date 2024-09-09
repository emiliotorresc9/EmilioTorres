/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora2_etc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author emili
 */
public class Ventana extends JFrame {
    private JButton botondelete;  
    private JTextField TFespacio;
    private String input = "";
    private double numeroActual = 0;
    private double numeroAnterior = 0;
    private char operadorAnterior = ' ';
             
    public Ventana() {
      super("Calculadora");
      setSize(325, 420);
      getContentPane().setBackground(Color.LIGHT_GRAY);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      setLayout(null); 
      
      JPanel bpanel = new JPanel(); 
      bpanel.setBounds(5, 80, 300, 300); 
      bpanel.setLayout( new GridLayout(4,4)); 
      
        Datos datos = new Datos();         
        Color azulOscuro = new Color(0x87, 0xCE, 0xEB);
        Color azulClaro = new Color(0xB0, 0xE0, 0xE6);
        Color anaranjado = new Color(0xFF, 0xDA, 0xB9);
        Color grisAzulado = new Color(140, 160, 200);
        
            botondelete = new JButton("Del");
            botondelete.setBounds(234, 5, 70, 68); 
            botondelete.setBackground(anaranjado);
            add(botondelete); 
            
            Font font = new Font("Arial", Font.PLAIN, 20);
            TFespacio = datos.getEspacio(); 
            TFespacio.setFont(font);
            TFespacio.setBounds(5, 5, 225, 70); 
            add(TFespacio); 
            
            JButton bot7 = datos.getBot7();
            bot7.setBackground(azulClaro);
            bpanel.add(bot7); 
            JButton bot8 = datos.getBot8(); 
            bot8.setBackground(azulClaro);
            bpanel.add(bot8); 
            JButton bot9 = datos.getBot9(); 
            bot9.setBackground(azulClaro);
            bpanel.add(bot9); 
            JButton bsuma = datos.getBsuma(); 
            bsuma.setBackground(grisAzulado); 
            bpanel.add(bsuma); 
            
            JButton bot4 = datos.getBot4();
            bot4.setBackground(azulClaro);
            bpanel.add(bot4); 
            JButton bot5 = datos.getBot5(); 
            bot5.setBackground(azulClaro);
            bpanel.add(bot5); 
            JButton bot6 = datos.getBot6(); 
            bot6.setBackground(azulClaro);
            bpanel.add(bot6); 
            JButton bresta = datos.getBresta();
            bresta.setBackground(grisAzulado); 
            bpanel.add(bresta); 
            
            JButton bot1 = datos.getBot1(); 
            bot1.setBackground(azulClaro);
            bpanel.add(bot1); 
            JButton bot2 = datos.getBot2(); 
            bot2.setBackground(azulClaro);
            bpanel.add(bot2); 
            JButton bot3 = datos.getBot3(); 
            bot3.setBackground(azulClaro);
            bpanel.add(bot3); 
            JButton bmulti = datos.getBmultiplicacion();
            bmulti.setBackground(grisAzulado); 
            bpanel.add(bmulti); 
            
            JButton botPunto = datos.getbC();
            botPunto.setBackground(azulClaro);
            bpanel.add(botPunto); 
            JButton bot0 = datos.getBot0(); 
            bot0.setBackground(azulClaro);
            bpanel.add(bot0); 
            JButton bigual = datos.getBigual(); 
            bpanel.add(bigual);
            bigual.setBackground(azulOscuro); 
            JButton bdivision = datos.getBdivision();
            bdivision.setBackground(grisAzulado); 
            bpanel.add(bdivision); 
        
        add(bpanel);
        
        botondelete.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        input = "";
        TFespacio.setText("");
    }
});

        ActionListener numListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                input += button.getText();
                TFespacio.setText(input);
            }
        };

        ActionListener opListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!input.isEmpty()) {
                    double num = Double.parseDouble(input);
                    realizarOperacion(num);
                    input = "";
                }
                JButton button = (JButton) e.getSource();
                operadorAnterior = button.getText().charAt(0);
            }
        };

        bigual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!input.isEmpty()) {
                    double num = Double.parseDouble(input);
                    realizarOperacion(num);
                    input = "";
                }
                TFespacio.setText(String.valueOf(numeroActual));
                numeroActual = 0;
                numeroAnterior = 0;
                operadorAnterior = ' ';
            }
        });

            

botPunto.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

        if (!input.contains(".")) {
            input += ".";
            TFespacio.setText(input);
        }
    }
});
        bot0.addActionListener(numListener);
        bot1.addActionListener(numListener);
        bot2.addActionListener(numListener);
        bot3.addActionListener(numListener);
        bot4.addActionListener(numListener);
        bot5.addActionListener(numListener);
        bot6.addActionListener(numListener);
        bot7.addActionListener(numListener);
        bot8.addActionListener(numListener);
        bot9.addActionListener(numListener);

        bsuma.addActionListener(opListener);
        bresta.addActionListener(opListener);
        bmulti.addActionListener(opListener);
        bdivision.addActionListener(opListener);
    }

private void realizarOperacion(double num) {
    if (operadorAnterior != ' ') { 
        switch (operadorAnterior) {
            case '+':
                numeroActual += num;
                break;
            case '-':
                numeroActual -= num;
                break;
            case '×':
                numeroActual *= num;
                break;
            case '÷':
                if (num != 0) {
                    numeroActual /= num;
                } else {
  
                    numeroActual = Double.NaN;
                }
                break;
        }
    } else {
        numeroActual = num;
    }
    numeroAnterior = num; 
}
}