call print("=== Emptying attractions and filling it ...");
truncate table attractions;
insert into attractions
    (id, name, type, sizealone, sizewithadult, moOP, moCL, tuOP, tuCL, weOP, weCL, thOP, thCL, frOP, frCL, saOP, saCL, suOP, suCL)
    values
    (
        0, "Attraction 1", "cinema4d",
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
        1, "Attraction 2", "familial",
        1.3, 1.3,
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00'
    );


call print("=== Emptying restaurants and filling it ...");
truncate table restaurants;
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

truncate table shows;
insert into shows
    (id, titre, heureDebut, duree, lieu)
    values
    (
        0, "Show 1", '08:00:00', 120, "Theater 1"
    ),
    (
        1, "Show 2", '09:00:00', 90, "Theater 2"
    ),
    (
        2, "Show 3", '10:00:00', 60, "Theater 3"
    ),
    (
        3, "Show 4", '11:00:00', 30, "Theater 4"
    ),
    (
        4, "Show 5", '12:00:00', 45, "Theater 5"
    ),
    (
        5, "Show 6", '13:00:00', 75, "Theater 6"
    ),
    (
        6, "Show 7", '14:00:00', 150, "Theater 7"
    ),
    (
        7, "Show 8", '15:00:00', 180, "Theater 8"
    );

truncate table characters;
insert into characters
    (id, name)
    values
    (
        0, "Character 1"
    ),
    (
        1, "Character 2"
    )
    (
        2, "Character 3"
    ),
    (
        3, "Character 4"
    );
    (
        4, "Character 5"
    ),
    (
        5, "Character 6"
    ),
    (
        6, "Character 7"
    ),
    (
        7, "Character 8"
    );
    (
        8, "Character 9"
    ),
    (
        9, "Character 10"
    ),
    (
        10, "Character 11"
    ),
    (
        11, "Character 12"
    ),
    (
        12, "Character 13"
    ),
    (
        13, "Character 14"
    ),
    (
        14, "Character 15"
    );
    (
        15, "Character 16"
    ),
    (
        16, "Character 17"
    ),
    (
        17, "Character 18"
    ),
    (
        18, "Character 19"
    ),
    (
        19, "Character 20"
    );

truncate table starring;
insert into starring
    (idShow, idCharacter)
    values
    (
        0, 0
    ),
    (
        0, 1
    ),
    (
        1, 0
    ),
    (
        1, 1
    ),
    (
        1, 2
    ),
    (
        2, 0
    ),
    (
        2, 1
    ),
    (
        2, 2
    ),
    (
        2, 3
    ),
    (
        3, 0
    ),
    (
        3, 1
    ),
    (
        3, 2
    ),
    (
        3, 3
    ),
    (
        3, 4
    ),
    (
        4, 0
    ),
    (
        4, 1
    ),
    (
        4, 2
    ),
    (
        4, 3
    ),
    (
        4, 4
    ),
    (
        4, 5
    ),
    (
        5, 0
    ),
    (
        5, 1
    ),
    (
        5, 2
    ),
    (
        5, 3
    ),
    (
        5, 4
    ),
    (
        5, 5
    ),
    (
        5, 6
    ),
    (
        6, 0
    ),
    (
        6, 1
    ),
    (
        6, 2
    ),
    (
        6, 3
    ),
    (
        6, 4
    ),
    (
        6, 5
    ),
    (
        6, 6
    ),
    (
        6, 7
    );
