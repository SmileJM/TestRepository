
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
    mid               VARCHAR2 (30) NOT NULL ,
    mname             VARCHAR2 (30) NOT NULL
  ) ;
ALTER TABLE board ADD CONSTRAINT board_PK PRIMARY KEY ( bno ) ;


CREATE TABLE boardcomment
  (
    bcno       NUMBER (5) NOT NULL ,
    bcdate     DATE ,
    bccomment  VARCHAR2 (1000) NOT NULL ,
    bcpassword VARCHAR2 (20) NOT NULL ,
    bno        NUMBER (10) NOT NULL ,
    mid        VARCHAR2 (30) NOT NULL ,
    bwriter    VARCHAR2 (30) NOT NULL
  ) ;
ALTER TABLE boardcomment ADD CONSTRAINT boardcomment_PK PRIMARY KEY ( bcno ) ;


CREATE TABLE hitcount
  (
    bno NUMBER (10) NOT NULL ,
    mid VARCHAR2 (30) NOT NULL
  ) ;


CREATE TABLE likecount
  (
    bno NUMBER (10) NOT NULL ,
    mid VARCHAR2 (30) NOT NULL
  ) ;


CREATE TABLE member
  (
    mid               VARCHAR2 (30) NOT NULL ,
    memail            VARCHAR2 (50) ,
    mname             VARCHAR2 (20) NOT NULL ,
    moriginalfilename VARCHAR2 (200) ,
    msavedfilename    VARCHAR2 (200) ,
    mfilecontent      VARCHAR2 (20) ,
    mlevel            NUMBER (3)
  ) ;
ALTER TABLE member ADD CONSTRAINT member_PK PRIMARY KEY ( mid ) ;


ALTER TABLE boardcomment ADD CONSTRAINT boardcomment_board_FK FOREIGN KEY ( bno ) REFERENCES board ( bno ) ;

ALTER TABLE hitcount ADD CONSTRAINT hitcount_board_FK FOREIGN KEY ( bno ) REFERENCES board ( bno ) ;

ALTER TABLE likecount ADD CONSTRAINT likecount_board_FK FOREIGN KEY ( bno ) REFERENCES board ( bno ) ;
