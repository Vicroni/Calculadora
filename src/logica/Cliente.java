/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Saúl Fernando González Dominguez
 * @author González Anguiano Carlos Eduardo
 */
public class Cliente {

    public static final int PORT = 5000;

    public static final String HOST = "localhost";

    /**
     * <h3>run()</h3>
     * 
     * Ejecuta al cliente, creando un objeto de la clase Socket a través del
     * cuál obtiene y envía valores al servidor.
     * 
     * Envía una ruta y recibe una String que convierte a double para mostrar
     * como resultado.
     * 
     * @param  ruta: Es la ruta que se le envía al servidor que contiene los
     * datos de número 1, número 2 y operación, necesarios para que el servidor
     * realice la operación y devuelva un resultado.
     * 
     */
    
    public double run(String ruta) {
        double x = 0;
        try {
            //Referencia del servidor
            Socket ref = new Socket(HOST, PORT);
            //Flujo de entrada
            DataInputStream input = new DataInputStream(ref.getInputStream());   
            DataOutputStream output = new DataOutputStream(ref.getOutputStream());
            
            output.writeUTF(ruta);
            
            x = Double.parseDouble(input.readUTF());
            ref.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return x;
    }
//
//    public static void main(String[] args) {
//        new Cliente().run("suma/23/46");
//    }

}
