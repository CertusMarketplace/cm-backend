-- --------------- CREATE DATABASE db_mk_certus -- ---------------

DROP DATABASE IF EXISTS db_mk_certus;
CREATE DATABASE db_mk_certus;
USE db_mk_certus;
-- --------------- CREATE TABLES -- ---------------

-- [1] TABLE roles
CREATE TABLE roles (
                       role_id INT AUTO_INCREMENT PRIMARY KEY,
                       role_name VARCHAR(50) NOT NULL,
                       role_description TEXT
);


-- [2] TABLE permissions
CREATE TABLE permissions (
                             permission_id INT AUTO_INCREMENT PRIMARY KEY,
                             permission_name VARCHAR(50) NOT NULL,
                             permission_description TEXT
);


-- [3] Tabla roles_permissions
CREATE TABLE roles_permissions (
                                   role_permission_id INT AUTO_INCREMENT PRIMARY KEY,
                                   id_role INT, -- FK
                                   id_permission INT, -- FK
                                   FOREIGN KEY (id_role) REFERENCES roles(role_id),
                                   FOREIGN KEY (id_permission) REFERENCES permissions(permission_id)
);


-- [4] TABLE users
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       id_role INT,-- FK
                       user_email VARCHAR(100) NOT NULL,
                       user_password VARCHAR(200) NOT NULL,
                       user_created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       user_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       user_status BOOLEAN DEFAULT TRUE,

    -- Claves foráneas:
                       FOREIGN KEY (id_role) REFERENCES roles(role_id)
);

-- Tabla career_categories
CREATE TABLE career_categories (
                                   career_category_id INT AUTO_INCREMENT PRIMARY KEY,
                                   career_category_name VARCHAR(100) NOT NULL,
                                   career_category_description TEXT
);

-- Tabla: carrers
CREATE TABLE careers (
                         career_id INT AUTO_INCREMENT PRIMARY KEY,
                         id_career_category INT, -- FK
                         career_name VARCHAR(100) NOT NULL,
                         career_description TEXT,
                         FOREIGN KEY (id_career_category) REFERENCES career_categories(career_category_id)
);

-- Tabla people
CREATE TABLE people (
                        person_id INT AUTO_INCREMENT PRIMARY KEY,
                        id_user INT, -- FK
                        id_career INT, -- FK
                        person_name VARCHAR(100) NOT NULL,
                        person_lastname VARCHAR(100) NOT NULL,
                        person_dni CHAR(8),
                        person_mobile_phone VARCHAR(15),
                        person_gender CHAR(1),
                        person_institute_location VARCHAR(100),
                        person_institutional_email VARCHAR(100),
                        person_institutional_cycle INT,
                        FOREIGN KEY (id_user) REFERENCES users(user_id),
                        FOREIGN KEY (id_career) REFERENCES careers(career_id)
);


-- [6] Tabla work_categories
CREATE TABLE work_categories (
                                 work_category_id INT AUTO_INCREMENT PRIMARY KEY,
                                 work_category_name VARCHAR(100) NOT NULL,
                                 work_category_description TEXT
);


-- [7] TABLE works
CREATE TABLE works (
                       work_id INT AUTO_INCREMENT PRIMARY KEY,
                       id_seller_user INT, -- FK
                       id_work_category INT, -- FK
                       work_title VARCHAR(150) NOT NULL,
                       work_description TEXT,
                       work_category VARCHAR(100),
                       work_price DECIMAL(10,2),
                       work_is_deleted BOOLEAN,
                       work_image_url TEXT, -- ruta de lxs img/archivo
                       work_published_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       work_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       work_status ENUM('PUBLICADO', 'EN REVISIÓN', 'RECHAZADO') DEFAULT 'en revision',


    -- Foreign keys :
                       FOREIGN KEY (id_seller_user) REFERENCES users(user_id),
                       FOREIGN KEY (id_work_category) REFERENCES work_categories(work_category_id)
);

-- [8] TABLE orders
CREATE TABLE orders (
                        order_id INT AUTO_INCREMENT PRIMARY KEY,
                        id_user INT NOT NULL, -- FK
                        FOREIGN KEY (id_user) REFERENCES users(user_id)
);

-- [9] TABLE paypal_payments
CREATE TABLE paypal_payments (
                                 paypal_payment_id INT AUTO_INCREMENT PRIMARY KEY,
                                 id_internal_order INT NOT NULL, -- FK
                                 paypal_payment_payer_email VARCHAR(255) NULL,
                                 paypal_payment_status VARCHAR(50) NOT NULL,
                                 paypal_payment_transaction_id VARCHAR(255) NULL UNIQUE,
                                 paypal_payment_amount DECIMAL(10, 2) NOT NULL,
                                 paypal_payment_currency CHAR(3) NOT NULL,
                                 paypal_payment_transaction_type VARCHAR(50) NOT NULL,
                                 paypal_payment_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 paypal_payment_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Foreign keys :
                                 FOREIGN KEY (id_internal_order) REFERENCES orders(order_id)
);


-- [10] TABLE order_details
CREATE TABLE order_details (
                               order_details_id INT AUTO_INCREMENT PRIMARY KEY,
                               id_order INT NOT NULL, -- FK
                               id_work INT NOT NULL, -- FK
                               order_details_status VARCHAR(50) NOT NULL,
                               order_details_quantity INT NOT NULL DEFAULT 1,
                               order_details_total_price DECIMAL(10, 2) NOT NULL,
                               order_details_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               order_details_access_granted BOOLEAN NULL,
                               FOREIGN KEY (id_order) REFERENCES orders(order_id),
                               FOREIGN KEY (id_work) REFERENCES works(work_id)
);

-- [11] TABLE seller_payments
CREATE TABLE seller_payments (
                                 seller_payment_id INT AUTO_INCREMENT PRIMARY KEY,
                                 seller_paypal_payment INT NULL, -- FK
                                 seller_id INT NOT NULL, -- FK
                                 seller_amount_received DECIMAL(10, 2) NOT NULL,
                                 seller_payment_status VARCHAR(50) NOT NULL,
                                 FOREIGN KEY (seller_paypal_payment) REFERENCES paypal_payments(paypal_payment_id),
                                 FOREIGN KEY (seller_id) REFERENCES users(user_id)
);


-- [12] TABLE comments
CREATE TABLE comments (
                          comment_id INT AUTO_INCREMENT PRIMARY KEY,
                          comment_work_id INT NOT NULL, -- FK
                          comment_user_id INT NOT NULL, -- FK
                          comment_parent_id INT NULL, -- FK
                          comment_body TEXT NOT NULL,
                          comment_is_deleted BOOLEAN,
                          comment_status VARCHAR(50) DEFAULT 'approved',
                          comment_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          comment_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (comment_work_id) REFERENCES works(work_id) ON DELETE CASCADE,
                          FOREIGN KEY (comment_user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                          FOREIGN KEY (comment_parent_id) REFERENCES comments(comment_id) ON DELETE CASCADE
);

-- [13] TABLE ratings
CREATE TABLE ratings (
                         rating_id INT AUTO_INCREMENT PRIMARY KEY,
                         work_id INT NOT NULL, -- FK
                         user_id INT NOT NULL, -- FK
                         order_id INT NULL, -- FK
                         rating_score TINYINT UNSIGNED NOT NULL CHECK (rating_score BETWEEN 1 AND 5),
                         rating_comment TEXT NULL,
                         rating_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (work_id) REFERENCES works(work_id) ON DELETE CASCADE,
                         FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                         FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE SET NULL
);

-- [14] TABLE split_payments
CREATE TABLE split_payments (
                                split_payment_id INT AUTO_INCREMENT PRIMARY KEY,
                                id_seller INT NOT NULL, -- FK
                                id_paypal_payment INT NULL, -- FK
                                split_amount DECIMAL(10, 2) NOT NULL,
                                split_payment_status VARCHAR(50) NOT NULL,
                                split_payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                commission_percentage DECIMAL(5, 2) NULL,
                                FOREIGN KEY (id_seller) REFERENCES users(user_id),
                                FOREIGN KEY (id_paypal_payment) REFERENCES paypal_payments(paypal_payment_id)
);


-- [15] TABLE social_logins
CREATE TABLE social_logins (
                               social_login_id INT AUTO_INCREMENT PRIMARY KEY,
                               id_social_login_user INT NOT NULL, -- FK
                               social_login_provider VARCHAR(50) NOT NULL,
                               social_login_provider_user_id VARCHAR(255) NOT NULL,
                               social_login_linked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (id_social_login_user) REFERENCES users(user_id) ON DELETE CASCADE,
                               UNIQUE KEY uq_social_provider_user (social_login_provider, id_social_login_user),
                               UNIQUE KEY uq_social_user_provider (id_social_login_user, social_login_provider)
);



-- [16] TABLE user_roles
CREATE TABLE user_roles (
                            id_user INT NOT NULL, -- FK
                            id_role INT NOT NULL, -- FK
                            PRIMARY KEY (id_user, id_role),
                            FOREIGN KEY (id_user) REFERENCES users(user_id) ON DELETE CASCADE,
                            FOREIGN KEY (id_role) REFERENCES roles(role_id) ON DELETE CASCADE
);