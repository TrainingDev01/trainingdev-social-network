
-- INSERT IN USERR

INSERT INTO userr (id, name, birthday, email, password, gender, profile_photo, cover_photo, modification_date, creation_date)
VALUES (100, 'Santiago Lozano', '2000-02-15', 'santtiagolozano@gmail.com', 'password123', 'MALE', 'profile_photo.png', 'cover_photo.png', '2024-06-29 00:00:00', '2024-06-29 00:00:00');


-- INSERT IN POST

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (1, 100, 'PUBLIC', 'HAPPY', 'This is the first post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (2, 100, 'PUBLIC', 'HAPPY', 'This is the second post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (3, 100, 'PUBLIC', null, 'This is the third post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (4, 100, 'PUBLIC', null, 'This is the fourth post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (5, 100, 'PUBLIC', null, 'This is the fifth post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');



-- INSERT IN COMMENT
INSERT INTO comment (id, user_id, post_id, description, image,  modification_date, creation_date)
VALUES (1, 100, 1, 'This is a comment for post 1 by user 100', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO comment (id, user_id, post_id, description, image,  modification_date, creation_date)
VALUES (2, 100, 1, 'This is a comment for post 1 by user 100', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO comment (id, user_id, post_id, description, image,  modification_date, creation_date)
VALUES (3, 100, 2, 'This is a comment for post 2 by user 100', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');