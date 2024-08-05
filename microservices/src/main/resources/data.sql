-- create table user_details (id integer not null , birth_date date,name varchar(255),primary key (id));
insert into user_details(id,birth_date,name) values (1,current_date(),'Hassan');
insert into user_details(id,birth_date,name) values (2,current_date(),'Ali');
insert into user_details(id,birth_date,name) values (3,current_date(),'Salah');
-- create sequence post_seq start with 1 increment by 50;
-- create sequence user_details_seq start with 1 increment by 50;
-- create table post (id integer not null,description varchar(255),user_id integer,primary key (id));
insert into post(id,description,user_id) values (20001,'I want to learn AWS',1);
insert into post(id,description,user_id) values (20003,'I want to learn DevOps',1);