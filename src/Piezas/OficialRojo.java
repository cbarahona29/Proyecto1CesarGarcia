package proyecto1programacion2;

public class OficialRojo extends Pieza {
    public OficialRojo(int x, int y, String color) {
        super(x, y, color, "src/imagenes/Oficial Rojo.png");
    }

    public boolean movimientoValido(int nuevoX, int nuevoY, Pieza[][] tablero) {
        if (Math.abs(nuevoX - x) == 1 && Math.abs(nuevoY - y) == 1 &&
            nuevoX >= 3 && nuevoX <= 5 && nuevoY >= 7 && nuevoY <= 9) {
            return true;
        }
        return false;
    }
}