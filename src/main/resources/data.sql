/*********** INSERT DATA ****************/
INSERT INTO clientes (cedula, nombres, apellidos, direccion, telefono) 
	VALUES (123, 'Juan Esteban', 'Giraldo', 'Calle 10', '555');
INSERT INTO clientes (cedula, nombres, apellidos, direccion, telefono) 
	VALUES (777, 'Juan Pablo', 'Rivera', 'Av 48', '444');
INSERT INTO clientes (cedula, nombres, apellidos, direccion, telefono) 
	VALUES (7020, 'Nicolas', 'Hidalgo', 'Diag 27', '222');


INSERT INTO mascotas (nombre, raza, edad, peso, cliente_id)
	VALUES ('Kay', 'Pug', 5, 15.5, 1);
INSERT INTO mascotas (nombre, raza, edad, peso, cliente_id)
	VALUES ('Danger', 'Pitbull', 9, 22.5, 2);
INSERT INTO mascotas (nombre, raza, edad, peso, cliente_id)
	VALUES ('Fenix', 'Gran Danes', 5, 35.5, 3);
INSERT INTO mascotas (nombre, raza, edad, peso, cliente_id)
	VALUES ('Leonidas', 'Boxer', 5, 24.3, 2);
INSERT INTO mascotas (nombre, raza, edad, peso, cliente_id)
	VALUES ('Koney', 'Pitbull', 5, 31.0, 1);


INSERT INTO servicios (fecha_servicio, descripcion, mascota_id)
	VALUES ('2022-10-9', 'Perro con pulgas', 1);
INSERT INTO servicios (fecha_servicio, descripcion, mascota_id)
	VALUES ('2022-08-19', 'Gastritis', 2);
INSERT INTO servicios (fecha_servicio, descripcion, mascota_id)
	VALUES ('2021-2-9', 'Problemas del corazon', 5);
INSERT INTO servicios (fecha_servicio, descripcion, mascota_id)
	VALUES ('2022-1-29', 'Perro muy activo', 4);
INSERT INTO servicios (fecha_servicio, descripcion, mascota_id)
	VALUES ('2022-10-9', 'Problemas del corazon', 3);


INSERT INTO medicamentos (nombre, descripcion, dosis, servicio_id)
	VALUES ('Adaptil', 'Ayuda a la ansiedad', '60 ml', 1);
INSERT INTO medicamentos (nombre, descripcion, dosis, servicio_id)
	VALUES ('Pimocard', 'Reduce la tendencia a arritmias', '10 mg', 2);
INSERT INTO medicamentos (nombre, descripcion, dosis, servicio_id)
	VALUES ('Attack', 'Control de pulgas', '1.5 ml', 5);
INSERT INTO medicamentos (nombre, descripcion, dosis, servicio_id)
	VALUES ('Rondel', 'Antiparasitario', '5 ml', 4);
INSERT INTO medicamentos (nombre, descripcion, dosis, servicio_id)
	VALUES ('Adaptil', 'Ayuda a la ansiedad', '60 ml', 3);



-- REPORTE INFORME DE SERVICIOS
-- SELECT medicamentos.nombre FROM medicamentos;

-- SELECT med.nombre as nombre_med, ser.descripcion as descripcion_servicio 
-- 	FROM medicamentos as med INNER JOIN servicios as ser ON med.id = ser.medicamento_id;
	
-- SELECT mas.nombre as mascota, med.nombre as nombre_med, ser.descripcion as descripcion_servicio 
-- 	FROM medicamentos as med INNER JOIN servicios as ser ON med.id = ser.medicamento_id
-- 	INNER JOIN mascotas as mas ON ser.mascota_id = mas.id;
	
-- SELECT cli.nombres as nombre_cliente, mas.nombre as mascota, med.nombre as nombre_med, ser.descripcion as descripcion_servicio 
-- 	FROM medicamentos as med INNER JOIN servicios as ser ON med.id = ser.medicamento_id
-- 	INNER JOIN mascotas as mas ON ser.mascota_id = mas.id
-- 	INNER JOIN clientes as cli ON cli.id = mas.cliente_id;