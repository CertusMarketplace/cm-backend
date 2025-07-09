-- =================================================================
-- CERTUS MARKETPLACE - SCRIPT DE CREACIÓN DE BASE DE DATOS
-- Versión: 2.3 (Corregido y Optimizado)
-- =================================================================

DROP DATABASE IF EXISTS db_mk_certus;
CREATE DATABASE db_mk_certus;
USE db_mk_certus;

-- TABLA [1]: roles
CREATE TABLE roles (
                       role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       role_name VARCHAR(50) NOT NULL UNIQUE,
                       role_description TEXT
);

-- TABLA [2]: permissions
CREATE TABLE permissions (
                             permission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             permission_name VARCHAR(50) NOT NULL UNIQUE,
                             permission_description TEXT
);

-- TABLA [3]: roles_permissions (Pivote)
CREATE TABLE roles_permissions (
                                   role_permission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   id_role BIGINT,
                                   id_permission BIGINT,
                                   FOREIGN KEY (id_role) REFERENCES roles(role_id) ON DELETE CASCADE,
                                   FOREIGN KEY (id_permission) REFERENCES permissions(permission_id) ON DELETE CASCADE
);

-- TABLA [4]: users
CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       id_role BIGINT,
                       user_email VARCHAR(100) NOT NULL UNIQUE,
                       user_password VARCHAR(255) NOT NULL,
                       user_created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       user_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       user_status BOOLEAN DEFAULT TRUE,
                       FOREIGN KEY (id_role) REFERENCES roles(role_id) ON DELETE SET NULL
);

-- TABLA [5]: work_categories
CREATE TABLE work_categories (
                                 category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 category_name VARCHAR(100) NOT NULL UNIQUE,
                                 category_description TEXT,
                                 banner_image_url VARCHAR(255)
);

-- TABLA [6]: people (CORREGIDA)
CREATE TABLE people (
                        person_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        id_user BIGINT NOT NULL UNIQUE,
                        person_name VARCHAR(100) NOT NULL,
                        person_lastname VARCHAR(100) NOT NULL, -- CAMPO AÑADIDO
                        person_profile_image_url VARCHAR(255) NULL, -- CAMPO AÑADIDO
                        person_dni VARCHAR(8) UNIQUE,
                        person_mobile_phone VARCHAR(15),
                        person_gender VARCHAR(1),
                        person_institute_campus VARCHAR(100),
                        person_institutional_email VARCHAR(100) UNIQUE,
                        person_institutional_career VARCHAR(100),
                        person_current_term INT,
                        FOREIGN KEY (id_user) REFERENCES users(user_id) ON DELETE CASCADE
);

-- TABLA [7]: works (MEJORADA)
CREATE TABLE works (
                       work_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       id_seller_user BIGINT NOT NULL,
                       id_work_category BIGINT,
                       work_title VARCHAR(150) NOT NULL,
                       work_description TEXT,
                       work_price DECIMAL(10,2) NOT NULL,
                       work_image_url TEXT,
                       work_file_path TEXT,
                       work_published_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       work_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       work_status ENUM('PUBLICADO', 'EN_REVISION', 'RECHAZADO') DEFAULT 'EN_REVISION',
                       work_is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                       FOREIGN KEY (id_seller_user) REFERENCES users(user_id),
                       FOREIGN KEY (id_work_category) REFERENCES work_categories(category_id) ON DELETE SET NULL
);

CREATE TABLE work_images (
                             image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             id_work BIGINT NOT NULL,
                             image_url TEXT NOT NULL,
                             is_primary BOOLEAN DEFAULT FALSE,
                             uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (id_work) REFERENCES works(work_id) ON DELETE CASCADE
);

-- TABLA [8]: orders
CREATE TABLE orders (
                        order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        id_user BIGINT NOT NULL,
                        order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (id_user) REFERENCES users(user_id)
);

-- TABLA [9]: paypal_payments
CREATE TABLE paypal_payments (
                                 paypal_payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 id_internal_order BIGINT NOT NULL,
                                 paypal_payment_payer_email VARCHAR(255),
                                 paypal_payment_status VARCHAR(50) NOT NULL,
                                 paypal_payment_transaction_id VARCHAR(255) UNIQUE,
                                 paypal_payment_amount DECIMAL(10, 2) NOT NULL,
                                 paypal_payment_currency VARCHAR(3) NOT NULL,
                                 paypal_payment_transaction_type VARCHAR(50),
                                 paypal_payment_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 paypal_payment_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 FOREIGN KEY (id_internal_order) REFERENCES orders(order_id)
);

-- TABLA [10]: order_details

CREATE TABLE order_details (
                               order_detail_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               id_order BIGINT NOT NULL,
                               id_work BIGINT NOT NULL,
                               order_detail_quantity INT NOT NULL DEFAULT 1,
                               order_detail_unit_price DECIMAL(10, 2) NOT NULL,
                               order_details_status VARCHAR(255) NULL,
                               order_details_total_price DECIMAL(19, 2) NULL,
                               order_details_created_at DATETIME(6) NULL,
                               order_details_access_granted BIT(1) NULL,
                               FOREIGN KEY (id_order) REFERENCES orders(order_id) ON DELETE CASCADE,
                               FOREIGN KEY (id_work) REFERENCES works(work_id)
);
-- TABLA [11]: comments
CREATE TABLE comments (
                          comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          id_work BIGINT NOT NULL,
                          id_user BIGINT NOT NULL,
                          id_parent_comment BIGINT NULL,
                          comment_body TEXT NOT NULL,
                          comment_is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                          comment_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          comment_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (id_work) REFERENCES works(work_id) ON DELETE CASCADE,
                          FOREIGN KEY (id_user) REFERENCES users(user_id) ON DELETE CASCADE,
                          FOREIGN KEY (id_parent_comment) REFERENCES comments(comment_id) ON DELETE CASCADE
);

-- TABLA [12]: ratings (MEJORADA)
CREATE TABLE ratings (
                         rating_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         work_id BIGINT NOT NULL,
                         user_id BIGINT NOT NULL,
                         order_id BIGINT NULL,
                         rating_score TINYINT UNSIGNED NOT NULL CHECK (rating_score BETWEEN 1 AND 5), -- TIPO DE DATO Y RESTRICCIÓN MEJORADOS
                         rating_comment TEXT NULL,
                         rating_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (work_id) REFERENCES works(work_id) ON DELETE CASCADE,
                         FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                         FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE SET NULL
);

-- TABLA [13]: social_logins
CREATE TABLE social_logins (
                               social_login_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               id_user BIGINT NOT NULL,
                               social_login_provider VARCHAR(50) NOT NULL,
                               social_login_provider_user_id VARCHAR(255) NOT NULL,
                               social_login_linked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (id_user) REFERENCES users(user_id) ON DELETE CASCADE,
                               UNIQUE KEY uq_social_provider_user (social_login_provider, social_login_provider_user_id),
                               UNIQUE KEY uq_social_user_provider (id_user, social_login_provider)
);