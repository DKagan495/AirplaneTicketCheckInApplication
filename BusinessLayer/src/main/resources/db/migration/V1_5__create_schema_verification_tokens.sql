create table verification_tokens
(
    id              bigserial       primary key         not null,
    user_id         bigint                              not null,
    token           varchar                             not null,
    expiry_date     date                                not null
)