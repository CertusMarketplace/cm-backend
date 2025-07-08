-- =================================================================
-- CERTUS MARKETPLACE - SCRIPT DE INSERCIÓN DE DATOS
-- Versión: 2.3 (Corregido y Optimizado)
-- =================================================================
USE db_mk_certus;

-- PASO 1: Tablas maestras sin dependencias
INSERT IGNORE INTO roles (role_id, role_name, role_description) VALUES
(1, 'Administrator', 'Acceso total a la gestión de la plataforma.'),
(2, 'Seller', 'Usuario que puede publicar y vender trabajos.'),
(3, 'Buyer', 'Usuario que puede buscar y comprar trabajos.');

INSERT IGNORE INTO work_categories (category_id, category_name, category_description, banner_image_url) VALUES 
(1, 'Software', 'Soluciones digitales que transforman tu mundo, hechas a tu medida.', '/img/works/banner-software.jpg'),
(2, 'Cursos', 'Aprende y domina nuevas habilidades con nuestros cursos especializados.', '/img/works/banner-cursos.jpg'),
(3, 'Música y Audio', 'Encuentra pistas, efectos y recursos de audio para tus creaciones.', '/img/works/banner-music.jpg'),
(4, 'Ilustraciones', 'Descubre arte digital único y personalizable para tus proyectos.', '/img/works/banner-ilustraciones.jpg');

-- PASO 2: Inserción de usuarios con su rol
INSERT IGNORE INTO users (user_id, id_role, user_email, user_password, user_status) VALUES 
(1, 1, 'admin@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(2, 2, 'regina.perez@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(3, 2, 'lucia.ramos@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(4, 2, 'jose.ramirez@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(5, 2, 'javier.pena@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(6, 2, 'hugo.medina@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(7, 2, 'cesar.acuna@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(8, 2, 'elena.rivas@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(9, 2, 'enrique.penanieto@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(10, 2, 'vendedor.diez@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(11, 2, 'vendedor.once@certus.edu.pe', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(12, 3, 'comprador.uno@gmail.com', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(13, 3, 'comprador.dos@hotmail.com', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(14, 3, 'comprador.tres@outlook.com', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(15, 3, 'comprador.cuatro@gmail.com', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(16, 3, 'comprador.cinco@gmail.com', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(17, 3, 'comprador.seis@hotmail.com', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(18, 3, 'comprador.siete@gmail.com', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(19, 3, 'comprador.ocho@outlook.com', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(20, 3, 'comprador.nueve@gmail.com', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE),
(21, 3, 'comprador.diez@hotmail.com', '$2a$10$E.qfR0s1T2u3V4i5O6p7A.AbCdEfGhIjKlMnO7pQrStU', TRUE);

-- PASO 3: Inserción de perfiles de personas (CORREGIDO)
INSERT IGNORE INTO people (id_user, person_name, person_lastname, person_profile_image_url) VALUES 
(1, 'Admin', 'Certus', '/img/profiles/admin.png'),
(2, 'Regina', 'Perez', '/img/profiles/regina_perez.png'),
(3, 'Lucía', 'Ramos', '/img/profiles/lucia_ramos.png'),
(4, 'José', 'Ramirez', '/img/profiles/jose_ramirez.png'),
(5, 'Javier', 'Peña', '/img/profiles/javier_pena.png'),
(6, 'Hugo', 'Medina', '/img/profiles/hugo_medina.png'),
(7, 'César', 'Acuña', '/img/profiles/cesar_acuna.png'),
(8, 'Elena', 'Rivas', '/img/profiles/elena_rivas.png'),
(9, 'Enrique', 'Peñanieto', '/img/profiles/enrique_penanieto.png'),
(10, 'Carlos', 'Díaz', '/img/profiles/carlos_diaz.png'),
(11, 'Martina', 'Cueva', '/img/profiles/martina_cueva.png'),
(12, 'Carlos', 'Gómez', '/img/profiles/comprador1.png'),
(13, 'Ana', 'Torres', '/img/profiles/comprador2.png'),
(14, 'Luis', 'Díaz', '/img/profiles/comprador3.png'),
(15, 'Sofía', 'Mendoza', '/img/profiles/comprador4.png'),
(16, 'David', 'Rojas', '/img/profiles/comprador5.png'),
(17, 'Laura', 'Castillo', '/img/profiles/comprador6.png'),
(18, 'Miguel', 'Vargas', '/img/profiles/comprador7.png'),
(19, 'Isabella', 'Soto', '/img/profiles/comprador8.png'),
(20, 'Mateo', 'Flores', '/img/profiles/comprador9.png'),
(21, 'Camila', 'Reyes', '/img/profiles/comprador10.png');

-- PASO 4: Inserción de trabajos de prueba (Sin cambios)
INSERT IGNORE INTO works (work_id, id_seller_user, id_work_category, work_title, work_description, work_price, work_image_url, work_status) VALUES
(1, 2, 1, 'Landing Page para Cafetería', 'Diseño y desarrollo de una landing page moderna y atractiva para cafeterías, optimizada para la conversión de clientes.', 220.00, '/img/works/landing-cafe.jpg', 'PUBLICADO'),
(2, 3, 1, 'Aplicación Móvil para Tienda', 'Desarrollo de una aplicación móvil nativa para iOS y Android que permite a tus clientes comprar tus productos desde cualquier lugar.', 480.00, '/img/works/app-tienda.jpg', 'PUBLICADO'),
(3, 4, 1, 'Portal Web para Colegio', 'Solución web integral para instituciones educativas que incluye gestión de alumnos, notas, y comunicación con padres de familia.', 520.00, '/img/works/web-colegio.jpg', 'PUBLICADO'),
(4, 5, 4, 'UI para App de Delivery', 'Diseño profesional de la interfaz y experiencia de usuario para tu aplicación de delivery, enfocado en la usabilidad y la estética.', 400.00, '/img/works/app-delivery.jpg', 'PUBLICADO'),
(5, 6, 1, 'Sitio Web para Consultora de TI', 'Creación de un sitio web corporativo profesional para empresas de consultoría tecnológica, destacando servicios y casos de éxito.', 590.00, '/img/works/web-consultoria.jpg', 'PUBLICADO'),
(6, 7, 1, 'Portal Web para Servicios Legales', 'Portal web especializado para estudios de abogados, con un diseño sobrio, profesional y que inspira confianza.', 480.00, '/img/works/web-legal.jpg', 'PUBLICADO'),
(7, 4, 1, 'App Web de Seguimiento de Proyectos', 'Aplicación web robusta para la gestión y seguimiento de tareas y proyectos en equipo, al estilo Trello o Asana.', 580.00, '/img/works/app-seguimiento.jpg', 'EN_REVISION'),
(8, 5, 4, 'Diseño de App de Recetas Saludables', 'Diseño de interfaz atractivo y funcional para una aplicación móvil de recetas saludables, con un enfoque en la fotografía de alimentos.', 400.00, '/img/works/app-recetas.jpg', 'PUBLICADO'),
(9, 8, 2, 'Curso: Diseño UX para Principiantes', 'Aprende los fundamentos del diseño de experiencia de usuario (UX) desde cero, con ejercicios prácticos y teoría aplicada.', 150.00, '/img/works/diseño-principiantes.jpg', 'PUBLICADO'),
(10, 2, 3, 'Composición Instrumental de Jazz', 'Pieza de jazz instrumental original con una duración de 3 minutos, perfecta para cortometrajes, podcasts o música de fondo.', 75.00, '/img/works/jazz.jpg', 'PUBLICADO'),
(11, 3, 2, 'Curso: Introducción a la Animación 2D', 'Descubre el mundo de la animación 2D. Aprenderás a crear personajes, fondos y a darles vida con principios básicos de animación.', 280.00, '/img/works/animacion-2d.jpg', 'PUBLICADO'),
(12, 2, 4, 'Retrato Digital Infantil', 'Transformo la foto de un niño en un adorable retrato digital estilo caricatura, ideal para regalos y decoración.', 60.00, '/img/works/retrato-infantil.jpg', 'PUBLICADO'),
(13, 3, 3, 'Efectos de Sonido para Videojuego', 'Paquete de 50 efectos de sonido de alta calidad (pasos, saltos, explosiones, etc.) para tu videojuego de plataforma.', 300.00, '/img/works/juego.jpg', 'PUBLICADO'),
(14, 9, 3, 'Música Ambiental para Cafetería', 'Playlist curada con 1 hora de música ambiental original, estilo lofi/chillhop, para crear una atmósfera relajante.', 120.00, '/img/works/musica-cafe.jpg', 'EN_REVISION'),
(15, 5, 3, 'Canción Personalizada para Regalar', 'Compongo y grabo una canción original (letra y música) basada en tu historia, perfecta para aniversarios o sorpresas.', 350.00, '/img/works/music-regalo.jpg', 'PUBLICADO'),
(16, 3, 4, 'Ilustración para Portada Estilo Yakuza', 'Ilustración detallada y potente con estética inspirada en el arte de los videojuegos de la saga Yakuza, para portadas o pósters.', 170.00, '/img/works/ilustracion-portada.jpg', 'PUBLICADO'),
(17, 9, 4, 'Diseño de Logo Frutal y Vibrante', 'Creación de un logo moderno, colorido y memorable con temática de frutas, ideal para marcas de jugos o postres.', 180.00, '/img/works/logo-frutal.jpg', 'PUBLICADO'),
(18, 9, 2, 'Curso: Marketing Digital Desde Cero', 'Domina las estrategias de marketing digital para potenciar negocios, desde redes sociales hasta SEO y SEM.', 250.00, '/img/works/mrkt-digital.jpg', 'PUBLICADO'),
(19, 5, 2, 'Curso: Introducción a Photoshop', 'Curso completo para dominar Adobe Photoshop desde lo más básico, ideal para principiantes que quieren iniciarse en el diseño gráfico.', 80.00, '/img/works/introduccion-photo.jpg', 'PUBLICADO'),
(20, 4, 4, 'Ilustración conceptual: Mundo Animal', 'Obra de arte digital que representa la dualidad del mundo animal, explorando la belleza y la crudeza de la naturaleza.', 110.00, '/img/works/mundo-animal.jpg', 'PUBLICADO');

-- PASO 5: Datos transaccionales (Sin cambios)
INSERT IGNORE INTO orders (id_user) VALUES (12), (13), (14), (15), (16);

INSERT IGNORE INTO order_details (id_order, id_work, order_detail_quantity, order_detail_unit_price) VALUES
(1, 1, 1, 220.00), (2, 2, 1, 480.00), (3, 3, 1, 520.00), (4, 4, 1, 400.00), (5, 5, 1, 590.00);

INSERT IGNORE INTO ratings (work_id, user_id, order_id, rating_score, rating_comment) VALUES
(1, 12, 1, 5, '¡El mejor landing page que he visto! Superó mis expectativas.'),
(2, 13, 2, 4, 'La app es funcional, aunque la interfaz podría ser un poco más moderna.'),
(3, 14, 3, 5, 'Excelente portal, muy completo y fácil de usar para nuestros docentes.'),
(4, 15, 4, 4, 'Buen diseño UI, pero tardó un poco en la entrega de los prototipos.'),
(5, 16, 5, 5, 'Un trabajo muy profesional. El sitio web de mi consultora quedó perfecto.');

INSERT IGNORE INTO comments (id_work, id_user, comment_body, comment_is_deleted) VALUES 
(1, 13, 'Hola, ¿el diseño es responsive? Se adapta bien a móviles?', FALSE),
(4, 12, '¿Se pueden añadir más opciones de pago en la app de delivery?', FALSE);

INSERT IGNORE INTO comments (id_work, id_user, id_parent_comment, comment_body, comment_is_deleted) VALUES 
(1, 2, 1, '¡Hola! Sí, por supuesto. Todos mis diseños son 100% responsivos.', FALSE),
(4, 5, 2, 'Claro, podemos integrarlo con la pasarela de pagos que necesites. Contáctame y lo vemos.', FALSE);

-- FIN DEL SCRIPT