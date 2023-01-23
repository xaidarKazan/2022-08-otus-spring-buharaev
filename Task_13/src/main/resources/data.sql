insert into USER (id, login, password) values (1, 'aidar', '$2a$12$3Lk09dum3fQp5CAt3PYPK.TOGCIv2Kj3C.4oX.90Whn.ebD3hqylC');
insert into USER (id, login, password) values (2, 'ruslan', '$2a$12$W05QIa90v1.KR8A/h/kiluajy4bhzbJXxz.IB5rupNTOHxqVca3O.');
insert into USER (id, login, password) values (3, 'admin', '$2a$12$USRANxacKgzO.o24DBK9ke3zZj54/s8Gv1GpxJTuRjTu6cjgMbA2i');

insert into ROLE (id, name) values (1, 'ROLE_USER');
insert into ROLE (id, name) values (2, 'ROLE_MANAGER');
insert into ROLE (id, name) values (3, 'ROLE_ADMIN');

insert into USER_ROLE (user_id, role_id) values (1, 1);
insert into USER_ROLE (user_id, role_id) values (2, 2);
insert into USER_ROLE (user_id, role_id) values (3, 3);

insert into AUTHOR (id, firstName, lastName) values (1, 'Михаил', 'Булгаков');
insert into AUTHOR (id, firstName, lastName) values (2, 'Федор', 'Достоевский');
insert into AUTHOR (id, firstName, lastName) values (3, 'Антон', 'Чехов');
insert into AUTHOR (id, firstName, lastName) values (4, 'Николай', 'Гоголь');
insert into AUTHOR (id, firstName, lastName) values (5, 'Александр', 'Пушкин');
insert into AUTHOR (id, firstName, lastName) values (6, 'Иван', 'Тургенев');

insert into GENRE (id, name) values (1, 'Проза');
insert into GENRE (id, name) values (2, 'Драматургия');
insert into GENRE (id, name) values (3, 'Ужасы');

insert into BOOK(id, NAME, publishingYear, author_id, genre_id)
    values(1, 'Мастер и Маргарита', 1928, 1, 1);
insert into BOOK(id, NAME, publishingYear, author_id, genre_id)
    values(2, 'Преступление и наказание', 1866, 2, 1);
insert into BOOK(id, NAME, publishingYear, author_id, genre_id)
    values(3, 'Вишневый сад', 1903, 3, 1);
insert into BOOK(id, NAME, publishingYear, author_id, genre_id)
    values(4, 'Собачье сердце', 1925, 1, 1);
insert into BOOK(id, NAME, publishingYear, author_id, genre_id)
    values(5, 'Мертвые души', 1835, 4, 2);
insert into BOOK(id, NAME, publishingYear, author_id, genre_id)
    values(6, 'Братья Карамазовы', 1879, 2, 2);
insert into BOOK(id, NAME, publishingYear, author_id, genre_id)
    values(7, 'Пиковая дама', 1833, 5, 3);
insert into BOOK(id, NAME, publishingYear, author_id, genre_id)
    values(8, 'Бежин луг', 1851, 6, 3);
insert into BOOK(id, NAME, publishingYear, author_id, genre_id)
    values(9, 'Вий', 1835, 4, 3);