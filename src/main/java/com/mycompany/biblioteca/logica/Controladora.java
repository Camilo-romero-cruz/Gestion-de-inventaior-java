package com.mycompany.biblioteca.logica;

import Clases.CCActualizarLibro;
import Clases.CCAgregarLibro;
import java.util.List;

public class Controladora {

    CCAgregarLibro agregar = new CCAgregarLibro();
    CCActualizarLibro actualizar = new CCActualizarLibro();

    public void agregarLibro(String Titulo, String Autor, String Genero,
            String editorial, int NumPaginas, int Cantidad) {

        Libro libro = new Libro();
        libro.setTitulo(Titulo);
        libro.setAutor(Autor);
        libro.setGenero(Genero);
        libro.setEditorial(editorial);
        libro.setNumPaginas(NumPaginas);
        libro.setCantidad(Cantidad);

        agregar.agregarLibro(libro);
    }

    public List<Libro> TraerLibros() {
        return agregar.TraerLibros();

    }

    public void actualizarLibro(int id, String nuevoTitulo, String nuevoAutor, String nuevoGenero,
            String nuevaEditorial, int nuevaNumPaginas, int nuevaCantidad) {
        // Crear una instancia de Libro con los nuevos datos
        Libro libroActualizado = new Libro();
        libroActualizado.setId(id);
        libroActualizado.setTitulo(nuevoTitulo);
        libroActualizado.setAutor(nuevoAutor);
        libroActualizado.setGenero(nuevoGenero);
        libroActualizado.setEditorial(nuevaEditorial);
        libroActualizado.setNumPaginas(nuevaNumPaginas);
        libroActualizado.setCantidad(nuevaCantidad);

        // Llamar al método actualizarLibro de la clase CCActualizarLibro
        actualizar.actualizarLibro(libroActualizado);
    }
}
