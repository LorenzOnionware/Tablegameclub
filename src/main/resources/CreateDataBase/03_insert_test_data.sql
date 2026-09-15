USE boardgame_club;
INSERT INTO publisher (name) VALUES
                                  ('KOSMOS'),
                                  ('Catan Studio'),
                                  ('Days of Wonder'),
                                  ('Asmodee');
INSERT INTO member (first_name, last_name, email, join_date) VALUES
                                                                  ('Anna',     'Meier',    'anna.meier@example.com',    '2024-03-15'),
                                                                  ('Ben',      'Keller',   'ben.keller@example.com',    '2024-05-02'),
                                                                  ('Carolin',  'Schmidt',  'carolin.schmidt@example.com','2024-06-20'),
                                                                  ('David',    'Brunner',  'david.brunner@example.com', '2024-09-01'),
                                                                  ('Elena',    'Rossi',    'elena.rossi@example.com',   '2025-01-10'),
                                                                  ('Felix',    'Baumann',  'felix.baumann@example.com', '2025-02-14'),
                                                                  ('Gabriela', 'Wyss',     'gabriela.wyss@example.com', '2025-04-01'),
                                                                  ('Henrik',   'Larsen',   'henrik.larsen@example.com', '2025-07-19'),
                                                                  ('Isabel',   'Frei',     'isabel.frei@example.com',   '2025-10-30'),
                                                                  ('Jonas',    'Steiner',  'jonas.steiner@example.com', '2026-08-25');

INSERT INTO board_game (title, release_year, min_player, max_playeR, average_play_time, publisher_ID) VALUES
                                                                                                                      ('Catan',                 1995, 3, 4,  90, 2),
                                                                                                                      ('Carcassonne',           2000, 2, 5,  45, 1),
                                                                                                                      ('Ticket to Ride',        2004, 2, 5,  60, 3),
                                                                                                                      ('Azul',                  2017, 2, 4,  40, 3),
                                                                                                                      ('Splendor',              2014, 2, 4,  30, 1),
                                                                                                                      ('7 Wonders',             2010, 2, 7,  30, 4),
                                                                                                                      ('Pandemic',              2008, 2, 4,  45, 4),
                                                                                                                      ('Codenames',             2015, 4, 8,  15, 2),
                                                                                                                      ('Dominion',              2008, 2, 4,  30, 1),
                                                                                                                      ('Terraforming Mars',     2016, 1, 5, 120, 2);
INSERT INTO game_night (date, start_time, location) VALUES
                                                        ('2025-06-05', '18:30:00', 'Vereinshaus, Raum 1'),
                                                        ('2025-06-19', '18:30:00', 'Vereinshaus, Raum 1'),
                                                        ('2025-07-03', '19:00:00', 'Café Zentral'),
                                                        ('2025-07-17', '18:30:00', 'Vereinshaus, Raum 2'),
                                                        ('2025-08-07', '18:00:00', 'Park bei gutem Wetter / Vereinshaus'),
                                                        ('2025-09-04', '18:30:00', 'Vereinshaus, Raum 1'),
                                                        ('2025-10-02', '18:30:00', 'Vereinshaus, Raum 1'),
                                                        ('2025-11-06', '18:30:00', 'Bibliothek, Gruppenraum'),
                                                        ('2025-12-11', '17:00:00', 'Weihnachtsfeier, Saal'),
                                                        ('2026-01-08', '18:30:00', 'Vereinshaus, Raum 1');

INSERT INTO night_member (game_night_ID, member_ID) VALUES
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6),
(3, 4), (3, 5),
(4, 1), (4, 8), (4, 9),
(5, 2), (5, 7),
(6, 6), (6, 1), (6, 3),
(7, 2), (7, 3),
(8, 4),
(9, 1), (9, 2), (9, 3), (9, 4), (9, 5), (9, 6), (9, 8), (9, 9),
(10, 1), (10, 6), (10, 9);

INSERT INTO night_game (game_night_ID, game_ID) VALUES
(1, 1), (1, 2), (1, 3),
(2, 2), (2, 5), (2, 6),
(3, 4),
(4, 10), (4, 4),
(5, 7),
(6, 8), (6, 9),
(7, 1), (7, 10),
(9, 3), (9, 4), (9, 6), (9, 8);