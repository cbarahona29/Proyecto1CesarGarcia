package proyecto1programacion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {
    private JButton loginbtn;
    private JButton crearcuentabtn;
    private JButton salirbtn;
    private JLabel fondo;

    public Menu() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("XIANQI Menu Principal");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        ImageIcon backgroundImage = new ImageIcon("src/imagenes/fondo.png");
        this.setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        fondo = new JLabel(backgroundImage);
        fondo.setBounds(0, 0, 500, 500);
        this.setContentPane(fondo);
        this.setLayout(null);

        loginbtn = new JButton("1. Login ");
        loginbtn.setBounds(200, 150, 110, 25);
        this.add(loginbtn);
        loginbtn.addActionListener(this);

        crearcuentabtn = new JButton("2. Crear Cuenta");
        crearcuentabtn.setBounds(200, 200, 110, 25);
        this.add(crearcuentabtn);
        crearcuentabtn.addActionListener(this);

        salirbtn = new JButton("3. Salir");
        salirbtn.setBounds(200, 250, 110, 25);
        this.add(salirbtn);
        salirbtn.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginbtn) {
            new Login();
            this.dispose();
        } else if (e.getSource() == crearcuentabtn) {
            new CrearPlayer();
            this.dispose();
        } else if (e.getSource() == salirbtn) {
            JOptionPane.showMessageDialog(null, "Saliendo XIANGQI");
            System.exit(0);
        }
    }
}
