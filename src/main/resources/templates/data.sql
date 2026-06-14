-- Publishers
INSERT INTO publishers(name) VALUES
('Valve');

-- Platforms
INSERT INTO platforms(name) VALUES
('PC'),
('Nintendo Switch'),
('Xbox 360'),
('PS5');

-- Genres
INSERT INTO genres(name) VALUES
('Puzzle'),
('Platformer');

-- Games
INSERT INTO games(name, release_date, publisher_id, description, review_count, rating_total) VALUES
('Portal', '2007-10-10', 1, 'Set in the mysterious Aperture Science Laboratories, Portal has been called one of the most innovative new games on the horizon and will offer gamers hours of unique gameplay.', 1, 4.9);

-- Game Descriptors
INSERT INTO game_platforms VALUES
(1,1),
(1,2),
(1,3);

INSERT INTO game_genres VALUES
(1,1),
(1,2);

-- Accounts
INSERT INTO accounts(email, username, password) VALUES
('fake@mail.com', 'foobar', 'hihi');

-- Account favorites
INSERT INTO account_games VALUES
(1,1);

-- Reviews
INSERT INTO reviews(game_id, account_id, rating, title, body, likes) VALUES
(1, 1, 4.9, 'Very good game!', 'Oh my goodness this game is awesome! I love being able to go through the portals and also experience the story. I hope they make a sequel!', 0);
