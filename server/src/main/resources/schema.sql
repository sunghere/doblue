CREATE TABLE IF NOT EXISTS PROJECT (
  seq      INT PRIMARY KEY AUTO_INCREMENT,
  url      varchar(2000),
  img      varchar(2000)  NOT NULL,
  title      varchar(1000)  NOT NULL,
  content      varchar(4000)  NOT NULL,
  sdate      varchar(20)    NOT NULL,  
  edate      varchar(20)    NOT NULL,
  completed INT default 0,
  del INT default 0
);
drop table PORT;
select * from PORT

CREATE TABLE IF NOT EXISTS PTUSER (
  id VARCHAR(100) PRIMARY KEY ,
  pwd VARCHAR(2048) NOT NULL ,
  name VARCHAR(20) NOT NULL
)