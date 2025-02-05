package proyecto1programacion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reportes extends JFrame implements ActionListener {
    
    private JButton rankingbtn;
    private JButton logsbtn;
    private JButton regresarbtn;
    private JLabel fondo;

    public Reportes() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("XIANQI Reportes");
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(177, 37, 7));
      
        ImageIcon backgroundImage = new ImageIcon("src/imagenes/fondo.png");
        this.setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        fondo = new JLabel(backgroundImage);
        fondo.setBounds(0, 0, 500, 500);
        this.setContentPane(fondo);
        this.setLayout(null);

        rankingbtn = new JButton("1. Ranking Jugadores ");
        rankingbtn.setBounds(150, 150, 200, 30);
        this.add(rankingbtn);
        rankingbtn.addActionListener(this);

        logsbtn = new JButton("2. Logs de mis ultimos Juegos");
        logsbtn.setBounds(150, 200, 200, 30);
        this.add(logsbtn);
        logsbtn.addActionListener(this);

        regresarbtn = new JButton("3. Regresar al Menu");
        regresarbtn.setBounds(150, 250, 200, 30);
        this.add(regresarbtn);
        regresarbtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rankingbtn) {
            LogicaGeneral.mostrarRanking();
        } else if (e.getSource() == logsbtn) {
            LogicaGeneral.logs();
        } else {
            new MenuJuego();
            this.dispose();
        }
    }
}

