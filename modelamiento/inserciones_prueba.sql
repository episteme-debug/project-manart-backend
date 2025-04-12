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

