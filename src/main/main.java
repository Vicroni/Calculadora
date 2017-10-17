/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Servidor;
import vista.Calculadora;



/**
 *
 * @author SAUL
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Servidor s = new Servidor("S");
        s.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Fallo en timer");
        }
        new Calculadora();
    }
    
}
