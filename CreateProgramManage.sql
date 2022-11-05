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
    PcOS varchar(20),
    PcInfo json,
    updateTime datetime not null, 
    CreateTime datetime default CURRENT_TIMESTAMP,
    primary key (PcName),
    foreign key(Location_ID) references Location(Location_ID)
);

create table Project(
	Project_ID varchar(20) not null,
    ProgramVersion varchar(10) not null,
    User_ID int,
    createTime datetime not null,
    ProgramPath varchar(200) not null,
    primary key(project_ID)
);

create table Program(
	Program_ID int auto_increment not null,
    Project_ID varchar(20) not null,
    programName varchar(20) not null,
    ProgramVersion varchar(10) not null,
    User_ID int,
    UpTime datetime not null,
    foreign key(Project_ID) references Project(Project_ID),
    primary key(Program_ID)
);

create table config(
	config_ID int auto_increment not null,
    Project_ID varchar(20) not null,
    configName varchar(20) not null,
    configVersion varchar(10) not null,
    User_ID int,
    UpTime datetime not null,
    foreign key(Project_ID) references Project(Project_ID),
    primary key(config_ID)
);

CREATE TABLE packageProgram(
	pg_ID int auto_increment not null,
	PcName varchar(20) not null,
    Project_ID varchar(20) not null,
    ProgramName varchar(20) not null,
    defaultconfigName varchar(20) not null,
    configName varchar(20) not null,
    foreign key(PcName) references pc(PcName),
    foreign key(project_ID) references project(project_ID),
    primary key(pg_ID)
);


