DROP DATABASE IF EXISTS puddy;
CREATE DATABASE puddy;
USE puddy;



CREATE TABLE user (
                id INT AUTO_INCREMENT NOT NULL,
                first_name VARCHAR(20) NOT NULL,
                last_name VARCHAR(20) NOT NULL,
                password VARCHAR(20) NOT NULL,
                email VARCHAR(50) NOT NULL,
                balance DECIMAL DEFAULT 0 NOT NULL,
                currency VARCHAR(3) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE bank_account (
                iban VARCHAR(50) NOT NULL,
                owner_user_id INT NOT NULL,
                description VARCHAR(100) NOT NULL,
                PRIMARY KEY (iban)
);


CREATE TABLE versement (
                id INT AUTO_INCREMENT NOT NULL,
                bank_account_iban VARCHAR(50) NOT NULL,
                date DATETIME NOT NULL,
                description VARCHAR(100),
                amount DECIMAL NOT NULL,
                currency VARCHAR(3) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE transfer (
                id INT AUTO_INCREMENT NOT NULL,
                recipient_user_id INT NOT NULL,
                source_user_id INT NOT NULL,
                date DATETIME NOT NULL,
                amount DECIMAL NOT NULL,
                currency VARCHAR(3) NOT NULL,
                tax DECIMAL NOT NULL,
                description VARCHAR(100) NOT NULL,
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
FOREIGN KEY (source_user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE transfer ADD CONSTRAINT user_transaction_fk1
FOREIGN KEY (recipient_user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE bank_account ADD CONSTRAINT user_bankaccount_fk
FOREIGN KEY (owner_user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE versement ADD CONSTRAINT bankaccount_banktransfer_fk
FOREIGN KEY (bank_account_iban)
REFERENCES bank_account (iban)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

INSERT INTO user (first_name, last_name, password, email, balance, currency) VALUES ("Matt", "Lau", "test", "matt.lau@gmail.com", 0, "EUR");
INSERT INTO user (first_name, last_name, password, email, balance, currency) VALUES ("Yann", "Lau", "test", "yann.lau@gmail.com", 0, "EUR");
INSERT INTO contact (user_id, contact_user_id) VALUES (1,2);