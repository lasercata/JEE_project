/*
This file will activate a trigger that prevents the user from creating a restaurant that has an opening time after the closing time.
*/
-- ERROR HERE :
insert into restaurants
    (id, name, cuisine, nbOfSeats, moOP, moCL, tuOP, tuCL, weOP, weCL, thOP, thCL, frOP, frCL, saOP, saCL, suOP, suCL)
    values
    (
        0, "Restaurant 1", "Italienne",
        100,
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '18:00:00', '08:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00',
        '08:00:00', '18:00:00'
    );