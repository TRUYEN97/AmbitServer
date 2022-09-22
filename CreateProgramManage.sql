create database ProgramManagement;
use ProgramManagement;
create table Location(
	Localtion_ID int not null auto_increment,
    Product varchar(10) not null,
    Station varchar(10) not null,
    Line varchar(10) not null,
    primary key(Localtion_ID, Product, Station, Line)
);

create table Pc(
	Pc_ID int auto_increment not null,
    PcName varchar(20) not null,
    Localtion_ID int not null,
    PcOS varchar(20),
    PcInfo json,
    primary key (Pc_ID, PcName),
    foreign key(Localtion_ID) references Location(Localtion_ID)
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

create table Pn(
	PN_ID int auto_increment,
    PnName varchar(20),
    PnCode varchar(10),
    CreateTime datetime,
    primary key(PN_ID, PnName)
);

create table LocaltionBackage(
	LBG_ID int auto_increment not null,
    LBG_NAME varchar(20),
    PN_ID int not null,
    primary key(LBG_ID, LBG_NAME),
    foreign key(PN_ID) references Pn(PN_ID)
);

CREATE TABLE BackageProgram(
	LBG_ID int not null,
    Program_ID int not null,
    foreign key(LBG_ID) references LocaltionBackage(LBG_ID),
    foreign key(Program_ID) references Program(Program_ID)
);
