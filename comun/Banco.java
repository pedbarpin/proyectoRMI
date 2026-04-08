package comun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaz remota para el servicio bancario.
 * Universidad de Sevilla - Sistemas Distribuidos
 */
public interface Banco extends Remote {

    /**
     * Crea una nueva cuenta bancaria para un titular.
     * @param t El titular de la cuenta (se pasa por valor)
     * @return El objeto remoto Cuenta
     * @throws RemoteException Si ocurre un error en la comunicación RMI
     */
    Cuenta crearCuenta(Titular t) throws RemoteException;

    /**
     * Obtiene la lista de todas las cuentas bancarias.
     * @return Lista de objetos remotos Cuenta
     * @throws RemoteException Si ocurre un error en la comunicación RMI
     */
    List<Cuenta> obtenerCuentas() throws RemoteException;
}
