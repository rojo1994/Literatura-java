package com.alura.literatura.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            JsonNode node = objectMapper.readTree(json);

            if (node.has("results") && node.get("results").isArray() && node.get("results").size() > 0) {
                JsonNode bookNode = node.get("results").get(0);
                return objectMapper.treeToValue(bookNode, clase);
            } else {
                System.out.println("No se encontraron libros con ese título.");
                return null;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
