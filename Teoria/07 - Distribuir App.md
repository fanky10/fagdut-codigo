# Distribuir la aplicación

## Firmando la aplicación en modo release:
Se puede utilizar Android Studio para manualmente generar APK's firmadas.

### 1 - En la barra de menu ir a: Build > Generate Signed APK.
### 2 - Seleccionar el modulo de aplicación: app
Le aparecerá un cartel como el siguiente:
![Generate Signed APK](res/Nueva_SignedAPK.png)
Si ya se tiene un keystore entonces saltar el paso siguiente. Sino darle click al boton: "Create New..."

### 3 - Crear un Keystore

Key store path: Donde se creará el archivo llave.
Password: Crear y confirmar password para el keystore.

Key
- Alias: Un nombre para la llave
- Password: Crear y confirmar un password seguro para la llave. Este debe ser diferente al password del archivo keystore.
- Validity (years): Es la longitud en años que la llave debe ser valida. Esta longitud debe ser al menos de 25 años, de esta forma, se puden firmar las actualizaciones con la misma llave para el largo de toda la vida la app.
Certificate: Ingresar información sobre vos para el certificado. Esta informacion no esta mostrada en la aplicación, pero es incluido en el certificado como parte del APK.

Imagen:
![Nueva Keystore](res/Nueva_Keystore.png)
