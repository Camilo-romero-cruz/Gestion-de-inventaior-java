package Clases;

import com.mycompany.biblioteca.logica.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CCAgregarLibro {

    public void agregarLibro(Libro libro) {
        try {
            // Establecer la conexión a la base de datos
            CConexions objetoConexion = new CConexions();
            Connection connection = objetoConexion.estableceConexion();

            // Definir la consulta SQL para insertar un libro en la tabla Libros
            String insertQuery = "INSERT INTO Libros (titulo, autor, genero, editorial, num_paginas, cantidad_disponible) VALUES (?, ?, ?, ?, ?, ?)";

            // Obtener los datos del libro
            String libroTitulo = libro.getTitulo();
            String libroAutor = libro.getAutor();
            String libroGenero = libro.getGenero();
            String libroEditorial = libro.getEditorial();
            int libroNumPaginas = libro.getNumPaginas();
            int cantidadDisponible = libro.getCantidad();

            // Preparar la sentencia SQL y especificar que se desean recuperar las claves generadas
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, libroTitulo);
                preparedStatement.setString(2, libroAutor);
                preparedStatement.setString(3, libroGenero);
                preparedStatement.setString(4, libroEditorial);
                preparedStatement.setInt(5, libroNumPaginas);
                preparedStatement.setInt(6, cantidadDisponible);

                // Ejecutar la inserción en la base de datos
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Recuperar las claves generadas (el ID del libro)
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int libroID = generatedKeys.getInt(1);
                            JOptionPane.showMessageDialog(null, "Libro registrado con éxito. ID: " + libroID, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al registrar libro. No se generaron claves.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar libro. No se afectaron filas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Cerrar la conexión
            connection.close();

        } catch (Exception e) {
            // Capturar y manejar cualquier excepción que ocurra durante el proceso
            JOptionPane.showMessageDialog(null, "Error al registrar libro: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Libro> TraerLibros() {
        // Esbozo del método para traer libros desde la base de datos
        List<Libro> libros = new ArrayList<>();

        try {
            // Establecer la conexión a la base de datos
            CConexions objetoConexion = new CConexions();
            Connection connection = objetoConexion.estableceConexion();

            // Definir la consulta SQL para obtener libros de la tabla Libros
            String selectQuery = "SELECT * FROM Libros";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                // Ejecutar la consulta
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Procesar los resultados y crear objetos Libro
                    while (resultSet.next()) {
                        Libro libro = new Libro();
                        libro.setId(resultSet.getInt("id"));  // Ajusta según la columna que almacena el ID en tu base de datos
                        libro.setTitulo(resultSet.getString("titulo"));
                        libro.setAutor(resultSet.getString("autor"));
                        libro.setGenero(resultSet.getString("genero"));
                        libro.setEditorial(resultSet.getString("editorial"));
                        libro.setNumPaginas(resultSet.getInt("num_paginas"));
                        libro.setCantidad(resultSet.getInt("cantidad_disponible"));

                        libros.add(libro);
                    }
                }
            }

            // Cerrar la conexión
            connection.close();

        } catch (Exception e) {
            // Capturar y manejar cualquier excepción que ocurra durante el proceso
            JOptionPane.showMessageDialog(null, "Error al obtener libros: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return libros;
    }
}
