-- Script de carga masiva para MySQL
-- Ejecutar luego de que la app haya creado el schema (moodtrackdb)
-- Podés usar MySQL Workbench o la consola de MySQL.

-- CREATE DATABASE moodtrackdb
--        CHARACTER SET utf8mb4
--        COLLATE utf8mb4_unicode_ci;


USE moodtrackdb;

-- Limpieza básica (opcional, solo para entorno de desarrollo)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE entrada_habito;
TRUNCATE TABLE entrada_diaria;
TRUNCATE TABLE habito;
TRUNCATE TABLE usuario;
TRUNCATE TABLE perfil_usuario;
SET FOREIGN_KEY_CHECKS = 1;

########################################
-- 1) Perfiles de usuario
########################################

INSERT INTO perfil_usuario (bio, color_favorito, frase_del_dia) VALUES
('Apasionado por la tecnología y el café.', 'AZUL', 'Cada día es una nueva oportunidad.'),
('Amante de los libros y la tranquilidad.', 'VERDE', 'Leer es soñar con los ojos abiertos.'),
('Fanático del deporte y la vida saludable.', 'ROJO', 'La disciplina vence al talento.'),
('Programador nocturno.', 'NEGRO', 'Primero funciona, luego optimiza.'),
('Explorador de montañas.', 'NARANJA', 'La cima está más cerca de lo que crees.'),
('Mente creativa.', 'VIOLETA', 'La creatividad es inteligencia divirtiéndose.'),
('Amante de los animales.', 'CELESTE', 'La lealtad se escribe con patas.'),
('Músico aficionado.', 'AMARILLO', 'La vida sin música sería un error.'),
('Cinéfilo empedernido.', 'GRIS', 'Cada película es una nueva vida.'),
('Minimalista digital.', 'BLANCO', 'Menos es más.');

########################################
-- 2) Usuarios (UUID en columna id con BINARY(16))
--    Usamos UUID_TO_BIN para compatibilidad con Hibernate.
########################################

INSERT INTO usuario (id, name, email, perfil_id) VALUES
(UUID_TO_BIN('11111111-1111-1111-1111-111111111111'), 'Ana',   'ana@example.com',   1),
(UUID_TO_BIN('22222222-2222-2222-2222-222222222222'), 'Bruno', 'bruno@example.com', 2),
(UUID_TO_BIN('33333333-3333-3333-3333-333333333333'), 'Carla', 'carla@example.com', 3),
(UUID_TO_BIN('44444444-4444-4444-4444-444444444444'), 'Diego', 'diego@example.com', 4),
(UUID_TO_BIN('55555555-5555-5555-5555-555555555555'), 'Elena', 'elena@example.com', 5),
(UUID_TO_BIN('66666666-6666-6666-6666-666666666666'), 'Facu',  'facu@example.com',  6),
(UUID_TO_BIN('77777777-7777-7777-7777-777777777777'), 'Gaby',  'gaby@example.com',  7),
(UUID_TO_BIN('88888888-8888-8888-8888-888888888888'), 'Hugo',  'hugo@example.com',  8),
(UUID_TO_BIN('99999999-9999-9999-9999-999999999999'), 'Iris',  'iris@example.com',  9),
(UUID_TO_BIN('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'), 'Juan',  'juan@example.com',  10);

########################################
-- 3) Hábitos
########################################

INSERT INTO habito (descripcion, nivel_de_importancia_enum) VALUES
('Caminar 30 minutos',        'BAJO'),
('Leer 20 minutos',           'MEDIO'),
('Meditar 10 minutos',        'ALTO'),
('Beber 2 litros de agua',    'MEDIO'),
('Dormir 8 horas',            'ALTO'),
('Escribir un diario',        'MEDIO'),
('Desconectarse del celular', 'ALTO'),
('Hacer una buena acción',    'BAJO');

########################################
-- 4) Entradas diarias
--    usuario_id referencia a la tabla usuario (BINARY(16)).
--    Usamos de nuevo UUID_TO_BIN con los mismos valores.
########################################

INSERT INTO entrada_diaria (fecha, reflexion, emocion, usuario_id) VALUES
('2025-01-01', 'Arrancando el año con muchas ganas.', 'FELIZ', UUID_TO_BIN('11111111-1111-1111-1111-111111111111')),
('2025-01-02', 'Día tranquilo, mucha lectura.',       'CALMO', UUID_TO_BIN('22222222-2222-2222-2222-222222222222')),
('2025-01-03', 'Entrené fuerte, estoy motivado.',     'ENERGICO', UUID_TO_BIN('33333333-3333-3333-3333-333333333333')),
('2025-01-04', 'Un poco cansado pero contento.',      'CANSADO', UUID_TO_BIN('44444444-4444-4444-4444-444444444444')),
('2025-01-05', 'Gran día en familia.',                'AGRADECIDO', UUID_TO_BIN('55555555-5555-5555-5555-555555555555')),
('2025-01-06', 'Estudié bastante Java.',              'ORGULLOSO', UUID_TO_BIN('66666666-6666-6666-6666-666666666666')),
('2025-01-07', 'Me sentí un poco abrumado.',          'ANSIOSO', UUID_TO_BIN('77777777-7777-7777-7777-777777777777')),
('2025-01-08', 'Buena charla con amigos.',            'CONTENTO', UUID_TO_BIN('88888888-8888-8888-8888-888888888888')),
('2025-01-09', 'Día muy productivo.',                 'SATISFECHO', UUID_TO_BIN('99999999-9999-9999-9999-999999999999')),
('2025-01-10', 'Necesito descansar un poco.',         'AGOTADO', UUID_TO_BIN('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa')),
('2025-01-11', 'Salí a caminar por la ciudad.',       'RELAX', UUID_TO_BIN('11111111-1111-1111-1111-111111111111')),
('2025-01-12', 'Aprendí algo nuevo en programación.', 'ENTUSIASMADO', UUID_TO_BIN('22222222-2222-2222-2222-222222222222')),
('2025-01-13', 'Día complicado en el trabajo.',       'ESTRESADO', UUID_TO_BIN('33333333-3333-3333-3333-333333333333')),
('2025-01-14', 'Noche de películas.',                 'ENTRETENIDO', UUID_TO_BIN('44444444-4444-4444-4444-444444444444')),
('2025-01-15', 'Pequeños avances, pero seguros.',     'ESPERANZADO', UUID_TO_BIN('55555555-5555-5555-5555-555555555555')),
('2025-01-16', 'Hoy no tuve muchas ganas.',           'APATICO', UUID_TO_BIN('66666666-6666-6666-6666-666666666666')),
('2025-01-17', 'Hice ejercicio y comí sano.',         'SALUDABLE', UUID_TO_BIN('77777777-7777-7777-7777-777777777777')),
('2025-01-18', 'Muchas reuniones, poco foco.',        'SATURADO', UUID_TO_BIN('88888888-8888-8888-8888-888888888888')),
('2025-01-19', 'Día de descanso total.',              'RELAX', UUID_TO_BIN('99999999-9999-9999-9999-999999999999')),
('2025-01-20', 'Planificando nuevas metas.',          'INSPIRADO', UUID_TO_BIN('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'));

########################################
-- 5) Relación muchos-a-muchos entrada_habito
--    Asumimos que los IDs de entrada_diaria y habito comienzan en 1.
########################################

INSERT INTO entrada_habito (entrada_id, habito_id) VALUES
(1, 1),
(1, 4),
(2, 2),
(2, 6),
(3, 1),
(3, 5),
(4, 4),
(5, 5),
(5, 8),
(6, 2),
(6, 6),
(7, 3),
(7, 7),
(8, 1),
(8, 2),
(9, 5),
(10, 3),
(10, 4),
(11, 1),
(11, 8),
(12, 2),
(12, 6),
(13, 4),
(14, 8),
(15, 5),
(16, 7),
(17, 3),
(18, 4),
(19, 1),
(20, 2);


