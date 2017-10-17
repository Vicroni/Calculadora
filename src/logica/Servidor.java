/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Saúl Fernando González Dominguez
 * @author González Anguiano Carlos Eduardo
 */
public class Servidor extends Thread{
    
    static final int PUERTO = 5000;
    
    public DataOutputStream salida;
    public DataInputStream entrada;

    public Servidor(String nombreHilo) {
        super(nombreHilo);
    }
    /**
     * <h3>run()</h3>
     * Ejecuta al servidor, deteniendolo hasta que se hayan conectado
     * 100 clientes o se detenga el programa.
     * 
     * Funciona recibiendo una entrada UTF8 en forma de ruta, dividiendola
     * por "/" (diagonales), emulando a una API.
     * 
     * La ruta se divide por: Numero 1 / Operación / Número 2
     * 
     * Ejecuta la operación deseada y la envía a traves de un dataOutputStream
     */
    public void run() {
        try {
            ServerSocket skServidor = new ServerSocket(Servidor.PUERTO);
            
            for(int i=0; i<100; i++){
                try(Socket skCliente = skServidor.accept()){
                    entrada = new DataInputStream(skCliente.getInputStream());
                    salida = new DataOutputStream(skCliente.getOutputStream());
                    
                    //READS
                    String ruta = entrada.readUTF();
                    String[] rutas = ruta.split("/");
                    
                    //WRITES
                    System.out.println("Ruta: " + ruta);
                    double num1 = Double.parseDouble(rutas[0]);
                    double num2 = Double.parseDouble(rutas[2]);
                    switch (rutas[1]) {
                        case "suma":
                            salida.writeUTF(String.valueOf(num1+num2));
                            break;
                        case "resta":
                            salida.writeUTF(String.valueOf((num1 - num2)));
                            break;
                        case "multiplica":
                            salida.writeUTF(String.valueOf((num1 * num2)));
                            break;
                        case "divide":
                            salida.writeUTF(String.valueOf((num1 / num2)));
                            break;
                        default:
                            System.out.println("No hay valor");
                    }
                    
                    skCliente.close();
                }
            }
            
            System.out.println("Finalizado");
            
        } catch (IOException ex) {
            System.out.println("Error perro");
        }
    }

}
