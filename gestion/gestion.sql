create database if not exists gestion;
use gestion;

create table if not exists clientes
(
	id int unsigned primary key auto_increment,
	nombre varchar(50),
	apellidos varchar(50),
	email varchar(50) not null,
	telefono varchar(12) unique
);

create table if not exists pedidos
(
	id int unsigned primary key auto_increment,
	numero varchar(10),
	fecha datetime,
	fecha_entrega datetime,
	base_imponible float default 0,
	iva float default 0,
	id_cliente int unsigned,
	index (id_cliente),
	foreign key (id_cliente) references clientes (id)
);


create table if not exists productos
(
	id int unsigned primary key auto_increment,
	nombre varchar(50) not null unique,
	descripcion varchar(50),
	precio float default 0
);

create table if not exists detalle_pedido
(
	id int unsigned primary key auto_increment,
	id_producto int unsigned,
	index (id_producto),
	foreign key (id_producto) references productos (id),
	id_pedido int unsigned,
	index (id_pedido),
	foreign key (id_pedido) references pedidos (id),
	precio float default 0,
	unidades int default 1
);


