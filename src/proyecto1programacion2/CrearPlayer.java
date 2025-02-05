package proyecto1programacion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CrearPlayer extends JFrame implements ActionListener {
     private JTextField usuarioField;
     private JPasswordField passwordField;
     private JButton registrarBtn, regresarBtn;
     private JLabel fondo;

    public CrearPlayer() {
        this.setTitle("XIANGQI Registro de Jugador");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(177, 37, 7));
    
        ImageIcon backgroundImage = new ImageIcon("src/imagenes/fondo.png");
        this.setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        fondo = new JLabel(backgroundImage);
        fondo.setBounds(0, 0, 500, 500);
        this.setContentPane(fondo);
        this.setLayout(null);
        
        
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setBounds(50, 50, 100, 30);
        usuarioLabel.setForeground(Color.black);
        this.add(usuarioLabel);

        usuarioField = new JTextField();
        usuarioField.setBounds(150, 50, 180, 30);
        this.add(usuarioField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(50, 100, 100, 30);
        passwordLabel.setForeground(Color.black);
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 180, 30);
        this.add(passwordField);

        registrarBtn = new JButton("Registrar");
        registrarBtn.setBounds(50, 160, 120, 30);
        registrarBtn.addActionListener(this);
        this.add(registrarBtn);

        regresarBtn = new JButton("Regresar");
        regresarBtn.setBounds(210, 160, 120, 30);
        regresarBtn.addActionListener(this);
        this.add(regresarBtn);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrarBtn) {
            String usuario = usuarioField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            boolean registrado = LogicaGeneral.crearJugador(usuario, password);
            
            if (registrado) {
                this.dispose();
            }
        } else if (e.getSource() == regresarBtn) {
            new Menu();
            this.dispose();
        }
    }
}
