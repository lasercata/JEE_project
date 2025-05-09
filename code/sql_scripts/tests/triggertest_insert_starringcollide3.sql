/*
This file will activate a trigger that prevents the user from adding a character at a show while this character is still busy.
CASE 3 : Show 1 starts after Show 2, but Show 1 starts less than 30 minutes after Show 2
*/

insert into show (id, titre, jour, heureDebut, heureFin, lieu) values
(0, 'Show 1', 'Mardi', '10:00:00', '13:00:00', 'Vallée des Dragons');
insert into show (id, titre, jour, heureDebut, heureFin, lieu) values
(1, 'Show 2', 'Mardi', '8:00:00', '9:40:00', 'Arène Intergalactique');

insert into characters (id, name) values (0, "A Very Popular Guy");

insert into starring (idShow, idCharacter) values
(0, 0);

-- ERROR HERE :
insert into starring (idShow, idCharacter) values
(1, 0);
