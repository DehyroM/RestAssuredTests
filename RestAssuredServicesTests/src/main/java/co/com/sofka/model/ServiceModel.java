package co.com.sofka.model;

import com.github.javafaker.Faker;

public class ServiceModel {

    Faker faker = new Faker();
    String name = faker.name().firstName();
    String lastName = faker.name().lastName();
    String job = faker.job().field();
    String emailLogin = name+"."+lastName+"@gmail.com";
    String passwordLogin = name +"_"+job;

    private String userName;
    private String jobTitle;
    private String email;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName() {
        this.userName = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle() {
        this.jobTitle = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = emailLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = passwordLogin;
    }
}
