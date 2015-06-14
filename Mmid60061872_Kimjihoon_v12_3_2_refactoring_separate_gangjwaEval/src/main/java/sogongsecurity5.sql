create table user (
	userid varchar(20) primary key,
	userpassword varchar(20) not null,
	enabled boolean not null,
	username varchar(20) not null
);
insert into user values ('1', '1', true, 'oneman');
insert into user values ('2', '2', true, 'twoman');
insert into user values ('3', '3', true, 'threeman');
insert into user values ('4', '4', true, 'fourman');
insert into user values ('5', '5', true, 'fiveman');
insert into user values ('6', '6', true, 'sixman');
insert into user values ('7', '7', true, 'sevenman');
insert into user values ('8', '8', true, 'eightman');
insert into user values ('9', '9', true, 'nineman');
insert into user values ('10', '10', true, 'tenman');
insert into user values ('11', '11', true, 'elevenman');
insert into user values ('12', '12', true, 'twelveman');
insert into user values ('13', '13', true, 'thirteenman');
insert into user values ('14', '14', true, 'fourteenman');
insert into user values ('15', '15', true, 'fifteenman');
insert into user values ('prof1', 'prof1', true, 'prof.choi');
insert into user values ('prof2', 'prof2' ,true, 'prof.park');
insert into user values ('prof3', 'prof3', true, 'prof.kim');
insert into user values ('admin1', 'admin1', true, 'admin1');
insert into user values ('admin2', 'admin2', true, 'admin2');


create table gwamok (
	gwamokid varchar(20) primary key,
	gwamokname varchar(30) not null,
	gwamokhakjeom varchar(2) not null,
	haknyun varchar(2) not null,
	year varchar(5) not null,
	hakki varchar(2) not null
);
insert into gwamok values ('1', 'java', '3', '1', '2015', '2');
insert into gwamok values ('2', 'softwareEngineering', '3', '3', '2014', '1');
insert into gwamok values ('3', 'network', '3', '4', '2013', '1');
insert into gwamok values ('4', 'c-language', '3', '1', '2015', '1');
insert into gwamok values ('5', 'spring', '2', '3', '2015', '2');
insert into gwamok values ('6', 'security', '2', '4', '2015', '1');
insert into gwamok values ('7', 'database', '3', '3', '2015', '2');
insert into gwamok values ('8', 'C++', '3', '2', '2013', '1');


create table gangjwa (
	gangjwaid varchar(20) primary key,
	maxstudent tinyint not null,
	currentstudent tinyint not null,
	gwamokid varchar(20) not null,
	profid varchar(20) not null,
	foreign key(gwamokid) references gwamok(gwamokid),
	foreign key(profid) references user(userid)
);
insert into gangjwa value ('11', '3', '0', '1', 'prof1');
insert into gangjwa value ('12', '10', '0', '1', 'prof3');
insert into gangjwa value ('13', '4', '0', '1', 'prof2');
insert into gangjwa value ('21', '30', '0', '2', 'prof2');
insert into gangjwa value ('22', '10', '0', '2', 'prof3');
insert into gangjwa value ('23', '13', '0', '2', 'prof1');
insert into gangjwa value ('24', '4', '0', '2', 'prof2');
insert into gangjwa value ('31', '5', '0', '3', 'prof1');
insert into gangjwa value ('32', '2', '0', '3', 'prof1');
insert into gangjwa value ('41', '4', '0', '4', 'prof3');
insert into gangjwa value ('42', '14', '0', '4', 'prof2');
insert into gangjwa value ('43', '21', '0', '4', 'prof2');
insert into gangjwa value ('44', '10', '0', '4', 'prof2');
insert into gangjwa value ('45', '10', '0', '4', 'prof1');
insert into gangjwa value ('51', '20', '0', '5', 'prof1');
insert into gangjwa value ('52', '2', '0', '5', 'prof3');
insert into gangjwa value ('53', '5', '0', '5', 'prof3');
insert into gangjwa value ('54', '6', '0', '5', 'prof1');
insert into gangjwa value ('61', '7', '0', '6', 'prof2');
insert into gangjwa value ('71', '30', '0', '7', 'prof1');
insert into gangjwa value ('72', '8', '0', '7', 'prof1');
insert into gangjwa value ('73', '8', '0', '7', 'prof2');
insert into gangjwa value ('74', '5', '0', '7', 'prof2');
insert into gangjwa value ('75', '7', '0', '7', 'prof3');
insert into gangjwa value ('81', '8', '0', '8', 'prof3');
insert into gangjwa value ('82', '6', '0', '8', 'prof1');
insert into gangjwa value ('83', '7', '0', '8', 'prof3');


create table gangjwa_score (
	userid varchar(20) not null,
	gangjwaid varchar(20) not null,
	jeomsu varchar(3) not null,
	foreign key (userid) references user(userid),
	foreign key (gangjwaid) references gangjwa(gangjwaid)
);
alter table gangjwa_score add primary key (userid,gangjwaid);	

create table gangjwa_evaluation (
	userid varchar(20) not null,
	gangjwaid varchar(20)	not null,
	gangjwaeval text,
	isread boolean,
	date varchar(20) not null,
	foreign key (userid) references user(userid),
	foreign key (gangjwaid) references gangjwa(gangjwaid)
);
alter table gangjwa_evaluation add primary key (userid,gangjwaid);	

create table sugangtable (
	gangjwaid varchar(20),
	userid varchar(20),
	foreign key(gangjwaid) references gangjwa(gangjwaid)
);


create table authority (
	authorityid varchar(50) primary key,
	authority_explain varchar(50) not null,
	authority_code varchar(2) not null
);
insert authority values ('ROLE_ADMIN', 'administrator', '1');
insert authority values ('ROLE_GYOSU', 'gyosu', '2');
insert authority values ('ROLE_STU', 'student', '3');


create table user_authority (
	userid varchar(50) not null,
	authorityid varchar(50) not null,
	foreign key(userid) references user(userid),
	foreign key(authorityid) references authority(authorityid)
);
insert user_authority values ('1', 'ROLE_STU');
insert user_authority values ('2', 'ROLE_STU');
insert user_authority values ('3', 'ROLE_STU');
insert user_authority values ('4', 'ROLE_STU');
insert user_authority values ('5', 'ROLE_STU');
insert user_authority values ('6', 'ROLE_STU');
insert user_authority values ('7', 'ROLE_STU');
insert user_authority values ('8', 'ROLE_STU');
insert user_authority values ('9', 'ROLE_STU');
insert user_authority values ('10', 'ROLE_STU');
insert user_authority values ('11', 'ROLE_STU');
insert user_authority values ('12', 'ROLE_STU');
insert user_authority values ('13', 'ROLE_STU');
insert user_authority values ('14', 'ROLE_STU');
insert user_authority values ('15', 'ROLE_STU');
insert user_authority values ('prof1', 'ROLE_GYOSU');
insert user_authority values ('prof2', 'ROLE_GYOSU');
insert user_authority values ('prof3', 'ROLE_GYOSU');
insert user_authority values ('admin1', 'ROLE_ADMIN');
insert user_authority values ('admin2', 'ROLE_ADMIN');


create table groups (
	groupid int primary key,
	groupname varchar(50) not null
);
insert into groups values(1, 'administrators');
insert into groups values(2, 'gyosu');
insert into groups values(3, 'users');


create table groups_member (
	groupid int,
	userid varchar(50),
	foreign key (groupid) references groups(groupid),
	foreign key (userid) references user(userid)
);
insert into groups_member values(3, '1');
insert into groups_member values(3, '2');
insert into groups_member values(3, '3');
insert into groups_member values(3, '4');
insert into groups_member values(3, '5');
insert into groups_member values(3, '6');
insert into groups_member values(3, '7');
insert into groups_member values(3, '8');
insert into groups_member values(3, '9');
insert into groups_member values(3, '10');
insert into groups_member values(3, '11');
insert into groups_member values(3, '12');
insert into groups_member values(3, '13');
insert into groups_member values(3, '14');
insert into groups_member values(3, '15');
insert into groups_member values(2, 'prof1');
insert into groups_member values(2, 'prof2');
insert into groups_member values(2, 'prof3');
insert into groups_member values(1, 'admin1');
insert into groups_member values(1, 'admin2');


create table groups_authority (
	groupid int,
	authorityid varchar(50),
	foreign key (groupid) references groups(groupid),
	foreign key (authorityid) references authority(authorityid)
);
insert into groups_authority values(1, 'ROLE_ADMIN');
insert into groups_authority values(1, 'ROLE_GYOSU');
insert into groups_authority values(1, 'ROLE_STU');
insert into groups_authority values(2, 'ROLE_GYOSU');
insert into groups_authority values(3, 'ROLE_STU');

