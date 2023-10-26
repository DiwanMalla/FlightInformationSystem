import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class User {
    private String username;
    private String password;

    //Constructor to initialize username and password
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    //Getter and setter methods
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
   
}
