# ExamenAlmundo
El principal objetivo del código es cumplir con las consignas dadas, el segundo objetivo es ser escalable.

Para resolver la relación del Dispatcher y los empleados apliqué una adaptación del patrón de diseño Observer.
Tanto Operator, Supervisor y Director son observers con con prioridad.

Cada empleado contiene un comportamiento que es atender llamadas. Este comportamiento está desacoplado de los empleados.

En este caso elegí resolver la relación de un empleado y su manera de atender llamadas con el patrón de diseño strategy.

La cantidad de llamadas atendidas está dada por el pool de Fixed Threads disponible (10) y la cantidad de empleados.

- Dar alguna solución sobre qué pasa con una llamada cuando no hay ningún empleado libre.
  - Cuando no existe ningún empleado disponible, la llamada es puesta en una cola. La cola es consultada por un Thread dentro del Distpach que asignará la call a un empleado cuando encuentre uno disponible.

- Dar alguna solución sobre qué pasa con una llamada cuando entran más de 10 llamadas concurrentes.
  - Al igual que en el caso anterior, llamadas que no pudieron ser atendidas son puestas en una cola hasta que haya un empleado disponible.
