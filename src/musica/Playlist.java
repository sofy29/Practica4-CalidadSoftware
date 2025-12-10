package musica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private String nombre;
    private final List<Cancion> canciones;

    public Playlist(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la playlist no puede ser vacío");
        }
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void renombrar(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new IllegalArgumentException("El nuevo nombre no puede ser vacío");
        }
        this.nombre = nuevoNombre;
    }

    public void agregarCancion(Cancion cancion) {
        if (cancion == null) {
            throw new IllegalArgumentException("La canción no puede ser null");
        }
        canciones.add(cancion);
    }

    public boolean eliminarCancion(Cancion cancion) {
        if (cancion == null) {
            return false;
        }
        return canciones.remove(cancion);
    }

    public boolean contieneCancion(Cancion cancion) {
        return canciones.contains(cancion);
    }

    public int getDuracionTotal() {
        int total = 0;
        for (Cancion c : canciones) {
            total += c.getDuracion();
        }
        return total;
    }

    public List<Cancion> getCanciones() {
        return Collections.unmodifiableList(canciones);
    }

    @Override
    public String toString() {
        return "Playlist{" + nombre + ", canciones=" + canciones.size() + "}";
    }
}
