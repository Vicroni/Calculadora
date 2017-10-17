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
 * @author Alumno
 */
public class Cliente {

    public static final int PORT = 5000;

    public static final String HOST = "localhost";

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
