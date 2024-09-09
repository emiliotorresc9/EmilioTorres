/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinalpoo;

/**
 *
 * @author emili
 */
import java.util.Map;

// Clase abstracta para gestionar puntajes
public abstract class GestorPuntajes {
    public abstract Map<String, Integer> leerPuntajes();
    public abstract void escribirPuntaje(String nombre, int puntaje);
}