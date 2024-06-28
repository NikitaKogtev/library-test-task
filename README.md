# Library Management System

## Описание

Library Management System - это приложение для управления библиотекой, позволяющее работать с книгами, читателями и займами книг. Приложение использует Java, JPA (Java Persistence API) для взаимодействия с базой данных и RESTful веб-сервисы для работы с клиентом.

## Основные возможности

- Управление книгами:
  - Получение списка всех книг.
  - Получение информации о книге по её ID.
  - Добавление новой книги.
  - Обновление информации о существующей книге.
  - Удаление книги по её ID.

- Управление выдачей/сдачей книг:
  - Получение списка выдач/сдач.
  - Получение информации о выдаче/сдаче по его ID.
  - Добавление нового выдачи/сдачи.
  - Обновление информации о существующей выдаче/сдаче.
  - Удаление выдачи/сдачи по его ID.

- Управление читателями:
  - Получение списка всех читателей.
  - Получение информации о читателе по его ID.
  - Добавление нового читателя.
  - Обновление информации о существующем читателе.
  - Удаление читателя по его ID.

## Установка и запуск

### Требования
- Eclipse EE
- Java 21
- Maven
- Tomcat 9
- PostgreSQL
- Hibernate
- REST (jaxrx, jersey)
- Dependency Inversion (Guice)
- Logger (log4j2)

API Документация
Книги
- Получить все книги
`GET /books`

- Получить книгу по ID
`GET /books/{id}`

- Добавить книгу
`POST /books`
Content-Type: application/json
{
  "title": "Название книги",
  "author": "Автор книги",
  "publishYear": 2020
}

- Обновить книгу
`PUT /books/{id}`
Content-Type: application/json
{
  "title": "Новое название книги",
  "author": "Новый автор книги",
  "publishYear": 2021
}

- Удалить книгу

`DELETE /books/{id}`

Анологично для других читателей и выдаче/сдаче книг.

#### Проблемы
Метод контроллера в приложении Jersey не вызывается при обращении к REST-эндпоинту
При разработке приложения на Java с использованием Jersey в качестве фреймворка для RESTful API, возникла проблема с тем, что метод контроллера не вызывается при обращении к соответствующему REST-эндпоинту.

Пример проблемы:
После настройки Jersey и Guice для внедрения зависимостей в контроллеры, я создал эндпоинт /rest/books для BookController. Однако при обращении по этому адресу (http://localhost:8080/library-web-backend/rest/books) метод getAllBooks() в BookController не вызывается, несмотря на то, что сервер Tomcat успешно запускается и приложение разворачивается без ошибок.