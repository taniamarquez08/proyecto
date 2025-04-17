package RegistroCitas;

import User.Usuario;

public class Appointment {

     private Usuario Doctor;
     private Usuario Paciente;
     private String Motivo;
     private DateTime Inicio;
    
    private Boolean IsOccupied;

    public void setDoctor(Usuario D){

        if (Doctor.getDoctor()){

            Doctor = D;

        }

    }

    public Usuario getDoctor(){

        return Doctor;

    }

     public Appointment(Usuario Doc, String Mot, DateTime I){

        Doctor = Doc;
        Motivo = Mot;
        Inicio = I;

        IsOccupied = false;
     }

     public void setPaciente(Usuario Pac){

        if (IsOccupied =  false){

            IsOccupied = true;
            Paciente = Pac;
            
        }
     }

     public String getString(){

        return "Cita con Dr/a " + Doctor.getNombre() + " " + Doctor.getApellido() + 
        "\n Inicio: " + Inicio.getString() + " \n Para mas informacion contacte al correo: "
        + Doctor.getEmail() + " o al numero: " + Doctor.getTelefono();
     }
    
}
