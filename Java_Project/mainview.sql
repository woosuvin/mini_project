/*
    user 작성자 varchar2 constraint /// references userjoin (id)
    ctgr 분류: check movie, tv, book
    title 제목  varchar2(100 char) notnull
    comment 코멘트: varchar2(100 char) notnull
    createtime 최초 작성 시간 timestamp 기본값(시스템 현재 시간)
    modifiedtime 수정 시간 timestamp 기본값(시스템 현재 시간)
*/

create table mainview (
    id              varchar2(100 char) constraint mv_user_fk references USERJOIN (ID),
    ctgr            varchar2(100 char) constraint mv_ctgr_ck check(ctgr in ('movie', 'tv', 'book')),
    title           varchar2(100 char) constraint mv_title_nn not null,
    content         varchar2(100 char) constraint mv_comment_nn not null,
    createtime      timestamp default sysdate,
    modifiedtime    timestamp default sysdate
);

alter table MAIN add constraint ;

alter table contents add no number(6) constraint mv_no_pk primary key;

commit;

commit;

alter table contents drop constraint mv_ctgr_ck;
alter table contents add constraint mv_ctgr_ck check(ctgr in ('MOVIE', 'TV', 'BOOK'));
commit;

alter table contents modify content varchar2(500 char);
commit;
