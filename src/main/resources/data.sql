DROP DATABASE IF EXISTS puddy;
CREATE DATABASE puddy;
USE puddy;


CREATE TABLE user (
                id INT AUTO_INCREMENT NOT NULL,
                first_name VARCHAR(20) NOT NULL,
                last_name VARCHAR(20) NOT NULL,
                password VARCHAR(20) NOT NULL,
                email VARCHAR(50) NOT NULL UNIQUE,
                balance DOUBLE PRECISION NOT NULL,
                currency VARCHAR(3) NOT NULL,
                PRIMARY KEY (id)
);



CREATE TABLE credit_card (
                number VARCHAR(16) NOT NULL UNIQUE,
                security_code INT NOT NULL,
                expire DATE NOT NULL,
                owner_user_id INT NOT NULL,
                description VARCHAR(100) NOT NULL,
                PRIMARY KEY (number)
);



CREATE TABLE versement (
                id INT AUTO_INCREMENT NOT NULL,
                credit_card_number VARCHAR(50) NOT NULL,
                date DATETIME NOT NULL,
                description VARCHAR(100),
                amount DOUBLE PRECISION NOT NULL,
                currency VARCHAR(3) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE transfer (
                id INT AUTO_INCREMENT NOT NULL,
                transmitter_user_id INT NOT NULL,
                recipient_user_id INT NOT NULL,
                date DATETIME NOT NULL,
                amount DOUBLE PRECISION NOT NULL,
                currency VARCHAR(3) NOT NULL,
                tax DOUBLE PRECISION NOT NULL,
                description VARCHAR(100) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE billing (
                id INT AUTO_INCREMENT NOT NULL,
                transfer_id INT NOT NULL,
                user_id INT NOT NULL,
                amount DOUBLE PRECISION NOT NULL,
                currency VARCHAR(3) NOT NULL,
                status VARCHAR(6) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE contact (
                id INT AUTO_INCREMENT NOT NULL,
                user_id INT NOT NULL,
                contact_user_id INT NOT NULL,
                PRIMARY KEY (id)
);


ALTER TABLE contact ADD CONSTRAINT user_friendship_fk
FOREIGN KEY (contact_user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE contact ADD CONSTRAINT user_friendship_fk1
FOREIGN KEY (user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE transfer ADD CONSTRAINT user_transaction_fk
FOREIGN KEY (transmitter_user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE transfer ADD CONSTRAINT user_transaction_fk1
FOREIGN KEY (recipient_user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE credit_card ADD CONSTRAINT user_bankaccount_fk
FOREIGN KEY (owner_user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE versement ADD CONSTRAINT credit_card_versement_fk
FOREIGN KEY (credit_card_number)
REFERENCES credit_card (number)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE billing ADD CONSTRAINT user_billing_fk
FOREIGN KEY (user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE billing ADD CONSTRAINT transfer_billing_fk
FOREIGN KEY (transfer_id)
REFERENCES transfer (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

INSERT INTO user (first_name, last_name, password, email, balance, currency) VALUES ("Matt", "Lau", "test", "matt.lau@gmail.com", 200, "EUR");
INSERT INTO user (first_name, last_name, password, email, balance, currency) VALUES ("Yann", "Lau", "test", "yann.lau@gmail.com", 200, "EUR");
INSERT INTO user (first_name, last_name, password, email, balance, currency) VALUES ("Herve", "Loiseau", "test", "herve.loiseau@gmail.com", 200, "EUR");
INSERT INTO user (first_name, last_name, password, email, balance, currency) VALUES ("Jean", "Joubler", "test", "jean.joubler@gmail.com", 200, "EUR");
INSERT INTO contact (user_id, contact_user_id) VALUES (1,2);
INSERT INTO contact (user_id, contact_user_id) VALUES (1,3);
INSERT INTO credit_card (number, security_code, expire, owner_user_id, description) VALUES ("1234123412341234", "123", "2021-09-00", 1, "CCP");
INSERT INTO versement (credit_card_number, date, description, amount, currency) VALUES ("1234123412341234", "2020-09-24 22:21:20", "test", 29.99, "EUR");
INSERT INTO transfer (transmitter_user_id, recipient_user_id, date, amount, currency, tax, description) VALUES (1, 2, "2021-01-22", 12, "EUR", 0.05, "test 1");
INSERT INTO transfer (transmitter_user_id, recipient_user_id, date, amount, currency, tax, description) VALUES (1, 3, "2021-01-24", 122, "EUR", 0.05, "test 2");
INSERT INTO transfer (transmitter_user_id, recipient_user_id, date, amount, currency, tax, description) VALUES (1, 2, "2021-01-25", 0.99, "EUR", 0.05, "test 3");
INSERT INTO transfer (transmitter_user_id, recipient_user_id, date, amount, currency, tax, description) VALUES (1, 3, "2021-01-26", 129, "EUR", 0.05, "test 4");
INSERT INTO transfer (transmitter_user_id, recipient_user_id, date, amount, currency, tax, description) VALUES (1, 2, "2021-01-27", 32, "EUR", 0.05, "test 5");
INSERT INTO transfer (transmitter_user_id, recipient_user_id, date, amount, currency, tax, description) VALUES (1, 3, "2021-01-28", 132, "EUR", 0.05, "test 6");
INSERT INTO transfer (transmitter_user_id, recipient_user_id, date, amount, currency, tax, description) VALUES (1, 2, "2021-01-29", 11332, "EUR", 0.05, "test 7");
