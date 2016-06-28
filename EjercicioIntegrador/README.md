# Ejercicio Integrador

Este ejercicio propone una integración de conocimientos del alumno, creando una aplicación desde cero con los conocimientos obtenidos hasta ahora.

La aplicación constará de dos pantallas: Filtro de usuario y lista de tareas asignadas por usuario. Los requisitos se escribirán en formato: Historia de Usuario, dando a conocer los criterios de aceptación, notas técnicas y pantalla a desarrollar si fuese necesario.

Conocimientos especificos del lenguaje Java:

- Manejo de listas y mapas: List / ArrayList / Map / HashMap.
- Tipos primitivos de datos, validación de los mismos.
- Clases.

Conocimientos especificos del framework Android:

- Manejo de actividades: agregar nueva actividad, lanzar una actividad, enviar parámetros a una nueva actividad.
- Manejo de layouts: decidir la estructura de una pantalla, conexión de eventos sobre una pantalla y la actividad asociada.

## HU1 - Pantalla de filtro de usuario.

El usuario al iniciar la aplicación debe poder filtrar por nombre el usuario para asi poder ver las tareas asignadas.

El usuario al entrar en la aplicación visualiza:

- Un campo de texto editable.
- Un botón para ejecutar la búsqueda con texto: "Buscar".

Criterios de aceptación:

- Si al ejecutar la búsqueda no hay texto ingresado, mostrar el mensaje de error: "Debe ingresar Texto para buscar".
- Si al ejecutar la búsqueda, no se encuentra el nombre de usuario, mostrar mensaje de error: "No se ha encontrado el usuario con nombre: {texto ingresado}".

Notas Técnicas:

- Los mensajes de error se mostrarán en un Toast.
- La lista de los usuarios se encuentran en un List<String> puestos en el codigo "hardcodeados".

Mockup: 

{link mockup}

## HU2 - Pantalla de Lista de Tareas por usuario.

El usuario al encontrar un usuario válido, debe poder ver la lista de tareas asociadas.

El usuario luego de presionar en el botón "buscar" es redirigido a una pantalla con una lista de tareas para el usuario. En la pantalla se puede visualizar:

- "Nombre de usuario: {nombre}".
- Lista de tareas.

Criterios de aceptación:

Notas Técnicas:

- Se espera que el parametro "usuario" se pase mediante un Bundle.
- La lista de tareas debe estar hardcodeada. Puede optarse por usar una lista por cada usuario en distintas variables, o directamente un Mapa.
