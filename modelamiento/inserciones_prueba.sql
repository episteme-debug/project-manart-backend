create database db_manart;
select * from usuario;
select * from producto;
select * from categoria_producto;

# INSERT USUARIOS
INSERT INTO usuario (nombre_usuario, apellido_usuario, email_usuario, hash_contrasena_usuario, telefono_usuario, estado_usuario, imagen_perfil_usuario, rol_usuario)
VALUES
('Marie', 'Curie', 'marie.curie@example.com', '1234abcd', '555-1234', true, 'avatarGenerico.jpg', 'COMPRADOR'),
('Albert', 'Einstein', 'albert.einstein@example.com', '5678efgh', '555-5678', true, 'avatarGenerico.jpg', 'VENDEDOR'),
('Isaac', 'Newton', 'isaac.newton@example.com', '9101ijkl', '555-9101', true, 'avatarGenerico.jpg', 'COMPRADOR'),
('Rosalind', 'Franklin', 'rosalind.franklin@example.com', '1122mnop', '555-1122', true, 'avatarGenerico.jpg', 'VENDEDOR'),
('Galileo', 'Galilei', 'galileo.galilei@example.com', '1314qrst', '555-1314', true, 'avatarGenerico.jpg', 'COMPRADOR'),
('Ada', 'Lovelace', 'ada.lovelace@example.com', '1516uvwx', '555-1516', true, 'avatarGenerico.jpg', 'VENDEDOR'),
('Nikola', 'Tesla', 'nikola.tesla@example.com', '1718yzab', '555-1718', true, 'avatarGenerico.jpg', 'COMPRADOR'),
('Charles', 'Darwin', 'charles.darwin@example.com', '1920cdef', '555-1920', true, 'avatarGenerico.jpg', 'VENDEDOR'),
('Katherine', 'Johnson', 'katherine.johnson@example.com', '2122ghij', '555-2122', true, 'avatarGenerico.jpg', 'COMPRADOR'),
('Alan', 'Turing', 'alan.turing@example.com', '2324klmn', '555-2324', true, 'avatarGenerico.jpg', 'VENDEDOR');

#INSERT PRODUCTOS
select * from producto;
INSERT INTO Producto (nombre_producto, descripcion_producto, stock_producto, precio_producto, imagen_producto, id_usuario, id_promocion) VALUES 
('Vasija Nazca', 'Vasija cerámica con diseños geométricos de la cultura Nazca.', 40, 150000.00, 'vasija_nazca.jpg', 2, NULL),  
('Vasija Chimú', 'Vasija escultórica con representaciones zoomorfas de la cultura Chimú.', 30, 180000.00, 'vasija_chimu.jpg', 3, NULL),
('Vasija Muisca', 'Vasija de barro inspirada en la cultura Muisca.', 50, 120000.00, 'vasija.jpg', 1, NULL),
('Ruana de Lana', 'Ruana tejida a mano con lana de oveja.', 30, 180000.00, 'ruana.jpg', 2, NULL),
('Anillo de Oro Zenú', 'Anillo artesanal con diseño Zenú en oro.', 20, 250000.00, 'anillo.jpg', 3, NULL),
('Máscara Totémica', 'Máscara esculpida en madera para rituales ancestrales.', 15, 75000.00, 'mascara.jpg', 4, NULL),
('Tambor Wayuu', 'Instrumento de percusión tradicional de la cultura Wayuu.', 10, 220000.00, 'tambor.jpg', 5, NULL),
('Canasto de Palma', 'Canasto tejido con palma de iraca.', 40, 90000.00, 'canasto.jpg', 1, NULL),
('Collar Precolombino', 'Collar hecho con cuentas de piedra inspiradas en la época precolombina.', 25, 130000.00, 'collar.jpg', 2, NULL),
('Bolso de Cuero Tallado', 'Bolso de cuero artesanal con tallado indígena.', 18, 270000.00, 'bolso.jpg', 3, NULL),
('Juguete de Madera', 'Juguete tradicional tallado en madera.', 35, 60000.00, 'juguete.jpg', 4, NULL),
('Pintura Rupestre', 'Reproducción de arte rupestre en piedra.', 12, 160000.00, 'pintura.jpg', 5, NULL);

#INSERT CATEGORIA PRODUCTOS
SELECT * FROM categoria_producto;
INSERT INTO categoria_producto (nombre_categoria, descripcion_categoria, estado_categoria, imagen_categoria) 
VALUES 
('Cerámica Indígena', 'Objetos de cerámica hechos a mano por comunidades indígenas.', true, 'ceramica.jpg'),
('Textiles Ancestrales', 'Ropa y tejidos elaborados con técnicas tradicionales.', true, 'textiles.jpg'),
('Orfebrería Tradicional', 'Joyas y accesorios elaborados con metales preciosos.', true, 'orfebreria.jpg'),
('Escultura en Madera', 'Figuras talladas en madera con gran detalle y precisión.', true, 'madera.jpg'),
('Instrumentos Musicales', 'Instrumentos artesanales usados en ceremonias y festividades.', true, 'instrumentos.jpg'),
('Cestería y Fibras', 'Cestas y objetos hechos con fibras naturales.', true, 'cesteria.jpg'),
('Arte Rupestre', 'Pinturas y grabados en piedra inspirados en el arte precolombino.', true, 'arte_rupestre.jpg'),
('Máscaras Ritualísticas', 'Máscaras usadas en ceremonias espirituales.', true, 'mascaras.jpg'),
('Cuero y Marroquinería', 'Bolsos y accesorios elaborados en cuero artesanal.', true, 'cuero.jpg'),
('Juguetes Tradicionales', 'Juguetes típicos hechos con materiales naturales.', true, 'juguetes.jpg');

#INSERT CATEGORIAXPRODUCTO
SELECT * FROM categoriaxproducto;

SELECT p.id_producto, p.nombre_producto, cp.nombre_categoria FROM producto p INNER JOIN categoriaxproducto cxp 
INNER JOIN categoria_producto cp WHERE p.id_producto = cxp.id_producto AND cxp.id_categoria = cp.id_categoria
AND cp.nombre_categoria LIKE '%Cerámica%';

INSERT INTO categoriaxproducto (id_categoria, id_producto) VALUES 
(1, 11), (3, 11), (5, 11),  -- Vasija Muisca (Cerámica, Orfebrería, Instrumentos)
(2, 12), (4, 12),  -- Ruana de Lana (Textiles, Escultura en Madera)
(3, 13),  -- Anillo de Oro Zenú (Orfebrería)
(4, 14), (8, 14),  -- Máscara Totémica (Escultura en Madera, Máscaras Ritualísticas)
(5, 15), -- Tambor Wayuu (Instrumentos Musicales)
(6, 16), (1, 16), -- Canasto de Palma (Cestería, Cerámica)
(7, 17), -- Collar Precolombino (Arte Rupestre)
(9, 18), (2, 18), -- Bolso de Cuero Tallado (Cuero y Marroquinería, Textiles)
(10, 19), -- Juguete de Madera (Juguetes Tradicionales)
(7, 20), (1, 20), (3, 20); -- Pintura Rupestre (Arte Rupestre, Cerámica, Orfebrería)

# Insertar direcciones de usuario
INSERT INTO direccion (
    tipo_via, numero_via_principal, letra_via_principal, bis_via_principal, 
    numero_via_secundaria, letra_via_secundaria, bis_via_secundaria, 
    numero_predio, complemento, barrio, ciudad, departamento, 
    es_predeterminada, fecha_creacion, fecha_actualizacion, id_usuario
) VALUES 
('Avenida', '100', 'D', false, '50', 'A', false, '10-20', 'Edificio Torre Central', 'Chapinero', 'Bogotá', 'Cundinamarca', true, NOW(), NOW(), 1),
('Calle', '80', NULL, false, '30', 'B', true, '15-40', 'Local 5', 'El Poblado', 'Medellín', 'Antioquia', false, NOW(), NOW(), 2),
('Carrera', '45', 'E', true, '12', NULL, false, '25-60', NULL, 'San Victorino', 'Bogotá', 'Cundinamarca', true, NOW(), NOW(), 3),
('Diagonal', '33', NULL, false, '22', 'C', false, '8-17', 'Apartamento 401', 'Granada', 'Cali', 'Valle del Cauca', false, NOW(), NOW(), 1),
('Transversal', '55', 'A', true, '70', NULL, false, '32-90', 'Casa 10', 'Centro', 'Barranquilla', 'Atlántico', true, NOW(), NOW(), 5),
('Calle', '120', NULL, false, '25', 'B', true, '5-15', 'Edificio Mirador', 'Santa Bárbara', 'Bogotá', 'Cundinamarca', false, NOW(), NOW(), 6),
('Carrera', '9', 'C', false, '48', 'D', false, '30-50', 'Oficina 302', 'El Recreo', 'Cartagena', 'Bolívar', true, NOW(), NOW(), 3),
('Avenida', '7', 'E', true, '20', NULL, true, '6-80', 'Local 10', 'Chicó', 'Bogotá', 'Cundinamarca', false, NOW(), NOW(), 8),
('Diagonal', '15', 'B', false, '35', 'A', false, '45-70', NULL, 'Los Rosales', 'Pereira', 'Risaralda', true, NOW(), NOW(), 9),
('Transversal', '98', NULL, true, '5', 'D', false, '12-25', 'Torre Norte', 'San Diego', 'Medellín', 'Antioquia', false, NOW(), NOW(), 10);

# Insertar promociones
select * from promocion;
INSERT INTO promocion (nombre_promocion, detalles_promocion, fecha_inicio_promocion, fecha_fin_promocion, porcentaje_descuento_promocion, estado_promocion) 
VALUES 
('Feria de Artesanías', 'Descuento del 20% en tejidos, cerámicas y madera tallada.', '2024-07-01', '2024-07-15', 20, TRUE),
('Especial Cerámica', '30% de descuento en jarrones y vajillas artesanales.', '2024-08-10', '2024-08-20', 30, TRUE),
('Semana del Tejido', '25% en tapices, ponchos y mantas hechas a mano.', '2024-09-05', '2024-09-15', 25, TRUE),
('Promoción de Cuero', '15% de descuento en bolsos y cinturones de cuero artesanal.', '2024-10-01', '2024-10-07', 15, TRUE),
('Arte en Madera', 'Promoción del 35% en esculturas y utensilios de madera.', '2024-11-01', '2024-11-10', 35, TRUE),
('Fiesta de los Textiles', 'Grandes descuentos en artesanías textiles.', '2024-07-20', '2024-07-25', 30, TRUE),
('Artesanía Navideña', 'Descuento del 40% en decoraciones navideñas hechas a mano.', '2024-12-10', '2024-12-24', 40, TRUE),
('Día del Artesano', 'Celebramos con un 50% en productos seleccionados.', '2024-06-19', '2024-06-19', 50, TRUE),
('Festival de Máscaras', 'Promoción del 20% en máscaras artesanales pintadas a mano.', '2024-08-25', '2024-09-05', 20, TRUE),
('Hecho a Mano con Amor', '10% de descuento en todos los productos de la tienda.', '2024-05-01', '2024-05-07', 10, TRUE);

INSERT INTO promocion (detalles_promocion, estado_promocion, fecha_fin_promocion, fecha_inicio_promocion, nombre_promocion, porcentaje_descuento_promocion) VALUES
('Descuento verano 2025', 1, '2025-08-31', '2025-06-01', 'Promo Verano', 15),
('Oferta especial', 1, '2025-09-30', '2025-07-01', 'Oferta Especial', 10),
('Promoción aniversario', 1, '2025-12-31', '2025-11-01', 'Aniversario', 20),
('Rebajas navideñas', 1, '2025-12-25', '2025-12-01', 'Navidad', 25),
('Liquidación final', 1, '2025-07-31', '2025-07-01', 'Liquidación', 30);

INSERT INTO producto (descripcion_producto, estado_producto, imagen_producto, nombre_producto, precio_producto, stock_producto, id_promocion, id_usuario) VALUES
('Mochila Wayuu tejida a mano por artesanas de La Guajira', 1, 'mochila1.jpg', 'Mochila Wayuu', 120000.00, 10, NULL, 1),
('Sombrero Vueltiao tradicional con 21 vueltas', 1, 'sombrero1.jpg', 'Sombrero Vueltiao', 85000.00, 12, NULL, 1),
('Collar Embera con chaquiras multicolor', 1, 'collar1.jpg', 'Collar Embera', 50000.00, 15, NULL, 1),
('Hamaca caribeña doble, tejida en algodón', 1, 'hamaca1.jpg', 'Hamaca Guajira', 150000.00, 7, NULL, 1),
('Aretes en filigrana de Mompox', 1, 'aretes1.jpg', 'Aretes Filigrana', 45000.00, 20, NULL, 1),
('Cartera de fique decorada con flores bordadas', 1, 'cartera1.jpg', 'Cartera de Fique', 60000.00, 9, NULL, 1),
('Pulsera tejida con patrones zenú', 1, 'pulsera1.jpg', 'Pulsera Zenú', 35000.00, 14, NULL, 1),
('Escultura en madera del Amazonas', 1, 'escultura1.jpg', 'Escultura Amazónica', 200000.00, 3, NULL, 1),
('Cerámica pintada a mano de Ráquira', 1, 'ceramica1.jpg', 'Vasija de Ráquira', 70000.00, 18, NULL, 1),
('Bolso ecológico con fibra de plátano', 1, 'bolso1.jpg', 'Bolso de Fibra', 55000.00, 11, NULL, 1),

('Juego de mates en cerámica artesanal', 1, 'mates1.jpg', 'Juego de Mates', 85000.00, 8, NULL, 1),
('Máscara tradicional del Carnaval de Barranquilla', 1, 'mascara1.jpg', 'Máscara Carnaval', 90000.00, 5, NULL, 1),
('Tapete tejida con lana natural', 1, 'tapete1.jpg', 'Tapete Andino', 120000.00, 6, NULL, 1),
('Llaveros de madera tallada a mano', 1, 'llaveros1.jpg', 'Llaveros Artesanales', 25000.00, 30, NULL, 1),
('Vaso de barro pintado a mano', 1, 'vaso1.jpg', 'Vaso de Barro', 35000.00, 22, NULL, 1),

('Figura de cerámica de cueva de los guácharos', 1, 'figura1.jpg', 'Figura Guácharos', 65000.00, 9, NULL, 1),
('Bufanda de lana de alpaca', 1, 'bufanda1.jpg', 'Bufanda Alpaca', 110000.00, 10, NULL, 1),
('Cinturón en cuero con diseño indígena', 1, 'cinturon1.jpg', 'Cinturón Indígena', 70000.00, 12, NULL, 1),
('Juego de platos pintados a mano', 1, 'platos1.jpg', 'Platos Artesanales', 95000.00, 8, NULL, 1),
('Pulsera con piedras semipreciosas', 1, 'pulsera2.jpg', 'Pulsera Piedras', 40000.00, 14, NULL, 1),

('Juego de cuchillos tallados a mano', 1, 'cuchillos1.jpg', 'Cuchillos Artesanales', 130000.00, 5, NULL, 1),
('Reloj de madera con diseño étnico', 1, 'reloj1.jpg', 'Reloj de Madera', 85000.00, 7, NULL, 1),
('Bolígrafo tallado en madera', 1, 'boligrafo1.jpg', 'Bolígrafo Madera', 45000.00, 20, NULL, 1),
('Cojín bordado a mano', 1, 'cojin1.jpg', 'Cojín Bordado', 55000.00, 15, NULL, 1),
('Cuadro pintado con técnicas ancestrales', 1, 'cuadro1.jpg', 'Cuadro Ancestral', 140000.00, 6, NULL, 1),

('Sombrero de palma tejida', 1, 'sombrero2.jpg', 'Sombrero de Palma', 70000.00, 11, NULL, 1),
('Juego de tazas de cerámica', 1, 'tazas1.jpg', 'Tazas Cerámicas', 65000.00, 18, NULL, 1),
('Alfombra tejida a mano', 1, 'alfombra1.jpg', 'Alfombra Tejida', 125000.00, 4, NULL, 1),
('Cesta tejida con fibras naturales', 1, 'cesta1.jpg', 'Cesta Artesanal', 60000.00, 13, NULL, 1),
('Pendientes de plata y oro', 1, 'pendientes1.jpg', 'Pendientes Plata', 95000.00, 9, NULL, 1),

('Bolsa de cuero con bordados tradicionales', 1, 'bolsa1.jpg', 'Bolsa de Cuero', 90000.00, 7, NULL, 1),
('Cuenco decorativo en cerámica', 1, 'cuenco1.jpg', 'Cuenco Cerámico', 40000.00, 20, NULL, 1),
('Collar de semillas naturales', 1, 'collar2.jpg', 'Collar Semillas', 30000.00, 16, NULL, 1),
('Mantón de lana con motivos indígenas', 1, 'manton1.jpg', 'Mantón de Lana', 115000.00, 8, NULL, 1),
('Porta velas de madera tallada', 1, 'portavelas1.jpg', 'Porta Velas', 45000.00, 22, NULL, 1),

('Alfileres artesanales', 1, 'alfileres1.jpg', 'Alfileres Artesanales', 15000.00, 25, NULL, 1),
('Cucharas de madera talladas', 1, 'cucharas1.jpg', 'Cucharas Madera', 20000.00, 30, NULL, 1),
('Sombrero de paja toquilla', 1, 'sombrero3.jpg', 'Sombrero Toquilla', 75000.00, 10, NULL, 1),
('Juego de juegos de mesa tradicionales', 1, 'juegosmesa1.jpg', 'Juegos de Mesa', 90000.00, 8, NULL, 1),
('Tapiz hecho a mano', 1, 'tapiz1.jpg', 'Tapiz Artesanal', 110000.00, 5, NULL, 1);



#INSERT CATEGORIA PRODUCTOS
SELECT * FROM categoria_producto;
INSERT INTO categoria_producto (nombre_categoria, descripcion_categoria, estado_categoria, imagen_categoria)
VALUES
('Cerámica Indígena', 'Objetos de cerámica hechos a mano por comunidades indígenas.', true, 'ceramica.jpg'),
('Textiles Ancestrales', 'Ropa y tejidos elaborados con técnicas tradicionales.', true, 'textiles.jpg'),
('Orfebrería Tradicional', 'Joyas y accesorios elaborados con metales preciosos.', true, 'orfebreria.jpg'),
('Escultura en Madera', 'Figuras talladas en madera con gran detalle y precisión.', true, 'madera.jpg'),
('Instrumentos Musicales', 'Instrumentos artesanales usados en ceremonias y festividades.', true, 'instrumentos.jpg'),
('Cestería y Fibras', 'Cestas y objetos hechos con fibras naturales.', true, 'cesteria.jpg'),
('Arte Rupestre', 'Pinturas y grabados en piedra inspirados en el arte precolombino.', true, 'arte_rupestre.jpg'),
('Máscaras Ritualísticas', 'Máscaras usadas en ceremonias espirituales.', true, 'mascaras.jpg'),
('Cuero y Marroquinería', 'Bolsos y accesorios elaborados en cuero artesanal.', true, 'cuero.jpg'),
('Juguetes Tradicionales', 'Juguetes típicos hechos con materiales naturales.', true, 'juguetes.jpg');

INSERT INTO relacion_categoria_producto (id_categoria_producto, id_producto) VALUES
(1, 41),
(9, 42),
(2, 43),
(2, 44),
(3, 45),
(2, 46),
(2, 47),
(4, 48),
(1, 49),
(6, 50),
(1, 51),
(8, 52),
(2, 53),
(4, 54),
(1, 55),
(1, 56),
(2, 57),
(9, 58),
(1, 59),
(3, 60),
(4, 61),
(4, 62),
(4, 63),
(2, 64),
(2, 65),
(2, 66),
(1, 67),
(2, 68),
(6, 69),
(3, 70),
(9, 71),
(1, 72),
(3, 73),
(2, 74),
(4, 75),
(6, 76),
(4, 77),
(2, 78),
(10, 79),
(2, 80);