package Clases;

import com.mycompany.biblioteca.logica.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class CCActualizarLibro {

    public void actualizarLibro(Libro libroActualizado) {
        try {
            // Crear una instancia de la clase CConexion para establecer una conexión a la base de datos
            CConexions objetoConexion = new CConexions();
            Connection connection = objetoConexion.estableceConexion();

            // Definir la consulta SQL para actualizar los datos del libro con el ID especificado
            String updateQuery = "UPDATE Libros SET titulo=?, autor=?, genero=?, editorial=?, num_paginas=?, cantidad_disponible=? WHERE id=?";

            // Preparar la sentencia SQL
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // Establecer los valores de los parámetros en la sentencia SQL
                preparedStatement.setString(1, libroActualizado.getTitulo());
                preparedStatement.setString(2, libroActualizado.getAutor());
                preparedStatement.setString(3, libroActualizado.getGenero());
                preparedStatement.setString(4, libroActualizado.getEditorial());
                preparedStatement.setInt(5, libroActualizado.getNumPaginas());
                preparedStatement.setInt(6, libroActualizado.getCantidad());
                preparedStatement.setInt(7, libroActualizado.getId());

                // Ejecutar la actualización en la base de datos y obtener el número de filas afectadas
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    // Si se actualizó al menos una fila, mostrar un mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Libro actualizado con éxito. ID: " + libroActualizado.getId(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Si no se encontró el libro, mostrar un mensaje de error
                    JOptionPane.showMessageDialog(null, "ID de libro no encontrado. No se ha actualizado ningún libro.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            // Capturar y manejar cualquier excepción que ocurra durante el proceso
            JOptionPane.showMessageDialog(null, "Error: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
