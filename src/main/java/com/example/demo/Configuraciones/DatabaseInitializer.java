package com.example.demo.Configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    DataSource dataSource;

    @Autowired
    public DatabaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        Connection conexion = dataSource.getConnection();
        Statement script = conexion.createStatement();

        try (conexion; script) {
            // TRIGGERS CORRESPONDIENTES A LA TABLA RELACIONCARRITOPRODUCTO

            // Multiplica la cantidad por el precio unitario
            if (!triggerExiste(conexion, "actualizar_subtotal_al_insertar")) {
                script.execute("CREATE TRIGGER actualizar_subtotal_al_insertar\n" +
                        "BEFORE INSERT ON relacion_carrito_producto\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "    SET NEW.subtotal = NEW.cantidad * NEW.precio_unitario;\n" +
                        "END;");
                System.out.println("Trigger 'actualizar_subtotal_al_insertar' creado.");
            }

            if (!triggerExiste(conexion, "actualizar_subtotal_al_actualizar")) {
                script.execute("CREATE TRIGGER actualizar_subtotal_al_actualizar\n" +
                        "BEFORE UPDATE ON relacion_carrito_producto\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "    IF OLD.cantidad <> NEW.cantidad OR OLD.precio_unitario <> NEW.precio_unitario THEN\n" +
                        "        SET NEW.subtotal = NEW.cantidad * NEW.precio_unitario;\n" +
                        "    END IF;\n" +
                        "END;");
                System.out.println("Trigger 'actualizar_subtotal' creado.");
            }

            // TRIGGERS CORRESPONDIENTES A CARRITOPRODUCTO

            //Este trigger actualiza el total si algo se actualiza en relacion_carrito_producto lo vuelve a sumar
            if (!triggerExiste(conexion, "Actualizar_total")) {
                script.execute("CREATE TRIGGER Actualizar_total " +
                        "AFTER INSERT ON relacion_carrito_producto " +
                        "FOR EACH ROW BEGIN " +
                        "    UPDATE carrito_compra c " +
                        "    JOIN ( " +
                        "        SELECT id_carrito, SUM(subtotal) AS total_subtotal " +
                        "        FROM relacion_carrito_producto " +
                        "        WHERE id_carrito = NEW.id_carrito " +
                        "        GROUP BY id_carrito " +
                        "    ) r ON c.id_carrito = r.id_carrito " +
                        "    SET c.total = r.total_subtotal; " +
                        "END;");
                System.out.println("Trigger 'Actualizar_total' creado.");
            }

            //Este vuelve a sumar el total cuando se agrega algo a relacion_carrito_producto
            if (!triggerExiste(conexion, "actualizar_total_update")) {
                script.execute("CREATE TRIGGER actualizar_total_update " +
                        "AFTER UPDATE ON relacion_carrito_producto " +
                        "FOR EACH ROW BEGIN " +
                        "    UPDATE carrito_compra c " +
                        "    JOIN ( " +
                        "        SELECT id_carrito, SUM(subtotal) AS total_subtotal " +
                        "        FROM relacion_carrito_producto " +
                        "        WHERE id_carrito = NEW.id_carrito " +
                        "        GROUP BY id_carrito " +
                        "    ) r ON c.id_carrito = r.id_carrito " +
                        "    SET c.total = r.total_subtotal; " +
                        "END;");
                System.out.println("Trigger 'actualizar_total_update' creado.");
            }

            //Este resta cuando elimiamos un probucto en relacion_carrito_producto y actualiza el total
            if (!triggerExiste(conexion, "actualizar_total_delete")) {
                script.execute("CREATE TRIGGER actualizar_total_delete " +
                        "AFTER DELETE ON relacion_carrito_producto " +
                        "FOR EACH ROW BEGIN " +
                        "    UPDATE carrito_compra c " +
                        "    SET c.total = c.total - OLD.subtotal " +
                        "    WHERE c.id_carrito = OLD.id_carrito; " +
                        "END;");
                System.out.println("Trigger 'actualizar_total_delete' creado.");
            }
        }
    }

    public boolean triggerExiste(Connection conexion, String nombreTrigger) throws SQLException {
        String query = "SELECT COUNT(*) FROM information_schema.TRIGGERS " +
                "WHERE TRIGGER_SCHEMA = DATABASE() AND TRIGGER_NAME = '" + nombreTrigger + "'";

        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
        return false;
    }
}