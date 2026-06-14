-- Game publishers
CREATE TABLE publishers(
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL
);

-- Big 3: games, accounts, reviews
CREATE TABLE games(
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    release_date DATE,
    publisher_id INTEGER NOT NULL,
    description VARCHAR(512),
    review_count INTEGER,
    rating_total FLOAT,
    FOREIGN KEY (publisher_id) REFERENCES publishers(id)
);

CREATE TABLE accounts(
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email VARCHAR(64) UNIQUE NOT NULL,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(64) NOT NULL
);

CREATE TABLE reviews(
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    game_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    rating FLOAT NOT NULL,
    title VARCHAR(64) NOT NULL,
    body VARCHAR(512),
    likes INTEGER,
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);


-- Big 3 intermediates
CREATE TABLE account_games(
    account_id INTEGER NOT NULL,
    game_id INTEGER NOT NULL,
    PRIMARY KEY (account_id, game_id),
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE,
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
);


-- Game descriptors + intermediates
CREATE TABLE platforms(
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE game_platforms(
    game_id INTEGER NOT NULL,
    platform_id INTEGER NOT NULL,
    PRIMARY KEY (game_id, platform_id),
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
    FOREIGN KEY (platform_id) REFERENCES platforms(id) ON DELETE CASCADE
);

CREATE TABLE genres(
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE game_genres(
    game_id INTEGER NOT NULL,
    genre_id INTEGER NOT NULL,
    PRIMARY KEY (game_id, genre_id),
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
);