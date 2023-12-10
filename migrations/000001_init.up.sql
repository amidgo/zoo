CREATE TABLE IF NOT EXISTS animal_kinds (
    id INT NOT NULL AUTO_INCREMENT,
    kind VARCHAR(30) NOT NULL,

    CONSTRAINT animal_kind_unique UNIQUE KEY (kind),
    PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS animals (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    animal_kind_id INT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_animals__animal_kinds FOREIGN KEY (animal_kind_id) REFERENCES animal_kinds (id)
);

CREATE TABLE IF NOT EXISTS visitors (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    father_name VARCHAR(30) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS visitors_favorite_animals (
    visitor_id INT NOT NULL,
    animal_id INT NOT NULL,

    CONSTRAINT fk_visitors_favorite_animals__visitors FOREIGN KEY (visitor_id) REFERENCES visitors(id),
    CONSTRAINT fk_visitors_favorite_animals__animals FOREIGN KEY (animal_id) REFERENCES animals(id),

    CONSTRAINT visitors_favorite_animals_key PRIMARY KEY (visitor_id,animal_id)
);
