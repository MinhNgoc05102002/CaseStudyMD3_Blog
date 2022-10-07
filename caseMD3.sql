drop database case3
create database Case3;
use Case3;

create table Account (
	accountID 	int	not null auto_increment,
	username 	varchar(20) not null,
    email 		varchar(20) not null,
    fullname	varchar(50),
    password	varchar(20),
    phoneNumber	varchar(20),
    address		varchar(50),
	role 		bit,
    status 		bit,
    constraint  primary key (accountID, username, email)
);

create table Blog (
	blogID 		int	not null auto_increment,
    title		varchar(50),
    content 	longtext,
    status		bit,
    createAt	datetime,
    accountID 	int,
    constraint	primary key (blogID),
    constraint  fk_blog_author foreign key (accountID) references Account(accountID)
);

create table Category(
	categoryID	int not null auto_increment,
    name		varchar(20),
    description	longtext,
    constraint	primary key (categoryID)
);

create table Category_Blog(
	categoryID	int not null,
    blogID 		int	not null,
    constraint  fk_blog foreign key (blogID) references Blog(blogID),
    constraint  fk_category foreign key (categoryID) references Category(categoryID)
);

