package musica;

public class Cancion {
    private final String titulo;
    private final String artista;
    private final int duracion; // en segundos

    public Cancion(String titulo, String artista, int duracion) {
        if (titulo == null || artista == null || duracion <= 0) {
            throw new IllegalArgumentException("Datos de canción no válidos");
        }
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + duracion + "s)";
    }
}
