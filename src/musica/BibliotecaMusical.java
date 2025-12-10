package musica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BibliotecaMusical {

    private final List<Cancion> catalogoCanciones;
    private final List<Usuario> usuarios;

    public BibliotecaMusical() {
        this.catalogoCanciones = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    // --- Gestión de catálogo ---

    public void agregarCancionACatalogo(Cancion cancion) {
        if (cancion == null) {
            throw new IllegalArgumentException("La canción no puede ser null");
        }
        catalogoCanciones.add(cancion);
    }

    public List<Cancion> getCatalogoCanciones() {
        return Collections.unmodifiableList(catalogoCanciones);
    }

    // --- Gestión de usuarios ---

    public void registrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        }
    }

    public List<Usuario> getUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    /**
     * Método extra para usar assertNull / assertNotNull.
     */
    public Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                return u;
            }
        }
        return null;
    }

    // --- Búsquedas de canciones ---

    public List<Cancion> buscarCancionPorTitulo(String titulo) {
        List<Cancion> resultado = new ArrayList<>();
        for (Cancion c : catalogoCanciones) {
            if (c.getTitulo().equalsIgnoreCase(titulo)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Cancion> buscarCancionPorArtista(String artista) {
        List<Cancion> resultado = new ArrayList<>();
        for (Cancion c : catalogoCanciones) {
            if (c.getArtista().equalsIgnoreCase(artista)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    // --- Búsqueda de playlists en todos los usuarios ---

    public List<Playlist> buscarPlaylistPorNombre(String nombre) {
        List<Playlist> resultado = new ArrayList<>();
        for (Usuario u : usuarios) {
            for (Playlist p : u.getPlaylists()) {
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    resultado.add(p);
                }
            }
        }
        return resultado;
    }
}
