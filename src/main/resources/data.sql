INSERT INTO usuario (id,nombres,apellidos,username,contrasena,correo)
VALUES (1,'nombrex','apellidox','usernamex','1234','example@correo.com');

INSERT INTO admin (id, usuario_id)
VALUES (1, 1);

INSERT INTO qa (id,pregunta,respuesta,admin_id)
VALUES (1,'preguntax','respuestax',1);
