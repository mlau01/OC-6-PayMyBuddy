DROP DATABASE IF EXISTS puddy_test;
CREATE DATABASE puddy_test;
USE puddy_test;


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


CREATE TABLE bank_account (
                iban VARCHAR(50) NOT NULL UNIQUE,
                owner_user_id INT NOT NULL,
                description VARCHAR(100) NOT NULL,
                PRIMARY KEY (iban)
);


CREATE TABLE versement (
                id INT AUTO_INCREMENT NOT NULL,
                bank_account_iban VARCHAR(50) NOT NULL,
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

ALTER TABLE bank_account ADD CONSTRAINT user_bankaccount_fk
FOREIGN KEY (owner_user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE billing ADD CONSTRAINT user_billing_fk
FOREIGN KEY (user_id)
REFERENCES user (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE versement ADD CONSTRAINT bankaccount_banktransfer_fk
FOREIGN KEY (bank_account_iban)
REFERENCES bank_account (iban)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE billing ADD CONSTRAINT transfer_billing_fk
FOREIGN KEY (transfer_id)
REFERENCES transfer (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;