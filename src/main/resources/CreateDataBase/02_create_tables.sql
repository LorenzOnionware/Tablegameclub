USE boardgame_club;

CREATE TABLE member
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    join_date DATE NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE publisher
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE board_game
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL UNIQUE,
    release_year INT NOT NULL,
    min_player INT NOT NULL,
    max_player INT NOT NULL,
    average_play_time INT NOT NULL,
    publisher_ID INT NOT NULL,
    CONSTRAINT fk_publishers
        FOREIGN KEY (publisher_ID)
            REFERENCES publisher(ID)
);

CREATE TABLE game_night
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    location VARCHAR(200) NOT NULL
);

CREATE TABLE night_member
(
    game_night_ID INT NOT NULL,
    member_ID INT NOT NULL,
    PRIMARY KEY (game_night_ID, member_ID),
    CONSTRAINT fk_gn_member_gn
        FOREIGN KEY (game_night_ID)
            REFERENCES game_night(ID)
            ON DELETE CASCADE,
    CONSTRAINT fk_gn_member
        FOREIGN KEY (member_ID)
            REFERENCES member(ID)
            ON DELETE CASCADE
);

CREATE TABLE night_game
(
    game_night_ID INT NOT NULL,
    game_ID INT NOT NULL,
    PRIMARY KEY (game_night_ID, game_ID),
    CONSTRAINT fk_ng_gn
        FOREIGN KEY (game_night_ID)
            REFERENCES game_night(ID)
            ON DELETE CASCADE,
    CONSTRAINT fk_ng_game
        FOREIGN KEY (game_ID)
            REFERENCES board_game(ID)
            ON DELETE CASCADE
);