import Expediente.*;
import MenuPrincipal.MenuInicio;
import RegistroCitas.*;
import User.*;

public class App {
    public static void main(String[] args) throws Exception {

        // Placeholders:
        AppointmentBase dummyAppointment = new AppointmentBase();
        Usuario dummyUser = new Usuario(
            "Default", "", "", "", "", "", "", "", "", "",
            dummyAppointment
        );

        // Ejecución inicial desde main:
        new MenuInicio(dummyUser);

        
    }
}
