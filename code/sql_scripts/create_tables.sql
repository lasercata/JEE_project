call print("=== Creating attractions table ...");
CREATE TABLE IF NOT EXISTS attractions (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    type VARCHAR(50),
    sizealone FLOAT,
    sizewithadult FLOAT,
    moOP TIME,
    moCL TIME,
    tuOP TIME,
    tuCL TIME,
    weOP TIME,
    weCL TIME,
    thOP TIME,
    thCL TIME,
    frOP TIME,
    frCL TIME,
    saOP TIME,
    saCL TIME,
    suOP TIME,
    suCL TIME
);

call print("=== Creating restaurants table ...");
CREATE TABLE IF NOT EXISTS restaurants (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    cuisine VARCHAR(100),
    nbofseats INT,
    moOP TIME,
    moCL TIME,
    tuOP TIME,
    tuCL TIME,
    weOP TIME,
    weCL TIME,
    thOP TIME,
    thCL TIME,
    frOP TIME,
    frCL TIME,
    saOP TIME,
    saCL TIME,
    suOP TIME,
    suCL TIME
);


CREATE TABLE IF NOT EXISTS shows (
    id INT PRIMARY KEY,
    titre VARCHAR(100),
    heureDebut TIME,
    duree INT,
    lieu VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS characters (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE starring (
    idShow INT,
    idCharacter INT,
    PRIMARY KEY (idShow, idCharacter),
    FOREIGN KEY (idShow) REFERENCES shows(id),
    FOREIGN KEY (idCharacter) REFERENCES characters(id)
);
