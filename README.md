
Посредством RestApi можно:

-  Создать счетчик с уникальным именем;
`   POST http://host:port/api/v1/counters`
`   (body: name (имя счетчика - строкой))`

-  Инкремент значения счетчика с указанным именем:
`  PUT http://host:port/api/v1/counters/increment/{{name}}`

-  Получить значения счетчика с указанным именем:
`  GET http://host:port/api/v1/counters/{{name}}`

-  Удалить счетчик с указанным именем:
`  DELETE http://host:{port}/api/v1/counters/{{name}}`

-  Получить суммарное значение всех счетчиков:
`  GET http://host:port/api/v1/counters/sum`

-  Получить уникальные имена счетчиков в виде списка:
`  GET http://host:port/api/v1/counters/names`