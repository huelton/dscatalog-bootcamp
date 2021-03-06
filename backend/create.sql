create table tb_category (id  bigserial not null, created_at TIMESTAMP WITHOUT TIME ZONE, name varchar(255), updated_at TIMESTAMP WITHOUT TIME ZONE, primary key (id))
create table tb_product (id  bigserial not null, date TIMESTAMP WITHOUT TIME ZONE, description TEXT, img_url varchar(255), name varchar(255), price float8, primary key (id))
create table tb_product_category (product_id int8 not null, category_id int8 not null, primary key (product_id, category_id))
alter table tb_category add constraint UK_4gpl7afmaxiecnv7fv7unn2mp unique (name)
alter table tb_product add constraint UK_lovy3681ry0dl5ox28r6679x6 unique (name)
alter table tb_product_category add constraint FK5r4sbavb4nkd9xpl0f095qs2a foreign key (category_id) references tb_category
alter table tb_product_category add constraint FKgbof0jclmaf8wn2alsoexxq3u foreign key (product_id) references tb_product
