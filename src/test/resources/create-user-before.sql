delete
from user_role;
delete
from usr;

insert into usr (id, active, password, username)
values (1, true, '$2a$08$eIg8IF2ZMqKohKo1gejmbenvAvDbNoLGKzdOEXvv/g8cB0vnEQShm', 'admin'),
       (2, true, '$2a$08$DtjtVHzO5QEmS0CNBdiBveycCtKTkFNkBDO3pQzDjfnN3np4SN3rG', 'Liza');

insert into user_role(user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN'),
       (2, 'USER');