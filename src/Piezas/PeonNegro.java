package proyecto1programacion2;

public class PeonNegro extends Pieza {
    public PeonNegro(int x, int y, String color) {
        super(x, y, color, "src/imagenes/Peon Negro.png");
    }

 @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, Pieza[][] tablero) {
        if (nuevoX == x && nuevoY == y + 1) {
            return true;
        }
        if (y >= 5 && Math.abs(nuevoX - x) == 1 && nuevoY == y) {
            return true;
        }
        return false;
    }
    
}