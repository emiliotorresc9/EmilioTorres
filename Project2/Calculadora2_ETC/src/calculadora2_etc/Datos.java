/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora2_etc;

import javax.swing.*;

/**
 *
 * @author emili
 */
public class Datos {
   private JButton Bot0, Bot1, Bot2, Bot3, Bot4, Bot5, Bot6, Bot7, Bot8, Bot9; 
   private JButton bsuma, bresta, bmultiplicacion, bdivision, bigual, bC; 
   private JTextField espacio;

    public Datos() {      
        Bot0 = new JButton("0"); 
        Bot1 = new JButton("1");
        Bot2 = new JButton("2");
        Bot3 = new JButton("3");
        Bot4 = new JButton("4");
        Bot5 = new JButton("5");
        Bot6 = new JButton("6");
        Bot7 = new JButton("7");
        Bot8 = new JButton("8");
        Bot9 = new JButton("9");

        bsuma = new JButton("+");
        bresta = new JButton("-");
        bmultiplicacion = new JButton("×");
        bdivision = new JButton("÷");
        bigual = new JButton("="); 
        bC = new JButton(".");
        
        espacio = new JTextField(); 
    }

    public JButton getBot0() {
        return Bot0;
    }

    public void setBot0(JButton Bot0) {
        this.Bot0 = Bot0;
    }

    public JButton getBot1() {
        return Bot1;
    }

    public void setBot1(JButton Bot1) {
        this.Bot1 = Bot1;
    }

    public JButton getBot2() {
        return Bot2;
    }

    public void setBot2(JButton Bot2) {
        this.Bot2 = Bot2;
    }

    public JButton getBot3() {
        return Bot3;
    }

    public void setBot3(JButton Bot3) {
        this.Bot3 = Bot3;
    }

    public JButton getBot4() {
        return Bot4;
    }

    public void setBot4(JButton Bot4) {
        this.Bot4 = Bot4;
    }

    public JButton getBot5() {
        return Bot5;
    }

    public void setBot5(JButton Bot5) {
        this.Bot5 = Bot5;
    }

    public JButton getBot6() {
        return Bot6;
    }

    public void setBot6(JButton Bot6) {
        this.Bot6 = Bot6;
    }

    public JButton getBot7() {
        return Bot7;
    }

    public void setBot7(JButton Bot7) {
        this.Bot7 = Bot7;
    }

    public JButton getBot8() {
        return Bot8;
    }

    public void setBot8(JButton Bot8) {
        this.Bot8 = Bot8;
    }

    public JButton getBot9() {
        return Bot9;
    }

    public void setBot9(JButton Bot9) {
        this.Bot9 = Bot9;
    }

    public JButton getBsuma() {
        return bsuma;
    }

    public void setBsuma(JButton bsuma) {
        this.bsuma = bsuma;
    }

    public JButton getBresta() {
        return bresta;
    }

    public void setBresta(JButton bresta) {
        this.bresta = bresta;
    }

    public JButton getBmultiplicacion() {
        return bmultiplicacion;
    }

    public void setBmultiplicacion(JButton bmultiplicacion) {
        this.bmultiplicacion = bmultiplicacion;
    }

    public JButton getBdivision() {
        return bdivision;
    }

    public void setBdivision(JButton bdivision) {
        this.bdivision = bdivision;
    }

    public JButton getbC() {
        return bC;
    }

    public void setbC(JButton bC) {
        this.bC = bC;
    }

    public JTextField getEspacio() {
        return espacio;
    }

    public void setEspacio(JTextField espacio) {
        this.espacio = espacio;
    }

    public JButton getBigual() {
        return bigual;
    }

    public void setBigual(JButton bigual) {
        this.bigual = bigual;
    }
    
}