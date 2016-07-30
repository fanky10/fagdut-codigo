# Extras

## Iniciar Android Virtual Device con proxy configurado

### 1. Encontrar el comando que ejecuta Android Studio para iniciar un nuevo AVD

* a) Iniciar la AVD requerida 

* b) Ir a la solapa inferior: RUN

* c) Copiar el comando de arranque, algo como:

```sh
C:\android\sdk\tools\emulator.exe -netdelay none -netspeed full -avd Nexus_One_API_19 
```

### 2. Ejecutar el comando en la terminal + configuración

* a) Ir a la terminal, pegar el comando

* b) Agregar el parámetro: -http-proxy http://proxy.server.fagdut:80

* c) Verificar que el comando sea algo como:


```sh
C:\android\sdk\tools\emulator.exe -netdelay none -netspeed full -avd Nexus_One_API_19 -http-proxy http://proxy.server.fagdut:80
```

### 3. Verificar que hay correcta conexión de red:

Cuando el emulador termine de iniciar, ir a la aplicación "Browser" y abrir una nueva página web. Si fue correctamente configurado, tendria que abrirla sin problemas.
