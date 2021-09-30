create table users
(
    id bigserial primary key not null,
    first_name varchar not null,
    last_name varchar not null,
    date_of_birth date not null,
    email varchar not null,
    password varchar not null,
    role_id bigint
);

create table roles
(
    role varchar primary key not null
);

create table flights
(
    id bigserial primary key not null,
    airport_from_id bigint not null,
    airport_to_id bigint not null,
    flight_date date not null
);

create table airports
(
    id bigserial primary key not null,
    name varchar not null
);

create table tickets
(
    id bigserial primary key not null,
    flight_id bigint not null,
    user_id bigint not null
);
