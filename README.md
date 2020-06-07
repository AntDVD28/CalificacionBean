# CalificacionBean
Creación de un componente(bean) para la gestión de una tabla

Los java beans (o componentes) usan los eventos para generar acciones. En nuestro caso al insertar un registro en la base de datos de calificaciones generará un evento que alertará de dicha modificación.

Utilizaremos una Base de Datos Relacional. 

Incluimos el driver JDBC utilizado para MySQL.

Incluimos el archivo "alumnos.sql" con la estructura de las tablas y registros de las mismas.

JDK utilizado versión 1.8


Pasos a seguir:

1) Descarga e Instalación de XAMPP.

2) Creación de la BD "alumnos" desde la herramienta phpMyAdmin.

3) Importar la estructura y contenido de la BD desde el archivo alumnos.sql

4) Creación de un usuario en el SGBD con permisos para manipular la BD creada.

5) Creación desde Netbeans de un nuevo proyecto al que llamaremos "CalificacionBean" de tipo JavaApplication sin clase principal.

6) Agregar al proyecto un componente de tipo Componente JavaBeans, lo nombré igual que el proyecto.

7) Realizar el desarrollo del componente.

8) Generar el .jar del bean para ello hacemos uso de la herramienta de Netbeans llamada "Limpiar y Construir"

9) Creación en Netbeans de un proyecto de prueba para poder probar nuestro bean, lo llamaremos "PruebaCalifiacionBean" con clase principal.

10) Agregar a las Bibliotecas del proyecto el driver JDBC de MySQL y el .jar del bean.

11) Realizar el desarrollo de las clases que hacen uso del bean.

12) Compilar el proyecto "PruebaCalifiacionBean"



