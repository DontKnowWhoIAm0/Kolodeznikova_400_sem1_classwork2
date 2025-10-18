package ru.kpfu.itis.Kolodeznikova.entity;

public class User {

    private Integer id;
    private String name;
    private String lastname;
    private String login;
    private String password;
    private String profileImage;

    public User() {
    }

    public User(Integer id, String name, String lastname, String login, String password, String profileImage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.profileImage = profileImage;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
