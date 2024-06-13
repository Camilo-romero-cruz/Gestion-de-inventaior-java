package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CConexions {

    Connection conectar;
    String usuario = "root"; // Nombre de usuario de la base de datos
    String contraseña = ""; // Contraseña de la base de datos
    String bd = "bibliote"; // Nombre de la base de datos
    String ip = "127.0.0.1"; // Dirección IP del servidor de la base de datos
    String puerto = "3306"; // Puerto de la base de datos
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd ;

    // Método para establecer una conexión a la base de datos
    public Connection estableceConexion() {
        try {
            // Cargar el controlador JDBC para MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión a la base de datos utilizando los valores especificados
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);

            // Mostrar un mensaje de éxito en la conexión
            JOptionPane.showMessageDialog(null, "Conexión exitosa");

        } catch (Exception e) {
            // Capturar y manejar cualquier excepción que ocurra durante la conexión
            JOptionPane.showMessageDialog(null, "Problemas en la conexión: " + e.toString());
        }
        return conectar; // Devolver la conexión establecida
    }
}
