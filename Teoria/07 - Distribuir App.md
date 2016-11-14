# Distribuir la aplicación

## Firmando la aplicación:
Se puede utilizar Android Studio para manualmente generar APK's firmadas.
En la bar de menu ir a: Build > Generate Signed APK.
Seleccionar el modulo de aplicación:

1 - Crear un Keystore

Key store path: Donde se creará el archivo llave.
Password: Crear y confirmar password para el keystore.

Key
- Alias: Un nombre para la llave
- Password: Crear y confirmar un password seguro para la llave. Este debe ser diferente al password del archivo keystore.
- Validity (years): Es la longitud en años que la llave debe ser valida. Esta longitud debe ser al menos de 25 años, de esta forma, se puden firmar las actualizaciones con la misma llave para el largo de toda la vida la app.
Certificate: Ingresar información sobre vos para el certificado. Esta informacion no esta mostrada en la aplicación, pero es incluido en el certificado como parte del APK.

Imagen:
