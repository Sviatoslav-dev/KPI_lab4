package sample;
import java.io.*;

public class Account implements Serializable{
    private String username;
    private String password;
    private String type;

    public String getUsername () {
        return username;
    }

    public void setUsername (String un) {
        username = un;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String pw) {
        password = pw;
    }

    public String getType () {
        return type;
    }

    public void setType (String tp) {
        type = tp;
    }
}
