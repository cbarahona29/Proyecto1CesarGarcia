package proyecto1programacion2;

public class CaballoRojo extends Pieza {
    public CaballoRojo(int x, int y, String color) {
        super(x, y, color, "src/imagenes/Caballo Rojo.png");
    }
   @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, Pieza[][] tablero) {
        if (nuevoX < 0 || nuevoX >= 9 || nuevoY < 0 || nuevoY >= 10) {
            return false;
        }
        int deltaX = Math.abs(nuevoX - x);
        int deltaY = Math.abs(nuevoY - y);

        if (!((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2))) {
            return false;
        }
        if (deltaX == 2) {
            int bloqueadorX = x + (nuevoX - x) / 2;
            if (tablero[bloqueadorX][y] != null) {
                return false;
            }
        } else {
            int bloqueadorY = y + (nuevoY - y) / 2;
            if (bloqueadorY >= 0 && bloqueadorY < 10 && tablero[x][bloqueadorY] != null) {
                return false;
            }
        }

        return tablero[nuevoX][nuevoY] == null || 
               !tablero[nuevoX][nuevoY].getColor().equals(this.color);
    }
}