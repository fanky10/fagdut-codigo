# Ejercicio Integrador

Este ejercicio propone una integración de conocimientos del alumno, creando una aplicación desde cero con los conocimientos obtenidos hasta ahora.

La aplicación constará de dos pantallas: Filtro de usuario y lista de tareas asignadas por usuario. Los requisitos se escribirán en formato: Historia de Usuario, dando a conocer los criterios de aceptación, notas técnicas y pantalla a desarrollar si fuese necesario.

## HU1 - Pantalla de filtro de usuario.

El usuario al iniciar la aplicación debe poder filtrar por nombre el usuario para asi poder ver las tareas asignadas.

El usuario al entrar en la aplicación ve un campo de texto editable y un botón para ejecutar la búsqueda.

Criterios de aceptación:

- Si al ejecutar la búsqueda no hay texto ingresado, mostrar el mensaje de error: "Debe ingresar Texto para buscar"
- Si al ejecutar la búsqueda, no se encuentra el nombre de usuario, mostrar mensaje de error: "No se ha encontrado el usuario con nombre: {texto ingresado}"

Notas Técnicas:

- Los mensajes de error se mostrarán en un Toast
- La lista de los usuarios se encuentran en un List<String> puestos en el codigo "hardcodeados".

Mockup: 

{link mockup}
