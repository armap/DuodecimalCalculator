# DuodecimalCalculator
Android app: Duodecimal Calculator - Ranking read from JSON

La app sirve para sumar en base 12.
Calcular en base 12 es lo mismo que en base 10, solo que tenemos dos símbolos adicionales que representan el 10 y el 11, que por convenio suelen ser los caracteres griegos chi (χ) y épsilon (ε) respectivamente. Por ejemplo:

|   Base 10 | Base 12   |
|----------:|-----------|
| 6+3 = 9   | 6+3 = 9   |
| 6+5 = 11  | 6+5 = ε   |
| 6+6 = 12  | 6+6 = 10  |
| 9+10 = 19 | 9+ χ = 17 |
| 5+18 = 23 | 5+16 = 1ε |

La app contiene 3 pantallas:

1. Easy: Hace sumas de números de 1 solo dígito. El usuario puede mostrar u ocultar la respuesta (para hacer cálculo mental).

2. Hard: Hace sumas de números de 7 dígitos que se representan en una tabla de sumas. El usuario puede pulsar las casillas correspondientes a cada uno de los dígitos del resultado de la suma, entonces aparece el teclado en Base12. Al pulsar sobre una tecla del teclado, si el dígito es correcto (coincide con la solución) se oculta otra vez y queda el número registrado en la casilla correspondiente.

3. Ránking: Carga el ránking de usuarios desde un fichero json (simulando el acceso a un servidor).
