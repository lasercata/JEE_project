/*
This file will activate a trigger that prevents the user from creating another character that has the same name as an already existing one.
*/

insert into characters (id, name) values (0, "DONTTAKEMYNAME")

-- ERROR HERE :
insert into characters (id, name) values (1, "DONTTAKEMYNAME")