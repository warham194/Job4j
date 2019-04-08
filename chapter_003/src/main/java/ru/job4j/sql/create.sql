create database requests;

create table roles (
	id serial primary key,
	roles_name varchar(100)
);
create table rules (
	id serial primary key,
	rules_name varchar(100)
);
create table rulesForRoles (
	roles_id integer references roles(id),
	rules_id integer references rules(id)
);
create table users (
	id serial primary key,
	users_name varchar(100),
	roles_id integer references roles(id)
);
create table category (
	id serial primary key,
	category_name varchar(100)
);
create table states (
	id serial primary key,
	states_name varchar(100)
);
create table items (
	id serial primary key,
	items_name varchar(100),
	users_id integer references users(id),
	category_id integer references category(id),
	states_id integer references states(id)
);
create table files (
	id serial primary key,
	files_name varchar(50),
	items_id integer references items(id)
);
create table commentaries (
	id serial primary key,
	commentaries_name  varchar(100),
	items_id integer references items(id)
);

insert into rules (id, roles_name) values
	(1, 'add'),(2, 'change_item'), (3, 'change_status'),
	(4, 'add_comment'), (5, 'add_files'),(6, 'delete');
insert into roles (id, name_roles) values
	(1, 'admin'),(2, 'user');
insert into rulesForRoles (roles_id, rules_id) values
	(1, 1), (1, 2), (1, 3), (1, 4),
	(1, 5), (1, 6), (2, 1),(2, 2),
	(2, 3), (2, 5), (2, 6);