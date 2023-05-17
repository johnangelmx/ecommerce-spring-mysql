package org.generation.ecommerce;

import org.generation.ecommerce.model.Producto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    //TODO - Actualizar el token con uno valido
    private final String token = "Bearer: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZG9lQGhvdG1haWwuY29tIiwicm9sZSI6InVzZXIiLCJpYXQiOjE2ODQyNzUxNzIsImV4cCI6MTY4NDMxMTE3Mn0.crDpb-D5_C3qKJ0aSjr6NKQVg5k2or99eFLrsHxRzsY";

    @Test
    @DisplayName("Prueba Para Obtener(GET) La Lista De Productos")
    void pruebaGET() throws Exception {
        this.mockMvc.perform(get("/api/productos/1"))//url a probar
                .andDo(print())// lo imprime
                .andExpect(status().isOk())//status de la solicitud
                .andExpect(content().string(containsString("bic3.gif")));// verifica que la peticion tenga el contenido

    }// Get

    @Test
    @Disabled("Probado la primera vez, dashabilitado")
    @DisplayName("Prueba Para Borrar(Delete) Un produtoLa Lista De Productos")
    void pruebaDELETE() throws Exception {
        this.mockMvc.perform(delete("/api/productos/7").header("Authorization", token))//url a probar
                .andDo(print())// lo imprime
                .andExpect(status().isOk())//status de la solicitud
                .andExpect(content().string(containsString("WWECuaderno.jpg")));// verifica que la peticion tenga el contenido
    }// Delete

    @Test
    @Disabled
    @DisplayName("Prueba para crear un nuevo producto")
    void pruebaPOST() throws Exception {
        Producto p = new Producto();
        p.setNombre("Cuaderno Profesional TRON / Doble Raya / 75 hojas");
        p.setDescripcion(" Cuaderno Profesional TRON / Doble Raya / 75 hojas - Disney");
        p.setImagen("Cuaderno-tron.jpg");
        p.setPrecio(80.23);

        this.mockMvc.perform(post("/api/productos/")//url a probar
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(p)).header("Authorization", token))//Tipo de contenido y contenido a enviar
                .andDo(print())// lo imprime
                .andExpect(status().isOk())//status de la solicitud
                .andExpect(content().string(containsString("Cuaderno-tron.jpg")));// verifica que la peticion tenga el contenido
    }// POST

    @Test
    @DisplayName("Se modifica el producto 5 con el nuevo precio")
    void pruebaPUT() throws Exception {
        this.mockMvc.perform(put("/api/productos/4").queryParam("precio", "27.08").header("Authorization", token))//url a probar
                .andDo(print())// lo imprime
                .andExpect(status().isOk())//status de la solicitud
                .andExpect(content().string(containsString("27.08")));// verifica que la peticion tenga el contenido
    }

    //? Metodo para convertir objeto a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }//catch
    } // asJsonString


}
