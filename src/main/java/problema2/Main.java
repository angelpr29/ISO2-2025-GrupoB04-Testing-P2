package problema2;

public class Main {
    public static void main(String[] args) {
        PerfilPasajero perfil = new PerfilPasajero();
        GestorTarifas gestor = new GestorTarifas();

        System.out.println("=== SISTEMA DE TARIFAS SEGÚN LOS DATOS DEL USUARIO ===");

        try {
            perfil.setEdad(LectorDatos.leerEntero("Introduce edad: "));

            perfil.setEsEstudiante(LectorDatos.leerBooleanoSN("¿Es estudiante? (S/N): "));
            if (perfil.isEsEstudiante()) {
                 perfil.setVuelosMensualesCurso(LectorDatos.leerEntero("Número de vuelos mensuales durante el curso: "));
            }

            perfil.setTrabaja(LectorDatos.leerBooleanoSN("¿Trabaja? (S/N): "));
            perfil.setViveConPadres(LectorDatos.leerBooleanoSN("¿Vive con sus padres? (S/N): "));
            perfil.setIngresos(LectorDatos.leerEntero("Ingresos anuales (euros): "));
            perfil.setVuelosAnuales(LectorDatos.leerEntero("Vuelos anuales totales: "));
            perfil.setViajesPlacerAnuales(LectorDatos.leerEntero("Viajes de placer anuales: "));
            perfil.setClase(LectorDatos.leerClasePreferida()); 
            perfil.setDestino(LectorDatos.leerDestino());     
            perfil.setViajaConNinos(LectorDatos.leerBooleanoSN("¿Viaja con niños <12? (S/N): "));

            // Tarifa mas adecuada
            String tarifa = gestor.determinarTarifa(perfil);
            System.out.println("\n-----------------------------");
            System.out.println(" RESULTADO: " + tarifa);

        } catch (ExcepcionTarifa e) {
            System.err.println("Error seleccionando tarifa: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        } finally {
            LectorDatos.cerrar();
        }
    }
}