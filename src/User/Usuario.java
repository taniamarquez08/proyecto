package User;

import RegistroCitas.Appointment;
import RegistroCitas.AppointmentBase;
import RegistroCitas.DateTime;
import java.util.List;

public class Usuario {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private String pais;
    
    private Boolean IsDoctor;
    private AppointmentBase MisCitas; 
    private AppointmentBase PublicAppointmentBase;

    public Usuario(String nombre, String apellido, String email, String password, String telefono, String direccion, String ciudad, String estado, String codigoPostal, String pais, String RegistroCitasGlobal) {
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.pais = pais;

        MisCitas = new AppointmentBase();
        PublicAppointmentBase = RegistroCitasGlobal;

        IsDoctor = false;
    }

    public Usuario(String nombre2, String apellido2, String email2, String password2, String telefono2,
            String direccion2, String ciudad2, String estado2, String codigoPostal2, String pais2,
            AppointmentBase appointmentBase) {
        //TODO Auto-generated constructor stub
    }

    public void CrearCita(String Motivo, DateTime Inicio){
        if (IsDoctor){

            Appointment Cita = new Appointment(this, Motivo, Inicio);
            PublicAppointmentBase.addAppointment(Cita);

        }
    }
    
    public List<Appointment> getPublicAppointments() {
        return PublicAppointmentBase.getAppointments();
    }

    public List<Appointment> GetMyAppointments(){

        return MisCitas.getAppointments();
        
    }
    
    public void AñadirCita(Appointment Cita){

        MisCitas.addAppointment(Cita);
        PublicAppointmentBase.removeAppointment(Cita);

    }

    public void CancelarCita(Appointment Cita){
        PublicAppointmentBase.addAppointment(Cita);
        MisCitas.removeAppointment(Cita);
    }

    public Boolean getDoctor(){

        return IsDoctor;

    }

    public void setDoctor(){

        IsDoctor = true;

    }

    public void quitDoctor(){

        IsDoctor = false;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Usuario [apellido=" + apellido + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal + ", direccion="
                + direccion + ", email=" + email + ", estado=" + estado + ", nombre=" + nombre + ", pais=" + pais
                + ", password=" + password + ", telefono=" + telefono + "]";
    }
        
}