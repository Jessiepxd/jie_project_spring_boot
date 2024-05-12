-- Users
INSERT INTO sec_user (email, encryptedPassword, enabled) VALUES ('user1@example.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', TRUE);
INSERT INTO sec_user (email, encryptedPassword, enabled) VALUES ('user2@example.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', TRUE);
INSERT INTO sec_user (email, encryptedPassword, enabled) VALUES ('admin@example.com', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', TRUE);

-- Roles
INSERT INTO sec_role (roleName) VALUES ('ROLE_USER');
INSERT INTO sec_role (roleName) VALUES ('ROLE_ADMIN');

-- User Roles
INSERT INTO user_role (userId, roleId) VALUES (1, 1); -- User1 as USER
INSERT INTO user_role (userId, roleId) VALUES (2, 1); -- User2 as USER
INSERT INTO user_role (userId, roleId) VALUES (3, 2); -- Admin as ADMIN

-- Books
INSERT INTO books (title, author) VALUES ('The 7 Habits of Highly Effective People', 'Stephen R. Covey');
INSERT INTO books (title, author) VALUES ('The Martian', 'Andy Weir');

-- Reviews
INSERT INTO reviews (content, bookId, userId) VALUES ('A transformative guide that provides insightful strategies for personal and professional growth.', 1, 1);
INSERT INTO reviews (content, bookId, userId) VALUES ('A great science fiction bok about an astronaut stranded on Mars.', 2, 2);
