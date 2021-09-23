alter table roles drop constraint roles_pkey;
alter table roles add column id bigserial primary key not null;