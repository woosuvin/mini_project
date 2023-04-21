/*
no: 회원 번호. number(6). primary key
name: 회원 이름. varchar2(100 char). not null
id: 회원 아이디. varchar2(100 char). not null
password: 회원 비밀번호. varchar2(100 char). not null
email: 회원 이메일. varchar2(100 char). not null
*/




create table userjoin (
    no          number(6) constraint userjoin_no_pk primary key,
    name        varchar2(100 char) constraint userjoin_name_nn not null,
    id          varchar2(100 char) constraint userjoin_id_uq unique,
    password    varchar2(100 char) constraint userjoin_password_nn not null,
    email       varchar2(100 char) constraint userjoin_email_uq unique
);

insert into userjoin (name, id, password, email)
values ('우수빈', 'test', 'test', 'test');

select * from userjoin;

insert into userjoin (name, id, password, email)
values ('aaa', 'test2', 'test2', 'test2');

select * from userjoin;

commit;

alter table userjoin add constraint userjoin_id_nn check(id is not null);
alter table userjoin add constraint userjoin_email_nn check(email is not null);
commit;