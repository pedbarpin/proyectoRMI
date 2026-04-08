package cliente;

import comun.*;
import java.rmi.Naming;
import java.util.List;

/**
 * Cliente de demostración del Sistema Bancario Distribuido.
 * Muestra: búsqueda del Banco, creación de cuenta, ingresos, retiradas y listado.
 * Universidad de Sevilla - Sistemas Distribuidos
 */
public class ClienteBanco {

    // Puerto y nombre del servicio (deben coincidir con el servidor)
    private static final int RMI_PORT = 54321;
    private static final String SERVICE_NAME = "Banco";

    /**
     * Método principal que ejecuta las operaciones de demostración.
     * @param args Argumentos: [hostname] (por defecto: localhost)
     */
    public static void main(String[] args) {
        // Obtener hostname desde argumentos o usar localhost
        String hostname = (args.length > 0) ? args[0] : "localhost";

        System.out.println("=== Cliente Bancario Distribuido ===");
        System.out.println("Universidad de Sevilla - Sistemas Distribuidos");
        System.out.println("Conectando a: " + hostname);
        System.out.println();

        try {
            // Construir URL del servicio RMI
            String url = "rmi://" + hostname + ":" + RMI_PORT + "/" + SERVICE_NAME;
            System.out.println("[INFO] Buscando servicio en: " + url);

            // Buscar el objeto remoto Banco
            Banco banco = (Banco) Naming.lookup(url);
            System.out.println("[OK] Servicio Banco encontrado");
            System.out.println();

            // --- DEMOSTRACIÓN DE OPERACIONES ---

            // 1. Crear un nuevo titular y su cuenta
            System.out.println("=== 1. Crear Nueva Cuenta ===");
            Titular nuevoTitular = new Titular("100", "Ana Fernandez");
            System.out.println("Titular: " + nuevoTitular);

            Cuenta nuevaCuenta = banco.crearCuenta(nuevoTitular);
            System.out.println("Cuenta creada exitosamente");
            System.out.println("Saldo inicial: " + nuevaCuenta.obtenerSaldo());
            System.out.println();

            // 2. Realizar un ingreso
            System.out.println("=== 2. Realizar Ingreso ===");
            float cantidadIngresar = 500.0f;
            System.out.println("Ingresando: " + cantidadIngresar + " EUR");
            nuevaCuenta.ingresar(cantidadIngresar);
            System.out.println("Saldo después del ingreso: " + nuevaCuenta.obtenerSaldo() + " EUR");
            System.out.println();

            // 3. Realizar una retirada
            System.out.println("=== 3. Realizar Retirada ===");
            float cantidadRetirar = 200.0f;
            System.out.println("Retirando: " + cantidadRetirar + " EUR");
            nuevaCuenta.retirar(cantidadRetirar);
            System.out.println("Saldo después de la retirada: " + nuevaCuenta.obtenerSaldo() + " EUR");
            System.out.println();

            // 4. Listar todas las cuentas existentes
            System.out.println("=== 4. Listar Cuentas Existentes ===");
            List<Cuenta> cuentas = banco.obtenerCuentas();
            System.out.println("Total de cuentas en el sistema: " + cuentas.size());
            System.out.println();

            int i = 1;
            for (Cuenta cuenta : cuentas) {
                if (cuenta instanceof CuentaImpl) {
                    CuentaImpl impl = (CuentaImpl) cuenta;
                    System.out.println("  Cuenta " + i + ": " + impl.getNombre() +
                                       " (ID: " + impl.getIdTitular() + ")" +
                                       " - Saldo: " + impl.obtenerSaldo() + " EUR");
                } else {
                    System.out.println("  Cuenta " + i + ": " + cuenta.obtenerSaldo() + " EUR");
                }
                i++;
            }
            System.out.println();

            // 5. Demostrar operación con cuenta existente
            System.out.println("=== 5. Operación Adicional ===");
            System.out.println("Creando segunda cuenta de prueba...");
            Titular titular2 = new Titular("101", "Pedro Ruiz");
            Cuenta cuenta2 = banco.crearCuenta(titular2);
            cuenta2.ingresar(1000.0f);
            System.out.println("Segunda cuenta creada con saldo: " + cuenta2.obtenerSaldo() + " EUR");
            System.out.println();

            System.out.println("=== Demostración Completada Exitosamente ===");

        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getClass().getSimpleName() + ": " + e.getMessage());
            System.err.println();
            System.err.println("Posibles causas:");
            System.err.println("  - El servidor no está ejecutándose");
            System.err.println("  - El hostname/puerto es incorrecto");
            System.err.println("  - Problemas de conectividad de red");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
