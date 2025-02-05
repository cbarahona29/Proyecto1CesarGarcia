package proyecto1programacion2;

import java.time.LocalDate;

public class Player implements PlayerInterface {
    
    private String usuario;
    private String contra;
    private int puntos;
    private final LocalDate fechaCreacion;
    private int partidasJugadas;
    private static final Player[] jugadores = new Player[100];
    private static int contadorJugadores = 0;
    private static Player playerActivo = null;

    public Player(String usuario, String contra) {
        this.usuario = usuario;
        this.contra = contra;
        this.puntos = 0;
        this.partidasJugadas = 0;
        this.fechaCreacion = LocalDate.now();
    }

    @Override
    public Player[] getJugadores() {
        return jugadores;
    }

    @Override
    public int getContadorJugadores() {
        return contadorJugadores;
    }

    @Override
    public Player getPlayerActivo() {
        return playerActivo;
    }

    @Override
    public void setPlayerActivo(Player player) {
        playerActivo = player;
    }

    @Override
    public void addJugador(Player player) {
        if (contadorJugadores < jugadores.length) {
            jugadores[contadorJugadores] = player;
            contadorJugadores++;
        }
    }

    @Override
    public void eliminarJugador(Player player) {
        for (int i = 0; i < contadorJugadores; i++) {
            if (jugadores[i] == player) {
                for (int j = i; j < contadorJugadores - 1; j++) {
                    jugadores[j] = jugadores[j + 1];
                }
                jugadores[contadorJugadores - 1] = null;
                contadorJugadores--;
                return;
            }
        }
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContra() {
        return contra;
    }

    public int getPuntos() {
        return puntos;
    }

    public void addPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void setContra(String nuevaContra) {
        this.contra = nuevaContra;
    }
    
      public int getPartidasJugadas() {
        return partidasJugadas;
    }
    
    public void incrementarPartidasJugadas() {
        this.partidasJugadas++;
    }
    
    public void nuevaPartida() {
        incrementarPartidasJugadas();
    }
}

