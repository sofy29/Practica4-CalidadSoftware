package musica.test;

import musica.*;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

public class BibliotecaMusicalPartitionByCategoryTest {

    private BibliotecaMusical biblioteca;
    private Usuario usuario;

    @Before
    public void setUp() {
        biblioteca = new BibliotecaMusical();
        usuario = new Usuario("carlos", "Premium");
        biblioteca.registrarUsuario(usuario);
    }

    // ---- Categoría 1: Métodos de catálogo ----
    @Test
    public void testCategoriaCatalogo_agregarYBuscarCanciones() {
        Cancion c1 = new Cancion("Intro", "Band X", 60);
        Cancion c2 = new Cancion("Outro", "Band X", 90);

        biblioteca.agregarCancionACatalogo(c1);
        biblioteca.agregarCancionACatalogo(c2);

        List<Cancion> porTitulo = biblioteca.buscarCancionPorTitulo("Intro");
        List<Cancion> porArtista = biblioteca.buscarCancionPorArtista("Band X");

        assertEquals(1, porTitulo.size());
        assertEquals(2, porArtista.size());
    }

    // ---- Categoría 2: Métodos de usuarios ----
    @Test
    public void testCategoriaUsuarios_registroYBusqueda() {
        Usuario otro = new Usuario("diana", "Gratis");
        biblioteca.registrarUsuario(otro);

        Usuario encontrado = biblioteca.buscarUsuarioPorNombre("diana");
        Usuario noEncontrado = biblioteca.buscarUsuarioPorNombre("nadie");

        assertNotNull(encontrado);
        assertNull(noEncontrado);
    }

    // ---- Categoría 3: Métodos de playlists ----
    @Test
    public void testCategoriaPlaylists_crearYBuscarPlaylists() {
        Playlist p1 = usuario.crearPlaylist("Gym");
        Playlist p2 = usuario.crearPlaylist("Relax");

        List<Playlist> resultadoGym = biblioteca.buscarPlaylistPorNombre("Gym");
        List<Playlist> resultadoRelax = biblioteca.buscarPlaylistPorNombre("Relax");

        assertEquals(1, resultadoGym.size());
        assertEquals(p1, resultadoGym.get(0));

        assertEquals(1, resultadoRelax.size());
        assertEquals(p2, resultadoRelax.get(0));
    }
}
