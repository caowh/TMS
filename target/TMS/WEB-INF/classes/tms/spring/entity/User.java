package tms.spring.entity;

/**
 * Created by user on 2017/7/14.
 */
public class User {
    private int id;
    private String name;
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
