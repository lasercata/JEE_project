/*
This file will activate a trigger that prevents the user from updating a character to have the same name as an already existing one.
*/

insert into characters (id, name) values 
(0, "DONTTAKEMYNAME"),
(1, "UNIQUE_NAME");

-- ERROR HERE:
update characters set name = "DONTTAKEMYNAME" where id = 1;