create table post(
    id bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title text not null,
    message text not null,
    person_id bigint not null,
    status text not null,
    createdAt timestamp with time zone,
    updatedAt timestamp with time zone,
    foreign key (person_id) references person(id)
);