drop table if exists hangman_game;


CREATE TABLE hangman_game_type (
    hangman_game_id INT AUTO_INCREMENT PRIMARY KEY,
    phrase_id Int,
    session_id INT
);

drop table if exists hangman_game_move;

CREATE TABLE hangman_game_move (
    hangman_game_move_id INT AUTO_INCREMENT PRIMARY KEY,
	hangman_game_id INT,
    guess_letter VARCHAR(5),
    session_id INT,
	guess_index INT
);