package Expediente;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

public class SistemaExpedienteTest {

    private SistemaExpediente sistema;
    private ExpedienteNuevo expediente;

    // Configuración inicial para las pruebas
    public void setUp() {
        sistema = new SistemaExpediente();
        expediente = new ExpedienteNuevo(1, "Dolor de cabeza", "Migraña", "Ibuprofeno", "Estudios de sangre", 
                                         "Juan Pérez", "Receta aquí", new Date());
    }



    // Prueba de la autenticación del doctor (ID incorrecto)
    @Test
    public void testAutenticacionIncorrecta() {
        setUp();
        String doctorID = "doctor123";
        String doctorCodigoSeguridad = "incorrecto";
        assertFalse(sistema.autenticarDoctor(doctorID, doctorCodigoSeguridad));
    }


    // Prueba para verificar la existencia del expediente por número
    @Test
    public void testObtenerExpedientePorNumero() {
        setUp();
        sistema.agregarExpediente(expediente);
        ExpedienteNuevo encontrado = sistema.obtenerExpediente(1);
        assertNotNull(encontrado);
        assertEquals(1, encontrado.getNumExpediente());
    }

    // Prueba para intentar obtener un expediente que no existe
    @Test
    public void testObtenerExpedienteNoExistente() {
        setUp();
        sistema.agregarExpediente(expediente);
        ExpedienteNuevo encontrado = sistema.obtenerExpediente(99);
        assertNull(encontrado);
    }

 

    // Prueba para verificar que un expediente tiene los datos correctos
    @Test
    public void testDatosDelExpediente() {
        setUp();
        sistema.agregarExpediente(expediente);
        ExpedienteNuevo exp = sistema.obtenerExpediente(1);
        assertEquals("Juan Pérez", exp.getNombre());
        assertEquals("Migraña", exp.getDiagnostico());
        assertEquals("Ibuprofeno", exp.getTratamiento());
    }

  

   

   
}
