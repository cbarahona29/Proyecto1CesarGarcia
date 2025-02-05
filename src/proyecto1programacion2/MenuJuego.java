package proyecto1programacion2;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class MenuJuego extends JFrame implements ActionListener {
    
    private JButton jugarbtn;
    private JButton micuentabtn;
    private JButton reportesbtn;
    private JButton salirbtn;
    private JLabel fondo;

    public MenuJuego() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("XIANQI Menu Principal");
        this.setLayout(null);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(177, 37, 7));
        
         ImageIcon backgroundImage = new ImageIcon("src/imagenes/fondo.png");
        this.setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        fondo = new JLabel(backgroundImage);
        fondo.setBounds(0, 0, 500, 500);
        this.setContentPane(fondo);
        this.setLayout(null);

        jugarbtn = new JButton("1. Jugar XIANQI");
        jugarbtn.setBounds(200, 100, 150, 30);
        jugarbtn.addActionListener(this);
        this.add(jugarbtn);

        micuentabtn = new JButton("2. Mi Cuenta");
        micuentabtn.setBounds(200, 150, 150, 30);
        micuentabtn.addActionListener(this);
        this.add(micuentabtn);

        reportesbtn = new JButton("3. Reportes");
        reportesbtn.setBounds(200, 200, 150, 30);
        reportesbtn.addActionListener(this);
        this.add(reportesbtn);

        salirbtn = new JButton("4. Log Out");
        salirbtn.setBounds(200, 250, 150, 30);
        salirbtn.addActionListener(this);
        this.add(salirbtn);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jugarbtn) {
               new SeleccionJugador();
               this.dispose();
        } else if (e.getSource() == micuentabtn) {
                new MiCuenta();
                this.dispose();
             
        } else if (e.getSource() == reportesbtn) {
            new Reportes();
            this.dispose();
        } else {
        int confirmacion = JOptionPane.showConfirmDialog(null, "Desea Cerrar Sesion?", "Confirmacion", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            new Menu();
            this.dispose();
        } else {
            return;    
        }
            
        }
    }
}
