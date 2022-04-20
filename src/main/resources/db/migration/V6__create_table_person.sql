create table person(
    id bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name text not null,
    address text not null,
    phone text not null,
    whatsapp boolean not null,
    cpf text not null,
    user_id bigint not null,
    createdAt timestamp with time zone,
    updatedAt timestamp with time zone,
    foreign key (user_id) references users(id)
);