package edu.eci.arep;

import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;


/**
 * This class implements web services
 *
 * @author Jefer Alexis Gonzalez Romero
 * @version 1.0
 * @since 2024-02-12
 */
public class MyWebServices {

    private static final ConcurrentHashMap<String, String> CACHE = new ConcurrentHashMap<>();
    private static final MovieDataProvider movieDataProvider = new OMDbMovieDataProvider();

    /**
     * Main entry point of the application. Initializes the web services and runs the server.
     *
     * @param args The command line arguments (unused in this case).
     * @throws URISyntaxException If an error occurs while creating a URI.
     */
    public static void main(String[] args) throws URISyntaxException {
        MovieInfoServer.get("/movies", p -> {
            MovieInfoServer.responseType("application/json");
            return CACHE.computeIfAbsent(p.get("title"), movieDataProvider::fetchMovieData);
        });
        MovieInfoServer.get("/hola", p -> {
            MovieInfoServer.responseType("text/html");
            return "<h1>Hola " + p.get("nombre") + "</h1>";
        });
        // Ejemplo de cómo podría funcionar un post
        MovieInfoServer.post("/users", p -> {
            // Cuerpo para agregar un nuevo usuario
            MovieInfoServer.responseType("text/html");
            return "<h1>Se ha agregado el usuario exitosamente</h1>";
        });
        MovieInfoServer.staticDirectory("public/files-copy");
        MovieInfoServer.getInstance().runServer();
    }

}
