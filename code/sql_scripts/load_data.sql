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

