
create table if not exists contact (
    id int unsigned not null auto_increment,
    name text,
    surname text,
    phone text,
    mail text,
    primary key(id)
);