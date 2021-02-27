DROP DATABASE IF EXISTS puddy;
CREATE DATABASE puddy;
USE puddy;

CREATE TABLE user (
                id INTEGER NOT NULL AUTO_INCREMENT,
                first_name VARCHAR(20) NOT NULL,
                last_name VARCHAR(20) NOT NULL,
                password VARCHAR(20) NOT NULL,
                email VARCHAR(50) NOT NULL,
                balance DECIMAL DEFAULT 0 NOT NULL,
                currency VARCHAR(3) NOT NULL,
                CONSTRAINT user_pk PRIMARY KEY (id)
);


CREATE TABLE bankAccount (
                iban VARCHAR(50) NOT NULL,
                owner_user_id INTEGER NOT NULL,
                description VARCHAR(100) NOT NULL,
                CONSTRAINT bankAccount_pk PRIMARY KEY (iban)
);


CREATE TABLE versement (
                id INTEGER NOT NULL AUTO_INCREMENT,
                bank_account_iban VARCHAR(50) NOT NULL,
                recipient_user_id INTEGER NOT NULL,
                date TIMESTAMP NOT NULL,
                description VARCHAR(100),
                amount DECIMAL NOT NULL,
                currency VARCHAR(3) NOT NULL,
                CONSTRAINT versement_pk PRIMARY KEY (id)
);


CREATE TABLE transfer (
                id INTEGER NOT NULL AUTO_INCREMENT,
                recipient_user_id INTEGER NOT NULL,
                source_user_id INTEGER NOT NULL,
                date TIMESTAMP NOT NULL,
                amount DECIMAL NOT NULL,
                currency VARCHAR(3) NOT NULL,
                tax DECIMAL NOT NULL,
                description VARCHAR(100) NOT NULL,
                CONSTRAINT transfer_pk PRIMARY KEY (id)
);


CREATE TABLE contact (
                id INTEGER NOT NULL AUTO_INCREMENT,
                user_id INTEGER NOT NULL,
                contact_user_id INTEGER NOT NULL,
                CONSTRAINT contact_pk PRIMARY KEY (id)
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

ALTER TABLE versement ADD CONSTRAINT user_bankTransfer_fk
FOREIGN KEY (recipient_user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE bankAccount ADD CONSTRAINT user_BankAccount_fk
FOREIGN KEY (owner_user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE versement ADD CONSTRAINT BankAccount_bankTransfer_fk FOREIGN KEY (bank_account_iban) REFERENCES bankAccount (iban)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

INSERT INTO user (first_name, last_name, password, email, balance, currency) VALUES ("Matt", "Lau", "test", "matt.lau@gmail.com", 0, "EUR");