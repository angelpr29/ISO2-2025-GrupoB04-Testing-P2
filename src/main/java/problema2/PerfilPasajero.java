package problema2;


public class PerfilPasajero {
    private int edad;
    private double ingresos;
    private int vuelosAnuales;
    private int vuelosMensualesCurso; 
    private int viajesPlacerAnuales;  
    private boolean esEstudiante;
    private boolean trabaja;
    private boolean viveConPadres;
    private boolean viajaConNinos;   
    private String clase;             
    private String destino;

    
    public PerfilPasajero() {
    }

    

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public int getVuelosAnuales() {
        return vuelosAnuales;
    }

    public void setVuelosAnuales(int vuelosAnuales) {
        this.vuelosAnuales = vuelosAnuales;
    }

    public int getVuelosMensualesCurso() {
        return vuelosMensualesCurso;
    }

    public void setVuelosMensualesCurso(int vuelosMensualesCurso) {
        this.vuelosMensualesCurso = vuelosMensualesCurso;
    }

    public int getViajesPlacerAnuales() {
        return viajesPlacerAnuales;
    }

    public void setViajesPlacerAnuales(int viajesPlacerAnuales) {
        this.viajesPlacerAnuales = viajesPlacerAnuales;
    }

    public boolean isEsEstudiante() {
        return esEstudiante;
    }

    public void setEsEstudiante(boolean esEstudiante) {
        this.esEstudiante = esEstudiante;
    }

    public boolean isTrabaja() {
        return trabaja;
    }

    public void setTrabaja(boolean trabaja) {
        this.trabaja = trabaja;
    }

    public boolean isViveConPadres() {
        return viveConPadres;
    }

    public void setViveConPadres(boolean viveConPadres) {
        this.viveConPadres = viveConPadres;
    }

    public boolean isViajaConNinos() {
        return viajaConNinos;
    }

    public void setViajaConNinos(boolean viajaConNinos) {
        this.viajaConNinos = viajaConNinos;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}