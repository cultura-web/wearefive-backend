
# We are Five API

API REST para el manejo de usuarios, modelos de zapatos, Q&As, materiales, procesos y stock de la aplicación We are Five


![Logo](https://i.ibb.co/jwrxrPy/logowearefive-1.png)


## Funcionalidades

- Manejo de modelos de zapatos (CRUD, listado y detallado de modelos)
- Manejo de preguntas y respuestas (CRUD y listado)
- Autenticación de usuarios en la plataforma (login y register)




## tecnologias

- Java: lenguaje 
- Spring boot: framework
- maven: gestor de dependencias
- Mysql: base de datos
- H2: base de datos
- JUnit: testing
- Mockito: testing
- Lombok: generador de código
- Jwt: Json web token





## Endpoints

- Registro de usuarios: POST /auth/register
- Login de usuarios: POST /auth/login
- Agregar modelo de zapato: POST /api/products/model
- Actualizar modelo de zapato: PUT /api/products/model/{id}
- Eliminar modelo de zapato: DELETE /api/products/model/{id}
- Listar modelos de zapatos: GET /api/products/list
- Buscar modelos de zapatos: GET /api/products/model/{nombre}
- Detallar modelo de zapato: GET /api/products/model?idModelo=[id]
- Agregar Q&A: POST /api/qa/new
- Actualizar Q&A: PUT /api/qa/edit/{id}
- Eliminar Q&A: DELETE /api/qa/delete/{id_Q&A}
- Listar Q&A: GET /api/qa/list
## Authors

- @CulturaWeb

