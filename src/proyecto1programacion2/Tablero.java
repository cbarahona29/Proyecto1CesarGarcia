package proyecto1programacion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tablero extends JPanel implements ActionListener {
    
    private static final int celdas = 60;
    private static final int filas = 10;
    private static final int columnas = 9;
    private Pieza[][] piezas = new Pieza[columnas][filas];
    private static final PlayerInterface PlayerInterface = new Player(null, null);

    private boolean turnoJugador1 = true;
    private String oponente;
    private JFrame frame;
    private JButton retirarseBtn;
    private JLabel turnoLabel, jugadorArribaLabel, jugadorAbajoLabel, jugador1CapturasLabel, jugador2CapturasLabel;
    private JPanel capturasJugador1Panel, capturasJugador2Panel;
    private JTextArea movesTextArea;
    private JScrollPane movesScrollPane;
    private int moveNumber = 1;

    private Pieza piezaSeleccionada = null;

    public Tablero(String oponente) {
        
         PlayerInterface.getPlayerActivo().nuevaPartida();
         Player[] jugadores = PlayerInterface.getJugadores();
          for (int i = 0; i < PlayerInterface.getContadorJugadores(); i++) {
        if (jugadores[i] != null && jugadores[i].getUsuario().equals(oponente)) {
            jugadores[i].nuevaPartida();
            break;
        }
      }
        
        this.oponente = oponente;
        this.setPreferredSize(new Dimension(columnas * celdas, filas * celdas));
        this.setBackground(new Color(233, 149, 65));

        frame = new JFrame("XIANGQI Partida");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 750);

        this.setBounds(200, 50, columnas * celdas, filas * celdas);
        frame.add(this);

        jugadorArribaLabel = new JLabel("NEGROS: " + oponente);
        jugadorArribaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        jugadorArribaLabel.setBounds(200, 20, 300, 30);
        frame.add(jugadorArribaLabel);

        jugadorAbajoLabel = new JLabel("ROJOS: " + PlayerInterface.getPlayerActivo().getUsuario());
        jugadorAbajoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        jugadorAbajoLabel.setBounds(200, 660, 300, 30);
        frame.add(jugadorAbajoLabel);

        retirarseBtn = new JButton("Retirarse");
        retirarseBtn.setBounds(400, 660, 100, 30);
        retirarseBtn.addActionListener(this);
        frame.add(retirarseBtn);

        turnoLabel = new JLabel("Turno: " + PlayerInterface.getPlayerActivo().getUsuario());
        turnoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        turnoLabel.setBounds(530, 660, 300, 30);
        frame.add(turnoLabel);

        capturasJugador1Panel = new JPanel();
        capturasJugador1Panel.setLayout(null);
        capturasJugador1Panel.setBounds(20, 50, 150, 300);
        capturasJugador1Panel.setBorder(BorderFactory.createTitledBorder("Capturas de " + oponente));
        frame.add(capturasJugador1Panel);

        jugador1CapturasLabel = new JLabel("Piezas capturadas:");
        jugador1CapturasLabel.setBounds(10, 20, 130, 20);
        capturasJugador1Panel.add(jugador1CapturasLabel);

        capturasJugador2Panel = new JPanel();
        capturasJugador2Panel.setLayout(null);
        capturasJugador2Panel.setBounds(20, 370, 150, 300);
        capturasJugador2Panel.setBorder(BorderFactory.createTitledBorder("Capturas de " + PlayerInterface.getPlayerActivo().getUsuario()));
        frame.add(capturasJugador2Panel);

        jugador2CapturasLabel = new JLabel("Piezas capturadas:");
        jugador2CapturasLabel.setBounds(10, 20, 130, 20);
        capturasJugador2Panel.add(jugador2CapturasLabel);

        movesTextArea = new JTextArea();
        movesTextArea.setEditable(false);
        movesTextArea.setFont(new Font("Arial", Font.BOLD, 12));
        
        movesScrollPane = new JScrollPane(movesTextArea);
        movesScrollPane.setBounds(750, 50, 220, 620);
        movesScrollPane.setBorder(BorderFactory.createTitledBorder("Historial de Movimientos"));
        frame.add(movesScrollPane);

        inicializarPiezas();
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int clickX = e.getX() / celdas;
                int clickY = e.getY() / celdas;

                if (clickX < 0 || clickX >= columnas || clickY < 0 || clickY >= filas) {
                    return;
                }

                Pieza piezaClickeada = piezas[clickX][clickY];
                String colorTurno = turnoJugador1 ? "rojo" : "negro";

                if (piezaSeleccionada != null) {
                    if (hayPiezaAmiga(clickX, clickY)) {
                        piezaSeleccionada = null;
                        repaint();
                        return;
                    }

                    if (piezaSeleccionada.movimientoValido(clickX, clickY, piezas)) {

                        String jugador = turnoJugador1 ? PlayerInterface.getPlayerActivo().getUsuario() : oponente;
                        String moveText = moveNumber + ". " +   jugador + ": " + piezaSeleccionada.getClass().getSimpleName()
                        .replace("Rojo", "").replace("Negro", "") + " (" + piezaSeleccionada.getX() + ","
                        + piezaSeleccionada.getY() + ") → (" + clickX + "," + clickY + ")\n";
                        movesTextArea.append(moveText);
                        moveNumber++;

                        movesTextArea.setCaretPosition(movesTextArea.getDocument().getLength());

                        Pieza piezaCapturada = piezas[clickX][clickY];
                        if (piezaCapturada != null) {
                            mostrarPiezaCapturada(piezaCapturada);

                           String captureText = "   Captura: " + piezaCapturada.getClass().getSimpleName().replace("Rojo", "") .replace("Negro", "") + "\n";
                            movesTextArea.append(captureText);
                        }

                        piezas[piezaSeleccionada.getX()][piezaSeleccionada.getY()] = null;
                        piezaSeleccionada.setPosicion(clickX, clickY);
                        piezas[clickX][clickY] = piezaSeleccionada;
                        piezaSeleccionada = null;

                        repaint();
                        cambiarTurno();
                    } else {
                        piezaSeleccionada = null;
                        repaint();
                    }
                } else {
                    if (piezaClickeada != null && piezaClickeada.getColor().equals(colorTurno)) {
                        piezaSeleccionada = piezaClickeada;
                        repaint();
                    }
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void inicializarPiezas() {
        piezas[4][0] = new GeneralNegro(4, 0, "negro");
        piezas[3][0] = new OficialNegro(3, 0, "negro");
        piezas[5][0] = new OficialNegro(5, 0, "negro");
        piezas[2][0] = new ElefanteNegro(2, 0, "negro");
        piezas[6][0] = new ElefanteNegro(6, 0, "negro");
        piezas[1][0] = new CaballoNegro(1, 0, "negro");
        piezas[7][0] = new CaballoNegro(7, 0, "negro");
        piezas[0][0] = new TorreNegro(0, 0, "negro");
        piezas[8][0] = new TorreNegro(8, 0, "negro");
        piezas[1][2] = new CanonNegro(1, 2, "negro");
        piezas[7][2] = new CanonNegro(7, 2, "negro");

        for (int i = 0; i < 9; i += 2) {
            piezas[i][3] = new PeonNegro(i, 3, "negro");
        }

        piezas[4][9] = new GeneralRojo(4, 9, "rojo");
        piezas[3][9] = new OficialRojo(3, 9, "rojo");
        piezas[5][9] = new OficialRojo(5, 9, "rojo");
        piezas[2][9] = new ElefanteRojo(2, 9, "rojo");
        piezas[6][9] = new ElefanteRojo(6, 9, "rojo");
        piezas[1][9] = new CaballoRojo(1, 9, "rojo");
        piezas[7][9] = new CaballoRojo(7, 9, "rojo");
        piezas[0][9] = new TorreRojo(0, 9, "rojo");
        piezas[8][9] = new TorreRojo(8, 9, "rojo");
        piezas[1][7] = new CanonRojo(1, 7, "rojo");
        piezas[7][7] = new CanonRojo(7, 7, "rojo");

        for (int i = 0; i < 9; i += 2) {
            piezas[i][6] = new PeonRojo(i, 6, "rojo");
        }
    }
    
    private void Tablero(Graphics g) {
        g.setColor(new Color(233, 149, 65)); 
        g.fillRect(0, 0, getWidth(), getHeight());
       
        g.setColor(new Color(88, 174, 223));
        g.fillRect(0, 4 * celdas, columnas * celdas, celdas); 
        g.fillRect(0, 5 * celdas, columnas * celdas, celdas); 
    
        g.setColor(Color.BLACK);

        for (int col = 0; col <= columnas; col++) {
            g.drawLine(col * celdas, 0, col * celdas, filas * celdas);
        }

        for (int row = 0; row <= filas; row++) {
            g.drawLine(0, row * celdas, columnas * celdas, row * celdas);
        }

        g.drawLine(3 * celdas, 0, 6 * celdas, 3 * celdas); 
        g.drawLine(6 * celdas, 0, 3 * celdas, 3 * celdas); 

        g.drawLine(3 * celdas, 7 * celdas, 6 * celdas, 10 * celdas); 
        g.drawLine(6 * celdas, 7 * celdas, 3 * celdas, 10 * celdas);
    }
    
    private void Piezas(Graphics g) {
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                if (piezas[i][j] != null) {
                    piezas[i][j].dibujar(g, this);
                }
            }
        }
    }
    
    private boolean hayPiezaAmiga(int x, int y) {
    if (piezas[x][y] != null) { 
        return piezaSeleccionada.getColor().equals(piezas[x][y].getColor()); 
    }
    return false;
}
    
    private void mostrarPiezaCapturada(Pieza piezaCapturada) {
        if (piezaCapturada == null) return;

        if (piezaCapturada instanceof GeneralRojo || piezaCapturada instanceof GeneralNegro) {
            finalizarJuego(piezaCapturada.getColor());
            return;
        }

        ImageIcon imagen = new ImageIcon(piezaCapturada.getImagePath());
        Image imgEscalada = imagen.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel nuevaCaptura = new JLabel(new ImageIcon(imgEscalada));

        if (piezaCapturada.getColor().equals("rojo")) {
            int componentCount = capturasJugador1Panel.getComponentCount() - 1;
            nuevaCaptura.setBounds(10 + (componentCount % 3) * 40, 50 + (componentCount / 3) * 40, 30, 30);
            capturasJugador1Panel.add(nuevaCaptura);
        } else if (piezaCapturada.getColor().equals("negro")) {
            int componentCount = capturasJugador2Panel.getComponentCount() - 1;
            nuevaCaptura.setBounds(10 + (componentCount % 3) * 40, 50 + (componentCount / 3) * 40, 30, 30);
            capturasJugador2Panel.add(nuevaCaptura);
        }

        capturasJugador1Panel.revalidate();
        capturasJugador2Panel.revalidate();
        capturasJugador1Panel.repaint();
        capturasJugador2Panel.repaint();
    }
    
    private void cambiarTurno() {
    turnoJugador1 = !turnoJugador1;
    turnoLabel.setText("Turno: " + (turnoJugador1 ? PlayerInterface.getPlayerActivo().getUsuario() : oponente));
}
   
    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Tablero(g);

    if (piezaSeleccionada != null) {
        Color mostrar = new Color(0, 255, 0, 100);
        g.setColor(mostrar);

        for (int x = 0; x < columnas; x++) {
            for (int y = 0; y < filas; y++) {
                if (piezaSeleccionada.movimientoValido(x, y, piezas) && !hayPiezaAmiga(x, y)) {
                    g.fillRect(x * celdas, y * celdas, celdas, celdas);
                }
            }
        }
    }

    Piezas(g);
}
    
    private void retirarse() {
    Player jugadorActual = PlayerInterface.getPlayerActivo();
    Player jugadorOponente = null;
    String mensaje;

    Player[] jugadores = PlayerInterface.getJugadores();
    for (int i = 0; i < PlayerInterface.getContadorJugadores(); i++) {
        if (jugadores[i] != null && jugadores[i].getUsuario().equals(oponente)) {
            jugadorOponente = jugadores[i];
            break;
        }
    }
    if (turnoJugador1) {
        if (jugadorOponente != null) {
            jugadorOponente.addPuntos(3);
            mensaje = jugadorActual.getUsuario() + " se ha retirado " + oponente + " gana 3 puntos";
            LogicaGeneral.addHistorial(oponente,jugadorActual.getUsuario(), jugadorActual.getUsuario() + " se ha retirado, gano " + oponente, true);
            
        } else {
            mensaje = jugadorActual.getUsuario() + " se ha retirado. No se pudieron asignar puntos al oponente.";
        }
    } else {
        jugadorActual.addPuntos(3);
        mensaje = oponente + " se ha retirado " + jugadorActual.getUsuario() + " gana 3 puntos";
        LogicaGeneral.addHistorial(jugadorActual.getUsuario(),oponente, oponente + " se ha retirado, gano " + jugadorActual.getUsuario(), true);
    }
    
    JOptionPane.showMessageDialog(this, mensaje);
    new MenuJuego();
    frame.dispose();
}
 
    private void finalizarJuego(String colorCapturador) {
    Player jugadorActual = PlayerInterface.getPlayerActivo();
    Player jugadorOponente = null;
    
    Player[] jugadores = PlayerInterface.getJugadores();
    for (int i = 0; i < PlayerInterface.getContadorJugadores(); i++) {
        if (jugadores[i] != null && jugadores[i].getUsuario().equals(oponente)) {
            jugadorOponente = jugadores[i];
            break;
        }
    }

    if (colorCapturador.equals("rojo")) {
        if (jugadorOponente != null) {
            jugadorOponente.addPuntos(3);
            JOptionPane.showMessageDialog(null, 
                oponente + " vencio a  " + jugadorActual.getUsuario() + ", Felicidades has ganado 3 puntos");
            LogicaGeneral.addHistorial(oponente,  jugadorActual.getUsuario(), oponente + " vencio a  " + jugadorActual.getUsuario(), false);
        }
    } else {
        jugadorActual.addPuntos(3);
        JOptionPane.showMessageDialog(null, 
            jugadorActual.getUsuario() + " vencio a " + oponente + ", Felicidades has ganado 3 puntos");
         LogicaGeneral.addHistorial(jugadorActual.getUsuario(),  oponente, jugadorActual.getUsuario() + " vencio a " + oponente, false);
    }
    
    new MenuJuego();
    frame.dispose();
}
    
     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retirarseBtn) {
          int confirmacion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea retirarse de la partida?", "Confirmacion", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            retirarse();
        } else {
            JOptionPane.showMessageDialog(null, "La Partida Continua");
            return;    
        }
            
        }
    }
}