package proyecto1programacion2;

public class OficialNegro extends Pieza {
    public OficialNegro(int x, int y, String color) {
        super(x, y, color, "src/imagenes/Oficial Negro.png");
    }
      @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, Pieza[][] tablero) {
        if (Math.abs(nuevoX - x) == 1 && Math.abs(nuevoY - y) == 1 &&
            nuevoX >= 3 && nuevoX <= 5 && nuevoY >= 0 && nuevoY <= 2) {
            return true;
        }
        return false;
    }

}
