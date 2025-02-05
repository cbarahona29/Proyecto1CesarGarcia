package proyecto1programacion2;

import javax.swing.*;

public final class LogicaGeneral {
    private static final PlayerInterface PlayerInterface = new Player(null, null);
    private static final Historial[] Historial = new Historial[10];
    private static int logCount = 0;

    
    public static boolean crearJugador(String usuario, String contra) {
        if (usuario.isEmpty() || contra.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuario y contraseña no pueden estar vacios");
            return false;
        }

        if (contra.length() != 5) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener exactamente 5 caracteres");
            return false;
        }

        Player[] jugadores = PlayerInterface.getJugadores();
        int contadorJugadores = PlayerInterface.getContadorJugadores();

        for (int i = 0; i < contadorJugadores; i++) {
            if (jugadores[i].getUsuario().equalsIgnoreCase(usuario)) {
                JOptionPane.showMessageDialog(null, "Nombre de usuario ya en uso");
                return false;
            }
        }

        Player nuevoJugador = new Player(usuario, contra);
        PlayerInterface.addJugador(nuevoJugador);
        PlayerInterface.setPlayerActivo(nuevoJugador);

        JOptionPane.showMessageDialog(null, "Jugador registrado exitosamente");
        new MenuJuego();
        return true;
    }  

    public static boolean crearJugadorSeleccion(String usuario, String contra) {
        if (usuario.isEmpty() || contra.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuario y contraseña no pueden estar vacios");
            return false;
        }

        if (contra.length() != 5) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener exactamente 5 caracteres");
            return false;
        }

        Player[] jugadores = PlayerInterface.getJugadores();
        int contadorJugadores = PlayerInterface.getContadorJugadores();

        for (int i = 0; i < contadorJugadores; i++) {
            if (jugadores[i].getUsuario().equalsIgnoreCase(usuario)) {
                JOptionPane.showMessageDialog(null, "Nombre de usuario ya en uso");
                return false;
            }
        }

        Player nuevoJugador = new Player(usuario, contra);
        PlayerInterface.addJugador(nuevoJugador);
        PlayerInterface.setPlayerActivo(nuevoJugador);

        JOptionPane.showMessageDialog(null, "Jugador registrado exitosamente");
        new SeleccionJugador();
        return true;
    }  
     
    public static void cambiarContrasena() {
        Player playerActivo = PlayerInterface.getPlayerActivo();
        
        if (playerActivo == null) {
            JOptionPane.showMessageDialog(null, "No hay un jugador activo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String contraActual = JOptionPane.showInputDialog("Ingrese su contraseña actual:");
        
        if (contraActual == null) { 
            return;
        }

        if (!contraActual.equals(playerActivo.getContra())) {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            cambiarContrasena(); 
            return;
        }

        String nuevaContra = JOptionPane.showInputDialog("Ingrese su nueva contraseña (5 caracteres):");
        if (nuevaContra == null) {  
            return;
        }
        
        if (nuevaContra.equals(contraActual)) {
        JOptionPane.showMessageDialog(null, "La nueva contraseña no puede ser igual a la actual", "Error", JOptionPane.ERROR_MESSAGE);
        cambiarContrasena();
        return;
    }

        if (nuevaContra.length() == 5) {
            playerActivo.setContra(nuevaContra);
            JOptionPane.showMessageDialog(null, "Contraseña cambiada exitosamente");
            return;
        } else {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener exactamente 5 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            cambiarContrasena(); 
            return;
        }
    }

    public static void eliminarCuenta() {
        Player playerActivo = PlayerInterface.getPlayerActivo();

        if (playerActivo == null) {
            JOptionPane.showMessageDialog(null, "No hay un jugador activo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String contraActual = JOptionPane.showInputDialog("Para eliminar la cuenta por favor ingrese su contraseña:");

        if (contraActual == null) {
            new MiCuenta();
            return;
        }

        if (!contraActual.equals(playerActivo.getContra())) {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta, No se elimino la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
            eliminarCuenta();
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea eliminar su cuenta?", "Confirmacion", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            PlayerInterface.eliminarJugador(playerActivo);
            PlayerInterface.setPlayerActivo(null);
            JOptionPane.showMessageDialog(null, "Su cuenta ha sido eliminada exitosamente");
            new Menu(); 
        } else {
            JOptionPane.showMessageDialog(null, "Su cuenta no fue eliminada");
            new MiCuenta(); 
        }
    }

    public static void mostrarRanking() {
        Player[] jugadoresOrdenados = PlayerInterface.getJugadores();
        int contadorJugadores = PlayerInterface.getContadorJugadores();

        if (contadorJugadores == 0) {
            JOptionPane.showMessageDialog(null, "No hay jugadores registrados");
            return;
        }

        Player[] jugadoresCopia = new Player[contadorJugadores];
        System.arraycopy(jugadoresOrdenados, 0, jugadoresCopia, 0, contadorJugadores);

        for (int i = 0; i < contadorJugadores - 1; i++) {
            for (int j = 0; j < contadorJugadores - i - 1; j++) {
                if (jugadoresCopia[j].getPuntos() < jugadoresCopia[j + 1].getPuntos()) {
                    Player temp = jugadoresCopia[j];
                    jugadoresCopia[j] = jugadoresCopia[j + 1];
                    jugadoresCopia[j + 1] = temp;
                }
            }
        }

        String ranking = " Ranking de jugadores:\n";
       for (int i = 0; i < contadorJugadores; i++) {
       ranking += (i + 1) + ". " + jugadoresCopia[i].getUsuario() + 
              " - Puntos: " + jugadoresCopia[i].getPuntos() + "\n";
     }
        JOptionPane.showMessageDialog(null, ranking.toString());
    }
    
     public static void addHistorial(String ganador, String perdedor, String mensaje, boolean rindio) {
    for (int i = Historial.length - 1; i > 0; i--) {
        Historial[i] = Historial[i - 1];
    }
    
    Historial[0] = new Historial(ganador, perdedor, mensaje, rindio);
    
    if (logCount < Historial.length) {
        logCount++;
    }
} 
    
     public static void logs() {
    if (logCount == 0) {
        JOptionPane.showMessageDialog(null, "No hay registros de partidas");
        return;
    }
    
    String logsText = "Ultimas partidas:\n";
    for (int i = 0; i < logCount; i++) {
        logsText += Historial[i].toString() + "\n";
    }
    
    JOptionPane.showMessageDialog(null, logsText);
}

}