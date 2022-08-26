drop database if exists forum;
create database forum;
use forum;

create table user_table (
    id int unsigned not null auto_increment primary key,
    username varchar(255) not null,
	password varchar(255) not null,
    role varchar(255) not null
);

insert into user_table (username, password, role) values
('admin', 'admin', 'administrateur'),
('user1', 'user1', 'utilisateur'),
('user2', 'user2', 'utilisateur');

create table topic_table (
    id int unsigned not null auto_increment primary key,
	title varchar(255) not null,
    id_user int unsigned not null,
    topic_date datetime not null,
    constraint fk_id_user_user_table_topic_table foreign key (id_user) references user_table (id)
);

insert into topic_table (title, id_user, topic_date) values
('Titre 1', 2, '2022-08-01 14:00'),
('Titre 2', 2, '2022-08-01 15:00');

create table message_table (
    id int unsigned not null auto_increment primary key,
	id_topic int unsigned not null,
    id_user int unsigned not null,
    message text not null,
    message_date datetime not null,
    constraint fk_id_topic_topic_table_message_table foreign key (id_topic) references topic_table (id),
    constraint fk_id_user_user_table_message_table foreign key (id_user) references user_table (id)
);

insert into message_table (id_topic, id_user, message, message_date) values
(1, 2, 'ceci est un message 1', '2022-08-01 14:05'),
(1, 2, 'ceci est un message 2', '2022-08-01 14:10');