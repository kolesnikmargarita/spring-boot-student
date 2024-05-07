# StudentsWeb Application

Приложение выполняет работу с данными студентов:
1. Вывод всех студентов
2. Поиск студента по id
3. Сохранение нового студента
4. Редактирование данных о студенте

## Список эндпоинтов

http:/localhost:8080/students - список студентов
http:/localhost:8080/students/new - добавить студента
http:/localhost:8080/students/{id} - информация о студенте
http:/localhost:8080/students/{id}/update - обновить студента

## Необходимая конфигурация

hibernate.properties -> исправить username и password
при первом запуске hibernate.hbm2ddl.auto=create
при повторном hibernate.hbm2ddl.auto=update

