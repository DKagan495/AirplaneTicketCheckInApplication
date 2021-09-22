CREATE TABLE users
(
    id bigserial primary key not null,
    first_name varchar not null,
    last_name varchar not null,
    date_of_birth date not null,
    email varchar not null,
    password varchar not null,
    role varchar not null
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
    flight_date date not null,
    places_left int not null
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
)

ALTER TABLE roles
    ADD FOREIGN KEY (role)
        REFERENCES users (role)
    NOT VALID;


ALTER TABLE users
    ADD FOREIGN KEY (id)
        REFERENCES tickets (user_id)
    NOT VALID;


ALTER TABLE airports
    ADD FOREIGN KEY (id)
        REFERENCES flights (airport_from_id)
    NOT VALID;


ALTER TABLE airports
    ADD FOREIGN KEY (id)
        REFERENCES flights (airport_to_id)
    NOT VALID;