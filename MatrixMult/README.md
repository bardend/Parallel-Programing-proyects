# Multiplicación de Matrices en Paralelo

Este proyecto implementa diferentes algoritmos para la multiplicación de matrices en paralelo utilizando hilos (threads) en Java. El objetivo principal es aprovechar el poder de procesamiento paralelo para acelerar el cálculo de la multiplicación de matrices.

## Descripción

El proyecto contiene tres métodos principales:

1. `parallelnaiveMatrixMultiply`: Implementa un enfoque ingenuo para multiplicar matrices en paralelo, creando un hilo para cada elemento del resultado.

2. `parallelchunkMatrixMultiply2`: Divide las matrices en bloques (chunks) y asigna hilos para procesar cada bloque de manera paralela.

3. `parallelchunkMatrixMultiply`: Similar al método anterior, pero con una implementación ligeramente diferente para la asignación de hilos.

Estos métodos toman como entrada dos matrices y un número de tareas (tasks) que determina la cantidad de hilos a utilizar. El resultado de la multiplicación se almacena en una tercera matriz.

## Carpeta de pruebas

El proyecto incluye una carpeta `test` donde se miden los tiempos de ejecución y el speedup (aceleración) de los diferentes algoritmos. Estas pruebas permiten comparar el rendimiento de los métodos paralelos con respecto a una implementación secuencial.
