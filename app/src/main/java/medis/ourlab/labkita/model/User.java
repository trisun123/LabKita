package medis.ourlab.labkita.model;

/**
 * Created by tri on 10/3/2016.
 */

import java.io.Serializable;

/**
 * Created by Lincoln on 07/01/16.
 */
public class User implements Serializable {
    String id, name, email,namae,foto,motto;
    double balance ;

    public User() {
    }

    public User(String id, String name, String email, String namae,double balance,String foto, String motto) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.namae = namae;
        this.balance = balance;
        this.foto = foto;
        this.motto= motto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNamae() {
        return namae;
    }

    public String getFoto() {
        return foto;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNamae(String namae) {
        this.namae = namae;
    }

    public void setBalance(double balance) {
        this.balance= balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotto(String motto) {
        this.motto= motto;
    }
    public String getMotto() {
        return motto;
    }
}
