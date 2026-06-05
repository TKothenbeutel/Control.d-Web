#Publishers
INSERT INTO publishers(id, name) VALUES
(1, "Valve");

#Platforms
INSERT INTO platforms(id, name) VALUES
(1, "PC"),
(2, "Nintendo Switch"),
(3, "Xbox 360"),
(4, "PS5");

#Genres
INSERT INTO genres(id, name) VALUES
(1, "Puzzle"),
(2, "Platformer");

#Games
INSERT INTO games(id, name, release_date, publisher_id, description, review_count, rating_total) VALUES
(1, "Portal", '2007-10-10', 1, "Portal", "Set in the mysterious Aperture Science Laboratories, Portal has been called one of the most innovative new games on the horizon and will offer gamers hours of unique gameplay.",0,0.0);

#Game Descriptors
INSERT INTO game_platforms(game_id, platform_id) VALUES
(1,1),
(1,2),
(1,3);

INSERT INTO game_genres(game_id, genre_id) VALUES
(1,1),
(1,2);

#Accounts
INSERT INTO accounts(id, email, password, username) VALUES
(1, "fake@mail.com", "hihi", "foobar");

#Reviews
INSERT INTO reviews(id, game_id, account_id, rating, title, body, likes) VALUES
(1, 1, 1, 4.9, "Very good game!", "Oh my goodness this game is awesome! I love being able to go through the portals and also experience the story. I hope they make a sequel!", 0);
