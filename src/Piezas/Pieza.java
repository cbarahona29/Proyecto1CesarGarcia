package proyecto1programacion2;

import javax.swing.*;
import java.awt.*;

public abstract class Pieza {
    protected int x, y;
    protected ImageIcon imagen;
    protected String color;
    protected String rutaImagen;
    protected String propietario;
    
    public Pieza(int x, int y, String color, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.rutaImagen = rutaImagen;
        this.imagen = new ImageIcon(rutaImagen);
    }
    
    public abstract boolean movimientoValido(int nuevoX, int nuevoY, Pieza[][] tablero);
    
    public void dibujar(Graphics g, JPanel panel) {
        g.drawImage(imagen.getImage(), x * 60 + 5, y * 60 + 5, 50, 50, panel);
    }
    
    public void dibujarMovimientosValidos(Graphics g, Pieza[][] tablero) {
        Color colorResaltado = new Color(0, 255, 0, 100);
        g.setColor(colorResaltado);
        
        for (int i = 0; i < tablero[0].length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (movimientoValido(i, j, tablero)) {
                    g.fillRect(i * 60, j * 60, 60, 60);
                }
            }
        }
    }
    
    public boolean mover(int nuevoX, int nuevoY, Pieza[][] tablero) {
        if (movimientoValido(nuevoX, nuevoY, tablero)) {
            tablero[y][x] = null;
            x = nuevoX;
            y = nuevoY;
            tablero[y][x] = this;
            return true;
        }
        return false;
    }
    
    public int getX() { return x; }
    
    public int getY() { return y; }
    
    public String getcolor() {
        return this.color;
    }

    public String getNombre() {
        return this.getClass().getSimpleName();
    }
    
    public void setPosicion(int x, int y) { this.x = x; this.y = y; }
    
    public String getColor() { return color; }
    
    protected boolean dentroTablero(int x, int y) {
        return x >= 0 && x < 9 && y >= 0 && y < 10;
    }
    
    protected boolean hayPiezaAmiga(int x, int y, Pieza[][] tablero) {
    if (!dentroTablero(x, y)) return false;  

    Pieza piezaDestino = tablero[y][x];
    return piezaDestino != null && piezaDestino.getColor().equals(this.color);
}
   
    public String getImagePath() {
        return this.rutaImagen;
    }
}
