#leader
create table account(id int(11) not null AUTO_INCREMENT,username varchar(45),amount int(11),primary key(id));
create table products(id int(11) not null, name varchar(45) default null,price int(11) default null,stock int(11) default null,primary key(id));

insert into account(username,amount) values("user1",2000);
insert into account(username,amount) values("user2",4000);
insert into account(username,amount) values("user3",1300);

insert into products(id,name,price,stock) values(101,"Product A",100,20);
insert into products(id,name,price,stock) values(102,"Product B",200,25);



CREATE TABLE Student(
   ID   INT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(20) NOT NULL,
   AGE  INT NOT NULL,
   PRIMARY KEY (ID)
);

CREATE TABLE Marks(
   SID INT NOT NULL,
   MARKS  INT NOT NULL,
   YEAR   INT NOT NULL
);










