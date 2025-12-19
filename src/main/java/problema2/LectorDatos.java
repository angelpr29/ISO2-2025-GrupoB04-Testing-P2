package problema2;

import java.util.Scanner;

public class LectorDatos {
	
    private static Scanner scanner = new Scanner(System.in);

    public static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir un número entero válido.");
            }
        }
    }


    public static boolean leerBooleanoSN(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("S")) return true;
            if (input.equals("N")) return false;
            System.out.println("Error: Responde 'S' (Sí) o 'N' (No).");
        }
    }

    public static String leerClasePreferida() {
        while (true) {
            System.out.print("Clase preferida (1. Turista / 2. Business): ");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) return "Turista";
            if (input.equals("2")) return "Business";
            System.out.println("Error: Selecciona 1 o 2.");
        }
    }

    public static String leerDestino() {
        while (true) {
            System.out.print("Destino (EU, AM, AS, OTRO): ");
            String dest = scanner.nextLine().trim().toUpperCase();
            if (dest.matches("EU|AM|AS|OTRO")) { 
                return dest;
            }
            System.out.println("Error: Código desconocido. Usa: EU, AM, AS u OTRO.");
        }
    }
    
    public static void cerrar() {
        scanner.close();
    }
}