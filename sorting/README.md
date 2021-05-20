## Sorting Summary

|           | in place? | stable? | worst | avg | best | remarks |
|----|:---:|:---:|:---:|:---:|:---:|---|
| selection   | x |   | N²/2 | N²/2 | N²/2 | N exchanges 
| insertion   | x | x | N²/2 | N²/4 | N | use for small N or partially ordered
| shell       | x |   |   ?  |  ?   | N | tight code, subquadratic
| merge       |   | x | N lg N | N lg N | N lg N | N log N guarantee, stable
| quick       | x |   | N²/2 | 2 N ln N | N lg N | N log N **probabilistic** guarantee <br/> fastest in practice
| 3-way quick | x | | N²/2 | 2 N ln N | N | improves quicksort in presence of **duplicate** keys
| BEST        | x | x | N lg N | N lg N | N lg N | to be discovered 
