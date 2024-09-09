/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinalpoo;

/**
 *
 * @author emili
 */
import java.io.*;
import java.util.Map;
import java.util.HashMap;


// Clase concreta para gestionar puntajes utilizando archivos
public class RegistroPuntajes extends GestorPuntajes {
    private File archivo;

    public RegistroPuntajes(String nombreArchivo) {
        archivo = new File(System.getProperty("user.dir"), nombreArchivo);
        try {
            if (!archivo.exists()) {
                if (archivo.createNewFile()) {
                    System.out.println("Archivo creado: " + archivo.getAbsolutePath());
                } else {
                    System.err.println("No se pudo crear el archivo: " + archivo.getAbsolutePath());
                }
            } else {
                System.out.println("Archivo encontrado: " + archivo.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Integer> leerPuntajes() {
        Map<String, Integer> puntajes = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    int puntaje = Integer.parseInt(partes[1].trim());
                    puntajes.put(nombre, puntaje);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return puntajes;
    }

    @Override
    public void escribirPuntaje(String nombre, int puntaje) {
        Map<String, Integer> puntajes = leerPuntajes();
        if (puntajes.containsKey(nombre)) {
            if (puntajes.get(nombre) < puntaje) {
                puntajes.put(nombre, puntaje);
            }
        } else {
            puntajes.put(nombre, puntaje);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Map.Entry<String, Integer> entry : puntajes.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue());
                bw.newLine();
            }
            System.out.println("Puntaje guardado para " + nombre + ": " + puntaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}