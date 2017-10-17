/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generico;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author SAUL
 */
public abstract class Ventana extends JFrame {

    public void start(int x, int y, int ancho, int alto) {
        this.setLocation(new Point(x, y));
        this.setSize(new Dimension(ancho, alto));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addElements();
        this.pack();
        this.setVisible(true);
    }
        
    protected abstract void addElements();
}
