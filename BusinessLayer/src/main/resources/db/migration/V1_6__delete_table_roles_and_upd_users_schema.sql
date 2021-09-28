drop table roles;
alter table users drop column role_id;
alter table users add column role varchar not null default 'ROLE_USER';