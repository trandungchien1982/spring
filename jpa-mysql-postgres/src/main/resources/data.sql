
drop table if exists "user";
drop table if exists "hibernate_sequence";

-- Create table for Auto Increment ID
create table hibernate_sequence ( next_val bigint );
insert into hibernate_sequence values (1);

-- Create main table: user
create table "user" (
    id bigint primary key,
    name varchar(255),
    password varchar(255),
    email varchar(255),
    description varchar(255),
    birthday date,
    active boolean,
    create_date date
);

insert into "user" values
     (1, 'Alex', 'Pwd-Alex', 'alex@test.com', 'Description for ALex', '2020-01-02', true, '2021-02-03')
    ,(2, 'John', 'Pwd-John', 'john@gmail.com', 'Description for Join', '2010-11-12', true, '2021-02-03')
    ,(3, 'Smith', 'Pwd-Smith', 'smt@test.com', 'Description for Smith', '2005-01-12', true, '2021-02-03')
    ,(4, 'Paul', 'Pwd-Paul', 'pl@test.com', 'Description for Paul', '2005-05-07', true, '2021-02-03')
    ,(5, 'Mary', 'Pwd-Mary', 'marry@test.com', 'Description for Mary', '2015-05-07', true, '2021-02-03')
    ,(6, 'Margaret', 'Pwd-Margaret', 'magrt@test.com', 'Description for Margaret', '2009-05-11', true, '2021-02-03')
    ,(7, 'James', 'Pwd-James', 'james@test.com', 'Description for James', '1990-05-11', true, '2011-02-03')
    ,(8, 'Carlos', 'Pwd-Carlos', 'cal@test.com', 'Description for Carlos', '1999-06-11', true, '2017-02-03')
    ,(9, 'Peter', 'Pwd-Peter', 'petter@test.com', 'Description for Peter', '2005-03-11', true, '2021-11-03')
    ,(10, 'James', 'Pwd-James10', 'james10@test.com', 'Description for James10', '1990-05-11', true, '2011-02-03')
    ,(11, 'James', 'Pwd-James11', 'james11@test.com', 'Description for James11', '1990-05-11', true, '2011-02-03')
    ,(15, 'James', 'Pwd-James15', 'james15@test.com', 'Description for James15', '1990-05-11', true, '2011-02-03')
    ,(17, 'Andy', 'Pwd-Andy', 'andy@test.com', 'Description for Andy', '2022-05-11', true, '2014-02-03')
    ,(22, 'Android', 'Pwd-Android', 'andr@test.com', 'Description for Android', '2021-04-11', true, '2014-02-03')
    ,(30, 'iOS', 'Pwd-iOS', 'ioss@test.com', 'Description for iOS', '2022-01-12', true, '2014-02-03')
;

