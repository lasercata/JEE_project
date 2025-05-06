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

call print("=== Creating shows table ...");
CREATE TABLE IF NOT EXISTS shows (
    id INT,
    titre VARCHAR(100),
    jour VARCHAR(100),
    heureDebut TIME,
    heureFin TIME,
    lieu VARCHAR(100),

    PRIMARY KEY (id, heureDebut)
);

call print("=== Creating characters table ...");
CREATE TABLE IF NOT EXISTS characters (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

call print("=== Creating starring table ...");
CREATE TABLE IF NOT EXISTS starring (
    idShow INT,
    idCharacter INT,
    PRIMARY KEY (idShow, idCharacter),
    FOREIGN KEY (idShow) REFERENCES shows(id),
    FOREIGN KEY (idCharacter) REFERENCES characters(id)
);
