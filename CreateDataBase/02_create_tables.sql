USE boardgame_club;
CREATE TABLE members
(
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50)  NOT NULL,
    email     VARCHAR(100) NOT NULL UNIQUE,
    join_date DATE         NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE publishers
(
    ID   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE board_games
(

    game_id              INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
    title                varchar(100) NOT NULL UNIQUE,
    release_year         INT         NOT NULL,
    minplayercount       INT          NOT NULL,
    maxplayercount       INT          NOT NULL,
    average_playing_time INT          NOT NULL,
    publisher_ID         INT NOT NULL,
    CONSTRAINT fk_publishers
        FOREIGN KEY (publisher_ID)
            REFERENCES publishers (ID)
);
CREATE TABLE game_nights
(
    ID        INT AUTO_INCREMENT PRIMARY KEY,
    date      DATE         not null,
    starttime TIME         NOT NULL,
    location  VARCHAR(200) NOT NULL
);
CREATE TABLE night_members(
    gamenight_id INT NOT NULL,
    member_id INT NOT NULL,
    PRIMARY KEY(gamenight_id, member_id),
    CONSTRAINT fk_gn_member_gn FOREIGN KEY (gamenight_id) REFERENCES  game_nights(ID)
                          ON DELETE CASCADE,
    CONSTRAINT fk_gn_member  FOREIGN KEY (member_id) REFERENCES members(member_id)
        ON DELETE CASCADE
);
CREATE TABLE night_games
(
    gamenight_id INT NOT NULL,
    game_id      INT NOT NULL,
    PRIMARY KEY (gamenight_id, game_id),
    CONSTRAINT fk_ng_gn FOREIGN KEY (gamenight_id)
        REFERENCES game_nights (ID) ON DELETE CASCADE,
    CONSTRAINT fk_ng_game FOREIGN KEY (game_id)
        REFERENCES board_games (game_id) ON DELETE CASCADE
);