package proyecto1programacion2;

public class TorreRojo extends Pieza {
    public TorreRojo(int x, int y, String color) {
        super(x, y, color, "src/imagenes/Torre Roja.png");
    }
    
@Override
public boolean movimientoValido(int destinoX, int destinoY, Pieza[][] piezas) {
    if (this.x != destinoX && this.y != destinoY) {
        return false;
    }
    int minX = Math.min(this.x, destinoX);
    int maxX = Math.max(this.x, destinoX);
    int minY = Math.min(this.y, destinoY);
    int maxY = Math.max(this.y, destinoY);

    if (this.x == destinoX) { 
        for (int i = minY + 1; i < maxY; i++) {
            if (piezas[this.x][i] != null) {
                return false;
            }
        }
    } else if (this.y == destinoY) { 
        for (int i = minX + 1; i < maxX; i++) {
            if (piezas[i][this.y] != null) { 
                return false;
            }
        }
    }

    if (piezas[destinoX][destinoY] != null && piezas[destinoX][destinoY].color.equals(this.color)) {
        return false;
    }

    return true;
}
}