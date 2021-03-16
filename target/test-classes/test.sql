create table user
(
    id              int     not null,
    USERNAME        varchar not null,
    FULLNAME        varchar not null,
    PASSWORD        varchar not null,
    CONFIRMPASSWORD int     not null
);
insert into user (id,USERNAME,FULLNAME,PASSWORD,CONFIRMPASSWORD) values(1,'DEMO1','demo1@gmail.com','demo123456')