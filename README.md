# PYTHON_IN_COMPOSE
En este repositorio vamos a ver lo que eh podido lograr hacer con la librería Chaquopy,
para poder implementar python en android, usamos Jetpack Compose en esta versión.
- ### <h3>Agregué esto en mi archivo build.gradle.kts (project) </h3>

      id("com.chaquo.python") version "14.0.2" apply false

- ### <h3>Agregué esto en mi archivo build.gradle.kts (app) </h3>
      plugins {
        id("com.chaquo.python")
      }
      defaultConfig {
          ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
         }

- ### <h3>A Tener en Cuenta</h3>
En esta primera versión que estoy subiendo, todavia hay fallos, el más importante
es que no eh logrado instalar más librerías aparte de las que Chaquopy le da por
defecto, como os

- ### <h3>Objetivos Futuros:</h3>
-Lograr instalar todas las librerías que el usaruio requiera desde la misma ui
-Hacer un coloreado a las palabras clave del lenguaje, como Def, class, if
-Hacer más pantallas, y Splash Screen para mejor experiencia de usuario.
  
  
  
