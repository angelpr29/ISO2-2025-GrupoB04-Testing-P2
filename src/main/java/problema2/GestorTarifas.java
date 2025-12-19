package problema2;

public class GestorTarifas {

    public String determinarTarifa(PerfilPasajero p) throws ExcepcionTarifa {
        // Validaciones básicas
        if (p.getEdad() < 0) throw new ExcepcionTarifa("La edad no puede ser negativa.");
        if (p.getIngresos() < 0) throw new ExcepcionTarifa("Los ingresos no pueden ser negativos.");
        if (p.getViajesPlacerAnuales() < 0) throw new ExcepcionTarifa("El número de viajes no puede ser negativo.");
        if ((p.getVuelosAnuales() < 0) || (p.getVuelosMensualesCurso() < 0)) throw new ExcepcionTarifa("El número de vuelos no puede ser negativo.");

        // Tarifa Pajarillo
        // Requisitos: Ser menor de 18 años y realizar 6 o más vuelos anuales totales
        if (p.getEdad() < 18 && p.getVuelosAnuales() >= 6) {
            return "Pajarillo (Descuento 10%)";
        }

        // Tarifa Gorrión 
        // Requisitos: Entre 18 y 25 años, ser estudiante, volar en clase Turista y hacer al menos 1 vuelo mensual durante el curso
        if (p.getEdad() >= 18 && p.getEdad() <= 25 && p.isEsEstudiante() 
            && "Turista".equalsIgnoreCase(p.getClase()) && p.getVuelosMensualesCurso() >= 1) {
            return "Gorrión (Descuento 15%)";
        }

        // Tarifas Trabajadores Jóvenes (Entre 18 y 25 años que esten trabajando)
        if (p.getEdad() >= 18 && p.getEdad() <= 25 && p.isTrabaja()) {
            
            // Requisitos: Vivir con los padres, clase Turista y al menos 3 viajes de placer al año
            if (p.isViveConPadres() && p.getViajesPlacerAnuales() >= 3 && "Turista".equalsIgnoreCase(p.getClase())) {
                return "Viaja ahora que puedes (Descuento 5%)";
            }
            
            // Requisitos: No vivir con los padres
            if (!p.isViveConPadres()) {
                return "Atreviéndose a saltar del Nido (Descuento 25%)";
            }
        }

        // Conoce Europa
        // Requisitos: Mayor de 25 años, ingresos entre 20.000 y 35.000, 6 o más vuelos anuales, clase Turista y destino Europa
        if (p.getEdad() > 25 && p.getIngresos() > 20000 && p.getIngresos() < 35000 
            && p.getVuelosAnuales() >= 6 && "Turista".equalsIgnoreCase(p.getClase()) 
            && "EU".equalsIgnoreCase(p.getDestino())) {
            
            if (p.isViajaConNinos()) {
                return "Conoce Europa con tus peques (Descuento 10% por billete)";
            } else {
                return "Conoce Europa (Descuento 15%)";
            }
        }

        // Conoce el Mundo 
        // Requisitos: Mayor de 25 años, ingresos superiores a 35.000, 6 o más vuelos anuales, clase Business y destino América o Asia
        if (p.getEdad() > 25 && p.getIngresos() >= 35000 && p.getVuelosAnuales() >= 6 
            && "Business".equalsIgnoreCase(p.getClase()) 
            && ("AS".equalsIgnoreCase(p.getDestino()) || "AM".equalsIgnoreCase(p.getDestino()))) {
            
            if (p.isViajaConNinos()) {
                return "Conoce el Mundo con tus peques (Descuento 10% por billete)";
            } else {
                return "Conoce el Mundo (Descuento 20%)";
            }
        }

        // Si no se adapta a ningun descuento, tarifa estándar
        return "Tarifa Estándar (Sin descuento)";
    }
}