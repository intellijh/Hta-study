CREATE TABLE delete_file
(
    board_file VARCHAR2(50),
    reg_date   DATE DEFAULT SYSDATE
);

CREATE OR REPLACE TRIGGER save_delete_file
    AFTER UPDATE OR DELETE
    ON board
    FOR EACH ROW
BEGIN
    IF (:old.board_file IS NOT NULL) THEN
        IF (:old.board_file != :new.board_file OR :new.board_file IS NULL) THEN
            INSERT INTO delete_file(board_file)
            VALUES (:old.board_file);
        end if;
    end if;
end;
