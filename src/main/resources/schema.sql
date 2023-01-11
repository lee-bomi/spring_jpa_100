DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS NOTICE;

create table USER
(
    ID          BIGINT auto_increment primary key,
    EMAIL       VARCHAR(255),
    USER_NAME   VARCHAR(255),
    PASSWORD    VARCHAR(255),
    PHONE       VARCHAR(255),
    REG_DATE    TIMESTAMP,
    UPDATE_DATE TIMESTAMP
);

create table NOTICE
(
    ID           BIGINT auto_increment primary key,
    TITLE        varchar(255),
    CONTENTS     varchar(255),

    HITS         INTEGER,
    LIKES        INTEGER,

    REG_DATE     TIMESTAMP,
    UPDATE_DATE  TIMESTAMP,
    DELETED_DATE TIMESTAMP,
    DELETED      BOOLEAN,

    USER_ID      BIGINT,
    constraint FK_NOTICE_USER_ID foreign key (USER_ID) REFERENCES USER (ID)
);
