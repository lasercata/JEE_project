call print("=== Emptying all tables ...");
SET FOREIGN_KEY_CHECKS = 0; -- disable foreign key checks

truncate table attractions;
truncate table restaurants;
truncate table starring;
truncate table shows;
truncate table characters;

SET FOREIGN_KEY_CHECKS = 1; -- re-enable foreign key checks

call print("=== Inserting in attractions ...");
insert into attractions
    (id, name, type, sizealone, sizewithadult, moOP, moCL, tuOP, tuCL, weOP, weCL, thOP, thCL, frOP, frCL, saOP, saCL, suOP, suCL)
    values
    (
        0, "Dinosaur Maxi Proto Simulator 3 4D", "cinema4d",
        1.5, 1,
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00'
    ),
    (
        1, "Death spin (hyper dangereux)", "familial",
        0.5, 0.01,
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00'
    ),
    (
        2, "Space Bouteille (jeu de mot)", "rollercoaster",
        6.4, 1.5,
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00'
    );

call print("=== Inserting in restaurants ...");
insert into restaurants
    (id, name, cuisine, nbOfSeats, moOP, moCL, tuOP, tuCL, weOP, weCL, thOP, thCL, frOP, frCL, saOP, saCL, suOP, suCL)
    values
    (
        0, "La Tour de Pizza", "ITALIAN",
        100,
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00'
    ),
    (
        1, "Ramen ta fraise", "JAPANESE",
        150,
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00'
    ),
    (
        2, "A la baguette", "FRENCH",
        50,
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00'
    );

call print("=== Inserting in characters ...");
insert into characters
    (id, name)
    values
    (0, "Sherlock Holmes"),
    (1, "Gandalf"),
    (2, "Darth Vader"),
    (3, "Harry Potter"),
    (4, "Aria Stark"),
    (5, "Spock"),
    (6, "Batman"),
    (7, "Homer Simpson"),
    (8, "Lara Croft"),
    (9, "The Joker"),
    (10, "Mario"),
    (11, "Legolas"),
    (12, "Yoda"),
    (13, "Kratos"),
    (14, "Spider-Man");

call print("=== Inserting in shows ...");
insert into shows
    (id, titre, jour, heureDebut, heureFin, lieu)
    values
    (
        0, "Dark Vador fait un backflip", "Lundi",
        '12:00:00', '13:00:00',
        'Vallée des Dragons'
    ),
    (
        1, "Dark Vador à l'hopital (il a raté son backflip)", "Mardi",
        '10:00:00', '12:00:00',
        'Château Mystérieux'
    ),
    (
        2, "Batman va voir Dark Vador à l'Hopital", "Mardi",
        '15:00:00', '18:00:00',
        'Château Mystérieux'
    ),
    (
        3, "Spider-Man fait ses coursees", "Mercredi",
        '08:00:00', '14:00:00',
        'Arène Intergalactique'
    );

call print("=== Inserting in starring ...");
insert into starring
    (idShow, idCharacter)
    values
    (0, 2),
    (1, 2),
    (2, 2),
    (2, 6),
    (3, 14);


-- TRIGGER TESTING ===========================
-- Test for character name uniqueness
-- insert into characters (id, name) values (15, 'Sherlock Holmes');

-- Test for show time conflict
-- insert into shows (id, titre, jour, heureDebut, heureFin, lieu) values (12, 'Other thing', 'lundi', '08:00:00', 120, 'Main Stage');

-- Test for character availability
-- insert into starring (idShow, idCharacter) values (8, 0);
-- insert into starring (idShow, idCharacter) values (9, 0);
-- insert into starring (idShow, idCharacter) values (10, 0);
