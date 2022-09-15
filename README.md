# Empleados y jornadas

### ¿De qué se trata?:

Esta es una aplicación web diseñada para el manejo de empleados junto con sus jornadas laborales. La misma permite cargar, modificar, eliminar y obtener tanto empleados como jornadas laborales, cada uno con sus respectivos campos. Además, los empleados gozan de otras funcionalidades como asignar una jornada existente a un empleado, asignar una jornada no existente a un empleado, modificar una jornada existente en un empleado, desvincular una jornada de un empleado y eliminar una jornada que está dentro de un empleado.


### Aclaraciones:

* Este programa da libertad a la hora de crear jornadas y asignarlas a los empleados de 2 maneras. Como es la primera vez que hago este tipo de trabajo, quise dejar estas 2 opciones aunque soy consciente de que son más líneas de código.
* Todavía faltan implementar ciertas cosas como por ejemplo dar feedback al usuario de cuando se realiza bien o mal algo. Esto se implementará en una futura actualización así que si no puede por ejemplo modificar un empleado, va a tener que dirigirse al apartado "Reglas de negocio" de aquí mismo o "Reglas de la aplicación" en la página web y fijarse qué es lo que no está cumpliendo.


## Probar la aplicación:

[Click aquí para ver la aplicación en internet.](https://ivan-benegas.github.io/Empleados-y-jornadas-pagina-web/)


## Hosts:

Esta aplicación tiene el frontend hosteado en GitHub Pages y el backend junto con la base de datos hosteados en Heroku.


## Tecnologías y/o herramientas que se utilizaron:

* Java
* Spring Boot
* Maven
* Postgres
* Heroku
* HTML
* CSS
* Typescript
* Bootstrap
* Angular
* GitHub Pages


## ¿Cómo puedo probar esta aplicación en forma local?:

#### Backend: 

1) Tener creada una base de datos en Postgres.
2) Importar la carpeta que contiene el backend en Spring Tool Suite (STS).
3) Dirigirse al archivo "application.properties".
4) Comentar la línea de código (se comenta con #):
* `spring.datasource.url: ${JDBC_DATABASE_URL_PEYJ:}`
5) Copiar y pegar:
* `spring.datasource.url=jdbc:postgresql://localhost:5000/proyecto`
* `spring.datasource.username=usuario`
* `spring.datasource.password=contraseña`
6) Reemplazar los campos con la información correcta que tiene tu base de datos.
7) Click derecho en el proyecto > Run as > Spring Boot App
8) El backend ya está listo. Se va abrir por defecto en el puerto 5000, si lo quiere cambiar lo puede hacer por otro puerto que desee.

#### Frontend: 

1) Abrir la carpeta que contiene el frontend con Visual Studio Code.
2) Dirigirse a src > environments > environment.ts
3) En la variable `backend` reemplazar el valor por el valor que se puso en `spring.datasource.url`.
4) Abrir la terminal con Control + ñ y escribir `ng s`.
5) El frontend ya está listo. Se va a abrir por defecto en el puerto 4200, si lo quiere cambiar lo puede hacer por otro puerto que desee.


## Planificación:

### Existen 2 entidades:

#### Entidad empleado que consta de:

* Un atributo de tipo Integer para el id.
* Un atributo de tipo String para el nombre.
* Un atributo de tipo String para el apellido.
* Un atributo de tipo Integer para la edad.
* Un atributo de tipo Integer para el DNI.
* Un atributo de tipo List de tipo JornadaEntity para las jornadas. Además, tiene la anotación OneToMany para permitir que un empleado tenga muchas jornadas, pero una jornada solo puede pertenecer a un empleado.

#### Entidad jornada que consta de:

* Un atributo de tipo Integer para el id.
* Un atributo de tipo Enum de tipo JornadaType para el tipo de jornada.
* Un atributo de tipo LocalDate para la fecha.
* Un atributo de tipo Integer para el horario de entrada.
* Un atributo de tipo Integer para el horario de salida.

#### Se creó un Enum de tipo JoradaType con los valores:
* TURNO_NORMAL
* TURNO_EXTRA
* DIA_LIBRE
* VACACIONES





### Reglas de negocio:

* Cada empleado no puede trabajar más de 48 horas semanales, ni menos de 30.
* Las horas de un turno normal pueden variar entre 6 y 8, y de un turno extra entre 2 y 6.
* Para cada fecha, un empleado (siempre que no esté de vacaciones o haya pedido día libre)
podrá cargar un turno normal, un turno extra o una combinación de ambos que no supere
las 12 horas.
* Por cada turno no puede haber más de 2 empleados.
* Si un empleado cargó “día libre” o "vacaciones" no podrá trabajar durante esas 24 horas.
* En la semana el empleado podrá tener hasta 2 días libres.
* Si la semana ya tiene un día libre o un día de vacaciones, la regla "cada empleado no puede trabajar más de 48 horas semanales, ni menos de 30" queda invalidada.


#### A la hora de crear un empleado se requerirá que:

* El nombre del empleado no esté vacío.
* El nombre solo pueda contener letras en mayúsculas y minúsculas y espacios en blanco.
* El apellido no esté vacío.
* El apellido solo pueda contener letras en mayúsculas y minúsculas y espacios en blanco.
* La edad no esté vacía.
* La edad solo pueda constar de números enteros.
* La edad no pueda ser menor a 18 años.
* La edad no pueda ser mayor a 100 años.
* El DNI no esté vacío.
* El DNI solo pueda constar de números enteros.
* El DNI no pueda tener menos de 8 dígitos.
* El DNI no pueda tener más de 8 dígitos.
* El DNI no pertenezca a otro empleado ya cargado.


#### A la hora de modificar un empleado se requerirá que:

* No se pueda ingresar un id vacío.
* El id que se ingresa tiene que ser de un empleado ya cargado.
* Aunque sea un valor tiene que ser distinto a los valores que ya tiene el empleado a modificar.
* El nombre del empleado no esté vacío.
* El nombre solo pueda contener letras en mayúsculas y minúsculas y espacios en blanco.
* El apellido no esté vacío.
* El apellido solo pueda contener letras en mayúsculas y minúsculas y espacios en blanco.
* La edad no esté vacía.
* La edad solo pueda constar de números enteros.
* La edad no pueda ser menor a 18 años.
* La edad no pueda ser mayor a 100 años.
* El DNI no esté vacío.
* El DNI solo pueda constar de números enteros.
* El DNI no pueda tener menos de 8 dígitos.
* El DNI no pueda tener más de 8 dígitos.
* El DNI no pertenezca a otro empleado ya cargado salvo que sea del empleado que se quiere modificar.


#### A la hora de eliminar un empleado se requerirá que:

* No se pueda ingresar un id vacío.
* Se ingrese el id de un empleado ya cargado.


#### A la hora de obtener un empleado se requerirá que:

* No se pueda ingresar un id vacío.
* Se ingrese el id de un empleado ya cargado.


#### A la hora de obtener todos los empleados no se requiere nada.





#### A la hora de crear una jornada se requerirá que:

* El tipo de la jornada no esté vacío.
* El tipo de jornada ingresado sea uno válido. Los valores correctos son: "TURNO_NORMAL", "TURNO_EXTRA", "DIA_LIBRE" y "VACACIONES".
* La fecha de la jornada no esté vacía.
* La fecha ingresada sea de formato válido. El mismo es día/mes/año.
* El día de la fecha tiene que ser un número entero del 01 al 31.
* El mes de la fecha tiene que ser un número entero del 01 al 12.
* El año de la fecha tiene que ser un número entero del 1900 al 2030.
* El día ingresado no puede ser un sábado o domingo.
* El horario de entrada de la jornada no esté vacía.
* El horario de entrada tiene que ser un número entero del 00 al 23.
* El horario de salida de la jornada no esté vacía.
* El horario de salida tiene que ser un número entero del 00 al 23.
* El horario de entrada no puede ser después que el horario de salida.
* Si el tipo de jornada ingresado es un "turno normal", la cantidad de horas trabajas tiene que ser entre 6 y 8.
* Si el tipo de jornada ingresado es un "turno extra", la cantidad de horas trabajas tiene que ser entre 2 y 6.


#### A la hora de modificar una jornada se requerirá que:

* No se pueda ingresar un id vacío.
* El id que se ingresa tiene que ser de una jornada ya cargada.
* Aunque sea un valor tiene que ser distinto a los valores que ya tiene la jornada a modificar.
* La jornada a modificar no debe pertenecer a ningún empleado.
* El tipo de la jornada no esté vacío.
* El tipo de jornada ingresado sea uno válido. Los valores correctos son: "TURNO_NORMAL", "TURNO_EXTRA", "DIA_LIBRE" y "VACACIONES".
* La fecha de la jornada no esté vacía.
* La fecha ingresada sea de formato válido. El mismo es día/mes/año.
* El día de la fecha tiene que ser un número entero del 01 al 31.
* El mes de la fecha tiene que ser un número entero del 01 al 12.
* El año de la fecha tiene que ser un número entero del 1900 al 2030.
* El día ingresado no puede ser un sábado o domingo.
* El horario de entrada de la jornada no esté vacía.
* El horario de entrada tiene que ser un número entero del 00 al 23.
* El horario de salida de la jornada no esté vacía.
* El horario de salida tiene que ser un número entero del 00 al 23.
* El horario de entrada no puede ser después que el horario de salida.
* Si el tipo de jornada ingresado es un "turno normal", la cantidad de horas trabajas tiene que ser entre 6 y 8.
* Si el tipo de jornada ingresado es un "turno extra", la cantidad de horas trabajas tiene que ser entre 2 y 6.


#### A la hora de eliminar una jornada se requerirá que:

* No se pueda ingresar un id vacío.
* Se ingrese el id de una jornada ya cargada.


#### A la hora de obtener una jornada se requerirá que:

* No se pueda ingresar un id vacío.
* Se ingrese el id de una jornada ya cargada.


#### A la hora de obtener todas las jornadas no se requiere nada.





#### A la hora de asignar una jornada ya existente a un empleado se requerirá que:

* No se pueda ingresar ninguno de los id requeridos vacíos.
* Se ingrese el id de un empleado ya cargado.
* Se ingrese el id de una jornada ya cargada.
* La jornada a asignar al empleado especificado no pertenezca a otro empleado.
* La jornada a asignar al empleado especificado no haya sido asignada anteriormente.
* Cada empleado no pueda trabajar más de 48 horas semanales, ni menos de 30 en el caso de que no haya días libres o vacaciones
en esa semana. Si los hay, entonces no hay límites de horas máximas ni mínimas semanales.
* Para cada fecha, un empleado (siempre que no esté de vacaciones o haya pedido día libre)
podrá cargar un turno normal, un turno extra o una combinación de ambos que no supere
las 12 horas.
* Por cada turno no puede haber más que 2 empleados. Se considerará turno a el "turno normal" y el "turno extra".
* Si un empleado cargó “Dia libre” o "Vacaciones" no podrá trabajar durante esas 24 horas.
* En la semana el empleado podrá tener hasta 2 días libres.


#### A la hora de asignar una jornada no existente a un empleado se requerirá que:

* No se pueda ingresar un id vacío.
* Se ingrese el id de un empleado ya cargado.
* Aunque sea un valor tiene que ser distinto a los valores que ya tiene la jornada del empleado.
* El tipo de la jornada no esté vacío.
* El tipo de jornada ingresado sea uno válido. Los valores correctos son: "TURNO_NORMAL", "TURNO_EXTRA", "DIA_LIBRE" y "VACACIONES".
* La fecha de la jornada no esté vacía.
* La fecha ingresada sea de formato válido. El mismo es día/mes/año.
* El día de la fecha tiene que ser un número entero del 01 al 31.
* El mes de la fecha tiene que ser un número entero del 01 al 12.
* El año de la fecha tiene que ser un número entero del 1900 al 2030.
* El día ingresado no puede ser un sábado o domingo.
* El horario de entrada de la jornada no esté vacía.
* El horario de entrada tiene que ser un número entero del 00 al 23.
* El horario de salida de la jornada no esté vacía.
* El horario de salida tiene que ser un número entero del 00 al 23.
* El horario de entrada no puede ser después que el horario de salida.
* Si el tipo de jornada ingresado es un "turno normal", la cantidad de horas trabajas tiene que ser entre 6 y 8.
* Si el tipo de jornada ingresado es un "turno extra", la cantidad de horas trabajas tiene que ser entre 2 y 6.
* Cada empleado no pueda trabajar más de 48 horas semanales, ni menos de 30 en el caso de que no haya días libres o vacaciones
en esa semana. Si los hay, entonces no hay límites de horas máximas ni mínimas semanales.
* Para cada fecha, un empleado (siempre que no esté de vacaciones o haya pedido día libre)
podrá cargar un turno normal, un turno extra o una combinación de ambos que no supere
las 12 horas.
* Por cada turno no puede haber más que 2 empleados. Se considerará turno a el "turno normal" y el "turno extra".
* Si un empleado cargó “Dia libre” o "Vacaciones" no podrá trabajar durante esas 24 horas.
* En la semana el empleado podrá tener hasta 2 días libres.


#### A la hora de modificar una jornada de un empleado se requerirá que:

* No se pueda ingresar ninguno de los id requeridos vacíos.
* Se ingrese el id de un empleado ya cargado.
* Se ingrese el id de una jornada ya cargada.
* La jornada a modificar del empleado especificado pertenezca al mismo.
* Aunque sea un valor tiene que ser distinto a los valores que ya tiene la jornada a modificar del empleado.
* El tipo de la jornada no esté vacío.
* El tipo de jornada ingresado sea uno válido. Los valores correctos son: "TURNO_NORMAL", "TURNO_EXTRA", "DIA_LIBRE" y "VACACIONES".
* La fecha de la jornada no esté vacía.
* La fecha ingresada sea de formato válido. El mismo es día/mes/año.
* El día de la fecha tiene que ser un número entero del 01 al 31.
* El mes de la fecha tiene que ser un número entero del 01 al 12.
* El año de la fecha tiene que ser un número entero del 1900 al 2030.
* El día ingresado no puede ser un sábado o domingo.
* El horario de entrada de la jornada no esté vacía.
* El horario de entrada tiene que ser un número entero del 00 al 23.
* El horario de salida de la jornada no esté vacía.
* El horario de salida tiene que ser un número entero del 00 al 23.
* El horario de entrada no puede ser después que el horario de salida.
* Si el tipo de jornada ingresado es un "turno normal", la cantidad de horas trabajas tiene que ser entre 6 y 8.
* Si el tipo de jornada ingresado es un "turno extra", la cantidad de horas trabajas tiene que ser entre 2 y 6.
* Cada empleado no pueda trabajar más de 48 horas semanales, ni menos de 30 en el caso de que no haya días libres o vacaciones
en esa semana. Si los hay, entonces no hay límites de horas máximas ni mínimas semanales.
* Para cada fecha, un empleado (siempre que no esté de vacaciones o haya pedido día libre)
podrá cargar un turno normal, un turno extra o una combinación de ambos que no supere
las 12 horas.
* Por cada turno no puede haber más que 2 empleados. Se considerará turno a el "turno normal" y el "turno extra".
* Si un empleado cargó “Dia libre” o "Vacaciones" no podrá trabajar durante esas 24 horas.
* En la semana el empleado podrá tener hasta 2 días libres.


#### A la hora de desvincular una jornada de un empleado se requerirá que:

* No se pueda ingresar ninguno de los id requeridos vacíos.
* Se ingrese el id de un empleado ya cargado.
* Se ingrese el id de una jornada ya cargada.
* El empleado tenga la jornada que se quiere desvincular.


#### A la hora de eliminar una jornada de un empleado se requerirá que:

* No se pueda ingresar ninguno de los id requeridos vacíos.
* Se ingrese el id de un empleado ya cargado.
* Se ingrese el id de una jornada ya cargada.
* El empleado tenga la jornada que se quiere eliminar.
