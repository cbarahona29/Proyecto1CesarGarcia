package proyecto1programacion2;

public class ElefanteNegro extends Pieza {
    public ElefanteNegro(int x, int y, String color) {
        super(x, y, color, "src/imagenes/Elefante Negro.png");
    }
    
  @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, Pieza[][] tablero) {
        if (Math.abs(nuevoX - x) != 2 || Math.abs(nuevoY - y) != 2) {
            return false;
        }
        
        if (nuevoY > 4) {
            return false;
        }
        
        int intermedioX = (x + nuevoX) / 2;
        int intermedioY = (y + nuevoY) / 2;
        if (tablero[intermedioX][intermedioY] != null) {
            return false;
        }
        
        return true;
    }
}
