INSERT INTO items VALUES(2, "other", "Something else", 12345);
ALTER TABLE bosses ADD COLUMN regen INTEGER;

INSERT INTO bosses VALUES(null, "Other One", 200, 500, 10);
UPDATE bosses SET regen = 10;