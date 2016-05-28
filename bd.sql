create table user (
	userId int auto_increment primary key not null,
    userName varchar(30) unique not null,
    pswd varchar(30) not null,
    rootRights boolean,
    isBlocked boolean,
    isRestricted boolean
);
use users;
select * from user;
ALTER TABLE user DROP Column pswd;
ALTER TABLE user ADD Column pswd varchar(30) ;
Update user set pswd = AES_ENCRYPT(pswd, 'alex'), userName = AES_ENCRYPT(userName, 'alex');
Update user set pswd = AES_DECRYPT(pswd, 'alex'), userName = AES_DECRYPT(userName, 'alex');