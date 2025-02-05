package proyecto1programacion2;

public class Historial {
    private String ganador;
    private String perdedor;
    private String menf;
    private boolean rindio;

    public Historial(String ganador, String perdedor, String menf, boolean rindio) {
        this.ganador = ganador;
        this.perdedor = perdedor;
        this.menf = menf;
        this.rindio = rindio;
    }

    public String toString() {
      String tipo;
       if (rindio){
       tipo = "Partida por Retiro";
       return  tipo + " - " + menf;
       } else {
     tipo = "Partida por Captura del General";
     return  tipo + " - " + menf;
     }
    }
}
        
    

