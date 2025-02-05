package proyecto1programacion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SeleccionJugador extends JFrame implements ActionListener {
    
    private JButton salirbtn;
    private JButton jugarbtn;  
    private JButton crearJugadorBtn; 
    private JComboBox<String> comboBox;
    private JLabel fondo;
    private JLabel jugadorActivoLabel;
    private static final PlayerInterface PlayerInterface = new Player(null, null);
    
    public SeleccionJugador() {
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("XIANQI Seleccionar Jugador");
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
        
        crearJugadorBtn = new JButton("Crear Jugador");
        crearJugadorBtn.setBounds(20, 20, 120, 25);
        crearJugadorBtn.addActionListener(this);
        this.add(crearJugadorBtn);
        
        jugadorActivoLabel = new JLabel("Jugador Activo: " + PlayerInterface.getPlayerActivo().getUsuario());
        jugadorActivoLabel.setBounds(150, 100, 200, 25);
        jugadorActivoLabel.setForeground(Color.black); 
        jugadorActivoLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        this.add(jugadorActivoLabel);
        
        comboBox = new JComboBox<>();
        comboBox.setBounds(150, 150, 200, 25);
        this.add(comboBox);
        llenarComboBox();
        
        jugarbtn = new JButton("Jugar");
        jugarbtn.setBounds(200, 200, 110, 25);
        this.add(jugarbtn);
        jugarbtn.addActionListener(this);
        
        salirbtn = new JButton("Salir");
        salirbtn.setBounds(200, 250, 110, 25);
        this.add(salirbtn);
        salirbtn.addActionListener(this);
        
        this.setVisible(true);
    }
    
    private void llenarComboBox() {
        comboBox.removeAllItems(); 
        Player[] jugadores = PlayerInterface.getJugadores();
        Player jugadorActivo = PlayerInterface.getPlayerActivo();
        for (int i = 0; i < PlayerInterface.getContadorJugadores(); i++) {
            if (jugadores[i] != null && jugadores[i] != jugadorActivo) {
                comboBox.addItem(jugadores[i].getUsuario());
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salirbtn) {
            new MenuJuego();
            this.dispose();
        } else if (e.getSource() == jugarbtn) {
            if (comboBox.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "Debe agregar otro jugador antes de jugar.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String oponente  = (String) comboBox.getSelectedItem();
               new Tablero(oponente); 
                this.dispose();
            }
        } else if (e.getSource() == crearJugadorBtn) {
           int confirmacion = JOptionPane.showConfirmDialog(null, "Se va a cerrar tu sesion estas seguro?", "ADEVERTENCIA", JOptionPane.YES_NO_OPTION);
           
        if (confirmacion == JOptionPane.YES_OPTION) {
             new CrearPlayerSeleccion();
            this.dispose();
        } else {
      
            return;    
        }
         
        }
    }
}