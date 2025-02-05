package proyecto1programacion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
     private JTextField usuario;
     private JPasswordField password;
     private JButton iniciarSesionBtn, regresarMenuBtn;
     private Player player;
     private JLabel fondo;
    private static final PlayerInterface PlayerInterface = new Player(null, null);


    public Login() {
        player = PlayerInterface.getPlayerActivo();

        this.setTitle("XIANGQI Login");
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
        usuarioLabel.setBounds(50, 50, 100, 25);
        usuarioLabel.setForeground(Color.black);
        this.add(usuarioLabel);

        usuario = new JTextField();
        usuario.setBounds(150, 50, 150, 25);
        this.add(usuario);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(50, 100, 100, 25);
        passwordLabel.setForeground(Color.black);
        this.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(150, 100, 150, 25);
        this.add(password);

        iniciarSesionBtn = new JButton("Iniciar Sesión");
        iniciarSesionBtn.setBounds(50, 160, 130, 30);
        iniciarSesionBtn.addActionListener(this);
        this.add(iniciarSesionBtn);

        regresarMenuBtn = new JButton("Regresar");
        regresarMenuBtn.setBounds(200, 160, 100, 30);
        regresarMenuBtn.addActionListener(this);
        this.add(regresarMenuBtn);

        this.setVisible(true);
    }

 @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iniciarSesionBtn) {
            String usuarioInput = usuario.getText().trim();
            String passwordInput = new String(password.getPassword()).trim();
            
            if (usuarioInput.isEmpty() || passwordInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese usuario y contraseña");
                return;
            }

            try {
                Player[] jugadores = PlayerInterface.getJugadores();
                int contadorJugadores = PlayerInterface.getContadorJugadores();
                boolean loginExitoso = false;

                for (int i = 0; i < contadorJugadores; i++) {
                    if (jugadores[i].getUsuario().equalsIgnoreCase(usuarioInput) && 
                        jugadores[i].getContra().equals(passwordInput)) {
                        PlayerInterface.setPlayerActivo(jugadores[i]);
                        loginExitoso = true;
                        JOptionPane.showMessageDialog(this, "Inicio de sesion exitoso");
                        new MenuJuego();
                        this.dispose();
                        break;
                    }
                }

                if (!loginExitoso) {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al intentar iniciar sesión: " + ex.getMessage());
            }
        } else if (e.getSource() == regresarMenuBtn) {
            new Menu();
            this.dispose();
        }
    }
}