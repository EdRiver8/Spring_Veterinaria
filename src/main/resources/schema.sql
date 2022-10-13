--/**** DROP TABLES - al contrario de su creacion por las relaciones FK ******/
/**** DROP TABLES - al contrario de su creacion por las relaciones FK ******/
DROP TABLE IF EXISTS servicios;
DROP TABLE IF EXISTS medicamentos;
DROP TABLE IF EXISTS mascotas;
DROP TABLE IF EXISTS clientes;


/*********** CREATE TABLES ****************/
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

CREATE TABLE IF NOT EXISTS medicamentos (
  id serial PRIMARY KEY,
  nombre varchar(50) NOT NULL,
  descripcion varchar(50),
  dosis varchar(20)
);
	
CREATE TABLE IF NOT EXISTS servicios (
  id serial PRIMARY KEY,
  creado DATE NOT NULL,
  actualizado DATE,
  descripcion varchar(255),
  medicamento_id int NOT NULL,
  FOREIGN KEY (medicamento_id) REFERENCES medicamentos (id)
);

/*********** ADD FK FROM mascotas TO servicios ***************/
ALTER TABLE servicios ADD mascota_id int NOT NULL;
ALTER TABLE servicios ADD FOREIGN KEY (mascota_id) REFERENCES mascotas (id);
