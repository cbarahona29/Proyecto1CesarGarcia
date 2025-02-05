package proyecto1programacion2;

public class CanonNegro extends Pieza {
    public CanonNegro(int x, int y, String color) {
        super(x, y, color, "src/imagenes/Cañon Negro.png");
    }

 @Override
    public boolean movimientoValido(int nuevoX, int nuevoY, Pieza[][] tablero) {
        if (x != nuevoX && y != nuevoY) {
            return false; 
        }
        int piezasEnMedio = contarPiezasEnMedio(nuevoX, nuevoY, tablero);
        if (piezasEnMedio == 0 && tablero[nuevoX][nuevoY] == null) {
            return true;
        }
        if (piezasEnMedio == 1 && tablero[nuevoX][nuevoY] != null) {
            return !tablero[nuevoX][nuevoY].color.equals(this.color);
        }

        return false; 
    }

    private int contarPiezasEnMedio(int nuevoX, int nuevoY, Pieza[][] tablero) {
        int piezasEnMedio = 0;
        int minX = Math.min(x, nuevoX);
        int maxX = Math.max(x, nuevoX);
        int minY = Math.min(y, nuevoY);
        int maxY = Math.max(y, nuevoY);

        if (x == nuevoX) { 
            for (int i = minY + 1; i < maxY; i++) {
                if (tablero[x][i] != null) {
                    piezasEnMedio++;
                }
            }
        } else { 
            for (int i = minX + 1; i < maxX; i++) {
                if (tablero[i][y] != null) {
                    piezasEnMedio++;
                }
            }
        }

        return piezasEnMedio;
    }
}