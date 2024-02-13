package edu.eci.arep;

import java.util.Map;

/**
 * Interface that defines a web service.
 *
 * @author Jefer Alexis Gonzalez Romero
 * @version 1.0
 * @since 2024-02-12
 */
public interface WebService {

    public String handle(Map<String, String> params);
}
