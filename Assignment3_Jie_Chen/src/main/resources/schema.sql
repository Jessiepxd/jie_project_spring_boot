CREATE TABLE sec_user (
    userId BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(75) NOT NULL UNIQUE,
    encryptedPassword VARCHAR(128) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE sec_role (
    roleId BIGINT AUTO_INCREMENT PRIMARY KEY,
    roleName VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_role (
    userId BIGINT NOT NULL,
    roleId BIGINT NOT NULL,
    PRIMARY KEY (userId, roleId),
    FOREIGN KEY (userId) REFERENCES sec_user (userId),
    FOREIGN KEY (roleId) REFERENCES sec_role (roleId)
);

CREATE TABLE books (
    bookId BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
);

CREATE TABLE reviews (
    reviewId BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    bookId BIGINT NOT NULL,
    userId BIGINT NOT NULL,
    FOREIGN KEY (bookId) REFERENCES books (bookId),
    FOREIGN KEY (userId) REFERENCES sec_user (userId)
);
