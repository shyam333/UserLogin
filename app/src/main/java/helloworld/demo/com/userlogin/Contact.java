package helloworld.demo.com.userlogin;

/**
 * Created by shyamramesh on 08/05/18.
 */

public class Contact {

    public String ID;
    public String Name;
    public String Email;
    public String Password;


    public Contact(String ID, String name, String email, String password) {
        this.ID = ID;
        Name = name;
        Email = email;
        Password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
