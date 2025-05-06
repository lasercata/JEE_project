call print("=== Emptying all tables ...");
SET FOREIGN_KEY_CHECKS = 0; -- disable foreign key checks

truncate table attractions;
truncate table restaurants;
truncate table shows;
truncate table characters;
truncate table starring;

SET FOREIGN_KEY_CHECKS = 1; -- re-enable foreign key checks

call print("=== Inserting in attractions ...");
insert into attractions
    (id, name, type, sizealone, sizewithadult, moOP, moCL, tuOP, tuCL, weOP, weCL, thOP, thCL, frOP, frCL, saOP, saCL, suOP, suCL)
    values
    (
        0, "4D Adventure", "cinema4d",
        1.5, 1.5,
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00'
    ),
    (
        1, "Family Coaster", "familial",
        1.3, 1.3,
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
        0, "Restaurant 1", "ITALIAN",
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
        1, "Restaurant 2", "CHINESE",
        150,
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00'
    );

call print("=== Inserting in shows ...");
insert into shows
    (id, titre, jour, heureDebut, duree, lieu)
    values
    (0, "Magic Show", 'lundi', '08:00:00', 120, "Main Stage"),
    (1, "Comedy Night", 'mardi', '09:00:00', 90, "Comedy Club"),
    (2, "Dance Performance", 'jeudi', '10:00:00', 60, "Dance Hall"),
    (3, "Acrobatics", 'samedi', '11:00:00', 30, "Acrobat Arena"),
    (4, "Puppet Show", 'mercredi', '12:00:00', 45, "Puppet Theater"),
    (5, "Live Concert", 'dimanche', '13:00:00', 75, "Concert Hall"),
    (6, "Drama Play", 'jeudi', '14:00:00', 150, "Drama Theater"),
    (7, "Fireworks Display", 'samedi', '15:00:00', 180, "Outdoor Arena");

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



call print("=== Inserting in starring ...");
insert into starring
    (idShow, idCharacter)
    values
    (0, 0),
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7);


-- TRIGGER TESTING ===========================
