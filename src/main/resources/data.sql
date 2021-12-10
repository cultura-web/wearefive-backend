INSERT INTO usuario (id,nombres,apellidos,username,contrasena,correo)
VALUES (1,'nombrex','apellidox','admin','12345678','example@correo.com'),
       (2,'cliente1','apellidoCliente1','cliente1','12345678','example2@correo.com');

INSERT INTO cliente (id, direccion, celular, usuario_id)
VALUES (1, 'calle 20 con avenida 2 #5-65', '3224387110', 2);

INSERT INTO admin (id, usuario_id)
VALUES (1, 1);

INSERT INTO qa (id,pregunta,respuesta,admin_id)
VALUES (1,'¿cuales son los medios de pago?','Nequi y Bancolombia',1);

INSERT INTO modelo_zapato (id, color, costo, preciounitario, descuento, nombre, descripcion, detalle, material,
                           imagenurl, tipo)
VALUES (1, 'negro', 8000, 10000, 50, 'ITADORI', 'Economico y artesanal, proporciona a tus pies la sensación de caminar en el cielo', 'hecho con costuras delicadas y resistentes', 'cuero', 'ejemplo.com', 'bota'),
       (2, 'azul', 15000, 20000, 0, 'THE ZIPPER', 'Minimalista en su diseño, con una cremallera atravezando la capellada', 'hecho con una cremayera entre las costuras del zapato', 'Cuero', 'ejemplo.com', 'tenis'),
       (3, 'azul', 8000, 10000, 50, 'CRANE DEUX', 'Un diseño fuera de onda, ideal para cualquier rockstar', 'hecho a medida con bordes resistentes', 'Cuero', 'ejemplo.com', 'bota'),
       (4, 'negro', 8000, 10000, 50, 'ICARO', 'diseño clasico de toda la vida con agarre perfecto para mayor durabilidad', 'costuras derechas, sin hilos sueltos ni pliegues indeseables.', 'cuero', 'ejemplo.com', 'bota');

INSERT INTO material (id, nombre, detalle, precio_unitario, unidad)
VALUES (1, 'suela de poliester', 'suelas especiales para zapato tipo tenis', 1000, 'unidad'),
       (2, 'cordones de algodon', 'cordones hechos en algodon', 500, 'par');

INSERT INTO proceso (id, nombre, detalle, costo_total, modelo_zapato_id)
VALUES (1, 'cocer', 'para este proceso es necesario contar con maquina de cocer y materiales necesarios', 15000, 1),
       (2, 'cortar', 'para este proceso es necesario contar con cortadora y materiales de cortado', 10000, 1);

INSERT INTO material_de_proceso (id, cantidad, material_id, proceso_id)
VALUES (1, 10, 1, 1),
       (2, 10, 2, 1),
       (3, 5, 1, 2),
       (4, 10, 2, 2);