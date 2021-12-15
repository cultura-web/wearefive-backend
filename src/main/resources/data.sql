INSERT INTO usuario (id,nombres,apellidos,username,contrasena,correo)
VALUES (1,'nombrex','apellidox','admin','12345678','example@correo.com'),
       (2,'cliente1','apellidoCliente1','cliente1','12345678','juanpablocardenasq@gmail.com');

INSERT INTO cliente (id, direccion, celular, usuario_id)
VALUES (1, 'calle 20 con avenida 2 #5-65', '3224387110', 2);

INSERT INTO admin (id, usuario_id)
VALUES (1, 1);

INSERT INTO qa (id,pregunta,respuesta,admin_id)
VALUES (1,'¿cuales son los medios de pago?','Nequi y Bancolombia',1);

INSERT INTO modelo_zapato (id, color, costo, preciounitario, descuento, nombre, descripcion, detalle, material,
                           imagenurl, tipo)
VALUES (1, 'negro', 8000, 10000, 50, 'ITADORI', 'Economico y artesanal, proporciona a tus pies la sensación de caminar en el cielo', 'hecho con costuras delicadas y resistentes', 'cuero', 'https://i.ibb.co/sFV6fDJ/9-3-itadori.jpg', 'bota'),
       (2, 'azul', 15000, 20000, 0, 'THE ZIPPER', 'Minimalista en su diseño, con una cremallera atravezando la capellada', 'hecho con una cremayera entre las costuras del zapato', 'Cuero', 'https://i.ibb.co/r27KmSk/10-1-thezipper.png', 'tenis'),
       (3, 'azul', 8000, 10000, 50, 'CRANE DEUX', 'Un diseño fuera de onda, ideal para cualquier rockstar', 'hecho a medida con bordes resistentes', 'Cuero', 'https://i.ibb.co/GpH4kjq/11-1-cr-ne-deux.png', 'bota'),
       (4, 'negro', 8000, 10000, 50, 'ICARO', 'diseño clasico de toda la vida con agarre perfecto para mayor durabilidad', 'costuras derechas, sin hilos sueltos ni pliegues indeseables.', 'cuero', 'https://i.ibb.co/3CSwjSn/1-1-icaro.png', 'bota'),
       (5, 'azul', 15000, 20000, 0, 'BASS OCEAN', 'Minimalista en su diseño, con una cremallera atravezando la capellada', 'hecho con una cremayera entre las costuras del zapato', 'Cuero', 'https://i.ibb.co/1KBLJZW/13-1-bass-ocean.png', 'tenis'),
       (6, 'marron', 8000, 10000, 50, 'BASS OCEAN2', 'Un diseño fuera de onda, ideal para cualquier rockstar', 'hecho a medida con bordes resistentes', 'Cuero', 'https://i.ibb.co/2NXnCJd/12-1-bass-coffee.png', 'bota'),
       (7, 'negro', 8000, 10000, 50, 'THE VEIN', 'diseño clasico de toda la vida con agarre perfecto para mayor durabilidad', 'costuras derechas, sin hilos sueltos ni pliegues indeseables.', 'cuero', 'https://i.ibb.co/JzmqqbZ/6-3-thevein.jpg', 'bota'),
       (8, 'azul', 15000, 20000, 0, 'LUCKY', 'Minimalista en su diseño, con una cremallera atravezando la capellada', 'hecho con una cremayera entre las costuras del zapato', 'Cuero', 'https://i.ibb.co/K0X02fR/5-1-lucky.png', 'tenis'),
       (9, 'gris', 8000, 10000, 50, 'THE GREY', 'Un diseño fuera de onda, ideal para cualquier rockstar', 'hecho a medida con bordes resistentes', 'Cuero', 'https://i.ibb.co/GFRYGy9/4-1-thegrey.png', 'bota'),
       (10, 'negro', 8000, 10000, 50, 'THE CANS', 'diseño clasico de toda la vida con agarre perfecto para mayor durabilidad', 'costuras derechas, sin hilos sueltos ni pliegues indeseables.', 'cuero', 'https://i.ibb.co/kGvs3Kk/7-1-thecans.png', 'bota'),
       (11, 'azul', 8000, 10000, 50, 'AUGUSTA', 'Un diseño fuera de onda, ideal para cualquier rockstar', 'hecho a medida con bordes resistentes', 'Cuero', 'https://i.ibb.co/T0xTjGP/3-1-Augusta.png', 'bota'),
       (12, 'negro', 8000, 10000, 50, 'NASCAR', 'diseño clasico de toda la vida con agarre perfecto para mayor durabilidad', 'costuras derechas, sin hilos sueltos ni pliegues indeseables.', 'cuero', 'https://i.ibb.co/4J8VvVP/2-2-nascar.jpg', 'bota');


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
INSERT INTO ejemplar_zapato (id, talla, status, modelo_zapato_id)
VALUES (1, '38', 'reservado',1),
       (2, '38', 'libre',1),
       (3, '40', 'libre',1);