create database db_manart;
select * from usuario;
select * from producto;
select * from categoria_producto;
# INSERT CATEGORIA_PRODUCTOS

INSERT INTO categoria_producto (id_categoria, descripcion_categoria, estado_categoria, imagen_categoria, nombre_categoria, orden_visualizacion_categoria) 
VALUES
(2, 'Artesanías hechas a mano con fibras naturales, como mimbre y bambú, para crear cestas y otros objetos tejidos.', 1, 'categoriaCesteria.jpg', 'Cestería', 2),
(1, 'Artesanías hechas de cerámica y arcilla.', 1, 'categoriaCeramica.jpg', 'Cerámica', 1),
(6, 'Bolsos tejidos a mano por la comunidad Wayuu, representando su cultura y tradición.', 1, 'categoriaMochilaWayuu.jpeg', 'Mochilas Wayuu', 7),
(3, 'Diseños únicos de collares, anillos y pulseras hechos a mano.', 0, 'categoriaJoyeria.jpg', 'Joyería', 3),
(5, 'Esculturas, muebles y utensilios hechos de madera trabajada a mano.', 1, 'categoriaMadera.jpg', 'Madera Tallada', 5);

# INSERT USUARIOS

INSERT INTO usuario (id_usuario, apellido_usuario, estado_usuario, imagen_perfil_usuario, nombre_usuario, telefono_usuario, tipo_usuario, contrasena_usuario, email_usuario) 
VALUES
(1, 'Darwin', 1, 'perfilDarwin.jpg', 'Charles', '123456789', 1, 'evolution123', 'cdarwin@biology.com'),
(2, 'Newton', 1, 'perfilNewton.jpg', 'Isaac', '987654321', 2, 'gravity987', 'inewton@physics.com'),
(3, 'Einstein', 1, 'perfilEinstein.jpg', 'Albert', '456789123', 2, 'relativity1905', 'aeinstein@physics.com'),
(4, 'Curie', 1, 'perfilCurie.jpg', 'Marie', '654321789', 2, 'radioactive1898', 'mcurie@physics.com'),
(5, 'Mendel', 1, 'perfilMendel.jpg', 'Gregor', '321987654', 1, 'genetics1865', 'gmendel@biology.com'),
(6, 'Bohr', 1, 'perfilBohr.jpg', 'Niels', '789123456', 2, 'quantum1922', 'nbohr@physics.com'),
(7, 'Hawking', 1, 'perfilHawking.jpg', 'Stephen', '112233445', 2, 'blackhole1974', 'shawking@physics.com'),
(8, 'Watson', 1, 'perfilWatson.jpg', 'James', '667788990', 1, 'dna1953', 'jwatson@biology.com'),
(9, 'Crick', 1, 'perfilCrick.jpg', 'Francis', '445566778', 1, 'doublehelix1953', 'fcrick@biology.com'),
(10, 'Heisenberg', 1, 'perfilHeisenberg.jpg', 'Werner', '223344556', 2, 'uncertainty1927', 'wheisenberg@physics.com');

#INSERT PROMOCION

INSERT INTO promocion (id_promocion, detalles_promocion, estado_promocion, fecha_fin_promocion, fecha_inicio_promocion, nombre_promocion, porcentaje_descuento_promocion)
VALUES
(1, 'Descuento especial en mochilas', 1, '2024-12-31', '2024-11-01', 'Descuento en Mochilas', 15),
(2, 'Promoción por temporada para sombreros', 1, '2024-12-15', '2024-11-15', 'Temporada de Sombreros', 20),
(3, 'Oferta especial en cerámica', 1, '2024-12-20', '2024-11-20', 'Oferta en Cerámica', 25),
(4, 'Promoción exclusiva para clientes frecuentes', 1, '2024-12-10', '2024-11-10', 'Clientes Frecuentes', 10),
(5, 'Black Friday en toda la tienda', 1, '2024-11-30', '2024-11-25', 'Black Friday', 50),
(6, 'Aniversario de la tienda con descuentos en artesanías', 1, '2024-12-31', '2024-12-01', 'Aniversario Artesanal', 30),
(7, 'Promoción navideña en regalos', 1, '2024-12-25', '2024-12-10', 'Navidad Artesanal', 35),
(8, 'Descuentos en productos de cuero y fique', 1, '2024-12-20', '2024-11-30', 'Descuento en Cuero y Fique', 15),
(9, 'Semana del artesano con promociones especiales', 1, '2024-11-10', '2024-11-05', 'Semana del Artesano', 20),
(10, 'Liquidación de fin de año en productos seleccionados', 1, '2024-12-31', '2024-12-20', 'Liquidación Fin de Año', 40);

INSERT INTO artesano(rol_usuario,id_usuario)
values
('Artesano',1),
('Artesano',2),
('Artesano',3),
('Artesano',4),
('Artesano',5),
('Artesano',6),
('Artesano',7),
('Artesano',8),
('Artesano',9),
('Artesano',10);

#INSERT PRODUCTOS
select * from producto;
INSERT INTO producto (id_producto, descripcion_producto, imagen_producto, nombre_producto, precio_producto, stock_producto, id_usuario, id_promocion)
VALUES
(1, 'Estas piezas artesanales están elaboradas a mano por la comunidad Wayuu, un grupo indígena ubicado en la región de la Guajira, entre Colombia y Venezuela. Utilizando técnicas ancestrales, los artesanos Wayuu crean productos únicos y coloridos, que son reflejo de su rica tradición cultural y de su conexión con la naturaleza.', 'img/ProductoMochilasWayuu.jpeg', 'Mochilas Wayuu', 50000, 45, 1, 1),
(2, 'Un sombrero vueltiao hecho de palma de caña fleha, tradicional de Sucre, es una prenda de gran valor cultural en Colombia, especialmente en la región caribeña. Este sombrero es conocido por su habilidad artesanal y su diseño único, que combina la tradición y el arte de los pueblos indígenas zenúes.', 'img/ProductoSombreroVueltiado.jpg', 'Sombreros Vueltiaos', 150000, 33, 2, 2),
(3, 'La joyería y figuras elaboradas con marfil vegetal, también conocido como tagua, son una tradición artesanal que proviene principalmente de las regiones tropicales de América del Sur, como Colombia, Ecuador y Perú. El tagua es una semilla de la palma de tagua (Phytelephas aequatorialis) que, debido a su dureza y apariencia similar al marfil, se ha utilizado durante siglos para crear una gran variedad de artículos.', 'img/ProductoTagua.jpg', 'Artesanías de Tagua', 55000, 10, 3, 3),
(4, 'Hechos a mano con la resistente fibra de fique, estas cestas y accesorios combinan funcionalidad y belleza. Utilizando técnicas tradicionales de tejido, cada pieza es única y ecológica. Perfectos para decoración o uso diario, estos productos ofrecen un toque rústico y natural a cualquier espacio. La fibra de fique, conocida por su durabilidad, se emplea en la creación de cestas, mochilas, carteras, sombreros y más, todos elaborados con cuidado y destreza artesanal.', 'img/ProductoFique.jpg', 'Cestas de Fique', 66000, 20, 4, 4),
(5, 'Estos jarrones de barro son el resultado de una tradición ancestral de alfarería transmitida de generación en generación. Cada pieza es moldeada a mano con técnicas artesanales que reflejan la esencia y el arte de las comunidades indígenas.', 'img/ProductoJarrones.jpg', 'Jarrones de Barro', 75000, 44, 5, 5),
(6, 'Elaborados con la resistente fibra de fique, estos productos son tejidos a mano utilizando técnicas ancestrales de la región andina. Cada pieza refleja la destreza y tradición de los artesanos locales, ofreciendo una mezcla de belleza y funcionalidad. Las cestas y accesorios, como mochilas, carteras y sombreros, son ideales tanto para decoración como para uso diario. La fibra de fique, un material natural y ecológico, garantiza durabilidad y un acabado único en cada artículo.', 'img/ProductoManta.jpg', 'Tejidos de Manta', 80000, 10, 6, 6),
(7, 'Las cestas elaboradas en Guaviare son piezas artesanales tejidas a mano con fibras naturales, como el fique, la palma o el bejuco. Estas cestas son el resultado de un trabajo meticuloso y lleno de tradición, realizado por las comunidades indígenas y artesanos locales utilizando técnicas ancestrales que se han transmitido de generación en generación.', 'img/ProductoMaseta.jpg', 'Másetas de Guaviare', 73000, 10, 7, 7),
(8, 'Los bolsos y carteras elaborados por artesanos de Antioquia son piezas de alta calidad, creadas con cuero de excelente durabilidad y acabado. Estos productos son el resultado de un proceso artesanal meticuloso, donde se combinan técnicas tradicionales de curtido y costura con diseños modernos y funcionales.', 'img/ProductoBlososCuero.jpg', 'Bolsos de Cuero', 200000, 20, 8, 8),
(9, 'as máscaras ceremoniales elaboradas por los zenúes son piezas de gran valor cultural y espiritual, creadas para representar figuras simbólicas, como espíritus, animales o seres mitológicos en sus rituales y ceremonias. El proceso de elaboración de estas máscaras es artesanal y sigue técnicas tradicionales transmitidas de generación en generación.', 'img/ProductoZenues.jpg', 'Zenúes', 79000, 30, 9, 9),
(10, 'Las pinturas que reflejan la vida cotidiana y paisajes del Caribe a través de la figura de la mujer caribeña son una manifestación artística que combina la belleza de la región con el papel fundamental de la mujer en la cultura y la vida diaria de este territorio. Este tipo de arte busca destacar la identidad, el trabajo y el espíritu de la mujer en el contexto del Caribe colombiano, con sus tradiciones, su entorno natural y su vitalidad.', 'img/ProductoPintura.jpg', 'Pintura Caribeñas', 59000, 5, 10, 10);