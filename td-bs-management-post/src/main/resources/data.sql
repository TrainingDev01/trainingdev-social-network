
-- INSERT IN USERR

INSERT INTO userr (id, name, birthday, email, password, gender, profile_photo, cover_photo, modification_date, creation_date)
VALUES (100, 'Santiago Lozano', '2000-02-15', 'santtiagolozano@gmail.com', 'password123', 'MALE', 'profile_photo.png', 'cover_photo.png', '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO userr (id, name, birthday, email, password, gender, profile_photo, cover_photo, modification_date, creation_date)
VALUES (200, 'Omar Perez', '2000-02-15', 'Omar@gmail.com', 'password123', 'MALE', 'profile_photo.png', 'cover_photo.png', '2024-06-29 00:00:00', '2024-06-29 00:00:00');

-- INSERT IN POST

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (100, 100, 'PUBLIC', 'HAPPY', 'This is the first post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (200, 100, 'PUBLIC', 'HAPPY', 'This is the second post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (300, 100, 'PUBLIC', null, 'This is the third post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (400, 100, 'PUBLIC', null, 'This is the fourth post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (500, 200, 'PUBLIC', null, 'This is the fifth post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (600, 200, 'PUBLIC', null, 'This is the fifth post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');

INSERT INTO post (id, user_id, type, feeling, description, image, modification_date, creation_date)
VALUES (700, 200, 'PUBLIC', null, 'This is the fifth post', null, '2024-06-29 00:00:00', '2024-06-29 00:00:00');


-- INSERT IN RELATIONSHIP
INSERT INTO relationship (id, sender_user, receptor_user, type, state, creation_date, modification_date)
VALUES (100, 100, 200, 'FRIENDSHIP', 'ACCEPTED', '2024-09-30 00:00:00', '2024-09-30 00:00:00');

