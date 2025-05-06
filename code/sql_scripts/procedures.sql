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


--================ TRIGGERs
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
        TIME_TO_SEC(NEW.heureDebut) < TIME_TO_SEC(heureDebut) + duree * 60
        AND TIME_TO_SEC(heureDebut) < TIME_TO_SEC(NEW.heureDebut) + NEW.duree * 60
      );

    IF conflict_count > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Conflit : un show est déjà prévu à ce lieu et cet horaire.';
    END IF;
END;


CREATE TRIGGER trigger_check_character_name_unique
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
END;

CREATE TRIGGER trigger_check_character_availability
BEFORE INSERT ON starring
FOR EACH ROW
BEGIN
    DECLARE show_start TIME;
    DECLARE show_end TIME;
    DECLARE buffer_start TIME;
    DECLARE buffer_end TIME;
    DECLARE conflict_count INT;

    -- Récupérer l'heure du show concerné
    SELECT heureDebut, ADDTIME(heureDebut, SEC_TO_TIME(duree * 60))
    INTO show_start, show_end
    FROM shows
    WHERE id = NEW.idShow;

    -- Calculer les bornes avec le buffer de 15 minutes
    SET buffer_start = SUBTIME(show_start, '00:15:00');
    SET buffer_end = ADDTIME(show_end, '00:15:00');

    SELECT COUNT(*) INTO conflict_count
    FROM starring s
    JOIN shows sh ON s.idShow = sh.id
    WHERE s.idCharacter = NEW.idCharacter
      AND sh.jour = (SELECT jour FROM shows WHERE id = NEW.idShow)
      AND (
        TIME_TO_SEC(buffer_start) < TIME_TO_SEC(sh.heureDebut) + sh.duree * 60 + 900
        AND TIME_TO_SEC(sh.heureDebut) - 900 < TIME_TO_SEC(buffer_end)
      );

    IF conflict_count > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Le personnage est déjà occupé à cet horaire.';
    END IF;
END;
