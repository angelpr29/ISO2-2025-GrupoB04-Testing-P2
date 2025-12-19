package problema2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class TarifaTest {

    private GestorTarifas gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorTarifas();
    }

    private PerfilPasajero crearPerfil(int edad, double ingresos, int vuelosAnuales, 
                                       int vuelosMensuales, int viajesPlacer, 
                                       boolean esEstudiante, boolean trabaja, 
                                       boolean conPadres, boolean conNinos, 
                                       String clase, String destino) {
        PerfilPasajero p = new PerfilPasajero();
        p.setEdad(edad);
        p.setIngresos(ingresos);
        p.setVuelosAnuales(vuelosAnuales);
        p.setVuelosMensualesCurso(vuelosMensuales);
        p.setViajesPlacerAnuales(viajesPlacer);
        p.setEsEstudiante(esEstudiante);
        p.setTrabaja(trabaja);
        p.setViveConPadres(conPadres);
        p.setViajaConNinos(conNinos);
        p.setClase(clase);
        p.setDestino(destino);
        return p;
    }

    @Test
    @DisplayName("CP-01: Edad negativa lanza Excepcion")
    void testEdadNegativa() {
        PerfilPasajero p = crearPerfil(-1, 1000, 1, 0, 0, false, false, false, false, "Turista", "EU");
        Exception e = assertThrows(ExcepcionTarifa.class, () -> gestor.determinarTarifa(p));
        assertEquals("La edad no puede ser negativa.", e.getMessage());
    }

    @Test
    @DisplayName("CP-02: Ingresos negativos lanzan Excepcion")
    void testIngresosNegativos() {
        PerfilPasajero p = crearPerfil(20, -100, 1, 0, 0, false, false, false, false, "Turista", "EU");
        Exception e = assertThrows(ExcepcionTarifa.class, () -> gestor.determinarTarifa(p));
        assertEquals("Los ingresos no pueden ser negativos.", e.getMessage());
    }

    @Test
    @DisplayName("CP-03: Viajes de placer negativos lanzan Excepcion")
    void testViajesPlacerNegativos() {
        PerfilPasajero p = crearPerfil(20, 1000, 1, 0, -5, false, false, false, false, "Turista", "EU");
        Exception e = assertThrows(ExcepcionTarifa.class, () -> gestor.determinarTarifa(p));
        assertEquals("El número de viajes no puede ser negativo.", e.getMessage());
    }

    @Test
    @DisplayName("CP-04: Vuelos anuales negativos lanzan Excepcion")
    void testVuelosAnualesNegativos() {
        PerfilPasajero p = crearPerfil(20, 1000, -1, 0, 0, false, false, false, false, "Turista", "EU");
        Exception e = assertThrows(ExcepcionTarifa.class, () -> gestor.determinarTarifa(p));
        assertEquals("El número de vuelos no puede ser negativo.", e.getMessage());
    }

    @Test
    @DisplayName("CP-05: Vuelos mensuales negativos lanzan Excepcion")
    void testVuelosMensualesNegativos() {
        PerfilPasajero p = crearPerfil(20, 1000, 1, -1, 0, false, false, false, false, "Turista", "EU");
        Exception e = assertThrows(ExcepcionTarifa.class, () -> gestor.determinarTarifa(p));
        assertEquals("El número de vuelos no puede ser negativo.", e.getMessage());
    }

    @Test
    @DisplayName("CP-06: Pajarillo Válido (Edad 17, Vuelos 6)")
    void testPajarilloValido() throws ExcepcionTarifa {
        // Límite superior edad (17) y límite inferior vuelos (6)
        PerfilPasajero p = crearPerfil(17, 0, 6, 0, 0, false, false, false, false, "Turista", "EU");
        assertEquals("Pajarillo (Descuento 10%)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-07: No Pajarillo (Edad 18) -> Estándar")
    void testNoPajarilloPorEdad() throws ExcepcionTarifa {
        // Valor límite frontera: 18 años ya no es < 18
        PerfilPasajero p = crearPerfil(18, 0, 6, 0, 0, false, false, false, false, "Turista", "EU");
        // No cumple gorrion (no estudiante) -> Estándar
        assertEquals("Tarifa Estándar (Sin descuento)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-08: No Pajarillo (Vuelos 5) -> Estándar")
    void testNoPajarilloPorVuelos() throws ExcepcionTarifa {
        // Valor límite: 5 vuelos (requiere 6)
        PerfilPasajero p = crearPerfil(17, 0, 5, 0, 0, false, false, false, false, "Turista", "EU");
        assertEquals("Tarifa Estándar (Sin descuento)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-09: Gorrión Válido (Frontera Edad 18)")
    void testGorrionValidoEdadMinima() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(18, 0, 0, 1, 0, true, false, false, false, "Turista", "EU");
        assertEquals("Gorrión (Descuento 15%)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-10: Gorrión Válido (Frontera Edad 25)")
    void testGorrionValidoEdadMaxima() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(25, 0, 0, 1, 0, true, false, false, false, "Turista", "EU");
        assertEquals("Gorrión (Descuento 15%)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-11: No Gorrión (No Estudiante)")
    void testGorrionFalloNoEstudiante() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(20, 0, 0, 1, 0, false, false, false, false, "Turista", "EU");
        assertEquals("Tarifa Estándar (Sin descuento)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-12: No Gorrión (Clase Business)")
    void testGorrionFalloClase() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(20, 0, 0, 1, 0, true, false, false, false, "Business", "EU");
        assertEquals("Tarifa Estándar (Sin descuento)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-13: No Gorrión (Vuelos Mensuales 0)")
    void testGorrionFalloVuelos() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(20, 0, 0, 0, 0, true, false, false, false, "Turista", "EU");
        assertEquals("Tarifa Estándar (Sin descuento)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-14: Viaja Ahora Válido")
    void testViajaAhoraValido() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(22, 10000, 0, 0, 3, false, true, true, false, "Turista", "EU");
        assertEquals("Viaja ahora que puedes (Descuento 5%)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-15: No Viaja Ahora (Menos de 3 viajes)")
    void testViajaAhoraFalloViajes() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(22, 10000, 0, 0, 2, false, true, true, false, "Turista", "EU");
        assertEquals("Tarifa Estándar (Sin descuento)", gestor.determinarTarifa(p));
    }
    
    @Test
    @DisplayName("CP-16: Saltar del Nido Válido")
    void testSaltarNidoValido() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(22, 20000, 0, 0, 0, false, true, false, false, "Turista", "EU");
        assertEquals("Atreviéndose a saltar del Nido (Descuento 25%)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-17: Conoce Europa Válido (Sin niños)")
    void testConoceEuropaValido() throws ExcepcionTarifa {
        // Ingresos 20001 (Limite inferior + 1)
        PerfilPasajero p = crearPerfil(26, 20001, 6, 0, 0, false, true, false, false, "Turista", "EU");
        assertEquals("Conoce Europa (Descuento 15%)", gestor.determinarTarifa(p));
    }
    
    @Test
    @DisplayName("CP-18: Conoce Europa Válido (Con niños)")
    void testConoceEuropaNinos() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(30, 30000, 6, 0, 0, false, true, false, true, "Turista", "EU");
        assertEquals("Conoce Europa con tus peques (Descuento 10% por billete)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-19: No Conoce Europa (Ingresos 20000 - Limite)")
    void testConoceEuropaFalloIngresosBajos() throws ExcepcionTarifa {
        // Debe ser estricto > 20000
        PerfilPasajero p = crearPerfil(26, 20000, 6, 0, 0, false, true, false, false, "Turista", "EU");
        assertEquals("Tarifa Estándar (Sin descuento)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-20: No Conoce Europa (Destino no EU)")
    void testConoceEuropaFalloDestino() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(26, 30000, 6, 0, 0, false, true, false, false, "Turista", "AS");
        assertEquals("Tarifa Estándar (Sin descuento)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-21: Conoce Mundo Válido (AS)")
    void testConoceMundoValidoAS() throws ExcepcionTarifa {
        // Ingresos 35000 (Límite inclusivo)
        PerfilPasajero p = crearPerfil(26, 35000, 6, 0, 0, false, true, false, false, "Business", "AS");
        assertEquals("Conoce el Mundo (Descuento 20%)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-22: Conoce Mundo Válido (AM) con Niños")
    void testConoceMundoValidoAMNinos() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(26, 40000, 6, 0, 0, false, true, false, true, "Business", "AM");
        assertEquals("Conoce el Mundo con tus peques (Descuento 10% por billete)", gestor.determinarTarifa(p));
    }

    @Test
    @DisplayName("CP-23: No Conoce Mundo (Clase Turista)")
    void testConoceMundoFalloClase() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(26, 40000, 6, 0, 0, false, true, false, false, "Turista", "AM");
        assertEquals("Tarifa Estándar (Sin descuento)", gestor.determinarTarifa(p));
    }
    
    @Test
    @DisplayName("CP-24: Limbo 35k Turista (Falla Europa y Mundo)")
    void testLimboIngresos35kTurista() throws ExcepcionTarifa {
        PerfilPasajero p = crearPerfil(26, 35000, 6, 0, 0, false, true, false, false, "Turista", "EU");
        assertEquals("Tarifa Estándar (Sin descuento)", gestor.determinarTarifa(p));
    }
}