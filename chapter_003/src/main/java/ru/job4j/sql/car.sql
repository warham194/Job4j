create table body (
	id serial primary key,
	name varchar(100)
);

create table engine (
	id serial primary key,
	name varchar(100)
);

create table transmission (
	id serial primary key,
	name varchar(100)
);

create table car (
	id serial primary key,
	name varchar(100),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(name) values
('gazelle'), ('mini'), ('trucker');

insert into engine(name) values
('Electrical engine'), ('six-cylinder engine'), ('internal combustion engine');

insert into transmission(name) values
('new transmission'), ('transmission from China'), ('transmission from Zhiguli');


insert into car(name, body_id, engine_id, transmission_id) values
	('volvo', 1, 3, 3), ('tesla', 2, 1, 1);

select * from car as c
    left outer join body as b on c.body_id = b.id
	left outer join engine as e on c.engine_id = e.id
	left outer join transmission as t on c.transmission_id = t.id;


	select b.name from body as b
    	left outer join car as c on c.body_id = b.id where c.car_body_id is null;

    select e.name from engine as e left outer join car as c
    	on c.engine_id = e.id where c.engine_id is null;

    select t.name from transmission as t left outer join car as c
    	on c.transmission_id = t.id where c.transmission_id is null;