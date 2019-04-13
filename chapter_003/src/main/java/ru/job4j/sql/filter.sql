--\connect requests

create table typeProduct (
	id serial primary key,
	name varchar(1000)
);
create table product (
	id serial primary key,
	name varchar(1000),
	type_id integer references typeProduct(id),
	expired_date timestamptz,
	price money
);

--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from typeProduct as t
inner join product as p on t.id = p.type_id
where t.name = 'СЫР';
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product as p
where p.name like '%мороженное%';
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
--select * from product as p
--where p.expired_date = expired_date
--4. Написать запрос, который выводит самый дорогой продукт.
--select * from product as p
--where GREATEST(p.price);
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select count(p.id)
from product p inner join typeProduct t on t.id = p.type_id
where t.name = 'СЫР';
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from typeProduct as t
inner join product as p on t.id = p.type_id
where t.name in ('СЫР', 'МОЛОКО');
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select count (product.name),
tp.name from product join typeProduct tp on product.type_id = tp.id
group by tp.name having count (product.name) < 10;
--8. Вывести все продукты и их тип.
select * from typeProduct as t
inner join product as p on t.id = p.type_id;
