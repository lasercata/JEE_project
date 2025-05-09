call print("=== Dropping existing triggers ...");
DROP TRIGGER IF EXISTS trigger_check_show_conflict_insert;
DROP TRIGGER IF EXISTS trigger_check_character_name_unique_insert;
DROP TRIGGER IF EXISTS trigger_check_character_availability_insert;
DROP TRIGGER IF EXISTS trigger_check_show_time_insert;
DROP TRIGGER IF EXISTS trigger_check_restaurant_hours_insert;
DROP TRIGGER IF EXISTS trigger_check_show_conflict_update;
DROP TRIGGER IF EXISTS trigger_check_character_name_unique_update;
DROP TRIGGER IF EXISTS trigger_check_character_availability_update;
DROP TRIGGER IF EXISTS trigger_check_show_time_update;
DROP TRIGGER IF EXISTS trigger_check_restaurant_hours_update;

/**
This procedure allows to print text.
 */
DROP PROCEDURE IF EXISTS print;
DELIMITER //
CREATE PROCEDURE print(in txt TEXT)
BEGIN
    SELECT txt as '';
END //
DELIMITER ;

call print("=== Creating triggers ...");
DELIMITER //

CREATE TRIGGER trigger_check_show_conflict_insert
BEFORE INSERT ON shows
FOR EACH ROW
BEGIN
    DECLARE conflict_count INT;
    SELECT COUNT(*) INTO conflict_count
    FROM shows
    WHERE lieu = NEW.lieu
      AND jour = NEW.jour
      AND (
        (TIME_TO_SEC(NEW.heureDebut) < TIME_TO_SEC(heureFin)
        AND TIME_TO_SEC(NEW.heureFin) > TIME_TO_SEC(heureDebut))
        OR
        (TIME_TO_SEC(heureDebut) < TIME_TO_SEC(NEW.heureFin)
        AND TIME_TO_SEC(heureFin) > TIME_TO_SEC(NEW.heureDebut))
      );

    IF conflict_count > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Conflit : un show est déjà prévu à ce lieu et cet horaire.';
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER trigger_check_show_conflict_update
BEFORE UPDATE ON shows
FOR EACH ROW
BEGIN
    DECLARE conflict_count INT;
    SELECT COUNT(*) INTO conflict_count
    FROM shows
    WHERE lieu = NEW.lieu
      AND jour = NEW.jour
      AND (
        (TIME_TO_SEC(NEW.heureDebut) < TIME_TO_SEC(heureFin)
        AND TIME_TO_SEC(NEW.heureFin) > TIME_TO_SEC(heureDebut))
        OR
        (TIME_TO_SEC(heureDebut) < TIME_TO_SEC(NEW.heureFin)
        AND TIME_TO_SEC(heureFin) > TIME_TO_SEC(NEW.heureDebut))
      );

    IF conflict_count > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Conflit : un show est déjà prévu à ce lieu et cet horaire.';
    END IF;
END //

DELIMITER ;


DELIMITER //
CREATE TRIGGER trigger_check_character_name_unique_insert
BEFORE INSERT ON characters
FOR EACH ROW
BEGIN
    DECLARE name_count INT;

    SELECT COUNT(*) INTO name_count
    FROM characters
    WHERE name = NEW.name;

    IF name_count > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Un personnage avec ce nom existe déjà.';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER trigger_check_character_name_unique_update
BEFORE UPDATE ON characters
FOR EACH ROW
BEGIN
    DECLARE name_count INT;

    SELECT COUNT(*) INTO name_count
    FROM characters
    WHERE name = NEW.name;

    IF name_count > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Un personnage avec ce nom existe déjà.';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER trigger_check_character_availability_insert
BEFORE INSERT ON starring
FOR EACH ROW
BEGIN
    DECLARE new_jour VARCHAR(20);
    DECLARE new_debut TIME;
    DECLARE new_fin TIME;
    DECLARE buffer_start TIME;
    DECLARE buffer_end TIME;
    DECLARE conflict_count INT;

    -- Récupère les infos du show
    SELECT jour, heureDebut, heureFin INTO new_jour, new_debut, new_fin
    FROM shows WHERE id = NEW.idShow LIMIT 1;

    SET buffer_start = SUBTIME(new_debut, '00:15:00');
    SET buffer_end = ADDTIME(new_fin, '00:15:00');

    SELECT COUNT(*) INTO conflict_count
    FROM starring s
    JOIN shows sh ON s.idShow = sh.id
    WHERE s.idCharacter = NEW.idCharacter
      AND sh.jour = new_jour
      AND (
        (TIME_TO_SEC(buffer_start) < TIME_TO_SEC(sh.heureFin) + 900
        AND TIME_TO_SEC(buffer_start) > TIME_TO_SEC(sh.heureDebut))
        OR
        (TIME_TO_SEC(sh.heureDebut) - 900 < TIME_TO_SEC(buffer_end)
        AND TIME_TO_SEC(sh.heureFin) > TIME_TO_SEC(buffer_end))
      );

    IF conflict_count > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Le personnage est déjà occupé à cet horaire.';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER trigger_check_show_time_insert
BEFORE INSERT ON shows
FOR EACH ROW
BEGIN
    IF NEW.heureFin <= NEW.heureDebut THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Erreur : l''heure de fin du spectacle doit être après l''heure de début.';
    END IF;
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER trigger_check_show_time_update
BEFORE UPDATE ON shows
FOR EACH ROW
BEGIN
    IF NEW.heureFin <= NEW.heureDebut THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Erreur : l''heure de fin du spectacle doit être après l''heure de début.';
    END IF;
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER trigger_check_restaurant_hours_insert
BEFORE INSERT ON restaurants
FOR EACH ROW
BEGIN
    IF NEW.moOP IS NOT NULL AND NEW.moCL IS NOT NULL AND NEW.moCL <= NEW.moOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Lundi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.tuOP IS NOT NULL AND NEW.tuCL IS NOT NULL AND NEW.tuCL <= NEW.tuOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mardi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.weOP IS NOT NULL AND NEW.weCL IS NOT NULL AND NEW.weCL <= NEW.weOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mercredi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.thOP IS NOT NULL AND NEW.thCL IS NOT NULL AND NEW.thCL <= NEW.thOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Jeudi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.frOP IS NOT NULL AND NEW.frCL IS NOT NULL AND NEW.frCL <= NEW.frOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Vendredi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.saOP IS NOT NULL AND NEW.saCL IS NOT NULL AND NEW.saCL <= NEW.saOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Samedi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.suOP IS NOT NULL AND NEW.suCL IS NOT NULL AND NEW.suCL <= NEW.suOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Dimanche : heure de fermeture <= heure d''ouverture';
    END IF;
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER trigger_check_restaurant_hours_update
BEFORE UPDATE ON restaurants
FOR EACH ROW
BEGIN
    IF NEW.moOP IS NOT NULL AND NEW.moCL IS NOT NULL AND NEW.moCL <= NEW.moOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Lundi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.tuOP IS NOT NULL AND NEW.tuCL IS NOT NULL AND NEW.tuCL <= NEW.tuOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mardi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.weOP IS NOT NULL AND NEW.weCL IS NOT NULL AND NEW.weCL <= NEW.weOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mercredi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.thOP IS NOT NULL AND NEW.thCL IS NOT NULL AND NEW.thCL <= NEW.thOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Jeudi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.frOP IS NOT NULL AND NEW.frCL IS NOT NULL AND NEW.frCL <= NEW.frOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Vendredi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.saOP IS NOT NULL AND NEW.saCL IS NOT NULL AND NEW.saCL <= NEW.saOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Samedi : heure de fermeture <= heure d''ouverture';
    END IF;
    IF NEW.suOP IS NOT NULL AND NEW.suCL IS NOT NULL AND NEW.suCL <= NEW.suOP THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Dimanche : heure de fermeture <= heure d''ouverture';
    END IF;
END//
DELIMITER ;


DELIMITER //

CREATE TRIGGER trigger_check_character_availability_update
BEFORE UPDATE ON shows
FOR EACH ROW
BEGIN
    DECLARE new_jour VARCHAR(20);
    DECLARE new_debut TIME;
    DECLARE new_fin TIME;
    DECLARE buffer_start TIME;
    DECLARE buffer_end TIME;
    DECLARE conflict_count INT;
    DECLARE character_id INT;
    DECLARE done BOOL DEFAULT FALSE;

    DECLARE cur CURSOR FOR 
        SELECT s.idCharacter
        FROM starring s
        WHERE s.idShow = NEW.id;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    SET new_jour = NEW.jour;
    SET new_debut = NEW.heureDebut;
    SET new_fin = NEW.heureFin;
    SET buffer_start = SUBTIME(new_debut, '00:15:00');
    SET buffer_end = ADDTIME(new_fin, '00:15:00');

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO character_id;
        IF done THEN
            LEAVE read_loop;
        END IF;

        SELECT COUNT(*) INTO conflict_count
        FROM starring s
        JOIN shows sh ON s.idShow = sh.id
        WHERE s.idCharacter = character_id
          AND sh.jour = new_jour
          AND sh.id != NEW.id
          AND (
              (TIME_TO_SEC(buffer_start) < TIME_TO_SEC(sh.heureFin) + 900
              AND TIME_TO_SEC(NEW.heureDebut) >= TIME_TO_SEC(sh.heureDebut))
            OR
              (TIME_TO_SEC(sh.heureDebut) - 900 < TIME_TO_SEC(buffer_end)
              AND TIME_TO_SEC(sh.heureFin) >= TIME_TO_SEC(NEW.heureFin))
      );

        IF conflict_count > 0 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Un personnage est déjà occupé à cet horaire.';
        END IF;
    END LOOP;

    CLOSE cur;
END //

DELIMITER ;
