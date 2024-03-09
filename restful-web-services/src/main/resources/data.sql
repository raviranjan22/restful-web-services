insert into user_details (id, name, birth_date)
values (10001, 'Ravi', current_date());

insert into user_details (id, name, birth_date)
values (10002, 'Rajnish', current_date());

insert into user_details (id, name, birth_date)
values (10003, 'Hemanth', current_date());

insert into post (id, user_id, description)
values (20001, 10001, 'I want to learn springboot');

insert into post (id, user_id, description)
values (20002, 10001, 'I want to learn Java');

insert into post (id, user_id, description)
values (20003, 10003, 'I want to learn JS');

insert into post (id, user_id, description)
values (20004, 10003, 'I want to learn NodeJS');