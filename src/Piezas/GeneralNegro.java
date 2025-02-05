package proyecto1programacion2;

public class GeneralNegro extends Pieza {
    public GeneralNegro(int x, int y, String color) {
        super(x, y, color, "src/imagenes/General Negro.png");
    }

   @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, Pieza[][] tablero) {
        boolean dentroDelPalacio = nuevoX >= 3 && nuevoX <= 5 && nuevoY >= 0 && nuevoY <= 2;
        
        int difX = Math.abs(nuevoX - x);
        int difY = Math.abs(nuevoY - y);
        
        boolean movimientoOrtogonal = (difX == 1 && difY == 0) || (difX == 0 && difY == 1);
        
        
        return dentroDelPalacio && movimientoOrtogonal;
    }
}