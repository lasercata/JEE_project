/*
This file will activate a trigger that prevents the user from updating a show to take place at the same time and location as another show.
CASE 2: Show 1 starts after Show 2
*/

insert into shows (id, titre, jour, heureDebut, heureFin, lieu) values
(0, 'Show 1', 'Mardi', '10:00:00', '13:00:00', 'Vallée des Dragons'),
(1, 'Show 2', 'Mardi', '08:00:00', '09:30:00', 'Vallée des Dragons');

-- ERROR HERE:
update shows set heureDebut = '09:00:00', heureFin = '12:00:00' where id = 1;