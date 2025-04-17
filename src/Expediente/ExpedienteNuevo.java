package Expediente;

import java.util.Date;

public class ExpedienteNuevo {
    private int numExpediente;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private String estudios;
    private String nombrePaciente; // Agregado para almacenar el nombre del paciente
    private String receta;  // Receta médica generada
    private Date fechaConsulta;  // Fecha de consulta para registrar la fecha exacta

    // Constructor actualizado para incluir los nuevos campos
    public ExpedienteNuevo(int numExpediente, String sintomas, String diagnostico, String tratamiento, 
                            String estudios, String nombrePaciente, String receta, Date fechaConsulta) {
        this.numExpediente = numExpediente;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.estudios = estudios;
        this.nombrePaciente = nombrePaciente;
        this.receta = receta;
        this.fechaConsulta = fechaConsulta;
    }

    // Métodos getters y setters
    public int getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(int numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getNombre() {
        return nombrePaciente; // Retorna el nombre del paciente asociado al expediente
    }

    public void setNombre(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getReceta() {
        return receta; // Retorna la receta médica asociada
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public Date getFechaConsulta() {
        return fechaConsulta; // Retorna la fecha de la consulta
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    // Método para actualizar la fecha de modificación (si es necesario)
    public void setFechaModificacion(String fecha) {
        // Aquí se puede implementar la lógica para actualizar la fecha de modificación
        // Por ejemplo, cambiar el atributo fechaConsulta por la nueva fecha.
        throw new UnsupportedOperationException("Unimplemented method 'setFechaModificacion'");
    }
}

