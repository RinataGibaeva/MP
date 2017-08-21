package ru.itpark.dto;

public class  UserRegistrationDto {
    private String name;
    private String password;
    private String login;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String name, String password, String login) {
        this.name = name;
        this.password = password;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
