/*
This file will activate a trigger that prevents the user from updating a show to take place at the same time and location as another show.
CASE 3: Show 1 and Show 2 at the same time
*/

insert into shows (id, titre, jour, heureDebut, heureFin, lieu) values
(0, 'Show 1', 'Mardi', '10:00:00', '13:00:00', 'Vallée des Dragons'),
(1, 'Show 2', 'Mardi', '14:00:00', '16:00:00', 'Vallée des Dragons');

-- ERROR HERE:
update shows set heureDebut = '10:00:00', heureFin = '13:00:00' where id = 1;