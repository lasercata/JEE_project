/* This file runs all the sql script for the project, in the right order. */

/*
This procedure allows to print text.
 */
DROP PROCEDURE IF EXISTS print;

DELIMITER //
CREATE PROCEDURE print(in txt TEXT)
BEGIN
    SELECT txt as '';
END //
DELIMITER ;


source code/sql_scripts/create_tables.sql
source code/sql_scripts/load_data.sql
source code/sql_scripts/procedures.sql
