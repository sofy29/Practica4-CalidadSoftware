package test;

import musica.*;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

public class BibliotecaMusicalTest {

    private static BibliotecaMusical biblioteca;  

    private Usuario usuarioPremium;
    private Usuario usuarioGratis;
    private Cancion c1, c2, c3;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println(">> @BeforeClass - Se crea la biblioteca una sola vez");
        biblioteca = new BibliotecaMusical();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println(">> @AfterClass - Se limpia la biblioteca");
        biblioteca = null;
    }

    @Before
    public void setUp() {
        System.out.println("-> @Before - Preparando datos iniciales para cada test");

        biblioteca = new BibliotecaMusical();

        usuarioPremium = new Usuario("alice", "Premium");
        usuarioGratis = new Usuario("bob", "Gratis");

        biblioteca.registrarUsuario(usuarioPremium);
        biblioteca.registrarUsuario(usuarioGratis);

        c1 = new Cancion("Song A", "Artist 1", 100);
        c2 = new Cancion("Song B", "Artist 1", 120);
        c3 = new Cancion("Song C", "Artist 2", 200);

        biblioteca.agregarCancionACatalogo(c1);
        biblioteca.agregarCancionACatalogo(c2);
        biblioteca.agregarCancionACatalogo(c3);
    }

    @After
    public void tearDown() {
        System.out.println("-> @After - Test finalizado\n");
    }

    // 1) assertEquals
    @Test
    public void testAssertEquals_numCancionesPorArtista() {
        List<Cancion> resultado = biblioteca.buscarCancionPorArtista("Artist 1");
        assertEquals("Debe haber 2 canciones de Artist 1", 700, resultado.size());
    }

    // 2) assertTrue
    @Test
    public void testAssertTrue_listaContieneCancion() {
        List<Cancion> resultado = biblioteca.buscarCancionPorTitulo("Song A");
        assertTrue("La lista debe contener la canción c1", resultado.contains(c1));
    }

    // 3) assertFalse
    @Test
    public void testAssertFalse_usuarioGratisNoPuedeCompartir() {
        Playlist p = usuarioGratis.crearPlaylist("Pop");
        boolean compartido = usuarioGratis.compartirPlaylist(p, usuarioPremium);
        assertFalse("Un usuario Gratis no debe poder compartir playlists", compartido);
    }

    // 4) assertNotNull
    @Test
    public void testAssertNotNull_buscarUsuarioExistente() {
        Usuario u = biblioteca.buscarUsuarioPorNombre("ana");
        assertNotNull("El usuario alice debe existir", u);
    }

    // 5) assertNull
    @Test
    public void testAssertNull_buscarUsuarioInexistente() {
        Usuario u = biblioteca.buscarUsuarioPorNombre("usuario_que_no_existe");
        assertNull("Un usuario inexistente debe devolver null", u);
    }

    // 6) assertSame
    @Test
    public void testAssertSame_mismaReferenciaUsuario() {
        Usuario u = biblioteca.buscarUsuarioPorNombre("alice");
        assertSame("Debe ser la misma referencia de usuarioPremium", usuarioPremium, u);
    }

    // 7) assertNotSame
    @Test
    public void testAssertNotSame_listasPlaylistsDistintas() {
        Playlist p = usuarioPremium.crearPlaylist("Rock");
        usuarioPremium.compartirPlaylist(p, usuarioGratis);

        assertNotSame("Las listas de playlists deben ser objetos distintos",
                usuarioPremium.getPlaylists(), usuarioGratis.getPlaylists());
    }

    // 8) assertArrayEquals
    @Test
    public void testAssertArrayEquals_titulosCancionesArtist1() {
        List<Cancion> resultado = biblioteca.buscarCancionPorArtista("Artist 1");

        String[] titulosEsperados = {"Song A", "Song B"};
        String[] titulosObtenidos = resultado.stream()
                                             .map(Cancion::getTitulo)
                                             .toArray(String[]::new);

        assertArrayEquals("Los títulos devueltos no coinciden",
                titulosEsperados, titulosObtenidos);
    }
}
