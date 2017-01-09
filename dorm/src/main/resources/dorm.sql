create table room
(
  room_id serial,
  room_number varchar(255) not null,
  capacity decimal(1,0) not null,
  primary key (room_id)
);

create table student
(
  student_id serial,
  first_name varchar(255) not null,
  surname varchar(255) not null,
  patronymic varchar(255) not null,
  date_of_birth date not null,
  sex char(1),
  room_id int not null,
  education_year int not null,
  primary key (student_id)
);

insert into room(room_number, capacity) values ('10', 2);
insert into room(room_number, capacity) values ('11', 3);
insert into room(room_number, capacity) values ('12', 2);
insert into room(room_number, capacity) values ('13', 3);
insert into room(room_number, capacity) values ('14', 2);
insert into room(room_number, capacity) values ('15', 3);
insert into room(room_number, capacity) values ('16', 3);
insert into room(room_number, capacity) values ('17', 2);
insert into room(room_number, capacity) values ('18', 3);
insert into room(room_number, capacity) values ('19', 2);
insert into room(room_number, capacity) values ('20', 3);
insert into room(room_number, capacity) values ('21', 2);
insert into room(room_number, capacity) values ('22', 3);
insert into room(room_number, capacity) values ('23', 2);
insert into room(room_number, capacity) values ('24', 3);
insert into room(room_number, capacity) values ('25', 2);
insert into room(room_number, capacity) values ('27', 3);
insert into room(room_number, capacity) values ('28', 2);
insert into room(room_number, capacity) values ('29', 3);
insert into room(room_number, capacity) values ('30', 2);
insert into room(room_number, capacity) values ('31', 3);
insert into room(room_number, capacity) values ('32', 2);
insert into room(room_number, capacity) values ('33', 3);
insert into room(room_number, capacity) values ('34', 2);
insert into room(room_number, capacity) values ('35', 3);
insert into room(room_number, capacity) values ('36', 2);
insert into room(room_number, capacity) values ('37', 3);
insert into room(room_number, capacity) values ('38', 2);
insert into room(room_number, capacity) values ('39', 3);
insert into room(room_number, capacity) values ('40', 2);
insert into room(room_number, capacity) values ('41', 3);
insert into room(room_number, capacity) values ('42', 2);
insert into room(room_number, capacity) values ('43', 3);
insert into room(room_number, capacity) values ('44', 2);
insert into room(room_number, capacity) values ('45', 3);
insert into room(room_number, capacity) values ('46', 2);
insert into room(room_number, capacity) values ('47', 3);
insert into room(room_number, capacity) values ('48', 2);
insert into room(room_number, capacity) values ('49', 3);
insert into room(room_number, capacity) values ('50', 2);
insert into room(room_number, capacity) values ('51', 3);
insert into room(room_number, capacity) values ('52', 3);
insert into room(room_number, capacity) values ('53', 3);
insert into room(room_number, capacity) values ('54', 3);
insert into room(room_number, capacity) values ('55', 3);
insert into room(room_number, capacity) values ('56', 3);
insert into room(room_number, capacity) values ('57', 3);
insert into room(room_number, capacity) values ('58', 3);
insert into room(room_number, capacity) values ('59', 3);

insert into student(first_name, patronymic, surname, sex, date_of_birth, room_id, education_year)
values ('Иван', 'Сергеевич', 'Степанов', 'М', '1993-03-20', 1, 2016);

insert into student(first_name, patronymic, surname, sex, date_of_birth, room_id, education_year)
values ('Наталья', 'Андреевна', 'Чичикова', 'Ж', '1993-06-10', 1, 2016);

insert into student(first_name, patronymic, surname, sex, date_of_birth, room_id, education_year)
values ('Виктор', 'Сидорович', 'Белов', 'М', '1993-01-10', 2, 2016);

insert into student(first_name, patronymic, surname, sex, date_of_birth, room_id, education_year)
values ('Петр', 'Викторович', 'Сушкин', 'М', '1993-03-12', 2, 2016);

insert into student(first_name, patronymic, surname, sex, date_of_birth, room_id, education_year)
values ('Вероника', 'Сергеевна', 'Ковалева', 'Ж', '1991-07-19', 2, 2016);

insert into student(first_name, patronymic, surname, sex, date_of_birth, room_id, education_year)
values ('Ирина', 'Федоровна', 'Истомина', 'Ж', '1991-04-29', 3, 2016);

insert into student(first_name, patronymic, surname, sex, date_of_birth, room_id, education_year)
values ('Виталия', 'Андреевна', 'Нечипоренко', 'Ж', '1993-07-11', 33, 2016);
