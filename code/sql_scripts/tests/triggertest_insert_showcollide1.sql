/*
This file will activate a trigger that prevents the user from creating a show at the same place as another taking place at the same time.
CASE 1 : Show 1 starts before Show 2
*/

insert into shows (id, titre, jour, heureDebut, heureFin, lieu) values
(0, "Show 1", "Mardi", "10:00:00", "13:00:00", "Vallée des Dragons");

-- ERROR HERE :
insert into shows (id, titre, jour, heureDebut, heureFin, lieu) values
(1, "Show 2", "Mardi", "12:00:00", "15:00:00", "Vallée des Dragons");
