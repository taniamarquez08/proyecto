package RegistroCitas;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBase {

    private ArrayList<Appointment> AppointmentBase;
    private int MaxAppointment = 5;

    public AppointmentBase(){

        AppointmentBase = new ArrayList<Appointment>(MaxAppointment);


    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(AppointmentBase);
    }

    public void RestartAppointmentBase(int Max, String Pass){

        AppointmentBase = new ArrayList<Appointment>(MaxAppointment);

    }

    public Appointment GetAppointmentFromID(int ID){

        return AppointmentBase.get(ID);

    }

    public void addAppointment(Appointment ap){

        if (!AppointmentBase.contains(ap)) {
            AppointmentBase.add(ap);
        }
        
    }

    public void removeAppointment(Appointment ap){
        
        AppointmentBase.remove(ap);

    }

}
