create table users(
    id bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email varchar(50),
    password varchar(50)
);





