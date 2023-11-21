
-- Create main table: user
create table if not exists `user` (
    id bigint primary key,
    name varchar(255),
    age int,
    create_date date
);
