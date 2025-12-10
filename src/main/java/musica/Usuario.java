package musica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private final String nombreUsuario;
    private String tipoSuscripcion; // "Gratis" o "Premium"
    private final List<Playlist> playlists;

    public Usuario(String nombreUsuario, String tipoSuscripcion) {
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            throw new IllegalArgumentException("Nombre de usuario no válido");
        }
        if (tipoSuscripcion == null) {
            tipoSuscripcion = "Gratis";
        }
        this.nombreUsuario = nombreUsuario;
        this.tipoSuscripcion = tipoSuscripcion;
        this.playlists = new ArrayList<>();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(String tipoSuscripcion) {
        if (tipoSuscripcion == null || tipoSuscripcion.isBlank()) {
            throw new IllegalArgumentException("Tipo de suscripción no válido");
        }
        this.tipoSuscripcion = tipoSuscripcion;
    }

    public Playlist crearPlaylist(String nombrePlaylist) {
        Playlist p = new Playlist(nombrePlaylist);
        playlists.add(p);
        return p;
    }

    public boolean eliminarPlaylist(Playlist playlist) {
        if (playlist == null) {
            return false;
        }
        return playlists.remove(playlist);
    }

    /**
     * Comparte una playlist con otro usuario.
     * Solo los usuarios "Premium" pueden compartir.
     * Se añade una referencia a la misma playlist al otro usuario.
     */
    public boolean compartirPlaylist(Playlist playlist, Usuario otroUsuario) {
        if (!"Premium".equalsIgnoreCase(tipoSuscripcion)) {
            return false;
        }
        if (playlist == null || otroUsuario == null) {
            return false;
        }
        if (!playlists.contains(playlist)) {
            return false; // solo puede compartir playlists que sean suyas
        }
        otroUsuario.playlists.add(playlist);
        return true;
    }

    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(playlists);
    }

    @Override
    public String toString() {
        return "Usuario{" + nombreUsuario + ", tipo=" + tipoSuscripcion + "}";
    }
}
