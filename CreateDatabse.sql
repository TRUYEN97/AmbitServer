create database DownloadManage;
use Downloadmanage;
create table Product
(
	Product_ID varchar(10),
    ProductName varchar(10),
    
    primary key(Product_ID, ProductName)
    
);
create table Station(
	Station_ID varchar(10),
    StationName varchar(20) not null,
    Product_ID varchar(10) not null ,
	primary key(Station_ID),
    foreign key(Product_ID) references Product(Product_ID)
);

create table Line(
	Line_ID varchar(10),
    LineName varchar(20) not null,
    Product_ID varchar(10) not null ,
	primary key(Line_ID, Product_ID),
    foreign key(Product_ID) references Product(Product_ID)
);

create table PN(
	PN_ID varchar(10) ,
    NamePN varchar(20) not null,
    Product_ID varchar(10) not null,
    primary key(PN_ID, Product_ID),
    foreign key(Product_ID) references Product(Product_ID)
);

create table Pc(
	Pc_ID varchar(10),
    PcName varchar(20),
    Line_ID varchar(10),
    Station_ID varchar(10),
    primary key (Pc_ID, PcName, Station_ID),
    foreign key (Line_ID) references Line(Line_ID),
    foreign key (Station_ID) references Station(Station_ID)
);
create table STATION_PN(
	STATION_PN_ID varchar(10),
	Station_ID varchar(10) not null,
    PN_ID varchar(10) not null,
    primary key(STATION_PN_ID) ,
    foreign key(Station_ID) references Station(Station_ID),
    foreign key(PN_ID) references PN(PN_ID)
);

create table Program(
	Program_ID varchar(10),
    ProgramName varchar(30) not null,
    ProgramType bit not null, -- 1 simple program, 0 beta
    programPath text not null,
    STATION_PN_ID varchar(10) not null,
    primary key(Program_ID, ProgramName),
    foreign key(STATION_PN_ID) references STATION_PN(STATION_PN_ID)
);

create table PcInformation(
	Pc_ID varchar(10),
    PcName varchar(20) not null,
    Card text,
    primary key (Pc_ID),
    foreign key (Pc_ID) references Pc(Pc_ID)
);
 -- drop database downloadmanage;