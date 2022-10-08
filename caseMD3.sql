drop database case3;
create database Case3;
use Case3;

create table Account (
                         accountID 	int	not null auto_increment,
                         username 	varchar(20) not null,
                         email 		varchar(50) not null,
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
                      title		longtext,
                      content 	longtext,
                      status		bit,
                      createAt	datetime,
                      accountID 	int,
                      image longtext,
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
                              constraint  fk_category foreign key (categoryID) references Category(categoryID),
                              constraint  primary key (categoryID, blogID)
);
-- Account
INSERT INTO `case3`.`account` (`username`, `email`, `fullname`, `password`, `phoneNumber`, `address`, `role`, `status`) VALUES ('Arial', 'ngoc43552@gmail.com', 'Nguyễn Minh Ngọc', 'Ngoc1988', '0399620226', 'Hà Nội', b'0', b'1');
INSERT INTO `case3`.`account` (`username`, `email`, `fullname`, `password`, `phoneNumber`, `address`, `role`, `status`) VALUES ('Syn', 'ngoc3@codegym.vn', 'Lê Văn Sỹ', 'Sys05102000', '0398762226', 'Lạng Sơn', b'1', b'1');
INSERT INTO `case3`.`account` (`username`, `email`, `fullname`, `password`, `phoneNumber`, `address`, `role`, `status`) VALUES ('Jane', 'linhninh@gmail.com', 'Tran My Linh', 'Linh2002', '0399620226', 'Hà Nội', b'0', b'1');
INSERT INTO `case3`.`account` (`username`, `email`, `fullname`, `password`, `phoneNumber`, `address`, `role`, `status`) VALUES ('Peter', 'peter@gmail.com', 'Peter Raine', 'Peter2002', '0399620226', 'Hà Nội', b'0', b'1');
INSERT INTO `case3`.`account` (`username`, `email`, `fullname`, `password`, `phoneNumber`, `address`, `role`, `status`) VALUES ('Sakura', 'Sakura@gmail.com', 'Sakura Mina', 'Sakura2002', '0399620226', 'Hà Nội', b'0', b'1');

-- Category
INSERT INTO `case3`.`category` (`categoryID`, `name`, `description`) VALUES ('1', 'Lifestyle', 'lifestyle blogs include a larger variety of topics from productivity, to wellness, workouts, nutrition, and other aspects of living a better life. ');
INSERT INTO `case3`.`category` (`categoryID`, `name`, `description`) VALUES ('2', 'Travel', 'The work of travel bloggers is hard (even though it might seem fun and easy at a first sight) because they put hours into research');
INSERT INTO `case3`.`category` (`categoryID`, `name`, `description`) VALUES ('3', 'Food', 'No one can live without eating, so it’s not a surprise that food can be found among the most popular types of blogs in the world.');
INSERT INTO `case3`.`category` (`categoryID`, `name`, `description`) VALUES ('4', 'Review', 'Focus on evaluating products or services on a specific market. It can be a niche market (e.g. camping equipment) or a broader one (e.g. technology). ');
INSERT INTO `case3`.`category` (`categoryID`, `name`, `description`) VALUES ('5', 'News', 'These blogs are the ones that keep you updated with what’s new in an industry that you follow.');
INSERT INTO `case3`.`category` (`categoryID`, `name`, `description`) VALUES ('6', 'Business', 'Over time, blogs proved to be efficient for businesses to communicate with their customers, build communities, and attract new clients.');
INSERT INTO `case3`.`category` (`categoryID`, `name`, `description`) VALUES ('7', 'Health', 'Health is something we tend to ignore when we have it.When our body is rather well, we have hardly even aware of it.');


-- Blog
INSERT INTO `case3`.`blog` (`blogID`, `title`, `content`, `status`, `createAt`, `accountID`) VALUES ('1', 'What is a list of skills that are amazing if you learn them?', 'Writing. I’ve never looked at writing as a skill. If I had started writing at a young age I feel I would have had significant advantages growing up. So, don’t be me, think, write and grow with it.', b'1', now(), '3');
INSERT INTO `case3`.`blog` (`blogID`, `title`, `content`, `status`, `createAt`, `accountID`) VALUES ('2', 'What examples would you share with a depressed person to tell him/her that their problems are really small in this world?', 'Would you tell a grieving parent who’d lost a child, “Cheer up mate, some other sod has lost three kids. By comparison, your loss is negligible.”They can no more wish it away than a Leukaemia patient can wish away their affliction.', b'1', now(), '4');
INSERT INTO `case3`.`blog` (`blogID`, `title`, `content`, `status`, `createAt`, `accountID`) VALUES ('3', 'Do people actually want to live past 60?', 'I’m a Canadian girl who was diagnosed with an incurable heart condition around the age of 60. After countless efforts to heal, the doctor gave me six months to live!', b'1', now(), '5');
INSERT INTO `case3`.`blog` (`blogID`, `title`, `content`, `status`, `createAt`, `accountID`) VALUES ('4', 'What are some examples of manipulation?', 'When the children passed an old house on their way home from school, they were always rubbing their sticks on the balcony railing and enjoying the sound. The older owner, who had been bothered by the noise for a long time, had a really good idea instead of scolding the kids.', b'1', now(), '5');
INSERT INTO `case3`.`blog` (`blogID`, `title`, `content`, `status`, `createAt`, `accountID`) VALUES ('5', 'What is one ridiculous reason for which you got kicked out of class?', '“I want everyone to write down ONE WORD - just one - that describes what they personally think of that poem.”And stuck it on the board amongst all the other words: “pretty”, “inspirational”, “thought-provoking.”', b'1', now(), '5');
INSERT INTO `case3`.`blog` (`blogID`, `title`, `content`, `status`, `createAt`, `accountID`) VALUES ('6', 'Why does it take some people so many years to finish therapy?', 'Um, sometimes because there are a shit-ton of issues to work through? I started therapy with a restrictive eating disorder (atypical anorexia) that swung over to bulimia, and then uncovered some traumas and developed PTSD on top of all of that. Oh yeah, and now I’m dealing with bereavement of someone whom I loved but caused one of my traumas. I think almost anyone would need a few years of therapy to deal with all that.', b'1', now(), '5');
INSERT INTO `case3`.`blog` (`blogID`, `title`, `content`, `status`, `createAt`, `accountID`) VALUES ('7', 'Why is therapy so hard?', 'So many reasons. I\'ll walk you through a session of mine.', b'1', '2022-10-07 22:40:07', '3');
INSERT INTO `case3`.`blog` (`blogID`, `title`, `content`, `status`, `createAt`, `accountID`) VALUES ('8', 'How can people who cannot get therapy help themselves?', 'As a licensed psychologist who has been practicing for more than 30 years I welcome your question. It is important to note that “therapy” - the many forms of psychotherapeutic contact that has the goal of providing support or guidance to a person who is struggling in life - has been around as a mass product for only a few decades and for many hundreds of thousands of years before people found ways of growing and learning without downing mouthfuls of pills or creating dependent relationships with “mental health providers.”', b'1', '2022-10-07 22:40:07', '3');
INSERT INTO `case3`.`blog` (`blogID`, `title`, `content`, `status`, `createAt`, `accountID`) VALUES ('9', 'Do Africans really think that China cares about them?', 'No. Africans do not think China cares about them.', b'1', '2022-10-07 22:40:07', '3');
INSERT INTO `case3`.`blog` (`blogID`, `title`, `content`, `status`, `createAt`, `accountID`) VALUES ('10', 'How does it feel to travel alone?', 'I lived and worked in five different countries by the time I was 19. Travelling alone was probably the best decision I could have made. If you have the opportunity to travel alone, take it! You won’t live to regret it.If you have the opportunity to travel alone, take it! You won’t live to regret it.If you have the opportunity to travel alone, take it! You won’t live to regret it.If you have the opportunity to travel alone, take it! You won’t live to regret it.If you have the opportunity to travel alone, take it! You won’t live to regret it.If you have the opportunity to travel alone, take it! You won’t live to regret it.If you have the opportunity to travel alone, take it! You won’t live to regret it.', b'1', '2022-10-07 22:40:07', '4');

-- Category - blog
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('1', '1');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('4', '1');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('7', '2');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('1', '2');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('7', '3');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('6', '4');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('5', '4');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('4', '5');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('7', '6');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('7', '8');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('7', '7');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('6', '9');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('5', '9');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('2', '10');
INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) VALUES ('4', '10');