package musica.test;

import musica.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BibliotecaMusicalTestFallos {

    private BibliotecaMusical biblioteca;
    private Usuario usuarioPremium;
    private Usuario usuarioGratis;
    private Cancion c1, c2, c3;

    @Before
    public void setUp() {
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

    // 1) FALLA: esperamos 4 en vez de 2
    @Test
    public void testFallo_assertEquals() {
        List<Cancion> resultado = biblioteca.buscarCancionPorArtista("Artist 1");
        assertEquals("Este test debe FALLAR: esperamos 4", 4, resultado.size());
    }

    // 2) FALLA: afirmamos que una lista vacía NO está vacía
    @Test
    public void testFallo_assertTrue() {
        List<Cancion> resultado = biblioteca.buscarCancionPorTitulo("NoExiste");
        assertTrue("Este test debe FALLAR: la lista está vacía", !resultado.isEmpty());
    }

    // 3) FALLA: esperamos null pero el usuario sí existe
    @Test
    public void testFallo_assertNull() {
        Usuario u = biblioteca.buscarUsuarioPorNombre("alice");
        assertNull("Este test debe FALLAR: alice no es null", u);
    }

    // 4) FALLA: el array esperado está mal a propósito
    @Test
    public void testFallo_assertArrayEquals() {
        List<Cancion> resultado = biblioteca.buscarCancionPorArtista("Artist 1");

        String[] titulosEsperados = {"Song A"}; // mal a propósito
        String[] titulosObtenidos = resultado.stream()
                                             .map(Cancion::getTitulo)
                                             .toArray(String[]::new);

        assertArrayEquals("Este test debe FALLAR: los arrays no coinciden",
                titulosEsperados, titulosObtenidos);
    }
}
