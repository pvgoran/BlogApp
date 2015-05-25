alter table users add firstname varchar(255) null,
                  add lastname varchar(255) null,
                  add login varchar(255) null;
update users set firstname='', lastname='', login='';
alter table users alter column firstname set not null,
                  alter column lastname set not null,
                  alter column login set not null;
