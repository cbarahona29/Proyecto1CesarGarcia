package proyecto1programacion2;

public interface PlayerInterface {
      Player[] getJugadores();
    int getContadorJugadores();
    Player getPlayerActivo();
    void setPlayerActivo(Player player);
    void addJugador(Player player);
    void eliminarJugador(Player player);
    
}
