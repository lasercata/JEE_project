/*
This file will activate a trigger that prevents the user from creating a show that has an ending time before the starting time.
*/
-- ERROR HERE :
insert into shows (id, titre, jour, heureDebut, heureFin, lieu) values
(0, 'Show 1', 'Mardi', '13:00:00', '10:00:00', 'Vallée des Dragons')