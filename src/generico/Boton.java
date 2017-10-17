/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author SAUL
 */
public abstract class Boton extends JButton{
    
    public Boton(String text){
        super();
        this.setText(text);
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Método abstracto para implementar acciones en Botones
                action();
            }
        });
    }
   
    /**
    * <h3>action</h3>
    * 
    * Método abstracto para implementar una acción en el botón
    */
    public abstract void action();
}
