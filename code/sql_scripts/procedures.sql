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
