/*
This file will activate a trigger that prevents the user from creating a show at the same place as another taking place at the same time.
CASE 3 : Show 1 and Show 2 at the same time
*/

insert into show (id, titre, jour, heureDebut, heureFin, lieu) values
(0, 'Show 1', 'Mardi', '10:00:00', '13:00:00', 'Vallée des Dragons');

-- ERROR HERE :
insert into show (id, titre, jour, heureDebut, heureFin, lieu) values
(1, 'Show 2', 'Mardi', '10:00:00', '13:00:00', 'Vallée des Dragons');
