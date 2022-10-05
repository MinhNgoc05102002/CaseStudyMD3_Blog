create database Case3;
use Case3;

create table Account (
	username 	varchar(20) not null,
    email 		varchar(20) not null,
    fullname	varchar(50),
    password	varchar(20),
    phoneNumber	varchar(20),
    address		varchar(50),
	role 		bit,
    status 		bit,
    constraint  primary key (username, email)
);

create table Blog (
	blogID 		int	not null auto_increment,
    title		varchar(50),
    content 	longtext,
    status		bit,
    createAt	datetime,
    username 	varchar(20),
    constraint	primary key (blogID),
    constraint  fk_blog_author foreign key (username) references Account(username)
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

