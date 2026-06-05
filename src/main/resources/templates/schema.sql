#Game publishers
CREATE TABLE publishers(
    id INTEGER PRIMARY KEY,
    name VARCHAR(32)
);

#Big 3: games, accounts, reviews
CREATE TABLE games(
    id INTEGER PRIMARY KEY,
    name VARCHAR(64),
    release_date DATE,
    publisher_id INTEGER,
    description VARCHAR(512),
    review_count INTEGER,
    rating_total FLOAT,
    FOREIGN KEY (publisher_id) REFERENCES publishers(id)
);

CREATE TABLE accounts(
    id INTEGER PRIMARY KEY,
    email UNIQUE VARCHAR(64),
    password VARCHAR(64),
    username VARCHAR(32),
);

CREATE TABLE reviews(
    id INTEGER PRIMARY KEY,
    game_id INTEGER,
    account_id INTEGER,
    rating FLOAT,
    title VARCHAR(64),
    body VARCHAR(512),
    likes INTEGER,
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (account_id) REFERENCES accounts(id)
);


#Big 3 intermediates
CREATE TABLE account_games(
    account_id INTEGER,
    game_id INTEGER,
    PRIMARY KEY (account_id, game_id),
    FOREIGN KEY (account_id) REFERENCES accounts(id),
    FOREIGN KEY (game_id) REFERENCES games(id)
);


#Game descriptors + intermediates
CREATE TABLE platforms(
    id INTEGER PRIMARY KEY,
    name VARCHAR(32)
);

CREATE TABLE game_platforms(
    game_id INTEGER,
    platform_id INTEGER,
    PRIMARY KEY (game_id, platform_id),
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (platform_id) REFERENCES platforms(id)
);

CREATE TABLE genres(
    id INTEGER PRIMARY KEY,
    name VARCHAR(32)
);

CREATE TABLE game_genres(
    game_id INTEGER,
    genre_id INTEGER,
    PRIMARY KEY (game_id, genre_id),
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);