create database ProgramManagement;
use ProgramManagement;
create table Location(
	Localtion_ID int not null auto_increment,
    Product varchar(10) not null,
    Station varchar(10) not null,
    Line varchar(10) not null,
    CreateTime datetime not null default CURRENT_TIMESTAMP, 
    primary key(Localtion_ID, Product, Station, Line)
);

create table Pc(
	Pc_ID int auto_increment not null,
    PcName varchar(20) not null,
    Localtion_ID int not null,
    IsOnline bit not null default 0,
    CreateTime datetime default CURRENT_TIMESTAMP,
    primary key (Pc_ID, PcName),
    foreign key(Localtion_ID) references Location(Localtion_ID)
);

create table PcInformation(
	Pc_ID int not null,
    PcOS varchar(20),
    PcInfo json,
    updateTime datetime default CURRENT_TIMESTAMP, 
    primary key (Pc_ID),
    foreign key(Pc_ID) references pc(Pc_ID)
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
	Localtion_ID int not null,
    Program_ID int not null,
    foreign key(Localtion_ID) references Location(Localtion_ID),
    foreign key(Program_ID) references Program(Program_ID)
);
