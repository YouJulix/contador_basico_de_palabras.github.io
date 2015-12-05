# contador_basico_de_palabras.github.io
Programa básico hecho en Java que cuenta palabras y arroja el número total de palabras así como el número de repeticiones de cada palabra.

Esta aplicación permite leer de un archivo de texto plano, y realizar un conteo estadístico de palabras,
arrojando en consola y en un archivo llamado "reporte.palabras.txt" los resultados

En consola muestra:
	1) Numero de palabras totales
	2) Numero de palabras sin auxiliares
	3) Las 10 palabras más repetidas

En archivo
	La misma información que en consola
	Todas las palabras seguidas de su número de repetición.	



-TECNOLOGIAS OCUPADAS
	Linux Ubuntu 14.04 (64 bits)
	Java versión 1.8
	Eclipse

-FORMA DE USO/FORMA DE CARGAR EL PROYECTO
	Descargar repositorio de github
	Descomprimirlo, se descomprimirá una carpeta llamada "contador_basico_de_palabras.github.io-master"
	Desde cmd(Windows), o terminal(Linux) nos ubicamos dentro de dicha carpeta, hacemos lo siguiente

	//Compilar programa con:
		javac src/coutWords.java
	
	//Ejecutar programa
		java src/coutWords name_archivo.txt


	//donde "name_archivo.txt" será el nombre de nuestro archivo a procesar


-EJEMPLO DE ALGUNA CORRIDA
	
	Dentro del proyecto se incluye un archivo llamado "prueba.txt", que
	contiene lo siguiente:
		
		Hello World esta es una prueba
	

	Si abrimos un terminal y nos ubicamos en la carpeta "contador_basico_de_palabras.github.io-master" y compilamos (javac src/countWords.java),
	luego tenemos que ejecutar:
	
		java src/coutWords prueba.txt
	
	El resultado será el siguiente:

		En consola:

				 1. Total de palabras: 6
				 2. Total de palabras sin auxiliares: 3
				 3. 10 palabras más comunes: 

					------- TOP 10 ------

					Palabra				Interacion

					1)hello				 1
					2)prueba				 1
					3)world				 1



		En la carpeta se crea el archivo "reporte.palabras.txt" que contiene:
			
		
				 1. Total de palabras: 6
				 2. Total de palabras sin auxiliares: 3
				 3. 10 palabras más comunes: 

					------- TOP 10 ------

					Palabra				Interacion

					1)hello				 1
					2)prueba				 1
					3)world				 1


				 ---------------------------------------------------
					CONTEO DE TODAS LAS PALABRAS

				 Palabra			 iteracion

				 hello				 1
				 prueba				 1
				 world				 1
				

	
		

	

	









