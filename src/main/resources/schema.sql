drop table if exists Ingredient;

create table Ingredient (
  id identity,
  slug varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);
