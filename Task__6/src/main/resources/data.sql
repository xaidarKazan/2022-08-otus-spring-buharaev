insert into AUTHOR (id, firstName, lastName) values (1, 'Михаил', 'Булгаков');
insert into AUTHOR (id, firstName, lastName) values (2, 'Федор', 'Достоевский');
insert into AUTHOR (id, firstName, lastName) values (3, 'Антон', 'Чехов');
insert into AUTHOR (id, firstName, lastName) values (4, 'Николай', 'Гоголь');
insert into AUTHOR (id, firstName, lastName) values (5, 'Александр', 'Пушкин');
insert into AUTHOR (id, firstName, lastName) values (6, 'Иван', 'Тургенев');

insert into GENRE (id, name) values (1, 'Проза');
insert into GENRE (id, name) values (2, 'Драматургия');
insert into GENRE (id, name) values (3, 'Ужасы');

insert into BOOK(ID, NAME, publishingYear, author_id, genre_id)
    values(1, 'Мастер и Маргарита', 1928, 1, 1);
insert into BOOK(ID, NAME, publishingYear, author_id, genre_id)
    values(2, 'Преступление и наказание', 1866, 2, 1);
insert into BOOK(ID, NAME, publishingYear, author_id, genre_id)
    values(3, 'Вишневый сад', 1903, 3, 1);
insert into BOOK(ID, NAME, publishingYear, author_id, genre_id)
    values(4, 'Собачье сердце', 1925, 1, 1);
insert into BOOK(ID, NAME, publishingYear, author_id, genre_id)
    values(5, 'Мертвые души', 1835, 4, 2);
insert into BOOK(ID, NAME, publishingYear, author_id, genre_id)
    values(6, 'Братья Карамазовы', 1879, 2, 2);
insert into BOOK(ID, NAME, publishingYear, author_id, genre_id)
    values(7, 'Пиковая дама', 1833, 5, 3);
insert into BOOK(ID, NAME, publishingYear, author_id, genre_id)
    values(8, 'Бежин луг', 1851, 6, 3);
insert into BOOK(ID, NAME, publishingYear, author_id, genre_id)
    values(9, 'Вий', 1835, 4, 3);

insert into COMMENT(ID, CONTENT, BOOK_ID) values (1, 'Интересная', 1);
insert into COMMENT(ID, CONTENT, BOOK_ID) values (2, 'Прочитаю еще раз', 1);
insert into COMMENT(ID, CONTENT, BOOK_ID) values (3, 'Советую, не пожалеете.', 1);