
-- [ROLES TABLE]
INSERT INTO roles (role_name, role_description) VALUES ('Administrator', 'Full access to platform management.');
INSERT INTO roles (role_name, role_description) VALUES ('Seller', 'User who can post and sell jobs.');
INSERT INTO roles (role_name, role_description) VALUES ('Buyer', 'User who can search for and buy jobs.');

-- [PERMISSIONS TABLE]
-- User Management
INSERT INTO permissions (permission_name, permission_description) VALUES ('user:view_list', 'View list of users');
INSERT INTO permissions (permission_name, permission_description) VALUES ('user:view_profile', 'View profile of a specific user');
INSERT INTO permissions (permission_name, permission_description) VALUES ('user:edit_profile_any', 'Edit any user''s profile');
INSERT INTO permissions (permission_name, permission_description) VALUES ('user:edit_profile_own', 'Edit their own profile');
INSERT INTO permissions (permission_name, permission_description) VALUES ('user:delete', 'Delete a user');
INSERT INTO permissions (permission_name, permission_description) VALUES ('user:ban', 'Suspend/Block a user');
INSERT INTO permissions (permission_name, permission_description) VALUES ('user:assign_role', 'Assign/Change roles to users');
INSERT INTO permissions (permission_name, permission_description) VALUES ('user:view_roles', 'View existing roles');
INSERT INTO permissions (permission_name, permission_description) VALUES ('user:manage_roles', 'Create, edit, delete roles');
INSERT INTO permissions (permission_name, permission_description) VALUES ('user:view_permissions', 'View existing permissions');

-- Order Management
INSERT INTO permissions (permission_name, permission_description) VALUES ('order:view_list_all', 'View list of all orders');
INSERT INTO permissions (permission_name, permission_description) VALUES ('order:view_list_own', 'View list of their own purchases - for buyers');
INSERT INTO permissions (permission_name, permission_description) VALUES ('order:view_list_seller', 'View list of orders for their own jobs - for sellers');
INSERT INTO permissions (permission_name, permission_description) VALUES ('order:view_details', 'View full details of a specific order - admin, support');
INSERT INTO permissions (permission_name, permission_description) VALUES ('order:update_status', 'Change the status of an order - admin, support');
INSERT INTO permissions (permission_name, permission_description) VALUES ('order:process_refund', 'Initiate or process a refund');
INSERT INTO permissions (permission_name, permission_description) VALUES ('order:view_payment_details', 'View details of the associated payment transaction');

-- Payment Management
INSERT INTO permissions (permission_name, permission_description) VALUES ('payment:view_transactions', 'View transaction history from PayPal/gateway');
INSERT INTO permissions (permission_name, permission_description) VALUES ('payout:request', 'Allow sellers to request payment for their earnings');
INSERT INTO permissions (permission_name, permission_description) VALUES ('payout:process', 'Process/Approve payments to sellers');
INSERT INTO permissions (permission_name, permission_description) VALUES ('payout:view_history_own', 'View their own received payment history - seller');
INSERT INTO permissions (permission_name, permission_description) VALUES ('payout:view_history_all', 'View all seller payment history - admin');
INSERT INTO permissions (permission_name, permission_description) VALUES ('commission:view_report', 'View platform commission reports');
INSERT INTO permissions (permission_name, permission_description) VALUES ('finance:view_summary', 'View general financial summaries');

-- Comment Management
INSERT INTO permissions (permission_name, permission_description) VALUES ('comment:create', 'Post a comment or reply on a job page');
INSERT INTO permissions (permission_name, permission_description) VALUES ('comment:edit_own', 'Edit their own comments');
INSERT INTO permissions (permission_name, permission_description) VALUES ('comment:delete_own', 'Delete their own comments');
INSERT INTO permissions (permission_name, permission_description) VALUES ('comment:moderate', 'Moderate comments: approve, edit, delete any - admin/support');

-- Rating Management
INSERT INTO permissions (permission_name, permission_description) VALUES ('rating:create', 'Leave a rating/review on a purchased job');
INSERT INTO permissions (permission_name, permission_description) VALUES ('rating:reply', 'Allow seller to respond to a received rating');
INSERT INTO permissions (permission_name, permission_description) VALUES ('rating:edit_own', 'Edit their own ratings - consider if allowed');
INSERT INTO permissions (permission_name, permission_description) VALUES ('rating:delete_own', 'Delete their own ratings - consider if allowed');
INSERT INTO permissions (permission_name, permission_description) VALUES ('rating:moderate', 'Moderate ratings: approve, edit, delete any - admin/support');


-- [ROLE PERMISSIONS TABLE]
-- Assign all permissions to the 'Administrator' role (role_id = 1)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 1); -- user:view_list (View list of users)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 2); -- user:view_profile (View profile of a specific user)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 3); -- user:edit_profile_any (Edit any user's profile)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 4); -- user:edit_profile_own (Edit their own profile)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 5); -- user:delete (Delete a user)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 6); -- user:ban (Suspend/Block a user)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 7); -- user:assign_role (Assign/Change roles to users)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 8); -- user:view_roles (View existing roles)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 9); -- user:manage_roles (Create, edit, delete roles)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 10); -- user:view_permissions (View existing permissions)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 11); -- order:view_list_all (View list of all orders)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 12); -- order:view_list_own (View list of their own purchases)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 13); -- order:view_list_seller (View list of orders for their own jobs)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 14); -- order:view_details (View full details of a specific order)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 15); -- order:update_status (Change the status of an order)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 16); -- order:process_refund (Initiate or process a refund)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 17); -- order:view_payment_details (View details of the associated payment transaction)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 18); -- payment:view_transactions (View transaction history)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 19); -- payout:request (Allow sellers to request payout)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 20); -- payout:process (Process/Approve payouts)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 21); -- payout:view_history_own (View own payout history)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 22); -- payout:view_history_all (View all seller payout history)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 23); -- commission:view_report (View commission reports)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 24); -- finance:view_summary (View financial summaries)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 25); -- comment:create (Post a comment)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 26); -- comment:edit_own (Edit own comments)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 27); -- comment:delete_own (Delete own comments)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 28); -- comment:moderate (Moderate comments)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 29); -- rating:create (Leave a rating/review)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 30); -- rating:reply (Allow seller to respond to rating)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 31); -- rating:edit_own (Edit own ratings)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 32); -- rating:delete_own (Delete own ratings)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (1, 33); -- rating:moderate (Moderate ratings)

-- Assign permissions to the 'Seller' role (role_id = 2)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (2, 4); -- user:edit_profile_own (Edit their own profile)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (2, 13); -- order:view_list_seller (View list of orders for their own jobs)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (2, 19); -- payout:request (Allow sellers to request payout)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (2, 21); -- payout:view_history_own (View their own received payment history)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (2, 25); -- comment:create (Post a comment)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (2, 26); -- comment:edit_own (Edit their own comments)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (2, 27); -- comment:delete_own (Delete their own comments)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (2, 30); -- rating:reply (Allow seller to respond to a rating)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (2, 31); -- rating:edit_own (Edit their own ratings - consider if allowed)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (2, 32); -- rating:delete_own (Delete their own ratings - consider if allowed)


-- Assign permissions to the 'Buyer' role (role_id = 3)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (3, 4); -- user:edit_profile_own (Edit their own profile)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (3, 12); -- order:view_list_own (View list of their own purchases)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (3, 25); -- comment:create (Post a comment)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (3, 26); -- comment:edit_own (Edit their own comments)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (3, 27); -- comment:delete_own (Delete their own comments)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (3, 29); -- rating:create (Leave a rating/review)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (3, 31); -- rating:edit_own (Edit their own ratings - consider if allowed)
INSERT INTO roles_permissions (id_role, id_permission) VALUES (3, 32); -- rating:delete_own (Delete their own ratings - consider if allowed)


-- [USERS TABLE]

-- Administrator (role_id = 1, must be @certus.edu.pe - DNI email)
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (1, '10203040@certus.edu.pe', '$2a$10$jK7yGgZpQvXwYcCbZaNc.eQfR0s1T2u3V4i5O6p7A8b9C0dE1fG2hI3j4k5L6m7N8o9P0qR', TRUE);

-- Sellers (role_id = 2, must be @certus.edu.pe - DNI email)
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (2, '45678901@certus.edu.pe', 'pbkdf2:sha256:150000$xyzABC123$e0c7d4a1b8f3c6e9d2a5b8c7d4e1f3a6b9c8d0e3f1a2b5c8d9e0f7g6h5i4j3k2', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (2, '78901234@certus.edu.pe', '$2b$12$hI8jKlMnO7pQrStUvWxYz.eDcEfGhIjKlMnOpQrStUvWxYzAbCdEfGhIjKlMnOpQrStUvWxYzA', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (2, '23456789@certus.edu.pe', 'pbkdf2:sha256:150000$UVW45678$9f8e7d6c5b4a3210fedcba9876543210abcdef0123456789abcdef0123456789ab', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (2, '56789012@certus.edu.pe', '$2a$10$AbCdEfGhIjKlMnO7pQrSt.uVwXyZ0aBcDeFgHiJkLmNoPqRsTuVwXyZ0aBcDeFgHiJkLmN', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (2, '89012345@certus.edu.pe', 'pbkdf2:sha256:150000$ZaYxWvUT$c8d9e0f7g6h5i4j3k210fedcba9876543210abcdef0123456789abcdef0123456789', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (2, '34567890@certus.edu.pe', '$2b$12$QvXwYcCbZaNcEeQfRgSh.iJkLlMnO7pQrStUvWxYzAbCdEfGhIjKlMnOpQrStUvWxYzAbC', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (2, '67890123@certus.edu.pe', 'pbkdf2:sha256:150000$NOP90123$10fedcba9876543210abcdef0123456789abcdef0123456789abcdef0123456789ab', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (2, '90123456@certus.edu.pe', '$2a$10$uVwXyZ0aBcDeFgHiJkLm.NoPqRsTuVwXyZ0aBcDeFgHiJkLmNoPqRsTuVwXyZ0aBcDeF', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (2, '11223344@certus.edu.pe', 'pbkdf2:sha256:150000$DEF67890$210abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789', TRUE);

-- Buyers (role_id = 3, must be @gmail, @hotmail, or @outlook - Real name email)
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (3, 'carlosgomez@gmail.com', '$2b$12$ZaNcEeQfRgShIjKlMnO.7pQrStUvWxYzAbCdEfGhIjKlMnOpQrStUvWxYzAbCdEfG', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (3, 'lauradiaz@hotmail.com', 'pbkdf2:sha256:150000$GHI12345$3210abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (3, 'pedrolopez@outlook.com', '$2a$10$iJkLlMnO7pQrStUvWxYz.AbCdEfGhIjKlMnOpQrStUvWxYz0aBcDeFgHiJkLmNoPq', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (3, 'sofiafernandez@gmail.com', 'pbkdf2:sha256:150000$JKL67890$43210abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (3, 'javierrios@gmail.com', '$2b$12$MnO7pQrStUvWxYzAbCdE.fGhIjKlMnOpQrStUvWxYz0aBcDeFgHiJkLmNoPqRsTuV', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (3, 'elenavargas@hotmail.com', 'pbkdf2:sha256:150000$MNO12345$543210abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (3, 'mariapaz@gmail.com', '$2a$10$OpQrStUvWxYzAbCdEfGh.IjKlMnOpQrStUvWxYz0aBcDeFgHiJkLmNoPqRsTuVwXy', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (3, 'josesilva@outlook.com', 'pbkdf2:sha256:150000$PQR67890$6543210abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (3, 'sofiadiaz@gmail.com', '$2b$12$StUvWxYzAbCdEfGhIjKl.MnOpQrStUvWxYz0aBcDeFgHiJkLmNoPqRsTuVwXyZ0aB', TRUE);
INSERT INTO users (id_role, user_email, user_password, user_status) VALUES (3, 'miguellopez@hotmail.com', 'pbkdf2:sha256:150000$STU12345$76543210abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789', TRUE);


-- [CAREER CATEGORIES TABLE]
INSERT INTO career_categories (career_category_name, career_category_description) VALUES ('Negocios', 'Carreras enfocadas en la administración, gestión y marketing.');
INSERT INTO career_categories (career_category_name, career_category_description) VALUES ('Finanzas', 'Carreras relacionadas con contabilidad, tributación y finanzas.');
INSERT INTO career_categories (career_category_name, career_category_description) VALUES ('Tecnología', 'Carreras en diseño, desarrollo y administración de sistemas.');
INSERT INTO career_categories (career_category_name, career_category_description) VALUES ('Creatividad', 'Carreras de diseño y comunicación visual.');


-- [CAREERS TABLE]
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (1, 'Administración de empresas', 'Gestión integral de organizaciones.');
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (1, 'Administración de negocios internacionales', 'Gestión de operaciones comerciales globales.');
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (1, 'Marketing y medios digitales', 'Estrategias de mercado y comunicación digital.');
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (1, 'Administración y gestión comercial', 'Enfoque en ventas y dirección comercial.');
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (1, 'Administración y recursos humanos', 'Gestión del talento humano.');
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (2, 'Contabilidad y tributación', 'Registro financiero y cumplimiento fiscal.');
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (2, 'Administración bancaria y financiera', 'Gestión de productos y servicios financieros.');
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (3, 'Diseño y desarrollo de software', 'Creación de aplicaciones y sistemas.');
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (3, 'Administración de sistemas', 'Gestión y mantenimiento de infraestructuras tecnológicas.');
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (4, 'Publicidad', 'Creación y gestión de campañas publicitarias.');
INSERT INTO careers (id_career_category, career_name, career_description) VALUES (4, 'Diseño gráfico', 'Creación de comunicación visual.');


-- [PEOPLE TABLE]
-- User 1 (Admin, Certus): DNI email in users table, complete Certus data in people
INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (1, 1, 'Juan', 'Pérez Rodríguez', '10203040', '987654321', 'M', 'Lima', '10203040@certus.edu.pe', 1); -- Assigned Career ID 1 (Negocios) and Cycle 1 for Admin

-- Users 2-10 (Sellers, Certus): DNI email in users table, complete Certus data
-- Careers based on the work types (Diseño Gráfico=11, Desarrollo Software=8, Admin Sistemas=9, Publicidad=10)
INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (2, 11, 'María', 'García Fernández', '45678901', '912345678', 'F', 'Lima', '45678901@certus.edu.pe', 5); -- Career: Diseño Gráfico (ID 11)

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (3, 8, 'Juan', 'Martínez López', '78901234', '923456789', 'M', 'Arequipa', '78901234@certus.edu.pe', 6); -- Career: Diseño y desarrollo de software (ID 8)

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (4, 11, 'Ana', 'Sánchez Ruiz', '23456789', '934567890', 'F', 'Lima', '23456789@certus.edu.pe', 4); -- Career: Diseño Gráfico (ID 11)

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (5, 9, 'Luis', 'González Hernández', '56789012', '945678901', 'M', 'Lima', '56789012@certus.edu.pe', 5); -- Career: Administración de sistemas (ID 9)

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (6, 8, 'Sofía', 'Jiménez Diaz', '89012345', '956789012', 'F', 'Arequipa', '89012345@certus.edu.pe', 6); -- Career: Diseño y desarrollo de software (ID 8)

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (7, 10, 'Diego', 'Álvarez Moreno', '34567890', '967890123', 'M', 'Lima', '34567890@certus.edu.pe', 5); -- Career: Publicidad (ID 10)

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (8, 11, 'Camila', 'Torres Navarro', '67890123', '978901234', 'F', 'Arequipa', '67890123@certus.edu.pe', 4); -- Career: Diseño Gráfico (ID 11)

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (9, 8, 'Gabriel', 'Ríos Castillo', '90123456', '989012345', 'M', 'Lima', '90123456@certus.edu.pe', 6); -- Career: Diseño y desarrollo de software (ID 8)

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (10, 9, 'Valeria', 'Vargas Medina', '11223344', '901234567', 'F', 'Arequipa', '11223344@certus.edu.pe', 5); -- Career: Administración de sistemas (ID 9)

-- Users 11-20 (Buyers, non-Certus): Public domain emails in users table, assigning placeholder/fictional data to institutional fields
INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (11, 1, 'Carlos', 'Gómez Silva', '99111111', '911111111', 'M', 'No Aplica', 'no.institucional.11@example.com', 0);

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (12, 1, 'Laura', 'Díaz Torres', '99222222', '922222222', 'F', 'No Aplica', 'no.institucional.12@example.com', 0);

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (13, 1, 'Pedro', 'López Navarro', '99333333', '933333333', 'M', 'No Aplica', 'no.institucional.13@example.com', 0);

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (14, 1, 'Sofía', 'Fernández Castillo', '99444444', '944444444', 'F', 'No Aplica', 'no.institucional.14@example.com', 0);

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (15, 1, 'Javier', 'Ríos Moreno', '99555555', '955555555', 'M', 'No Aplica', 'no.institucional.15@example.com', 0);

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (16, 1, 'Elena', 'Vargas Ortiz', '99666666', '966666666', 'F', 'No Aplica', 'no.institucional.16@example.com', 0);

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (17, 1, 'María', 'Paz Soto', '99777777', '977777777', 'F', 'No Aplica', 'no.institucional.17@example.com', 0);

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (18, 1, 'José', 'Silva Mendoza', '99888888', '988888888', 'M', 'No Aplica', 'no.institucional.18@example.com', 0);

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (19, 1, 'Sofía', 'Díaz Rojas', '99999999', '999999999', 'F', 'No Aplica', 'no.institucional.19@example.com', 0);

INSERT INTO people (id_user, id_career, person_name, person_lastname, person_dni, person_mobile_phone, person_gender, person_institute_location, person_institutional_email, person_institutional_cycle)
VALUES (20, 1, 'Miguel', 'López Cáceres', '99000000', '900000000', 'M', 'No Aplica', 'no.institucional.20@example.com', 0);

-- [WORK CATEGORIES TABLE]
INSERT INTO work_categories (work_category_name, work_category_description) VALUES ('Diseño', 'Servicios relacionados con diseño gráfico y visual.');
INSERT INTO work_categories (work_category_name, work_category_description) VALUES ('Ilustración', 'Servicios de creación de ilustraciones digitales o tradicionales.');
INSERT INTO work_categories (work_category_name, work_category_description) VALUES ('Desarrollo de Software', 'Servicios de programación y desarrollo de aplicaciones o sistemas.');

-- [WORKS TABLE]
INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (2, 1, 'Diseño de Logo Profesional', 'Creación de identidad visual para tu marca, con archivos fuente.', 'Diseño', 80.00, FALSE, '/uploads/works/seller_2/logo_design.jpg', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (3, 1, 'Packaging Design para Productos', 'Diseño atractivo y funcional para empaques y etiquetas.', 'Diseño', 150.50, FALSE, '/uploads/works/seller_3/packaging_mockup.png', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (4, 1, 'Diseño de Interfaz de Usuario (UI)', 'Diseño de interfaces intuitivas y estéticas para apps/web.', 'Diseño', 200.00, FALSE, '/uploads/works/seller_4/ui_design.jpg', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (5, 1, 'Diseño de Post para Redes Sociales', 'Diseños creativos para tus campañas en Instagram, Facebook, etc.', 'Diseño', 25.00, FALSE, '/uploads/works/seller_5/social_media_post.png', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (6, 1, 'Brand Guide Completa', 'Creación de manual de marca detallado, incluyendo usos y aplicaciones.', 'Diseño', 300.00, FALSE, '/uploads/works/seller_6/brand_guide.pdf', 'EN REVISIÓN');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (2, 1, 'Diseño de Brochure Corporativo', 'Material de marketing profesional en formato digital o para impresión.', 'Diseño', 120.00, FALSE, '/uploads/works/seller_2/brochure.jpg', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (4, 1, 'Rediseño de Sitio Web (Visual)', 'Mejora estética y de usabilidad de un sitio web existente.', 'Diseño', 450.00, FALSE, '/uploads/works/seller_4/website_redesign.jpg', 'PUBLICADO');


-- Illustration Works (id_work_category = 2) - 7 works
INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (7, 2, 'Ilustración Digital Personalizada', 'Arte digital a medida para cualquier propósito: banners, artículos, etc.', 'Ilustración', 90.00, FALSE, '/uploads/works/seller_7/digital_illustration.png', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (8, 2, 'Character Design', 'Diseño de personajes únicos y expresivos para tu proyecto animado o juego.', 'Ilustración', 180.00, FALSE, '/uploads/works/seller_8/character_sheet.jpg', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (9, 2, 'Ilustración para Libros Infantiles', 'Imágenes coloridas y atractivas que capturan la imaginación de los niños.', 'Ilustración', 250.00, FALSE, '/uploads/works/seller_9/kids_book_illustration.jpg', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (10, 2, 'Retrato Ilustrado', 'Convierte tu foto en una hermosa ilustración digital o con estilo artístico.', 'Ilustración', 70.00, FALSE, '/uploads/works/seller_10/illustrated_portrait.png', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (7, 2, 'Concept Art para Videojuegos', 'Creación visual de mundos, personajes y elementos para tu juego.', 'Ilustración', 350.00, FALSE, '/uploads/works/seller_7/concept_art.jpg', 'EN REVISIÓN');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (3, 2, 'Ilustración Rechazada (Ejemplo)', 'Esta ilustración fue revisada pero no cumplió con los estándares.', 'Ilustración', 0.00, FALSE, '/uploads/works/seller_3/rejected_illustration.jpg', 'RECHAZADO'); -- Example rejected work

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (8, 2, 'Ilustración Editorial para Artículo', 'Ilustraciones temáticas para acompañar artículos de blog o revistas.', 'Ilustración', 110.00, FALSE, '/uploads/works/seller_8/editorial_illustration.jpg', 'PUBLICADO');


-- Software Development Works (id_work_category = 3) - 6 works
INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (2, 3, 'Desarrollo Web Frontend Básico', 'Creación de landing page responsive o sitio web simple con HTML/CSS/JS.', 'Desarrollo de Software', 500.00, FALSE, '/uploads/works/seller_2/web_frontend.zip', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (3, 3, 'Scripting de Automatización en Python', 'Pequeños scripts para automatizar tareas repetitivas.', 'Desarrollo de Software', 100.00, FALSE, '/uploads/works/seller_3/python_script.py', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (4, 3, 'Plugin para WordPress Personalizado', 'Desarrollo de funcionalidades específicas y a medida para tu sitio WP.', 'Desarrollo de Software', 400.00, FALSE, '/uploads/works/seller_4/wp_plugin.zip', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (5, 3, 'Desarrollo de API RESTful Simple', 'Creación de un servicio web básico para gestionar datos.', 'Desarrollo de Software', 600.00, FALSE, '/uploads/works/seller_5/api_rest.zip', 'PUBLICADO');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (10, 3, 'Optimización de Base de Datos SQL', 'Mejora de rendimiento de consultas y estructura de base de datos.', 'Desarrollo de Software', 180.00, FALSE, '/uploads/works/seller_10/db_optimization.sql', 'EN REVISIÓN');

INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (6, 3, 'Desarrollo de E-commerce Básico', 'Implementación de una tienda online sencilla con carrito y pagos.', 'Desarrollo de Software', 1200.00, FALSE, '/uploads/works/seller_6/ecommerce_basic.zip', 'PUBLICADO');


-- Example Deleted Work (status might be irrelevant if deleted)
INSERT INTO works (id_seller_user, id_work_category, work_title, work_description, work_category, work_price, work_is_deleted, work_image_url, work_status)
VALUES (5, 1, 'Diseño Eliminado (Ejemplo)', 'Este diseño fue marcado como eliminado y no está visible públicamente.', 'Diseño', 100.00, TRUE, '/uploads/works/seller_5/deleted_design.jpg', 'PUBLICADO');

-- [ORDERS TABLE]
INSERT INTO orders (id_user) VALUES (11); -- carlosgomez@gmail.com
INSERT INTO orders (id_user) VALUES (12); -- lauradiaz@hotmail.com
INSERT INTO orders (id_user) VALUES (13); -- pedrolopez@outlook.com
INSERT INTO orders (id_user) VALUES (14); -- sofiafernandez@gmail.com
INSERT INTO orders (id_user) VALUES (15); -- javierrios@gmail.com
INSERT INTO orders (id_user) VALUES (16); -- elenavargas@hotmail.com
INSERT INTO orders (id_user) VALUES (17); -- mariapaz@gmail.com
INSERT INTO orders (id_user) VALUES (18); -- josesilva@outlook.com
INSERT INTO orders (id_user) VALUES (19); -- sofiadiaz@gmail.com
INSERT INTO orders (id_user) VALUES (20); -- miguellopez@hotmail.com
INSERT INTO orders (id_user) VALUES (11); -- Another order by Carlos
INSERT INTO orders (id_user) VALUES (13); -- Another order by Pedro
INSERT INTO orders (id_user) VALUES (15); -- Another order by Javier
INSERT INTO orders (id_user) VALUES (17); -- Another order by Maria
INSERT INTO orders (id_user) VALUES (19); -- Another order by Sofia D.
INSERT INTO orders (id_user) VALUES (12); -- Another order by Laura
INSERT INTO orders (id_user) VALUES (14); -- Another order by Sofia F.
INSERT INTO orders (id_user) VALUES (16); -- Another order by Elena
INSERT INTO orders (id_user) VALUES (18); -- Another order by Jose
INSERT INTO orders (id_user) VALUES (20); -- Another order by Miguel


-- [PAYPAL PAYMENTS TABLE]
-- Order 1 -> User 11 (carlosgomez@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (1, 'carlosgomez_paypal@example.com', 'COMPLETED', 'PAY-ABC1D2E3F4G5H6I7J8K9L0M1N2O3P4Q5', 80.00, 'PEN', 'Sale');

-- Order 2 -> User 12 (lauradiaz@hotmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (2, 'lauradiaz_paypal@example.com', 'COMPLETED', 'PAY-RST6U7V8W9X0Y1Z2A3B4C5D6E7F8G9H0', 150.50, 'PEN', 'Sale');

-- Order 3 -> User 13 (pedrolopez@outlook.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (3, 'pedrolopez_paypal@example.com', 'COMPLETED', 'PAY-JKL1M2N3O4P5Q6R7S8T9U0V1W2X3Y4Z5', 200.00, 'PEN', 'Sale');

-- Order 4 -> User 14 (sofiafernandez@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (4, 'sofiaf_paypal@example.com', 'COMPLETED', 'PAY-ABCDE67890FGH01234IJKL567890MNO', 25.00, 'PEN', 'Sale');

-- Order 5 -> User 15 (javierrios@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (5, 'javierrios_paypal@example.com', 'COMPLETED', 'PAY-PQRST12345UVWXY67890ZABCDE01234', 300.00, 'PEN', 'Sale');

-- Order 6 -> User 16 (elenavargas@hotmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (6, 'elenav_paypal@example.com', 'COMPLETED', 'PAY-FGHJI56789KLMNO12345PQRST67890', 120.00, 'PEN', 'Sale');

-- Order 7 -> User 17 (mariapaz@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (7, 'mariapaz_paypal@example.com', 'COMPLETED', 'PAY-UVWXY01234ZABCD56789EFGHI01234', 90.00, 'PEN', 'Sale');

-- Order 8 -> User 18 (josesilva@outlook.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (8, 'josesilva_paypal@example.com', 'COMPLETED', 'PAY-JKLMN56789OPQRS01234TUVWZ56789', 180.00, 'PEN', 'Sale');

-- Order 9 -> User 19 (sofiadiaz@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (9, 'sofiadiaz_paypal@example.com', 'COMPLETED', 'PAY-ABCDEFG12345HIJKL67890MNO P012', 250.00, 'PEN', 'Sale');

-- Order 10 -> User 20 (miguellopez@hotmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (10, 'miguell_paypal@example.com', 'COMPLETED', 'PAY-QRSTUVW34567XYZAB89012CDEF45678', 70.00, 'PEN', 'Sale');

-- Order 11 -> User 11 (carlosgomez@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (11, 'carlosgomez_paypal@example.com', 'COMPLETED', 'PAY-GHIJKL90123MNO P45678QRSTU9012', 450.00, 'PEN', 'Sale');

-- Order 12 -> User 13 (pedrolopez@outlook.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (12, 'pedrolopez_paypal@example.com', 'PENDING', 'PAY-VWXYZ34567ABCDE89012FGHIJ34567', 110.00, 'PEN', 'Sale'); -- Example pending payment

-- Order 13 -> User 15 (javierrios@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (13, 'javierrios_paypal@example.com', 'COMPLETED', 'PAY-KLMNO78901PQRST23456UVWXY78901', 500.00, 'PEN', 'Sale');

-- Order 14 -> User 17 (mariapaz@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (14, 'mariapaz_paypal@example.com', 'COMPLETED', 'PAY-ZABCD23456EFGHI78901JKLMNOP234', 100.00, 'PEN', 'Sale');

-- Order 15 -> User 19 (sofiadiaz@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (15, 'sofiadiaz_paypal@example.com', 'COMPLETED', 'PAY-QRSTU78901VWXYZ23456ABCDE78901', 400.00, 'PEN', 'Sale');

-- Order 16 -> User 12 (lauradiaz@hotmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (16, 'lauradiaz_paypal@example.com', 'COMPLETED', 'PAY-FGHIJ23456KLMNO78901PQRST23456', 600.00, 'PEN', 'Sale');

-- Order 17 -> User 14 (sofiafernandez@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (17, 'sofiaf_paypal@example.com', 'COMPLETED', 'PAY-UVWXY78901ZABCD23456EFGHI78901', 800.00, 'PEN', 'Sale');

-- Order 18 -> User 16 (elenavargas@hotmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (18, 'elenav_paypal@example.com', 'COMPLETED', 'PAY-JKLMN23456OPQRS78901TUVWZ23456', 50.00, 'PEN', 'Sale');

-- Order 19 -> User 18 (josesilva@outlook.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (19, 'josesilva_paypal@example.com', 'REFUNDED', 'PAY-ABCDE78901FGHJI23456KLMNO78901', 180.00, 'PEN', 'Sale'); -- Example order that was refunded
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (19, 'josesilva_paypal@example.com', 'COMPLETED', 'REF-PQRST23456UVWXY78901ZABCD23456', 180.00, 'PEN', 'Refund'); -- Corresponding refund transaction

-- Order 20 -> User 20 (miguellopez@hotmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (20, 'miguell_paypal@example.com', 'COMPLETED', 'PAY-EFGHI78901JKLMN23456OPQRS78901', 1200.00, 'PEN', 'Sale');

-- One additional payment for Order 1 to reach 20 total payments, maybe a partial refund example?
-- Order 1 -> User 11 (carlosgomez@gmail.com)
INSERT INTO paypal_payments (id_internal_order, paypal_payment_payer_email, paypal_payment_status, paypal_payment_transaction_id, paypal_payment_amount, paypal_payment_currency, paypal_payment_transaction_type)
VALUES (1, 'carlosgomez_paypal@example.com', 'COMPLETED', 'REF-TUVWZ23456ABCDE78901FGHIJ23456', 20.00, 'PEN', 'Refund'); -- Example partial refund


-- [ORDERS DETAILS TABLE]
INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (1, 1, 'COMPLETED', 1, 80.00, TRUE); -- Order 1, Work 1 (Logo Design, $80)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (2, 3, 'COMPLETED', 1, 200.00, TRUE); -- Order 2, Work 3 (UI Design, $200)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (3, 8, 'COMPLETED', 1, 180.00, TRUE); -- Order 3, Work 8 (Character Design, $180)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (4, 10, 'COMPLETED', 2, 140.00, TRUE); -- Order 4, Work 10 (Illustrated Portrait, $70 * 2)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (5, 13, 'COMPLETED', 1, 500.00, TRUE); -- Order 5, Work 13 (Frontend Basic, $500)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (6, 15, 'COMPLETED', 1, 400.00, TRUE); -- Order 6, Work 15 (WP Plugin, $400)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (7, 17, 'COMPLETED', 1, 800.00, TRUE); -- Order 7, Work 17 (Mobile App Prototype, $800)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (8, 2, 'COMPLETED', 1, 150.50, TRUE); -- Order 8, Work 2 (Packaging Design, $150.50)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (9, 4, 'COMPLETED', 5, 125.00, TRUE); -- Order 9, Work 4 (Social Media Post, $25 * 5)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (10, 7, 'COMPLETED', 1, 450.00, TRUE); -- Order 10, Work 7 (Website Redesign, $450)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (11, 12, 'COMPLETED', 1, 110.00, TRUE); -- Order 11, Work 12 (Editorial Illustration, $110)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (12, 14, 'PENDING', 1, 100.00, FALSE); -- Order 12, Work 14 (Python Script, $100) - Payment was PENDING

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (13, 16, 'COMPLETED', 1, 600.00, TRUE); -- Order 13, Work 16 (API RESTful, $600)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (14, 18, 'COMPLETED', 3, 150.00, TRUE); -- Order 14, Work 18 (Bug Fixing, $50 * 3)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (15, 9, 'COMPLETED', 1, 250.00, TRUE); -- Order 15, Work 9 (Kids Book Illustration, $250)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (16, 1, 'COMPLETED', 1, 80.00, TRUE); -- Order 16, Work 1 (Logo Design, $80) - Work 1 purchased again

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (17, 3, 'CANCELLED', 1, 200.00, FALSE); -- Order 17, Work 3 (UI Design, $200) - Example cancelled detail

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (18, 8, 'PENDING', 1, 180.00, FALSE); -- Order 18, Work 8 (Character Design, $180)

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (19, 19, 'REFUNDED', 1, 180.00, FALSE); -- Order 19, Work 19 (DB Optimization, $180) - Linked to the refunded payment

INSERT INTO order_details (id_order, id_work,  order_details_status,  order_details_quantity,  order_details_total_price,  order_details_access_granted)
VALUES (20, 20, 'COMPLETED', 1, 1200.00, TRUE); -- Order 20, Work 20 (Ecommerce Basic, $1200)


-- [ORDER DETAILS TABLE]
INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (1, 1, 'COMPLETED', 1, 80.00, TRUE); -- Order 1, Work 1 (Logo Design, $80)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (2, 3, 'COMPLETED', 1, 200.00, TRUE); -- Order 2, Work 3 (UI Design, $200)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (3, 8, 'COMPLETED', 1, 180.00, TRUE); -- Order 3, Work 8 (Character Design, $180)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (4, 10, 'COMPLETED', 2, 140.00, TRUE); -- Order 4, Work 10 (Illustrated Portrait, $70 * 2)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (5, 13, 'COMPLETED', 1, 500.00, TRUE); -- Order 5, Work 13 (Frontend Basic, $500)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (6, 15, 'COMPLETED', 1, 400.00, TRUE); -- Order 6, Work 15 (WP Plugin, $400)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (7, 17, 'COMPLETED', 1, 800.00, TRUE); -- Order 7, Work 17 (Mobile App Prototype, $800)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (8, 2, 'COMPLETED', 1, 150.50, TRUE); -- Order 8, Work 2 (Packaging Design, $150.50)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (9, 4, 'COMPLETED', 5, 125.00, TRUE); -- Order 9, Work 4 (Social Media Post, $25 * 5)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (10, 7, 'COMPLETED', 1, 450.00, TRUE); -- Order 10, Work 7 (Website Redesign, $450)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (11, 12, 'COMPLETED', 1, 110.00, TRUE); -- Order 11, Work 12 (Editorial Illustration, $110)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (12, 14, 'PENDING', 1, 100.00, FALSE); -- Order 12, Work 14 (Python Script, $100) - Payment was PENDING

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (13, 16, 'COMPLETED', 1, 600.00, TRUE); -- Order 13, Work 16 (API RESTful, $600)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (14, 18, 'COMPLETED', 3, 150.00, TRUE); -- Order 14, Work 18 (Bug Fixing, $50 * 3)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (15, 9, 'COMPLETED', 1, 250.00, TRUE); -- Order 15, Work 9 (Kids Book Illustration, $250)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (16, 1, 'COMPLETED', 1, 80.00, TRUE); -- Order 16, Work 1 (Logo Design, $80) - Work 1 purchased again

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (17, 3, 'CANCELLED', 1, 200.00, FALSE); -- Order 17, Work 3 (UI Design, $200) - Example cancelled detail

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (18, 8, 'PENDING', 1, 180.00, FALSE); -- Order 18, Work 8 (Character Design, $180)

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (19, 19, 'REFUNDED', 1, 180.00, FALSE); -- Order 19, Work 19 (DB Optimization, $180) - Linked to the refunded payment

INSERT INTO order_details (id_order, id_work, order_details_status, order_details_quantity, order_details_total_price, order_details_access_granted)
VALUES (20, 20, 'COMPLETED', 1, 1200.00, TRUE); -- Order 20, Work 20 (Ecommerce Basic, $1200)


-- [SELLER PAYMENTS TABLE]
INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 2, 50.00, 'REQUESTED'); -- Payout requested by seller 2

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 3, 120.50, 'PROCESSED'); -- Payout processed for seller 3

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 4, 85.00, 'PROCESSED'); -- Payout processed for seller 4

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 5, 30.00, 'REQUESTED'); -- Payout requested by seller 5

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 6, 200.00, 'PROCESSING'); -- Payout processing for seller 6

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 7, 75.00, 'PROCESSED'); -- Payout processed for seller 7

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 8, 150.00, 'PROCESSED'); -- Payout processed for seller 8

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 9, 210.00, 'REQUESTED'); -- Payout requested by seller 9

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 10, 60.00, 'PROCESSED'); -- Payout processed for seller 10

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 2, 90.00, 'PROCESSED'); -- Another payout for seller 2

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 3, 180.00, 'PROCESSING'); -- Another payout processing for seller 3

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 4, 110.00, 'PROCESSED'); -- Another payout for seller 4

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 5, 45.00, 'PROCESSED'); -- Another payout for seller 5

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 6, 250.00, 'PROCESSED'); -- Another payout for seller 6

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 7, 100.00, 'REQUESTED'); -- Another payout requested by seller 7

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 8, 130.00, 'PROCESSED'); -- Another payout for seller 8

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 9, 170.00, 'PROCESSED'); -- Another payout for seller 9

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 10, 80.00, 'REQUESTED'); -- Another payout requested by seller 10

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 2, 150.00, 'PROCESSING'); -- Another payout processing for seller 2

INSERT INTO seller_payments (seller_paypal_payment, seller_id, seller_amount_received, seller_payment_status)
VALUES (NULL, 3, 65.00, 'PROCESSED'); -- Another payout for seller 3


-- [COMMENTS TABLE]
INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (1, 11, NULL, 'Excelente trabajo de diseño de logo, muy profesional.', FALSE, 'approved'); -- Comment on Work 1 by Buyer 11

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (3, 12, NULL, 'La interfaz de usuario se ve genial, ¿es adaptable a móvil?', FALSE, 'approved'); -- Comment on Work 3 by Buyer 12

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (8, 13, NULL, 'Me encanta el estilo de diseño de personajes.', FALSE, 'approved'); -- Comment on Work 8 by Buyer 13

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (13, 14, NULL, 'Interesado en el desarrollo frontend. ¿Qué tecnologías usas?', FALSE, 'approved'); -- Comment on Work 13 by Buyer 14

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (17, 15, NULL, 'El prototipo de app móvil se ve prometedor.', FALSE, 'approved'); -- Comment on Work 17 by Buyer 15

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (20, 16, NULL, 'Excelente servicio de desarrollo e-commerce.', FALSE, 'approved'); -- Comment on Work 20 by Buyer 16

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (2, 17, NULL, 'Buen packaging, ¿ofreces impresión también?', FALSE, 'approved'); -- Comment on Work 2 by Buyer 17

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (4, 18, NULL, 'Diseños para redes sociales muy creativos.', FALSE, 'approved'); -- Comment on Work 4 by Buyer 18

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (9, 19, NULL, 'Las ilustraciones infantiles son adorables.', FALSE, 'approved'); -- Comment on Work 9 by Buyer 19

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (14, 20, NULL, 'Útil script de Python. ¿Tienes más ejemplos?', FALSE, 'approved'); -- Comment on Work 14 by Buyer 20

-- Replies to existing comments (comment_parent_id references a comment_id above)
-- Assuming comment_id increments from 1 for these inserts

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (1, 2, 1, '¡Muchas gracias por tu comentario! Me alegra que te guste mi trabajo.', FALSE, 'approved'); -- Seller 2 replies to comment 1 on Work 1

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (3, 4, 2, 'Sí, todos mis diseños UI son completamente responsive.', FALSE, 'approved'); -- Seller 4 replies to comment 2 on Work 3

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (8, 8, 3, 'Gracias! Pongo mucho esfuerzo en el diseño de personajes.', FALSE, 'approved'); -- Seller 8 replies to comment 3 on Work 8

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (13, 2, 4, 'Principalmente uso HTML, CSS, y JavaScript (React/Vue).', FALSE, 'approved'); -- Seller 2 replies to comment 4 on Work 13

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (17, 7, 5, 'Estamos trabajando en la versión completa. ¡Mantente atento!', FALSE, 'approved'); -- Seller 7 replies to comment 5 on Work 17 (assuming seller 7 is owner of work 17)

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (20, 6, 6, 'Gracias por tu feedback. La idea es ofrecer una solución completa.', FALSE, 'approved'); -- Seller 6 replies to comment 6 on Work 20

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (2, 3, 7, 'No, solo ofrezco el diseño del arte final listo para imprimir.', FALSE, 'approved'); -- Seller 3 replies to comment 7 on Work 2

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (4, 5, 8, '¡Gracias! Siempre busco ideas frescas para mis posts.', FALSE, 'approved'); -- Seller 5 replies to comment 8 on Work 4

-- Examples with different status or deleted
INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (1, 11, 1, 'Tengo otra pregunta sobre la entrega.', FALSE, 'pending'); -- Another comment on Work 1, Status Pending

INSERT INTO comments (comment_work_id, comment_user_id, comment_parent_id, comment_body, comment_is_deleted, comment_status)
VALUES (8, 1, NULL, 'Este comentario es spam. Debe ser eliminado.', TRUE, 'rejected'); -- Example deleted and rejected comment by Admin (user_id 1)


-- [RATINGS TABLE]
INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (1, 11, 1, 5, 'Excelente diseño de logo, justo lo que necesitaba.'); -- Rating for Work 1 by User 11 (Order 1)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (3, 12, 2, 4, 'La interfaz se ve muy bien, fácil de usar.'); -- Rating for Work 3 by User 12 (Order 2)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (8, 13, 3, 5, 'Diseño de personaje fantástico, muy detallado.'); -- Rating for Work 8 by User 13 (Order 3)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (10, 14, 4, 4, 'Retrato ilustrado muy bonito, buena calidad.'); -- Rating for Work 10 by User 14 (Order 4)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (13, 15, 5, 5, 'Rápido y eficiente desarrollo frontend.'); -- Rating for Work 13 by User 15 (Order 5)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (15, 16, 6, 4, 'Plugin de WordPress funcional, cumple lo prometido.'); -- Rating for Work 15 by User 16 (Order 6)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (17, 17, 7, 3, 'El prototipo es básico, pero muestra la idea.'); -- Rating for Work 17 by User 17 (Order 7)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (2, 18, 8, 5, 'Diseño de packaging muy creativo y original.'); -- Rating for Work 2 by User 18 (Order 8)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (4, 19, 9, 5, 'Excelentes posts para redes, muy llamativos.'); -- Rating for Work 4 by User 19 (Order 9)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (7, 20, 10, 4, 'Buen rediseño visual del sitio, aunque tomó tiempo.'); -- Rating for Work 7 by User 20 (Order 10)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (12, 11, 11, 5, 'Ilustración editorial perfecta para mi artículo.'); -- Rating for Work 12 by User 11 (Order 11)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (14, 13, 12, 2, 'El script de Python funcionó, pero necesité hacer ajustes.'); -- Rating for Work 14 by User 13 (Order 12)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (16, 15, 13, 4, 'La API RESTful es simple, pero eficaz para mi necesidad.'); -- Rating for Work 16 by User 15 (Order 13)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (18, 17, 14, 5, 'Corrección de bugs muy rápida y efectiva.'); -- Rating for Work 18 by User 17 (Order 14)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (9, 19, 15, 5, 'Las ilustraciones infantiles son justo lo que quería, muy bonitas.'); -- Rating for Work 9 by User 19 (Order 15), comment not NULL

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (1, 18, 16, 4, NULL); -- Rating for Work 1 by User 18 (Order 16), no comment

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (3, 20, 17, 1, 'Cancelé el pedido, no puedo valorar el trabajo.'); -- Rating for Work 3 by User 20 (Order 17 was CANCELLED in order_details)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (8, 11, 18, 3, 'El diseño está bien, pero la comunicación fue lenta.'); -- Rating for Work 8 by User 11 (Order 18 was PENDING)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (19, 13, 19, 1, 'Hubo un problema con la optimización, tuve que pedir reembolso.'); -- Rating for Work 19 by User 13 (Order 19 was REFUNDED)

INSERT INTO ratings (work_id, user_id, order_id, rating_score, rating_comment)
VALUES (20, 15, 20, 5, 'La tienda online funciona perfecto, muy recomendado.'); -- Rating for Work 20 by User 15 (Order 20)


-- [SPLIT PAYMENTS TABLE]
INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (2, 1, 68.00, 'CALCULATED', 15.00); -- From PayPal 1 (Order 1, Work 1 by Seller 2)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (4, 2, 127.93, 'CALCULATED', 15.00); -- From PayPal 2 (Order 2, Work 3 by Seller 4)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (8, 3, 170.00, 'CALCULATED', 15.00); -- From PayPal 3 (Order 3, Work 8 by Seller 8)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (10, 4, 21.25, 'CALCULATED', 15.00); -- From PayPal 4 (Order 4, Work 10 by Seller 10)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (2, 5, 255.00, 'CALCULATED', 15.00); -- From PayPal 5 (Order 5, Work 13 by Seller 2)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (4, 6, 102.00, 'CALCULATED', 15.00); -- From PayPal 6 (Order 6, Work 15 by Seller 4)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (7, 7, 76.50, 'CALCULATED', 15.00); -- From PayPal 7 (Order 7, Work 17 by Seller 7)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (3, 8, 153.00, 'CALCULATED', 15.00); -- From PayPal 8 (Order 8, Work 2 by Seller 3)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (5, 9, 212.50, 'CALCULATED', 15.00); -- From PayPal 9 (Order 9, Work 4 by Seller 5)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (4, 10, 59.50, 'CALCULATED', 15.00); -- From PayPal 10 (Order 10, Work 7 by Seller 4)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (8, 11, 382.50, 'CALCULATED', 15.00); -- From PayPal 11 (Order 11, Work 12 by Seller 8)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (3, 12, 93.50, 'PENDING_PAYMENT', 15.00); -- From PayPal 12 (Order 12, Work 14 by Seller 3 - PayPal status was PENDING)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (5, 13, 425.00, 'CALCULATED', 15.00); -- From PayPal 13 (Order 13, Work 16 by Seller 5)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (9, 14, 85.00, 'CALCULATED', 15.00); -- From PayPal 14 (Order 14, Work 18 by Seller 9)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (9, 15, 340.00, 'CALCULATED', 15.00); -- From PayPal 15 (Order 15, Work 9 by Seller 9)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (2, 16, 510.00, 'CALCULATED', 15.00); -- From PayPal 16 (Order 16, Work 1 by Seller 2)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (4, 17, 680.00, 'CALCULATED', 15.00); -- From PayPal 17 (Order 17, Work 3 by Seller 4)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (8, 18, 42.50, 'CALCULATED', 15.00); -- From PayPal 18 (Order 18, Work 8 by Seller 8)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (10, 19, 153.00, 'REFUNDED_SOURCE', 15.00); -- From PayPal 19 (Sale, Order 19, Work 19 by Seller 10 - Order was refunded)

INSERT INTO split_payments (id_seller, id_paypal_payment, split_amount, split_payment_status, commission_percentage)
VALUES (6, 20, 1020.00, 'CALCULATED', 15.00); -- From PayPal 20 (Order 20, Work 20 by Seller 6)

-- [SOCIAL LOGINS TABLE]
INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (1, 'google', 'google_user_id_10001'); -- User 1 (Admin) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (2, 'google', 'google_user_id_10002'); -- User 2 (Seller) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (3, 'google', 'google_user_id_10003'); -- User 3 (Seller) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (4, 'google', 'google_user_id_10004'); -- User 4 (Seller) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (5, 'google', 'google_user_id_10005'); -- User 5 (Seller) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (6, 'google', 'google_user_id_10006'); -- User 6 (Seller) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (7, 'google', 'google_user_id_10007'); -- User 7 (Seller) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (8, 'google', 'google_user_id_10008'); -- User 8 (Seller) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (9, 'google', 'google_user_id_10009'); -- User 9 (Seller) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (10, 'google', 'google_user_id_10010'); -- User 10 (Seller) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (11, 'google', 'google_user_id_10011'); -- User 11 (Buyer) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (12, 'google', 'google_user_id_10012'); -- User 12 (Buyer) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (13, 'google', 'google_user_id_10013'); -- User 13 (Buyer) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (14, 'google', 'google_user_id_10014'); -- User 14 (Buyer) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (15, 'google', 'google_user_id_10015'); -- User 15 (Buyer) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (16, 'google', 'google_user_id_10016'); -- User 16 (Buyer) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (17, 'google', 'google_user_id_10017'); -- User 17 (Buyer) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (18, 'google', 'google_user_id_10018'); -- User 18 (Buyer) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (19, 'google', 'google_user_id_10019'); -- User 19 (Buyer) with Google login

INSERT INTO social_logins (id_social_login_user, social_login_provider, social_login_provider_user_id)
VALUES (20, 'google', 'google_user_id_10020'); -- User 20 (Buyer) with Google login


-- [USER ROLES]
-- User 1 (Administrator) gets Role 1 (Administrator)
INSERT INTO user_roles (id_user, id_role) VALUES (1, 1);

-- Users 2-10 (Sellers) get Role 2 (Seller) - 9 users
INSERT INTO user_roles (id_user, id_role) VALUES (2, 2);
INSERT INTO user_roles (id_user, id_role) VALUES (3, 2);
INSERT INTO user_roles (id_user, id_role) VALUES (4, 2);
INSERT INTO user_roles (id_user, id_role) VALUES (5, 2);
INSERT INTO user_roles (id_user, id_role) VALUES (6, 2);
INSERT INTO user_roles (id_user, id_role) VALUES (7, 2);
INSERT INTO user_roles (id_user, id_role) VALUES (8, 2);
INSERT INTO user_roles (id_user, id_role) VALUES (9, 2);
INSERT INTO user_roles (id_user, id_role) VALUES (10, 2);