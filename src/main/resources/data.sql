INSERT INTO usuario (id,nombres,apellidos,username,contrasena,correo)
VALUES (1,'nombrex','apellidox','usernamex','1234','example@correo.com');

INSERT INTO admin (id, usuario_id)
VALUES (1, 1);

INSERT INTO qa (id,pregunta,respuesta,admin_id)
VALUES (1,'preguntax','respuestax',1);

INSERT INTO modelo_zapato (id, color, costo, preciounitario, descuento, nombre, descripcion, detalle, material,
                           imagenurl, tipo)
VALUES (1, 'azul', 8000, 10000, 50, 'cielo', 'xxxxxxxxx', 'yyyyyy', 'tela', 'ejemplo.com', 'bota'),
       (2, 'verde', 15000, 20000, 0, 'macielo', 'xxxxxxxxx', 'yyyyyy', 'cañamo', 'ejemplo.com', 'tenis'),
       (3, 'marron', 8000, 10000, 50, 'chocolate', 'xxxxxxxxx', 'yyyyyy', 'tela', 'ejemplo.com', 'bota'),
       (4, 'azul', 8000, 10000, 50, 'hielolate', 'xxxxxxxxx', 'yyyyyy', 'tela', 'ejemplo.com', 'bota');
