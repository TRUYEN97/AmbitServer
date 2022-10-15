create database ProgramManagement;
use ProgramManagement;
create table Location(
	Location_ID int not null auto_increment,
    Product varchar(10) not null,
    Station varchar(10) not null,
    Line varchar(10) not null,
    CreateTime datetime not null default CURRENT_TIMESTAMP, 
    primary key(Location_ID, Product, Station, Line)
);

create table Pc(
    PcName varchar(20) not null,
    Location_ID int not null,
    IsOnline bit not null default 0,
    PcOS varchar(20),
    PcInfo json,
    updateTime datetime default CURRENT_TIMESTAMP, 
    CreateTime datetime default CURRENT_TIMESTAMP,
    primary key (PcName),
    foreign key(Location_ID) references Location(Location_ID)
);

create table Program(
	Program_ID int auto_increment not null,
    programName varchar(20) not null,
    ProgramVersion varchar(10) not null,
    User_ID int,
    UpTime datetime not null,
    ProgramPath varchar(200) not null,
    primary key(Program_ID, programName)
);

CREATE TABLE BackageProgram(
	Location_ID int not null,
    Program_ID int not null,
    foreign key(Location_ID) references Location(Location_ID),
    foreign key(Program_ID) references Program(Program_ID)
);
