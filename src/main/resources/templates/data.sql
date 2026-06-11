-- Publishers
INSERT INTO publishers VALUES
(1, 'Valve');

-- Platforms
INSERT INTO platforms VALUES
(1, 'PC'),
(2, 'Nintendo Switch'),
(3, 'Xbox 360'),
(4, 'PS5');

-- Genres
INSERT INTO genres VALUES
(1, 'Puzzle'),
(2, 'Platformer');

-- Games
INSERT INTO games VALUES
(1, 'Portal', '2007-10-10', 1, 'Set in the mysterious Aperture Science Laboratories, Portal has been called one of the most innovative new games on the horizon and will offer gamers hours of unique gameplay.', 1, 4.9);

-- Game Descriptors
INSERT INTO game_platforms VALUES
(1,1),
(1,2),
(1,3);

INSERT INTO game_genres VALUES
(1,1),
(1,2);

-- Accounts
INSERT INTO accounts VALUES
(1, 'fake@mail.com', 'foobar', 'hihi');

-- Account favorites
INSERT INTO account_games VALUES
(1,1);

-- Reviews
INSERT INTO reviews VALUES
(1, 1, 1, 4.9, 'Very good game!', 'Oh my goodness this game is awesome! I love being able to go through the portals and also experience the story. I hope they make a sequel!', 0);
