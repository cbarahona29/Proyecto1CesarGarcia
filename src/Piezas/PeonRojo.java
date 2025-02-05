package proyecto1programacion2;

public class PeonRojo extends Pieza {
    public PeonRojo(int x, int y, String color) {
        super(x, y, color, "src/imagenes/Peon Rojo.png");
    }
  @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, Pieza[][] tablero) {
        if (nuevoX == x && nuevoY == y - 1) {
            return true;
        }
        if (y <= 4 && Math.abs(nuevoX - x) == 1 && nuevoY == y) {
            return true;
        }
        return false;
    }
}