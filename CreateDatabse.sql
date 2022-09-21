create database DownloadManage;
use Downloadmanage;
create table Product
(
	Product_ID int not null auto_increment,
    ProductName varchar(10) not null,
    primary key(Product_ID, ProductName)
    
);

create table Station(
	Station_ID int not null auto_increment,
    StationName varchar(20) not null,
    Product_ID int not null,
	primary key(Station_ID),
    foreign key(Product_ID) references Product(Product_ID)
);

create table Line(
	Line_ID int not null auto_increment,
    LineName varchar(20) not null,
    Product_ID int not null,
	primary key(Line_ID, Product_ID),
    foreign key(Product_ID) references Product(Product_ID)
);

create table Pn(
    Pn_id int not null auto_increment,
    PnName varchar(20) not null,
    PnCode varchar(20) not null,
    Product_id int not null,
    primary key (Pn_id, PnName, PnCode),
    foreign key (Product_id) references Product(Product_id)
);

create table Program(
	Program_ID int not null,
    ProgramName varchar(30) not null,
    Program_version varchar(10) not null,
    Program_path varchar(200) not null,
    Time_Up datetime not null default CURRENT_TIMESTAMP,
    Member_id int,
    Product_id int not null,
    primary key(Program_ID, ProgramName),
    foreign key (Product_id) references Product(Product_id)
);

create table Pc(
	Pc_ID int not null AUTO_INCREMENT,
    PcName varchar(20) not null,
    Line_ID int not null,
    Station_ID int not null,
    primary key (Pc_ID, PcName, Station_ID),
    foreign key (Line_ID) references Line(Line_ID),
    foreign key (Station_ID) references Station(Station_ID)
);

create table StationProgram(
	StationProgram_ID varchar(10) not null,
    Program_ID int not null,
    Pn_id int not null,
    Station_ID int not null,
    primary key(StationProgram_ID, Pn_id, Station_ID),
    foreign key(Program_ID) references Program(Program_ID),
    foreign key(Pn_id) references Pn(Pn_id),
    foreign key(Station_ID) references Station(Station_ID)
);

create table PcInformation(
	Pc_ID int not null,
    Pc_OS varchar(50),
    Card json,
    UpDateTime dateTime not null default current_timestamp,
    primary key (Pc_ID),
    foreign key (Pc_ID) references Pc(Pc_ID)
);