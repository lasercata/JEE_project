DROP TRIGGER IF EXISTS trigger_check_show_conflict;
DROP TRIGGER IF EXISTS trigger_check_character_name_unique;
DROP TRIGGER IF EXISTS trigger_check_character_availability;

DELIMITER //

CREATE TRIGGER trigger_check_show_conflict
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
CREATE TRIGGER trigger_check_character_name_unique
BEFORE INSERT ON characters
FOR EACH ROW
BEGIN
    DECLARE name_count INT;

    SELECT COUNT(id) INTO name_count
    FROM characters
    WHERE name = NEW.name;

    IF name_count > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Un personnage avec ce nom existe déjà.';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER trigger_check_character_availability
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
