create table users
(
	id serial primary key,
	name varchar(31),
	lastname varchar(31),
	login varchar(31) unique,
	password varchar
)