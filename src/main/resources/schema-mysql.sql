drop table if exists my_order_item_table;
drop table if exists my_order_address_table;
drop table if exists my_order_table;

drop table if exists my_customer_table;
drop table if exists my_product_table;

create table my_customer_table (
	id int not null primary key auto_increment,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(100) not null,
	date_of_birth date
);

create table my_order_table (
	ref int not null primary key auto_increment,
	customer_id int not null,
	place_date datetime not null,
	status char(2) not null,
	created_by varchar(50),
	created_ts timestamp,
	last_upd_by varchar(50),
	last_upd_ts timestamp,
	foreign key fk_order_to_customer(customer_id) references my_customer_table(id)
	on update cascade on delete restrict
);

create table my_order_address_table (
	addr_id int not null primary key auto_increment,
	order_ref int not null,
	addr_line1 varchar(200),
	addr_line2 varchar(200),
	addr_line3 varchar(200),
	addr_line4 varchar(200),
	city_code char(2),
	country_code char(3),
	foreign key fk_address_to_order(order_ref) references my_order_table(ref)
	on update cascade on delete restrict	
);

create table my_product_table (
	id int not null auto_increment,
	short_desc varchar(500),
	unit_price decimal(17,2),
	primary key (id)
);

create table my_order_item_table (
	order_ref int not null,
	item_no int not null,
	product_id int not null,
	unit_price decimal(12,2) not null,
	qty int not null,
	primary key(order_ref, product_id),
	foreign key fk_item_to_order(order_ref) references my_order_table(ref)
	on update cascade on delete restrict,
	foreign key fk_item_to_product(product_id) references my_product_table(id)
	on update cascade on delete restrict
);


