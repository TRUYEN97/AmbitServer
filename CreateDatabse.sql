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
    Product_ID varchar(10),
	primary key(Station_ID),
    foreign key(Product_ID) references Product(Product_ID)
);

create table Line(
	Line_ID varchar(10) primary key,
    LineName varchar(20) not null,
    Product_ID varchar(10),
    foreign key(Product_ID) references Product(Product_ID)
);

create table Pc(
	Pc_ID varchar(10),
    PcName varchar(20),
    Line_ID varchar(10),
    Station_ID varchar(10),
    primary key (Pc_ID, PcName),
    foreign key (Line_ID) references Line(Line_ID),
    foreign key (Station_ID) references Station(Station_ID)
);

create table PN(
	PN_ID varchar(10) primary key,
    NamePN varchar(20) not null,
    Product_ID varchar(10),
    foreign key(Product_ID) references Product(Product_ID)
);

