create sequence users_seq;

create table users
(
  id int not null primary key default nextval('users_seq'),
  createtimestamp timestamp not null,
  firstname varchar(255) not null,
  lastname varchar(255) not null,
  login varchar(255) not null
);

create sequence posts_seq;

create table posts
(
  id int not null primary key default nextval('posts_seq'),
  createuserid int not null references users(id) on delete restrict,
  createtimestamp timestamp not null,
  updatetimestamp timestamp not null,
  text text not null
);
