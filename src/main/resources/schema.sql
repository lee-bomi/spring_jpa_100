DROP TABLE IF EXISTS NOTICE;
create table NOTICE
(
    ID BIGINT auto_increment primary key,
    TITLE varchar(255),
    CONTENTS varchar(255),

    HITS INTEGER,
    LIKES INTEGER,
    REG_DATE TIMESTAMP,
    UDT_DATE TIMESTAMP
)