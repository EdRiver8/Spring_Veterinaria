--/**** DROP TABLES - al contrario de su creacion por las relaciones FK ******/
DROP TABLE IF EXISTS medicamentos;
DROP TABLE IF EXISTS servicios;
DROP TABLE IF EXISTS mascotas;
DROP TABLE IF EXISTS clientes;


--/*********** CREATE TABLES ****************/
CREATE TABLE IF NOT EXISTS clientes (
  id serial, /* serial es el autoincrementable de postgres */
  cedula int not null,
  nombres varchar(50) NOT NULL,
  apellidos varchar(255) NOT NULL,
  direccion varchar(255),
  telefono varchar(15),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS mascotas (
  id serial PRIMARY KEY,
  nombre varchar(50) NOT NULL,
  raza varchar(50),
  edad int,
  peso float4,
  cliente_id int NOT NULL,
  FOREIGN KEY (cliente_id) REFERENCES clientes (id)
);

CREATE TABLE IF NOT EXISTS servicios (
  id serial PRIMARY KEY,
  fecha_servicio DATE NOT NULL,
  descripcion varchar(255),
  mascota_id int NOT NULL,
  FOREIGN KEY (mascota_id) REFERENCES mascotas (id)
);

CREATE TABLE IF NOT EXISTS medicamentos (
  id serial PRIMARY KEY,
  nombre varchar(50) NOT NULL,
  descripcion varchar(50),
  dosis varchar(20)
);

--/*********** ADD FK FROM mascotas TO medicamentos ***************/
ALTER TABLE medicamentos ADD servicio_id int NOT NULL;
ALTER TABLE medicamentos ADD FOREIGN KEY (servicio_id) REFERENCES servicios (id);
