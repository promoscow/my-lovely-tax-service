create table inspections
(
    id                bigint auto_increment
        primary key,
    active            int           null,
    created           datetime      not null,
    updated           datetime      null,
    company_name      varchar(1000) null,
    official_location varchar(1000) null,
    ogrn              bigint        null,
    inn               bigint        null,
    inspection_target varchar(4000) null,
    other_reasons     varchar(1000) null,
    start_date        date          null,
    limitation_days   int           null,
    limitation_hours  int           null,
    format            varchar(255)  null,
    note              varchar(4000) null
)
