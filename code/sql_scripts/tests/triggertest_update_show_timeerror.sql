/*
This file will activate a trigger that prevents the user from updating a show to have an ending time before the starting time.
*/

insert into shows (id, titre, jour, heureDebut, heureFin, lieu) values
(0, 'Show 1', 'Mardi', '10:00:00', '13:00:00', 'Vallée des Dragons');

-- ERROR HERE:
update shows set heureDebut = '13:00:00', heureFin = '10:00:00' where id = 0;