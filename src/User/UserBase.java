package User;
import java.util.ArrayList;


public class UserBase {

    // Atributos

    private ArrayList<Usuario> UserBase;
    private int MaxUserAmount;

    private String RestartPass = "ABC123";

    // Constructor

    public UserBase(){

        UserBase = new ArrayList<Usuario>(MaxUserAmount);


    }

    // Metodos

    public void RestartUserBase(int Max, String Pass){

        if (Pass == RestartPass){

            if (Max > 0){

                MaxUserAmount = Max;
    
            }

            UserBase = new ArrayList<Usuario>(MaxUserAmount);

        }
    }

    public Usuario findUserFromUser(Usuario user) {
        for (int i = 0; i < UserBase.size(); i++) {

            Usuario u = UserBase.get(i);
            if (u.equals(user)) {
                return u; // Muy redundante, ya se, pero lo voy a dejar porque ya escribi el codigo
            }
        }

        return null;
    }

    public Usuario findUserFromID(int id) {

        if (id >= 0 && id < UserBase.size()) {

            return UserBase.get(id);
        }

        return null;
    }

    public Usuario findUserFromName(String name) {
        for (int i = 0; i < UserBase.size(); i++) {

            Usuario u = UserBase.get(i);

            if (u.getNombre().equals(name)) {
                return u;
            }
            
        }
        return null;
    }

    public Usuario findUserFromEmail(String email) {
        for (int i = 0; i < UserBase.size(); i++) {

            Usuario u = UserBase.get(i);

            if (u.getEmail().equals(email)) {

                return u;

            }
        }

        return null;
    }

    public void addUser(Usuario user) {
        if (!UserBase.contains(user)) {
            UserBase.add(user);
        }
    }

    public void removeUser(Usuario user) {
        UserBase.remove(user);
    }

}
