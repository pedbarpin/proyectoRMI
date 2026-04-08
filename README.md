<<<<<<< HEAD
# Sistema Bancario Distribuido - RMI

**Universidad de Sevilla - Sistemas Distribuidos**

Implementación de un sistema bancario distribuido utilizando Java RMI (Remote Method Invocation) con persistencia en PostgreSQL.

## Estructura del Proyecto

```
proyectoRMI/
├── cliente/
│   └── ClienteBanco.java    # Cliente de demostración
├── servidor/
│   └── ServidorBanco.java   # Servidor principal
├── comun/
│   ├── Banco.java           # Interfaz remota del servicio
│   ├── BancoImpl.java       # Implementación del servicio
│   ├── Cuenta.java          # Interfaz remota de cuenta
│   ├── CuentaImpl.java      # Implementación de cuenta
│   └── Titular.java         # Modelo serializable
└── README.md
```

## Requisitos Previos

- Java Development Kit (JDK) 8 o superior
- PostgreSQL instalado y ejecutándose
- Driver JDBC de PostgreSQL (`postgresql-*.jar`)

## Configuración de la Base de Datos

1. Crear la base de datos en PostgreSQL:
```bash
createdb banco
```

2. Ejecutar el script de inicialización:
```bash
psql -d banco -f init.sql
```

## Compilación

```bash
# Compilar desde la raíz del proyecto
javac -cp .:postgresql-*.jar comun/*.java servidor/*.java cliente/*.java
```

## Ejecución

### 1. Iniciar el RMI Registry (puerto 54321)

```bash
rmiregistry -J-Djava.class.path=. -J-Djava.rmi.server.hostname=localhost 54321
```

### 2. Ejecutar el Servidor

En una nueva terminal:

```bash
java -Djava.security.policy=servidor.permisos \
     -Djava.rmi.server.hostname=localhost \
     -cp .:comun:postgresql-*.jar \
     servidor.ServidorBanco
```

### 3. Ejecutar el Cliente

En otra terminal nueva:

```bash
java -Djava.rmi.server.hostname=localhost \
     -cp .:comun:postgresql-*.jar \
     cliente.ClienteBanco localhost
```

## Comandos Rápidos (Script Completo)

```bash
# Terminal 1 - RMI Registry
rmiregistry -J-Djava.class.path=. -J-Djava.rmi.server.hostname=localhost 54321

# Terminal 2 - Servidor
java -Djava.security.policy=servidor.permisos -Djava.rmi.server.hostname=localhost -cp .:comun:postgresql-*.jar servidor.ServidorBanco

# Terminal 3 - Cliente
java -Djava.rmi.server.hostname=localhost -cp .:comun:postgresql-*.jar cliente.ClienteBanco localhost
```

## Arquitectura del Sistema

### Interfaces RMI

| Interfaz | Descripción |
|----------|-------------|
| `Banco` | Servicio principal (Patrón Fábrica) |
| `Cuenta` | Cuenta bancaria individual |

### Clases Principales

| Clase | Paquete | Descripción |
|-------|---------|-------------|
| `Titular` | `comun` | Modelo serializable (paso por VALOR) |
| `BancoImpl` | `comun` | Implementación del servicio Banco |
| `CuentaImpl` | `comun` | Implementación de Cuenta (métodos `synchronized`) |
| `ServidorBanco` | `servidor` | Servidor que exporta el servicio RMI |
| `ClienteBanco` | `cliente` | Cliente de demostración |

### Operaciones Disponibles

- `crearCuenta(Titular t)` - Crea nueva cuenta
- `obtenerCuentas()` - Lista todas las cuentas
- `obtenerSaldo()` - Consulta saldo
- `ingresar(float)` - Ingresa cantidad
- `retirar(float)` - Retira cantidad

## Seguridad

El archivo `servidor.permisos` otorga todos los permisos necesarios para la ejecución del servidor RMI. En un entorno de producción, se recomienda restringir los permisos a los mínimos necesarios.

## Solución de Problemas

### Error: "Connection refused"
- Verificar que `rmiregistry` esté ejecutándose
- Comprobar que el servidor se haya iniciado correctamente

### Error: "Cannot bind to location"
- El puerto 54321 ya está en uso
- Matar procesos RMI existentes o usar otro puerto

### Error: "UnknownHostException"
- Especificar explícitamente el hostname:
```bash
java -Djava.rmi.server.hostname=127.0.0.1 ...
```

## Autor

Implementación para la asignatura de Sistemas Distribuidos - Universidad de Sevilla
=======
# proyectoRMI
proyecto RMI para la asignatura de SDSW
>>>>>>> 35795f5eacfe9aa1d7e308b44f5ebe875aa6f85e
