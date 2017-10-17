/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import generico.Boton;
import generico.Ventana;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logica.Cliente;

/**
 *
 * @author Saúl Fernando González Dominguez
 * @author González Anguiano Carlos Eduardo
 */
public class Calculadora extends Ventana {

    JTextField text;
    Boton[] btnNum;
    Boton btn_multiplica;
    Boton btn_suma;
    Boton btn_resta;
    Boton btn_divide;

    public Calculadora() {
        this.start(100, 100, 400, 200);
    }

    /**
     * <h3>addElements()</h3>
     * 
     * Inicializa los componentes gráficos de la calculadora así como las acciones
     * de los botones.
     */
    
    @Override
    protected void addElements() {
        Container panel = this.getContentPane();

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        this.text = new JTextField(30); //Tamaño en numero de columnas
        this.text.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
//                char c = e.getKeyChar();
//                System.out.println(c);
                e.consume();
            }
        });
        panelSuperior.add(this.text);

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new GridLayout(4, 4, 4, 4));

        this.btnNum = new Boton[10];
        for (int i = 0; i < this.btnNum.length; i++) {
            this.btnNum[i] = this.configNum(i);
        }

        panelBtn.add(this.btnNum[7]);
        panelBtn.add(this.btnNum[8]);
        panelBtn.add(this.btnNum[9]);
        btn_suma = this.configBtnOp("+");
        panelBtn.add(btn_suma);

        panelBtn.add(this.btnNum[4]);
        panelBtn.add(this.btnNum[5]);
        panelBtn.add(this.btnNum[6]);
        btn_divide = this.configBtnOp("÷");
        panelBtn.add(btn_divide);

        panelBtn.add(this.btnNum[1]);
        panelBtn.add(this.btnNum[2]);
        panelBtn.add(this.btnNum[3]);
        btn_resta = this.configBtnOp("-");
        panelBtn.add(btn_resta);

        JButton btn = this.configCE();
        JButton btn1 = this.configBtnIgual();

        panelBtn.add(btn);
        panelBtn.add(this.btnNum[0]);
        panelBtn.add(btn1);
        btn_multiplica = this.configBtnOp("x");
        panelBtn.add(btn_multiplica);

        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(panelBtn, BorderLayout.CENTER);
    }

    /**
     * Regresa un boton configurado para realizar la una operación en específico
     * 
     * @return  boton: El objeto boton creado que realiza una operación
     * 
     * @param op: Operación que realizará el botón.
     */
    
    public Boton configBtnOp(String op) {
        Boton boton = new Boton(op) {
            @Override
            public void action() {
                String[] sabelotodo = text.getText().split(" ");
                if (!sabelotodo[sabelotodo.length - 1].equals("+")
                        && !sabelotodo[sabelotodo.length - 1].equals("-")
                        && !sabelotodo[sabelotodo.length - 1].equals("÷")
                        && !sabelotodo[sabelotodo.length - 1].equals("x")
                        && !sabelotodo[sabelotodo.length - 1].equals("")) {

                    text.setText(text.getText() + " " + op + " ");
                }

            }
        };
        boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boton.setBackground(java.awt.Color.orange);
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(java.awt.Color.green);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(java.awt.Color.orange);
            }
        });
        return boton;
    }

    /**
     * Inicializa un boton que genera el enrutamiento que se le envía al
     * servidor posterior a haber realizado una operación y seleccionado 
     * los dos núemeros a operar.
     * 
     * @return boton: El botón que tendrá la función de ejecutar la operación.
     */
    
    public Boton configBtnIgual() {
        Boton boton = new Boton("=") {
            @Override
            public void action() {
                if(!text.getText().equalsIgnoreCase("")){
                    String[] sabelotodo = text.getText().split(" ");
                    if (!sabelotodo[sabelotodo.length - 1].equals("+")
                            && !sabelotodo[sabelotodo.length - 1].equals("-")
                            && !sabelotodo[sabelotodo.length - 1].equals("÷")
                            && !sabelotodo[sabelotodo.length - 1].equals("x")) {
                        String ruta = "";
                        for (int i = 0; i < sabelotodo.length; i++) {
                            switch (sabelotodo[i]) {
                                case "÷":
                                    sabelotodo[i] = "divide";
                                    break;
                                case "+":
                                    sabelotodo[i] = "suma";
                                    break;
                                case "-":
                                    sabelotodo[i] = "resta";
                                    break;
                                case "x":
                                    sabelotodo[i] = "multiplica";
                                    break;
                            }
                            ruta += sabelotodo[i] + "/"; 
                        }
                        ArrayList<String> otro = new ArrayList<>(Arrays.asList(ruta.split("/")));
                        do{
                            String nuevo =  Double.toString(new Cliente().run(otro.get(0)+"/"+otro.get(1)+"/"+otro.get(2))) ;
                            otro.remove(0);
                            otro.remove(0);
                            otro.set(0, nuevo);
                        }while(otro.size()-1 != 0);
                        text.setText(otro.get(0));
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingrese el otro dato");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Meta algun dato");
                }
                
            }
        };
        boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boton.setBackground(java.awt.Color.pink);
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(java.awt.Color.magenta);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(java.awt.Color.pink);
            }
        });
        return boton;
    }

    /**
     * Inicializa un boton que contiene un valor numérico
     * 
     * @param i: Valor numérico del botón.
     * 
     * @return boton: botón con un valor numérico entero.
     */
    
    public Boton configNum(int i) {
        Boton boton = new Boton(Integer.toString(i)) {
            @Override
            public void action() {
                text.setText(text.getText() + i);
            }
        };

        boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boton.setBackground(java.awt.Color.LIGHT_GRAY);

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(java.awt.Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(java.awt.Color.lightGray);
            }
        });
        return boton;
    }
    
    /**
     * Inicializa un botón que puede limpiar el cuadro de texto de entrada
     * de valores de la calculadora
     * 
     * @return boton: Boton con acción de limpiar el valor de input.
     */
    
    public Boton configCE() {
        Boton boton = new Boton("CE") {
            @Override
            public void action() {
                text.setText("");
            }
        };
        boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boton.setBackground(java.awt.Color.cyan);
        
         boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(java.awt.Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(java.awt.Color.cyan);
            }
        });
        
        return boton;
        
    }
}
