package comun;

import java.io.Serializable;

/**
 * Clase que representa un titular de cuenta bancaria.
 * Se transfiere por VALOR en las llamadas RMI (implementa Serializable).
 * Universidad de Sevilla - Sistemas Distribuidos
 */
public class Titular implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;

    /**
     * Constructor por defecto requerido para serialización.
     */
    public Titular() {
    }

    /**
     * Constructor con parámetros.
     * @param id Identificador único del titular
     * @param nombre Nombre completo del titular
     */
    public Titular(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador del titular.
     * @return El ID del titular
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del titular.
     * @param id El nuevo ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del titular.
     * @return El nombre del titular
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del titular.
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Titular{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               '}';
    }
}
