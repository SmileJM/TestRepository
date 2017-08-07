-- 생성자 Oracle SQL Developer Data Modeler 4.1.3.901
--   위치:        2017-08-04 14:12:39 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g




CREATE TABLE board
  (
    bno               NUMBER (10) NOT NULL ,
    btitle            VARCHAR2 (200) NOT NULL ,
    bcontent          VARCHAR2 (4000) ,
    bdate             DATE ,
    bpassword         VARCHAR2 (20) NOT NULL ,
    bhitcount         NUMBER (5) ,
    blikecount        NUMBER (5) ,
    boriginalfilename VARCHAR2 (200) ,
    bsavedfilename    VARCHAR2 (200) ,
    bfilecontent      VARCHAR2 (20) ,
    bwriter           VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE board ADD CONSTRAINT board_PK PRIMARY KEY ( bno ) ;


CREATE TABLE boardcomment
  (
    bcno       NUMBER (5) NOT NULL ,
    bcdate     DATE ,
    bccomment  VARCHAR2 (1000) NOT NULL ,
    bcpassword VARCHAR2 (20) NOT NULL ,
    bcwriter   VARCHAR2 (50) NOT NULL ,
    bno        NUMBER (10) NOT NULL
  ) ;
ALTER TABLE boardcomment ADD CONSTRAINT boardcomment_PK PRIMARY KEY ( bcno ) ;


CREATE TABLE likecount
  (
    memail VARCHAR2 (50) NOT NULL ,
    bno    NUMBER (10) NOT NULL
  ) ;


CREATE TABLE member
  (
    memail            VARCHAR2 (50) NOT NULL ,
    mname             VARCHAR2 (20) NOT NULL ,
    moriginalfilename VARCHAR2 (200) ,
    msavedfilename    VARCHAR2 (200) ,
    mfilecontent      VARCHAR2 (20) ,
    mlevel            NUMBER (3)
  ) ;
ALTER TABLE member ADD CONSTRAINT member_PK PRIMARY KEY ( memail ) ;


ALTER TABLE board ADD CONSTRAINT board_member_FK FOREIGN KEY ( bwriter ) REFERENCES member ( memail ) ;

ALTER TABLE boardcomment ADD CONSTRAINT boardcomment_board_FK FOREIGN KEY ( bno ) REFERENCES board ( bno ) ;

ALTER TABLE boardcomment ADD CONSTRAINT boardcomment_member_FK FOREIGN KEY ( bcwriter ) REFERENCES member ( memail ) ;

ALTER TABLE likecount ADD CONSTRAINT likecount_board_FK FOREIGN KEY ( bno ) REFERENCES board ( bno ) ;

ALTER TABLE likecount ADD CONSTRAINT likecount_member_FK FOREIGN KEY ( memail ) REFERENCES member ( memail ) ;


-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             4
-- CREATE INDEX                             0
-- ALTER TABLE                              8
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
