delete
from message;

insert into message(id, text, tag, user_id)
values (1, 'first', 'tag', 1),
       (2, 'second', 'another', 1),
       (3, 'third', 'thirdTag', 1),
       (4, 'fourth', 'tag', 1);

alter sequence message_id_seq restart with 10;