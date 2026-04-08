-- Script de inicialización de base de datos para Sistema Bancario Distribuido
-- Universidad de Sevilla - Sistemas Distribuidos

-- Eliminar tabla si existe (para reinicio limpio)
DROP TABLE IF EXISTS cuentas;

-- Crear tabla de cuentas bancarias
CREATE TABLE cuentas (
    id_titular VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    saldo FLOAT DEFAULT 0.0
);

-- Insertar algunos datos de ejemplo (opcional)
INSERT INTO cuentas (id_titular, nombre, saldo) VALUES
    ('001', 'Juan Garcia', 1000.00),
    ('002', 'Maria Lopez', 2500.50),
    ('003', 'Carlos Martinez', 500.00);

-- Verificación de datos
SELECT * FROM cuentas;
